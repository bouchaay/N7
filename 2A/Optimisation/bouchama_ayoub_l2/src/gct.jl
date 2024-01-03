using LinearAlgebra
"""
Approximation de la solution du problème 

    min qₖ(s) = s'gₖ + 1/2 s' Hₖ s, sous la contrainte ‖s‖ ≤ Δₖ

# Syntaxe

    s = gct(g, H, Δ; kwargs...)

# Entrées

    - g : (Vector{<:Real}) le vecteur gₖ
    - H : (Matrix{<:Real}) la matrice Hₖ
    - Δ : (Real) le scalaire Δₖ
    - kwargs  : les options sous formes d'arguments "keywords", c'est-à-dire des arguments nommés
        • max_iter : le nombre maximal d'iterations (optionnel, par défaut 100)
        • tol_abs  : la tolérence absolue (optionnel, par défaut 1e-10)
        • tol_rel  : la tolérence relative (optionnel, par défaut 1e-8)

# Sorties

    - s : (Vector{<:Real}) une approximation de la solution du problème

# Exemple d'appel

    g = [0; 0]
    H = [7 0 ; 0 2]
    Δ = 1
    s = gct(g, H, Δ)

"""
function gct(g::Vector{<:Real}, H::Matrix{<:Real}, Δ::Real; 
    max_iter::Integer = 100, 
    tol_abs::Real = 1e-10, 
    tol_rel::Real = 1e-8)

    # Initialisation
    s = zeros(length(g))
    p = -g
    j = 0

    # Fonction q
    function q(sk, pk, Σ)
        return g' * (sk + Σ*pk) + 0.5 * (sk + Σ*pk)' * H * (sk + Σ*pk)
    end
    
    while (j < 2*length(g)) && (norm(g) > max(tol_rel * norm(g), tol_abs))
        
        # Calcul de Σ1 et Σ2
        k = p' * H * p
        a = norm(p)^2
        b = 2 * p' * s
        c = norm(s)^2 - Δ^2
        Σ1 = (-b + (sqrt(b^2 - 4*a*c))) / (2*a)
        Σ2 = (-b - (sqrt(b^2 - 4*a*c))) / (2*a)

        # Calcul de q1 et q2
        if k <= 0
            q1 = q(s, Σ1, p)
            q2 = q(s, Σ2, p)

            # Mise à jour de s
            if q1 < q2
                s = s + Σ1*p
            else
                s = s + Σ2*p
            end
            return s
        end

        # Calcul de α
        α  = (g'*g) / k

        # Mise à jour de s
        if norm(s + α*p) >= Δ
            if Σ1 >= 0
                s = s + Σ1*p
            else
                s = s + Σ2*p
            end
            return s
        end
        
        # Mise à jour des variables
        s = s + α*p
        gk = g
        g = g + α*H*p
        β = g'*g/(gk'*gk)
        p = -g + β*p
        j = j + 1
    end

    return s
end

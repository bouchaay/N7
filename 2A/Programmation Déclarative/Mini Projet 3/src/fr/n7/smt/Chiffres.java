// Auteur : Ayoub Bouchama (aboucham) | Oussama El Guerraoui (oelguerr)

// 1- au maximum {2*(nombres_constantes_entrées) - 1} étapes de calcul.
package fr.n7.smt;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



import com.microsoft.z3.*;

/**
 * Classe qui implémente l'algorithme de BMC pour la partie "chiffres"
 * du jeu "des chiffres et des lettres".
 */
public class Chiffres {

    /**
     * Contexte utilisé par l'instance Chiffres pour créer les formules,
     * solveurs, etc.
     */
    private Context context;

    /** Cache de constantes booléennes, indicé par leur nom. */
    private HashMap<String, BoolExpr> boolCache = new HashMap<>();

    /** Cache de constantes booléennes, indicé par leur nom. */
    private HashMap<String, BitVecExpr> bvCache = new HashMap<>();

    /** Cache de constantes booléennes, indicé par leur nom. */
    private HashMap<String, IntExpr> intCache = new HashMap<>();

    /** Cache de constantes booléennes, indicé par leur nom. */
    private HashMap<String, ArrayExpr> arrayCache = new HashMap<>();

    /** Nombre de bits de la représentation des bitvectors. */
    private int bvBits;

    /** Sorte bitvector de taille bvBits. */
    private Sort bvSort;

    /**
     * Sorte tableaux à indices intSort et elements bitvectors de
     * taille bvBits.
     */
    private Sort bvArraySort;

    /** Sorte des entiers mathématiques. */
    private Sort intSort;

    /** Constantes numériques pour le calcul du système de transition. */
    private int[] nums;

    /** Valeur cible du calcul du système de transition. */
    private int target;

    /** Nombre maximum d'étapes de calcul du système de transition. */
    private int maxNofSteps;

    /** Si vrai alors interdiction d'overflow sur les operations bitvector. */
    private boolean noOverflows;
    private BigInteger maxBvRange;
    private BigInteger minBvRange;

    /**
     * Initialise tous les attributs de la classe: paramètres utilisateur,
     * contexte, sortes.
     */
    public Chiffres(int[] _nums, int _target, int _bvBits, boolean _noOverflows) {
        nums        = _nums;
        target      = _target;
        bvBits      = _bvBits;
        maxBvRange  = new BigInteger("2").pow(bvBits-1).subtract(new BigInteger("1"));
        minBvRange  = new BigInteger("2").pow(bvBits-1).negate();
        maxNofSteps = 2 * nums.length - 1;
        noOverflows = _noOverflows;

        HashMap<String, String> cfg = new HashMap<>();
        cfg.put("model", "true");
        cfg.put("proof", "true");

        context     = new Context(cfg);
        intSort     = context.mkIntSort();
        bvSort      = context.mkBitVecSort(bvBits);
        bvArraySort = context.mkArraySort(intSort, bvSort);
        boolCache   = new HashMap<>();
        bvCache     = new HashMap<>();
        intCache    = new HashMap<>();
        arrayCache  = new HashMap<>();
    }

    /**
     * Retourne la constante du cache si présente, ou bien en crée une
     * nouvelle avec le nom donné si absente.
     */
    private BoolExpr boolConst(String name) {
        BoolExpr res = boolCache.get(name);

        if (res == null) {
            res = context.mkBoolConst(name);
            boolCache.put(name, res);
        }

        return res;
    }

    /**
     * Retourne la constante du cache si présente, ou bien en crée une
     * nouvelle avec le nom donné si absente.
     */
    private BitVecExpr bvConst(String name) {
        BitVecExpr res = bvCache.get(name);

        if (res == null) {
            res = context.mkBVConst(name, bvBits);
            bvCache.put(name, res);
        }

        return res;
    }

    /**
     * Retourne la constante du cache si présente, ou bien en crée une
     * nouvelle avec le nom donné si absente.
     */
    private IntExpr intConst(String name) {
        IntExpr res = intCache.get(name);

        if (res == null) {
            res = context.mkIntConst(name);
            intCache.put(name, res);
        }

        return res;
    }

    /**
     * Retourne la constante du cache si présente, ou bien en crée une
     * nouvelle avec le nom donné si absente.
     */
    private ArrayExpr arrayConst(String name) {
        ArrayExpr res = arrayCache.get(name);

        if (res == null) {
            res = context.mkArrayConst(name, intSort, bvSort);
            arrayCache.put(name, res);
        }

        return res;
    }

    /** Expression vraie ssi exactement une des exprs est vraie. */
    private BoolExpr exactlyOne(BoolExpr... exprs) {
        return context.mkAnd(context.mkOr(exprs), atMostOne(exprs));
    }

    /** Expression vraie ssi au plus une des exprs est vraie. */
    private BoolExpr atMostOne(BoolExpr... exprs) {
        ArrayList<BoolExpr> conjuncts = new ArrayList<>();

        for (BoolExpr expr : exprs) {
            ArrayList<BoolExpr> otherExprs = new ArrayList<>();
            for (BoolExpr e : exprs) {
                if (e != expr) {
                    otherExprs.add(e);
                }
            }

            BoolExpr bigOr = context.mkOr(otherExprs.stream().toArray(BoolExpr[]::new));
            BoolExpr res = context.mkImplies(expr, context.mkNot(bigOr));

            conjuncts.add(res);
        }

        return context.mkAnd(conjuncts.stream().toArray(BoolExpr[]::new));
    }

    /** Convertit un int java en valeur symbolique bitvector Z3. */
    private BitVecNum toBvNum(int num) {
        if (noOverflows) {
            BigInteger bigNum = new BigInteger(String.valueOf(num));

            if (bigNum.compareTo(minBvRange) >= 0 && bigNum.compareTo(maxBvRange) <= 0)
                return context.mkBV(num, bvBits);
            else
                throw new Error("le numeral " + String.valueOf(num) +
                                " dépasse la capacité des bitvectors signés de taille " +
                                String.valueOf(bvBits));
        } else {
            return context.mkBV(num, bvBits);
        }
    }

    /**
     * Trigger de l'action "pousser un numéral sur la pile" pour le pas
     * d'execution "step" du calcul.
     */
    private BoolExpr pushNumVar(int step, int num) {
        return boolConst("push_" + String.valueOf(num) + "@" +
                         String.valueOf(step));
    }

    /**
     * Trigger de l'action "additionner les deux éléments du dessus de
     * pile et pousser le resultat en dessus de pile" pour le pas
     * d'execution "step" du calcul.
     */
    private BoolExpr addVar(int step) {
        return boolConst("add@" + String.valueOf(step));
    }

    /**
     * Trigger de l'action "soustraire les deux éléments du dessus de
     * pile et pousser le resultat en dessus de pile" pour le pas
     * d'execution "step" du calcul.
     */
    private BoolExpr subVar(int step) {
        return boolConst("sub@" + String.valueOf(step));
    }

    /**
     * Trigger de l'action "multiplier les deux éléments du dessus de
     * pile et pousser le resultat en dessus de pile" pour le pas
     * d'execution "step" du calcul.
     */
    private BoolExpr mulVar(int step) {
        return boolConst("mul@" + String.valueOf(step));
    }

    /**
     * Trigger de l'action "diviser les deux éléments du dessus de
     * pile et pousser le resultat en dessus de pile" pour le pas
     * d'execution "step" du calcul.
     */
    private BoolExpr divVar(int step) {
        return boolConst("div@" + String.valueOf(step));
    }

    /** Variable d'état représentant la pile au pas d'exécution "step". */
    private ArrayExpr stackStateVar(int step) {
        return arrayConst("stack@" + String.valueOf(step));
    }

    /**
     * Variable d'état représentant l'indice de dessus de pile au pas
     * d'exécution "step".
     */
    private IntExpr idxStateVar(int step) {
        return intConst("idx@" + String.valueOf(step));
    }

    /**
     * Formule de transition, vraie ssi l'état au pas step et au pas
     * step + 1 sont liés par une action "push(num)".
     */
    private BoolExpr pushNumFormula(int step, int num) {
   
        // État de la pile à l'étape actuelle
        ArrayExpr s_i = stackStateVar(step);
        IntExpr idx_i = idxStateVar(step);

        // État de la pile à l'étape suivante
        ArrayExpr s_iPlus1 = stackStateVar(step + 1);
        IntExpr idx_iPlus1 = idxStateVar(step + 1);

        // transition de l'indice
        BoolExpr idxTransition = context.mkEq(idx_iPlus1, context.mkAdd(idx_i, context.mkInt(1)));

        // transition de la pile
        BoolExpr stackTransition = context.mkAnd(idxTransition, context.mkEq(s_iPlus1, context.mkStore(s_i, idx_i, context.mkBV(num, bvBits))));

        for(int i = 0; i <= step; i++) {
            stackTransition = context.mkAnd(stackTransition, context.mkNot(pushNumVar(i, num)));
        }

        stackTransition = context.mkImplies(pushNumVar(step, num), stackTransition);

        return stackTransition;

    }


    private interface ActionVar {
        /**
         * Retourne la variable trigger de l'action au step donné.
         */
        BoolExpr get(int step);
    }

    private interface ActionResult {
        /**
         * Retourne l'expression du résultat de l'action au step donné
         * en fonction de e1 et e2, les deux valeurs du dessus de la pile.
         */
        BitVecExpr get(int step, BitVecExpr e1, BitVecExpr e2);
    }

    private interface ActionPrecondition {
        /**
         * Retourne l'expression de la précondition de l'action au step
         * donné en fonction de e1 et e2, les deux valeurs du dessus de
         * la pile.
         */
        BoolExpr get(int step, BitVecExpr e1, BitVecExpr e2);
    }


    private BoolExpr actionFormula(int step, ActionVar actVar, ActionPrecondition precond, ActionResult opRes) {
        // État de la pile et de l'indice à l'étape actuelle
        ArrayExpr s_i = stackStateVar(step);
        IntExpr idx_i = idxStateVar(step);
    
        // État de la pile et de l'indice à l'étape suivante
        ArrayExpr s_iPlus1 = stackStateVar(step + 1);
        IntExpr idx_iPlus1 = idxStateVar(step + 1);
    
        // Vérifier que l'action est en cours à l'étape actuelle
        BoolExpr isAction = actVar.get(step);
    
        // Vérifier que la pile contient au moins deux éléments
        BoolExpr isContent2 = context.mkAnd(isAction, context.mkGe(idx_i, context.mkInt(2)));
    
        // Obtenir les deux éléments du haut de la pile
        BitVecExpr e1 = (BitVecExpr) context.mkSelect(s_i, context.mkSub(idx_i, context.mkInt(1)));
        BitVecExpr e2 = (BitVecExpr) context.mkSelect(s_i, context.mkSub(idx_i, context.mkInt(2)));
    
        // Préconditions de l'action
        BoolExpr precondAction = context.mkAnd(isContent2, precond.get(step, e1, e2));
    
        // Résultat de l'action
        BitVecExpr res = opRes.get(step + 1, e1, e2);
    
        // Mettre à jour l'état de la pile avec le résultat de l'action
        ArrayExpr updatedStack = context.mkStore(s_i, context.mkSub(idx_i, context.mkInt(2)), res);
    
 
       
    
        // État de la pile après l'exécution de l'action
        BoolExpr stackUpdated = context.mkEq(s_iPlus1, updatedStack);
    
        // Indice de la pile après l'exécution de l'action
        BoolExpr idxUpdated = context.mkEq(idx_iPlus1, context.mkSub(idx_i, context.mkInt(1)));
    
        // La formule finale combine les préconditions et les mises à jour
        BoolExpr result = context.mkAnd(precondAction, stackUpdated, idxUpdated);
    
        // Créer une implication pour l'action
        BoolExpr finalFormula = context.mkImplies(isAction, result);
    
        return finalFormula;
    }

    /**
     * Formule de transition, vraie ssi l'état au pas step et au pas
     * step + 1 sont liés par une action "addition".
     */
    private BoolExpr addFormula(int step) {

        ActionVar addVar = this::addVar;

        ActionPrecondition addPrecond = (currentStep, v1, v2) -> {
            return context.mkTrue();
        };

        ActionResult addRes = (currentStep, v1, v2) -> {
            return context.mkBVAdd(v1, v2);
        };


        return actionFormula(step, addVar, addPrecond, addRes);

        
        

    }

    /**
     * Formule de transition, vraie ssi l'état au pas step et au pas
     * step + 1 sont liés par une action "soustraction".
     */
    private BoolExpr subFormula(int step) {
        
        ActionVar subVar = this::subVar;

        ActionPrecondition subPrecond = (currentStep, v1, v2) -> {
            return context.mkTrue();
        };

        ActionResult subRes = (currentStep, v1, v2) -> {
            return context.mkBVSub(v1, v2);
        };

        return actionFormula(step, subVar, subPrecond, subRes);
    }

    /**
     * Formule de transition, vraie ssi l'état au pas step et au pas
     * step + 1 sont liés par une action "multiplication".
     */
    private BoolExpr mulFormula(int step) {
        
        ActionVar mulVar = this::mulVar;

        ActionPrecondition mulPrecond = (currentStep, v1, v2) -> {
            return context.mkTrue();
        };

        ActionResult mulRes = (currentStep, v1, v2) -> {
            return context.mkBVMul(v1, v2);
        };

        return actionFormula(step, mulVar, mulPrecond, mulRes);
    }

    /**
     * Formule de transition, vraie ssi l'état au pas step et au pas
     * step + 1 sont liés par une action "division".
     */
    private BoolExpr divFormula(int step) {
            
            ActionVar divVar = this::divVar;
    
            ActionPrecondition divPrecond = (currentStep, v1, v2) -> {
                return context.mkTrue();
            };
    
            ActionResult divRes = (currentStep, v1, v2) -> {
                return context.mkBVSDiv(v1, v2);
            };
    
            return actionFormula(step, divVar, divPrecond, divRes);
    }

    /**
     * Tableau contenant tous les triggers d'actions push, mul,
     * div, add, sub au pas "step".
     */
    private BoolExpr[] allActions(int step) {
        ArrayList<BoolExpr> arr = new ArrayList<>();

        for (int num : nums) {
            arr.add(pushNumVar(step, num));
        }

        arr.add(mulVar(step));
        arr.add(divVar(step));
        arr.add(addVar(step));
        arr.add(subVar(step));

        return arr.stream().toArray(BoolExpr[]::new);
    }

    /**
     * Formule vraie ssi les états aux pas step et step+1 sont liés par
     * une transition d'action.
     */
    private BoolExpr transitionFormula(int step) {
        
        BoolExpr[] allActions = allActions(step);
        BoolExpr onAction = exactlyOne(allActions);
        
       
        BoolExpr res = context.mkAnd(context.mkAnd(onAction, addFormula(step), subFormula(step)), mulFormula(step), divFormula(step));

        for(int i = 0; i < nums.length; i++) {
            res = context.mkAnd(res, pushNumFormula(step, nums[i]));
        }

        return res;

    }

    /**
     * Formule vraie ssi la pile est dans son état initial (au pas 0,
     * toutes les cellules à zéro et dessus de pile à zero).
     */
    private BoolExpr initialStateFormula() {
        ArrayExpr s_0 = stackStateVar(0);
        IntExpr idx_0 = idxStateVar(0);

        BoolExpr stackInit = context.mkEq(s_0, context.mkConstArray(intSort, context.mkBV(0, bvBits)));
        BoolExpr idxInit = context.mkEq(idx_0, context.mkInt(0));

        return context.mkAnd(stackInit, idxInit);
    }

    /**
     * Formule vraie ssi la pile ne contient qu'un élément qui est égal
     * à la valeur cible au pas "step".
     */
    private BoolExpr finalStateFormula(int step) {

        ArrayExpr s_i = stackStateVar(step);
        IntExpr idx_i = idxStateVar(step);

        BitVecExpr e1 = (BitVecExpr) context.mkSelect(s_i, context.mkSub(idx_i, context.mkInt(1)));

        BoolExpr isOneElement = context.mkEq(idx_i, context.mkInt(1));
        BoolExpr isTarget = context.mkEq(e1, context.mkBV(target, bvBits));

        return context.mkAnd(isOneElement, isTarget);
        

    }

    /**
     * Algorithme de résolution exacte. Déroule une boucle de
     * model-checking borné de la relation de transition depuis l'état
     * initial sur au plus maxNofSteps. Pour chaque itération de
     * model-checking, on ajoute une formule de transition pour le step
     * suivant dans le solveur, on pousse la formule d'état final, on
     * lance une résolution. Si le problème est SAT, on imprime la
     * solution, si le problème est UNSAT, on retire la formule d'état
     * final et on passe à l'itération suivante. Si le problème est
     * UNKNOWN, on retourne le status UNKOWN. Si le problème est UNSAT
     * pour toutes les itérations, on retourne le status UNSAT.
     */
    private Status solveExact(int timeout) {
    	// Variable pour suivre si le problème est insatisfiable
    	boolean unsatisfaisable = true;
    	
        // Initialisation du solveur et ajout de la formule de l'état initial
        Solver solver = context.mkSolver();
        solver.add(initialStateFormula());

        // Configuration du timeout si applicable
        configureTimeout(solver, timeout);

        // Résolution du problème en plusieurs étapes
        for (int i = 0; i < maxNofSteps; i++) {
            solver.add(transitionFormula(i));
            solver.push();
            solver.add(finalStateFormula(i + 1));

            // Vérification de la satisfiabilité du solveur
            Status checkStatus = solver.check();
            if (checkStatus == Status.SATISFIABLE) {
            	unsatisfaisable = false;
                handleSatisfiable(solver, i);
            } else if (checkStatus == Status.UNSATISFIABLE) {
                solver.pop();
            } else {
                System.out.println("Statut Inconnu !");
                return Status.UNKNOWN;
            }
        }

        // Résultat final basé sur l'état du problème
        if (unsatisfaisable) {
            System.out.println("Statut non satisfaisable !");
            return Status.UNSATISFIABLE;
        }
        return Status.UNKNOWN;
    }

    // Méthode pour configurer le timeout du solveur
    private void configureTimeout(Solver solver, int timeout) {
        if (timeout > 0) {
            Params params = context.mkParams();
            params.add("timeout", timeout);
            solver.setParameters(params);
            System.out.println("\n\nsolveExact with timeout " + timeout);
        } else {
            System.out.println("\n\nsolveExact without timeout");
        }
    }

    // Méthode pour gérer les solutions satisfiables
    private void handleSatisfiable(Solver solver, int step) {
        System.out.println("SAT problem");
        Model model = solver.getModel();
        printModel(model, step);
    }


    /**
     * Formule vraie ssi la pile n'est pas dans son état final au pas
     * "step".
     */
    private BoolExpr finalStateApproxFormula(int step) {
        return context.mkNot(finalStateFormula(step));
    }

    /**
     * Critère d'optimisation, écart en valeur absolue entre la valeur
     * du dessus de la pile et la valeur cible au pas "step".
     */
    private BitVecExpr finalStateApproxCriterion(int step) {
        
        ArrayExpr s_i = stackStateVar(step);
        IntExpr idx_i = idxStateVar(step);

        BitVecExpr e1 = (BitVecExpr) context.mkSelect(s_i, context.mkSub(idx_i, context.mkInt(1)));

        return context.mkBVSub(e1, context.mkBV(target, bvBits));
    }

    /**
     * Algorithme de résolution approchée par optimisation max-SMT. À
     * chaque étape de BMC, on minimise la distance à la cible. Le
     * solveur d'optimisation n'est pas incrémental donc push et pop ne
     * sont pas disponibles, on instancie donc un nouveau solveur et
     * toutes les contraintes jusqu'au pas "step" à chaque itération,
     * ainsi que le critère à optimiser. Pour chaque itération le
     * problème est sensé être SAT et on imprime la solution à chaque
     * itération. Si le problème est UNSAT, on retire la formule d'état
     * final et on passe à l'itération suivante. Si le problème est
     * UNKNOWN, on retourne le status UNKOWN. Si le problème était SAT
     * pour toutes les itérations, on retourne le status SAT.
     */
    private Status solveApprox(int timeout) {

        // Ce solveur n'est pas incrémental, il faut le recréer à chaque nouvelle itération du BMC.
        // Utiliser les méthodes suivantes sur le solveur (attention aux majuscules !) :
        // - Add pour ajouter une formule
        // - MkMinimize pour ajouter un critère à optimiser
        // - Check pour résoudre

        Status finalStatus = Status.UNKNOWN;

        for (int step = 0; step < this.maxNofSteps; step++) {
            Optimize optimizer = context.mkOptimize();

            optimizer.Add(initialStateFormula());
            for (int transitionStep = 0; transitionStep <= step; transitionStep++) {
                optimizer.Add(transitionFormula(transitionStep));
            }
            optimizer.Add(finalStateApproxFormula(step + 1));

            optimizer.MkMinimize(finalStateApproxCriterion(step + 1));
            finalStatus = optimizer.Check();

            if (finalStatus == Status.SATISFIABLE) {
                printModel(optimizer.getModel(), step);
            }

            if (timeout > 0) {
                Params parameters = context.mkParams();
                parameters.add("timeout", timeout);
                optimizer.setParameters(parameters);
            }
        }

        return finalStatus;
    }

    /**
     * Résout le problème en essayant une résolution exacte,
     * puis une résolution approximative en cas d'échec.
     */
    Status solve(int timeout) {
        printParams();

        Status s = solveExact(timeout);

        if (s != Status.SATISFIABLE) {
            s = solveApprox(timeout);
        }

        return s;
    }

    /** Affiche le contenu de la pile en ASCII sur sdtout. */
    private void printStackAtStep(Model m, int step) {
        for (int idx = 0; idx <= maxNofSteps; idx++) {
            BitVecExpr resbv =
                (BitVecExpr) context.mkSelect(stackStateVar(step),
                                              context.mkInt(idx));
            IntExpr resi = context.mkBV2Int(resbv, true);

            if (m.eval(context.mkEq(idxStateVar(step),
                                    context.mkInt(idx)), true).isTrue()) {
                System.out.print(" <| ");
            } else {
                System.out.print(" | ");
            }

            System.out.print(m.eval(resi, true));
        }

        System.out.println();
    }

    /**
     * Affiche le contenu d'un modèle m obtenu par BMC jusqu'à
     * la profondeur steps.
     */
    private void printModel(Model m, int steps) {
        System.out.print("init ~> ");
        printStackAtStep(m, 0);

        for (int step = 0; step <= steps; step++) {
            for (int num : nums) {
                if (m.eval(pushNumVar(step, num), true).isTrue()) {
                    System.out.print("push " + String.valueOf(num) + " ~> ");
                }
            }

            if (m.eval(mulVar(step), true).isTrue()) {
                System.out.print("mul ~> ");
            }

            if (m.eval(divVar(step), true).isTrue()) {
                System.out.print("div ~> ");
            }

            if (m.eval(addVar(step), true).isTrue()) {
                System.out.print("add ~> ");
            }

            if (m.eval(subVar(step), true).isTrue()) {
                System.out.print("sub ~> ");
            }

            printStackAtStep(m, step + 1);
        }
    }

    private void printParams() {
        System.out.println("\nParameters:");
        System.out.println("- bvBits     : " + String.valueOf(bvBits));
        System.out.println("- noOverflows: " + String.valueOf(noOverflows));
        System.out.println("- nums       : " + Arrays.toString(nums));
        System.out.println("- target     : " + String.valueOf(target));
    }
}

// Question 1 :

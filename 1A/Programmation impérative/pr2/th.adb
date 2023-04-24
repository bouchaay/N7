with Ada.Text_IO;            use Ada.Text_IO;
with SDA_Exceptions;         use SDA_Exceptions;
with Ada.Integer_Text_IO;       use Ada.Integer_Text_IO;
with Ada.Unchecked_Deallocation;

package body TH is

    procedure Initialiser(TH: out T_TH) is
    begin
        for i in 1..CAPACITE loop
            Initialiser(Sda => TH(i));
        end loop;
    end Initialiser;

    function Est_Vide(TH: in T_TH) return Boolean is
        Vide : Boolean;
        indice: Integer;
    begin
        Vide := True;
        indice := 1;
        while Vide and then indice <= CAPACITE loop
            if not Est_Vide(Sda => TH(indice)) then
                Vide := False;
            end if;
            indice := indice + 1;
        end loop;
        return Vide;
    end Est_Vide;

    function Taille(TH: in T_TH) return Integer is
        Nbr_elements: Integer;
    begin
        Nbr_elements := 0;
        for indice in 1..CAPACITE loop
            Nbr_elements := Nbr_elements + Taille (TH(indice));
        end loop;
        return Nbr_elements;
    end Taille;

    function Taille_Cellule(TH: in T_TH; Cle: in T_Cle ) return Integer is
        indice: Integer;
    begin
        indice := traiter_cle_hachage(Cle);
        return Taille(Sda => TH(indice));
    end Taille_Cellule;

    procedure Enregistrer(TH: in out T_TH; Donnee: in T_Donnee; Cle: in T_Cle) is
        indice : Integer;
    begin
        indice := traiter_cle_hachage(Cle);
        Enregistrer(Sda    => TH(indice),
                    Cle    => Cle,
                    Donnee => Donnee);
    end Enregistrer;

    procedure Supprimer(TH: in Out T_TH; Cle: in T_Cle) is
        indice : Integer;
    begin
        indice := traiter_cle_hachage(Cle);
        Supprimer (Sda => TH(indice),
                   Cle => Cle);
    end Supprimer;

    function Cle_Presente (TH: in T_TH; Cle: in T_Cle) return Boolean is
        Present : Boolean;
        indice : Integer;
    begin
        Present := False;
        indice := 1;
        while not Present and then indice <= CAPACITE loop
            if Cle_Presente (TH(indice), Cle) then
                Present := True;
            end if;
            indice := indice + 1;
        end loop;
        return Present;
    end Cle_Presente;

    function La_Donnee (TH: in T_TH; Cle: in T_Cle) return T_Donnee is
        indice: Integer;
    begin
        indice := traiter_cle_hachage(Cle);
        return La_Donnee(TH(indice), Cle);
    end La_Donnee;

    procedure Vider(TH: in Out T_TH) is
    begin
        for indice in 1..CAPACITE loop
            Vider(TH(indice));
        end loop;
    end Vider;

    procedure Pour_Chaque (TH: in Out T_TH) is
        procedure LCA_Pour_Chaque is new LCA_TH.Pour_Chaque(Traiter_TH);
    begin
        for indice in 1..CAPACITE loop
            Put ("Case numero ");
            Put (indice, 1);
            Put (" :");
            New_Line;
            LCA_Pour_Chaque(TH(indice));
            New_Line;
            New_Line;
        end loop;
    end Pour_Chaque;

end TH;




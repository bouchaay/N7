with TH;
with Ada.Text_IO;               use Ada.Text_IO;
with Ada.Integer_Text_IO;       use Ada.Integer_Text_IO;
with Ada.Strings;               use Ada.Strings;
with Ada.Strings.Unbounded;     use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO;  use  Ada.Text_IO.Unbounded_IO;

procedure th_sujet is

    Capacite : Constant Integer := 11;

    function fonction_hachage (Cle: in Unbounded_String) return Integer is
    begin
        return (Length(Cle) mod Capacite) + 1;
    end fonction_hachage;
    package TH_nombre is new TH(T_Cle               => Unbounded_String,
                                T_Donnee            => Integer,
                                CAPACITE              => Capacite,
                                traiter_cle_hachage => fonction_hachage);
    use TH_nombre;

    procedure Affichage (Cle : in Unbounded_String; Donnee: in Integer)is
    begin
       Put("La clef est : " & Cle);
       Put(" et la donnee associee est ");
       Put(Donnee, 1);
       New_Line;
    end Affichage;
    procedure Afficher is new Pour_Chaque(Traiter_TH => Affichage);

    TH : T_TH;

begin
    Initialiser(Th => TH);
    Enregistrer(Th     => TH,
                Donnee => 1,
                Cle    => To_Unbounded_String("un"));
    Enregistrer(Th     => TH,
                Donnee => 2,
                Cle    => To_Unbounded_String("deux"));
    Enregistrer(Th     => TH,
                Donnee => 3,
                Cle    => To_Unbounded_String("trois"));
    Enregistrer(Th     => TH,
                Donnee => 4,
                Cle    => To_Unbounded_String("quatre"));
    Enregistrer(Th     => TH,
                Donnee => 5,
                Cle    => To_Unbounded_String("cinq"));
    Enregistrer(Th     => TH,
                Donnee => 99,
                Cle    => To_Unbounded_String("quatre-vingt-dix-neuf"));
    Enregistrer(Th     => TH,
                Donnee => 21,
                Cle    => To_Unbounded_String("vingt-et-un"));
    Afficher(Th => TH);

end th_sujet;


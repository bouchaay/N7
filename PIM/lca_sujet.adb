with LCA;
with Ada.Text_IO;               use Ada.Text_IO;
with Ada.Integer_Text_IO;       use Ada.Integer_Text_IO;
with Ada.Strings;               use Ada.Strings;
with Ada.Strings.Unbounded;     use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO;  use  Ada.Text_IO.Unbounded_IO;

procedure lca_sujet is

    package LCA_nombre is new LCA(T_Cle               => Unbounded_String,
                                T_Donnee            => Integer);
    use LCA_nombre;

    procedure Affichage (Cle : in Unbounded_String; Donnee: in Integer)is
    begin
       Put("La clef est : " & Cle);
       Put(" et la donnee associee est ");
       Put(Donnee, 1);
       New_Line;
    end Affichage;
    procedure Afficher is new Pour_Chaque(Traiter => Affichage);

    LISTE : T_LCA;
begin
    Initialiser(Sda => LISTE);
    Enregistrer(Sda     => LISTE,
                Donnee => 1,
                Cle    => To_Unbounded_String("un"));
    Enregistrer(Sda     => LISTE,
                Donnee => 2,
                Cle    => To_Unbounded_String("deux"));
    Enregistrer(Sda     => LISTE,
                Donnee => 3,
                Cle    => To_Unbounded_String("trois"));
    Enregistrer(Sda     => LISTE,
                Donnee => 4,
                Cle    => To_Unbounded_String("quatre"));
    Afficher(Sda => LISTE);

end lca_sujet;

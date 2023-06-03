package tutoriel;

/** Classe des différentes variables utilisées dans le cours.
 * Ces variables sont utilisées dans la classe CoursRegex.
 */
public class StringVarCours {
	// Titre des slides
	public static String S1 = "\n\n\n\n\n           Introduction aux expressions régulières";
	public static String S2 = "\n\n\n\n\n                                Métacaractères";
	public static String S3 = "\n\n\n\n\n\n                       Liste des caractères abrégés";
	public static String S4 = "\n\n\n\n\n\n                                       Recherche";
	public static String S5 = "\n\n\n\n\n                                     Drapeaux";
	public static String S6 = "\n\n\n\n\n      Maintenant vous êtes prêts à découvrir notre logiciel !";

	// Titre des sous-slides
	public static String SS1 = "Full Stop";
	public static String SS2 = "Inclusion de caractères";
	public static String SS3 = "Répétition";
	public static String SS4 = "Accolades";
	public static String SS5 = "Groupement de caractères";
	public static String SS6 = "Alternation";
	public static String SS7 = "Caractère d'échappement";
	public static String SS8 = "Ancres";
	public static String SS9 = "Recherche avant positive";
	public static String SS10 = "Recherche avant négative";
	public static String SS11 = "Recherche arrière positive";
	public static String SS12 = "Recherche arrière négative";
	public static String SS13 = "Insensibilité à la casse";
	public static String SS14 = "Correspondance globale";
	public static String SS15 = "Multiligne";

	// Contenu des slides
	public static String I1 = "\n\n\n\nLes expressions régulières sont des modèles de caractères qui permettent d'effectuer des recherches dans un texte en spécifiant un schéma à suivre. Par exemple, en utilisant l'expression régulière \"the\", on peut rechercher toutes les occurrences de la lettre t suivie de la lettre h suivie de la lettre e dans un texte.\nVoici un autre exemple d'expression régulière : supposons que vous vouliez trouver toutes les adresses e-mail dans un texte. Vous pouvez utiliser l'expression régulière suivante :\n\n                                             [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}\n\nCette expression régulière correspond à l\'adresse e-mail standard et recherche tous les caractères alphanumériques, les points, les tirets, les tirets bas et les signes de pourcentage dans la partie avant l'arobase, puis tous les caractères alphanumériques, les tirets et les points dans la partie après l'arobase, et enfin deux caractères alphabétiques pour le domaine de niveau supérieur (par exemple, com, org, net).";
	
	public static String I2 = "\n\n\n\n\n\n\nLes expressions régulières utilisent les meta-caractères comme éléments de base. Les meta-caractères sont interprétés de manière spécifique et certains d'entre eux ont des significations particulières qui sont indiquées en les plaçant entre crochets. En d'autres termes, les meta-caractères constituent une sorte de vocabulaire spécialisé utilisé pour la création de motifs de recherche. Ils sont cruciaux pour le fonctionnement des expressions régulières et permettent aux programmeurs de rechercher et de manipuler des chaînes de caractères de manière très précise.";
	
	public static String tabMetaCarac = "\n\n\n\n   Meta-caractère\t\t\tDescription\n\n"
	+ "   .\tUn point coïncide avec n'importe quel caractère unique à part le retour à la ligne.\n\n"
	+ "   [ ]\tClasse de caractères. Coïncide avec n'importe quel caractère entre crochets.\n\n"
	+ "   [^ ]\tNégation de classe de caractère. Coïncide avec n'importe quel caractère qui n'est pas entre les crochets.\n\n"
	+ "   *\tCoïncide avec 0 ou plus répétitions du caractère précédent.\n\n"
	+ "   +\tCoïncide avec 1 ou plus répétitions du caractère précédent.\n\n"
	+ "   ?\tRend le caractère précédent optionnel.\n\n"
	+ "   {n,m}\tAccolades. Coïncide avec au moins 'n' mais pas plus que 'm' répétition(s) du caractère précédent.\n\n"
	+ "   (xyz)\tGroupe de caractères. Coïncide avec les caractères 'xyz' dans l'ordre exact.\n\n"
	+ "   |\tAlternation (ou). Coïncide soit avec le caractère avant ou après le symbole.\n\n"
	+ "   \\\tÉchappe le prochain caractère. Cela permet de faire coïncider des caractères réservés tels que [ ] ( ) { } . * + ? ^ $ \\ |\n\n"
	+ "   ^\tCoïncide avec le début de la chaîne de caractères (string).\n\n"
	+ "   $\tCoïncide avec la fin de la chaîne de caractères (string).";
	
	public static String I3 = "\n\n\n\n\n\n\nUn meta-caractère courant en expression régulière est le point, représenté par le symbole \".\". Le point permet de coïncider avec n'importe quel caractère unique, sauf les caractères de retour à la ligne. Par exemple, l'expression régulière \".ar\" peut correspondre à \"car\", \"bar\", \"tar\", etc., où le point correspond à n'importe quel caractère.\n\nUn autre exemple d'utilisation du meta-caractère \".\" est l\'expression régulière \"c.t\", qui peut correspondre à \"cat\", \"cot\", \"cut\", etc. Le point dans cette expression régulière correspond à n'importe quel caractère unique, ce qui permet de trouver tous les mots qui ont la même structure que \"c.t\".";

	public static String I4 = "\n\n\n\nLes classes de caractères sont aussi connues sous le nom d'inclusions de caractères et sont spécifiées à l'aide de crochets. Pour définir une plage de caractères, on utilise un trait d'union dans une inclusion de caractères, et l'ordre des caractères dans la plage n'a pas d'importance. Par exemple, l'expression régulière [Tt]he signifie qu'il peut y avoir un T majuscule ou un t minuscule, suivi de la lettre h, puis de la lettre e.\n\nUn autre exemple d'expression régulière utilisant des inclusions de caractères est [0-9], qui correspond à tous les chiffres compris entre 0 et 9. Cette expression peut être utilisée pour trouver toutes les occurrences de chiffres dans une chaîne de caractères. Par exemple, l'expression régulière \"^[0-9]+\" trouvera la première série de chiffres au début d'une chaîne de caractères. Si la chaîne de caractères est \"123ABC456DEF\", l'expression régulière trouvera \"123\".\n\nEn règle générale, le caractère circonflexe représente le début d'une chaîne de caractères (string). Néanmoins, lorsqu'il est utilisé après le crochet ouvrant, il permet d'exclure la gamme de caractères. Par exemple, l'expression régulière [^c]ar signifie : n'importe quel caractère sauf c, suivi par la lettre a, suivie par la lettre r.";

	public static String I5 = "\n\n\n\n\n\n\n\n\nLes symboles spéciaux +, *, ou ? sont employés pour indiquer la répétition d'un sous-modèle dans une expression régulière. Le comportement de ces symboles varie en fonction de leur contexte d'utilisation. Dans cette optique, l'astérisque est l'un de ces symboles qui permet de spécifier la présence d'un sous-modèle zéro fois ou plusieurs fois.";

	public static String I6 = "Astérisque\n\n\n\n\n\nLe symbole * permet de spécifier zéro ou plusieurs occurrences du modèle précédent dans une expression régulière. Par exemple, l'expression régulière a* correspond à zéro ou plusieurs occurrences de la lettre \"a\". Cependant, si le symbole * est utilisé après une liste de caractères, cela signifie que la liste entière peut se répéter zéro ou plusieurs fois. Par exemple, l'expression régulière [a-z]* signifie que la chaîne peut contenir n'importe quel nombre de lettres minuscules.\n\nEn utilisant le meta-caractère ., le symbole * peut être employé pour correspondre à n\'importe quelle chaîne de caractères en utilisant l'expression .* . De même, le caractère espace vide \\s peut être utilisé avec le symbole * pour correspondre à une chaîne d'espaces vides avec l'expression \\scat\\s signifiant qu'il y a zéro ou plusieurs espaces, suivis par le mot \"cat\", suivi à nouveau par zéro ou plusieurs espaces. Exemple d'utilisation: Pour l'expression régulière \"\\scat\\s\", elle correspondrait à la phrase \"The fat cat sat on the concatenation\".";

	public static String I7 = "Le Plus\n\n\n\n\n\nLe meta-caractère \"+\" en expression régulière signifie \"une ou plusieurs occurrences\" du caractère ou modèle précédent. Par exemple, l'expression régulière c.+t signifie qu'il doit y avoir la lettre \"c\" minuscule, suivie d'au moins un caractère quelconque, suivi de la lettre \"t\" minuscule. Le \"+\" exige donc qu'il y ait au moins un caractère entre la lettre \"c\" et la lettre \"t\". Le \"t\" qui correspond est le dernier \"t\" de la chaîne.\n\n Voici un autre exemple : l'expression régulière \\d+ correspondra à une ou plusieurs occurrences de chiffres. Par exemple, l'expression régulière \\d+apple signifie qu'il doit y avoir un ou plusieurs chiffres, suivi du mot \"apple\". Donc, \"123apple\" correspondra à cette expression régulière mais pas \"apple123\".";

	public static String I8 = "Le Point d'Interrogation\n\n\n\n\n\nLe symbole ? en expression régulière signifie \"zéro ou une occurrence\" du caractère ou modèle précédent. Par exemple, l'expression régulière c.?t signifie qu'il doit y avoir la lettre \"c\" minuscule, suivi d'un caractère quelconque (ou aucun), suivi de la lettre \"t\" minuscule. Le \"t\" qui correspond est le dernier \"t\" de la chaîne.\n\n Voici un autre exemple : l'expression régulière \\d?apple signifie qu'il doit y avoir zéro ou un chiffre, suivi du mot \"apple\". Donc, \"apple\" correspondra à cette expression régulière mais pas \"1apple\".";

	public static String I9 = "\n\n\n\n\n\nLes accolades permettent de spécifier le nombre exact de répétitions d'un caractère ou d'un modèle. Par exemple, l'expression régulière c{2}t signifie qu'il doit y avoir la lettre \"c\" minuscule, suivie de deux fois la lettre \"c\" minuscule, suivi de la lettre \"t\" minuscule. Le \"t\" qui correspond est le dernier \"t\" de la chaîne.\n\n Voici un autre exemple : l'expression régulière \\d{3}apple signifie qu'il doit y avoir trois chiffres, suivi du mot \"apple\". Donc, \"123apple\" correspondra à cette expression régulière mais pas \"12apple\".";

	public static String I10 = "\n\n\n\n\n\nLes parenthèses sont utilisées pour grouper des sous-modèles dans une expression régulière. \n\nPar exemple, l'expression régulière (cat|dog) signifie qu'il doit y avoir soit \"cat\" soit \"dog\". Les parenthèses sont également utilisées pour extraires des sous-chaînes de caractères. Par exemple, l'expression régulière (\\d{3})-(\\d{3})-(\\d{4}) permet d'extraire les trois groupes de chiffres séparés par des tirets.\n\nUn autre exemple simple est l'expression régulière (\\d+). Elle permet d'extraire un nombre quelconque de chiffres.";

	public static String I11 = "\n\n\n\n\n\nL'alternation est utilisée pour spécifier plusieurs modèles possibles pour une expression régulière. Par exemple, l'expression régulière (cat|dog) signifie qu'il doit y avoir soit \"cat\" soit \"dog\". L'alternation est également utilisée pour extraire des sous-chaînes de caractères. Par exemple, l'expression régulière (\\d{3})-(\\d{3})-(\\d{4}) permet d'extraire les trois groupes de chiffres séparés par des tirets.\n\nUn autre exemple simple est l'expression régulière (\\d+). Elle permet d'extraire un nombre quelconque de chiffres.";

	public static String I12 = "\n\n\n\n\n\nLes caractères d'échappement sont utilisés pour spécifier des caractères spéciaux dans une expression régulière. Par exemple, l'expression régulière \\s signifie qu'il doit y avoir un espace vide. L'expression régulière \\s+ signifie qu'il doit y avoir un ou plusieurs espaces vides. L'expression régulière \\s* signifie qu'il doit y avoir zéro ou plusieurs espaces vides. L'expression régulière \\s? signifie qu'il doit y avoir zéro ou un espace vide. L'expression régulière \\s{2} signifie qu'il doit y avoir deux espaces vides. L'expression régulière \\s{2,} signifie qu'il doit y avoir deux ou plusieurs espaces vides. L'expression régulière \\s{2,4} signifie qu'il doit y avoir deux, trois ou quatre espaces vides.\n\nVoici une liste des caractères d'échappement les plus courants : \\. \\* \\+ \\? \\[ \\] \\^ \\$ \\( \\) \\{ \\} \\| \\s \\S \\d \\D \\w \\W \\b \\B \\t \\n \\r \\f \\v \\cX \\xhh \\uhhhh \\uhhhhhhh \\Q \\E \\A \\Z \\z \\G \\p \\P \\N \\k \\K \\1 \\2 \\3 \\4 \\5 \\6 etc...";

	public static String I13 = "\n\n\n\n\n\nLes ancres sont utilisées pour spécifier des positions spécifiques dans une chaîne de caractères. Par exemple, l'expression régulière ^\\d{3}-\\d{3}-\\d{4}$ signifie qu'il doit y avoir trois chiffres, suivi d'un tiret, suivi de trois chiffres, suivi d'un tiret, suivi de quatre chiffres, et que la chaîne de caractères doit commencer et se terminer par ces caractères.\n\nLe circumflexe (^) est utilisé pour spécifier le début de la chaîne de caractères.\n\nLe dollar ($) est utilisé pour spécifier la fin de la chaîne de caractères.";

	public static String tabCaracAbrege = "\n\n\n\n\nAbréviation\t\tDescription\n\n" +
	"   .\tN'importe quel caractère à part le retour de ligne\n\n" +
	"   \\w\tCaractères alphanumériques : [a-zA-Z0-9_]\n\n" +
	"   \\W\tCaractères non-alphanumériques : [^\\w]\n\n" +
	"   \\d\tChiffres : [0-9]\n\n" +
	"   \\D\tNon-numériques : [^\\d]\n\n" +
	"   \\s\tEspace vide : [\\t\\n\\f\\r\\p{Z}]\n\n" +
	"   \\S\tTout sauf espace vide : [^\\s]\n\n";

	public static String I14 = "\n\n\nLes groupes non-capturants de recherche en avant et en arrière sont utilisés pour identifier un schéma sans le capturer dans la liste de correspondance. Les recherches en avant positives sont utiles lorsque l'on veut identifier un schéma précédé ou suivi par un autre schéma. Par exemple, si nous voulons trouver tous les chiffres qui sont précédés par le caractère $ dans la chaîne \"$4.44 and $10.88\", nous pouvons utiliser l'expression régulière suivante : \"(?<=\\$)[0-9\\.]*\". Cette expression signifie : trouver tous les nombres qui contiennent le caractère . et qui sont précédés par le caractère $. Les expressions régulières contiennent plusieurs types de recherches, telles que :\n\nSymbole\tDescription\n\n  ?= \tRecherche en avant positive\n\n  ?!\tRecherche en avant négative\n\n  ?<=\tRecherche en arrière positive\n\n  ?<!\tRecherche en arrière négative";

	public static String I15 = "\n\n\n\n\n\nLa recherche en avant positive dans les expressions régulières permet de s'assurer que la première partie de l'expression est suivie par une expression recherchée spécifique. Seul le texte qui correspond à la première partie de l'expression est retourné. Pour définir une recherche en avant positive, on utilise des parenthèses contenant un point d'interrogation et un signe égal : (?=...). L'expression de recherche est écrite après le signe égal dans les parenthèses. Par exemple, l'expression régulière suivante a-z recherche la lettre minuscule suivie de la séquence \"oo\". Cela trouvera toutes les lettres minuscules qui sont suivies de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I16 = "\n\n\n\n\n\nLa recherche en avant négative dans les expressions régulières permet de s'assurer que la première partie de l'expression n'est pas suivie par une expression recherchée spécifique. Seul le texte qui correspond à la première partie de l'expression est retourné. Pour définir une recherche en avant négative, on utilise des parenthèses contenant un point d'interrogation et un signe exclamé : (?!...). L'expression de recherche est écrite après le signe exclamé dans les parenthèses. Par exemple, l'expression régulière suivante a-z recherche la lettre minuscule suivie de la séquence \"oo\". Cela trouvera toutes les lettres minuscules qui ne sont pas suivies de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I17 = "\n\n\n\n\n\nLa recherche en arrière positive dans les expressions régulières permet de s'assurer que la dernière partie de l'expression est précédée par une expression recherchée spécifique. Seul le texte qui correspond à la dernière partie de l'expression est retourné. Pour définir une recherche en arrière positive, on utilise des parenthèses contenant un point d'interrogation, un signe égal et un signe inférieur : (?<=...). L'expression de recherche est écrite après le signe égal et le signe inférieur dans les parenthèses. Par exemple, l'expression régulière suivante recherche la séquence \"oo\" suivie de la lettre minuscule. Cela trouvera toutes les lettres minuscules qui sont précédées de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I18 = "\n\n\n\n\n\nLa recherche en arrière négative dans les expressions régulières permet de s'assurer que la dernière partie de l'expression n'est pas précédée par une expression recherchée spécifique. Seul le texte qui correspond à la dernière partie de l'expression est retourné. Pour définir une recherche en arrière négative, on utilise des parenthèses contenant un point d'interrogation, un signe exclamé et un signe inférieur : (?<!...). L'expression de recherche est écrite après le signe exclamé et le signe inférieur dans les parenthèses. Par exemple, l'expression régulière suivante recherche la séquence \"oo\" suivie de la lettre minuscule. Cela trouvera toutes les lettres minuscules qui ne sont pas précédées de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I19 = "Les drapeaux sont aussi appelés modifieurs car ils modifient la sortie d'une expression régulière. Ces drapeaux peuvent être utilisés dans n'importe quel ordre et combinaison et font partie intégrante de la RegExp.\n\nDrapeau\tDescription\ni\tInsensible à la casse : Définit que la correspondance sera insensible à la casse.\ng\tRecherche globale : Recherche la correspondance dans la chaine de caractères (string) entière.\nm\tMultiligne : Meta-caractère ancre qui agit sur toutes les lignes.";

	public static String I20 = "L\'insensibilité à la casse est un drapeau qui permet de rechercher une expression régulière sans tenir compte de la casse. Par exemple, l'expression régulière suivante recherche la séquence \"oo\" suivie de la lettre minuscule. Cela trouvera toutes les lettres minuscules qui ne sont pas précédées de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I21 = "La correspondance globale est un drapeau qui permet de rechercher une expression régulière dans la chaîne de caractères entière. Par exemple, l'expression régulière suivante recherche la séquence \"oo\" suivie de la lettre minuscule. Cela trouvera toutes les lettres minuscules qui ne sont pas précédées de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";

	public static String I22 = "La correspondance multiligne est un drapeau qui permet de rechercher une expression régulière sur plusieurs lignes. Par exemple, l'expression régulière suivante recherche la séquence \"oo\" suivie de la lettre minuscule. Cela trouvera toutes les lettres minuscules qui ne sont pas précédées de la séquence \"oo\" sans inclure \"oo\" dans la liste de correspondance.";
}


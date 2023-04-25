package allumettes;

/**
 * Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author Xavier Crégut
 * @version $Revision: 1.5 $
 */
public class Jouer {

	/** Le nombre d'allumettes initiale du jeu. */
	static final int NB_ALLUMETTES_INITIAL = 13;
	/**
	 * Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			// on vérifie les arguments
			verifierNombreArguments(args);

			// on vérifie la confiance = 1 si confiant, 0 sinon
			int modeConfianceActif = verifierConfiance(args);

			// on crée les joueurs
			Joueur premierJoueur = initialiserJoueur(args[modeConfianceActif]);
			Joueur deuxiemeJoueur = initialiserJoueur(args[modeConfianceActif + 1]);

			// on vérifie que les noms des joueurs sont différents
			if (premierJoueur.getNom().equals(deuxiemeJoueur.getNom())) {
				throw new ConfigurationException("Les noms des joueurs "
						+ "doivent être différents");
			}
			// Initialisation de l'arbitre
			Arbitre arbitre = new Arbitre(premierJoueur, deuxiemeJoueur);

			// Initialisation du jeu
			SujetReel jeu = new SujetReel(NB_ALLUMETTES_INITIAL);

			// on active le mode confiant
			if (modeConfianceActif == 1) {
				arbitre.activerConfiance();
			}

			// on lance le jeu
			arbitre.arbitrer(jeu);

		// on gère les exceptions
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur : " + e.getMessage());
		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		} catch (CoupInvalideException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	/**
	 * Vérifier le nombre d'arguments.
	 * @param args les arguments
	 * @throws ConfigurationException si le nombre d'arguments est incorrect
	 */
	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
		if (args.length == nbJoueurs + 1) {
			if (!args[0].equals("-confiant")) {
				throw new ConfigurationException("Argument incorrect : "
						+ args[0]);
			}
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
				+ "Ordinateur@naif"
				+ "\n");
	}

	/**
	 * Récupérer une stratégie à partir de son nom.
	 * @param strStrategie le nom de la stratégie
	 * @return la stratégie correspondante
	 */
	public static Strategie strategieParNom(String strStrategie) {
		assert strStrategie != null : "strStrategie ne doit pas être null";
		if (strStrategie.equals("expert")) {
			return new StratExpert();
		} else if (strStrategie.equals("rapide")) {
			return new StratRapide();
		} else if (strStrategie.equals("humain")) {
			return new StratHumain();
		} else if (strStrategie.equals("naif")) {
			return new StratNaif();
		} else if (strStrategie.equals("tricheur")) {
			return new StratTricheur();
		} else {
			throw new ConfigurationException("Strategie inconnue : "
					+ strStrategie);
		}
	}

	/**
	 * Initialiser un joueur à partir d'une chaîne de caractères.
	 * @param argument la chaîne de caractères
	 * @return le joueur initialisé
	 * @throws ConfigurationException si le format de la chaîne est incorrect
	 */
	public static Joueur initialiserJoueur(String argument) {
		String[] joueur = argument.split("@");
		if (joueur.length != 2 || joueur[0].equals("") || joueur[1].equals("")) {
			throw new ConfigurationException("Argument incorrect : "
					+ argument);
		}
		String strategieString = joueur[1].toLowerCase();
		String nom = joueur[0];
		Strategie strategie = strategieParNom(strategieString);
		return new Joueur(nom, strategie);
	}

	/**
	 * Vérifier si le mode confiant est activé.
	 * @param args les arguments
	 * @return 1 si le mode confiant est activé, 0 sinon
	 */
	public static int verifierConfiance(String[] args) {
		if (args[0].equals("-confiant")) {
			return 1;
		} else {
			return 0;
		}
	}
}

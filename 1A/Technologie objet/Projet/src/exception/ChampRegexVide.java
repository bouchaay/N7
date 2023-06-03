package exception;

/**
 * Exception levée lorsqu'aucune expression régulière n'est entrée.
 */
public class ChampRegexVide extends Exception {
	
	/**
	 * Constructeur de l'exception.
	 */
	public ChampRegexVide() {
		super("Veuillez entrer une expression régulière.");
	}
}

package allumettes;

/**
 * Exception levée lorsqu'une opération n'est pas autorisée.
 * @author Ayoub Bouchama
 */
public class OperationInterditeException extends RuntimeException {

    /**
     * Constructeur de l'exception.
     */
    public OperationInterditeException() {
        super("Abandon de la partie car le joueur triche !");
    }
}

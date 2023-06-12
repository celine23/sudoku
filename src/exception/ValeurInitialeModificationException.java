package sudoku.exception;

/**
 * ValeurInitialeModificationException.
 * @author Groupe J
 */
public class ValeurInitialeModificationException extends Exception {

    /**
     * Constructeur de la classe.
     * @param error message d'erreur.
     */
    public ValeurInitialeModificationException(final String error) {
        super(error);
    }
}

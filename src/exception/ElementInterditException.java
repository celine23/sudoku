package sudoku.exception;

/**
 * ElementInterditException.
 * @author Groupe J
 */
public class ElementInterditException extends Exception {

    /**
     * Constructeur de la classe.
     * @param error message d'erreur.
     */
    public ElementInterditException(final String error) {
        super(error);
    }
}

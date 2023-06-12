package sudoku.exception;

/**
 * HorsBornesException.
 * @author Groupe J
 */
public class HorsBornesException extends Exception {

    /**
     * Constructeur de la classe.
     * @param error message d'erreur.
     */
    public HorsBornesException(final String error) {
        super(error);
    }
}

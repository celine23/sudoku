package sudoku.exception;

import org.junit.Test;
/**
 * ElementInterditException.
 * @author Groupe J
 */
public class ValeurInitialeModificationExceptionTest {

    @Test(expected = ValeurInitialeModificationException.class)
    public void testValeurInitialeModificationException() throws ValeurInitialeModificationException {
        throw new ValeurInitialeModificationException("Erreur : modification de valeur initiale !");
    }
}

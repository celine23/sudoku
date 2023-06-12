package sudoku.exception;

import org.junit.Test;
/**
 * ElementInterditException.
 * @author Groupe J
 */
public class ValeurImpossibleExceptionTest {

    @Test(expected = ValeurImpossibleException.class)
    public void testValeurImpossibleException() throws ValeurImpossibleException {
        throw new ValeurImpossibleException("Erreur : valeur impossible !");
    }
}

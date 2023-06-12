package sudoku.exception;

import org.junit.Test;
/**
 * ElementInterditException.
 * @author Groupe J
 */
public class HorsBornesExceptionTest {

    @Test(expected = HorsBornesException.class)
    public void testHorsBornesException() throws HorsBornesException {
        throw new HorsBornesException("Erreur : hors bornes !");
    }
}

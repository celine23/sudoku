package sudoku.exception;

import org.junit.Test;
/**
 * ElementInterditException.
 * @author Groupe J
 */
public class ElementInterditExceptionTest {

    @Test(expected = ElementInterditException.class)
    public void testElementInterditException() throws ElementInterditException {
        throw new ElementInterditException("Erreur : élément interdit !");
    }
}

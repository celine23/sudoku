import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contenusudoku.implementation.ElementDeGrilleImplAsChar;
import contenusudoku.implementation.GrilleImpl;
import contenusudoku.sudoku.ElementDeGrille;
import contenusudoku.sudoku.Grille;

/**
 * ElementInterditException.
 * @author Groupe J
 */
public class TestGrille {

    private Grille grilleTest;
    private Set<ElementDeGrille> expectedElements;

    @BeforeEach
    public void setUp() {
        ElementDeGrille[][] elements2d = {
                { new ElementDeGrilleImplAsChar('1', true), new ElementDeGrilleImplAsChar('2', true) },
                { new ElementDeGrilleImplAsChar('3', true), new ElementDeGrilleImplAsChar('4', true) }
        };

        expectedElements = getExpectedElement();

        grilleTest = new GrilleImpl(elements2d, expectedElements);
    }

    private Set<ElementDeGrille> getExpectedElement() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(new ElementDeGrilleImplAsChar('1'));
        expectedElements.add(new ElementDeGrilleImplAsChar('2'));
        expectedElements.add(new ElementDeGrilleImplAsChar('3'));
        expectedElements.add(new ElementDeGrilleImplAsChar('4'));
        expectedElements.add(new ElementDeGrilleImplAsChar('5'));
        expectedElements.add(new ElementDeGrilleImplAsChar('6'));
        expectedElements.add(new ElementDeGrilleImplAsChar('7'));
        expectedElements.add(new ElementDeGrilleImplAsChar('8'));
        expectedElements.add(new ElementDeGrilleImplAsChar('9'));
        expectedElements.add(new ElementDeGrilleImplAsChar('a'));
        expectedElements.add(new ElementDeGrilleImplAsChar('b'));
        expectedElements.add(new ElementDeGrilleImplAsChar('c'));
        expectedElements.add(new ElementDeGrilleImplAsChar('d'));
        expectedElements.add(new ElementDeGrilleImplAsChar('e'));
        expectedElements.add(new ElementDeGrilleImplAsChar('f'));
        return expectedElements;
    }

    @Test
    public void testgetElements() {
        ElementDeGrille[][] elements2d = {
                { new ElementDeGrilleImplAsChar('4'), new ElementDeGrilleImplAsChar('4') },
                { new ElementDeGrilleImplAsChar('4'), new ElementDeGrilleImplAsChar('4') }
        };

        GrilleImpl grilleTest = new GrilleImpl(elements2d, expectedElements);

        Set<ElementDeGrille> possible = grilleTest.getElements();

        assertEquals(true, possible.contains(new ElementDeGrilleImplAsChar('1')));
    }

    @Test
    public void testgetElementsAutre() {
        ElementDeGrille[][] elements2d = {
                { new ElementDeGrilleImplAsChar('4'), new ElementDeGrilleImplAsChar('4') },
                { new ElementDeGrilleImplAsChar('4'), new ElementDeGrilleImplAsChar('4') }
        };

        GrilleImpl grilleTest = new GrilleImpl(elements2d, expectedElements);

        Set<ElementDeGrille> possible = grilleTest.getElements();

        assertEquals(true, possible.contains(new ElementDeGrilleImplAsChar('1')));
    }

    @Test
    public void testgetElement() {
        ElementDeGrille element = grilleTest.getElement(0, 0);
        assertNotNull(element);
        assertEquals('1', element.getValeur());
    }

    @Test
    public void testgetElementInvalide() {
        ElementDeGrille element = grilleTest.getElement(2, 2);
        assertNull(element);
    }

    @Test
    public void testsetElement() {
        ElementDeGrilleImplAsChar newElement = new ElementDeGrilleImplAsChar('5');
        grilleTest.setElement(0, 0, newElement);

        ElementDeGrille element = grilleTest.getElement(0, 0);
        assertNotNull(element);
        assertEquals('5', element.getValeur());
    }

    @Test
    public void testsetElementInvalide() {
        ElementDeGrilleImplAsChar newElement = new ElementDeGrilleImplAsChar('5');
        grilleTest.setElement(2, 2, newElement);

        ElementDeGrille element = grilleTest.getElement(2, 2);
        assertNull(element);
    }

    @Test
    public void testgetElementsUnmodifiable() {
        Set<ElementDeGrille> elements = grilleTest.getElements();
        assertNotNull(elements);

        try {
            elements.add(new ElementDeGrilleImplAsChar('5'));
        } catch (UnsupportedOperationException e) {
            // L'exception est attendue
        }

        assertEquals(expectedElements.size(), elements.size());
    }

    @Test
    public void testgetElementsDistincts() {
        Set<ElementDeGrille> elements = grilleTest.getElements();
        assertNotNull(elements);

        assertEquals(expectedElements.size(), elements.size());
    }
}

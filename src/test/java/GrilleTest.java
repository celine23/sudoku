import static org.junit.jupiter.api.Assertions.*;

import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.implementation.ElementDeGrilleImplAsChar;
import fr.upjv.miage.sudoku.Grille;
import fr.upjv.miage.implementation.GrilleImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.upjv.miage.sudoku.ElementDeGrille;

import java.util.HashSet;
import java.util.Set;

/**
 * ElementInterditException.
 * @author Groupe J
 */

public class GrilleTest {

    private GrilleImpl grilleTest;

    @BeforeEach
    public void setUp() {
        // Initialiser la grille avant chaque test
        ElementDeGrille[][] grille = new ElementDeGrille[9][9];
        Set<ElementDeGrille> elementAutorise = new HashSet<>();
        elementAutorise.add(new ElementDeGrilleImplAsChar('1'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('2'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('3'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('4'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('5'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('6'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('7'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('8'));
        elementAutorise.add(new ElementDeGrilleImplAsChar('9'));
        grilleTest = new GrilleImpl(9, grille, elementAutorise);
    }


    @Test
    public void testGetElements() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        // Ajouter les éléments de grille attendus à expectedElements
        expectedElements.add(new ElementDeGrilleImplAsChar('1'));
        expectedElements.add(new ElementDeGrilleImplAsChar('2'));
        expectedElements.add(new ElementDeGrilleImplAsChar('3'));
        expectedElements.add(new ElementDeGrilleImplAsChar('4'));
        expectedElements.add(new ElementDeGrilleImplAsChar('5'));
        expectedElements.add(new ElementDeGrilleImplAsChar('6'));
        expectedElements.add(new ElementDeGrilleImplAsChar('7'));
        expectedElements.add(new ElementDeGrilleImplAsChar('8'));
        expectedElements.add(new ElementDeGrilleImplAsChar('9'));

        Set<ElementDeGrille> actualElements = grilleTest.getElements();

        assertEquals(expectedElements, actualElements);
    }

    @Test
    public void testGetDimension() {
        int expectedDimension = 9;
        int actualDimension = grilleTest.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    public void testSetValue() throws HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('1');
        int x = 0;
        int y = 0;

        assertDoesNotThrow(() -> grilleTest.setValue(x, y, value));

        assertEquals(value, grilleTest.getValue(x, y));
    }

    @Test
    public void testGetValue() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('5');
        int x = 3;
        int y = 5;

        grilleTest.setValue(x, y, value);

        ElementDeGrille actualValue = grilleTest.getValue(x, y);

        assertEquals(value, actualValue);
    }

    @Test
    public void testIsComplete() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        assertFalse(grilleTest.isComplete());

        // Remplir la grille avec des valeurs valides
        for (int x = 0; x < grilleTest.getDimension(); x++) {
            for (int y = 0; y < grilleTest.getDimension(); y++) {
                ElementDeGrille value = new ElementDeGrilleImplAsChar((char) ('1' + (x + y) % 9));
                grilleTest.setValue(x, y, value);
            }
        }

        assertTrue(grilleTest.isComplete());
    }

    @Test
    public void testIsPossible() throws ElementInterditException, HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('3');
        int x = 0;
        int y = 0;

        assertTrue(grilleTest.isPossible(x, y, value));
    }

    @Test
    public void testIsValeurInitiale() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('8');
        int x = 4;
        int y = 7;

        grilleTest.setValue(x, y, value);

        assertFalse(grilleTest.isValeurInitiale(x, y));
    }
}

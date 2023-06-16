import static org.junit.jupiter.api.Assertions.*;

import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.implementation.ElementDeGrilleImplAsChar;
import fr.upjv.miage.sudoku.ElementDeGrille;
import fr.upjv.miage.sudoku.Grille;
import fr.upjv.miage.implementation.GrilleImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * ElementInterditException.
 * @author Groupe J
 */

public class GrilleTest {

    private Grille grille;

    @BeforeEach
    public void setUp() {
        // Initialiser la grille avant chaque test
        grille = new GrilleImpl(9);
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

        Set<ElementDeGrille> actualElements = grille.getElements();

        assertEquals(expectedElements, actualElements);
    }

    @Test
    public void testGetDimension() {
        int expectedDimension = 9;
        int actualDimension = grille.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    public void testSetValue() throws HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('1');
        int x = 0;
        int y = 0;

        assertDoesNotThrow(() -> grille.setValue(x, y, value));

        assertEquals(value, grille.getValue(x, y));
    }

    @Test
    public void testGetValue() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('5');
        int x = 3;
        int y = 5;

        grille.setValue(x, y, value);

        ElementDeGrille actualValue = grille.getValue(x, y);

        assertEquals(value, actualValue);
    }

    @Test
    public void testIsComplete() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        assertFalse(grille.isComplete());

        // Remplir la grille avec des valeurs valides
        for (int x = 0; x < grille.getDimension(); x++) {
            for (int y = 0; y < grille.getDimension(); y++) {
                ElementDeGrille value = new ElementDeGrilleImplAsChar((char) ('1' + (x + y) % 9));
                grille.setValue(x, y, value);
            }
        }

        assertTrue(grille.isComplete());
    }

    @Test
    public void testIsPossible() throws ElementInterditException, HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('3');
        int x = 0;
        int y = 0;

        assertTrue(grille.isPossible(x, y, value));
    }

    @Test
    public void testIsValeurInitiale() throws ElementInterditException, ValeurInitialeModificationException, HorsBornesException, ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('8');
        int x = 4;
        int y = 7;

        grille.setValue(x, y, value);

        assertTrue(grille.isValeurInitiale(x, y));
    }
}

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.implementation.ElementDeGrilleImplAsChar;
import fr.upjv.miage.implementation.GrilleImpl;
import fr.upjv.miage.sudoku.ElementDeGrille;
import fr.upjv.miage.sudoku.Grille;
import fr.upjv.miage.sudoku.Solveur;

public class SolveurTest {

    ElementDeGrille initialElement1 = new ElementDeGrilleImplAsChar('1', true);
    ElementDeGrille initialElement2 = new ElementDeGrilleImplAsChar('2', true);
    ElementDeGrille initialElement3 = new ElementDeGrilleImplAsChar('3', true);
    ElementDeGrille initialElement4 = new ElementDeGrilleImplAsChar('4', true);

    ElementDeGrille[][] gridElements1 = {
            { initialElement1, null, initialElement2, null },
            { null, null, null, null },
            { null, null, null, null },
            { initialElement2, null, initialElement3, null },
    };

    ElementDeGrille[][] gridElements2 = {
            { initialElement1, null, initialElement2, initialElement3 },
            { initialElement3, initialElement4, initialElement1, initialElement2 },
            { initialElement4, initialElement2, initialElement3, initialElement1 },
            { initialElement2, initialElement1, initialElement4, initialElement4 },
    };

    ElementDeGrille value1 = new ElementDeGrilleImplAsChar('1');
    ElementDeGrille value2 = new ElementDeGrilleImplAsChar('2');
    ElementDeGrille value3 = new ElementDeGrilleImplAsChar('3');
    ElementDeGrille value4 = new ElementDeGrilleImplAsChar('4');

    Solveur solver;

    public SolveurTest() {
        solver = new Solveur() {
            @Override
            public boolean solve(Grille grille) {
                return false;
            }
        };
    }

    private Set<ElementDeGrille> getExpectedElements() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(value1);
        expectedElements.add(value2);
        expectedElements.add(value3);
        expectedElements.add(value4);

        return expectedElements;
    }

    Grille validGrid = new GrilleImpl(gridElements1, this.getExpectedElements());
    Grille invalidGrid = new GrilleImpl(gridElements2, this.getExpectedElements());

    @Timeout(5)
    @Test
    public void testValidSolving() throws HorsBornesException, ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException {
        System.out.println("Initial grid:");
        System.out.println(validGrid);

        boolean result = solver.solve(validGrid);

        System.out.println("Solved grid:");
        System.out.println(validGrid);

        if (validGrid.isComplete()) {
            System.out.println("La grille a été résolue avec succès.");
            assertTrue(result);
        } else {
            System.out.println("La grille n'a pas pu être résolue.");
            assertFalse(result);
        }
    }

    @Timeout(5)
    @Test
    public void testInvalidSolving() throws HorsBornesException, ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException {
        System.out.println("Initial grid:");
        System.out.println(invalidGrid);

        boolean result = solver.solve(invalidGrid);

        System.out.println("Solved grid:");
        System.out.println(invalidGrid);

        if (invalidGrid.isComplete()) {
            System.out.println("La grille a été résolue avec succès.");
            assertTrue(result);
        } else {
            System.out.println("La grille n'a pas pu être résolue.");
            assertFalse(result);
        }
    }
}


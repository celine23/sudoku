import static org.junit.jupiter.api.Assertions.assertEquals;

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
import fr.upjv.miage.sudoku.SolveurClasse;

/**
 *
 * @author GroupeJ
 */
public class SolveurTest {

    /**
     * Cette variable représente l'élément initial 1.
     */
    ElementDeGrille initialElement1 = new ElementDeGrilleImplAsChar('1', true);

    /**
     * Cette variable représente l'élément initial 2.
     */
    ElementDeGrille initialElement2 = new ElementDeGrilleImplAsChar('2', true);

    /**
     * Cette variable représente l'élément initial 3.
     */
    ElementDeGrille initialElement3 = new ElementDeGrilleImplAsChar('3', true);

    /**
     * Cette variable représente l'élément initial 4.
     */
    ElementDeGrille initialElement4 = new ElementDeGrilleImplAsChar('4', true);

    /**
     * Cette variable représente la première grille.
     */
    ElementDeGrille[][] gridElements1 = {
            { initialElement2, null, initialElement3, null },
            { null, null, null, null },
            { null, null, null, null },
            { initialElement3, null, initialElement4, null },
    };

    /**
     * Cette variable représente la deuxième grille.
     */
    ElementDeGrille[][] gridElements2= {
            { initialElement1, null, initialElement2, initialElement3 },
            { initialElement3, initialElement4, initialElement1, initialElement2 },
            { initialElement4, initialElement2, initialElement3, initialElement1 },
            { initialElement2, initialElement1, initialElement4, initialElement4 },
    };

    /**
     * Cette variable représente la valeur 1 d'une case.
     */
    ElementDeGrille value1 = new ElementDeGrilleImplAsChar('1');
    /**
     * Cette variable représente la valeur 1 d'une case.
     */
    ElementDeGrille value2 = new ElementDeGrilleImplAsChar('2');

    /**
     * Cette variable représente la valeur 1 d'une case.
     */
    ElementDeGrille value3 = new ElementDeGrilleImplAsChar('3');

    /**
     * Cette variable représente la valeur 1 d'une case.
     */
    ElementDeGrille value4 = new ElementDeGrilleImplAsChar('4');


    /**
     * Cette méthode calcule la somme des valeurs.
     * @return L'élement attendu.
     */
    private Set<ElementDeGrille> getExpectedElement() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(value1);
        expectedElements.add(value2);
        expectedElements.add(value3);
        expectedElements.add(value4);

        return expectedElements;
    }

    Grille  validGrid = new GrilleImpl(gridElements1, this.getExpectedElement());

    Grille invalidGrid = new GrilleImpl(gridElements2, this.getExpectedElement());

    Solveur solver = new SolveurClasse();


    @Timeout(5)
    @Test
    public void testValidSolving() throws HorsBornesException, ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException {
        System.out.println( validGrid);

        boolean result = solver.solve( validGrid);

        System.out.println("grille resolue : ");
        System.out.println( validGrid);

        assertEquals(true,  validGrid.isComplete());
        assertEquals(true, result);

    }

    @Timeout(5)
    @Test
    public void testInvalidSolving() throws HorsBornesException, ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException {

        System.out.println(invalidGrid);
        boolean result = solver.solve(invalidGrid);

        System.out.println(invalidGrid);

        assertEquals(false, result);
    }

}

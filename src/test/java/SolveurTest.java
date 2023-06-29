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
 * Classe de test pour Solveur.
 *
 * @author GroupeJ
 */
public class SolveurTest {

    /**
     * Cette variable représente l'élément timeOut.
     */
    private static final int TIMEOUT_SECONDS = 5;

    /**
     * Cette variable représente l'élément initial 1.
     */
    private ElementDeGrille initialElement1 =
            new ElementDeGrilleImplAsChar('1', true);

    /**
     * Cette variable représente l'élément initial 2.
     */
    private ElementDeGrille initialElement2 =
            new ElementDeGrilleImplAsChar('2', true);

    /**
     * Cette variable représente l'élément initial 3.
     */
    private ElementDeGrille initialElement3 =
            new ElementDeGrilleImplAsChar('3', true);

    /**
     * Cette variable représente l'élément initial 4.
     */
    private ElementDeGrille initialElement4 =
            new ElementDeGrilleImplAsChar('4', true);

    /**
     * Cette variable représente la première grille.
     */
    private ElementDeGrille[][] gridElements1 =
            {{initialElement2, null, initialElement3, null },
            {null, null, null, null },
            {null, null, null, null },
            {initialElement3, null, initialElement4, null },
    };

    /**
     * Cette variable représente la deuxième grille.
     */
    private ElementDeGrille[][] gridElements2 = {
            {initialElement1, null,
                    initialElement2, initialElement3 },
            {initialElement3, initialElement4,
                    initialElement1, initialElement2 },
            {initialElement4, initialElement2,
                    initialElement3, initialElement1 },
            {initialElement2, initialElement1,
                    initialElement4, initialElement4 },
    };

    /**
     * Cette variable représente la valeur 1 d'une case.
     */
    private ElementDeGrille value1 = new ElementDeGrilleImplAsChar('1');

    /**
     * Cette variable représente la valeur 2 d'une case.
     */
    private ElementDeGrille value2 = new ElementDeGrilleImplAsChar('2');

    /**
     * Cette variable représente la valeur 3 d'une case.
     */
    private ElementDeGrille value3 = new ElementDeGrilleImplAsChar('3');

    /**
     * Cette variable représente la valeur 4 d'une case.
     */
    private ElementDeGrille value4 = new ElementDeGrilleImplAsChar('4');

    /**
     * Cette méthode calcule la somme des valeurs.
     *
     * @return L'ensemble des éléments attendus.
     */
    private Set<ElementDeGrille> getExpectedElement() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(value1);
        expectedElements.add(value2);
        expectedElements.add(value3);
        expectedElements.add(value4);

        return expectedElements;
    }

    /**
     * Cette variable représente une grille valide.
     */
    private Grille validGrid = new GrilleImpl(
            gridElements1, this.getExpectedElement());

    /**
     * Cette variable représente une grille invalide.
     */
    private Grille invalidGrid = new
            GrilleImpl(gridElements2, this.getExpectedElement());

    /**
     * Cette variable représente le solveur.
     */
    private Solveur solver = new SolveurClasse();

    /**
     * Teste la résolution d'une grille valide.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     * @throws ValeurImpossibleException valeur impossible.
     * @throws ValeurInitialeModificationException valeur init mod.
     */
    @Timeout(TIMEOUT_SECONDS)
    @Test
    public final void testValidSolving()
            throws HorsBornesException, ElementInterditException,
            ValeurImpossibleException,
            ValeurInitialeModificationException {
        System.out.println(validGrid);

        boolean result = solver.solve(validGrid);

        System.out.println("grille resolue : ");
        System.out.println(validGrid);

        assertEquals(true, validGrid.isComplete());
        assertEquals(true, result);
    }

    /**
     * Teste la résolution d'une grille invalide.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     * @throws ValeurImpossibleException Si une valeur est impossible.
     * @throws ValeurInitialeModificationException val init mod.
     */
    @Timeout(TIMEOUT_SECONDS)
    @Test
    public final void testInvalidSolving()
            throws HorsBornesException,
            ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException {
        System.out.println(invalidGrid);
        boolean result = solver.solve(invalidGrid);

        System.out.println(invalidGrid);

        assertEquals(false, result);
    }
}

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    /**
     * Cette variable représente la grille.
     */
    private GrilleImpl grilleTest;

    /**
     * déclaration de la dimension.
     */
    private static final int DIM = 9;

    /**
     * déclaration d'un element.
     */
    private static final int X = 3;

    /**
     * déclaration d'un element.
     */
    private static final int Y = 5;

    /**
     * déclaration d'un element.
     */
    private static final int QT = 4;

    /**
     * déclaration d'un element.
     */
    private static final int ST = 7;


    /**
     * Initialisation de la grille de jeu avant chaque test.
     * Crée une grille avec les éléments autorisés de 1 à 9.
     */
    @BeforeEach
    public final void setUp() {
        // Initialiser la grille avant chaque test
        ElementDeGrille[][] grille = new ElementDeGrille[DIM][DIM];
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
        grilleTest = new GrilleImpl(DIM, grille, elementAutorise);
    }


    /**
     * Test de la méthode getElements.
     * Vérifie que éléments retournés = éléments attendus.
     */
    @Test
    public final void testGetElements() {
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

    /**
     * Test de la méthode GetDimension.
     * récupère la dimension de la grille.
     */
    @Test
    public final void testGetDimension() {
        int expectedDimension = DIM;
        int actualDimension = grilleTest.getDimension();
        assertEquals(expectedDimension, actualDimension);
    }


    /**
     * Teste ajout val.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     */
    @Test
    public final void testSetValue() throws HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('1');
        int x = 0;
        int y = 0;

        assertDoesNotThrow(() -> grilleTest.setValue(x, y, value));

        assertEquals(value, grilleTest.getValue(x, y));
    }

    /**
     * Teste la récup d'une val.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     * @throws ValeurImpossibleException valeur impossible.
     * @throws ValeurInitialeModificationException valeur init mod.
     */
    @Test
    public final void testGetValue() throws ElementInterditException,
            ValeurInitialeModificationException, HorsBornesException,
            ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('Y');
        int x = X;
        int y = Y;

        grilleTest.setValue(x, y, value);

        ElementDeGrille actualValue = grilleTest.getValue(x, y);

        assertEquals(value, actualValue);
    }


    /**
     * vérif si la grille est complète.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     * @throws ValeurImpossibleException valeur impossible.
     * @throws ValeurInitialeModificationException valeur init mod.
     */
    @Test
    public final void testIsComplete() throws ElementInterditException,
            ValeurInitialeModificationException, HorsBornesException,
            ValeurImpossibleException {
        // Créer une grille de test
        int dimension = X;
        ElementDeGrille[][] grille = new ElementDeGrille[dimension][dimension];
        grille[0][0] = new ElementDeGrilleImplAsChar('1');
        grille[0][1] = new ElementDeGrilleImplAsChar('2');
        grille[0][2] = new ElementDeGrilleImplAsChar('3');
        grille[1][0] = new ElementDeGrilleImplAsChar('4');
        grille[1][1] = new ElementDeGrilleImplAsChar('5');
        grille[1][2] = new ElementDeGrilleImplAsChar('6');
        grille[2][0] = new ElementDeGrilleImplAsChar('7');
        grille[2][1] = new ElementDeGrilleImplAsChar('8');
        grille[2][2] = new ElementDeGrilleImplAsChar('9');

        Set<ElementDeGrille> elementsAutorises = new HashSet<>();
        elementsAutorises.add(new ElementDeGrilleImplAsChar('1'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('2'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('3'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('4'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('5'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('6'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('7'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('8'));
        elementsAutorises.add(new ElementDeGrilleImplAsChar('9'));

        Grille grilleTest = new GrilleImpl(grille, elementsAutorises);

        // Vérifier si la grille est complète
        assertTrue(grilleTest.isComplete());

        // Modifier une valeur dans la grille
        grille[2][1] = null;
        assertTrue(grilleTest.isComplete());

        // Vérifier que la grille n'est plus complète
        grilleTest.setValue(2, 1, null);
        assertFalse(grilleTest.isComplete());
    }



    /**
     * Teste si c'est possible.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     */
    @Test
    public final void testIsPossible() throws ElementInterditException,
            HorsBornesException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('3');
        int x = 0;
        int y = 0;

        assertTrue(grilleTest.isPossible(x, y, value));
    }


    /**
     * vérif si c'est la val init.
     *
     * @throws HorsBornesException Si une valeur est hors des bornes autorisées.
     * @throws ElementInterditException Si un élément est interdit.
     * @throws ValeurImpossibleException valeur impossible.
     * @throws ValeurInitialeModificationException valeur init mod.
     */
    @Test
    public final void testIsValeurInitiale() throws ElementInterditException,
            ValeurInitialeModificationException, HorsBornesException,
            ValeurImpossibleException {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('8');
        int x = QT;
        int y = ST;

        grilleTest.setValue(x, y, value);

        assertFalse(grilleTest.isValeurInitiale(x, y));
    }
}


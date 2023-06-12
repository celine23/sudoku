package c306.sudoku;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class GrilleTest {

    @Test
    public void testGetElements() {
        Grille grille = createEmptyGrille();
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        assertEquals(expectedElements, grille.getElements());
    }

    @Test
    public void testGetDimension() {
        Grille grille = createEmptyGrille();
        int expectedDimension = 9; // Mettre ici la dimension attendue de la grille
        assertEquals(expectedDimension, grille.getDimension());
    }

    @Test
    public void testSetValue() {
        Grille grille = createEmptyGrille();
        int x = 0; // Mettre ici les coordonnées de la case à tester
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN; // Mettre ici la valeur à tester
        try {
            grille.setValue(x, y, value);
        } catch (Exception e) {
            fail("Une exception ne devrait pas être lancée ici : " + e.getMessage());
        }
        // Ajouter d'autres assertions pour vérifier si la valeur a été correctement affectée
    }

    @Test(expected = HorsBornesException.class)
    public void testSetValueHorsBornesException() throws HorsBornesException {
        Grille grille = createEmptyGrille();
        int x = -1; // Mettre ici des coordonnées hors bornes
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        grille.setValue(x, y, value);
    }

    @Test(expected = ValeurImpossibleException.class)
    public void testSetValueValeurImpossibleException() throws ValeurImpossibleException {
        Grille grille = createEmptyGrille();
        int x = 0;
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        // Ajouter ici les étapes nécessaires pour rendre la valeur impossible
        grille.setValue(x, y, value);
    }

    @Test(expected = ElementInterditException.class)
    public void testSetValueElementInterditException() throws ElementInterditException {
        Grille grille = createEmptyGrille();
        int x = 0;
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        // Ajouter ici les étapes nécessaires pour rendre l'élément interdit
        grille.setValue(x, y, value);
    }

    @Test(expected = ValeurInitialeModificationException.class)
    public void testSetValueValeurInitialeModificationException() throws ValeurInitialeModificationException {
        Grille grille = createEmptyGrille();
        int x = 0;
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        // Ajouter ici les étapes nécessaires pour rendre la valeur initiale
        grille.setValue(x, y, value);
    }

    @Test
    public void testGetValue() {
        Grille grille = createEmptyGrille();
        int x = 0; // Mettre ici les coordonnées de la case à tester
        int y = 0;
        try {
            ElementDeGrille value = grille.getValue(x, y);
            assertNull(value); // Vérifiez si la valeur est null
        } catch (Exception e) {
            fail("Une exception ne devrait pas être lancée ici : " + e.getMessage());
        }
    }

    @Test(expected = HorsBornesException.class)
    public void testGetValueHorsBornesException() throws HorsBornesException {
        Grille grille = createEmptyGrille();
        int x = -1; // Mettre ici des coordonnées hors bornes
        int y = 0;
        grille.getValue(x, y);
    }

    @Test
    public void testIsComplete() {
        Grille grille = createEmptyGrille();
        assertFalse(grille.isComplete()); // La grille est vide, donc elle n'est pas complète
        // Ajouter des assertions pour tester le cas où la grille est complète
    }

    @Test(expected = HorsBornesException.class)
    public void testIsPossibleHorsBornesException() throws HorsBornesException, ElementInterditException {
        Grille grille = createEmptyGrille();
        int x = -1; // Mettre ici des coordonnées hors bornes
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        grille.isPossible(x, y, value);
    }

    @Test(expected = ElementInterditException.class)
    public void testIsPossibleElementInterditException() throws HorsBornesException, ElementInterditException {
        Grille grille = createEmptyGrille();
        int x = 0;
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN;
        // Ajouter ici les étapes nécessaires pour rendre l'élément interdit
        grille.isPossible(x, y, value);
    }

    @Test
    public void testIsPossible() {
        Grille grille = createEmptyGrille();
        int x = 0; // Mettre ici les coordonnées de la case à tester
        int y = 0;
        ElementDeGrille value = ElementDeGrille.UN; // Mettre ici la valeur à tester
        try {
            boolean isPossible = grille.isPossible(x, y, value);
            assertTrue(isPossible); // Vérifiez si la valeur est possible
        } catch (Exception e) {
            fail("Une exception ne devrait pas être lancée ici : " + e.getMessage());
        }
    }

    @Test
    public void testIsValeurInitiale() {
        Grille grille = createEmptyGrille();
        int x = 0; // Mettre ici les coordonnées de la case à tester
        int y = 0;
        assertFalse(grille.isValeurInitiale(x, y)); // Vérifiez si la valeur n'est pas initiale
        // Ajouter des assertions pour tester le cas où la valeur est initiale
    }

    // Méthode utilitaire pour créer une grille vide pour les tests
    private Grille createEmptyGrille() {
        return new Grille() {
            @Override
            public Set<ElementDeGrille> getElements() {
                return new HashSet<>();
            }

            @Override
            public int getDimension() {
                return 9; // Mettre ici la dimension attendue de la grille
            }

            @Override
            public void setValue(int x, int y, ElementDeGrille value) throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModificationException {
                // Implémentation vide
            }

            @Override
            public ElementDeGrille getValue(int x, int y) throws HorsBornesException {
                return null; // Implémentation vide
            }

            @Override
            public boolean isComplete() {
                return false; // Implémentation vide
            }

            @Override
            public boolean isPossible(int x, int y, ElementDeGrille value) throws HorsBornesException, ElementInterditException {
                return true; // Implémentation vide
            }

            @Override
            public boolean isValeurInitiale(int x, int y) {
                return false; // Implémentation vide
            }
        };
    }
}

package fr.upjv.miage.sudoku;
import java.util.HashSet;
import java.util.Set;

import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.implementation.ElementDeGrilleImplAsChar;
import fr.upjv.miage.implementation.GrilleImpl;

/**
 * Classe profiler.
 */
public final class Prof {
    /**
     * Classe main.
     * @param args variable.
     */
    public static void main(final String[] args) {

        ElementDeGrille initialElement1 =
                new ElementDeGrilleImplAsChar('1', true);
        ElementDeGrille initialElement2 =
                new ElementDeGrilleImplAsChar('2', true);
        ElementDeGrille initialElement3 =
                new ElementDeGrilleImplAsChar('3', true);
        ElementDeGrille initialElement4 =
                new ElementDeGrilleImplAsChar('4', true);

        ElementDeGrille value1 = new ElementDeGrilleImplAsChar('1');
        ElementDeGrille value2 = new ElementDeGrilleImplAsChar('2');
        ElementDeGrille value3 = new ElementDeGrilleImplAsChar('3');
        ElementDeGrille value4 = new ElementDeGrilleImplAsChar('4');

        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(value1);
        expectedElements.add(value2);
        expectedElements.add(value3);
        expectedElements.add(value4);


        ElementDeGrille[][] elements2d = {
                {initialElement2, null, initialElement3, null },
                {null, null, null, null },
                {null, null, null, null },
                {initialElement3, null, initialElement4, null },
        };

        Grille grille = new GrilleImpl(elements2d, expectedElements);

        Solveur solve = new SolveurClasse();

        System.out.println(grille);

        try {
            boolean resP = solve.solve(grille);
        } catch (HorsBornesException
                 | ElementInterditException | ValeurImpossibleException
                 | ValeurInitialeModificationException e) {
            e.printStackTrace();
        }
        System.out.println("grille resolue : ");
        System.out.println(grille);
    }
}

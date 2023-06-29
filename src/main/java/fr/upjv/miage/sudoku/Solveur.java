package fr.upjv.miage.sudoku;

import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;

/**
 * Interface de résolveur de Grille.
 *
 * @author Groupe J
 */
public interface Solveur {
    /**
     * Résoud une Grille.
     *
     * @param grille la grille à résoudre
     * @return true si la grille a été résolue
     * @throws HorsBornesException exception.
     * @throws ElementInterditException exception.
     * @throws ValeurImpossibleException exception.
     * @throws ValeurInitialeModificationException exception.
     */
    boolean solve(Grille grille)
            throws HorsBornesException, ElementInterditException,
            ValeurImpossibleException,
            ValeurInitialeModificationException;

    /**
     * boolean solver.
     * @param validGrid validgrid.
     * @return boolean.
     */
   boolean solver(Grille validGrid);

}

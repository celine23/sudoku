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
     * Résout une Grille.
     *
     * @param grille la grille à résoudre
     * @return true si la grille a été résolue
     * @throws HorsBornesException  si une position est hors des bornes de la grille
     * @throws ElementInterditException si un élément est interdit dans la grille
     * @throws ValeurImpossibleException si une valeur est impossible à placer dans la grille
     * @throws ValeurInitialeModificationException si une valeur initiale est modifiée dans la grille
     *
     */
    public boolean solve(Grille grille)
            throws HorsBornesException, ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException;


   boolean solver(Grille validGrid);

}
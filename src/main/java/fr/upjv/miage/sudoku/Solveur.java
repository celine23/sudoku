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
    /**
     * ClasseSolveur.
     */
    public class VotreClasseSolveur implements Solveur {

        @Override
        public boolean solve(final Grille grille) throws HorsBornesException,
                ElementInterditException, ValeurImpossibleException,
                ValeurInitialeModificationException {
            // Vérifier si la grille est complète, c'est-à-dire si toutes les cases sont remplies
            if (grille.isComplete()) {
                return true; // La grille est résolue
            }

            // Parcourir chaque case de la grille
            for (int x = 0; x < grille.getDimension(); x++) {
                for (int y = 0; y < grille.getDimension(); y++) {
                    // Vérifier si la case est vide
                    if (grille.getValue(x, y) == null) {
                        // Essayer chaque valeur possible dans la case
                        for (ElementDeGrille valeur : grille.getElements()) {
                            // Vérifier si la valeur est possible à cette position
                            if (grille.isPossible(x, y, valeur)) {
                                // Placer la valeur dans la case
                                grille.setValue(x, y, valeur);

                                // Résoudre récursivement la grille après avoir placé la valeur
                                if (solve(grille)) {
                                    return true; // La grille est résolue
                                }

                                // Si la grille n'est pas résolue, retirer la valeur de la case
                                grille.setValue(x, y, null);
                            }
                        }

                        // Si aucune valeur possible ne convient, revenir en arrière
                        return false;
                    }
                }
            }
            return false; // La grille est impossible à résoudre
        }
    }
}
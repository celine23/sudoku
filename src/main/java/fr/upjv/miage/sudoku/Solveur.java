package fr.upjv.miage.sudoku;
import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;


/**
 * Interface de résolveur de Grille
 *
 * @author Groupe J
 */
public interface Solveur {
    /**
     * Résoudre une Grille
     *
     * @return true si la grille a été résolue
     */

   /* public class VotreClasseSolveur implements Solveur {

        public boolean solve(Grille grille) throws HorsBornesException,
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

    }*/


    boolean solve(Grille grille) throws HorsBornesException,
            ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException;;
}

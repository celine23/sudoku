package fr.upjv.miage.sudoku;
import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
/**
 * Interface de résolveur de Grille
 *
 * @author Sébastien Choplin <sebastien.choplin@u-picardie.fr>
 */
public interface Solveur {
    /**
     * Résoud une Grille
     *
     * @param grille Grille à résoudre
     * @return true si la grille a été résolue
     */
    boolean solve(Grille grille) throws HorsBornesException,
            ElementInterditException, ValeurImpossibleException,
            ValeurInitialeModificationException;;
}

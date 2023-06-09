package fr.upjv.miage.implementation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.upjv.miage.sudoku.ElementDeGrille;
import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.sudoku.Grille;

/**
 * Implementation de l'interface Grille.
 */
public class GrilleImpl implements Grille {
    /**
     * Tableau representant les cases de la grille.
     */
    private final ElementDeGrille[][] casesGrille;
    /**
     * Ensemble des elements autorises dans la grille.
     */
    private final Set<ElementDeGrille> elementAutorise;

    /**
     * Constructeur pour créer une nouvelle
     * instance de GrilleImpl avec une grille de
     * dimension donnée.
     *
     * @param dimension la dimension de la grille
     */
    public GrilleImpl(final int dimension) {
        this.casesGrille = new ElementDeGrille[dimension][dimension];
        this.elementAutorise = getExpectedElement();
    }
    /**
     * GrilleImpl.
     * @param grille grille.
     * @param dimension dimension.
     * @param elementAutorise elementAutorise.
     */
    public GrilleImpl(final int dimension, final ElementDeGrille[][] grille, final Set<ElementDeGrille> elementAutorise) {
        this.casesGrille = new ElementDeGrille[dimension][dimension];
        // Effectuer une copie défensive du tableau grille
        for (int i = 0; i < grille.length; i++) {
            System.arraycopy(grille[i], 0, this.casesGrille[i], 0, grille[i].length);
        }
        // Effectuer une copie défensive de l'ensemble elementAutorise
        this.elementAutorise = new HashSet<>(elementAutorise);
    }

    /**
     * Retourne un ensemble contenant tous
     * les elements autorises dans la grille.
     *
     * @return ensemble contenant les elements autorises
     */
    private Set<ElementDeGrille> getExpectedElement() {
        Set<ElementDeGrille> expectedElements = new HashSet<>();
        expectedElements.add(new ElementDeGrilleImplAsChar('1'));
        expectedElements.add(new ElementDeGrilleImplAsChar('2'));
        expectedElements.add(new ElementDeGrilleImplAsChar('3'));
        expectedElements.add(new ElementDeGrilleImplAsChar('4'));
        expectedElements.add(new ElementDeGrilleImplAsChar('5'));
        expectedElements.add(new ElementDeGrilleImplAsChar('6'));
        expectedElements.add(new ElementDeGrilleImplAsChar('7'));
        expectedElements.add(new ElementDeGrilleImplAsChar('8'));
        expectedElements.add(new ElementDeGrilleImplAsChar('9'));
        return expectedElements;
    }

    /**
     * Constructeur pour créer une nouvelle
     * instance de GrilleImpl avec une grille
     * et une liste d'éléments autorisés.
     * @param grille               une grille
     * @param paramElementAutorise une liste d'éléments autorisés
     */
    public GrilleImpl(final ElementDeGrille[][] grille,
                      final Set<ElementDeGrille> paramElementAutorise) {
        if (grille == null) {
            throw new
                    IllegalArgumentException("Grille null impossible");
        }
        this.casesGrille = new ElementDeGrille[grille.length][grille[0].length];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                this.casesGrille[i][j] = grille[i][j];
            }
        }
        this.elementAutorise = new HashSet<>(paramElementAutorise);
    }

    /**
     * Constructeur pour créer une nouvelle
     * instance de GrilleImpl avec une grille.
     *
     * @param elementDeGrilleMap element grille map.
     * @param grille une grille.
     */
    public GrilleImpl(final Map<Character, ElementDeGrille> elementDeGrilleMap,
                      final ElementDeGrille[][] grille) {

        this.casesGrille = new ElementDeGrille[grille.length][grille[0].length];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                this.casesGrille[i][j] = grille[i][j];
            }
        }

        this.elementAutorise = getExpectedElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<ElementDeGrille> getElements() {
        Set<ElementDeGrille> elements = new HashSet<>();
        elements.addAll(elementAutorise);
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    public final int getDimension() {
        int dimension = casesGrille.length;
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ElementDeGrille getValue(final int x, final int y)
            throws HorsBornesException {
        if (x < 0 || x >= casesGrille.length
                || y < 0 || y >= casesGrille[x].length) {
            throw new HorsBornesException("valeur hors borne");
        }
        return casesGrille[x][y];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isComplete() {
        boolean bc = true;
        int nbLignes = casesGrille[0].length;
        int nbColonnes = casesGrille.length;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (casesGrille[i][j] == null) {
                    bc = false;
                }
            }
        }
        return bc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isValeurInitiale(final int x, final int y) {
        boolean vi = false;
        try {
            if (getValue(x, y) != null
                    && ((ElementDeGrilleImplAsChar) casesGrille[x][y])
                    .getInitialValueValidated()) {
                vi = true;
            }
        } catch (HorsBornesException e) {
            vi = false;
        }
        return vi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setValue(final int x,
                               final int y, final ElementDeGrille value)
            throws HorsBornesException, ValeurImpossibleException,
            ElementInterditException, ValeurInitialeModificationException {

        if (x < 0 || x >= casesGrille.length
                || y < 0 || y >= casesGrille[x].length) {
            throw new HorsBornesException("valeur hors borne");
        }
        if (isValeurInitiale(x, y)) {
            throw new
                    ValeurInitialeModificationException("impossible");
        }

        if (!isPossible(x, y, value)) {
            throw new ValeurImpossibleException("valeur impossible a placer");
        }

        casesGrille[x][y] = value;
    }




    /**
     * Vérifie si une valeur donnée peut être placée à une position donnée de la
     * grille.
     *
     * @param x     l'indice de ligne de la position à vérifier
     * @param y     l'indice de colonne de la position à vérifier
     * @param value la valeur à placer dans la grille
     * @return true si la valeur peut être placée
     *         à la position donnée, false sinon
     * @throws HorsBornesException      si la position (x, y) est
     *                                  hors des bornes de
     *                                  la grille
     * @throws ElementInterditException si la valeur à placer n'est
     *                                  pas autorisée
     */
    @Override
    public final boolean isPossible(
            final int x, final int y, final ElementDeGrille value)
            throws HorsBornesException, ElementInterditException {

        if (x < 0 || x >= casesGrille.length
                || y < 0 || y >= casesGrille[x].length) {
            throw new HorsBornesException(
                    "valeur hors borne");
        }

        if (isValeurInitiale(x, y)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        for (int i = 0; i < getDimension(); i++) {
            if (value.equals(casesGrille[x][i])) {
                return false;
            }
        }

        for (int j = 0; j < getDimension(); j++) {
            if (value.equals(casesGrille[j][y])) {
                return false;
            }
        }

        double tailleSousGrille = Math.sqrt(getDimension());
        int tailleSousGrillereel = (int) Math.floor(tailleSousGrille);
        int debutX = (x / tailleSousGrillereel) * tailleSousGrillereel;
        int debutY = (y / tailleSousGrillereel) * tailleSousGrillereel;
        for (int i = debutX; i < debutX + tailleSousGrillereel; i++) {
            for (int j = debutY; j < debutY + tailleSousGrillereel; j++) {
                if (value.equals(casesGrille[i][j])) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     *
     * Retourne une représentation textuelle de la grille sous
     * forme de chaîne de caractères.
     *
     * @return une chaîne de caractères représentant la grille
     */
    @Override
    public final String toString() {
        StringBuilder chaine = new StringBuilder();
        double nbLignes = getDimension();
        double nbColonnes = nbLignes;
        for (int i = 0; i < nbLignes; i++) {
            chaine.append(i + ": ");
            for (int j = 0; j < nbColonnes; j++) {
                chaine.append(casesGrille[i][j] + " ");
            }
            chaine.append('\n');
        }
        return chaine.toString();
    }
}

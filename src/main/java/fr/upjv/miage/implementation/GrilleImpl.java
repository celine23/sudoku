package fr.upjv.miage.implementation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.upjv.miage.sudoku.ElementDeGrille;
import fr.upjv.miage.exception.ElementInterditException;
import fr.upjv.miage.exception.HorsBornesException;
import fr.upjv.miage.exception.ValeurImpossibleException;
import fr.upjv.miage.exception.ValeurInitialeModificationException;
import fr.upjv.miage.sudoku.ElementDeGrille;
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

    public GrilleImpl(int dimension, ElementDeGrille[][] grille, Set<ElementDeGrille> elementAutorise) {
        this.casesGrille = grille;
        this.elementAutorise = elementAutorise;
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
            throw new IllegalArgumentException("Le tableau grille ne peut pas être null");
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
     * @param elementDeGrilleMap
     * @param grille             une grille
     */
    public GrilleImpl(Map<Character, ElementDeGrille> elementDeGrilleMap, final ElementDeGrille[][] grille) {

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
    public final void setValue(final int x, final int y, final ElementDeGrille value)
            throws HorsBornesException, ValeurImpossibleException,
            ElementInterditException, ValeurInitialeModificationException {

        if (x < 0 || x >= casesGrille.length || y < 0 || y >= casesGrille[x].length) {
            throw new HorsBornesException("valeur hors borne");
        }
        if (isValeurInitiale(x, y)) {
            throw new ValeurInitialeModificationException("impossible de modifier une valeur initiale");
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
    public boolean isPossible(int x, int y, ElementDeGrille value) {
        // Vérifier si la valeur existe déjà dans la même ligne ou colonne
        for (int i = 0; i < dimension; i++) {
            if (grille[x][i] != null && grille[x][i].equals(value)) {
                return false; // La valeur existe déjà dans la même ligne
            }
            if (grille[i][y] != null && grille[i][y].equals(value)) {
                return false; // La valeur existe déjà dans la même colonne
            }
        }

        // Vérifier si la valeur existe déjà dans le même bloc
        int blocX = x / 3; // Calculer le numéro du bloc en fonction des coordonnées
        int blocY = y / 3;
        int startX = blocX * 3; // Calculer les coordonnées de départ du bloc
        int startY = blocY * 3;

        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (grille[i][j] != null && grille[i][j].equals(value)) {
                    return false; // La valeur existe déjà dans le même bloc
                }
            }
        }

        return true; // La valeur peut être placée à cet endroit
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

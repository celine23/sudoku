package fr.upjv.miage;

import fr.upjv.miage.sudoku.Solveur;
import fr.upjv.miage.sudoku.SolveurClasse;

/**
 * Classe principale du programme.
 */
public class Main {


        public static void main(String[] args) {
            int[][] grid = {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

            Solveur solver = new SolveurClasse();
            boolean isSolved = solver.solver(grid);

            if (isSolved) {
                System.out.println("Grille résolue :");
                printGrid(grid);
            } else {
                System.out.println("La grille n'a pas de solution.");
            }
        }

        private static void printGrid(int[][] grid) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    System.out.print(grid[row][col] + " ");
                }
                System.out.println();
            }
        }

}


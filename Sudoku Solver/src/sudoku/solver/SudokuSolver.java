package sudoku.solver;

import java.util.Scanner;


public class SudokuSolver {
    public static void main(String[] args) {
        int[][] sudokuMatrix = new int[9][9];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the Sudoku puzzle 9X9 (use 0 to represent empty cells):");
        for (int i = 0; i < 9; i++) {
            String[] rowValues = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < 9; j++) {
                sudokuMatrix[i][j] = Integer.parseInt(rowValues[j]);
            }
        }

        if (sudokuSolver(sudokuMatrix)) {
            System.out.println("Sudoku puzzle solved:");
            printSudoku(sudokuMatrix);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }

        scanner.close();
    }

    public static boolean sudokuSolver(int[][] sudokuMatrix) {
        int[] emptyCell = findEmptyCell(sudokuMatrix);
        if (emptyCell == null) {
            return true;
        }
        int row = emptyCell[0];
        int col = emptyCell[1];
        for (int num = 1; num <= 9; num++) {
            if (isValid(sudokuMatrix, row, col, num)) {
                sudokuMatrix[row][col] = num;
                if (sudokuSolver(sudokuMatrix)) {
                    return true;
                }
                sudokuMatrix[row][col] = 0;
            }
        }
        return false;
    }

    private static int[] findEmptyCell(int[][] sudokuMatrix) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudokuMatrix[row][col] == 0) {
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }

    private static boolean isValid(int[][] sudokuMatrix, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudokuMatrix[row][i] == num || sudokuMatrix[i][col] == num) {
                return false;
            }
        }
        int subgridRowStart = row - row % 3;
        int subgridColStart = col - col % 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (sudokuMatrix[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSudoku(int[][] sudokuMatrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

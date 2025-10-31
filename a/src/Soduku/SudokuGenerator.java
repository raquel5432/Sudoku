/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soduku;

/**
 *
 * @author trigo
 */
import java.util.Random;

public class SudokuGenerator extends Sbase {

    private Random random = new Random();

    public void generarTablero(String dificultad) {
        limpiarTablero();
        llenarDiagonal();
        resolverSudoku(0, 0);
        eliminarCeldas(dificultad);
    }

    private void limpiarTablero() {
        for (int i = 0; i < sz; i++)
            for (int j = 0; j < sz; j++)
                tablero[i][j] = 0;
    }

    private void llenarDiagonal() {
        for (int i = 0; i < sz; i += 3)
            llenarSubcuadro(i, i);
    }

    private void llenarSubcuadro(int fila, int col) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = random.nextInt(9) + 1;
                } while (!numDisponible(fila, col, num));
                tablero[fila + i][col + j] = num;
            }
    }

    private boolean numDisponible(int fila, int col, int num) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (tablero[fila + i][col + j] == num)
                    return false;
        return true;
    }

    private boolean esSeguro(int fila, int col, int num) {
        for (int x = 0; x < 9; x++)
            if (tablero[fila][x] == num || tablero[x][col] == num)
                return false;

        int startRow = fila - fila % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (tablero[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    private boolean resolverSudoku(int fila, int col) {
        if (fila == sz - 1 && col == sz)
            return true;

        if (col == sz) {
            fila++;
            col = 0;
        }

        if (tablero[fila][col] != 0)
            return resolverSudoku(fila, col + 1);

        for (int num = 1; num <= 9; num++) {
            if (esSeguro(fila, col, num)) {
                tablero[fila][col] = num;
                if (resolverSudoku(fila, col + 1))
                    return true;
            }
            tablero[fila][col] = 0;
        }
        return false;
    }

    private void eliminarCeldas(String dificultad) {
        int celdasAEliminar;
        switch (dificultad.toLowerCase()) {
            case "facil": celdasAEliminar = 35; break;
            case "medio": celdasAEliminar = 45; break;
            default: celdasAEliminar = 55; break;
        }

        while (celdasAEliminar > 0) {
            int fila = random.nextInt(9);
            int col = random.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                celdasAEliminar--;
            }
        }
    }

    public boolean verificarSolucion(int[][] intento) {
        for (int fila = 0; fila < 9; fila++)
            for (int col = 0; col < 9; col++) {
                int num = intento[fila][col];
                if (num < 1 || num > 9 || !esValido(intento, fila, col, num))
                    return false;
            }
        return true;
    }

    private boolean esValido(int[][] intento, int fila, int col, int num) {
        for (int i = 0; i < 9; i++)
            if (i != col && intento[fila][i] == num)
                return false;

        for (int i = 0; i < 9; i++)
            if (i != fila && intento[i][col] == num)
                return false;

        int startRow = fila - fila % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++)
            for (int j = startCol; j < startCol + 3; j++)
                if ((i != fila || j != col) && intento[i][j] == num)
                    return false;

        return true;
    }
}


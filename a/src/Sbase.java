/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CarlosXl
 */
public abstract class Sbase {
    
    protected final int sz = 9;
    protected final int cd = 3;
    protected int[][] b = new int[sz][sz];

    public int[][] b() {
        return b;
    }

    public abstract boolean resolver();

    public void tablero(int[][] nb) {
        if (nb == null) return;
        for (int r = 0; r < sz; r++) {
            for (int c = 0; c < sz; c++) {
                b[r][c] = nb[r][c];
            }
        }
    }

    protected boolean aseguro(int[][] b, int row, int col, int num) {
        for (int c = 0; c < sz; c++) {
            if (b[row][c] == num) return false;
        }
        for (int r = 0; r < sz; r++) {
            if (b[r][col] == num) return false;
        }
        int inicioR = row - row % cd;
        int inicioC = col - col % cd;
        for (int r = inicioR; r < inicioR + cd; r++) {
            for (int c = inicioC; c < inicioC + cd; c++) {
                if (b[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}

    

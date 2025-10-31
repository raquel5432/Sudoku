/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Soduku;

/**
 *
 * @author trigo
 */
import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {

    private JTextField[][] celdas = new JTextField[9][9];
    private SudokuGenerator generador = new SudokuGenerator();

    public SudokuGUI() {
        setTitle("Sudoku - Programacin II");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                celdas[i][j] = new JTextField();
                celdas[i][j].setHorizontalAlignment(JTextField.CENTER);
                celdas[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;

                celdas[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                panel.add(celdas[i][j]);
            }
        }

        JButton btnFacil = new JButton("Facil");
        JButton btnMedio = new JButton("Medio");
        JButton btnDificil = new JButton("Dificil");
        JButton btnVerificar = new JButton("Verificar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnFacil);
        panelBotones.add(btnMedio);
        panelBotones.add(btnDificil);
        panelBotones.add(btnVerificar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnFacil.addActionListener(e -> generar("facil"));
        btnMedio.addActionListener(e -> generar("medio"));
        btnDificil.addActionListener(e -> generar("dificil"));
        btnVerificar.addActionListener(e -> verificar());

        setVisible(true);
    }

    private void generar(String dificultad) {
        generador.generarTablero(dificultad);
        int[][] tablero = generador.tablero;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] == 0) {
                    celdas[i][j].setText("");
                    celdas[i][j].setEditable(true);
                    celdas[i][j].setForeground(Color.BLUE);
                } else {
                    celdas[i][j].setText(String.valueOf(tablero[i][j]));
                    celdas[i][j].setEditable(false);
                    celdas[i][j].setForeground(Color.BLACK);
                }
            }
        }
    }

    private void verificar() {
        int[][] intento = new int[9][9];
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = celdas[i][j].getText();
                    intento[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Solo se permiten numeros del 1 al 9");
            return;
        }

        if (generador.verificarSolucion(intento)) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Sudoku correcto ");
        } else {
            JOptionPane.showMessageDialog(this, "Sudoku incorrecto. Revisa tus numeros.");
        }
    }

 
}

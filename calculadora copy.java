import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.*;

public class calculadora {
    public static void main(String[] args) {
        int numeros[] = {7,8,9,4,5,6,1,2,3,0};
        ArrayList<String> operadoress = new ArrayList<>();
        operadoress.addAll(Arrays.asList("(",")", "n!","/","*","-","+", "+/-","," , "="));

        String operadores[] = {"(",")", "n!","/","*","-","+", "+/-","," , "="};
        double op1 = 0;
        double op2 = 0;

        // VENTANA //

        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // PANTALLA DE CÁLCULO //
        JPanel pantalla = new JPanel();

        JTextPane pantallita = new JTextPane();
        pantallita.setBorder(new LineBorder(new Color(160, 80, 190), 4));
        pantallita.setPreferredSize(new Dimension(250, 50));
        pantallita.setMaximumSize(new Dimension(340, 75));
        pantallita.setEditable(false);
        pantallita.setFont(new Font("arial", Font.BOLD, 30));

        pantalla.add(pantallita);
        
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        panel.add(pantallita);

        // BOTONES NÚMEROS //

        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(5, 5));

        int indice_op = 0;
        int indice_nu = 0;

        for (int i = 1; i <= 4; i++) {
            JPanel panel_boton = new JPanel(new BorderLayout()); 
            panel_boton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); 

            RoundedButton boton = new RoundedButton(operadores[indice_op], 25);
            // JButton boton = new JButton(operadores[indice_op]);
            boton = caracteristicas_botones(boton);

            panel_boton.add(boton, BorderLayout.CENTER);
            botones.add(panel_boton);

            indice_op +=1;
        };

        for (int i = 1; i <=16; i++) {
            if (!(i%4 == 0) && !(i==13) && !(i==15) && !(i==16)) {
                JPanel panel_boton = new JPanel(new BorderLayout()); 
                panel_boton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); 

                RoundedButton boton = new RoundedButton(String.valueOf(numeros[indice_nu]), 25);
                // JButton boton = new JButton(String.valueOf(numeros[indice_nu]));
                boton = caracteristicas_botones(boton);

                panel_boton.add(boton, BorderLayout.CENTER);
                botones.add(panel_boton);

                indice_nu +=1;
            } else {
                JPanel panel_boton = new JPanel(new BorderLayout()); 
                panel_boton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); 

                RoundedButton boton = new RoundedButton(operadores[indice_op], 25);
                // JButton boton = new JButton(operadores[indice_op]);
                boton = caracteristicas_botones(boton);

                panel_boton.add(boton, BorderLayout.CENTER);        
                botones.add(panel_boton);

                indice_op +=1;
            }
        }

        panel.add(botones);

        // FUNCIONES BOTONES //

        StringBuilder numerosOp = new StringBuilder();

        for (Component componente : botones.getComponents()) {
            if (componente instanceof JPanel) {
                JPanel panelBoton = (JPanel) componente;
                Component[] botones_array = panelBoton.getComponents();
                for (Component componente_boton : botones_array) {
                    if (componente_boton instanceof RoundedButton) {
                        RoundedButton boton = (RoundedButton) componente_boton;
                        boton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String num = boton.getText();

                                    numerosOp.append(num);
                                    pantallita.setText(numerosOp.toString());
                                    pantallita.revalidate();
                                    pantallita.repaint();

                            }
                        });
                    }
                }
            }
        }        

        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static RoundedButton caracteristicas_botones(RoundedButton boton) {
        boton.setBorder(new EmptyBorder(2,2,2,2));
        boton.setFont(new Font("arial", Font.BOLD, 16));
        boton.setBackground(new Color(60, 150, 230));

        return boton;
    }
}
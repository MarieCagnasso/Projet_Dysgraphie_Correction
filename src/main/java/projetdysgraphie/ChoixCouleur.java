/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 *
 * @author cvidal06
 */
public class ChoixCouleur {
 
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Créer le bouton
        final javax.swing.JButton button = new JButton();
        button.setText("Choisir une couleur");
//Ajouter un evenement sur le click de la souris
        MouseAdapter actionListener = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Color background = JColorChooser.showDialog(null,
                        "Choix de la couleur d'écriture", null);
                if (background != null) {
                    button.setBackground(background);
                }
            }
        };
        button.addMouseListener(actionListener);
        //Placer le button sur le Frame
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(button);
 
        frame.setSize(200, 100);
        frame.setVisible(true);
    }
}

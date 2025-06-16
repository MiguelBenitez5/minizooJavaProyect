/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.zoo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *Pantalla de carga donde se visualiza un gif con una transicion de animales
 * 
 * Ventana tipo JDialog que hereda todos los atributos y metodos de JDialog
 * @author 2-LASI-2025
 */
public class LoadingScreen extends JDialog {
    /**
     * Se contruye la ventana con todos los elementos y se visualiza en pantalla
     */
    public LoadingScreen(){
        //declaracion de elementos necesarios
        String text = "Analizando...";
        Image imgGif = new ImageIcon(getClass().getResource("/images/animalees.gif")).getImage();
        ImageIcon loadingImg = new ImageIcon(imgGif);
        //guardar elementos en labels
        JLabel textLabel = new JLabel(text);
        JLabel loadingImgLabel = new JLabel(loadingImg);
        //estilo para los labels
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingImgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setFont(new Font("Arial", Font.BOLD, 22));
        textLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 15));
        //declaracion del panel
        JPanel panel = new JPanel();
        //estilo para el panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(550, 600));
        panel.setMinimumSize(new Dimension(550, 600));
        panel.setMaximumSize(new Dimension(550, Short.MAX_VALUE));
        //agregar elementos jlabel al panel
        panel.add(textLabel);
        panel.add(loadingImgLabel);
        //Configurar posicion y mostrar dialogo en pantalla
        this.setTitle("Tu animal interior");
        this.setModal(true);
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}

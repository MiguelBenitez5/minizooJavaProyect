/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.zoo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author 2-LASI-2025
 */
public class ResultScreen extends JScrollPane{
    public ResultScreen(String[] data){
        //obtener nombre
        String name = data[0];
        //obrener imagen y qr
        String img = data[1];
        String qr = data[2];
        URL imgURL = null;
        URL qrURL = null;
        try {
            imgURL = new URL(img);
            qrURL = new URL(qr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        //procesar imagen
        ImageIcon imgIcon = new ImageIcon(imgURL);
        Image imgResized = resizeImgIfNeeded(imgIcon);
        ImageIcon finalImg = new ImageIcon(imgResized);
        //procesar qr
        ImageIcon specieQR = new ImageIcon(qrURL);
        Image resizedQR = specieQR.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon QR = new ImageIcon(resizedQR);
        //titulo y texto
        String title = "Tu animal interior es:";
        String text = "Escanea el QR para obtener mas informacion";
        //creacion de los elementos label
        JLabel titleLabel = new JLabel(title);
        JLabel nameLabel = new JLabel(name);
        JLabel imgLabel = new JLabel(finalImg);
        JLabel textLabel = new JLabel(text);
        JLabel qrLabel = new JLabel(QR);
        //estilizacion de los labels
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qrLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 15));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        
        nameLabel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        Color coffeBrown = new Color(101,67,33);
        nameLabel.setForeground(coffeBrown);

        textLabel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        textLabel.setFont(new Font("Arial", Font.PLAIN,20));
        
        //creacion del panel agregar los elementos al mismo
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(imgLabel);
        panel.add(textLabel);
        panel.add(qrLabel);
        //creacion del scrollPane
        this.setViewportView(panel);
        this.setPreferredSize(new Dimension(550, 650));
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    /**
     * Obtiene una imagen y la reescala si es necesario
     * @param specieIcon la imagen a comprobar
     * @return la imagen reescalada en caso de necesidad o la imagen sin modificaciones
     */
    private Image resizeImgIfNeeded(ImageIcon specieIcon){
        Image img = specieIcon.getImage();
            if(specieIcon.getIconWidth() > specieIcon.getIconHeight()){
                if(specieIcon.getIconWidth() > 350){
                    int newWidth = 350;
                    int newHeight = (int) ((double)specieIcon.getIconHeight()*newWidth)/specieIcon.getIconWidth();
                    if(newHeight > 240){
                        newHeight = 240;
                        newWidth = (specieIcon.getIconWidth() * newHeight) / specieIcon.getIconHeight();
                    }
                    img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                }
            }else if(specieIcon.getIconWidth() < specieIcon.getIconHeight()){
                if(specieIcon.getIconHeight() > 240){
                    int newHeight = 240;
                    int newWidth = (specieIcon.getIconWidth() * newHeight) / specieIcon.getIconHeight();
                    img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                }
            }else if(specieIcon.getIconWidth() == specieIcon.getIconHeight()){
                if(specieIcon.getIconHeight() > 240){
                    int newHeight = 240;
                    int newWidth = (specieIcon.getIconWidth() * newHeight) / specieIcon.getIconHeight();
                    img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                }
            }
            return img;
    }
    
    
}

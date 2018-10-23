/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.components;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author varguelles
 */
public class Imagen extends JPanel{
    private String url;
    private int width;
    private int heigh;

    public Imagen() {
    }

    public Imagen(String url, int width, int heigh) {
        this.url = url;
        this.width = width;
        this.heigh = heigh;
        this.setSize(this.width, this.heigh);
    }
    
    @Override
    public void paint(Graphics grafico) {
        Dimension height = getSize();
        
        //Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
        
        
        try{
            //ImageIcon Img = new ImageIcon(getClass().getResource(this.url));
            //ImageIcon Img = new ImageIcon(getClass().getResource(this.url));
            ImageIcon Img = new ImageIcon(getClass().getClassLoader().getResource(this.url));
            //ImageIcon Img = new ImageIcon(getImage(getCodeBase(), "/calculator/Warp.jpg"));
            
            //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(grafico);
        }catch(NullPointerException ex){
            System.out.println(ex);
        }
    }
}

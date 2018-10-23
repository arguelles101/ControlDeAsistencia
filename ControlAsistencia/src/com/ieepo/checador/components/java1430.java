/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.checador.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author varguelles
 */
public class java1430 extends JButton {

  // Constructor del Botón Redondo
  public java1430( String rotulo ) {
    super( rotulo );

    // Igualamos las dimensiones para que el botón sea un círculo en vez de
    // una elipse
    Dimension tamano = getPreferredSize();
    tamano.width = tamano.height = Math.max( tamano.width,tamano.height );
    setPreferredSize( tamano );

    // Hacemos que el JButton no pinte su fondo, de este modo podremos
    // nosotros hacer que el color de fondo que se salga de la figura sea
    // del mismo color que el fondo de la ventana
    setContentAreaFilled( false );
    }

  // Este es el método que pinta el botón en el color correspondiente al estado
  // en que se encuentre, y también coloca el rótulo que se haya indicado en el
  // centro del botón
  @Override
  protected void paintComponent( Graphics g ) {
    if( getModel().isArmed() ) {
      // Se puede hacer que la característica de Pulsado sea una propiedad de
      // esta clase
      g.setColor( Color.lightGray );
      } 
    else {
      g.setColor( getBackground() );
      }
    g.fillOval( 0,0,getSize().width-1,getSize().height-1 );

    // Llamando al método de la clase padre, haremos que aparezca el rótulo y
    // hacemos que el restángulo correspondiente al botón sea el que controla
    // el foco
    super.paintComponent( g );
    }

  // Pintamos el borde del botón con una línea simple
  @Override
  protected void paintBorder( Graphics g ) {
    g.setColor( getForeground() );
    g.drawOval( 0,0,getSize().width-1,getSize().height-1 );
    }

  // Este es el método que controla la posición del ratón en el momento de
  // pulsar su botón. Se sobreescribe para controlar los cambios de tamaño
  // del botón
  Shape figura;
  @Override
  public boolean contains( int x,int y ) {
    // En caso de que el botón cambie de tamaño, hay que conseguir una nueva
    // figura que se adapte a ese nuevo tamaño
    if( figura == null || !figura.getBounds().equals(getBounds()) ) {
      figura = new Ellipse2D.Float( 0,0,getWidth(),getHeight() );
      }
    return( figura.contains( x,y ) );
    }


  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.getContentPane().setLayout( new FlowLayout() );
    ventana.getContentPane().setBackground( Color.yellow );

    ventana.addWindowListener( new WindowAdapter() {
      @Override
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );
    
    // Se crea el botón y se le pone fondo rojo
    JButton boton = new java1430( "Boton" );
    boton.setBackground( Color.red );
    // Se incorpora a la ventana
    ventana.getContentPane().add( boton );
    ventana.setSize( 300,150 );
    ventana.show();
    }
  }
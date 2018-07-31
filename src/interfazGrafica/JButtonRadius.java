/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**JButtonRadius
Esta clase crea botones cirulares.
 * @author Roberto Garcia
 */
public class JButtonRadius extends JButton{
    
    @SuppressWarnings("FieldMayBeFinal")
    private boolean round;
    @SuppressWarnings("FieldMayBeFinal")
    private Color colorFondo;
    @SuppressWarnings("FieldMayBeFinal")
    private Color colorPresionado;
    private int f,f1;
    private Shape figura;
    
     /**JButoonRadius
     * Constructor para crear boton de forma circular.
     * @param icono
     * @param fondo
     * @param presion 
     */
    public JButtonRadius(ImageIcon icono,Color fondo,Color presion){
        super(icono);
        this.round = true;
        this.colorFondo = fondo;
        this.colorPresionado = presion;
        setContentAreaFilled(false);
    }
    
    /**JButoonRadius
     * Constructor para crear boton de forma rectangular con las esquinas redondeadas.
     * @param icono
     * @param fondo
     * @param presion
     * @param i
     * @param j 
     */
    public JButtonRadius(ImageIcon icono,Color fondo,Color presion,int i,int j){
     super(icono);
     f=i;
     f1=j;
     this.round = false;
     this.colorFondo = fondo;
     this.colorPresionado = presion;
     setContentAreaFilled(false);
    }
    
    /**paintComponent
     * Sobre escritura del metodo paintComponent para crear el fondo y contorno 
     * de la forma iniciada en el constructor.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g){
       if (getModel().isArmed()) { 
            g.setColor(colorPresionado); 
        } else { 
            g.setColor(colorFondo); 
        } 
        if (round) { 
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1); 
        } else { 
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, f, f1); 
        } 
        super.paintComponent(g); 
    }    
    
    /**paintBorder
     * Sobre escritura del metodo paintBorder para lograr el borde con la misma 
     * forma selecionada en el constructor.
     * @param g 
     */
    @ Override 
    protected void paintBorder(Graphics g) { 
        g.setColor(Color.white); 
        if (round) { 
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1); 
        } else { 
            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, f, f1); 
        } 
    } 
  
    /**contains
     * Sobre escritura de contains para actualizar el conportamiento del boton 
     * al ser oprimido.
     * @param x
     * @param y
     * @return 
     */ 
    @ Override 
    public boolean contains(int x, int y) { 
        if (round) { 
            figura = new Ellipse2D.Float(0, 0, getWidth(), getHeight()); 
       
        }else{ 
            figura = new RoundRectangle2D.Double(0, 0,getWidth(),getHeight(), f, f1); 

        } 
        return (figura.contains(x, y)); 
    } 
}

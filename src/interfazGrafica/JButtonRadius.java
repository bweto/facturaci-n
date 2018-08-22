/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * JButtonRadius Esta clase crea botones cirulares.
 *
 * @author Roberto Garcia
 */
public class JButtonRadius extends JButton {

    @SuppressWarnings("FieldMayBeFinal")
    private boolean round;
    @SuppressWarnings("FieldMayBeFinal")
    private Color colorFondo;
    @SuppressWarnings("FieldMayBeFinal")
    private Color colorPresionado;
    private int f, f1;
    private Shape figura;
    private float opacity = 1f;

    /**
     * JButoonRadius Constructor para crear boton de forma circular.
     *
     * @param icono
     * @param fondo
     * @param presion
     */
    public JButtonRadius(ImageIcon icono, Color fondo, Color presion) {
        super(icono);
        this.round = true;
        this.colorFondo = fondo;
        this.colorPresionado = presion;
        setContentAreaFilled(false);
        addMouseListener(new JButtonRadius.EventButton());
    }

    /**
     * JButoonRadius Constructor para crear boton de forma rectangular con las
     * esquinas redondeadas.
     *
     * @param icono
     * @param fondo
     * @param presion
     * @param i
     * @param j
     */
    public JButtonRadius(ImageIcon icono, Color fondo, Color presion, int i, int j) {
        super(icono);
        f = i;
        f1 = j;
        this.round = false;
        this.colorFondo = fondo;
        this.colorPresionado = presion;
        setContentAreaFilled(false);
        addMouseListener(new JButtonRadius.EventButton());
    }

    public JButtonRadius() {
        super();
        addMouseListener(new JButtonRadius.EventButton());
    }

    /**
     * paintComponent Sobre escritura del metodo paintComponent para crear el
     * fondo y contorno de la forma iniciada en el constructor.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        if (getModel().isArmed()) {
            g2.setColor(colorPresionado);
        } else {
            g2.setColor(colorFondo);
        }
        if (round) {
            g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        } else {
            g2.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, f, f1);
        }
        super.paintComponent(g2);
    }

    public float getOpacity() {
        return opacity;
    }
    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }   
    /**
     * paintBorder Sobre escritura del metodo paintBorder para lograr el borde
     * con la misma forma selecionada en el constructor.
     *
     * @param g
     */
//    @Override
//    protected void paintBorder(Graphics g) {
//        g.setColor(null);
//        if (round) {
//            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//        } else {
//            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, f, f1);
//        }
//    }

    /**
     * contains Sobre escritura de contains para actualizar el conportamiento
     * del boton al ser oprimido.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean contains(int x, int y) {
        if (round) {
            figura = new Ellipse2D.Float(0, 0, getWidth(), getHeight());

        } else {
            figura = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), f, f1);

        }
        return (figura.contains(x, y));
    }

    //*************************
    public class EventButton extends MouseAdapter {

        @Override
        public void mouseExited(MouseEvent me) {
            efectHover(0.5f, 1f, 0.03f, 10, true);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            efectHover(1f, 0.5f, 0.03f, 10, false);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            efectHover(1f, 0.6f, 0.1f, 1, false);
        }

        private void efectHover(float index, float range, float cont, int sleep, boolean event) {
            new Thread(() -> {
                for (float i = index; (event) ? i <= range : i >= range; i = (event) ? i + cont : i - cont) {
                    setOpacity(i);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
    }

    //*************************
}

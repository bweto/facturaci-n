/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author DisToshiba
 */


class JComboBoxColors extends BasicComboBoxUI{
    
    private Color colorFondo = new Color(33, 133, 191);
    
    public static ComboBoxUI CreateUi(JComponent com){
      return new JComboBoxColors();
    }
    @Override
    protected JButton createArrowButton() {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(Vista.class.getResource("/img/arrow.png")));
        btn.setBackground(colorFondo);
        btn.setContentAreaFilled(false);
        return btn; //To change body of generated methods, choose Tools | Templates.
    }
}


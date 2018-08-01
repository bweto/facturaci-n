/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author Roberto Garcia
 */
public class HeaderCellRenderer implements TableCellRenderer{
    
   @SuppressWarnings("FieldMayBeFinal")
   private Font fuenteBoltN = new Font("Sans-Serif",1, 15);
    @Override
   @SuppressWarnings("null")
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JComponent jcomponent = null;

        if(value instanceof String ) {
            jcomponent = new JLabel((String) " " + value);
            ((JLabel)jcomponent).setHorizontalAlignment(SwingConstants.CENTER );
            ((JLabel)jcomponent).setSize( 40, jcomponent.getWidth() );
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth()) );
            ((JLabel)jcomponent).setFont(fuenteBoltN);
            ((JLabel)jcomponent).setForeground(Color.WHITE);   
        } 

        jcomponent.setEnabled(true);        
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground( new Color(65,105,255));
        jcomponent.setToolTipText("Colum No. "+(column+1));
        

        return jcomponent;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
/**
 *
 * @author Roberto Garcia
 */
public class CellRenderer extends DefaultTableCellRenderer{
    private Font normal = new Font( "Arial",Font.PLAIN ,12 );
    private Color color1 = new Color(255,255,255);
    private Color color2 = new Color(240,255,255);

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {    
        setEnabled(table == null || table.isEnabled());
        setBackground(Color.white);
        table.setFont(normal);

        setBackground( (row % 2 == 1)?this.color2:this.color1  );
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
         addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
      
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground( (row % 2 == 1)?color2:color1  );
            }
        });

        return this;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import logica.CellRenderer;
import logica.Cliente;
import logica.HeaderCellRenderer;


/**
 *
 * @author Roberto Garcia
 */
public class JPModificarInformacion extends JPanel{
    
    @SuppressWarnings("FieldMayBeFinal")
    private List<Cliente> aClientes;
    @SuppressWarnings("FieldMayBeFinal")
    private JTable tblClientes;
    @SuppressWarnings("empty-statement")
    public JPModificarInformacion(List<Cliente> client){ 
     this.aClientes = client;
     this.setLayout(new BorderLayout());
     String[] titulo = {"Indice","Nombre","NIT","Telefono","Dirección"};
     Object[] arrObj = new String[5];
     DefaultTableModel model = new DefaultTableModel(titulo,0);
     int i =1;
     for(Cliente clien:aClientes){     
          arrObj[0] = ""+i; 
          arrObj[1] = clien.getNombre();
          arrObj[2] = clien.getNit();
          arrObj[3] = clien.getTelefono();
          arrObj[4] = clien.getDireccion();
          model.addRow(arrObj);
          i++;
      }
      
       tblClientes = new JTable(model);
       
       tblClientes.setPreferredScrollableViewportSize(new Dimension(320,100));
       tblClientes.getTableHeader().setReorderingAllowed(false);
       tblClientes.setFillsViewportHeight(true);
       tblClientes.setUpdateSelectionOnSort(false);
       tblClientes.setEnabled(true);
       tblClientes.setGridColor(Color.BLACK);
       tblClientes.setShowHorizontalLines( true );
       tblClientes.setColumnSelectionAllowed(true);
       tblClientes.setSelectionForeground( Color.black );
       tblClientes.setGridColor(new Color(0,191,255));
       tblClientes.setDefaultRenderer (Object.class, new CellRenderer());
       tblClientes.setSelectionBackground(new Color(0,191,255) );
       tblClientes.setAutoResizeMode(1);
       tblClientes.setSurrendersFocusOnKeystroke(false);
       tblClientes.setColumnSelectionAllowed(false);
       tblClientes.setRowHeight(25);
       tblClientes.setRowSelectionAllowed(true);
       JScrollPane scroll = new JScrollPane(tblClientes);
       tblClientes.setShowVerticalLines(false);
       JTableHeader tblheader = tblClientes.getTableHeader();
       tblheader.setDefaultRenderer(new HeaderCellRenderer());
    
       scroll.setVisible(true);
       
       add(scroll,BorderLayout.CENTER);      
       //add(tblClientes)
    }

    public JTable getTblClientes() {
        return tblClientes;
    }
    
    
    
    
                        
                        
                        
                       
                             
                            
                        
                       
}

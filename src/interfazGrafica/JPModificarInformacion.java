/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Cliente;

/**
 *
 * @author DisToshiba
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
       tblClientes.setFillsViewportHeight(true);
       tblClientes.setUpdateSelectionOnSort(false);
       tblClientes.setEnabled(true);
       tblClientes.setGridColor(Color.BLACK);
       tblClientes.setShowHorizontalLines( true );
       tblClientes.setColumnSelectionAllowed(true);
       tblClientes.setSelectionForeground( Color.black );
       tblClientes.setSelectionBackground( Color.LIGHT_GRAY );
       
       JScrollPane scroll = new JScrollPane(tblClientes);
       scroll.setVisible(true);
       
       add(scroll,BorderLayout.CENTER);      
       //add(tblClientes)
    }

    public JTable getTblClientes() {
        return tblClientes;
    }
    
    
    
    
                        
                        
                        
                       
                             
                            
                        
                       
}

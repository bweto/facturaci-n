/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import logica.Cliente;
import logica.Controlador;

/**JDVistaInfoCLientes
 *Ventana de vizualización y modificación de la información almacenada.
 * @author Roberto Garcia
 */
public class JDVistaInfoCLientes extends JDialog{
    
    @SuppressWarnings("FieldMayBeFinal")
    private JPModificarInformacion modificar;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Cliente> client;
    private JButtonRadius JBCorregir;
    /**JDVistaInfoClientes
     * Muestra la información de los clientes.
     * @param client 
     */
    public JDVistaInfoCLientes(List<Cliente> client){
        
        this.client = client;
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/escritura.png"));
        this.setIconImage(icon);
        this.setTitle("Modificar Información");
        this.setSize(320, 300);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(1);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.cerrar();
        this.modificar = new JPModificarInformacion(client);
        add(modificar,BorderLayout.CENTER);
        this.crearJBCorregir();
        this.setVisible(true);
       
    }
    
    /**cerrar
     * Se encarga de la logica de antes de cerrar la ventana.
     */
    public void cerrar(){
        this.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
             Controlador.estaActivaVerClientes = true;
             } 
});
        
    }
    
    /**crearJBCorregir
     * Crea el boton corregir.
     */
    private void crearJBCorregir(){
    ImageIcon icon = new ImageIcon(getClass().getClass().getResource("/iconos/modificar.png"));
       this.JBCorregir = new JButtonRadius(icon, Color.white, new Color(255,204,0),15,15);
       this.JBCorregir.setFocusPainted(false);
       
       add(JBCorregir,BorderLayout.AFTER_LAST_LINE);	
    }
    
    /**getJBCorregir
     * Obtine el boton corregir.
     * @return 
     */
    public JButtonRadius getJBCorregir() {
        return JBCorregir;
    }  
    
    /**getModificar
     * 
     * @return 
     */
    public JPModificarInformacion getModificar() {
        return modificar;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import logica.Controlador;

/**JFIngresoDeClientes
Ventana de opciones inicial de optención de  información para el programa.
 * @author Roberto Garcia Betancourt.
 */
public class JFIngresoDeClientes extends JDialog{
    
    @SuppressWarnings("FieldMayBeFinal")
    private JPInfoCliente panelInicial;
    public static int     seleccion = 0;
    /**JFOpcionesIniciales
     * Constructor de la calse.
     */
    public JFIngresoDeClientes() {
      
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/guardarP.png"));
        this.setIconImage(icon);
        this.setTitle("Ingreso de Información");
        this.setSize(320, 300);
        this.setLayout(null);
        this.cerrar();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        panelInicial = new JPInfoCliente();
        panelInicial.setBounds(0, 0, 320, 300);
        add(panelInicial);
        this.setVisible(true);
        
        
    }
    
    /**getPanelInicial
     * Obtiene PanelInicial
     * @return 
     */
    public JPInfoCliente getPanelInicial() {
        return panelInicial;
    }
    
    /**crearStringDatos
     * Se crea un String de datos
     * @return 
     */
    public String crearStringDatos(){
        @SuppressWarnings("UnusedAssignment")
        String datos = "";
        datos = panelInicial.getjTFNombreCliente().getText()+";"+panelInicial.getjTFNitCliente().getText()+
                ";"+panelInicial.getjTFTelefonoCliente().getText()+";"+panelInicial.getjTFDireccionCliente().getText();
        return datos;
    }
    
    public void cerrar(){
        //this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
             Controlador.estaActivaCrearClientes = true;
             } 
});
        
    }
}

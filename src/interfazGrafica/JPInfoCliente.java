/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import static interfazGrafica.JPCuerpo.fuente;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**JPInfoCliente
Componentes informativos
 * @author Roberto Garcia
 */
public class JPInfoCliente extends JPanel{
    
    private JLabel        jLInformacion;
    private JLabel        jLNombreCliente;
    private JLabel        jLNitCliente;
    private JLabel        jLTelefonoCliente;
    private JLabel        jLDireccionCliente;
    private JTextField    jTFNombreCliente;
    private JTextField    jTFNitCliente;
    private JTextField    jTFTelefonoCliente;
    private JTextField    jTFDireccionCliente;
    private JButtonRadius jBGuardar; 
    /**JPInfoInicial
     * Constructor de la calse.
     */
    public JPInfoCliente(){
        this.setBackground(Color.WHITE);
        this.setSize(320,300);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.crearJLTelefonoCliente();
        this.crearJTFNitCliente();
        this.crearJLNitCliente();
        this.crearJTFNombreCliente();
        this.crearJLNombreCliente();
        this.crearJLInformacion();
        this.crearJTFTelefonoCliente();
        this.crearJLDireccionCliente();
        this.crearJTFDireccionCliente();
        this.crearJBGuardar();
    }
    /**crearJLInformacion
     * Crea JLabel
     */
    private void crearJLInformacion() {
        String texto = "<html><body>Ingrese la información para crear un "
                + "nuevo cliente</body></html>"; 
        this.jLInformacion = new JLabel(texto);
        this.jLInformacion.setFont(fuente);
        this.jLInformacion.setBounds(10, 10, 300,20);
        add(jLInformacion);
    }
    
    /**crearJLNombreCliente
     * Crea JLNombreCliente
     */
    private void crearJLNombreCliente() {
        this.jLNombreCliente = new JLabel("Nombre: ");
        this.jLNombreCliente.setFont(fuente);
        this.jLNombreCliente.setBounds(10, 50, 80, 20);
        add(jLNombreCliente);
    }
    
    /**crearJTFNombreCliente del cliente
     * Crea JTFNombreCliente
     */
    private void crearJTFNombreCliente() {
        this.jTFNombreCliente = new JTextField();
        this.jTFNombreCliente.setBounds(95, 50, 215, 20);
        this.jTFNombreCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.jTFNombreCliente.setBackground(Color.WHITE);
	add(jTFNombreCliente);
    }
    
    /**crearJLNitCliente
     * Crea JLNitCliente
     */
    private void crearJLNitCliente() {
        this.jLNitCliente = new JLabel("Nit: ");
        this.jLNitCliente.setFont(fuente);
        this.jLNitCliente.setBounds(10, 80, 80, 20);
        add(jLNitCliente);
    }
    
    /**crearJTFNitCliente
     * Crea jTFNitCliente
     */
    private void crearJTFNitCliente() {
        this.jTFNitCliente = new JTextField();
        this.jTFNitCliente.setBounds(95, 80, 215, 20);
        this.jTFNitCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.jTFNitCliente.setBackground(Color.WHITE);
	add(jTFNitCliente);
    }
    
    /**crearJLTelefonoCliente
     * Crea jLTelefonoCliente
     */
    private void crearJLTelefonoCliente() {
        this.jLTelefonoCliente = new JLabel("Telefono: ");
        this.jLTelefonoCliente.setFont(fuente);
        this.jLTelefonoCliente.setBounds(10, 110, 80, 20);
        add(jLTelefonoCliente);
    }
    
    /**crearJTFTelefonoCliente
     * Crea jTFTelefonoCliente
     */
    private void crearJTFTelefonoCliente() {
        this.jTFTelefonoCliente = new JTextField();
        this.jTFTelefonoCliente.setBounds(95, 110, 215, 20);
        this.jTFTelefonoCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.jTFTelefonoCliente.setBackground(Color.WHITE);
	add(jTFTelefonoCliente);
    }
    
    /**crearJLDireccionCliente
     * Crea jLDireccionCliente
     */
    private void crearJLDireccionCliente() {
        this.jLDireccionCliente = new JLabel("Dirección: ");
        this.jLDireccionCliente.setFont(fuente);
        this.jLDireccionCliente.setBounds(10, 140, 80, 20);
        add(jLDireccionCliente);
    }
    
    /**crearJTFDireccionCliente
     * Crea jTFDireccionCliente
     */
    private void crearJTFDireccionCliente() {
        this.jTFDireccionCliente = new JTextField();
        this.jTFDireccionCliente.setBounds(95, 140, 215, 20);
        this.jTFDireccionCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.jTFDireccionCliente.setBackground(Color.WHITE);
	add(jTFDireccionCliente);
    }
    
    /**crearJBGuardar
     * Crea el jBGuardar
     */
    private void crearJBGuardar(){
     ImageIcon icon = new ImageIcon(getClass().getClass().getResource("/iconos/guardarG.png"));   
     this.jBGuardar = new JButtonRadius(icon, Color.white, Color.LIGHT_GRAY);
     this.jBGuardar.setBounds(120, 180, 54, 54);
     this.jBGuardar.setFocusPainted(false);
     add(jBGuardar);
    }
    
    /**getjTFNombreCliente
     * Obtiene jTFNombreCliente
     * @return jTFNombreCliente
     */ 
    public JTextField getjTFNombreCliente() {
        return jTFNombreCliente;
    }
    
    /**getjTFNitCliente
     * Obtiene jTFNitCliente
     * @return jTFNitCliente
     */ 
    public JTextField getjTFNitCliente() {
        return jTFNitCliente;
    }
    
    /**getjTFTelefonoCliente
     * Obtiene jTFTelefonoCliente
     * @return jTFTelefonoCliente
     */ 
    public JTextField getjTFTelefonoCliente() {
        return jTFTelefonoCliente;
    }
    
    /**getjTFDireccionCliente
     * Obtiene jTFDireccionCliente
     * @return jTFDireccionCliente
     */ 
    public JTextField getjTFDireccionCliente() {
        return jTFDireccionCliente;
    }
    
    /**getjBGuardar
     * Obtiene jBGuardar
     * @return getjBGuardar
     */
    public JButtonRadius getjBGuardar() {
        return jBGuardar;
    }
    
    
}

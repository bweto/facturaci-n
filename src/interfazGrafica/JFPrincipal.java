/**
 * 
 */
package interfazGrafica;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import logica.ConexionDB;

/**JFPrincipal
 * GUI donde se procede a escoger la informaci�n de la DB para crear una factura electrinica y PDF,
 * que se puede imprimir y almacenar en la base de datos.
 * @author Roberto Garcia
 */
@SuppressWarnings("serial")
public class JFPrincipal extends JFrame {
    
   private JPTitulo  titulo;
   private JPCuerpo  cuerpo;
   private JMenuBar  barraMenu;
   private JMenuItem crear;
   private JMenuItem actualizar;
   private JMenuItem modificar;
   private JMenuItem numeracion;
   private JMenuItem manual; 
   
    /**JFPrincipal()
     * Constructor basico de la clase JFPrincipal
     * @throws HeadlessException
     */
    public JFPrincipal() throws HeadlessException {
	Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/invoice.png"));
        this.setIconImage(icon);
        //carateristicas de la ventana
        this.setTitle("Facturación");
        this.setSize(565, 540);
        this.setLayout(null);
        this.setDefaultCloseOperation(1);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.cerrarVentana();
	//Creación de componentes
        this.crearBarraMenu();
        this.crearTitulo();
        this.crearCuerpo();
        this.crearNumeracion();
	this.setVisible(true);
    }
    
    /**crearTitulo
     * Crea el panel contenedor del titulo y lo ubica en la parte superior de la
     * ventana principal.
     */
    public void crearTitulo() {
	this.titulo = new JPTitulo();
        this.titulo.setBounds(0, 25, 565, 50);
        this.add(titulo);
    }
    
    /**crearCuerpo
     * Crea el panel contenedor del cuerpo y lo ubica en la parte central de la ventana.
     */
    public void crearCuerpo() {
	this.cuerpo = new JPCuerpo();
        this.cuerpo.setBounds(0, 75, 560, 460);
        this.add(cuerpo);
    }
    
    /**cerrarVentana
     * Esta clase cierra la ventana.
     */
    private void cerrarVentana(){
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent evt){
            try {
                ConexionDB db = new ConexionDB();
                db.cerrarDB();
            } catch (InterruptedException ex) {
                Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
            
        }
        });
        
    }
    
    /**crearBarraMenu
     * Se cre la barra de menu con las opciones
     */
    private void crearBarraMenu(){
       this.barraMenu = new JMenuBar();
       this.barraMenu.setBounds(0, 0, 565, 25);
       this.crearMenuCrear();
       this.crearMenuActualizar();
       this.crearMenuModificar();
       this.crearMenuManual();
       //barraMenu.add();
       add(barraMenu);
    }
    
    /**CrearMenuCrear
     * se crea el menu crear
     */
    @SuppressWarnings("Convert2Lambda")
    private void crearMenuCrear(){
       ImageIcon Icrear = new ImageIcon(getClass().getClass().getResource("/iconos/crear.png"));
       crear = new JMenu();
       crear.setIcon(Icrear);
       barraMenu.add(crear);  
    }
    
    /**CrearMenuActualizar
     * se crea el menu actualizar
     */
    private void crearMenuActualizar(){
       ImageIcon IActualizar = new ImageIcon(getClass().getClass().getResource("/iconos/actualizar.png"));
       actualizar = new JMenu();
       actualizar.setIcon(IActualizar);
       actualizar.setFocusable(true);
       this.barraMenu.add(actualizar);  
    }
    
    /**CrearMenuModificar
     * se crea el menu modificar
     */
    private void crearMenuModificar(){
       ImageIcon IModificar = new ImageIcon(getClass().getClass().getResource("/iconos/modificar.png"));
       modificar = new JMenu();
       modificar.setIcon(IModificar);
       modificar.setFocusable(true);
       this.barraMenu.add(modificar);  
    }
    
    /**CrearMenuManual
     * se crea el menu Manual
     */
    private void crearNumeracion(){
       ImageIcon IManual = new ImageIcon(getClass().getClass().getResource("/iconos/manual.png"));
       numeracion = new JMenu();
       numeracion.setIcon(IManual);
       numeracion.setFocusable(true);
       this.barraMenu.add(numeracion);  
    }
    
    /**CrearMenuManual
     * se crea el menu Manual
     */
    private void crearMenuManual(){
       ImageIcon IManual = new ImageIcon(getClass().getClass().getResource("/iconos/p.png"));
       manual = new JMenu();
       manual.setIcon(IManual);
       manual.setFocusable(true);
       this.barraMenu.add(manual);  
    }
    
    /**getCuerpo
     * retorna Cuerpo
     * @return Cuerpo
     */
    public JPCuerpo getCuerpo() {
        return cuerpo;
    }
    
    /**getBarraMenu
     * Se obtiene la JMenu.
     * @return 
     */
    public JMenuBar getBarraMenu() {
        return barraMenu;
    }
    
    /**getCrear
     * Obtiene la obcion crear del menú.
     * @return crear
     */
    public JMenuItem getCrear() {
        return crear;
    }
    
    /**getActualizar
     * Obtiene la obcion actualizar del menú.
     * @return actualizar
     */
    public JMenuItem getActualizar() {
        return actualizar;
    }
    
    /**getModificar
     * Obtiene la obcion modificar del menú.
     * @return modificar
     */
    public JMenuItem getModificar() {
        return modificar;
    }
    
    /**getManual
 Obtiene la obcion numeracion del menú.
     * @return numeracion
     */
    public JMenuItem getNumeracion() {
        return numeracion;
    }
    
    /**getManual
       Obtiene la obcion numeracion del menú.
     * @return numeracion
     */
    public JMenuItem getManual() {
        return manual;
    }
    
}

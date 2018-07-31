/**
 * 
 */
package factura;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.SQLException;
import logica.Controlador;

/**MainFactura
 * Clase principal del proyecto de facturacion
 * @author Roberto Garcia
 */
public class MainFactura {
    
   

    /**MainFactura
     * Clase principal del Proyecto de facturaci�n electronica
     * @param args
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws java.sql.SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
	try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       @SuppressWarnings("unused")
        Controlador con;
        con = new Controlador();

    }

}

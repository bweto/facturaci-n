/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**Conexion
 *Clase Conexion
 * @author Roberto Garcia
 */
public class Conexion {

    /**Conexion
     * Constructor de la clase para obtener información desde el archivo txt.
     */
    public Conexion(){
 
    }
    
    /**almacenarDI
     * Almacena información en el txt de datos de cliente.
     * @param informacionCliente 
     * @param dir 
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public void almacenar(String informacionCliente, String dir){
        try{
            //"src/informacion/db.txt"
            File archivo = new File(dir);
            FileWriter escribir = new FileWriter(archivo,true);
            escribir.write("\n"+informacionCliente);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
     public void almacenarDI(String informacionCliente,String dir){
        try{
            RandomAccessFile archivo = new RandomAccessFile(dir,"rw");
            archivo.seek(0);
            archivo.writeBytes(informacionCliente);
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @SuppressWarnings("ConvertToTryWithResources")
     public void guardar(String informacionCliente,String di){
        try{
           RandomAccessFile archivo = new RandomAccessFile(di,"rw");
            archivo.seek(0);
            archivo.writeBytes(informacionCliente);
            archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    /**leer
     * Obtiene la información del archivo txt.
     * @param dir
     * @return 
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public String leer(String dir){
        String informacionTxt = "";
        @SuppressWarnings("UnusedAssignment")
        String texto          = "";
        
        try{
            InputStream s = getClass().getResourceAsStream(dir);
            FileReader lector = new FileReader(dir);
            //new InputStreamReader(s)
      
            BufferedReader contenido = new BufferedReader(lector);
            while((texto = contenido.readLine())!= null){
                informacionTxt += texto+"&";                
            }
            contenido.close();
        } catch (FileNotFoundException  ex) {
            JOptionPane.showMessageDialog(null,"Digite informacion de clientes",
                        "Mensaje Informativo",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return informacionTxt;
    }
    
}    
 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Roberto Garcia
 * 
 */
public class ConexionDB {
    
  private Connection  connection;
  private Statement   stmt;  
  
public ConexionDB() throws InterruptedException{ 
 conectar();
}

public void conectar(){
      try {    
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:.\\DB\\facturas;create=true");
            
            existe();
       
      } catch (ClassNotFoundException | SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }
   
}

private void existe(){
    
            Statement stmp;
      try {
          stmp = connection.createStatement();
          stmp.executeQuery("select * from clientes");
      } catch (SQLException ex) {
          String con = "create table clientes("
                    + "id numeric primary key, "
                    + "nombre varchar(80),"
                    + " nit varchar(20),"
                    + " direccion varchar(100)"
                    + ", telefono varchar(30))";
          crear_tablas(con);
           con = "create table iva(id numeric primary key, "
                    + "valor numeric)";
          crear_tablas(con);
          con = "insert into iva values (1,19)";
          crear_tablas(con);
          con = "create table facturas(id numeric primary key, valor numeric)";
          crear_tablas(con);
          con = "insert into facturas values(1,322)";
          crear_tablas(con);
          
          
}

}
  private void crear_tablas(String consulta){
      try {
          PreparedStatement pstm = connection.prepareStatement(consulta);
          pstm.execute();
          pstm.close();
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }
}
        

  @SuppressWarnings("ConvertToTryWithResources")
  public String selecionarRegistros(){
   String informacion = ""; 
      conectar();
      try {
          String query = "SELECT * FROM clientes";
          PreparedStatement pstm = connection.prepareStatement(query);
          ResultSet result = pstm.executeQuery();
          while(result.next()){
              informacion += result.getString("nombre")+";"+
                             result.getString("nit")+";"+
                             result.getString("direccion")+";"+
                             result.getString("telefono")+"&";
           
        //System.out.println("Conexión exitosa!!");
        //connection.close();
          }
          result.close();
      } catch (SQLException ex) {
          
         
      }
   return informacion;
}

public int selecionarIva(){
   int iva = 0;
       conectar();
    try { 
          String query = "SELECT * FROM iva";
          PreparedStatement pstm = connection.prepareStatement(query);
          ResultSet result = pstm.executeQuery();
          while(result.next()){
          iva= Integer.parseInt(result.getString("valor"));
          }
          result.close();
          pstm.close();
      } catch (SQLException ex) {
          
          
      }
    return iva;
}

public int selecionarFactura(){
 int Nfactu = 0;
       conectar();
    try { 
          String query = "SELECT * FROM facturas";
          PreparedStatement pstm = connection.prepareStatement(query);
          ResultSet result = pstm.executeQuery();
          while(result.next()){
          Nfactu= Integer.parseInt(result.getString("valor"));
          }
          result.close();
          pstm.close();
      } catch (SQLException ex) {
          
        
      }
    return Nfactu;   
}

public void crearRegistro(int id, String nombre, String nit, String direccion, String telefono){
    conectar(); 
    try {
           PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes VALUES (?,?,?,?,?)");
           ps.setInt(1, id);
           ps.setString(2, nombre);
           ps.setString(3, nit);
           ps.setString(4, direccion);
           ps.setString(5, telefono); 
           ps.execute(); 
           ps.close();       
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }     
}

public void actualizarRegistro(int id, String nombre, String nit, String direccion, String telefono){
    conectar(); 
    //System.out.println("Referencia de posicion: "+id);
    try {
           PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET nombre = ?,"
                   + "nit = ?, direccion = ?, telefono = ? WHERE id = ?" );
           ps.setString(1, nombre);
           ps.setString(2, nit);
           ps.setString(3, telefono);
           ps.setString(4, direccion); 
           ps.setInt(5, id);
           ps.execute();
           ps.close();
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }       
}

public void actualizarIva(int val){
 conectar(); 
    try {
           PreparedStatement ps = connection.prepareStatement("UPDATE iva SET valor = ?"
                   + "WHERE id = ?" );
           ps.setInt(1, val);
           ps.setInt(2,1);
           ps.execute();
           ps.close();
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }     
}

public void actualizarMenu(int num){
   conectar(); 
    try {
           PreparedStatement ps = connection.prepareStatement("UPDATE facturas SET valor = ?"
                   + "WHERE id = ?" );
           ps.setInt(1,num);
           ps.setInt(2,1);
           ps.execute();
           ps.close();
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }    
}

public void cerrarDB(){
      try {  
          connection.close();
      } catch (SQLException ex) {
          Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
      }
}

}
                        
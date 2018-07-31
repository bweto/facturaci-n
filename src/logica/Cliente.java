/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**Cliente
 *Esta clase almacen la información de un cliente.
 * @author Roberto Garcia;
 */
public class Cliente {
    
    @SuppressWarnings("FieldMayBeFinal")
    private String nombre;
    @SuppressWarnings("FieldMayBeFinal")
    private String nit;
    @SuppressWarnings("FieldMayBeFinal")
    private String telefono;
    @SuppressWarnings("FieldMayBeFinal")
    private String direccion;
    
    
    /**Cliente
     * Constructor basico
     */
    public Cliente() {
        
       this.nombre     = "";
       this.nit        = "";
       this.telefono  = "";
       this.direccion  = "";
    }
    
    public Cliente(String nom, String ni, String tel, String dir){
       this.nombre     = nom;
       this.nit        = ni;
       this.telefono   = tel;
       this.direccion  = dir; 
        
    }
    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}

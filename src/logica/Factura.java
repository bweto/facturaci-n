/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;

/**Factura
 *Contiene la información de las facturas.
 * @author Roberto Garcia.
 */
public class Factura {
    
    /**Atributos
     * Cualidades de la clase Factura
     */
    @SuppressWarnings("FieldMayBeFinal")
    private String       dia_E;
    private String       mes_E;
    private String       año_E;
    private String       dia_V;
    private String       mes_V;
    private String       año_V;
    @SuppressWarnings("FieldMayBeFinal")
    private String       fechaV;
    @SuppressWarnings("FieldMayBeFinal")
    private String       cliente;
    @SuppressWarnings("FieldMayBeFinal")
    private String       nit;
    @SuppressWarnings("FieldMayBeFinal")
    private String       direccion; 
    @SuppressWarnings("FieldMayBeFinal")
    private String       telefono;
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> conceptos;
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> cantiad;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Double> valores;
    @SuppressWarnings("FieldMayBeFinal")
    private double       subtotal;
    @SuppressWarnings("FieldMayBeFinal")
    private double       iva;
    @SuppressWarnings("FieldMayBeFinal")
    private double       total;
    @SuppressWarnings("FieldMayBeFinal")
    private String       valorTxt;
    @SuppressWarnings("FieldMayBeFinal")
    private double       numf;
    
    /**constructor basi de clase
     * inicializa las variables de la clase
     */
    public Factura() {
        this.numf      = 0;
        this.dia_E     = "";
        this.mes_E     = "";
        this.año_E     = "";
        this.dia_V     = "";
        this.mes_V     = "";
        this.año_V     = "";
        this.cliente   = "";
        this.nit       = "";
        this.direccion = "";
        this.telefono  = "";
        this.conceptos = new ArrayList<>();
        this.cantiad   = new ArrayList<>();
        this.valores   = new ArrayList<>();
        this.subtotal  = 0;
        this.iva       = 0;
        this.total     = 0;
        this.valorTxt  = "";
    }

  

    public String getCliente() {
        return cliente;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<String> getConceptos() {
        return conceptos;
    }

    public List<String> getCantiad() {
        return cantiad;
    }

    public List<Double> getValores() {
        return valores;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public String getValorTxt() {
        return valorTxt;
    }

    public double getNumf() {
        return numf;
    }
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setConceptos(List<String> conceptos) {
        this.conceptos = conceptos;
    }

    public void setCantiad(List<String> cantiad) {
        this.cantiad = cantiad;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setValorTxt(String valorTxt) {
        this.valorTxt = valorTxt;
    }

    public void setNumf(double numf) {
        this.numf = numf;
    }

    public String getDia_E() {
        return dia_E;
    }

    public void setDia_E(String dia_E) {
        this.dia_E = dia_E;
    }

    public String getMes_E() {
        return mes_E;
    }

    public void setMes_E(String mes_E) {
        this.mes_E = mes_E;
    }

    public String getAño_E() {
        return año_E;
    }

    public void setAño_E(String año_E) {
        this.año_E = año_E;
    }

    public String getDia_V() {
        return dia_V;
    }

    public void setDia_V(String dia_V) {
        this.dia_V = dia_V;
    }

    public String getMes_V() {
        return mes_V;
    }

    public void setMes_V(String mes_V) {
        this.mes_V = mes_V;
    }

    public String getAño_V() {
        return año_V;
    }

    public void setAño_V(String año_V) {
        this.año_V = año_V;
    }

    public String getFechaV() {
        return fechaV;
    }

    public void setFechaV(String fechaV) {
        this.fechaV = fechaV;
    }
    
    
    
}

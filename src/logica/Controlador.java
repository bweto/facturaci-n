
package logica;

import com.itextpdf.text.DocumentException;
import interfazGrafica.JButtonRadius;
import interfazGrafica.JDVistaInfoCLientes;
import interfazGrafica.JFIngresoDeClientes;
import interfazGrafica.JFPrincipal;
import interfazGrafica.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**Controlador
 *Esta clase se encarga de obtener la informacion de la factura y ejecutar la 
 * creación de la factura.
 * @author Roberto Garcia.
 */
public class Controlador {

    @SuppressWarnings("FieldMayBeFinal")
    private List<Cliente>       clientes;
    @SuppressWarnings("FieldMayBeFinal")
    private List<String>        nombres;
    //private JFPrincipal         ventana_principal;
    @SuppressWarnings("FieldMayBeFinal")
    private static int          iva   = 19;
    private static int          numeF = 1000;
    private double              factN = 0;
    @SuppressWarnings("FieldMayBeFinal")
    //private Conexion            conexion;
    private JFIngresoDeClientes ingreso;
    @SuppressWarnings("FieldMayBeFinal")
    private String              dir;
    private JDVistaInfoCLientes infoClientes;
    public static boolean estaActivaVerClientes;
    public static boolean estaActivaCrearClientes;
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "FieldMayBeFinal"})
    private List<Factura> facturas;
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Hashtable<String,String> valores;
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Hashtable<String,String> miles;
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Hashtable<String,String> cientos;
    private ConexionDB db;
    private Vista view;
    
    /**Controlador
     * Constructor de la clase controlador donde se inicia la conexion a la 
     * base de datos.
     */
    @SuppressWarnings({"CallToPrintStackTrace", "static-access"})
    public Controlador() throws InterruptedException{
            this.clientes                = new ArrayList<>();
            this.nombres                 = new ArrayList<>();
            //this.ventana_principal       = null;
            this.db                      = new ConexionDB();
            this.dir                     = "src/informacion/db.txt";
            this.estaActivaVerClientes   = true;
            this.estaActivaCrearClientes = true;
            //this.ventana_principal       = new JFPrincipal();
            this.facturas                = new ArrayList<>();
            this.view                    = new Vista();
            this.leerDatos();
            this.ventana();
            this.ejecutarMenuCrear();
            this.ejecutarMenuActualizar();
            this.ejecutarMenuModificar();
            this.ejecutarMenuNumeracion();
            this.cargarIva();
            this.cargarMenuF();
            this.crearpdf();
            this.llenarValores();
            this.ejecutarMenuManualPdf();
            this.validarFechaVencimiento();
    }
   
    /**ventana
     * Se genera la ventana y los proceso de visualización e interacción con el cliente.
     */
    private void ventana(){
        this.llenar();
        this.asignarValores();
        this.calcularIVA();
        this.calcularCantidades();
    }
   
    /**llenar
     * Llena la información del jcombo box
     */
    private void llenar(){
      //JComboBox ite = ventana_principal.getCuerpo().getJCBCliente();
      JComboBox ite = view.getjCBNombreCliente();
       for(int i =0;i<nombres.size();i++){
            ite.insertItemAt(nombres.get(i),i);
       }  
    }
    
    /**asignarInfoComboBox
     * Asigna valores al combo box
     */
    @SuppressWarnings("UnusedAssignment")
    private void asignarInfoComboBox(){
        //JComboBox ite = ventana_principal.getCuerpo().getJCBCliente();
        JComboBox ite = view.getjCBNombreCliente();
        clientes.clear();
        nombres.clear();
        leerDatos();
        ite.removeAllItems();
        llenar();
        ite.invalidate();
        ite.repaint();
    }
  
    /**asignarValores
     * ASigna los valores del cliente selecionado a los campos de información.
     */
    @SuppressWarnings("Convert2Lambda")
    private void asignarValores(){
        //JComboBox iterador =ventana_principal.getCuerpo().getJCBCliente();
        JComboBox iterador = view.getjCBNombreCliente();
        iterador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   try{ 
                   @SuppressWarnings("UnusedAssignment")
                    int valorSelecionado = -1;
                    iterador.repaint();
                    iterador.invalidate();
                    valorSelecionado = iterador.getSelectedIndex();
                    Cliente clienteSelecionado = clientes.get(valorSelecionado);
                    view.getjLcontentNit().setText(clienteSelecionado.getNit());
                    view.getjLcontentTelefono().setText(clienteSelecionado.getTelefono());
                    view.getjLcontentDireccion().setText(clienteSelecionado.getDireccion());
                   }
                   catch(ArrayIndexOutOfBoundsException eb){
                       
                   }
                }
        });
    }
    
    /**calcularIVA
     * Suma los valores en el campo del campo valores y los suma para hallar el
     * valor del IVA.
     */
    @SuppressWarnings("Convert2Lambda")
    private void calcularIVA() {
        //ventana_principal.getCuerpo().getJTAValores().addCaretListener(new CaretListener()
        view.getjTXAValores().addCaretListener(new CaretListener() {
         @Override
         public void caretUpdate(CaretEvent e){
            double neto = 0;
            
//            String[] valoresEntrantes  = ventana_principal.getCuerpo().getJTAValores().getText().split("\n");
//            String[] cantidades        = ventana_principal.getCuerpo().getJTACantidades().getText().split("\n");
              String[] valoresEntrantes  = view.getjTXAValores().getText().split("\n");
              String[] cantidades        = view.getjTXACantidad().getText().split("\n");
             double valorIva = 0;//(view.getjCheck1().isSelected()) ? neto*iva*0.01:0;  
             double total    = 0;
             double val = 0;
            for(int i = 0;i<valoresEntrantes.length;i++){
                try{
                    if(cantidades.length == 0){
                    val = Double.parseDouble(valoresEntrantes[i]);
                    neto+= val;
                    valorIva = ((view.getjCheck1().isSelected()) && (Double.parseDouble(valoresEntrantes[0]) == val )) ? val*iva*0.01:0;
                    if(valoresEntrantes.length > 2){
                    valorIva+= ((view.getjCheck2().isSelected()) && (Double.parseDouble(valoresEntrantes[1]) == val )) ? val*iva*0.01:0;
                    }
                    }
                else{
                try{
                    neto+= (Double.parseDouble(valoresEntrantes[i]))*(Double.parseDouble(cantidades[i]));
                     valorIva = ((view.getjCheck1().isSelected()) && (Double.parseDouble(valoresEntrantes[0]) == val )) ? val*iva*0.01:0;
                    if(valoresEntrantes.length > 2){
                    valorIva+= ((view.getjCheck2().isSelected()) && (Double.parseDouble(valoresEntrantes[1]) == val )) ? val*iva*0.01:0;
                    }
                }catch(ArrayIndexOutOfBoundsException tr){
                    neto+= Double.parseDouble(valoresEntrantes[i]);
                     valorIva = ((view.getjCheck1().isSelected()) && (Double.parseDouble(valoresEntrantes[0]) == val )) ? val*iva*0.01:0;
                    if(valoresEntrantes.length > 2){
                    valorIva+= ((view.getjCheck2().isSelected()) && (Double.parseDouble(valoresEntrantes[1]) == val )) ? val*iva*0.01:0;
                    }
                }       
                    }
                }
                catch (NumberFormatException ex){
                   //calcularIVA();
                }
                //cierra for
                }
          
//            double valorIva = (ventana_principal.getCuerpo().getJChBIva().isSelected()) ? neto*iva*0.01:0;  
//            double total    = valorIva+neto;
//            ventana_principal.getCuerpo().getJTFIVA().setText(String.valueOf(valorIva));
//            ventana_principal.getCuerpo().getJTFNeto().setText(String.valueOf(neto));
//            ventana_principal.getCuerpo().getJTFTotal().setText(String.valueOf(total)); 
           // double valorIva = neto*iva*0.01;  
            total    = valorIva+neto;
            view.getjLcontentValorIva().setText(String.valueOf(valorIva));
            view.getjLcontentValorNeto().setText(String.valueOf(neto));
            view.getjLcontentValorTotal().setText(String.valueOf(total));
         }
     });         
    }
    
    
    
    /**calcularCantidades
     * calcula con respecto a la cantidad.
     */
    @SuppressWarnings("Convert2Lambda")
    private void calcularCantidades(){
        view.getjTXACantidad().addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
               double neto = 0;
               double valorIva = 0;
               double total =  0;
               double val = 0;
               double valIva = iva*0.01;
               double iterador = 0;
               int contador = 1;
               String[] valoresCantidades = view.getjTXACantidad().getText().split("\n");
               String[] valoresEntrantes  = view.getjTXAValores().getText().split("\n");
               for( int z = 0;z<valoresCantidades.length;z++){
                   try{
                       iterador = Double.parseDouble(valoresEntrantes[z])*Double.parseDouble(valoresCantidades[z]);
                       neto+= iterador;
                       switch(contador){
                           case 1:{
                               val = Double.parseDouble(valoresEntrantes[0])*Double.parseDouble(valoresCantidades[0]);
                               valorIva += (view.getjCheck1().isSelected()) ?  val * valIva:0;
                               break;
                           }
                           case 2:{
                               val = Double.parseDouble(valoresEntrantes[1]) * Double.parseDouble(valoresCantidades[1]);
                               valorIva += (view.getjCheck2().isSelected()) ? val * valIva:0;
                               break;
                           }
                           default: {
                               
                           }
                       }
                           contador++;            
                    }
                    catch (NumberFormatException |ArrayIndexOutOfBoundsException ex){
                       //calcularIVA();
                    }
                  
                }
               
            total    = valorIva+neto;
            view.getjLcontentValorIva().setText(darFormatoDec(String.valueOf(valorIva)));
            view.getjLcontentValorNeto().setText(darFormatoDec(String.valueOf(neto)));
            view.getjLcontentValorTotal().setText(darFormatoDec(String.valueOf(total)));
         
            }             
            });
    }
    
    /**leerDatos
     * Lee los datos almacenados en el txt
     */
    private void leerDatos() {
       //String informacionEntrante    = conexion.leer(dir);
        String informacionEntrante     = db.selecionarRegistros();
        //System.out.println(informacionEntrante);
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        String informacionPorLineas[] = informacionEntrante.split("&");
        for (String informacionPorLinea : informacionPorLineas) {
            this.estraerInformacionLinea(informacionPorLinea);
        }
    }
    
    /**estraerInformacionLinea
     * Obtiene los datos para crear un objeto de tipo cliente.
     * @param iteracion 
     */
    private void estraerInformacionLinea(String iteracion) {
         try{   
            @SuppressWarnings("MismatchedReadAndWriteOfArray") 
            String datosCliente[] = iteracion.split(";") ;
            Cliente leido = new Cliente();
            leido.setNombre(datosCliente[0]);
            leido.setNit(datosCliente[1]);
            leido.setTelefono(datosCliente[2]);
            leido.setDireccion(datosCliente[3]);
            clientes.add(leido);
            nombres.add(datosCliente[0]);
         }
         catch(ArrayIndexOutOfBoundsException e){  
         }
    }
    
    /**ejecutarMenuCrear
     * Ejecuta la accion al oprimir el boton crear del menu.
     */
    private void ejecutarMenuCrear(){
//        JMenuItem iterador = ventana_principal.getCrear();
        JButtonRadius iterador = view.getBtnCreate();
        iterador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {} 
            @Override
            public void mousePressed(MouseEvent e) {
               if(estaActivaCrearClientes){
               JFIngresoDeClientes.seleccion = 0; 
               vistaIngresarCliente();
               guardarCliente(ingreso);
               cerrarVentanaIngreso(ingreso);
               estaActivaCrearClientes = false;
               }
                else{
                   JOptionPane.showMessageDialog(null, "La ventana se encuentra"
                   + " activa", "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
                   ingreso.setAlwaysOnTop(true);
               }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        }  );
    }
    
    /**guardarCliente
     * Se almacena un nuevo cliente
     */
    @SuppressWarnings({"Convert2Lambda", "InfiniteRecursion"})
    private void guardarCliente(JFIngresoDeClientes in){
        try{
        in.getPanelInicial().getjBGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource().equals(in.getPanelInicial().getjBGuardar())){
                     JFIngresoDeClientes.seleccion = 1;
                     validarCampos(in);
                 }
            }
        });
        }
        catch(NullPointerException ex){
            //guardarCliente(in);
        }
    }
    
    /**cerrarVentanaIngreso
     * Cierra la ventana de ingreso de informacion del cliente
     */
    private void cerrarVentanaIngreso(JFIngresoDeClientes in){
        in.addWindowListener(new WindowAdapter() { 
        @Override
        public void windowClosing(WindowEvent evt){
           cerrar(in); 
        }
        });
    }
    
    /**cerrar
     * Condicion de cierre del JDialog
     * @param in 
     */
    private void cerrar(JFIngresoDeClientes in){
        if(JFIngresoDeClientes.seleccion == 0){
                if (JOptionPane.showConfirmDialog(null, "¿Desea salir sin guardar?",
                "Salir ", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
                     in.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
            else if(JFIngresoDeClientes.seleccion == 1){
                in.dispose();
                //this.leerDatos();
                this.asignarInfoComboBox();  
            }
    };
    
    /**guardarDatos
     * Se almacenan los datos del cliente nuevo en el archivo txt.
     */
    private void guardarDatos(JFIngresoDeClientes in){
       String informacionCliente =  in.crearStringDatos();
      // conexion.almacenar(informacionCliente,dir);
       int id = clientes.size()+1;
       db.crearRegistro(id, in.getPanelInicial().getjTFNombreCliente().getText(), 
               in.getPanelInicial().getjTFNitCliente().getText(),
               in.getPanelInicial().getjTFTelefonoCliente().getText(), 
               in.getPanelInicial().getjTFDireccionCliente().getText());
       asignarInfoComboBox();
       JOptionPane.showMessageDialog(null, "Se agrego un nuevo cliente",
               "Mensaje informativo",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**validarCampos
     * valida si los campos estan diligencidoas al crear un cliente nuevo.
     */
    private void validarCampos(JFIngresoDeClientes in){
        String nom = in.getPanelInicial().getjTFNombreCliente().getText();
        String nit = in.getPanelInicial().getjTFNitCliente().getText();
        String tel = in.getPanelInicial().getjTFTelefonoCliente().getText();
        String dire = in.getPanelInicial().getjTFDireccionCliente().getText();
        if(nom.isEmpty()||nit.isEmpty()||tel.isEmpty()||dire.isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe diligenciar todos los "
                    + "campos", "Mensaje informativo", JOptionPane.ERROR_MESSAGE);
            in.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        }
        else{
            guardarDatos(in);
        }
    } 
    
    /**ejecutarMenuActualizar
     * Ejecuta la ccion al oprimir el boton Actualizar del menu.
     */
    private void ejecutarMenuActualizar(){
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        String dir = "src/informacion/iva.txt";
//        JMenuItem iterador = ventana_principal.getActualizar();
        JButtonRadius iterador = view.getBtnIva();
        iterador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {} 
            @Override
            public void mousePressed(MouseEvent e) {
              String val = JOptionPane.showInputDialog(null, "EL valor del IVA actual es "
                      + iva ,"digite valores numericos");
              try{
                  int iv = Integer.parseInt(val);
                  //conexion.almacenarDI(val,dir);
                  db.actualizarIva(iv);
                  cargarIva();
              }
              catch(NumberFormatException | NullPointerException ex){
                  JOptionPane.showMessageDialog(null,"no ingreso un valor valido",
                          "Mensaje informativo",JOptionPane.ERROR_MESSAGE );
              }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        }  );
    }
    
    /**cargarIva
     * Inicializa wl valor del iva.
     */
    private void cargarIva(){
//        String dirIva = "/informacion/iva.txt";
//        String[] valIva = conexion.leer(dirIva).split("&");
       int i = db.selecionarIva();
        try{
        iva = i;
        }
        catch(NumberFormatException d){
            JOptionPane.showMessageDialog(null, "Digite un valor para el calculo del iva", 
            "Mensaje Informativo", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    
     /**cargarMenuF
     * Inicializa wl valor del numeracion de la factura.
     */
    private void cargarMenuF(){
//        String dirmenuF = "/informacion/menuf.txt";
//        String[] valIva = conexion.leer(dirmenuF).split("&");
        try{
        numeF =  db.selecionarFactura();
        }catch(NumberFormatException pl){
         
        }
    }
    
    /**ejecutarMenuModificar
     * Ejecuta la acción del menu modificar
     */
    private void ejecutarMenuModificar(){
        
//        JMenuItem iterador = ventana_principal.getModificar();
        JButtonRadius iterador = view.getBtnEdit();
        iterador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {} 
            @Override
            public void mousePressed(MouseEvent e) {
               if(estaActivaVerClientes){
                infoClientes = new JDVistaInfoCLientes(clientes);   
                vistaClientes();
                estaActivaVerClientes = false;
               }
                else{
                   JOptionPane.showMessageDialog(null, "La ventana se encuentra "
                   + "activa", "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
                infoClientes.setAlwaysOnTop(true);   
               }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        }  );
    }
    
    /**vistaIngresarCliente
     * Inicializa el JDialog para ingresar información del cliente
     */
    @SuppressWarnings("static-access")
    private void vistaIngresarCliente(){       
        ingreso = new JFIngresoDeClientes();
    }
    
    /**vistaClientes
     * Abre la ventana de visualización de los clientes.
     */
    @SuppressWarnings("static-access")
    private void vistaClientes(){
     this.corregir(infoClientes);
    }
    
    /**ejecutarMenuManual
     * Ejecuta las acciones del boton menu manual.
     */
    private void ejecutarMenuNumeracion(){
      
         //String dir = "src/informacion/numf.txt";
//         JMenuItem iterador = ventana_principal.getNumeracion();
         JButtonRadius iterador = view.getBtnConsecutivo();
         iterador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {} 
            @Override
            public void mousePressed(MouseEvent e) {
              
              String val = JOptionPane.showInputDialog(null, "Número de factura actual: "
                      + numeF ,"Digite número de factura que desea");
              try{
                  numeF = Integer.parseInt(val);
                  //conexion.almacenarDI(val,dir);
                  db.actualizarMenu(numeF);
              }
              catch(NumberFormatException | NullPointerException ex){
                  JOptionPane.showMessageDialog(null,"no ingreso un valor valido",
                          "Mensaje informativo",JOptionPane.ERROR_MESSAGE );
              }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        }  );
    }
    
    /**activarCampos
     * Activa los campos de información y crea un objeto cliente.
     */
//    private void activarCampos(){
//        ventana_principal.getCuerpo().getJCBCliente().setEditable(true);
//        ventana_principal.getCuerpo().getJTFNit().setEditable(true);
//        ventana_principal.getCuerpo().getJTFTelefono().setEditable(true);
//        ventana_principal.getCuerpo().getJTFDireccion().setEditable(true);
//            
//    }
    
    /**Corregir
     * Corrige y almacena los valores de la tabla y los almacena de nuevo. 
     */
    int fila =0;
    @SuppressWarnings("Convert2Lambda")
    private void corregir(JDVistaInfoCLientes ventana){
        
        ventana.getModificar().getTblClientes().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
          
             fila = ventana.getModificar().getTblClientes().getSelectedRow()+1;
             ventana.getModificar().getTblClientes().clearSelection();
             ventana.getModificar().getTblClientes().setFocusable(false);
             //ventana.getModificar().getTblClientes().editCellAt(0, 0);
             //acturalizarDB(fila,ventana.getModificar().getTblClientes());
             //System.out.println("Esta es la fila: "+fila);
             
            }
        });
        ventana.getJBCorregir().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(ventana.getJBCorregir())){
               convertirTabla(ventana.getModificar().getTblClientes(),fila);
                                                                                                
                                        }
           }
       });
    }
    
    public void acturalizarDB(int fila,JTable tbl){
        if(fila != -1){
            String nom, nit, tel, dire ="";
            nom = tbl.getValueAt(fila, 1).toString();
            nit = tbl.getValueAt(fila, 2).toString();
            tel = tbl.getValueAt(fila, 3).toString();
            dire = tbl.getValueAt(fila,4).toString();
            fila = fila-1;
            db.actualizarRegistro(fila, nom, nit, tel, dire);
        }
    }
    /**convertirTabla
    * Convierte los elementos de la tabla en objetos cliente y actualizar los objetos. en el txt
    * @param tbl 
    */
    private void convertirTabla(JTable tbl, int fila) {
        @SuppressWarnings("UnusedAssignment")
        String nombre    = "";
        @SuppressWarnings("UnusedAssignment")
        String nit       = "";
        @SuppressWarnings("UnusedAssignment")
        String telefono  = "";
        @SuppressWarnings("UnusedAssignment")
        String direccion = "";
        String ite       = "";
        String infoC    = "";
        List<Cliente> iterador = new ArrayList<>();
        List<String> itenom = new ArrayList<>();
        fila = fila-1;
        for(int i = 0; i<tbl.getRowCount();i++ ){
            for(int j = 0; j<tbl.getColumnCount();j++){
                String informacion = ""+tbl.getValueAt(i,j).toString();
                    if(!informacion.isEmpty()){
                       ite += tbl.getValueAt(i,j).toString()+";";
                    } 
            }
                    String[] values = ite.split(";");
             
                    try{
                    infoC += values[1]+";"+values[2]+";"+values[3]+";"+values[4]+"&";
                    
                    nombre          = values[1];
                    nit             = values[2];
                    telefono        = values[3];
                    direccion       = values[4];
                    Cliente nuevo   = new Cliente(nombre,nit,telefono,direccion);
                    iterador.add(nuevo);
                    ite ="";
                    itenom.add(values[1]);
                    
                    
                    if(i == fila){
                        fila=fila+1;
                     db.actualizarRegistro(fila, nombre, nit, direccion, telefono);
//                     JOptionPane.showMessageDialog(null, "Modificación exitosa"
//                        ,"Mensaje Informativo",JOptionPane.INFORMATION_MESSAGE);
                     }
                    }
                    catch(ArrayIndexOutOfBoundsException ei){
//                        JOptionPane.showMessageDialog(null,"No se pueden almacenar campos vacios.",
//                                 "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    
        }
                //conexion.guardar(infoC, dir);
                clientes = iterador;
                nombres  = itenom;
                asignarInfoComboBox();
            }
    
    /**crearPdf
     * Se crea el pdf de la factura.
     */
    private void crearpdf(){
//        this.ventana_principal.getCuerpo().getJBPdf()
        view.getBtnPdf().addActionListener((ActionEvent ae) -> {
            if(ae.getSource().equals(view.getBtnPdf())){
                try {
                    //ventana_principal.getCuerpo().getJTFTotal()
                    if(Double.parseDouble(darFormatoC(view.getjLcontentValorTotal().getText()))< 999999999){
                    ArchPdf nuevaFactura = new ArchPdf();
                    Factura nFactura = crearObjFactura();
                    numeF +=1;
                    nuevaFactura.crear_PDF(nFactura);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El total supera el valor soportado por el sofware",
                                "Mensaje informativo",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException | DocumentException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch(NumberFormatException |NullPointerException nb){
                    JOptionPane.showMessageDialog(null, "Lo campos no pueden tener espacios vacios", "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
                } 
                
            }
        });
    }
    
    /**crearObjFactura
     * Se crea un objeto de tipo factura.
     */
    private Factura crearObjFactura (){
        Factura nuevaF = new Factura();
        cargarfacT();
      
            db.actualizarMenu(numeF);
            nuevaF.setNumf(numeF);
//            nuevaF.setDia_E(""+ventana_principal.getCuerpo().getFecha_expedicion().getDate().getDate());
             nuevaF.setDia_E(""+view.getFecha_expedicion().getDate().getDate());
//            int m_e = ventana_principal.getCuerpo().getFecha_expedicion().getDate().getMonth()+1;
            int m_e = view.getFecha_expedicion().getDate().getMonth()+1;
            nuevaF.setMes_E(""+m_e);
//            int año_e = ventana_principal.getCuerpo().getFecha_expedicion().getDate().getYear()+1900;
            int año_e = view.getFecha_expedicion().getDate().getYear()+1900;
            nuevaF.setAño_E(""+año_e);
//            nuevaF.setDia_V(""+ventana_principal.getCuerpo().getFecha_vencimiento().getDate().getDate());
            nuevaF.setDia_V(""+view.getFecha_vencimiento().getDate().getDate());
//            int m_v = ventana_principal.getCuerpo().getFecha_vencimiento().getDate().getMonth()+1;
            int m_v = view.getFecha_vencimiento().getDate().getMonth()+1;
            nuevaF.setMes_V(""+m_v);
//            int año_v = ventana_principal.getCuerpo().getFecha_vencimiento().getDate().getYear()+1900;
            int año_v = view.getFecha_vencimiento().getDate().getYear()+1900;
            nuevaF.setAño_V(""+año_v);
//            String cln = ventana_principal.getCuerpo().getJCBCliente().getSelectedItem().toString();
            String cln = view.getjCBNombreCliente().getSelectedItem().toString();
            nuevaF.setCliente(cln);
//            String nit = ventana_principal.getCuerpo().getJTFNit().getText();
            String nit = view.getjLcontentNit().getText();
            nuevaF.setNit(nit);
//            String direct = ventana_principal.getCuerpo().getJTFDireccion().getText();
            String direct = view.getjLcontentDireccion().getText();
            nuevaF.setDireccion(direct);
//            String tel = ventana_principal.getCuerpo().getJTFTelefono().getText();
            String tel = view.getjLcontentTelefono().getText();
            nuevaF.setTelefono(tel);
//            JTextArea cantidad = ventana_principal.getCuerpo().getJTACantidades();
            JTextArea cantidad = view.getjTXACantidad();
            List<String> cant = cargarListString(cantidad);
            nuevaF.setCantiad(cant);
//            JTextArea concepto = ventana_principal.getCuerpo().getJTAConcepto();
            JTextArea concepto = view.getjTXAConcepto();
            List<String> con = cargarListString(concepto);
            nuevaF.setConceptos(con);
//            JTextArea valores = ventana_principal.getCuerpo().getJTAValores();
            JTextArea valores = view.getjTXAValores();
            List<Double> val = cargarListDouble(valores);
            nuevaF.setValores(val);
//            String sub = ventana_principal.getCuerpo().getJTFNeto().getText();
            String sub = view.getjLcontentValorNeto().getText();
            nuevaF.setSubtotal(Double.parseDouble(darFormatoC(sub)));
//            String tot = ventana_principal.getCuerpo().getJTFTotal().getText();
            String tot = view.getjLcontentValorTotal().getText();
            nuevaF.setTotal(Double.parseDouble(darFormatoC(tot)));
//            String iv = ventana_principal.getCuerpo().getJTFIVA().getText();
            String iv = view.getjLcontentValorIva().getText();
            nuevaF.setIva(Double.parseDouble(darFormatoC(iv)));
 
            String valorStringTotal = tot;
            nuevaF.setValorTxt(evaluarCaracteres(darFormatoC(valorStringTotal)));
            facturas.add(nuevaF);
        
       
        return nuevaF;
    }
    
    /**cargarfacT
     * Se lee el valor de la factura actual
    */
    private void cargarfacT() {
//        String dirFactN = "src/informacion/factn.txt";
//        String[] valFactN = conexion.leer(dirFactN).split("&");
        factN = db.selecionarFactura();
    }
    
    /**cargarListString
     * Carga los valores en la lista.
     */
    private List<String> cargarListString(JTextArea jString){
        List<String> listChar = new ArrayList<>();
        String textE = jString.getText();
        String[] text = textE.split("\n");
        for(String txt: text){
            listChar.add(txt);
        }
        return listChar;
    } 
    
    /**cargarListDouble
     * Carga los valores en la lista.
     */
    private List<Double> cargarListDouble(JTextArea jString){
        List<Double> listChar = new ArrayList<>();
        String textE = jString.getText();
        String[] text = textE.split("\n");
        for(String num :text){
            listChar.add(Double.parseDouble(num));
        }
        return listChar;
    } 
    
    /**llenarValores
     * da valores iniciales al hash de valores
     */
    @SuppressWarnings({"Convert2Diamond", "UseOfObsoleteCollectionType"})
    private void llenarValores(){
        valores = new Hashtable<String,String>();
        valores.put("1","un");
        valores.put("2","dos");
        valores.put("3","tres");
        valores.put("4","cuatro");
        valores.put("5","cinco");
        valores.put("6","seis");
        valores.put("7","siete");
        valores.put("8","ocho");
        valores.put("9","nueve");
        valores.put("10","diez");
        valores.put("11","once");
        valores.put("12","doce");
        valores.put("13","trece");
        valores.put("14","catorce");
        valores.put("15","quince");
        valores.put("16","dieciseis");
        valores.put("17","diecisiete");
        valores.put("18","dieciocho");
        valores.put("19","diecinueve");
        valores.put("20","veinte");
        valores.put("30","treinta");
        valores.put("40","cuarenta");
        valores.put("50","cincuenta");
        valores.put("60","sesenta");
        valores.put("70","setenta");
        valores.put("80","ochenta");
        valores.put("90","noventa");
        valores.put("100","ciento");
        valores.put("200","docientos");
        valores.put("300","trecientos");
        valores.put("400","cuatrocientos");
        valores.put("500","quinientos");
        valores.put("600","seicientos");
        valores.put("700","setecientos");
        valores.put("800","ochocientos");
        valores.put("900","novecientos");
        
    }
    
    /**evaluarCaracteres
     * Recive un string y lo separa caracter a caracter
     */
    @SuppressWarnings("element-type-mismatch")
    private String evaluarCaracteres(String valorS){
        String vlString = "";
            int ite = valorS.length();
            switch(ite){
                case 9:{
                    vlString = casoNueve(valorS);
                    break;
                }
                case 8:{
                    vlString = casoOcho(valorS);
                    break;
                }
                case 7:{
                    vlString = casoSiete(valorS);
                    break;
                }
                case 6:{
                    vlString = casoSeis(valorS);
                    break;
                }
                case 5:{
                    vlString = casoCinco(valorS);
                    break;
                }
                case 4:{
                    vlString = casoCuatro(valorS);
                    break;
                }
                case 3:{
                    vlString = casoTres(valorS);
                    break;
                }
                case 2:{
                    vlString = casoDos(valorS);
                    break;
                }
                case 1:{
                    vlString = casoUno(valorS);
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog(null, "La suma supera $999.999.999 de pesos"
                            + "\nComuniquese con el desarrolador para contemplar un\n"
                            + "nuevo rango de valores", "Mensaje informativo",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        return vlString;
    }
    
    /**casoNueve
     * determina el valor en letras para cigras con la forma
     * ###.###.### cifras
     * @param vl
     * @return 
     */
    private String  casoNueve(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "", primeraParte = "",segundaParte = "",terceraParte ="";
        if((String.valueOf(vl.charAt(0)).equals("1"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))){
            primeraParte = " cien millones";
        }
        else{
            primeraParte = escribirCentesimas(vl.charAt(0)+""+vl.charAt(1)+""+vl.charAt(2))+" millones ";
        }
        if((String.valueOf(vl.charAt(3)).equals("0"))&&(String.valueOf(vl.charAt(4)).equals("0"))&&(String.valueOf(vl.charAt(5)).equals("0"))){
            segundaParte =" ";
        }
        else{
            segundaParte = escribirCentesimas(""+vl.charAt(3)+""+vl.charAt(4)+""+vl.charAt(5))+" mil ";
        }
        if((String.valueOf(vl.charAt(6)).equals("0"))&&(String.valueOf(vl.charAt(7)).equals("0"))&&(String.valueOf(vl.charAt(8)).equals("0"))){
            terceraParte =" pesos moneda corriente.";
        }
        else{
            terceraParte = escribirCentesimas(""+vl.charAt(6)+""+vl.charAt(7)+""+vl.charAt(8))+" pesos modeda corriente.";
        }
        if((String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))
          &&(String.valueOf(vl.charAt(3)).equals("0"))&&(String.valueOf(vl.charAt(4)).equals("0"))
          &&(String.valueOf(vl.charAt(5)).equals("0"))&&(String.valueOf(vl.charAt(6)).equals("0"))
          &&(String.valueOf(vl.charAt(7)).equals("0"))&&(String.valueOf(vl.charAt(8)).equals("0"))   
                ){
            terceraParte = " de pesos moneda corriente.";
            
        }
        rt = primeraParte+segundaParte+terceraParte;
        
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoOcho
     * determina el valor en letras para cigras con la forma
     * ##.###.### cifras
     * @param vl
     * @return 
     */
    private String casoOcho(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "",parteuno = "", parteDos = "",parteTres = "";
        parteuno = escribirDecimas(String.valueOf(vl.charAt(0))+String.valueOf(vl.charAt(1)))+" millones "; 
        parteDos = escribirCentesimas(String.valueOf(vl.charAt(2))+String.valueOf(vl.charAt(3))+String.valueOf(vl.charAt(4)))+" mil ";
        parteTres = escribirCentesimas(String.valueOf(vl.charAt(5))+String.valueOf(vl.charAt(6))+String.valueOf(vl.charAt(7)))
             +" pesos moneda corriente."; 
        
             rt = parteuno+parteDos+parteTres;
     return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoSiete
     * determina el valor en letras para cigras con la forma
     * #.###.### cifras
     * @param vl
     * @return 
     */
    private String casoSiete(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt="",parteuno = "",partedos = "",parteTres = "";
        if((String.valueOf(vl.charAt(0)).equals("1"))){
          parteuno = " un millon"; 
        }else{
        parteuno = escribirUnidades(String.valueOf(vl.charAt(0)))+" millones "; 
        }
        partedos = escribirCentesimas(String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2))+String.valueOf(vl.charAt(3)))+" mil ";
        parteTres = escribirCentesimas(String.valueOf(vl.charAt(4))+String.valueOf(vl.charAt(5))+String.valueOf(vl.charAt(6)))
           +" pesos moneda corriente."; 
        rt=parteuno+partedos+parteTres;
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoSeis
     * determina el valor en letras para cigras con la forma
     * ###.### cifras
     * @param vl
     * @return 
     */
    private String casoSeis(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "",parteuno="",partedos="";  
        parteuno = escribirCentesimas(String.valueOf(vl.charAt(0))+String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2)))+" mil ";
        partedos = escribirCentesimas(String.valueOf(vl.charAt(3))+String.valueOf(vl.charAt(4))+String.valueOf(vl.charAt(5)));  
        rt =parteuno+partedos+" pesos moneda corriente.";
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoCinco
     * determina el valor en letras para cigras con la forma
     * ##.### cifras
     * @param vl
     * @return 
     */
    private String casoCinco(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "",parteuno="",partedos="";
        parteuno = escribirDecimas(String.valueOf(vl.charAt(0))+String.valueOf(vl.charAt(1)))+" mil ";
        partedos = escribirCentesimas(String.valueOf(vl.charAt(2))+String.valueOf(vl.charAt(3))+String.valueOf(vl.charAt(4)))
             +" pesos moneda corriente."; 
        rt = parteuno+partedos;      
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoCuatro
     * determina el valor en letras para cigras con la forma
     * #.### cifras
     * @param vl
     * @return 
     */
    private String casoCuatro(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "",parteuno = "",partedos = "";
        if(String.valueOf(vl.charAt(0)).equals("1")){
            parteuno = " mi";
        }
        else{
        parteuno = escribirUnidades(String.valueOf(vl.charAt(0)))+" mil "; 
        }
        partedos = escribirCentesimas(String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2))+String.valueOf(vl.charAt(3)))
             +" pesos moneda corriente.";
        rt = parteuno+partedos;         
        return rt;
    }
    
    /**casoTres
     * determina el valor en letras para cigras con la forma
     * ### cifras
     * @param vl
     * @return 
     */
    private String casoTres(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "";
        rt = escribirCentesimas(String.valueOf(vl.charAt(0))+String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2)))
             +" pesos moneda corriente.";
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoDos
     * determina el valor en letras para cigras con la forma
     * ## cifras
     * @param vl
     * @return 
     */
    private String casoDos(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "";
        if(!String.valueOf(vl.charAt(0)).equals("0")){
        String val = valores.get(String.valueOf(vl.charAt(0))+String.valueOf(vl.charAt(1)));
        rt = val+" pesos moneda corriente.";    
        } 
        else{
        rt = valores.get(String.valueOf(vl.charAt(1)))+" pesos moneda corriente.";
        }   
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**casoUno
     * determina el valor en letras para cigras con la forma
     * # cifras
     * @param vl
     * @return 
     */
    private String casoUno(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt="";
        rt = escribirUnidades(String.valueOf(vl.charAt(0)))
             +" pesos moneda corriente.";
        return rt.substring(0, 1).toUpperCase() + rt.substring(1); 
    }
    
    /**escribirCentesimas
     *Escribe el formato ###
     * @param vl
     * @return 
     */
    @SuppressWarnings({"element-type-mismatch", "UnusedAssignment"})
    private String escribirCentesimas(String vl){
        @SuppressWarnings("UnusedAssignment")
        String rt = "",c = "",d = "",ite = "";
      if((String.valueOf(vl.charAt(0)).equals("1"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))){
          c = "cien ";
          rt=c;
        }  
      else if((!String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))
              &&(!String.valueOf(vl.charAt(0)).equals("1"))){
            ite =vl.charAt(0)+""+"00";
            c = valores.get(ite);
            rt=c;
        }
      else if((!String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(!String.valueOf(vl.charAt(2)).equals("0"))){
          ite =vl.charAt(0)+""+"00";
          c = valores.get(ite)+" ";
          d = valores.get(String.valueOf(vl.charAt(2)));
          rt =c+" "+d;
      }
      else  if((!String.valueOf(vl.charAt(0)).equals("0"))&&(!String.valueOf(vl.charAt(1)).equals("0"))&&(!String.valueOf(vl.charAt(2)).equals("0"))){
                ite =String.valueOf(vl.charAt(0))+"00";
                c = valores.get(ite);
                ite = String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2));
                d = escribirDecimas(ite);
                rt = c+" "+d;
            } 
      else if((String.valueOf(vl.charAt(0)).equals("0"))&&(!String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))){
              ite = String.valueOf(vl.charAt(1))+"0";
              d = valores.get(ite);
              rt=d;
              }
      else if((String.valueOf(vl.charAt(0)).equals("0"))&&(!String.valueOf(vl.charAt(1)).equals("0"))&&(!String.valueOf(vl.charAt(2)).equals("0"))){
            ite = String.valueOf(vl.charAt(1))+String.valueOf(vl.charAt(2));
              d = valores.get(ite);
              rt=d;
        }
       else if((String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))){
           rt = " ";
       }
       else if((!String.valueOf(vl.charAt(0)).equals("0"))&&(!String.valueOf(vl.charAt(1)).equals("0"))&&(String.valueOf(vl.charAt(2)).equals("0"))){
            ite = String.valueOf(vl.charAt(0))+"00";
            c = valores.get(ite);
            ite = valores.get(String.valueOf(vl.charAt(1))+"0");
            d = ite;
            rt = c+" "+d; 
        }
       else if((String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0"))&&(!String.valueOf(vl.charAt(2)).equals("0"))){
           d = valores.get(String.valueOf(vl.charAt(2)));
           rt = d;
       }
       else if((String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("2"))&&(!String.valueOf(vl.charAt(2)).equals("0"))){
           d = "veiti"+valores.get(String.valueOf(vl.charAt(2)));
           rt = d;        
       }
          else{
               System.out.println("opcion no conteplada en centesimas\n");
            }
            
        return rt;
    }
    
    /**escribirDecimas
     * Escribe el formato de la forma ##
     * @param vl
     * @return 
     */
    @SuppressWarnings({"element-type-mismatch", "UnusedAssignment"})
    private String escribirDecimas(String vl){
        @SuppressWarnings("UnusedAssignment")
                String rt = " ";
        String d=" ";
        @SuppressWarnings("UnusedAssignment")
        String ite = " ";  
        if((!String.valueOf(vl.charAt(0)).equals("0"))&&(!String.valueOf(vl.charAt(1)).equals("0"))
           &&(!String.valueOf(vl.charAt(0)).equals("1"))&&(!String.valueOf(vl.charAt(0)).equals("2"))   
                ){
                ite = valores.get(String.valueOf(vl.charAt(0))+"0");
                d = ite +" y ";
                ite = valores.get(String.valueOf(vl.charAt(1)));
                d += ite; 
                rt=d;
            }
            else if((!String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0")))
            {
                ite = String.valueOf(vl.charAt(1))+"0";
                d = valores.get(ite);
                rt=d;
            }
            else if((String.valueOf(vl.charAt(0)).equals("2"))&&(!String.valueOf(vl.charAt(1)).equals("0"))){
                ite = String.valueOf(vl.charAt(1));
                d = "veinti"+valores.get(ite);
                rt=d;
            }
            else if((String.valueOf(vl.charAt(0)).equals("1"))){
                ite = String.valueOf(String.valueOf(vl.charAt(0)))+String.valueOf(vl.charAt(1));
                d = valores.get(ite);
                rt=d;
            }
            else if((String.valueOf(vl.charAt(0)).equals("0"))&&(String.valueOf(vl.charAt(1)).equals("0"))){
                rt=" ";
            }       
            else{
                 System.out.println("Caso no contemplado en decimas");
                 
            }
            
        return rt;
    }
    
    /**escribirUnidades
     * Escribe el formato de la forma #
     * @param vl
     * @return 
     */
    private String escribirUnidades(String vl){
        String rt ="";
        String ite = "";
        ite = String.valueOf(vl.charAt(0));
        rt = valores.get(ite);
        return rt;
    }
    
    /**darFormatoDec
     Se da el formato numerico
     ###,###,###
     */
    private String darFormatoDec(String val){
        String rt = "";
        double valor = Double.parseDouble(val);
        DecimalFormat dec = new DecimalFormat("#,###,###,###");
        rt = ""+dec.format(valor);
        return rt;
    }
    
    /**darFormatoC
    Se da el formato numerico
    #########
     */
    private String darFormatoC(String val){
        String rt = "";
        String valor[] = val.split(",");
        for(String n: valor){
            rt +=n;
        }
        return rt;
    }
    
    /**ejecutarMenuManualPdF
     * Ejecuta la ccion al oprimir el boton Actualizar del menu.
     */
    private void ejecutarMenuManualPdf(){
//        JMenuItem iterador = ventana_principal.getManual();
        JButtonRadius iterador = view.getBtnPdfEstructura();
        iterador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {} 
            @Override
            public void mousePressed(MouseEvent e) {
             try {
                    ArchPdf_1 nuevaFactura = new ArchPdf_1();
                    Factura nFactura = crearObjFactura();
                    nuevaFactura.crear_PDF(nFactura);
                } catch (IOException | DocumentException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                    catch(NumberFormatException |NullPointerException nb){
                    JOptionPane.showMessageDialog(null, "Lo campos no pueden tener espacios vacios", "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);
                }   
              
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        }  );
    }
    
    /**validarFechaVencimiento
     * Valida que la fecha de vencimiento se mayor a la fecha de inicio
     */
    private void validarFechaVencimiento(){
        
        Calendar fecSis = Calendar.getInstance();
        int dia = fecSis.get(Calendar.DATE);
        int mes = fecSis.get(Calendar.MONTH);
        int año = fecSis.get(Calendar.YEAR);
        int añoV = año-1900; 
        Date date_ini = new Date(añoV,mes,dia );
        //ventana_principal.getCuerpo().getFecha_expedicion().setDate(date_ini);
        view.getFecha_expedicion().setDate(date_ini);
        fecSis.add(Calendar.DATE,15);
        int diaVen = fecSis.get(Calendar.DATE);
        int mesVen = fecSis.get(Calendar.MONTH);
        int añoVen = fecSis.get(Calendar.YEAR);
        int añoOk = añoVen-1900;
        Date date_fin = new Date(añoOk,mesVen,diaVen);
//        ventana_principal.getCuerpo().getFecha_vencimiento().setDate(date_fin);   
        view.getFecha_vencimiento().setDate(date_fin);
    }

    }


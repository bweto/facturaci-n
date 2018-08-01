package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**JPCuerpo
 * Contiene los elementos que forman parte del cuerpo de la ventana principal.
 * @author Roberto Garcia
 *
 */
@SuppressWarnings("serial")
public class JPCuerpo extends JPanel {
    
    private JDateChooser fecha_expedicion;
    private JDateChooser fecha_vencimiento;
    private JLabel       JLFecha_expedicion;
    private JLabel       JLFecha_vencimiento;
    private JLabel       JLCliente;
    private JLabel       JLNit;
    private JLabel       JLTelefono;
    private JLabel       JLDireccion;
    private JLabel       JLComplemento;
    private JLabel       JLValores;
    private JLabel       JLIva;
    private JLabel       JLTotal;
    private JLabel       JLNeto;
    private JLabel       JLCantidad;
    private JComboBox    JCBCliente;
    private JTextField   JTFNit;
    private JTextField   JTFTelefono;
    private JTextField   JTFDireccion;
    private JTextField   JTFIVA;
    private JTextField   JTFTotal;
    private JTextField   JTFNeto;
    private JTextArea    JTAConcepto;
    private JTextArea    JTAValores;
    private JTextArea    JTACantidades;
    private JCheckBox    JChBIva;
    private JButton      JBPdf;
    @SuppressWarnings("FieldMayBeFinal")
    public static Font   fuente;
    
    /**JPCuerpo
     * Constructor de la clase cuerpo
     */
    @SuppressWarnings("static-access")
    public JPCuerpo(){ 
        this.fuente = new Font("Arial",3,12);
        this.setBackground(Color.WHITE);
        this.setSize(565, 465);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.crearJLFechaExpedicion();
        this.crearFechaExpedicion();
        this.crearJLFechaVencimiento();
        this.crearFechaVencimiento();
        this.crearJLCliente();
        this.crearJCBCliente();
        this.crearJLNit();
        this.crearJTFNit();
        this.crearJLTelefono();
        this.crearJTFTelefono();
        this.crearJLDireccion();
        this.crearJTFDireccion();
        this.crearJLConcepto();
        this.crearJTAConcepto();
        this.crearJLValores();
        this.crearJTAValores();
        this.crearJLCantidad();
        this.crearJTACantidades();
        this.crearJChBIva();
        this.crearJLIva();
        this.crearJTFIva();
        this.crearJLNeto();
        this.crearJTFNeto();
        this.crearJLTotal();
        this.crearJTFTotal();
        this.crearJBPdf();
    }
    
    /**crearJLFechaExpedicion
     * Se utiliza para crear y dar caracteristicas al label.
     */
    public void crearJLFechaExpedicion() {
	 this.JLFecha_expedicion = new JLabel("Fecha Expedición:");
	 this.JLFecha_expedicion.setFont(fuente);
         this.JLFecha_expedicion.setBounds(10, 5, 115, 20);
	 add(JLFecha_expedicion);
    }
    
    /**crearFechaExpedicion
     * Se utiliza para crear y dar caracteristicas al label.
     */
    public void crearFechaExpedicion() {
	 this.fecha_expedicion = new JDateChooser();
         this.fecha_expedicion.setBounds(130, 5, 100, 20);
	 add(fecha_expedicion);
    }
    
    /**crearJLFechaVencimiento
    * Se utiliza para crear y dar caracteristicas al label.
    */
   public void crearJLFechaVencimiento() {
	 this.JLFecha_vencimiento = new JLabel("Fecha Vencimiento: ");
	 this.JLFecha_vencimiento.setFont(fuente);
	 this.JLFecha_vencimiento.setBounds(320, 5, 120, 20);
	 add(JLFecha_vencimiento);
   }
   
    /**crearFechaExpedicion
     * Se utiliza para crear y dar caracteristicas al label.
     */
    public void crearFechaVencimiento() {
	 this.fecha_vencimiento = new JDateChooser();
         this.fecha_vencimiento.setBounds(450, 5, 100, 20);
	 add(fecha_vencimiento);
    }
    
   /**crearJLCliente<
    * Se utiliza para crear y dar caracteristicas al label.
    */
   public void crearJLCliente() {
	 this.JLCliente = new JLabel("Cliente: ");
	 this.JLCliente.setFont(fuente);
         this.JLCliente.setBounds(10, 35, 60, 20);
	 add(JLCliente);
   }
   
   /**crearJCBCliente
    * Se utiliza para crear y dar caracteristicas al JComboBox.
    */
   public void crearJCBCliente() {
	 this.JCBCliente = new JComboBox<>();
         this.JCBCliente.setBounds(75, 35, 475, 20);
	 this.JCBCliente.setFont(new Font("Arial",3,12));
         this.JCBCliente.setEditable(false);
	 add(JCBCliente);
   }
   
   /**crearJLNit
    * Se utiliza para crear y dar caracteristicas al label.
    */
   public void crearJLNit() {
	 this.JLNit = new JLabel("NIT: ");
	 this.JLNit.setFont(fuente);
	 this.JLNit.setBounds(10, 65, 60, 20);
	 add(JLNit);
   }
   
   /**crearJTFNit
    * Se utiliza para crear y dar caracteristicas al JTextField.
    */
   private void crearJTFNit() {
	this.JTFNit = new JTextField();
        this.JTFNit.setBounds(75, 65, 100, 20);
        this.JTFNit.setEditable(false);
        add(JTFNit);
   }
   
   /**crearJLTelefono
    * Se utiliza para crear y dar caracteristicas al JLabel.
    */
    private void crearJLTelefono() {
	this.JLTelefono = new JLabel("Telefono: ");
        this.JLTelefono.setFont(fuente);
	this.JLTelefono.setBounds(390, 65, 80, 20);
        add(JLTelefono);
   }
    /**crearJTFTelefono
     * Se utiliza para crear y dar caracteristicas al JTextField.
     */
   private void crearJTFTelefono() {
	this.JTFTelefono = new JTextField();
	this.JTFTelefono.setBounds(450, 65, 100, 20);
        this.JTFTelefono.setEditable(false);
        add(JTFTelefono);
   }
   
   /**crearJLDireccion
    * SE utiliza para crear y dar caracteristicas al JLabel
    */
    private void crearJLDireccion() {
       this.JLDireccion = new JLabel("Dirección: ");
       this.JLDireccion.setFont(fuente);
       this.JLDireccion.setBounds(10, 95, 70, 20);
       add(JLDireccion);
   }
   
    /**crearJTFDireccion
     * Se utiliza para crear y dar caracteristicas al JTextField
     */
    private void crearJTFDireccion() {
	this.JTFDireccion = new JTextField();
        this.JTFDireccion.setBounds(75, 95, 475, 20);
        this.JTFDireccion.setEditable(false);
        add(JTFDireccion);
   }

    /**crearJLConcepto
     * Se utiliza para crear y dar carqacteristicas al JLabel.
     */
    private void crearJLConcepto() {
        this.JLComplemento = new JLabel("Concepto: ");
        this.JLComplemento.setFont(fuente);
        this.JLComplemento.setBounds(10, 125, 70, 20);
        add(JLComplemento);
    }
    
    /**crearJTAConcepto
     * Se utiliza para crear y dar caracteristicas al JTextField
     */
    private void crearJTAConcepto() {
        JScrollPane scroll;
	this.JTAConcepto = new JTextArea(3,1);
        this.JTAConcepto.setBounds(75, 125, 475, 80);
        this.JTAConcepto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
  
        scroll = new JScrollPane(JTAConcepto);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(75, 125, 475, 80);
        scroll.setVisible(true);
        add(scroll);
        
        
    }
    
    /**crearJLValores
     * Se utiliza para crear y dar caracteristicas al JLabel
     */
    private void crearJLValores() {
	this.JLValores = new JLabel("Valores: ");
        this.JLValores.setFont(fuente);
	this.JLValores.setBounds(10, 220, 70, 20);
        add(JLValores);
    }
    
    /**crearJTAValores
     * Se utiliza para crear y dar caracteristicas al JTextArea
     */
    private void crearJTAValores() {
        JScrollPane scrollV;
	this.JTAValores = new JTextArea(3, 1);
        this.JTAValores.setBounds(75, 220, 150, 80);
        this.JTAValores.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollV = new JScrollPane(JTAValores);
        scrollV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollV.setBounds(75, 220, 150, 80);
        scrollV.setVisible(true);
        this.JTAValores.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                 char letras = e.getKeyChar();
               if(Character.isLetter(letras)){
                   getToolkit().beep();
                   e.consume();
                   JOptionPane.showMessageDialog(null,"Este campo solo almacena valores numericos", "Ingrese informacion valida",JOptionPane.ERROR_MESSAGE);
               }   
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
	add(scrollV);
   }
   
   /**crearJLCantidad
     * Se utiliza para crear y dar caracteristicas al JLabel
     */
    private void crearJLCantidad() {
	this.JLCantidad = new JLabel("Cantidad: ");
        this.JLCantidad.setFont(fuente);
	this.JLCantidad.setBounds(245, 220, 75, 20);
        add(JLCantidad);
    }
    
    /**crearJTACantidades
     * Se utiliza para crear y dar caracteristicas al JTextArea
     */
    private void crearJTACantidades() {
        JScrollPane scrollC;
	this.JTACantidades = new JTextArea(3, 1);
        this.JTACantidades.setBounds(315, 220, 75, 80);
        this.JTACantidades.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollC = new JScrollPane(JTACantidades);
        scrollC.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollC.setBounds(315, 220, 75, 80);
        scrollC.setVisible(true);
        this.JTACantidades.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                 char letras = e.getKeyChar();
               if(Character.isLetter(letras)){
                   getToolkit().beep();
                   e.consume();
                   JOptionPane.showMessageDialog(null,"Este campo solo almacena valores numericos", "Ingrese informacion valida",JOptionPane.ERROR_MESSAGE);
               }   
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
	add(scrollC);
   } 
    
   
    /**crearJChBIva
     * Se utiliza para crear y dar caracteristicas al JCheckBox.
     */
    private void crearJChBIva() {
	this.JChBIva = new JCheckBox();
        this.JChBIva.setBounds(10, 310, 20, 20);
        this.JChBIva.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.JChBIva.setBackground(Color.WHITE);
        this.JChBIva.setSelected(true);
	add(JChBIva);
    }
    
   /**crearJLIva
    * Se utiliza para crear y dar caracteristicas al JLabel
    */
    private void crearJLIva() {
	this.JLIva = new JLabel("IVA: ");
        this.JLIva.setFont(fuente);
	this.JLIva.setBounds(40, 310, 50, 20);
        add(JLIva);
    }
    
    /**crearJTFIva
     * Se utiliza para crear y dar caracteristicas al JTextField
     */
    private void crearJTFIva() {
	this.JTFIVA = new JTextField();
        this.JTFIVA.setBounds(75, 310, 100, 20);
        this.JTFIVA.setEditable(false);
        add(JTFIVA);
   }
   
    /**crearJLTotal
    * Se utiliza para crear y dar caracteristicas al JLabel
    */
    private void crearJLTotal() {
	this.JLTotal = new JLabel("Total: ");
        this.JLTotal.setFont(fuente);
	this.JLTotal.setBounds(375, 310, 60, 20);
        add(JLTotal);
    }    
    
    /**crearJTFIva
     * Se utiliza para crear y dar caracteristicas al JTextField
     */
    private void crearJTFTotal() {
	this.JTFTotal = new JTextField();
        this.JTFTotal.setBounds(415, 310, 100, 20);
        this.JTFTotal.setEditable(false);
        add(JTFTotal);	
    }
    
    /**crearJLNeto
    * Se utiliza para crear y dar caracteristicas al JLabel
    */
    private void crearJLNeto() {
	this.JLNeto = new JLabel("Neto: ");
        this.JLNeto.setFont(fuente);
	this.JLNeto.setBounds(205, 310, 60, 20);
        add(JLNeto);
    }    
    
    /**crearJTFNeto
     * Se utiliza para crear y dar caracteristicas al JTextField
     */
    private void crearJTFNeto() {
	this.JTFNeto = new JTextField();
        this.JTFNeto.setBounds(245, 310, 100, 20);
        this.JTFNeto.setEditable(false);
        add(JTFNeto);	
    }
    
    /**crearJTFIva
     * Se utiliza para crear y dar caracteristicas al JButton
     */
     private void crearJBPdf() {
       ImageIcon icon = new ImageIcon(getClass().getClass().getResource("/iconos/pdf.png"));
       this.JBPdf = new JButtonRadius(icon, Color.white, new Color(255,204,0));
       this.JBPdf.setFocusPainted(false);
       this.JBPdf.setBounds(255, 340, 80, 80);
       add(JBPdf);	
    }
    
    /**crearFormateador
     * clea el formato numerico
     * @return 
     */
    
    
    public JDateChooser getFecha_expedicion() {
        return fecha_expedicion;
    }

    public JDateChooser getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public JComboBox getJCBCliente() {
        return JCBCliente;
    }

    public JTextField getJTFNit() {
        return JTFNit;
    }

    public JTextField getJTFTelefono() {
        return JTFTelefono;
    }

    public JTextField getJTFDireccion() {
        return JTFDireccion;
    }

    public JTextField getJTFIVA() {
        return JTFIVA;
    }

    public JTextField getJTFTotal() {
        return JTFTotal;
    }

    public JTextArea getJTAConcepto() {
        return JTAConcepto;
    }

    public JTextArea getJTAValores() {
        return JTAValores;
    }

    public JCheckBox getJChBIva() {
        return JChBIva;
    }

    public JButton getJBPdf() {
        return JBPdf;
    }

    public JTextField getJTFNeto() {
        return JTFNeto;
    }

    public JTextArea getJTACantidades() {
        return JTACantidades;
    }
    
    
    

 
}

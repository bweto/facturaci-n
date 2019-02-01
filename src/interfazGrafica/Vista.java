/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import com.itextpdf.text.Document;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.NavigationFilter;
import logica.CellRenderer;
import logica.HeaderCellRenderer;

/**
 *
 * @authorRoberto Garcia
 */

public class Vista extends JFrame {

    private JButtonRadius btnClose,
            btnCreate,
            btnEdit,
            btnIva,
            btnConsecutivo,
            btnPdfEstructura,
            btnPdf,
            btnMin;

    private JPanel jPAside,
            jPHeader,
            jPBody,
            jPInfoCliente,
            jPConcepto,
            jPValores,
            jPTotales,
            jpBackground;

    private JLabel jLTitle,
            jLlogo,
            jLDateInicio,
            jLDateEnd,
            jLNombreCliente,
            jLNit,
            jLcontentNit,
            jLTelefono,
            jLcontentTelefono,
            jLDireccion,
            jLcontentDireccion,
            jLValorIva,
            jLcontentValorIva,
            jLvalorNeto,
            jLcontentValorNeto,
            jLvalorTotal,
            jLcontentValorTotal,
            jLcontrol
            ;
    
    private JTable jTblContenido;
    private JScrollPane scroll;
    private JComboBox<String> jCBNombreCliente;

    private JTextArea jTXAConcepto,
            jTXAValores,
            jTXACantidad;

    private JDateChooser fecha_expedicion,
                         fecha_vencimiento;

    public static final int CHAT_ROW_LIMIT = 6;
   
    private Font fuente = new Font("Serif", 1, 15);

    private JScrollPane scrollConcepto,
            scrollValor,
            scrollCantidad;
    private JCheckBox jCheck1,
            jCheck2;
    
    private int x,y =0;
    
    public Vista() {
        setSize(800, 600);
        setTitle("Facturación");
        setUndecorated(true);
        //setLayout(null);
        //setEnabled(true);
        setLocationRelativeTo(null);
        setResizable(false);
       
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/invoice.png"));
        setIconImage(icon);
        jpBackground();
        add(jpBackground);
        setVisible(true);

    }
    
    private void jpBackground(){
        jpBackground = new JPanel();
 
        jpBackground.setSize(800, 600);
        crt_JPAside();
        crt_JPHeader();
        crt_JPBody();
        add(jPAside);
        add(jPHeader);
        add(jPBody);
    }
    
    private void crt_JPAside() {
        jPAside = new JPanel();
        jPAside.setLayout(null);
        jPAside.setBounds(0, 0, 150, 600);
        jPAside.setBackground(new Color(11, 44, 64));
        crt_jLogo();
        crt_btnCreate();
        crt_btnEdit();
        crt_btnIva();
        crt_btnContador();
        crt_btnPdfEstructura();
        add(jLlogo);
        add(btnCreate);
        add(btnEdit);
        add(btnIva);
        add(btnConsecutivo);
        add(btnPdfEstructura);
        jPAside.setVisible(true);
    }

    private void crt_btnClose() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Close.png"));
        btnClose = new JButtonRadius(i, new Color(22, 88, 127), new Color(33, 133, 191), 10, 10);
        btnClose.setBorderPainted(false);
        btnClose.setFont(fuente);
        btnClose.setBounds(615, 5, 30, 30);
        btnClose.setFocusable(false);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClose.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnClose)) {
                System.exit(1);
            }
        });

    }
    
    private void crt_btnMin() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Minimize.png"));
        btnMin = new JButtonRadius(i, new Color(22, 88, 127), new Color(33, 133, 191), 10, 10);
        btnMin.setBorderPainted(false);
        btnMin.setFont(fuente);
        btnMin.setBounds(580, 5, 30, 30);
        btnMin.setFocusable(false);
        btnMin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMin.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnMin)) {
                this.setExtendedState(1);
            }
        });

    }

    private void crt_JPHeader() {
        jPHeader = new JPanel();
        jPHeader.setLayout(null);
        jPHeader.setBounds(150, 0, 650, 100);
        jPHeader.setBackground(new Color(22, 88, 127));
        crt_btnMin();
        crt_btnClose();
        crt_jltitle();
        jPHeader.add(jLcontrol);
        jPHeader.add(jLTitle);
        jPHeader.add(btnClose);
        jPHeader.add(btnMin);
        jPHeader.setVisible(true);
    }

    private void crt_jLogo() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/logoDis.png"));
        jLlogo = new JLabel(i);
        jLlogo.setBounds(30, 15, 80, 100);
    }

    private void crt_btnCreate() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Create.png"));
        btnCreate = new JButtonRadius(i, new Color(11, 44, 64), new Color(11, 44, 64));
        btnCreate.setBorderPainted(false);
        btnCreate.setBounds(0, 190, 150, 20);
        btnCreate.setFocusable(false);
        btnCreate.setText("Crear");
        btnCreate.setFont(fuente);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCreate.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnCreate)) {

            }
        });

    }

    private void crt_btnEdit() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Editar.png"));
        btnEdit = new JButtonRadius(i, new Color(11, 44, 64), new Color(11, 44, 64));
        btnEdit.setBorderPainted(false);
        btnEdit.setBounds(0, 220, 150, 20);
        btnEdit.setFocusable(false);
        btnEdit.setText("Editar");
        btnEdit.setFont(fuente);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEdit.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnEdit)) {

            }
        });

    }

    private void crt_btnIva() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Iva.png"));
        btnIva = new JButtonRadius(i, new Color(11, 44, 64), new Color(11, 44, 64));
        btnIva.setBorderPainted(false);
        btnIva.setBounds(0, 250, 150, 20);
        btnIva.setFocusable(false);
        btnIva.setText("IVA");
        btnIva.setFont(fuente);
        btnIva.setForeground(Color.WHITE);
        btnIva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIva.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnIva)) {

            }
        });

    }

    private void crt_btnContador() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Contador.png"));
        btnConsecutivo = new JButtonRadius(i, new Color(11, 44, 64), new Color(11, 44, 64));
        btnConsecutivo.setBorderPainted(false);
        btnConsecutivo.setBounds(0, 280, 150, 20);
        btnConsecutivo.setFocusable(false);
        btnConsecutivo.setText("Consecutivo");
        btnConsecutivo.setFont(fuente);
        btnConsecutivo.setForeground(Color.WHITE);
        btnConsecutivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConsecutivo.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnConsecutivo)) {

            }
        });

    }

    private void crt_btnPdfEstructura() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/PdfEstructura.png"));
        btnPdfEstructura = new JButtonRadius(i, new Color(11, 44, 64), new Color(11, 44, 64));
        btnPdfEstructura.setBorderPainted(false);
        btnPdfEstructura.setBounds(0, 310, 150, 20);
        btnPdfEstructura.setFocusable(false);
        btnPdfEstructura.setText("Factura Fisica");
        btnPdfEstructura.setFont(fuente);
        btnPdfEstructura.setForeground(Color.WHITE);
        btnPdfEstructura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPdfEstructura.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnPdfEstructura)) {

            }
        });

    }

    private void crt_jltitle() {
        
        jLcontrol = new JLabel();
        jLcontrol.setBounds(0, 0,550, 100);
        jLcontrol.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        } );
        jLcontrol.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX()- x, getLocation().y + e.getY() - y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        } );
        jLTitle = new JLabel("Facturación");
        jLTitle.setForeground(Color.white);
        jLTitle.setFont(new Font("serif", 0, 40));
        jLTitle.setBounds(200, 30, 220, 50);
        
       
    }

    private void crt_JPBody() {
        jPBody = new JPanel();
        jPBody.setLayout(null);
        jPBody.setBounds(150, 100, 650, 500);
        jPBody.setBackground(new Color(33, 133, 191));
        crt_jLDateInicio();
        crt_jLDateEnd();
        crt_jpInfoCliente();
        crt_jpConcepto();
        crt_jpValores();
        crt_tblContent();
        add(jLDateInicio);
        add(fecha_expedicion);
        add(jLDateEnd);
        add(fecha_vencimiento);
        add(jPInfoCliente);
        add(jPConcepto);
        add(jPValores);
        //add(scroll);
         //add(jTblContenido);
        jPBody.setVisible(true);
    }
    
    private void crt_tblContent(){
       
       Object[] titulo = {"Conceptos","Cantidad","Valor","IVA"}; 
       DefaultTableModel model = new DefaultTableModel(titulo,10);
       
       jTblContenido= new JTable(model);
       jTblContenido.setBounds(180, 300, 595, 150);
       jTblContenido.setPreferredScrollableViewportSize(new Dimension(595,100));
       jTblContenido.getTableHeader().setReorderingAllowed(false);
       jTblContenido.setFillsViewportHeight(true);
       jTblContenido.setUpdateSelectionOnSort(false);
       jTblContenido.setEnabled(true);
       jTblContenido.setGridColor(Color.BLACK);
       jTblContenido.setShowHorizontalLines( true );
       jTblContenido.setColumnSelectionAllowed(true);
       jTblContenido.setSelectionForeground( Color.black );
       jTblContenido.setGridColor(new Color(0,191,255));
       jTblContenido.setDefaultRenderer (Object.class, new CellRenderer());
       jTblContenido.setSelectionBackground(new Color(0,191,255) );
       jTblContenido.setAutoResizeMode(1);
       jTblContenido.setSurrendersFocusOnKeystroke(false);
       jTblContenido.setColumnSelectionAllowed(false);
       jTblContenido.setRowHeight(25);
       jTblContenido.setRowSelectionAllowed(true);
       scroll = new JScrollPane(jTblContenido);
       jTblContenido.setShowVerticalLines(false);
       JTableHeader tblheader = jTblContenido.getTableHeader();
       tblheader.setDefaultRenderer(new HeaderCellRenderer());
       scroll.setVisible(true);
       scroll.setBounds(180, 300, 595, 150);
    }
    
    private void crt_jLDateInicio() {
        jLDateInicio = new JLabel("Fecha de Expedición");
        jLDateInicio.setForeground(Color.white);
        jLDateInicio.setBounds(180, 110, 150, 20);
        jLDateInicio.setFont(fuente);
        fecha_expedicion = new JDateChooser();
        fecha_expedicion.setBounds(180, 140, 150, 20);

    }

    private void crt_jLDateEnd() {
        jLDateEnd = new JLabel("Fecha de Vencimiento");
        jLDateEnd.setForeground(Color.white);
        jLDateEnd.setBounds(340, 110, 150, 20);
        jLDateEnd.setFont(fuente);
        fecha_vencimiento = new JDateChooser();
        fecha_vencimiento.setBounds(340, 140, 150, 20);
    }

    private void crt_jpInfoCliente() {
        jPInfoCliente = new JPanel();
        jPInfoCliente.setBounds(180, 170, 595, 120);
        jPInfoCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(11, 44, 64)), "Información Cliente", 0, 0, fuente, new Color(11, 44, 64)));
        jPInfoCliente.setBackground(new Color(33, 133, 191));
        crt_jLNombreCliente();
        crt_jLNit();
        crt_jLTelefono();
        crt_jLDireccion();
        add(jLNombreCliente);
        add(jCBNombreCliente);
        add(jLNit);
        add(jLcontentNit);
        add(jLTelefono);
        add(jLcontentTelefono);
        add(jLDireccion);
        add(jLcontentDireccion);
        jPInfoCliente.setVisible(true);
    }

    private void crt_jLNombreCliente() {
        jLNombreCliente = new JLabel("Cliente: ");
        jLNombreCliente.setForeground(Color.WHITE);
        jLNombreCliente.setBounds(190, 190, 65, 20);
        jLNombreCliente.setFont(fuente);
        jCBNombreCliente = new JComboBox<>();
        jCBNombreCliente.setFont(fuente);
        jCBNombreCliente.setForeground(Color.white);
        jCBNombreCliente.setBounds(250, 190, 505, 20);
        jCBNombreCliente.setBackground(new Color(33, 133, 191));
        jCBNombreCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jCBNombreCliente.setBorder(BorderFactory.createLineBorder(new Color(33, 133, 191)));
        jCBNombreCliente.setFocusable(false);
        jCBNombreCliente.setUI(JComboBoxColors.CreateUi(jCBNombreCliente));
        jCBNombreCliente.setVisible(true);

        //jCBNombreCliente.setEditor(new JComboBoxColors(new Color(33, 133, 191)));
        //jCBNombreCliente.setEditable(true);
        //jCBNombreCliente.repaint();
    }

    private void crt_jLNit() {
        jLNit = new JLabel("Nit: ");
        jLNit.setForeground(Color.white);
        jLNit.setBounds(190, 220, 30, 20);
        jLNit.setFont(fuente);
        jLcontentNit = new JLabel();
        jLcontentNit.setFont(fuente);
        jLcontentNit.setForeground(Color.white);
        jLcontentNit.setBounds(220, 220, 100, 20);
    }

    private void crt_jLTelefono() {
        jLTelefono = new JLabel("Telefono: ");
        jLTelefono.setForeground(Color.white);
        jLTelefono.setBounds(375, 220, 90, 20);
        jLTelefono.setFont(fuente);
        jLcontentTelefono = new JLabel();
        jLcontentTelefono.setFont(fuente);
        jLcontentTelefono.setForeground(Color.white);
        jLcontentTelefono.setBounds(445, 220, 120, 20);
    }

    private void crt_jLDireccion() {
        jLDireccion = new JLabel("Dirección: ");
        jLDireccion.setForeground(Color.white);
        jLDireccion.setBounds(190, 250, 90, 20);
        jLDireccion.setFont(fuente);
        jLcontentDireccion = new JLabel();
        jLcontentDireccion.setFont(fuente);
        jLcontentDireccion.setForeground(Color.white);
        jLcontentDireccion.setBounds(265, 250, 490, 20);
    }

    private void crt_jpConcepto() {
        jPConcepto = new JPanel();
        jPConcepto.setBounds(240, 300, 420, 120);
        jPConcepto.setFont(fuente);
        jPConcepto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(11, 44, 64)), "Concepto", 0, 0,
                fuente, new Color(11, 44, 64))
        );
        jPConcepto.setBackground(new Color(33, 133, 191));
        crt_jtxaConcepto();
        //add(scrollConcepto);
        jPConcepto.setVisible(true);
    }

    private void crt_jtxaConcepto() {
        
        jTXAConcepto = new JTextArea();
        jTXAConcepto.setBackground(new Color(33, 133, 191));
        jTXAConcepto.setForeground(Color.WHITE);
        jTXAConcepto.setFont(fuente);
        jTXAConcepto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXAConcepto.setBounds(260, 325, 380, 80);
        jTXAConcepto.setLineWrap(true);
        jTXAConcepto.setWrapStyleWord(true);
        //jTXAConcepto.addInputMethodListener(l);
        jTXAConcepto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (jTXAConcepto.getLineCount() > 9){
                    try{
                        destroy(jTXAConcepto.getLineCount());
                    }
                    catch(ArrayIndexOutOfBoundsException err){
                        JOptionPane.showMessageDialog(null,"Solo puede ingresar 9 lineas");
                        jTXAConcepto.setSelectionStart(1);
                    }
                    
                //updateLineCount(jTXAConcepto, jPConcepto);
               
            }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
               //updateLineCount(jTXAConcepto, jPConcepto);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //updateLineCount(jTXAConcepto, jPConcepto);
            }
            
            private void destroy(int p){
                jTXAConcepto.setSelectionStart(0);
                jTXAConcepto.remove(jTXAConcepto.getLineCount());
                JOptionPane.showMessageDialog(null, "se Alcanso el limite de lineas");
            }
        });
        
        //jTXAConcepto.getUI().getRootView(jTXAConcepto);
        scrollConcepto = new JScrollPane(jTXAConcepto);
        scrollConcepto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollConcepto.setBounds(250, 325, 400, 80);//750 325 20 80
        scrollConcepto.setBackground(new Color(33, 133, 191));
        scrollConcepto.setBorder(BorderFactory.createLineBorder(new Color(33, 133, 191)));
        //scrollConcepto.setVisible(true);
        this.add(scrollConcepto);

    }
    
    
    private void updateLineCount(JTextArea iterador, JPanel panelIterador){
        int lineCount = iterador.getLineCount();
        System.out.println(lineCount);
        if (lineCount <= CHAT_ROW_LIMIT && lineCount < 10) {
            iterador.setRows(lineCount);
            panelIterador.revalidate();
            jPBody.revalidate();
        }
    }

    private void crt_jpValores() {
        jPValores = new JPanel();
        jPValores.setBounds(180, 430, 595, 150);
        jPValores.setFont(fuente);
        jPValores.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(11, 44, 64)), "Valores", 0, 0,
                fuente, new Color(11, 44, 64))
        );
        jPValores.setBackground(new Color(33, 133, 191));
        crt_jtxaValores();
        crt_jtxCantidad();
        crt_jPTotales();
        crt_jBtnPDF();
        jCheck1 = new JCheckBox();
        jCheck1.setBounds(190, 480, 20, 20);
        jCheck1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jCheck1.setBackground(new Color(33, 133, 191));

        jCheck1.setSelected(true);
        jCheck2 = new JCheckBox();
        jCheck2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jCheck2.setBackground(new Color(33, 133, 191));
        jCheck2.setSelected(true);
        jCheck2.setBounds(190, 510, 20, 20);
        add(jCheck1);
        add(jCheck2);
        //add(jTXAValores);
        //add(jTXACantidad);
        add(jPTotales);
        add(btnPdf);
        jPValores.setVisible(true);
    }

    private void crt_jtxaValores() {
        JScrollPane scroll;
        jTXAValores = new JTextArea(3, 1);
        jTXAValores.setFont(fuente);
        jTXAValores.setBackground(new Color(33, 133, 191));
        jTXAValores.setForeground(Color.WHITE);
        jTXAValores.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(11, 44, 64)), "Valor hora", 0, 0,
                fuente, new Color(11, 44, 64))
        );
        jTXAValores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXAValores.setBounds(220, 460, 130, 90);
        jTXAValores.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (jTXAValores.getLineCount() > 9){
                    try{
                        destroy(jTXAValores.getLineCount());
                    }
                    catch(ArrayIndexOutOfBoundsException err){
                        JOptionPane.showMessageDialog(null,"Solo puede ingresar 9 lineas");
                        jTXAValores.setSelectionStart(1);
                    }
                    
                //updateLineCount(jTXAConcepto, jPConcepto);
               
            }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
               //updateLineCount(jTXAConcepto, jPConcepto);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //updateLineCount(jTXAConcepto, jPConcepto);
            }
            
            private void destroy(int p){
                jTXAValores.setSelectionStart(0);
                jTXAValores.remove(jTXAValores.getLineCount());
                JOptionPane.showMessageDialog(null, "se Alcanso el limite de lineas");
            }
        });
        
        scrollValor = new JScrollPane(jTXAValores);
        scrollValor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollValor.setBounds(220, 460, 130, 90);//320 460 20 90
        scrollValor.setBorder(BorderFactory.createLineBorder(new Color(33, 133, 191)));
        scrollValor.setVisible(true);
        this.add(scrollValor);
    }

    private void crt_jtxCantidad() {
        JScrollPane scroll;
        jTXACantidad = new JTextArea(3, 1);
        jTXACantidad.setBackground(new Color(33, 133, 191));
        jTXACantidad.setForeground(Color.WHITE);
        jTXACantidad.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(11, 44, 64)), "Cantidad", 0, 0,
                fuente, new Color(11, 44, 64))
        );
        jTXACantidad.setFont(fuente);
        jTXACantidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXACantidad.setBounds(360, 460, 100, 90);
        jTXACantidad.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (jTXACantidad.getLineCount() > 9){
                    try{
                        destroy(jTXACantidad.getLineCount());
                    }
                    catch(ArrayIndexOutOfBoundsException err){
                        JOptionPane.showMessageDialog(null,"Solo puede ingresar 9 lineas");
                        jTXACantidad.setSelectionStart(1);
                    }
                    
                //updateLineCount(jTXAConcepto, jPConcepto);
               
            }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
               //updateLineCount(jTXAConcepto, jPConcepto);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //updateLineCount(jTXAConcepto, jPConcepto);
            }
            
            private void destroy(int p){
                jTXACantidad.setSelectionStart(0);
                jTXACantidad.remove(jTXACantidad.getLineCount());
                JOptionPane.showMessageDialog(null, "se Alcanso el limite de lineas");
            }
        });
        
        
        scrollCantidad = new JScrollPane(jTXACantidad);
        scrollCantidad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCantidad.setBounds(360, 460, 100, 90);//450 460 20 90 
        scrollCantidad.setBorder(BorderFactory.createLineBorder(new Color(33, 133, 191)));
        scrollCantidad.setVisible(true);
        this.add(scrollCantidad);
    }

    private void crt_jPTotales() {
        jPTotales = new JPanel();
        jPTotales.setBounds(470, 460, 180, 90);
        jPTotales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(11, 44, 64)), "Totales", 0, 0,
                fuente, new Color(11, 44, 64))
        );
        jPTotales.setBackground(new Color(33, 133, 191));
        crt_jLValorIva();
        crt_jLValorneto();
        crt_jLValorTotal();
        add(jLValorIva);
        add(jLcontentValorIva);
        add(jLvalorNeto);
        add(jLcontentValorNeto);
        add(jLvalorTotal);
        add(jLcontentValorTotal);
        jPTotales.setVisible(true);
    }

    private void crt_jLValorIva() {
        jLValorIva = new JLabel("Iva: ");
        jLValorIva.setForeground(Color.white);
        jLValorIva.setBounds(490, 480, 50, 20);
        jLValorIva.setFont(fuente);
        jLcontentValorIva = new JLabel();
        jLcontentValorIva.setForeground(Color.white);
        jLcontentValorIva.setBounds(535, 480, 100, 20);
        jLcontentValorIva.setFont(fuente);
    }

    private void crt_jLValorneto() {
        jLvalorNeto = new JLabel("Neto: ");
        jLvalorNeto.setForeground(Color.white);
        jLvalorNeto.setBounds(490, 500, 50, 20);
        jLvalorNeto.setFont(fuente);
        jLcontentValorNeto = new JLabel();
        jLcontentValorNeto.setForeground(Color.white);
        jLcontentValorNeto.setBounds(535, 500, 100, 20);
        jLcontentValorNeto.setFont(fuente);
    }

    private void crt_jLValorTotal() {
        jLvalorTotal = new JLabel("Total: ");
        jLvalorTotal.setForeground(Color.white);
        jLvalorTotal.setBounds(490, 520, 50, 20);
        jLvalorTotal.setFont(fuente);
        jLcontentValorTotal = new JLabel();
        jLcontentValorTotal.setForeground(Color.white);
        jLcontentValorTotal.setBounds(535, 520, 100, 20);
        jLcontentValorTotal.setFont(fuente);
    }

    private void crt_jBtnPDF() {
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Pdf.png"));
        btnPdf = new JButtonRadius(i, new Color(33, 133, 191), new Color(33, 133, 191));
        btnPdf.setBorderPainted(false);
        btnPdf.setBounds(620, 450, 150, 80);
        btnPdf.setFocusable(false);
        btnPdf.setText("Imprimir");
        btnPdf.setForeground(Color.WHITE);
        btnPdf.setFont(fuente);
        btnPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        Vista v = new Vista();
//    }
    public JButtonRadius getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButtonRadius btnClose) {
        this.btnClose = btnClose;
    }

    public JButtonRadius getBtnCreate() {
        return btnCreate;
    }

    public void setBtnCreate(JButtonRadius btnCreate) {
        this.btnCreate = btnCreate;
    }

    public JButtonRadius getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButtonRadius btnEdit) {
        this.btnEdit = btnEdit;
    }

    public JButtonRadius getBtnIva() {
        return btnIva;
    }

    public void setBtnIva(JButtonRadius btnIva) {
        this.btnIva = btnIva;
    }

    public JButtonRadius getBtnConsecutivo() {
        return btnConsecutivo;
    }

    public void setBtnConsecutivo(JButtonRadius btnConsecutivo) {
        this.btnConsecutivo = btnConsecutivo;
    }

    public JButtonRadius getBtnPdfEstructura() {
        return btnPdfEstructura;
    }

    public void setBtnPdfEstructura(JButtonRadius btnPdfEstructura) {
        this.btnPdfEstructura = btnPdfEstructura;
    }

    public JButtonRadius getBtnPdf() {
        return btnPdf;
    }

    public void setBtnPdf(JButtonRadius btnPdf) {
        this.btnPdf = btnPdf;
    }

    public JPanel getjPAside() {
        return jPAside;
    }

    public void setjPAside(JPanel jPAside) {
        this.jPAside = jPAside;
    }

    public JPanel getjPHeader() {
        return jPHeader;
    }

    public void setjPHeader(JPanel jPHeader) {
        this.jPHeader = jPHeader;
    }

    public JPanel getjPBody() {
        return jPBody;
    }

    public void setjPBody(JPanel jPBody) {
        this.jPBody = jPBody;
    }

    public JPanel getjPInfoCliente() {
        return jPInfoCliente;
    }

    public void setjPInfoCliente(JPanel jPInfoCliente) {
        this.jPInfoCliente = jPInfoCliente;
    }

    public JPanel getjPConcepto() {
        return jPConcepto;
    }

    public void setjPConcepto(JPanel jPConcepto) {
        this.jPConcepto = jPConcepto;
    }

    public JPanel getjPValores() {
        return jPValores;
    }

    public void setjPValores(JPanel jPValores) {
        this.jPValores = jPValores;
    }

    public JPanel getjPTotales() {
        return jPTotales;
    }

    public void setjPTotales(JPanel jPTotales) {
        this.jPTotales = jPTotales;
    }

    public JLabel getjLTitle() {
        return jLTitle;
    }

    public void setjLTitle(JLabel jLTitle) {
        this.jLTitle = jLTitle;
    }

    public JLabel getjLDateInicio() {
        return jLDateInicio;
    }

    public void setjLDateInicio(JLabel jLDateInicio) {
        this.jLDateInicio = jLDateInicio;
    }

    public JLabel getjLDateEnd() {
        return jLDateEnd;
    }

    public void setjLDateEnd(JLabel jLDateEnd) {
        this.jLDateEnd = jLDateEnd;
    }

    public JLabel getjLNombreCliente() {
        return jLNombreCliente;
    }

    public void setjLNombreCliente(JLabel jLNombreCliente) {
        this.jLNombreCliente = jLNombreCliente;
    }

    public JLabel getjLNit() {
        return jLNit;
    }

    public void setjLNit(JLabel jLNit) {
        this.jLNit = jLNit;
    }

    public JLabel getjLcontentNit() {
        return jLcontentNit;
    }

    public void setjLcontentNit(JLabel jLcontentNit) {
        this.jLcontentNit = jLcontentNit;
    }

    public JLabel getjLTelefono() {
        return jLTelefono;
    }

    public void setjLTelefono(JLabel jLTelefono) {
        this.jLTelefono = jLTelefono;
    }

    public JLabel getjLcontentTelefono() {
        return jLcontentTelefono;
    }

    public void setjLcontentTelefono(JLabel jLcontentTelefono) {
        this.jLcontentTelefono = jLcontentTelefono;
    }

    public JLabel getjLDireccion() {
        return jLDireccion;
    }

    public void setjLDireccion(JLabel jLDireccion) {
        this.jLDireccion = jLDireccion;
    }

    public JLabel getjLcontentDireccion() {
        return jLcontentDireccion;
    }

    public void setjLcontentDireccion(JLabel jLcontentDireccion) {
        this.jLcontentDireccion = jLcontentDireccion;
    }

    public JLabel getjLValorIva() {
        return jLValorIva;
    }

    public void setjLValorIva(JLabel jLValorIva) {
        this.jLValorIva = jLValorIva;
    }

    public JLabel getjLcontentValorIva() {
        return jLcontentValorIva;
    }

    public void setjLcontentValorIva(JLabel jLcontentValorIva) {
        this.jLcontentValorIva = jLcontentValorIva;
    }

    public JLabel getjLvalorNeto() {
        return jLvalorNeto;
    }

    public void setjLvalorNeto(JLabel jLvalorNeto) {
        this.jLvalorNeto = jLvalorNeto;
    }

    public JLabel getjLcontentValorNeto() {
        return jLcontentValorNeto;
    }

    public void setjLcontentValorNeto(JLabel jLcontentValorNeto) {
        this.jLcontentValorNeto = jLcontentValorNeto;
    }

    public JLabel getjLvalorTotal() {
        return jLvalorTotal;
    }

    public void setjLvalorTotal(JLabel jLvalorTotal) {
        this.jLvalorTotal = jLvalorTotal;
    }

    public JLabel getjLcontentValorTotal() {
        return jLcontentValorTotal;
    }

    public void setjLcontentValorTotal(JLabel jLcontentValorTotal) {
        this.jLcontentValorTotal = jLcontentValorTotal;
    }

    public JComboBox<String> getjCBNombreCliente() {
        return jCBNombreCliente;
    }

    public void setjCBNombreCliente(JComboBox<String> jCBNombreCliente) {
        this.jCBNombreCliente = jCBNombreCliente;
    }

    public JTextArea getjTXAConcepto() {
        return jTXAConcepto;
    }

    public void setjTXAConcepto(JTextArea jTXAConcepto) {
        this.jTXAConcepto = jTXAConcepto;
    }

    public JTextArea getjTXAValores() {
        return jTXAValores;
    }

    public void setjTXAValores(JTextArea jTXAValores) {
        this.jTXAValores = jTXAValores;
    }

    public JTextArea getjTXACantidad() {
        return jTXACantidad;
    }

    public void setjTXACantidad(JTextArea jTXACantidad) {
        this.jTXACantidad = jTXACantidad;
    }

    public JDateChooser getFecha_expedicion() {
        return fecha_expedicion;
    }

    public void setFecha_expedicion(JDateChooser fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public JDateChooser getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(JDateChooser fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public JCheckBox getjCheck1() {
        return jCheck1;
    }

    public void setjCheck1(JCheckBox jCheck1) {
        this.jCheck1 = jCheck1;
    }

    public JCheckBox getjCheck2() {
        return jCheck2;
    }

    public void setjCheck2(JCheckBox jCheck2) {
        this.jCheck2 = jCheck2;
    }

    public JButtonRadius getBtnMin() {
        return btnMin;
    }

    public void setBtnMin(JButtonRadius btnMin) {
        this.btnMin = btnMin;
    }

}

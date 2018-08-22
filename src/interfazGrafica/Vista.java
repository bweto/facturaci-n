/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.ComboBoxUI;

/**
 *
 * @author DisToshiba
 */
public class Vista extends JFrame {

    private JButtonRadius btnClose,
                          btnCreate,
                          btnEdit,
                          btnIva,
                          btnConsecutivo,
                          btnPdfEstructura,
                          btnPdf;

    private JPanel jPAside,
                   jPHeader,
                   jPBody,
                   jPInfoCliente,
                   jPConcepto,
                   jPValores,
                   jPTotales;

    private JLabel  jLTitle,
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
                    jLcontentValorTotal;

    private JComboBox<String> jCBNombreCliente;

    private JTextArea jTXAConcepto,
                      jTXAValores,
                      jTXACantidad;
    
    private JDateChooser fecha_expedicion,
                         fecha_vencimiento;
    
    private Font fuente = new Font("Serif", 1, 15);
    
    public Vista() {
        setSize(800, 600);
        setTitle("Facturación");
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/invoice.png"));
        setIconImage(icon);
        crt_JPAside();
        crt_JPHeader();
        crt_JPBody();
        add(jPAside);
        add(jPHeader);
        add(jPBody);
        setVisible(true);

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

    private void crt_JPHeader() {
        jPHeader = new JPanel();
        jPHeader.setLayout(null);
        jPHeader.setBounds(150, 0, 650, 100);
        jPHeader.setBackground(new Color(22, 88, 127));
        crt_btnClose();
        crt_jltitle();
        jPHeader.add(jLTitle);
        jPHeader.add(btnClose);
        jPHeader.setVisible(true);
    }
    
    private void crt_jLogo(){
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
        add(jLDateInicio);
        add(fecha_expedicion);
        add(jLDateEnd);
        add(fecha_vencimiento);
        add(jPInfoCliente);
        add(jPConcepto);
        add(jPValores);
        jPBody.setVisible(true);
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
        jPInfoCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Información Cliente", 0, 0, new Font("Serif", 1, 12), Color.white));
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
        jLNombreCliente.setBounds(190, 190, 50, 20);
        jLNombreCliente.setFont(fuente);
        jCBNombreCliente = new JComboBox<>();
        jCBNombreCliente.setFont(fuente);
        jCBNombreCliente.setForeground(Color.white);
        jCBNombreCliente.setBounds(245, 190, 500, 20);
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
        jLNit.setBounds(190, 220, 20, 20);
        jLNit.setFont(fuente);
        jLcontentNit = new JLabel();
        jLcontentNit.setFont(fuente);
        jLcontentNit.setForeground(Color.white);
        jLcontentNit.setBounds(215, 220, 100, 20);
    }

    private void crt_jLTelefono() {
        jLTelefono = new JLabel("Telefono: ");
        jLTelefono.setForeground(Color.white);
        jLTelefono.setBounds(375, 220, 50, 20);
        jLTelefono.setFont(fuente);
        jLcontentTelefono = new JLabel();
        jLcontentTelefono.setFont(fuente);
        jLcontentTelefono.setForeground(Color.white);
        jLcontentTelefono.setBounds(430, 220, 100, 20);
    }

    private void crt_jLDireccion() {
        jLDireccion = new JLabel("Dirección: ");
        jLDireccion.setForeground(Color.white);
        jLDireccion.setBounds(190, 250, 50, 20);
        jLDireccion.setFont(fuente);
        jLcontentDireccion = new JLabel();
        jLcontentDireccion.setFont(fuente);
        jLcontentDireccion.setForeground(Color.white);
        jLcontentDireccion.setBounds(245, 250, 500, 20);
    }

    private void crt_jpConcepto() {
        jPConcepto = new JPanel();
        jPConcepto.setBounds(180, 300, 595, 120);
        jPConcepto.setFont(fuente);
        jPConcepto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), "Concepto", 0, 0,
                new Font("Serif", 1, 12), Color.white)
        );
        jPConcepto.setBackground(new Color(33, 133, 191));
        crt_jtxaConcepto();
        add(jTXAConcepto);
        jPConcepto.setVisible(true);
    }

    private void crt_jtxaConcepto() {
        JScrollPane scroll;
        jTXAConcepto = new JTextArea(3, 1);
        jTXAConcepto.setBackground(new Color(33, 133, 191));
        jTXAConcepto.setForeground(Color.WHITE);
        jTXAConcepto.setFont(fuente);
        jTXAConcepto.setBorder(BorderFactory.createLineBorder(new Color(33, 133, 191)));
        jTXAConcepto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXAConcepto.setBounds(190, 325, 560, 80);

        scroll = new JScrollPane(jTXAConcepto);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(750, 325, 15, 80);
        scroll.setVisible(true);
        add(scroll);
    }

    private void crt_jpValores() {
        jPValores = new JPanel();
        jPValores.setBounds(180, 430, 595, 150);
        jPValores.setFont(fuente);
        jPValores.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), "Valores", 0, 0,
                new Font("Serif", 1, 12), Color.white)
        );
        jPValores.setBackground(new Color(33, 133, 191));
        crt_jtxaValores();
        crt_jtxCantidad();
        crt_jPTotales();
        crt_jBtnPDF();
        add(jTXAValores);
        add(jTXACantidad);
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
                BorderFactory.createLineBorder(Color.WHITE), "Valor hora", 0, 0,
                new Font("Serif", 1, 15), Color.white)
        );
        jTXAValores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXAValores.setBounds(190, 460, 100, 90);

        scroll = new JScrollPane(jTXAValores);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(290, 460, 15, 90);
        scroll.setVisible(true);
        add(scroll);
    }

    private void crt_jtxCantidad() {
        JScrollPane scroll;
        jTXACantidad = new JTextArea(3, 1);
        jTXACantidad.setBackground(new Color(33, 133, 191));
        jTXACantidad.setForeground(Color.WHITE);
        jTXACantidad.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), "Cantidad", 0, 0,
                new Font("Serif", 1, 15), Color.white)
        );
        jTXACantidad.setFont(fuente);
        jTXACantidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jTXACantidad.setBounds(320, 460, 100, 90);

        scroll = new JScrollPane(jTXACantidad);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(420, 460, 15, 90);
        scroll.setVisible(true);
        add(scroll);
    }

    private void crt_jPTotales() {
        jPTotales = new JPanel();
        jPTotales.setBounds(450, 460, 180, 90);
        jPTotales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), "Totales", 0, 0,
                new Font("Serif", 1, 12), Color.white)
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
        jLValorIva.setBounds(460, 480, 50, 20);
        jLValorIva.setFont(fuente);
        jLcontentValorIva = new JLabel();
        jLcontentValorIva.setForeground(Color.white);
        jLcontentValorIva.setBounds(515, 480, 100, 20);
    }
    
    private void crt_jLValorneto() {
        jLvalorNeto = new JLabel("Neto: ");
        jLvalorNeto.setForeground(Color.white);
        jLvalorNeto.setBounds(460, 500, 50, 20);
        jLcontentValorNeto = new JLabel();
        jLcontentValorNeto.setForeground(Color.white);
        jLcontentValorNeto.setBounds(515, 500, 100, 20);
    }
    
    private void crt_jLValorTotal() {
        jLvalorTotal = new JLabel("Total: ");
        jLvalorTotal.setForeground(Color.white);
        jLvalorTotal.setBounds(460, 520, 50, 20);
        jLcontentValorTotal = new JLabel();
        jLcontentValorTotal.setForeground(Color.white);
        jLcontentValorTotal.setBounds(515, 520, 100, 20);
    }
    
    private void crt_jBtnPDF(){
        ImageIcon i = new ImageIcon(Vista.class.getResource("/img/Pdf.png"));
        btnPdf = new JButtonRadius(i, new Color(33, 133, 191), new Color(33, 133, 191));
        btnPdf.setBorderPainted(false);
        btnPdf.setBounds(620, 450, 150, 80);
        btnPdf.setFocusable(false);
        btnPdf.setText("Imprimir");
        btnPdf.setForeground(Color.WHITE);
        btnPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPdf.addActionListener((ActionEvent e) -> {
            if (e.getSource().equals(btnPdf)) {

            }
        });

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

}

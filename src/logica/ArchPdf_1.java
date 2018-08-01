package logica;

/* 
|   Copyright (C) <2018>  <Roebrto Garcia>
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**ArchPDF
 *Clase para crear PDF.
 * @author Roberto Garcia
 */
public class ArchPdf_1 {
   /**Atributos
    * Elemntos de la calse ArchPDF
    */
    private File ruta_destino;
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuenteBolt = new Font(Font.FontFamily.COURIER,10,Font.BOLD,BaseColor.WHITE);
    private Font fuenteBoltN = new Font(Font.FontFamily.COURIER,10,Font.BOLD);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuenteBoltR = new Font(Font.FontFamily.COURIER,10,Font.BOLD,BaseColor.WHITE);
    private Font fuenteBoltRN = new Font(Font.FontFamily.COURIER,10,Font.BOLD);
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER,5,Font.NORMAL,BaseColor.WHITE);
    private Font fuenteNormalN = new Font(Font.FontFamily.COURIER,8,Font.NORMAL);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuenteN = new Font(Font.FontFamily.COURIER,7,Font.NORMAL,BaseColor.WHITE);
    private Font fuenteNN = new Font(Font.FontFamily.COURIER,10,Font.NORMAL);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuentePequena = new Font(Font.FontFamily.COURIER,5,Font.NORMAL,BaseColor.WHITE);
    private Font fuentePequenaN = new Font(Font.FontFamily.COURIER,5,Font.NORMAL);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuenteMediana = new Font(Font.FontFamily.COURIER,8,Font.NORMAL,BaseColor.WHITE);
    private Font fuenteMedianaN = new Font(Font.FontFamily.COURIER,8,Font.NORMAL);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuentePT = new Font(Font.FontFamily.COURIER,6,Font.NORMAL,BaseColor.WHITE);
    private Font fuentePTN = new Font(Font.FontFamily.COURIER,6,Font.NORMAL);
    @SuppressWarnings("FieldMayBeFinal")
    private Font fuenteBoltW = new Font(Font.FontFamily.COURIER, 8, Font.BOLD, BaseColor.WHITE);
    private Font fuenteBoltWN = new Font(Font.FontFamily.COURIER, 8, Font.BOLD);
    private DecimalFormat decimal;
            
    /**ArchPdf
     *constructor basica donde inicia las variables con su valor elemental.
     */
    public ArchPdf_1(){
        ruta_destino = null; // me permite almacenar la ruta del archivo donde 
    }
 
    /**crear_PDF
     * Se crea un PDF a partir de un arreglo de Desprendibles.
     * @param fac
     * @throws IOException
     * @throws DocumentException
     * @throws java.io.FileNotFoundException 
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public void crear_PDF(Factura fac) throws  IOException, DocumentException, FileNotFoundException{
        this.decimal = new DecimalFormat("###,###,###,###");
        //abre ventana de dialogo "guardar"
        this.Colocar_Destino();
        //si destino es diferente de null
        if(this.ruta_destino!=null){
             // se crea instancia del documento
            Document mipdf = new Document(PageSize.LETTER,1,-60,-8,1);
            // se establece una instancia a un documento pdf
            @SuppressWarnings("unused")
            PdfWriter pw = PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));//aqui se esta creando el archivo
            mipdf.open();// se abre el documento
            mipdf.addTitle("Factura"); // se añade el titulo ES UN METADATO
            mipdf.addAuthor("Disconsulting SAS"); // se añade el autor del documento
            //cuadro superior
                PdfPTable tbSuperior   = new PdfPTable(3);
                this.crearEncabezado(tbSuperior,fac);
                tbSuperior.setSpacingAfter(3.0f);
                PdfPTable tbcontenedor = new PdfPTable(3);
                this.crearContenido(tbcontenedor,fac);
                tbcontenedor.setSpacingAfter(3.0f);
                PdfPTable tbvalores = new PdfPTable(3);
                tbvalores.setSpacingAfter(3.0f);
                this.crearValores(tbvalores,fac);
                PdfPTable tbFinal = new PdfPTable(3);
                this.crearFinal(tbFinal);
            mipdf.add(tbSuperior);
            mipdf.add(tbcontenedor);
            mipdf.add(tbvalores);
            mipdf.add(tbFinal);
            mipdf.close();
            try {
                File path = new File (ruta_destino+".pdf");
                Desktop.getDesktop().open(path);
            }catch (IOException ex) {
            ex.printStackTrace();
            }
                }           
        }//se cierra el PDF&
            
    /**colocar_destino
     *Metodo que genera la ubicación del archivo en el PC.
     * @throws java.io.IOException
     */
    public void Colocar_Destino() throws   IOException{
       java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/carpeta-animada-16.png"));
        Frame Fprincipal = new Frame();
        Fprincipal.setIconImage(icon);
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF"); // se le crea un flitro con FLieNameExtensionFilter "el us solo puede generar archivos pdf"
       JFileChooser fileChooser = new JFileChooser(); // jFileChosser es la ventana donde selecciono la ruta, directorio URL tipo de Archivo pdf "se crea el selecionador "
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(Fprincipal); // variable que almacena un valor numerico 
       if ( result == JFileChooser.APPROVE_OPTION){
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }
    
    /**crearEncabezaado
     * Crea el encabezado de la factura:
     */
    private void crearEncabezado(PdfPTable tbSuperior, Factura fac) throws DocumentException{
        try {
            float[]medidas = {20.0f,200.0f,700.0f};
            PdfPCell logo;
            PdfPTable infoFactura,infoEmpresa;
            //tbSuperior.setSpacingBefore(45.0f);
            tbSuperior.setWidths(medidas);
            //agregar logo parte superior izquierda
            String IMG ="src/iconos/logoDis.png";
            java.awt.Image imagen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/logoDis.png"));
            Image image = Image.getInstance(imagen,null);
            image.scaleAbsolute(50,45);
            logo = new PdfPCell();
            logo.setHorizontalAlignment(Element.ALIGN_LEFT);
            logo.setVerticalAlignment(Element.ALIGN_BOTTOM);
            logo.setBorder(0);
            logo.setBorderColorLeft(BaseColor.WHITE);
            //tbl info empresa, información de encabezado
            infoEmpresa = new PdfPTable(1);
            infoEmpresa.setTotalWidth(200);
            infoEmpresa.setLockedWidth(true);
            
            PdfPCell info_E = new PdfPCell(infoEmpresa);
            PdfPCell titulo_1 = new PdfPCell(new Phrase("DiS Consulting S.A.S. \n",fuenteBolt));
            titulo_1.setBorder(0);
            titulo_1.setBorderColor(BaseColor.WHITE);
            titulo_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell titulo_2 = new PdfPCell(new Phrase("NIT 900.357.007-9\n",fuentePT));
            titulo_2.setBorder(0);
            titulo_2.setBorderColor(BaseColor.WHITE);
            titulo_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell titulo_3 = new PdfPCell(new Phrase("Calle 134 #20-56 Torre 3 Of 702\n"
            + " Cel: 312 588 2440\nEmail: dmunera@disconsultingsa.com\nBogotá, D.C. - Colombia.",fuenteMediana));
            titulo_3.setBorder(0);
            titulo_3.setBorderColor(BaseColor.WHITE);
            titulo_3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titulo_3.setHorizontalAlignment(Element.ALIGN_CENTER);
            //agregar titulos a la tabla infoempresa
            infoEmpresa.addCell(titulo_1);
            infoEmpresa.addCell(titulo_2);
            infoEmpresa.addCell(titulo_3);
            info_E.setBorder(0);
//          celdas de info de factura
                PdfPCell numeracion;
                PdfPCell resolucion;
//          info factura
            //float[]tamaños = {50.0f,20.0f,20.0f,30.0f,50.0f,20.0f,20.0f,30.0f};
                infoFactura = new PdfPTable(8);
                //infoFactura.setWidths(tamaños);
                 PdfPCell info_f = new PdfPCell(infoFactura);
                infoFactura.setHorizontalAlignment(Element.ALIGN_RIGHT);
                //elementos de la tabla info factura
                //numercion
                numeracion = new PdfPCell(new Phrase("FACTURA DE VENTA",fuenteBolt));
                numeracion.setColspan(5);
                numeracion.setBorder(5);
                numeracion.setBorderColor(BaseColor.WHITE);
                DecimalFormat d = new DecimalFormat("###");
                
                PdfPCell numero = new PdfPCell(new Phrase("N°"+d.format(fac.getNumf()),fuenteBoltR));
                numero.setColspan(3);
                numero.setBorder(9);
                numero.setBorderColor(BaseColor.WHITE);
                //resolucion
                resolucion = new PdfPCell(new Phrase("Rsolución DIAN 320001035590 de 2023/07/11 "
                    + "Autoriza del No 101 al 1000 Tarifa Retención CREE: 0,6%"
                    + "Régimen Común No Gran Contribuyente ni Autoretenedor Act. "
                    + "ICA 6202 Tarifa RETEICA:6,9 por mil",fuentePequena));
                resolucion.setColspan(8);
                resolucion.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                resolucion.setBorderColor(BaseColor.WHITE);
                //fecha expedicion
//                PdfPCell fecha_exp = new PdfPCell(new Phrase("Fecha\nExpedición",fuentePequena));
//                fecha_exp.setHorizontalAlignment(Element.ALIGN_CENTER);
//                fecha_exp.setVerticalAlignment(Element.ALIGN_CENTER);
//                fecha_exp.setBorderColor(BaseColor.WHITE);
//                PdfPCell dia       = new PdfPCell(new Phrase(String.format("%80s",fac.getDia_E()),fuenteNormalN));
//                dia.setVerticalAlignment(Element.ALIGN_TOP);
//                dia.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                dia.setBorderColor(BaseColor.WHITE);
//                PdfPCell mes       = new PdfPCell(new Phrase(String.format("%80s",fac.getMes_E()),fuenteNormalN));
//                mes.setVerticalAlignment(Element.ALIGN_TOP);
//                mes.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                mes.setBorderColor(BaseColor.WHITE);
//                PdfPCell año       = new PdfPCell(new Phrase(String.format("%80s",fac.getAño_E()),fuenteNormalN));
//                año.setVerticalAlignment(Element.ALIGN_TOP);
//                año.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                año.setBorderColor(BaseColor.WHITE);
//                PdfPCell fecha_ven = new PdfPCell(new Phrase("Fecha\nVencimiento",fuentePequena));
//                fecha_ven.setHorizontalAlignment(Element.ALIGN_CENTER);
//                fecha_ven.setVerticalAlignment(Element.ALIGN_CENTER);
//                fecha_ven.setBorderColor(BaseColor.WHITE);
//                PdfPCell diaV       = new PdfPCell(new Phrase(String.format("%80s",fac.getDia_V()),fuenteNormalN));
//                diaV.setVerticalAlignment(Element.ALIGN_TOP);
//                diaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                diaV.setBorderColor(BaseColor.WHITE);
//                PdfPCell mesV       = new PdfPCell(new Phrase(String.format("%80s",fac.getMes_V()),fuenteNormalN));
//                mesV.setVerticalAlignment(Element.ALIGN_TOP);
//                mesV.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                mesV.setBorderColor(BaseColor.WHITE);
//                PdfPCell añoV       = new PdfPCell(new Phrase(String.format("%80s",fac.getAño_V()),fuenteNormalN));
//                añoV.setVerticalAlignment(Element.ALIGN_TOP);
//                añoV.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                añoV.setBorderColor(BaseColor.WHITE);
                String diaCreacion    = fac.getDia_E();
                String mesCreacion    = fac.getMes_E();
                String añoCreacion    = fac.getAño_E();
                String diaVencimiento = fac.getDia_V();
                String mesVencimiento = fac.getMes_V();
                String añoVencimiento = fac.getAño_V();
                
                String texto = String.format("%1s", diaCreacion)+String.format("%4s", mesCreacion)
                        +String.format("%7s", añoCreacion)+String.format("%15s", diaVencimiento)
                        +String.format("%4s", mesVencimiento)+String.format("%7s", añoVencimiento);
                PdfPCell fecha = new PdfPCell(new Phrase("\n\n\n\n"+"\n"+texto,fuenteNormalN));
                fecha.setColspan(8);
                fecha.setBorderColor(BaseColor.WHITE);
                fecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
//
                infoFactura.addCell(numeracion);
                infoFactura.addCell(numero);
                infoFactura.addCell(resolucion);
                infoFactura.addCell(fecha);
//                infoFactura.addCell(dia);
//                infoFactura.addCell(mes);
//                infoFactura.addCell(año);
//                infoFactura.addCell(fecha_ven);
//                infoFactura.addCell(diaV);
//                infoFactura.addCell(mesV);
//                infoFactura.addCell(añoV);
                info_f.setBorder(0);
                 info_f.setBorderColor(BaseColor.WHITE);
//agregar elementos.
           //tbSuperior.setTotalWidth(100);
           tbSuperior.addCell(logo);
           tbSuperior.addCell(info_E); 
           tbSuperior.addCell(info_f);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(ArchPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**crearContenido
     * Crea el contenido de la factura
     */
    private void crearContenido(PdfPTable tbcontenedor, Factura fac) throws DocumentException{
        float[]medidas = {30.0f,230.0f,80.0f};
        tbcontenedor.setWidths(medidas);
        PdfPCell señor = new PdfPCell(new Phrase(String.format("\n"+"%30s",fac.getCliente()),fuenteNN));
        señor.setColspan(2);
        señor.setBorder(5);
        señor.setBorderColor(BaseColor.WHITE);
        señor.setVerticalAlignment(Element.ALIGN_TOP);
        PdfPCell nit   = new PdfPCell(new Phrase("\n"+fac.getNit(),fuenteNN));
        nit.setBorder(9);
        nit.setBorderColor(BaseColor.WHITE);
        nit.setHorizontalAlignment(Element.ALIGN_RIGHT);
        nit.setVerticalAlignment(Element.ALIGN_TOP);
        PdfPCell direccion = new PdfPCell(new Phrase(String.format("%31s",fac.getDireccion())+"\n\n",fuenteNN));
        direccion.setColspan(2);
        direccion.setBorder(3);
        direccion.setBorder(5);
        direccion.setBorderColor(BaseColor.WHITE);
        direccion.setVerticalAlignment(Element.ALIGN_TOP);
        PdfPCell telefono = new PdfPCell(new Phrase(fac.getTelefono()+"\n",fuenteNN));
        telefono.setBorder(9);
        telefono.setBorderColor(BaseColor.WHITE);
        telefono.setHorizontalAlignment(Element.ALIGN_RIGHT);
        telefono.setVerticalAlignment(Element.ALIGN_TOP);
//
        PdfPCell cant = new PdfPCell(new Phrase("",fuenteBoltW));
        cant.setBackgroundColor(BaseColor.WHITE);
        cant.setBorder(5);
        cant.setBorderColor(BaseColor.WHITE);
        cant.setVerticalAlignment(Element.ALIGN_CENTER);
        cant.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell concepto = new PdfPCell(new Phrase("CONCEPTO",fuenteBoltW));
        concepto.setBackgroundColor(BaseColor.WHITE);
        concepto.setBorder(12);
        concepto.setBorderColor(BaseColor.WHITE);
        concepto.setBorderColor(BaseColor.WHITE);
        concepto.setVerticalAlignment(Element.ALIGN_CENTER);
        concepto.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell valor = new PdfPCell(new Phrase("VALOR",fuenteBoltW));
        valor.setBackgroundColor(BaseColor.WHITE);
        valor.setBorder(9);
        valor.setBorderColor(BaseColor.WHITE);
        valor.setVerticalAlignment(Element.ALIGN_CENTER);
        valor.setHorizontalAlignment(Element.ALIGN_CENTER);
        //
        PdfPCell can1,can2,can3,can4,can5,can6,can7,can8,can9,can10,con1,con2,con3,con4,con5,
                con6,con7,con8,con9,con10,val1,val2,val3,val4,val5,val6,val7,val8,val9,val10;
        List<PdfPCell> cantidad = new ArrayList<>();
        List<PdfPCell> contenido = new ArrayList<>();
        List<PdfPCell> valore = new ArrayList<>();
        int val = 12;
       
        can1 = new PdfPCell(new Phrase());
        can1.setBorder(4);
        can1.setBorderColor(BaseColor.WHITE);
        con1 = new PdfPCell(new Phrase());
        con1.setBorder(4);
        con1.setBorderColor(BaseColor.WHITE);
        val1 = new PdfPCell(new Phrase());
        val1.setBorder(val);
        val1.setBorderColor(BaseColor.WHITE);
        cantidad.add(can1);
        contenido.add(con1);
        valore.add(val1);
        
        can2 = new PdfPCell(new Phrase(" "));
        can2.setBorder(4);
        can2.setBorderColor(BaseColor.WHITE);
        can2.setBackgroundColor(BaseColor.WHITE);
        con2 = new PdfPCell(new Phrase(" "));
        con2.setBackgroundColor(BaseColor.WHITE);
        con2.setBorder(4);
        con2.setBorderColor(BaseColor.WHITE);
        val2 = new PdfPCell(new Phrase(" "));
        val2.setBackgroundColor(BaseColor.WHITE);
        val2.setBorder(val);
        val2.setBorderColor(BaseColor.WHITE);
        cantidad.add(can2);
        contenido.add(con2);
        valore.add(val2);
                
        can3 = new PdfPCell(new Phrase(" "));
        can3.setBorder(4);
        can3.setBorderColor(BaseColor.WHITE);
        con3 = new PdfPCell(new Phrase(" "));
        con3.setBorder(4);
        con3.setBorderColor(BaseColor.WHITE);
        val3 = new PdfPCell(new Phrase(" "));
        val3.setBorder(val);
        val3.setBorderColor(BaseColor.WHITE);
        cantidad.add(can3);
        contenido.add(con3);
        valore.add(val3);
        
        can4 = new PdfPCell(new Phrase(" "));
        can4.setBorder(4);
        can4.setBorderColor(BaseColor.WHITE);
        can4.setBackgroundColor(BaseColor.WHITE);
        con4 = new PdfPCell(new Phrase(" "));
        con4.setBorder(4);
        con4.setBorderColor(BaseColor.WHITE);
        con4.setBackgroundColor(BaseColor.WHITE);
        val4 = new PdfPCell(new Phrase(" "));
        val4.setBorder(val);
        val4.setBorderColor(BaseColor.WHITE);
        val4.setBackgroundColor(BaseColor.WHITE);        
        cantidad.add(can4);
        contenido.add(con4);
        valore.add(val4);
        
        can5 = new PdfPCell(new Phrase(" "));
        can5.setBorder(4);
        can5.setBorderColor(BaseColor.WHITE);
        con5 = new PdfPCell(new Phrase(" "));
        con5.setBorder(4);
        con5.setBorderColor(BaseColor.WHITE);
        val5 = new PdfPCell(new Phrase(" "));
        val5.setBorder(val);
        val5.setBorderColor(BaseColor.WHITE);
        cantidad.add(can5);
        contenido.add(con5);
        valore.add(val5);
        
        can6 = new PdfPCell(new Phrase(" "));
        can6.setBackgroundColor(BaseColor.WHITE);
        can6.setBorder(4);
        can6.setBorderColor(BaseColor.WHITE);
        con6 = new PdfPCell(new Phrase(" "));
        con6.setBackgroundColor(BaseColor.WHITE);
        con6.setBorder(4);
        con6.setBorderColor(BaseColor.WHITE);
        val6 = new PdfPCell(new Phrase(" "));
        val6.setBackgroundColor(BaseColor.WHITE);        
        val6.setBorder(val);
        val6.setBorderColor(BaseColor.WHITE);
        cantidad.add(can6);
        contenido.add(con6);
        valore.add(val6);
        
        can7 = new PdfPCell(new Phrase(" "));
        can7.setBorder(4);
        can7.setBorderColor(BaseColor.WHITE);
        con7 = new PdfPCell(new Phrase(" "));
        con7.setBorder(4);
        con7.setBorderColor(BaseColor.WHITE);
        val7 = new PdfPCell(new Phrase(" "));
        val7.setBorder(val);
        val7.setBorderColor(BaseColor.WHITE);
        cantidad.add(can7);
        contenido.add(con7);
        valore.add(val7);
        
        can8 = new PdfPCell(new Phrase(" "));
        can8.setBorder(4);
        can8.setBorderColor(BaseColor.WHITE);
        can8.setBackgroundColor(BaseColor.WHITE);
        con8 = new PdfPCell(new Phrase(" "));
        con8.setBorder(4);
        con8.setBorderColor(BaseColor.WHITE);
        con8.setBackgroundColor(BaseColor.WHITE);
        val8 = new PdfPCell(new Phrase(""));
        val8.setBackgroundColor(BaseColor.WHITE);
        val8.setBorder(val);
        val8.setBorderColor(BaseColor.WHITE);
        cantidad.add(can8);
        contenido.add(con8);
        valore.add(val8);
        
        can9 = new PdfPCell(new Phrase(""));
        can9.setBorder(4);
        can9.setBorderColor(BaseColor.WHITE);
        con9 = new PdfPCell(new Phrase(""));
        con9.setBorder(4);
        con9.setBorderColor(BaseColor.WHITE);
        val9 = new PdfPCell(new Phrase(""));
        val9.setBorder(val);
        val9.setBorderColor(BaseColor.WHITE);
        cantidad.add(can9);
        contenido.add(con9);
        valore.add(val9);
        
        can10 = new PdfPCell(new Phrase(""));
        can10.setBorder(5);
        can10.setBorderColor(BaseColor.WHITE);
        can10.setBackgroundColor(BaseColor.WHITE);
        con10 = new PdfPCell(new Phrase(""));
        con10.setBorder(9);
        con10.setBorderColor(BaseColor.WHITE);
        con10.setBackgroundColor(BaseColor.WHITE);
        val10 = new PdfPCell(new Phrase(""));
        val10.setBorder(9);
        val10.setBackgroundColor(BaseColor.WHITE);
        val10.setBorderColor(BaseColor.WHITE);
        cantidad.add(can10);
        contenido.add(con10);
        valore.add(val10);
        //
        
        for(int kt=0;kt<fac.getCantiad().size();kt++){
            PdfPCell canIte = cantidad.get(kt);
            Phrase tx = new Phrase();
            canIte.setPhrase(tx);
            canIte.setHorizontalAlignment(Element.ALIGN_LEFT);
        }
        for(int k=0;k<fac.getConceptos().size();k++){
            PdfPCell canIte = contenido.get(k);
            Phrase tx = new Phrase(fac.getConceptos().get(k));
            canIte.setPhrase(tx);
        }
    
//            PdfPCell canIte = valore.get(1);
//            canIte.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            String text ="$ "+darFormatoDec(String.valueOf(fac.getTotal()));
//            Phrase tx = new Phrase(String.format("%80s",text));
//            canIte.setPhrase(tx);
            for(int k=0;k<fac.getValores().size();k++){
            PdfPCell canIte = valore.get(k+1);
            //Phrase tx = new Phrase("$ "+darFormatoDec(String.valueOf(fac.getTotal())),fuenteBoltS);
            double total_trabajado = fac.getValores().get(k)* Double.parseDouble(fac.getCantiad().get(k));
            Phrase tx = new Phrase("$ "+darFormatoDec(String.valueOf(total_trabajado)),fuenteBolt);
            canIte.setPhrase(tx);
        }
        
        //
        tbcontenedor.addCell(señor);
        tbcontenedor.addCell(nit);
        tbcontenedor.addCell(direccion);
        tbcontenedor.addCell(telefono);
        tbcontenedor.addCell(cant);
        tbcontenedor.addCell(concepto);
        tbcontenedor.addCell(valor);
        tbcontenedor.addCell(can1);
        tbcontenedor.addCell(con1);
        tbcontenedor.addCell(val1);
        tbcontenedor.addCell(can2);
        tbcontenedor.addCell(con2);
        tbcontenedor.addCell(val2);
        tbcontenedor.addCell(can3);
        tbcontenedor.addCell(con3);
        tbcontenedor.addCell(val3);
        tbcontenedor.addCell(can4);
        tbcontenedor.addCell(con4);
        tbcontenedor.addCell(val4);
        tbcontenedor.addCell(can5);
        tbcontenedor.addCell(con5);
        tbcontenedor.addCell(val5);
        tbcontenedor.addCell(can6);
        tbcontenedor.addCell(con6);
        tbcontenedor.addCell(val6);
        tbcontenedor.addCell(can7);
        tbcontenedor.addCell(con7);
        tbcontenedor.addCell(val7);
        tbcontenedor.addCell(can8);
        tbcontenedor.addCell(con8);
        tbcontenedor.addCell(val8);
        tbcontenedor.addCell(can9);
        tbcontenedor.addCell(con9);
        tbcontenedor.addCell(val9);
        tbcontenedor.addCell(can10);
        tbcontenedor.addCell(con10);
        tbcontenedor.addCell(val10);
        
    }
    
    /**CrearValores
     * Crea la tabla contenedora de valores
     * @param tbvalores 
     */
    private void crearValores(PdfPTable tbvalores, Factura fac) throws DocumentException {
     float[]medidas = {100.0f,100.0f,100.0f};
        tbvalores.setWidths(medidas);
        
        PdfPCell son = new PdfPCell(new Phrase(String.format("\n"+"%5s",fac.getValorTxt()),fuenteNN));
        son.setColspan(2);
        son.setRowspan(3);
        son.setBorderColor(BaseColor.WHITE);
        son.setVerticalAlignment(Element.ALIGN_TOP);
        PdfPTable valores = new PdfPTable(2);
        float[]tam = {40.0f,80.0f};
        valores.setWidths(tam);
        PdfPCell value = new PdfPCell(valores);
        value.setBorderColor(BaseColor.WHITE);
        PdfPCell subtotal = new PdfPCell(new Phrase("SUB TOTAL $",fuenteN));
        subtotal.setBorder(0);
        subtotal.setBorderColor(BaseColor.WHITE);
        subtotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subtotal.setVerticalAlignment(Element.ALIGN_CENTER);
        PdfPCell valSubtotal = new PdfPCell(new Phrase(String.format("\n"+"%5s",darFormatoDec(String.valueOf(fac.getSubtotal()))),fuenteNN));
        valSubtotal.setBorder(0);
        valSubtotal.setBorderColor(BaseColor.WHITE);
        valSubtotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valSubtotal.setVerticalAlignment(Element.ALIGN_TOP);
        
        PdfPCell IVA = new PdfPCell(new Phrase("IVA $",fuenteN));
        IVA.setBorder(0);
        IVA.setBorderColor(BaseColor.WHITE);
        IVA.setHorizontalAlignment(Element.ALIGN_RIGHT);
        IVA.setVerticalAlignment(Element.ALIGN_CENTER);
        PdfPCell valIVA = new PdfPCell(new Phrase(String.format("%5s",darFormatoDec(String.valueOf(fac.getIva()))),fuenteNN));
        valIVA.setBorder(0);
        valIVA.setBorderColor(BaseColor.WHITE);
        valIVA.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valIVA.setVerticalAlignment(Element.ALIGN_TOP);
        
        PdfPCell total = new PdfPCell(new Phrase("TOTAL $",fuenteBolt));
        total.setBorder(0);
        total.setBorderColor(BaseColor.WHITE);
        total.setHorizontalAlignment(Element.ALIGN_RIGHT);
        total.setVerticalAlignment(Element.ALIGN_BOTTOM);
        PdfPCell valtotal = new PdfPCell(new Phrase(String.format("%15s",darFormatoDec(String.valueOf(fac.getTotal()))),fuenteNN));
        valtotal.setBorder(0);
        valtotal.setBorderColor(BaseColor.WHITE);
        valtotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valtotal.setVerticalAlignment(Element.ALIGN_TOP);
        
        valores.addCell(subtotal);
        valores.addCell(valSubtotal);
        valores.addCell(IVA);
        valores.addCell(valIVA);
        valores.addCell(total);
        valores.addCell(valtotal);
        
        tbvalores.addCell(son);
        tbvalores.addCell(value);
        
        
    }

    /**crearFinal
     * Crea la parte final de la factura.
     * @param tbFinal 
     */
    private void crearFinal(PdfPTable tbFinal) throws DocumentException {
      float[]medidas = {80.0f,140.0f,80.0f};
        tbFinal.setWidths(medidas);  
        
        PdfPTable tbFirma = new PdfPTable(1);
        PdfPCell firmaD = new PdfPCell(tbFirma);
        firmaD.setBorderColor(BaseColor.WHITE);
        PdfPCell ateSpacio = new PdfPCell(new Phrase("Atte,\n\n\n",fuentePequena));
        ateSpacio.setBorderColor(BaseColor.WHITE);
        ateSpacio.setRowspan(9);
        ateSpacio.setBorder(0);
        PdfPCell separador = new PdfPCell(new Phrase("\t\t"));
        separador.setBorder(0);
        separador.setBorderColor(BaseColor.WHITE);
        PdfPCell disName = new PdfPCell(new Phrase("DiS Consulting S.A.S.",fuentePequena));
        disName.setHorizontalAlignment(Element.ALIGN_CENTER);
        disName.setBorder(0);
        disName.setBorderColor(BaseColor.WHITE);
        
        tbFirma.addCell(ateSpacio);
        tbFirma.addCell(separador);
        tbFirma.addCell(disName);
        PdfPCell text = new PdfPCell(new Phrase("Esta Factura de venta se asimila en sus efectos"
                + " legales a la Letra de Cambio(Articulo 774 del Código de Comercio), vencido el plazo"
                + " otorgado para el pago"
                + "de la presente factura, se causaran intereses a la taza maxima permitida por ley."
                + "El cliente acepta que quien firme en su nombre tiene la autorización para ello,"
                + "por tanto el cliente se hace responsable de la cancelación de esta factura."
                + "El comprador declara haber recibido real y materialmente las mercacías y/o servicios en este Titulo"
                + "-Valor. (Ley 1231 Julio 2008))",fuentePequena));
        text.setBorder(0);
        text.setBorderColor(BaseColor.WHITE);
        text.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        
        float[]tama = {40.0f,20.0f,20.0f,20.0f};
        PdfPTable tbClient = new PdfPTable(4);
        tbClient.setWidths(tama);
        PdfPCell recibido = new PdfPCell(tbClient);
        recibido.setBorderColor(BaseColor.WHITE);
        PdfPCell rConforme = new PdfPCell(new Phrase("RECIBÍ CONFORME\n\n\n",fuentePequena));
        rConforme.setColspan(4);
        rConforme.setRowspan(4);
        rConforme.setBorder(0);
        rConforme.setBorderColor(BaseColor.WHITE);
        PdfPCell nom = new PdfPCell(new Phrase("Nombre/C.C.",fuentePequena));
        nom.setColspan(4);
        nom.setBorder(0);
        nom.setBorderColor(BaseColor.WHITE);
        PdfPCell fecha = new PdfPCell(new Phrase("Fecha",fuentePequena));
        fecha.setHorizontalAlignment(Element.ALIGN_CENTER);
        fecha.setBorderColor(BaseColor.WHITE);
        PdfPCell dia = new PdfPCell(new Phrase("Día",fuentePequena));
        dia.setVerticalAlignment(Element.ALIGN_TOP);
        dia.setHorizontalAlignment(Element.ALIGN_CENTER);
        dia.setBorderColor(BaseColor.WHITE);
        PdfPCell mes = new PdfPCell(new Phrase("Mes",fuentePequena));
        mes.setHorizontalAlignment(Element.ALIGN_CENTER);
        mes.setVerticalAlignment(Element.ALIGN_TOP);
        mes.setBorderColor(BaseColor.WHITE);
        PdfPCell año = new PdfPCell(new Phrase("Año",fuentePequena));
        año.setVerticalAlignment(Element.ALIGN_TOP);
        año.setHorizontalAlignment(Element.ALIGN_CENTER);
        año.setBorderColor(BaseColor.WHITE);
        tbClient.addCell(rConforme);
        tbClient.addCell(nom);
        tbClient.addCell(fecha);
        tbClient.addCell(dia);
        tbClient.addCell(mes);
        tbClient.addCell(año);
        tbFinal.addCell(firmaD);
        tbFinal.addCell(text);
        tbFinal.addCell(recibido);
    }
    
    /**darFormatoDec
     Se da el formato numerico
     ###,###,###
     */
    private String darFormatoDec(String val){
        String rt = "";
        if((val.equals(" "))||(val.equals("\0"))||(val.equals("\n"))){
            rt = "";
        }
        else{
        double valor = Double.parseDouble(val);
        DecimalFormat dec = new DecimalFormat("#,###,###,###");
        rt = ""+dec.format(valor);
        }
        return rt;
    }
    //
}
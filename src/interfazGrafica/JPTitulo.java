/*
 *  Copyright (C) <2018>  <Roebrto Garcia>
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
package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**JpanelTitulo
 *Contiene informacion del titulo.
 * @author Roberto Garcia
 */
@SuppressWarnings("serial")
public class JPTitulo extends JPanel{
    
 /**Atributos
  * Elemntos utilizados en el panel
  */
private JLabel titulo;
private JLabel imagen;
private GridBagConstraints c;
  /**JpanelTitulo
   * Se incializan las variables y metodos qeu forman los JLabels.
   */
 public JPTitulo(){
      //carateristicas del panel
        this.setSize(540,50); 
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //elementos del panel
        this.crearImagen();
        this.crearTitulo();
  }
  
  /**CrearTitulo
   * Metodo encargado de inicializar y crear las caracteristicas del titulo.
   */
   public void crearTitulo(){
       this.titulo = new JLabel("Facturación");
       this.titulo.setFont(new Font("Georgia",3,19));
       this.add(titulo,c);
   }
   
   /**crearImagen
    * Metodo encargado de adicionar una imagen junto al titulo.
    */
   public void crearImagen(){
       ImageIcon icon = new ImageIcon(getClass().getClass().getResource("/iconos/logoDis.png"));
       ImageIcon logo = new ImageIcon(icon.getImage().getScaledInstance(40, 40, 1));
       this.imagen = new JLabel(logo);
       this.add(imagen,c);
   }
}

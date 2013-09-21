/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import pojo.Empleado;
import novedades.dao.imp.EmpleadoDaoImp;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class GestorEmpleado extends javax.swing.JDialog {
    public static final int VENTANA_GESTOR_ASISTENCIA=1;
    public static final int MENU=2;
    public   boolean isModificar = false;// paramentro global uso: para ver si se presiono un boton agregar o moficar
                                         // sirve para configuarar ventnana infomracion  empleado
    
    
    private DefaultTableModel modelo;
    private List<Empleado> listaEmpleado;
    private boolean seleccionado;
    private int legajo;
    int quienloyamo;
    java.awt.Frame parent;// indica quien es el padre. me sirve para pasar el icono de la aplcacion
  //  EmpleadoDao empleados ;
   
    public GestorEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        
        
        initComponentesVentana();    
        this.setTitle("MODIFICACION DE EMPLEADO");
        setLocationRelativeTo(this);
        setVisible(true);
        
        
    }
    public GestorEmpleado(java.awt.Frame parent, boolean modal,int quienloyamo) {
        super(parent, modal);
        this.parent = parent;
        this.quienloyamo = quienloyamo;
        initComponents();
        this.setTitle("ALTA DE EMPLEADO");

         
        if (MENU== quienloyamo) {
            //boton seleccionar no debe aparecer
            btnSeleccion2.setVisible(false);
           
            
        }else{
           // buscador de empleado
            this.setSize(this.getWidth(),this.getHeight()-btnModificar.getWidth() );
            btnNuevo.setVisible(false);
            btnModificar.setVisible(false);
            btnCancelarOperacion.setVisible(false);
            btnReporte.setVisible(false);
        }
        initComponentesVentana();  
        setLocationRelativeTo(this);
        setVisible(true);
        
        
    }
    /**
     *  cargarTablaConEmpleado();
        
        setEnableVentanaInformacionEmpleado(false);
        limpiarVenanaEmpleado();
        btnModificar.setEnabled(false);
        btnNuevo.setEnabled(true);
     */
    public void initComponentesVentana(){
        //empleados = new EmpleadoDaoImp();
        cargarTablaConEmpleado();
                
        btnModificar.setEnabled(false);
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        elegirFoto = new javax.swing.JFileChooser();
        btnSeleccion = new org.edisoncor.gui.button.ButtonIpod();
        panel1 = new org.edisoncor.gui.panel.Panel();
        btnModificar = new org.edisoncor.gui.button.ButtonIpod();
        btnNuevo = new org.edisoncor.gui.button.ButtonIpod();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        comboBoxRound1 = new org.edisoncor.gui.comboBox.ComboBoxRound();
        txtEmpleado = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        btnSeleccion2 = new org.edisoncor.gui.button.ButtonIpod();
        btnCancelarOperacion = new org.edisoncor.gui.button.ButtonIpod();
        btnReporte = new org.edisoncor.gui.button.ButtonIpod();

        btnSeleccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/OK.jpg"))); // NOI18N
        btnSeleccion.setText("SELECT");
        btnSeleccion.setAnimacion(false);
        btnSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GESTOR DE EMPLEADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 24), new java.awt.Color(204, 204, 204))); // NOI18N

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/modificar.png"))); // NOI18N
        btnModificar.setText("MODIFIC");
        btnModificar.setAnimacion(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/user_16.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setAnimacion(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        tblEmpleado.setBackground(new java.awt.Color(204, 204, 204));
        tblEmpleado.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LEGAJO", "APELLIDO", "NOMBRE", "DNI", "FECHA INGRESO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        tblEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblEmpleadoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmpleado);

        labelMetric1.setText("FILTRO");

        comboBoxRound1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Legajo" }));

        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyPressed(evt);
            }
        });

        btnSeleccion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/tick_32.png"))); // NOI18N
        btnSeleccion2.setText("  ");
        btnSeleccion2.setAnimacion(false);
        btnSeleccion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccion2ActionPerformed(evt);
            }
        });

        btnCancelarOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/Atras.png"))); // NOI18N
        btnCancelarOperacion.setText("ATRAS");
        btnCancelarOperacion.setAnimacion(false);
        btnCancelarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarOperacionActionPerformed(evt);
            }
        });

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freelancersteam/www/java/tomafoto/images/PDF1.png"))); // NOI18N
        btnReporte.setText("REPORTE");
        btnReporte.setAnimacion(false);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(comboBoxRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnCancelarOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSeleccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean isBotonSeleccionado() {
        return seleccionado;
    }

    public int getLegajo() {
        return legajo;
    }

    
    
    
    private void txtEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyPressed
        // filtrar las coincidencias con el contenido de la caja de texto
        
            
             listaEmpleado = new EmpleadoDaoImp().listarEmpleado();
        if ( txtEmpleado.getText().trim().isEmpty()) {
          listaEmpleado = filtrarPorNombreEmpleado(listaEmpleado,txtEmpleado.getText()); 

        }else{
            if (comboBoxRound1.getSelectedIndex()==0) {
                // POR  NOMBRE
                 listaEmpleado = filtrarPorNombreEmpleado(listaEmpleado,txtEmpleado.getText()); 

        }
         else {
            // FILTRO POR LEGAJO
             listaEmpleado = filtrarPorLegajoEmpleado(listaEmpleado,txtEmpleado.getText()); 
        }
        }
       com.freelancersteam.www.java.tomafoto.util.TablaUtil.prepararTablaEmpleado(modelo, tblEmpleado);
       com.freelancersteam.www.java.tomafoto.util.TablaUtil.cargarModeloEmpleado(modelo, listaEmpleado, tblEmpleado);
    }//GEN-LAST:event_txtEmpleadoKeyPressed
  
    private void buttonIpod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod1ActionPerformed
//       limpiarVenanaEmpleado();
//       setEditableVentanaInformacionEmpleado(true);
    }//GEN-LAST:event_buttonIpod1ActionPerformed

    private void buttonIpod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIpod2ActionPerformed
     
    }//GEN-LAST:event_buttonIpod2ActionPerformed

    private void btnCancelarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOperacionActionPerformed
       // cancela la operacion actual

        this.dispose();
        
    }//GEN-LAST:event_btnCancelarOperacionActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
           int fila = tblEmpleado.getSelectedRow();
        if (fila!= -1) {
            btnModificar.setEnabled(true);  
            System.out.println("selecciono con el mouse");
        }
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void tblEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmpleadoKeyPressed
//        if (!isModificar) {
//           if (evt.getKeyCode()==KeyEvent.VK_ENTER){
//        int fila = tblEmpleado.getSelectedRow();
//        if (fila!= -1) {
//        modelo =(DefaultTableModel)tblEmpleado.getModel();
//        legajo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
//            if (quienloyamo==MENU) {
//                 Empleado empleado = new EmpleadoDaoImp().getEmpleado(legajo);
//                 System.out.println(empleado.getApellido());
//                 btnModificar.setEnabled(true);    
//            }
//       
//        }
//       }
            
//        }
        
        
    }//GEN-LAST:event_tblEmpleadoKeyPressed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
//        isModificar= true;
        int fila = tblEmpleado.getSelectedRow();
        if (fila!= -1) {
            legajo = (Integer) tblEmpleado.getModel().getValueAt(tblEmpleado.getSelectedRow(), 0);
            //LLAMAR A A LA VENTANA NUEVO EMPLEADO PARA EDITAR
            AltaEmpleado ventanaEditEmpleado = new AltaEmpleado(parent, true,legajo);
            cargarTablaConEmpleado();    
        }else{
            JOptionPane.showMessageDialog(null, "Debes seleccionar un Empleado de la Tabla");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        AltaEmpleado ventanaNuevoEmpleado = new AltaEmpleado(parent, true);
//        if (ventanaNuevoEmpleado.isBotonGuardarSelecciono()) {
            cargarTablaConEmpleado();
//            ventanaNuevoEmpleado.dispose();
//            
//        }
        
         
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionActionPerformed
      
    }//GEN-LAST:event_btnSeleccionActionPerformed

    private void btnSeleccion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccion2ActionPerformed
            
        int fila = tblEmpleado.getSelectedRow();
        if (fila== -1) {
            // no se selecciono ninguna fila de la lista
            JOptionPane.showMessageDialog(null, "debes seleccionar un Empleado ", "Informacion",JOptionPane.INFORMATION_MESSAGE);
        } else {
            seleccionado = true;
            modelo = (DefaultTableModel)tblEmpleado.getModel();
            legajo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            this.dispose();
        }
    }//GEN-LAST:event_btnSeleccion2ActionPerformed

private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_btnReporteActionPerformed
      private void permitirSoloNumero(java.awt.event.KeyEvent evt) {
//          // permitir solo el ingreso de numero
//         char caracter = evt.getKeyChar();
//        if(((caracter < '0') ||
//         (caracter > '9')) &&
//         (caracter != '\b' /*corresponde a BACK_SPACE*/))
//      {
//         evt.consume();  // ignorar el evento de teclado
//      }
       
     }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestorEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestorEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestorEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestorEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestorEmpleado dialog = new GestorEmpleado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod btnCancelarOperacion;
    private org.edisoncor.gui.button.ButtonIpod btnModificar;
    private org.edisoncor.gui.button.ButtonIpod btnNuevo;
    private org.edisoncor.gui.button.ButtonIpod btnReporte;
    private org.edisoncor.gui.button.ButtonIpod btnSeleccion;
    private org.edisoncor.gui.button.ButtonIpod btnSeleccion2;
    private org.edisoncor.gui.comboBox.ComboBoxRound comboBoxRound1;
    private javax.swing.JFileChooser elegirFoto;
    private javax.swing.JScrollPane jScrollPane2;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTable tblEmpleado;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtEmpleado;
    // End of variables declaration//GEN-END:variables

    /**
     * PREPERARA Y CARAGA LA TABLA EMPLEADO CON DATOS 
     */
        private void cargarTablaConEmpleado() {

       listaEmpleado =new EmpleadoDaoImp().listarEmpleado();
       com.freelancersteam.www.java.tomafoto.util.TablaUtil.prepararTablaEmpleado(modelo, tblEmpleado);
       com.freelancersteam.www.java.tomafoto.util.TablaUtil.cargarModeloEmpleado(modelo, listaEmpleado, tblEmpleado);
    }

    private List<Empleado> filtrarPorNombreEmpleado(List<Empleado> listaEmpleado, String text) {
         List<Empleado> list = new ArrayList<Empleado>();
         for (Empleado empleado : listaEmpleado) {
             if (empleado.getApellido().contains(text)||empleado.getNombre().contains(text)) {
                 list.add(empleado);
             }
        }
         return list;
    }
    private List<Empleado> filtrarPorLegajoEmpleado(List<Empleado> listaEmpleado, String text) {
         List<Empleado> list = new ArrayList<Empleado>();
         for (Empleado empleado : listaEmpleado) {
             if (String.valueOf(empleado.getLegajo()).contains(text)) {
                 list.add(empleado);
             }
        }
         return list;
    }

   /**
    * 
    * @param logico indica que los componetnes estara o no editables
    * @param isModificado indica si se va a editar para agregar o modificar un empleado existente
    * en caso de que sea un empleado existente las cajas legajo,clave,y repertir clave se mantendran no editable
    */
     
     private void prepararParaModificar(){
          
       
            //boton agregar no activar
            btnNuevo.setEnabled(false);
            btnModificar.setEnabled(false);
        
     }
     private void prepararParaAgregar(){

          //boton modificar no activo
          btnModificar.setEnabled(false);
           btnNuevo.setEnabled(false);
     }
    

     
}

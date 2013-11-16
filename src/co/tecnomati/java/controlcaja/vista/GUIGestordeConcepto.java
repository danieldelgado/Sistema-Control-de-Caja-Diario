/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tecnomati.java.controlcaja.vista;

import co.tecnomati.java.controlcaja.cons.Constantes;

import co.tecnomati.java.controlcaja.dominio.Concepto;
import co.tecnomati.java.controlcaja.modelo.ModeloConcepto;
import co.tecnomati.java.controlcaja.modelo.ModeloConcepto2;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Joel
 */
public class GUIGestordeConcepto extends javax.swing.JDialog {
int numeroSeleccion;
    Concepto asociado;
    ModeloConcepto modeloasociado = new ModeloConcepto();  // para mostrar todos los conceptos
    ModeloConcepto2 modeloasociado2 = new ModeloConcepto2();// para filtrar los conceptos automaticos
    

   
    private  TableRowSorter sorter;
    GUIGestorAsociado parent;
    boolean agregado= false;
    
    int i ; // tipo de modelo de concepto
    /**
     * Creates new form GUIGestordeConcepto
     */
public GUIGestordeConcepto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarTabla();
        btnSeleccionar.setVisible(false);
        this.setTitle(Constantes.NAME_GESTOR_CONCEPTOS);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    GUIGestordeConcepto(java.awt.Frame parent,boolean modal, int i) {
        super(parent, modal);
        initComponents();
        this.i = i;
        if (i==Constantes.MODELO_TODOS_LOS_CONCEPTOS) {
            btnSeleccionar.setVisible(false);
        }    
        inicializarGestorModeloTabla();
        this.setTitle(Constantes.NAME_GESTOR_CONCEPTOS);
        this.setLocationRelativeTo(null);
        this.setVisible(true);    }

     
    public void inicializarTabla(){
        modeloasociado= new ModeloConcepto();
        sorter = new TableRowSorter(modeloasociado);
        tblConcepto.setModel(modeloasociado);
    }
    public void inicializarTabla2(){
        modeloasociado2= new ModeloConcepto2();
        sorter = new TableRowSorter(modeloasociado2);
        tblConcepto.setModel(modeloasociado2);
    }
    public Concepto getAsociado() {
        return asociado;
    }
    public void setAsociado(Concepto asociado) {
        this.asociado = asociado;
    }

    public boolean isAgregado() {
        return agregado;
    }

    public void setAgregado(boolean agregado) {
        this.agregado = agregado;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConcepto = new javax.swing.JTable();
        btnCancelar = new org.edisoncor.gui.button.ButtonIpod();
        btnNuevoConcepto = new org.edisoncor.gui.button.ButtonIpod();
        btnEditar = new org.edisoncor.gui.button.ButtonIpod();
        jPanel3 = new javax.swing.JPanel();
        labelCodigo = new org.edisoncor.gui.label.LabelMetric();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        labelDescripcion = new org.edisoncor.gui.label.LabelMetric();
        btnSeleccionar = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblConcepto.setBackground(new java.awt.Color(204, 204, 204));
        tblConcepto.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblConcepto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblConcepto);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/Atras.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevoConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/document_64.png"))); // NOI18N
        btnNuevoConcepto.setText("Nuevo");
        btnNuevoConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoConceptoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/modificar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar por ...", 0, 0, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        labelCodigo.setText("Codigo:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });

        labelDescripcion.setText("Descripcion:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo)
                    .addComponent(txtDescripcion))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/tick_48.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
private void inicializarGestorModeloTabla(){
    if (i==Constantes.MODELO_TODOS_LOS_CONCEPTOS) {
                  inicializarTabla();
                  
              } else {
                  inicializarTabla2();
              }
}
    private void btnNuevoConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoConceptoActionPerformed
          GUIConceptos guiConcepto =  new GUIConceptos(null, true);
          if (guiConcepto.isAgregado()) {
              
           inicializarGestorModeloTabla();
              
        }
 
           
    }//GEN-LAST:event_btnNuevoConceptoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       
           if (tblConcepto.getSelectedRow() != -1) {
            numeroSeleccion = sorter.convertRowIndexToModel(tblConcepto.getSelectedRow());
               if (i==Constantes.MODELO_TODOS_LOS_CONCEPTOS){
                   asociado = modeloasociado.getConcepto(numeroSeleccion);
               } else {
                   asociado = modeloasociado2.getConcepto(numeroSeleccion);
               }
            
            // abrir el formulario alta de persona para editar los datos de persona
            GUIConceptos guiConceptos = new GUIConceptos(null, true, asociado);
            // actulizar la tabla con los datos modificados
            if (guiConceptos.isAgregado()|| guiConceptos.isEliminado()) {
                guiConceptos.dispose();
                if (i==0) {
                  inicializarTabla();
              } else {
                  inicializarTabla2();
              }
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
       }
           
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed
        public void filtro(JTextField caja){
          if (tblConcepto.getModel().getRowCount() != 0) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + caja.getText() + ".*"));
            tblConcepto.setRowSorter(sorter);

    }
    }
        
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed

        filtro(txtCodigo);
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        filtro(txtDescripcion);
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        int fila = tblConcepto.getSelectedRow();
        if (tblConcepto.getSelectedRow() != -1) {
              numeroSeleccion = sorter.convertRowIndexToModel(tblConcepto.getSelectedRow());
             asociado = modeloasociado2.getConcepto(fila);
             System.out.println("concepto "+asociado.getDescripcion() );
            setAgregado(true);
        }else{
           JOptionPane.showMessageDialog(this, "Seleccione una fila");
           setAgregado(false); 
        }
        
        this.dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

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
            java.util.logging.Logger.getLogger(GUIGestordeConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIGestordeConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIGestordeConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIGestordeConcepto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIGestordeConcepto dialog = new GUIGestordeConcepto(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonIpod btnCancelar;
    private org.edisoncor.gui.button.ButtonIpod btnEditar;
    private org.edisoncor.gui.button.ButtonIpod btnNuevoConcepto;
    private org.edisoncor.gui.button.ButtonIpod btnSeleccionar;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private org.edisoncor.gui.label.LabelMetric labelCodigo;
    private org.edisoncor.gui.label.LabelMetric labelDescripcion;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTable tblConcepto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}

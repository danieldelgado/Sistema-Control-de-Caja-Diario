/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tecnomati.java.controlcaja.vista;

import co.tecnomati.java.controlcaja.cons.Constantes;
import co.tecnomati.java.controlcaja.dominio.Cooperativa;
import co.tecnomati.java.controlcaja.dominio.dao.imp.CooperativaDaoImp;
import java.awt.Color;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Joel
 */
public class GUIDatosCooperativa extends javax.swing.JDialog {

    Cooperativa cooperativa=null;
    boolean operacionOk = false; // este es una variable que se pone en verdadero si se guardo con exito los datos 
    private boolean modificar=false;
    javax.swing.ImageIcon icono= new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/ErrorX.png"));
    javax.swing.ImageIcon iconoGuardar= new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/GUARDAR.jpg"));
    javax.swing.ImageIcon iconoModificar= new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/pencil_48.png"));
    /**
     * Creates new form GUIDatosCooperativa
     */
    public boolean isOperacionOk() {
        return operacionOk;
    }
    
    public GUIDatosCooperativa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        List<Cooperativa> lista = new CooperativaDaoImp().listarCooperativa();
        if (!lista.isEmpty()) {
            cooperativa = lista.get(0);
            setDatos(cooperativa);
            modificar = true;
            // cambiar las etiquetas para que mensionen q se hara una actualizacion de datos
            btnGuardar.setText("Modificar");            
            btnGuardar.setIcon(iconoModificar);            
            this.setTitle("Actualizar Datos de la Persona");
        }else{
            modificar = false;
            btnGuardar.setText("Guardar");            
            btnGuardar.setIcon(iconoGuardar);
        }            
        
        this.setTitle(Constantes.NAME_REGISTRO_DATOS_COOPERATIVA);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
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
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        txtNombre = new org.edisoncor.gui.textField.TextField();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        txtMatricula = new org.edisoncor.gui.textField.TextField();
        labelMetric3 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric4 = new org.edisoncor.gui.label.LabelMetric();
        txtDomicilio = new org.edisoncor.gui.textField.TextField();
        btnGuardar = new org.edisoncor.gui.button.ButtonIpod();
        btnCancelar = new org.edisoncor.gui.button.ButtonIpod();
        labelMetric5 = new org.edisoncor.gui.label.LabelMetric();
        txtIngresoBruto = new org.edisoncor.gui.textField.TextField();
        labelMetric6 = new org.edisoncor.gui.label.LabelMetric();
        dateIncioActividad = new com.toedter.calendar.JDateChooser();
        txtTelefono = new org.edisoncor.gui.textField.TextField();
        labelMetric7 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric8 = new org.edisoncor.gui.label.LabelMetric();
        txtInicioCaja = new org.edisoncor.gui.textField.TextField();
        lblImgNombre = new javax.swing.JLabel();
        lblImgMatricula = new javax.swing.JLabel();
        lblImgCuit = new javax.swing.JLabel();
        lblImgIngBruto = new javax.swing.JLabel();
        lblImgInicioCaja = new javax.swing.JLabel();
        lblImgInicioActividad = new javax.swing.JLabel();
        lblImgDomicilio = new javax.swing.JLabel();
        txtCuit = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(537, 417));
        setMinimumSize(new java.awt.Dimension(537, 417));
        setResizable(false);

        labelMetric1.setText("NOMBRE");
        labelMetric1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        labelMetric2.setText("MATRICULA");
        labelMetric2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatriculaMouseClicked(evt);
            }
        });
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyTyped(evt);
            }
        });

        labelMetric3.setText("CUIT");
        labelMetric3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        labelMetric4.setText("DOMICILIO");
        labelMetric4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });
        txtDomicilio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDomicilioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDomicilioKeyTyped(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/Atras.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        labelMetric5.setText("INGRESO BRUTOS");
        labelMetric5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtIngresoBruto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIngresoBrutoMouseClicked(evt);
            }
        });
        txtIngresoBruto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresoBrutoActionPerformed(evt);
            }
        });
        txtIngresoBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIngresoBrutoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresoBrutoKeyTyped(evt);
            }
        });

        labelMetric6.setText("INICIO DE ACTIVIDAD");
        labelMetric6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        dateIncioActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateIncioActividadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dateIncioActividadKeyTyped(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        labelMetric7.setText("TELEFONO");
        labelMetric7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        labelMetric8.setText("INICIO DE CAJA");
        labelMetric8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtInicioCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInicioCajaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInicioCajaKeyTyped(evt);
            }
        });

        lblImgNombre.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgNombre.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgNombre.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgMatricula.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgMatricula.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgMatricula.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgCuit.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgCuit.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgCuit.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgIngBruto.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgIngBruto.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgIngBruto.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgInicioCaja.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgInicioCaja.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgInicioCaja.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgInicioActividad.setMaximumSize(new java.awt.Dimension(25, 25));
        lblImgInicioActividad.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgInicioActividad.setPreferredSize(new java.awt.Dimension(25, 25));

        lblImgDomicilio.setMinimumSize(new java.awt.Dimension(25, 25));
        lblImgDomicilio.setPreferredSize(new java.awt.Dimension(25, 25));

        txtCuit.setBackground(new java.awt.Color(204, 204, 204));
        try {
            txtCuit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCuit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuitKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelMetric5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMetric1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateIncioActividad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInicioCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(txtIngresoBruto, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(txtCuit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImgMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgIngBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgInicioCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgInicioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblImgNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblImgMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblImgCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIngresoBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgIngBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImgInicioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateIncioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInicioCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImgInicioCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblImgDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMetric4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
     this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtIngresoBrutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresoBrutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoBrutoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
            if (validacionGUICooperativa()) {                
                labelGUICooperativa();
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos que son Obligatorios.");

            } else {
                if (cooperativa==null) {
                    System.out.println("entro nuevo");
                    cooperativa= new Cooperativa();
                    getDatos();       
                    new CooperativaDaoImp().addCooperativa(cooperativa);
                    JOptionPane.showMessageDialog(null, "Los datos fueron cargados correctamente.");
                    this.dispose();
                } else {                       
                    System.out.println("entro a modificar");
                getDatos();
                new CooperativaDaoImp().upDateCooperativa(cooperativa);
                JOptionPane.showMessageDialog(null, "Los datos fueron modificados correctamente.");
                this.dispose();

                }                        
            }
                         
        operacionOk= true;
        //this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
       if(txtNombre.getText().length()>=25){
            evt.consume();
       }
//        
//       char TipoDeTecla = evt.getKeyChar();
//       if(Character.isDigit(TipoDeTecla)){
//            evt.consume();
//       }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyTyped
        permitirSoloNumero(evt);
                if(txtMatricula.getText().length()>=10){
            evt.consume();
        }
    }//GEN-LAST:event_txtMatriculaKeyTyped

    private void txtIngresoBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoBrutoKeyTyped
        //permitirSoloNumero(evt);
                if(txtIngresoBruto.getText().length()>=12){
            evt.consume();
        }
    }//GEN-LAST:event_txtIngresoBrutoKeyTyped

    private void txtDomicilioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioKeyTyped
        if(txtDomicilio.getText().length()>=12){
            evt.consume();
        }        
//        char TipoDeTecla = evt.getKeyChar();
//        if(Character.isDigit(TipoDeTecla)){
//            evt.consume();
//        }
    }//GEN-LAST:event_txtDomicilioKeyTyped

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost

    }//GEN-LAST:event_txtNombreFocusLost

    private void txtInicioCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInicioCajaKeyTyped
     
        permitirSoloNumero(evt);
                if(txtInicioCaja.getText().length()>=12){
            evt.consume();
        }
    }//GEN-LAST:event_txtInicioCajaKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        permitirSoloNumero(evt);
                if(txtTelefono.getText().length()>=12){
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked
    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatriculaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatriculaMouseClicked

    private void txtIngresoBrutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIngresoBrutoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoBrutoMouseClicked

    private void txtInicioCajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInicioCajaKeyPressed
       // esto es para cuando ingrese al campo se vorre el icono
        lblImgInicioCaja.setIcon(null);
    }//GEN-LAST:event_txtInicioCajaKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        lblImgNombre.setIcon(null);
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtMatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyPressed
        lblImgMatricula.setIcon(null);
    }//GEN-LAST:event_txtMatriculaKeyPressed

    private void txtIngresoBrutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoBrutoKeyPressed
        lblImgIngBruto.setIcon(null);
    }//GEN-LAST:event_txtIngresoBrutoKeyPressed

    private void dateIncioActividadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateIncioActividadKeyPressed
        lblImgInicioActividad.setIcon(null);
    }//GEN-LAST:event_dateIncioActividadKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtDomicilioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioKeyPressed
        lblImgDomicilio.setIcon(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioKeyPressed

    private void dateIncioActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateIncioActividadKeyTyped
        lblImgInicioActividad.setIcon(null);        // TODO add your handling code here:
    }//GEN-LAST:event_dateIncioActividadKeyTyped

    private void txtDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioActionPerformed

    private void txtCuitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyPressed

    }//GEN-LAST:event_txtCuitKeyPressed

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped

    }//GEN-LAST:event_txtCuitKeyTyped

    public void setDatos(Cooperativa cooperativa){
        txtNombre.setText(cooperativa.getRazonSocial());
        txtMatricula.setText(String.valueOf(cooperativa.getMatricula()));
        txtCuit.setText(cooperativa.getCuit());
        txtIngresoBruto.setText(cooperativa.getIngresoBruto());
        dateIncioActividad.setDate(cooperativa.getInicioActividad());
        txtInicioCaja.setText(String.valueOf(cooperativa.getInicioCaja()));
        txtTelefono.setText(cooperativa.getTelefono());
        txtDomicilio.setText(cooperativa.getDomicilio());
    }
    public void getDatos(){
        cooperativa.setRazonSocial(txtNombre.getText());
        cooperativa.setMatricula(Integer.parseInt(txtMatricula.getText()));
        cooperativa.setCuit(txtCuit.getText());
        cooperativa.setIngresoBruto(txtIngresoBruto.getText());
        cooperativa.setInicioActividad(dateIncioActividad.getDate());
        cooperativa.setInicioCaja(Double.parseDouble(txtInicioCaja.getText()));
        cooperativa.setTelefono(txtTelefono.getText());
        cooperativa.setDomicilio(txtDomicilio.getText());
    }
    
    /**
     * Metodo de Validacion de JTextField de Dtos de Cooperativa.
     */
    public boolean validacionGUICooperativa(){
        boolean vacio = true;        
        ArrayList<JTextField> listaTxt = new ArrayList();
        listaTxt.add(txtNombre);
        listaTxt.add(txtMatricula); listaTxt.add(txtCuit);
        listaTxt.add(txtIngresoBruto);listaTxt.add(txtInicioCaja);
        listaTxt.add(txtDomicilio);
        for (Iterator<JTextField> it = listaTxt.iterator(); it.hasNext();) {
            JTextField jTextField = it.next();
                if (jTextField.getText().equals("") || dateIncioActividad.getDate() == null) {                    
                    vacio = true;
                    break;
                }else{
                    vacio = false;
                }                             
        }
        if (txtCuit.getText().replaceAll("[.-]", "").trim().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }
    
    public boolean validacionModificacionGUICooperativa(){
        boolean vacio = true;        
        ArrayList<JTextField> listaTxte = new ArrayList();
        listaTxte.add(txtNombre);
        listaTxte.add(txtMatricula); listaTxte.add(txtCuit);
        listaTxte.add(txtIngresoBruto);listaTxte.add(txtInicioCaja);
        listaTxte.add(txtDomicilio);
        for (Iterator<JTextField> it = listaTxte.iterator(); it.hasNext();) {
            JTextField jTextField = it.next();
                if (jTextField.getText().equals("") || dateIncioActividad.getDate() == null) {                    
                    vacio = true;
                }else{
                    vacio = false;
                }                             
        }        
        return vacio;
    }

    /**
     * Metodo de Validacion de JTextField de Dtos de Cooperativa.
     */
    public void labelGUICooperativa(){
        if (txtNombre.getText().equals("")) {
            lblImgNombre.setIcon(icono);
        } 
        if (txtMatricula.getText().equals("")) {
            lblImgMatricula.setIcon(icono);
        }
        if (txtCuit.getText().equals("")) {
            lblImgCuit.setIcon(icono);
        }
        if (txtInicioCaja.getText().equals("")) {
            lblImgInicioCaja.setIcon(icono);
        }
        if (txtIngresoBruto.getText().equals("")) {
            lblImgIngBruto.setIcon(icono);
        }
        if (txtDomicilio.getText().equals("")) {
            lblImgDomicilio.setIcon(icono);
        } 
        if (dateIncioActividad.getDate() == null) {
            lblImgInicioActividad.setIcon(icono);
        }                 
    }
    

    private void permitirSoloNumero(java.awt.event.KeyEvent evt) {
        // permitir solo el ingreso de numero
        char caracter = evt.getKeyChar();
        if(((caracter < '0') ||
            (caracter > '9')) &&
            (caracter != '\b' /*corresponde a BACK_SPACE*/))
        {
             evt.consume();  // ignorar el evento de teclado
        }
       
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
            java.util.logging.Logger.getLogger(GUIDatosCooperativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDatosCooperativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDatosCooperativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDatosCooperativa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIDatosCooperativa dialog = new GUIDatosCooperativa(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonIpod btnGuardar;
    private com.toedter.calendar.JDateChooser dateIncioActividad;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private org.edisoncor.gui.label.LabelMetric labelMetric3;
    private org.edisoncor.gui.label.LabelMetric labelMetric4;
    private org.edisoncor.gui.label.LabelMetric labelMetric5;
    private org.edisoncor.gui.label.LabelMetric labelMetric6;
    private org.edisoncor.gui.label.LabelMetric labelMetric7;
    private org.edisoncor.gui.label.LabelMetric labelMetric8;
    private javax.swing.JLabel lblImgCuit;
    private javax.swing.JLabel lblImgDomicilio;
    private javax.swing.JLabel lblImgIngBruto;
    private javax.swing.JLabel lblImgInicioActividad;
    private javax.swing.JLabel lblImgInicioCaja;
    private javax.swing.JLabel lblImgMatricula;
    private javax.swing.JLabel lblImgNombre;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JFormattedTextField txtCuit;
    private org.edisoncor.gui.textField.TextField txtDomicilio;
    private org.edisoncor.gui.textField.TextField txtIngresoBruto;
    private org.edisoncor.gui.textField.TextField txtInicioCaja;
    private org.edisoncor.gui.textField.TextField txtMatricula;
    private org.edisoncor.gui.textField.TextField txtNombre;
    private org.edisoncor.gui.textField.TextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

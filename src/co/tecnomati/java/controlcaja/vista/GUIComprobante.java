/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





//    private void txtCuitKeyPressed(java.awt.event.KeyEvent evt) {                                   
//        if (evt.getKeyCode() == KeyEvent.VK_F1) {
//            buscarEntidad();
//        }
//    }                                  
//
//    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {                                 
//        MyUtil.consumirLetras(evt, txtCodigoConcepto, 0);
//    }                                

package co.tecnomati.java.controlcaja.vista;

import co.tecnomati.java.controlcaja.cons.Constantes;
import co.tecnomati.java.controlcaja.dominio.Asociado;
import co.tecnomati.java.controlcaja.dominio.Cliente;
import co.tecnomati.java.controlcaja.dominio.Concepto;

import co.tecnomati.java.controlcaja.dominio.Comprobante;
import co.tecnomati.java.controlcaja.dominio.Comprobanteconcepto;
import co.tecnomati.java.controlcaja.dominio.Proveedor;
import co.tecnomati.java.controlcaja.dominio.Tipocomprobante;
import co.tecnomati.java.controlcaja.dominio.dao.imp.AsociadoDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.ClienteDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.ComprobanteDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.ComprobanteconceptoDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.ConceptoDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.ProveedorDaoImp;
import co.tecnomati.java.controlcaja.dominio.dao.imp.TipoComprobanteDaoImp;
import co.tecnomati.java.controlcaja.util.ComprobanteUtil;
import co.tecnomati.java.controlcaja.util.Entidad;
import co.tecnomati.java.controlcaja.util.Impresora;
import co.tecnomati.java.controlcaja.util.MyUtil;
import co.tecnomati.java.controlcaja.util.mensajero;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import org.jfree.data.time.Month;

/**
 *
 * @author Joel
 */
public class GUIComprobante extends javax.swing.JDialog {

    // esto es lo basico que debe tener una ventana de carga de datos
    private Comprobante comprobante; // comprobante que sera guardado o modificado
    private boolean modificar; // indicador de si uso esta ventana para modificar o crear 
    private boolean agregado; // indicador para saber si el usuario realizo la operacion de  carga o actualizacion de los datos del comprobante
    private boolean elimiado;
    // clase auxiliar en la que almacena temporalmente un Asociado, Cliente o Proveedor
    Entidad entidad;
    // Son elemementos que conforman un comprobante
    private Concepto concepto = null;
    private Tipocomprobante tipoComprobante = null;
    Set<Comprobanteconcepto> conjuntoConceptos = null;
    Comprobanteconcepto comprobanteconcepto = null;
    // guarda la numeracion del numerod de serie del comprobante
    private long numIzq;
    private long numDer;
    private String refComprobante;
    private boolean visualizar = false;
    // almacena el valor del monotributo en caso de recien creado su valor es 0
    private double monotributo;
    private Double monto;
    String formatoValor="0.00";
    
    public boolean isElimiado() {
        return elimiado;
    }

    public void setElimiado(boolean elimiado) {
        this.elimiado = elimiado;
    }

    /**
     * Constructor usado para crear un Comprobante
     */
    public GUIComprobante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        newComprobante();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * Constructor usado para Editar un comprobante
     *
     * @param parent
     * @param modal
     * @param compr El comprobante que envio para q se desplegue en el
     * formulario para su edicion
     */
    public GUIComprobante(java.awt.Frame parent, boolean modal, Comprobante compr) {
        super(parent, modal);
        initComponents();
        modificar = true;
        visualizar = true;
        this.comprobante = compr;

        setDatos();
        numIzq = comprobante.getNumeroSerieIzq();
        numDer = comprobante.getNumeroSerieDer();

        controlarTipoOperacion();

        controlarTipodeComprobante();

        ControlarEditableNumeroSerie();
        setEnabledBotonImprimir(tipoComprobante.getCodigo());
        controlarPanelMonotributo();

        setEditableComprobante(false);
        txtnumSerie1.setEnabled(true);
        txtNumSerie2.setEnabled(true);
        configuarBotonEditarDesdeGestor();


//        setDatosNombreEntidad();
        this.setTitle(Constantes.NAME_NUEVO_REGISTRO);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void configuarBotonEditarDesdeGestor() {
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(false);
    }

    /**
     * Este controla que los botones
     */
    public void activarBotonesParaNuevo() {
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnImprimi.setEnabled(false);
        btnNuevo.setEnabled(false);





    }

    /**
     * Solo usar para crear un nuevo comprobante controla de q si se ingresa un
     * recibo automatico , este debe generar automaticamente su concetpo
     * asociado Ej . si se elige un recibo anticipo de retorno como tipo de
     * comporobante , entonces en el camppo concepto deberia completarse
     * autmaticamente el concepto anticipo retorno
     */
    public void controlarConcepto() {
        switch (tipoComprobante.getCodigo()) {
            case Constantes.CODIGO_RECIBO_ANTICIPO_RETORNO:
                System.out.println("boleta");
                if (!modificar) {
                    // nuevo entnoces traigo su concetpo asociado
                    concepto = new ConceptoDaoImp().getConcepto(Constantes.CONCEPTO_CODIGO_ANTICIPO_RETORNO);
                    cargarConceptoAutomatico(concepto);

                }

                break;
            case Constantes.CODIGO_RECIBO_DISTRIBUCION_EXCEDENTE:
                System.out.println("");
                if (!modificar) {
                    // nuevo entnoces traigo su concetpo asociado
                    concepto = new ConceptoDaoImp().getConcepto(Constantes.CONCEPTO_CODIGO_DISTRIBUCION_EXCEDENTE);
                    cargarConceptoAutomatico(concepto);

                }
                break;
            case Constantes.CODIGO_RECIBO_INTEGRACION_CUOTA:
                System.out.println("");
                if (!modificar) {
                    // nuevo entnoces traigo su concetpo asociado
                    concepto = new ConceptoDaoImp().getConcepto(Constantes.CONCEPTO_CODIGO_INTEGRACION_CUOTA);
                    cargarConceptoAutomatico(concepto);

                }
                break;
            case Constantes.CODIGO_RECIBO_REEMBOLSO_CUOTA_SOCIALES:
                System.out.println("");
                if (!modificar) {
                    // nuevo entnoces traigo su concetpo asociado
                    concepto = new ConceptoDaoImp().getConcepto(Constantes.CONCEPTO_CODIGO_REEMBOLSO_CUOTA_SOCIAL);
                    cargarConceptoAutomatico(concepto);
                }
                break;
            default:
                txtCodigoConcepto.setText("");
                txtCodigoConcepto.setEditable(true);
                txtDescripcionConcepto.setText("");
                txtDescripcionConcepto.setEditable(false);
                break;
        }

    }

    /**
     * cargar el concepto en el formulario si el tipo de comprobante es un
     * recibo: rar,ric,rrc,rde
     *
     * @param concepto
     */
    private void cargarConceptoAutomatico(Concepto concepto) {
        // aqi se setea el campo concepto
        txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
        txtCodigoConcepto.setEditable(false);
        txtDescripcionConcepto.setText(concepto.getDescripcion());
        txtDescripcionConcepto.setEditable(false);
    }

    /**
     * Cuando se utilize para editar el comprobante , No se puede editar el
     * campo Tipo de Comprobante.
     *
     */
    public void controlarTipodeComprobante() {
        txtRefTipoCompr.setEditable(false);
    }

    /**
     * Carga en la ventana la informacion del Comprobante
     */
    public void setDatos() {
        // cargos los datos para editar
        dateComprobante.setDate(comprobante.getFecha());
        cmbTipoProceso.setSelectedIndex(comprobante.getTipoProceso());
        txtnumSerie1.setText(ComprobanteUtil.formatearNumSerieIzq(comprobante.getNumeroSerieIzq()));
        txtNumSerie2.setText(ComprobanteUtil.formatearNumSerieDer(comprobante.getNumeroSerieDer()));

        // entidad
        entidad = new Entidad();
        entidad.setId(comprobante.getIdEntidad());
        entidad.setTipoEntidad(comprobante.getTipoPersona());

        //tipo de comprobante
        tipoComprobante = comprobante.getTipocomprobante();
//        tipoComprobante = new ComprobanteDaoImp().getTipocomprobante(comprobante.getId());
        txtTipoComprobante.setText(tipoComprobante.getFormulario());
        txtRefTipoCompr.setText(tipoComprobante.getReferencia());

        //Conceptoss
        conjuntoConceptos = comprobante.getComprobanteconceptos();

        System.out.println("Tamaño del conjunto" + conjuntoConceptos.size());

        for (Iterator<Comprobanteconcepto> it = conjuntoConceptos.iterator(); it.hasNext();) {
            comprobanteconcepto = it.next();

            if (comprobanteconcepto.getConcepto().getCodigoConcepto() != Constantes.CONCEPTO_CODIGO_MONOTRIBUTO) {
                System.out.println("elemento nro 1 del conjunto" + comprobanteconcepto.getConcepto().getCodigoConcepto() + Constantes.CONCEPTO_CODIGO_ANTICIPO_RETORNO);
                concepto = comprobanteconcepto.getConcepto();
                System.out.println("conceptos guardado ahoara esta para editar" + concepto.getDescripcion());
                txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                txtDescripcionConcepto.setText(concepto.getDescripcion());
                txtMonto.setText(String.valueOf(comprobanteconcepto.getMonto()));
            } else {
                System.out.println("elemento nro 2 del conjunto");

                // existe el aporte de monotribbuto ademas
                monotributo = MyUtil.convertirPositivoDouble(comprobanteconcepto.getMonto());

                txtAporteMonotributo.setText(String.valueOf(monotributo));// lo multiplico para que aparesca positivo
                chkAporteMonotributo.setSelected(true);


            }



            switch (comprobante.getTipoPersona()) {

                case Constantes.ASOCIADO_INT:
                    Asociado a = new AsociadoDaoImp().getAsociado(comprobante.getIdEntidad());
                    txtCuit.setText(String.valueOf(a.getCuit()));
                    txtNombre.setText(a.getApellido() + " " + a.getNombre());
                    break;
                case Constantes.PROVEEDOR_INT:
                    Proveedor p = new ProveedorDaoImp().getProveedor(comprobante.getIdEntidad());
                    txtCuit.setText(String.valueOf(p.getCuit()));
                    txtNombre.setText(p.getRazonSocial());
                    break;
                case Constantes.CLIENTE_INT:
                    Cliente c = new ClienteDaoImp().getCliente(comprobante.getIdEntidad());
                    txtCuit.setText(String.valueOf(c.getCuit()));
                    txtNombre.setText(c.getRazonSocial());
                    break;
            }
        }


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
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        panelComprobante = new org.edisoncor.gui.panel.Panel();
        labelMetric4 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric5 = new org.edisoncor.gui.label.LabelMetric();
        txtnumSerie1 = new org.edisoncor.gui.textField.TextField();
        labelMetric3 = new org.edisoncor.gui.label.LabelMetric();
        cmbTipoProceso = new org.edisoncor.gui.comboBox.ComboBoxRect();
        txtNumSerie2 = new org.edisoncor.gui.textField.TextField();
        txtTipoComprobante = new org.edisoncor.gui.textField.TextField();
        txtRefTipoCompr = new org.edisoncor.gui.textField.TextField();
        panelEntidad = new org.edisoncor.gui.panel.Panel();
        labelMetric9 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric8 = new org.edisoncor.gui.label.LabelMetric();
        btnBuscarEntidad = new org.edisoncor.gui.button.ButtonIpod();
        txtNombre = new org.edisoncor.gui.textField.TextField();
        txtCuit = new org.edisoncor.gui.textField.TextField();
        panelConcepto = new org.edisoncor.gui.panel.Panel();
        labelMetric6 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric7 = new org.edisoncor.gui.label.LabelMetric();
        txtCodigoConcepto = new org.edisoncor.gui.textField.TextField();
        txtMonto = new org.edisoncor.gui.textField.TextField();
        labelMetric10 = new org.edisoncor.gui.label.LabelMetric();
        txtDescripcionConcepto = new org.edisoncor.gui.textField.TextField();
        dateComprobante = new com.toedter.calendar.JDateChooser();
        panelContendedorPanelMonotrib = new org.edisoncor.gui.panel.Panel();
        panelMonotributo = new org.edisoncor.gui.panel.Panel();
        chkAporteMonotributo = new javax.swing.JCheckBox();
        txtAporteMonotributo = new org.edisoncor.gui.textField.TextField();
        panelMonotributo1 = new org.edisoncor.gui.panel.Panel();
        btnImprimi = new org.edisoncor.gui.button.ButtonIpod();
        btnGuardar = new org.edisoncor.gui.button.ButtonIpod();
        btnNuevo = new org.edisoncor.gui.button.ButtonIpod();
        btnEditar = new org.edisoncor.gui.button.ButtonIpod();
        btnEliminar = new org.edisoncor.gui.button.ButtonIpod();
        btnCancelar = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 102, 102)));
        panel1.setColorPrimario(new java.awt.Color(0, 0, 0));
        panel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panel1KeyPressed(evt);
            }
        });

        labelMetric2.setText("FECHA");
        labelMetric2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        panelComprobante.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelComprobante.setForeground(new java.awt.Color(255, 255, 255));
        panelComprobante.setColorPrimario(new java.awt.Color(0, 0, 0));

        labelMetric4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMetric4.setText("TIPO DE COMPROB");
        labelMetric4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        labelMetric5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMetric5.setText("      NUMERO-SERIE");
        labelMetric5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtnumSerie1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnumSerie1.setEnabled(false);
        txtnumSerie1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtnumSerie1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnumSerie1FocusLost(evt);
            }
        });
        txtnumSerie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumSerie1KeyTyped(evt);
            }
        });

        labelMetric3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMetric3.setText("  OPERACION");
        labelMetric3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        cmbTipoProceso.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        cmbTipoProceso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "ENTRADA", "SALIDA" }));
        cmbTipoProceso.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbTipoProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoProcesoActionPerformed(evt);
            }
        });
        cmbTipoProceso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoProcesoKeyPressed(evt);
            }
        });

        txtNumSerie2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumSerie2.setEnabled(false);
        txtNumSerie2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNumSerie2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumSerie2FocusLost(evt);
            }
        });
        txtNumSerie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumSerie2KeyTyped(evt);
            }
        });

        txtTipoComprobante.setEditable(false);
        txtTipoComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoComprobanteKeyPressed(evt);
            }
        });

        txtRefTipoCompr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRefTipoCompr.setText("a");
        txtRefTipoCompr.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtRefTipoCompr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRefTipoComprFocusGained(evt);
            }
        });
        txtRefTipoCompr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRefTipoComprKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRefTipoComprKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelComprobanteLayout = new javax.swing.GroupLayout(panelComprobante);
        panelComprobante.setLayout(panelComprobanteLayout);
        panelComprobanteLayout.setHorizontalGroup(
            panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComprobanteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMetric5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprobanteLayout.createSequentialGroup()
                        .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnumSerie1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(txtRefTipoCompr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelComprobanteLayout.createSequentialGroup()
                                .addComponent(txtNumSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(cmbTipoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelComprobanteLayout.setVerticalGroup(
            panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComprobanteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRefTipoCompr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTipoComprobante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelEntidad.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelEntidad.setColorPrimario(new java.awt.Color(0, 0, 0));

        labelMetric9.setText("RAZON SOCIAL:    ");
        labelMetric9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        labelMetric8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMetric8.setText("CUIT");
        labelMetric8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnBuscarEntidad.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 102)));
        btnBuscarEntidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/Profile.png"))); // NOI18N
        btnBuscarEntidad.setText("Buscar");
        btnBuscarEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEntidadActionPerformed(evt);
            }
        });
        btnBuscarEntidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarEntidadKeyPressed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtCuit.setEditable(false);
        txtCuit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuitKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelEntidadLayout = new javax.swing.GroupLayout(panelEntidad);
        panelEntidad.setLayout(panelEntidadLayout);
        panelEntidadLayout.setHorizontalGroup(
            panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntidadLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMetric9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEntidadLayout.createSequentialGroup()
                        .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEntidadLayout.setVerticalGroup(
            panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntidadLayout.createSequentialGroup()
                .addGroup(panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEntidadLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMetric8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntidadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscarEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGroup(panelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelConcepto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelConcepto.setColorPrimario(new java.awt.Color(0, 0, 0));

        labelMetric6.setText("COD CONCEPTO");
        labelMetric6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        labelMetric7.setText("MONTO $");
        labelMetric7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtCodigoConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoConcepto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCodigoConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoConceptoActionPerformed(evt);
            }
        });
        txtCodigoConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoConceptoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoConceptoKeyTyped(evt);
            }
        });

        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setText("0.00");
        txtMonto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtMonto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMontoFocusLost(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        labelMetric10.setText("CONCEPTO");
        labelMetric10.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtDescripcionConcepto.setEditable(false);
        txtDescripcionConcepto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout panelConceptoLayout = new javax.swing.GroupLayout(panelConcepto);
        panelConcepto.setLayout(panelConceptoLayout);
        panelConceptoLayout.setHorizontalGroup(
            panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConceptoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMetric10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelConceptoLayout.createSequentialGroup()
                        .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelConceptoLayout.setVerticalGroup(
            panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConceptoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConceptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(207, 207, 207))
        );

        dateComprobante.setDate(new Date());
        dateComprobante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateComprobante.setMaxSelectableDate(new Date());

        panelContendedorPanelMonotrib.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelContendedorPanelMonotrib.setColorPrimario(new java.awt.Color(0, 0, 0));
        panelContendedorPanelMonotrib.setMaximumSize(new java.awt.Dimension(426, 84));
        panelContendedorPanelMonotrib.setMinimumSize(new java.awt.Dimension(426, 84));

        panelMonotributo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelMonotributo.setColorPrimario(new java.awt.Color(0, 0, 0));
        panelMonotributo.setEnabled(false);
        panelMonotributo.setMaximumSize(new java.awt.Dimension(414, 62));
        panelMonotributo.setMinimumSize(new java.awt.Dimension(414, 62));

        chkAporteMonotributo.setBackground(new java.awt.Color(0, 0, 0));
        chkAporteMonotributo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        chkAporteMonotributo.setForeground(new java.awt.Color(255, 255, 255));
        chkAporteMonotributo.setText("Aporte de Monotributo: $");
        chkAporteMonotributo.setEnabled(false);
        chkAporteMonotributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAporteMonotributoActionPerformed(evt);
            }
        });

        txtAporteMonotributo.setEditable(false);
        txtAporteMonotributo.setBackground(new java.awt.Color(255, 255, 255));
        txtAporteMonotributo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAporteMonotributo.setText("0.00");
        txtAporteMonotributo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAporteMonotributo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtAporteMonotributo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtAporteMonotributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAporteMonotributoActionPerformed(evt);
            }
        });
        txtAporteMonotributo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAporteMonotributoFocusLost(evt);
            }
        });
        txtAporteMonotributo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAporteMonotributoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelMonotributoLayout = new javax.swing.GroupLayout(panelMonotributo);
        panelMonotributo.setLayout(panelMonotributoLayout);
        panelMonotributoLayout.setHorizontalGroup(
            panelMonotributoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonotributoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkAporteMonotributo)
                .addGap(18, 18, 18)
                .addComponent(txtAporteMonotributo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMonotributoLayout.setVerticalGroup(
            panelMonotributoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonotributoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelMonotributoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAporteMonotributo)
                    .addComponent(txtAporteMonotributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelMonotributo1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        panelMonotributo1.setColorPrimario(new java.awt.Color(0, 51, 51));
        panelMonotributo1.setEnabled(false);
        panelMonotributo1.setMaximumSize(new java.awt.Dimension(414, 62));
        panelMonotributo1.setMinimumSize(new java.awt.Dimension(414, 62));

        btnImprimi.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 102)));
        btnImprimi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/print_32.png"))); // NOI18N
        btnImprimi.setText("Imprimir");
        btnImprimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimiActionPerformed(evt);
            }
        });
        btnImprimi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnImprimiKeyPressed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/GUARDAR.jpg"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/document_32.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        btnNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNuevoKeyPressed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/pencil_48.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        btnEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEditarKeyPressed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/ELIMINAR3.jpg"))); // NOI18N
        btnEliminar.setText("Anular");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarKeyPressed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/tecnomati/java/controlcaja/imagen/Atras.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelMonotributo1Layout = new javax.swing.GroupLayout(panelMonotributo1);
        panelMonotributo1.setLayout(panelMonotributo1Layout);
        panelMonotributo1Layout.setHorizontalGroup(
            panelMonotributo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMonotributo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelMonotributo1Layout.setVerticalGroup(
            panelMonotributo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMonotributo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMonotributo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelContendedorPanelMonotribLayout = new javax.swing.GroupLayout(panelContendedorPanelMonotrib);
        panelContendedorPanelMonotrib.setLayout(panelContendedorPanelMonotribLayout);
        panelContendedorPanelMonotribLayout.setHorizontalGroup(
            panelContendedorPanelMonotribLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContendedorPanelMonotribLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContendedorPanelMonotribLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMonotributo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMonotributo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelContendedorPanelMonotribLayout.setVerticalGroup(
            panelContendedorPanelMonotribLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContendedorPanelMonotribLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMonotributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMonotributo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelContendedorPanelMonotrib, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelContendedorPanelMonotrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cmbTipoProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoProcesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoProcesoActionPerformed

    /**
     * Se encarga de verificar que los campos del comprobante se hayan cargados
     * si encuentra algun campo vacio te aparece un mje donde falta completar
     *
     * @return true si se cargo todos los campos false si falta llenar algun
     * campo.
     */
    public boolean validarCamposVacio() {
        boolean b = false;

        boolean bconcepto, bentidad, btipocomprobante, bnumeroSerie, bmonto, bmonotributo, btipoOperacion;
        btipocomprobante = tipoComprobante == null;
        bconcepto = concepto == null;
        bentidad = txtNombre.getText().trim().isEmpty();
        bnumeroSerie = txtnumSerie1.getText().trim().isEmpty() && txtNumSerie2.getText().trim().isEmpty();
        bmonto = txtMonto.getText().trim().isEmpty();
        bmonotributo = chkAporteMonotributo.isSelected() && txtAporteMonotributo.getText().trim().isEmpty();
        btipoOperacion = cmbTipoProceso.getSelectedIndex() == 0;
        //         System.out.println("El monto es "+ bmonto);
        if (!btipocomprobante && !bconcepto && !bentidad && !bentidad && !bnumeroSerie && !bmonto && !bmonotributo && !btipoOperacion) {
            b = true;
        } else {
            if (btipocomprobante) {
                mensajero.mensajeError(null, "Tipo comprobante no puede estar vacio");
            } else if (bnumeroSerie) {
                mensajero.mensajeError(null, "Numero de Serie no puede estar vacio");
            } else if (btipoOperacion) {
                mensajero.mensajeError(null, "Tipo Operacion no puede estar vacio");
            } else if (bentidad) {
                mensajero.mensajeError(null, "Entidad no puede estar vacio");
            } else if (bconcepto) {
                mensajero.mensajeError(null, "Concepto no puede estar vacio");
            } else if (bmonto) {
                mensajero.mensajeError(null, "Monto no puede estar vacio");
            } else if (bmonotributo) {
                mensajero.mensajeError(null, "Monotributo no puede estar vacio");
            }

        }


        return b;
    }

    /**
     * metodo que se encarga de realizar la accion del boton Guardar Comprobante
     */
    private void guardarComprobante() {
        try {
                       
            monotributo = -Double.parseDouble(txtAporteMonotributo.getText().trim());
           monto = MyUtil.Redondear(Double.parseDouble(txtMonto.getText()), 2);
            if (validarCamposVacio()) {
                
//                //////redondeo las cantidades monetarias y lo reflejo en la ventana para que lo sepa el usuario
//                monto = MyUtil.Redondear(Double.parseDouble(txtMonto.getText().concat("001")), 2);
//                txtMonto.setText(String.valueOf(monto));
//                
//                monotributo = MyUtil.Redondear(MyUtil.convertirPositivoDouble(monotributo), 2);
//                txtAporteMonotributo.setText(String.valueOf(monotributo));
//                ///////////////////////////////////
                if (MyUtil.isMenorOrIgual(Double.valueOf(txtAporteMonotributo.getText().trim()), monto)) {
                    //cargo los datos en el objeto comprobante
                    comprobante.setIdEntidad(entidad.getId());
                    comprobante.setTipoPersona(entidad.getTipoEntidad());
                    comprobante.setFecha(dateComprobante.getDate());
                    // aqui debo preguntar si es un numero de serie autogenerado o ingresasdo por el usuario
                    if (!isComprobanteParaBuscarConcepto(tipoComprobante) || isReciboPago(tipoComprobante)) {
                        comprobante.setNumeroSerieIzq(numIzq);
                        comprobante.setNumeroSerieDer(numDer);
                    } else {
                        if (txtnumSerie1.getText().trim().length() == 4 && txtNumSerie2.getText().trim().length() == 8) {

                            numIzq = Long.valueOf(txtnumSerie1.getText());
                            numDer = Long.valueOf(txtNumSerie2.getText());
                            comprobante.setNumeroSerieIzq(numIzq);
                            comprobante.setNumeroSerieDer(numDer);
                        }
                    }

                    // aqui deberia ir concatendado la ref+iz+der
                    String nserie = ComprobanteUtil.formatearNumSerieIzq(numIzq) + "-" + ComprobanteUtil.formatearNumSerieDer(numDer);
                    comprobante.setNumeroSerie(tipoComprobante.getReferencia() + nserie);
                    System.out.println(tipoComprobante.getReferencia() + numIzq + numDer);
                    // este atributo pasa a tipo comprobante
                    comprobante.setTipoProceso(cmbTipoProceso.getSelectedIndex());
                    comprobante.setTipocomprobante(tipoComprobante);

                    //realizo el almacenamiento o actualizacion de los datos segun corresponda
                    if (modificar) {
                        System.out.println("...Actualizando..");
                        //modificar
                        new ComprobanteDaoImp().upDateFormulario(comprobante);
                        //update para los conceptos del formulario
                        for (Iterator<Comprobanteconcepto> it = comprobante.getComprobanteconceptos().iterator(); it.hasNext();) {
                            comprobanteconcepto = it.next();

                            if (comprobanteconcepto.getConcepto().getCodigoConcepto() == Constantes.CONCEPTO_CODIGO_MONOTRIBUTO) {


                                comprobanteconcepto.setMonto(monotributo);
                                new ComprobanteconceptoDaoImp().upDateComprobanteconcepto(comprobanteconcepto);
                                System.out.println("Se actualizo el monotributo");

//                        if (chkAporteMonotributo.isSelected()) {
//                            System.out.println("monotributo chequeado");
//                            // actualiza
//                           monotributo=  - Double.parseDouble(txtAporteMonotributo.getText().trim());
//                          
//                            comprobanteconcepto.setMonto(monotributo);
//                            new ComprobanteconceptoDaoImp().upDateComprobanteconcepto(comprobanteconcepto);
//                            System.out.println("Se actualizo el monotributo");
//                        } else {
//                            // quiere decir que no aplicara monotributo , por ende debemos eliminarlo 
//                            new ComprobanteconceptoDaoImp().deleteComprobanteconcepto(comprobanteconcepto);
//                            System.out.println("Se borro el monotributo");

//                        }

                            } else {
                                // es otro concepto
                                comprobanteconcepto.setConcepto(new ConceptoDaoImp().getConcepto(Integer.parseInt(txtCodigoConcepto.getText())));
                                comprobanteconcepto.setMonto(monto);
                                new ComprobanteconceptoDaoImp().upDateComprobanteconcepto(comprobanteconcepto);
                                System.out.println("Se actualizo el concepto 1");
                            }
                        }


                    } else {
                        //el objeto entidad solo se cargara cuando es nuevo
                        comprobante.setEstado(true);
                        new ComprobanteDaoImp().addFormulario(comprobante);
                        System.out.println("SE guardo el comprobante");
                        // aqui debe cargar 1 o dos conceptos dependiendo si carga monotributo son dos
                        Comprobanteconcepto detalle = new Comprobanteconcepto();
                        detalle.setConcepto(concepto);
                        detalle.setComprobante(comprobante);
                        detalle.setMonto(monto);
                        new ComprobanteconceptoDaoImp().addComprobanteconcepto(detalle);
                        System.out.print("Se guardo el detalle 1");
                        // carga el segundo detalle si es un recibo anticipo de retorno
//                if (tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_ANTICIPO_RETORNO && chkAporteMonotributo.isSelected()) {
                        if (tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_ANTICIPO_RETORNO) {
//                    if (chkAporteMonotributo.isSelected()) {
//                        monotributo=  - Double.parseDouble(txtAporteMonotributo.getText().trim());
//                    } else {
//                        monotributo=  0;
//                    }
                            System.out.println(Constantes.CONCEPTO_DESCRIPCION_ANTICIPO_RETORNO);
                            Comprobanteconcepto detalleMonotributo = new Comprobanteconcepto();
                            detalleMonotributo.setConcepto(new ConceptoDaoImp().getConcepto(Constantes.CONCEPTO_CODIGO_MONOTRIBUTO));
                            detalleMonotributo.setComprobante(comprobante);
//                    monotributo=  - Double.parseDouble(txtAporteMonotributo.getText().trim());
                            detalleMonotributo.setMonto(monotributo);
                            new ComprobanteconceptoDaoImp().addComprobanteconcepto(detalleMonotributo);
                            System.out.print("Se guardo el detalle 2");

                        }
                        // si saliio todo ok entonces actualizo el contador de comprobante 
                        actualizarNumeroDeSerie(tipoComprobante);

                    }
                    agregado = true;

                    JOptionPane.showMessageDialog(null, "Se cargo correctamente...");
                    visualizar = true;
                    setEditableComprobante(false);
                    // botones que deben figurar cuando se guarda
                    btnEditar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnGuardar.setEnabled(false);
                    btnNuevo.setEnabled(true);
                    btnEditar.setEnabled(true);
                    System.out.println("monotributo luego de guardar: " + monotributo);
                    System.out.println("comparar 0 == -0.0 " + (monotributo == 0));
                    // 
                    setEnabledBotonImprimir(tipoComprobante.getCodigo());

                } else {
                    mensajero.mensajeError(null, "El aporte de monotributo no puede superar al monto del Anticipo de Retorno");
                }
            }

        } catch (java.lang.NumberFormatException eformatDouble) {
            mensajero.mensajeError(null, "Error en el formato al ingresar un cantidad monetaria, ingrese un valor con el formato 0.00 ");
        }

    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarComprobante();

    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     *
     * @param tc Tipo de comprobante que se utilizo Realiza la actualizacion del
     * numero de serie en la bd para el tipo de comprobante que se utilizo en la
     * carga del comprobante
     */
    public void actualizarNumeroDeSerie(Tipocomprobante tc) {
        if (tc.getFormulario().equals(Constantes.RECIBO_ANTICIPO_RETORNO)
                || tc.getFormulario().equals(Constantes.RECIBO_DISTRIBUCION_EXCEDENTE)
                || tc.getFormulario().equals(Constantes.RECIBO_INTEGRACION_CUOTA)
                || tc.getFormulario().equals(Constantes.RECIBO_PAGO)
                || tc.getFormulario().equals(Constantes.RECIBO_REEMBOLSO_CUOTA_SOCIAL)) {
            tc.setNumeroSerieIzq(numIzq);
            tc.setNumeroSerieDer(numDer);
            new TipoComprobanteDaoImp().upDateTipoFormulario(tc);

        }
    }

    public boolean isReciboPago(Tipocomprobante tc) {
        boolean b = false;
        if (tc.getFormulario().equals(Constantes.RECIBO_PAGO)) {
            b = true;
        }

        return b;

    }

    /**
     *
     * @param tc tipo de comprobant
     * @return si es un tipo de comprobante = anticipo retorno , distri,
     * integraci, cuota social devolber
     */
    public boolean isComprobanteParaBuscarConcepto(Tipocomprobante tc) {
        boolean b = true;
        try {
            if (tc.getFormulario().equals(Constantes.RECIBO_ANTICIPO_RETORNO)
                    || tc.getFormulario().equals(Constantes.RECIBO_DISTRIBUCION_EXCEDENTE)
                    || tc.getFormulario().equals(Constantes.RECIBO_INTEGRACION_CUOTA)
                    || tc.getFormulario().equals(Constantes.RECIBO_REEMBOLSO_CUOTA_SOCIAL)) {
                b = false;
                System.out.print("false");
            }

            System.out.print("true");
        } catch (java.lang.NullPointerException excNull) {
        }


        return b;
    }

    /**
     *
     * @param tipoComprobante entero que determina el tipo de comprobante
     * almacenado. Si el tipo de comprobante pertenece a uno de los 5 tipos de
     * recibo entonces se activa para que el usuario determine si desea imprimir
     */
    public void setEnabledBotonImprimir(int tipoComprobante) {
        if (tipoComprobante <= 5) {
            btnImprimi.setEnabled(true);
        }
    }

    public GUIComprobante() {
    }

    /**
     * i
     *
     * @param cod de concepto
     * @return true si el concepto no pertenece a un conceto=
     * monotributo,distribuion excedente, anticipo retrono, rec integrac de
     * cuota false de otro modo
     */
    public boolean isConceptoAutomatico(int cod) {
        boolean b = false;

        if (cod == Constantes.CONCEPTO_CODIGO_ANTICIPO_RETORNO
                || cod == Constantes.CONCEPTO_CODIGO_DISTRIBUCION_EXCEDENTE
                || cod == Constantes.CONCEPTO_CODIGO_INTEGRACION_CUOTA
                || cod == Constantes.CONCEPTO_CODIGO_MONOTRIBUTO
                || cod == Constantes.CONCEPTO_CODIGO_REEMBOLSO_CUOTA_SOCIAL) {

            b = true;
        }

        return b;
    }

    private void getProcessConcepto() {
        SNumeros(txtCodigoConcepto);

        try {
            int codigoConcepto = Integer.parseInt(txtCodigoConcepto.getText().trim());

//             System.out.println("codigo de concetpor "+ codigoConcepto);
//             System.out.println("tipode comprobante "+ tipoComprobante.getFormulario());
            if (isComprobanteParaBuscarConcepto(tipoComprobante)) {

                // no se debe buscar un concepto que corresponde alos comprobantes automaticos

//            if (!"".equals(codigoConcepto)) {
//                System.out.println("no es distinto de cero codigo concetp");
                concepto = new ConceptoDaoImp().getConcepto(codigoConcepto);
//                 System.out.println("concetor"+ concepto.getCodigoConcepto());
                if (concepto != null && !isConceptoAutomatico(codigoConcepto)) {
                    txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                    txtDescripcionConcepto.setText(concepto.getDescripcion());
                    txtMonto.requestFocus();
                } else {
                    System.out.println("salto bien el pescao");
                    // llama a la ayuda
                    GUIGestordeConcepto gestorConcepto = new GUIGestordeConcepto(null, true, Constantes.MODELO_FILTRO_CONCEPTOS);
                    // si eligio un concepto debe ser reflejado 
                    if (gestorConcepto.isAgregado()) {
                        concepto = gestorConcepto.getAsociado();
//                        concepto.toString();
                        txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                        txtDescripcionConcepto.setText(concepto.getDescripcion());
                        txtMonto.requestFocus();

                    }
                }
//            }
            } else {

                if (!"".equals(codigoConcepto)) {
                    concepto = new ConceptoDaoImp().getConcepto(codigoConcepto);
                    if (concepto != null) {
                        txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                        txtDescripcionConcepto.setText(concepto.getDescripcion());
                        txtMonto.requestFocus();
                    } else {
                        // llama a la ayuda
                        GUIGestordeConcepto gestorConcepto = new GUIGestordeConcepto(null, true);
                        // si eligio un concepto debe ser reflejado 
                        if (gestorConcepto.isAgregado()) {
                            concepto = gestorConcepto.getAsociado();
                            txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                            txtDescripcionConcepto.setText(concepto.getDescripcion());
                            txtMonto.requestFocus();

                        }
                    }
                }
            }

        } catch (java.lang.NumberFormatException excep) {
            if (tipoComprobante != null) {

                GUIGestordeConcepto gestorConcepto = new GUIGestordeConcepto(null, true, Constantes.MODELO_FILTRO_CONCEPTOS);
                // si eligio un concepto debe ser reflejado 
                if (gestorConcepto.isAgregado()) {
                    concepto = gestorConcepto.getAsociado();
//                        concepto.toString();
                    txtCodigoConcepto.setText(String.valueOf(concepto.getCodigoConcepto()));
                    txtDescripcionConcepto.setText(concepto.getDescripcion());
                    txtMonto.requestFocus();

                }

            }

        } catch (java.lang.NullPointerException exNull) {
            mensajero.mensajeError(null, "Debes elegir un tipo de comprobante para elegir un concepto");
        }


    }
    private void txtCodigoConceptoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoConceptoKeyPressed
        txtCodigoConcepto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, java.util.Collections.EMPTY_SET);
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            getProcessConcepto();

            // realiza el proceso para obtener el concepto
        }

    }//GEN-LAST:event_txtCodigoConceptoKeyPressed

    private void txtTipoComprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoComprobanteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            GUIGestorTipoComprobante guiGestorTipoFormulario = new GUIGestorTipoComprobante(null, true);
            if (guiGestorTipoFormulario.isAgregado()) {
                tipoComprobante = guiGestorTipoFormulario.getTipoComp();

                txtTipoComprobante.setText(tipoComprobante.getFormulario());
                txtRefTipoCompr.setText(tipoComprobante.getReferencia());
                // para que obtenga el foco 
                txtnumSerie1.requestFocus();
            }

        }
    }//GEN-LAST:event_txtTipoComprobanteKeyPressed

    /**
     * controla el cmbtipooperacion controla cuando se crea un nuevo comprobante
     * y cuando se modifica
     */
    public void controlarTipoOperacion() {
        if (modificar) {
            // control para editar
            cmbTipoProceso.setSelectedIndex(comprobante.getTipoProceso());
            cmbTipoProceso.setEnabled(false);
        } else {
            // control para nuevo
            if (tipoComprobante.getOperacion() == Constantes.OP_ENTRADA_SALIDA) {
                cmbTipoProceso.setSelectedIndex(0);
                cmbTipoProceso.setEnabled(true);
            } else {
                cmbTipoProceso.setEnabled(false);
                cmbTipoProceso.setSelectedIndex(tipoComprobante.getOperacion());
            }
        }


    }

    /**
     * Controla de que si se ingresa un tipo de comprobante luego de que ya se
     * haya cargado una entidad , entonces si el tipo de comprobante no se
     * corresponde con el tipo de entidad que deberia ir entonces se borra los
     * datos de la entidad
     */
    public void controlarEntidad() {
//        if (entidad != null) {
        if (ComprobanteUtil.isReciboParaAsociado(tipoComprobante.getCodigo())) {
            if (!(entidad.getTipoEntidad() == Constantes.ASOCIADO_INT)) {
                limpiarDatosEntidad();
            }
//            }
        }
    }

    public void limpiarNumeroSerie() {
        txtnumSerie1.setEnabled(true);
        txtNumSerie2.setEnabled(true);
        txtnumSerie1.setText("");
        txtNumSerie2.setText("");
    }

    /**
     * Determina la numeracion del comprobante actual
     */
    public void GenerarNumeroSerie() {
        switch (tipoComprobante.getCategoriacomprobante().getDescripcion()) {
            case Constantes.CATEGORIA_BOLETA:
                System.out.println("boleta");
                limpiarNumeroSerie();

                break;
            case Constantes.CATEGORIA_CHEQUE:
                System.out.println("cheque");
                limpiarNumeroSerie();
                break;
            case Constantes.CATEGORIA_FACTURA:
                System.out.println("factura");
                limpiarNumeroSerie();
                break;

            case Constantes.CATEGORIA_RECIBO:
                System.out.println("recibo");

                // generar automaticamente el numero de serie con la numeracion que le toca
                txtnumSerie1.setEnabled(true);
                txtNumSerie2.setEnabled(true);
                txtnumSerie1.setEditable(false);
                txtNumSerie2.setEditable(false);
                generarNumerodeSerieRecibo();
                txtCuit.requestFocus();
                break;
        }

    }

    /**
     * Controla de que no se pueda editar este panel si el Tipo de Cmprobante no
     * es Uno de Anticipo de REtorno
     *
     */
    public void controlarPanelMonotributo() {
        if (tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_ANTICIPO_RETORNO) {
//            panelContendedorPanelMonotrib.setVisible(true);

            if (modificar && visualizar) {
                // si se llama esta ventana desde la gestora entonces no se debe editar ya que solo visualiza 
                chkAporteMonotributo.setEnabled(false);
                txtAporteMonotributo.setEnabled(false);

            } else {
                chkAporteMonotributo.setEnabled(true);
                txtAporteMonotributo.setEnabled(true);
                txtAporteMonotributo.setEditable(true);


            }



        } else {
//            panelContendedorPanelMonotrib.setVisible(false);
            chkAporteMonotributo.setEnabled(false);
            txtAporteMonotributo.setEnabled(false);
            chkAporteMonotributo.setSelected(false);
            txtAporteMonotributo.setText("0.0");

        }
    }

    /**
     * Controla si se puede poner la propiedad Editable o no el Numero de Serie
     * Esto depende de que si se trata de un Tipo de comprobante con numero de
     * serie de generacion automatica o no.
     */
    public void ControlarEditableNumeroSerie() {
        if (tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_PAGO || tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_ANTICIPO_RETORNO || tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_DISTRIBUCION_EXCEDENTE || tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_REEMBOLSO_CUOTA_SOCIALES || tipoComprobante.getCodigo() == Constantes.CODIGO_RECIBO_INTEGRACION_CUOTA) {
            // para estos tipos de comprobante se genera automaticamente por lo que se debe controlar de que no se pueda editar el campo de numero de serie
            txtnumSerie1.setEditable(false);
            txtNumSerie2.setEditable(false);
            txtCuit.requestFocus();
        } else {
            txtnumSerie1.setEditable(true);
            txtNumSerie2.setEditable(true);
            txtnumSerie1.requestFocus();

        }
    }

    public void generarNumerodeSerieRecibo() {
        // incrementar el numero de serie
        numIzq = tipoComprobante.getNumeroSerieIzq();
        numDer = tipoComprobante.getNumeroSerieDer();
        if (ComprobanteUtil.isLLenoPosicionDer(numDer)) {
            numIzq = ComprobanteUtil.incrementarNumSerieIzq(numIzq);
        }
        numDer = ComprobanteUtil.incrementarNumSerieDer(tipoComprobante.getNumeroSerieDer());
//        System.out.println(numIzq);
//        System.out.println(numDer);
        txtnumSerie1.setText(ComprobanteUtil.formatearNumSerieIzq(numIzq));
        txtNumSerie2.setText(ComprobanteUtil.formatearNumSerieDer(numDer));

    }

    public void limpiarDatosEntidad() {
        entidad = new Entidad();
        txtCuit.setText("");
//        txtCuit.setValue("");
        txtNombre.setText("");


    }

    /**
     * Se encarga de procesar la referencia del tipo de comprobante que se desea
     * generar
     */
    private void getProcessComprobante() {
        String tipo = txtRefTipoCompr.getText().trim();
        boolean tipoisVacio = txtRefTipoCompr.getText().trim().isEmpty();
        tipoComprobante = new TipoComprobanteDaoImp().getTipoComprobanteRef(tipo);
//            tipoComprobante = new TipoComprobanteDaoImp().getTipoFormularioRef(tipo);
        if (!tipoisVacio && tipoComprobante != null) {

            txtTipoComprobante.setText(tipoComprobante.getFormulario());
            controlarTipoOperacion();
            controlarEntidad();
            controlarConcepto();
            GenerarNumeroSerie();
            controlarPanelMonotributo();
            ControlarEditableNumeroSerie();



        } else {
            GUIGestorTipoComprobante guiGestorTipoComp = new GUIGestorTipoComprobante(null, true,Constantes.NAME_NUEVO_REGISTRO);
            if (guiGestorTipoComp.isAgregado()) {
                tipoComprobante = guiGestorTipoComp.getTipoComp();
                txtRefTipoCompr.setText(tipoComprobante.getReferencia());
                txtTipoComprobante.setText(tipoComprobante.getFormulario());
                controlarTipoOperacion();
                controlarEntidad();
                controlarConcepto();
                GenerarNumeroSerie();
                controlarPanelMonotributo();
                ControlarEditableNumeroSerie();

            }
        }

    }

    private void txtRefTipoComprKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRefTipoComprKeyPressed
        // esta linea es para que podamos utilizar el evento cuando tecleamos la tecla TAB, sino ponemos esto no funciona
        txtRefTipoCompr.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, java.util.Collections.EMPTY_SET);

        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println("entro al tab");
            if (!txtRefTipoCompr.getText().trim().isEmpty()) {

                // esto chequea cuando ya ingreso anteriormente un codigo
                if (!refComprobante.equals(txtRefTipoCompr.getText().trim())) {
                    getProcessComprobante();

                } else {
                    // si ingreso anteriormente y no modifico el codigo
                    //paso al numero de serie
                    txtnumSerie1.requestFocus();
                }

            } else {
                mensajero.mensajeError(null, "NO PUEDE ESTAR VACIO");
                txtRefTipoCompr.requestFocus();
            }
        }
    }//GEN-LAST:event_txtRefTipoComprKeyPressed
    /**
     * metodo que se encarga de realizar la accion del boton Imprimir
     * Comprobante
     */
    private void imprimirComprobante() {
        // lo vuelvo a traer de la bd para q comprobante este con el conjunto concepto.
//        comprobante = new ComprobanteDaoImp().getFormulario(comprobante.getId());
        new Impresora(comprobante.getId()).Imprimir();
    }
    private void btnImprimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimiActionPerformed
        imprimirComprobante();

    }//GEN-LAST:event_btnImprimiActionPerformed

    private void txtCodigoConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoConceptoKeyTyped
        MyUtil.consumirLetras(evt, txtCodigoConcepto, 3);

    }//GEN-LAST:event_txtCodigoConceptoKeyTyped

    private void chkAporteMonotributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAporteMonotributoActionPerformed
        if (chkAporteMonotributo.isSelected()) {
            if (modificar) {
                txtAporteMonotributo.setText(String.valueOf(monotributo));
            } else {
                txtAporteMonotributo.setText(formatoValor);
            }

            txtAporteMonotributo.setEditable(true);

        } else {

            txtAporteMonotributo.setText(formatoValor);
            txtAporteMonotributo.setEditable(false);

        }
    }//GEN-LAST:event_chkAporteMonotributoActionPerformed

    /**
     * metodo que se encarga de buscar la entidad y lo almacena en un objeto
     * entidad Reliza una pequeña validadcion : si no se eligio un tipo de
     * copmrobante entnoces no se puede realizar la busqueda
     */
    private void buscarEntidad() {
        if (tipoComprobante != null) {
//            if (!txtRefTipoCompr.getText().isEmpty()) {
            GUIgestorEntidades gestorEntidad = new GUIgestorEntidades(null, true, tipoComprobante.getCodigo());
            if (gestorEntidad.isSelecciono()) {
                entidad = gestorEntidad.getEntidad();
                if (entidad.getTipoEntidad() == Constantes.PROVEEDOR_INT) {
                    Proveedor proveedor = new ProveedorDaoImp().getProveedor(entidad.getId());
                    txtCuit.setText(String.valueOf(proveedor.getCuit()));
                    txtNombre.setText(proveedor.getRazonSocial());
                } else if (entidad.getTipoEntidad() == Constantes.CLIENTE_INT) {
                    Cliente cliente = new ClienteDaoImp().getCliente(entidad.getId());
                    txtCuit.setText(String.valueOf(cliente.getCuit()));
                    txtNombre.setText(cliente.getRazonSocial());
                } else if (entidad.getTipoEntidad() == Constantes.ASOCIADO_INT) {
                    Asociado asociado = new AsociadoDaoImp().getAsociado(entidad.getId());
                    txtCuit.setText(String.valueOf(asociado.getCuit()));
                    txtNombre.setText(asociado.getApellido() + " " + asociado.getNombre());
                }
                txtCodigoConcepto.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes elegir un Tipo de Comprobante antes de continuar con este paso");
        }
    }
    private void txtnumSerie1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumSerie1KeyTyped

        if (txtnumSerie1.getText().length() == 4) {
            txtNumSerie2.requestFocus();
        }
        MyUtil.consumirLetras(evt, txtnumSerie1, 4);


    }//GEN-LAST:event_txtnumSerie1KeyTyped

    private void txtNumSerie2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumSerie2KeyTyped
        MyUtil.consumirLetras(evt, txtNumSerie2, 8);
        if (txtNumSerie2.getText().length() == 8) {
            txtCuit.requestFocus();
        }
    }//GEN-LAST:event_txtNumSerie2KeyTyped

    private void txtRefTipoComprKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRefTipoComprKeyTyped
        if (txtRefTipoCompr.getText().length() == 4 && evt.getKeyCode() != 8) {
            txtCuit.requestFocus();
        }
    }//GEN-LAST:event_txtRefTipoComprKeyTyped

    private void cmbTipoProcesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoProcesoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCuit.requestFocus();
        }
    }//GEN-LAST:event_cmbTipoProcesoKeyPressed

    /**
     * Metodo que se encarga de realizar la accion cuando se presiona el boton
     * Eliminar Comprobante
     */
    private void eliminarComprobante() {
        int opc = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar el Comprobante: " + comprobante.getNumeroSerie(), "ELIMINAR COMPROBANTE", JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
//           new ComprobanteDaoImp().deleteFormulario(comprobante);
            // no se elimina el comprobante sino solo que cambia su estado a anulado (o sea estado false)
            comprobante.setEstado(false);
            new ComprobanteDaoImp().upDateFormulario(comprobante);

            mensajero.mensajeInformacionAtualizacionOK(null);
            setElimiado(true);
            // configuarar botones luego de eliminar
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnImprimi.setEnabled(false);
            btnNuevo.setEnabled(true);
            limpiarDatosComprobante();
            setEditableComprobante(false);
        }
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarComprobante();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEntidadActionPerformed
        buscarEntidad();
    }//GEN-LAST:event_btnBuscarEntidadActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        MyUtil.consumirAll(evt, txtMonto, 20);
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtAporteMonotributoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAporteMonotributoKeyTyped
        MyUtil.consumirAll(evt, txtAporteMonotributo, 15);

    }//GEN-LAST:event_txtAporteMonotributoKeyTyped

    /**
     * Metodo que se encarga de ejecutar la accion del boton nuevo Comprobante
     */
    private void newComprobante() {
        // inicializar toodo
        comprobante = new Comprobante();
        conjuntoConceptos = null;
        tipoComprobante = null;
        comprobanteconcepto = null;
        entidad = null;
        concepto = null;
        numDer = 0;
        numIzq = 0;
        monotributo = 0.0;
        monto=0.0;
        limpiarDatosComprobante();

        //botones
        activarBotonesParaNuevo();
        modificar = false;

        txtRefTipoCompr.requestFocus();
        this.setTitle(Constantes.NAME_NUEVO_REGISTRO);
    }
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        newComprobante();


    }//GEN-LAST:event_btnNuevoActionPerformed
    private void activarBotonesParaEditar() {
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(false);
    }

    /**
     * metodo que sen encarga de realizar la accion del boton Editar Comprobane
     *
     */
    private void editarComprobante() {
        setEditableComprobante(true);
        //lo hago positivo para que en la ventana aparesca sin el signo
//         System.out.println("monotributo antes del metodo editable comprobante " + monotributo);
        monotributo = MyUtil.convertirPositivoDouble(monotributo);
//         System.out.println("monotributo despues del metodo editable comprobante" + monotributo);

        txtRefTipoCompr.setEditable(false);
        txtTipoComprobante.setEditable(false);
        activarBotonesParaEditar();
        modificar = true;


    }
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // mapeo de nuevo el comprobante para que ahira el comprbane tenga cargadas sus colecciones perisistentes
        comprobante = new ComprobanteDaoImp().getFormulario(comprobante.getId());
        // pongo visualizar en false debido a que ahora se edita no se visualiza
        visualizar = false;
//        modificar= true;
        // ahora edito
        editarComprobante();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtnumSerie1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnumSerie1FocusLost
        try {
            if (isComprobanteParaBuscarConcepto(tipoComprobante)) {
                if (txtnumSerie1.getText().trim().length() != 4) {

                    txtnumSerie1.setText(ComprobanteUtil.formatearNumSerieIzq(Long.parseLong(txtnumSerie1.getText().trim())));
                }
            }
        } catch (java.lang.NumberFormatException exParser) {
        }

    }//GEN-LAST:event_txtnumSerie1FocusLost

    private void txtNumSerie2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumSerie2FocusLost
        try {
            if (isComprobanteParaBuscarConcepto(tipoComprobante)) {
                if (txtNumSerie2.getText().trim().length() != 8) {

                    txtNumSerie2.setText(ComprobanteUtil.formatearNumSerieDer(Long.parseLong(txtNumSerie2.getText().trim())));
                }
            }
        } catch (java.lang.NumberFormatException exnumber) {
        }

    }//GEN-LAST:event_txtNumSerie2FocusLost

    public void SNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent v) {
                char c = v.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    getToolkit().beep();
                    //  JOptionPane.showMessageDialog(null, "Solo Nùmeros...");
                    v.consume();
                }
            }
        });
    }

    private void txtCodigoConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoConceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoConceptoActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void panel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panel1KeyPressed
    }//GEN-LAST:event_panel1KeyPressed

    private void btnEliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            eliminarComprobante();
        }
    }//GEN-LAST:event_btnEliminarKeyPressed

    private void btnEditarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditarKeyPressed
        editarComprobante();
    }//GEN-LAST:event_btnEditarKeyPressed

    private void btnNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNuevoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            newComprobante();
        }
    }//GEN-LAST:event_btnNuevoKeyPressed

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarComprobante();
        }
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnImprimiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImprimiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            imprimirComprobante();
        }
    }//GEN-LAST:event_btnImprimiKeyPressed

    private void btnBuscarEntidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarEntidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscarEntidad();
        }
    }//GEN-LAST:event_btnBuscarEntidadKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscarEntidad();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtRefTipoComprFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRefTipoComprFocusGained
        refComprobante = txtRefTipoCompr.getText().trim();
//        System.out.println("coidgo de tipode comproabante"+ txtRefComprobante);
    }//GEN-LAST:event_txtRefTipoComprFocusGained

    private void txtAporteMonotributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAporteMonotributoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAporteMonotributoActionPerformed

    private void txtMontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMontoFocusLost
       //////redondeo las cantidades monetarias y lo reflejo en la ventana para que lo sepa el usuario
        try{
           monto = MyUtil.Redondear(Double.parseDouble(txtMonto.getText()), 2);
           txtMonto.setText(String.valueOf(monto));    
        }catch(java.lang.NumberFormatException eformat){
            mensajero.mensajeError(null, "Error en el formato al ingresar un cantidad monetaria, ingrese un valor con el formato 0.00 ");
            txtMonto.setText(formatoValor);
            txtMonto.requestFocus();
        }     
        
    }//GEN-LAST:event_txtMontoFocusLost

    private void txtAporteMonotributoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAporteMonotributoFocusLost
              try{
                monotributo = MyUtil.Redondear((Double.parseDouble(txtAporteMonotributo.getText())), 2);
                  System.out.println("monotriburo losfocus convertido"+ monotributo);
                txtAporteMonotributo.setText(String.valueOf(monotributo));
                  
              }catch(java.lang.NumberFormatException eformat){
            mensajero.mensajeError(null, "Error en el formato al ingresar un cantidad monetaria, ingrese un valor con el formato 0.00 ");
            txtAporteMonotributo.setText(formatoValor);
            txtAporteMonotributo.requestFocus();
            }     
                ///////////////////////////////////
    }//GEN-LAST:event_txtAporteMonotributoFocusLost

    private void txtCuitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            buscarEntidad();
        }
        
    }//GEN-LAST:event_txtCuitKeyPressed

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
         MyUtil.consumirLetras(evt, txtCodigoConcepto, 0);
    }//GEN-LAST:event_txtCuitKeyTyped

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
            java.util.logging.Logger.getLogger(GUIComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIComprobante dialog = new GUIComprobante(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.button.ButtonIpod btnBuscarEntidad;
    private org.edisoncor.gui.button.ButtonIpod btnCancelar;
    private org.edisoncor.gui.button.ButtonIpod btnEditar;
    private org.edisoncor.gui.button.ButtonIpod btnEliminar;
    private org.edisoncor.gui.button.ButtonIpod btnGuardar;
    private org.edisoncor.gui.button.ButtonIpod btnImprimi;
    private org.edisoncor.gui.button.ButtonIpod btnNuevo;
    private javax.swing.JCheckBox chkAporteMonotributo;
    private org.edisoncor.gui.comboBox.ComboBoxRect cmbTipoProceso;
    private com.toedter.calendar.JDateChooser dateComprobante;
    private org.edisoncor.gui.label.LabelMetric labelMetric10;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private org.edisoncor.gui.label.LabelMetric labelMetric3;
    private org.edisoncor.gui.label.LabelMetric labelMetric4;
    private org.edisoncor.gui.label.LabelMetric labelMetric5;
    private org.edisoncor.gui.label.LabelMetric labelMetric6;
    private org.edisoncor.gui.label.LabelMetric labelMetric7;
    private org.edisoncor.gui.label.LabelMetric labelMetric8;
    private org.edisoncor.gui.label.LabelMetric labelMetric9;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panelComprobante;
    private org.edisoncor.gui.panel.Panel panelConcepto;
    private org.edisoncor.gui.panel.Panel panelContendedorPanelMonotrib;
    private org.edisoncor.gui.panel.Panel panelEntidad;
    private org.edisoncor.gui.panel.Panel panelMonotributo;
    private org.edisoncor.gui.panel.Panel panelMonotributo1;
    private org.edisoncor.gui.textField.TextField txtAporteMonotributo;
    private org.edisoncor.gui.textField.TextField txtCodigoConcepto;
    private org.edisoncor.gui.textField.TextField txtCuit;
    private org.edisoncor.gui.textField.TextField txtDescripcionConcepto;
    private org.edisoncor.gui.textField.TextField txtMonto;
    private org.edisoncor.gui.textField.TextField txtNombre;
    private org.edisoncor.gui.textField.TextField txtNumSerie2;
    private org.edisoncor.gui.textField.TextField txtRefTipoCompr;
    private org.edisoncor.gui.textField.TextField txtTipoComprobante;
    private org.edisoncor.gui.textField.TextField txtnumSerie1;
    // End of variables declaration//GEN-END:variables

    private void limpiarDatosComprobante() {


        txtRefTipoCompr.setText("");
        txtTipoComprobante.setText("");
        txtRefTipoCompr.setEditable(true);
        txtTipoComprobante.setEditable(true);
//        txtnumSerie1.setText("");
//        txtNumSerie2.setText("");
//        
        dateComprobante.setDate(new Date());

        cmbTipoProceso.setSelectedIndex(0);

        limpiarDatosEntidad();
        btnBuscarEntidad.setEnabled(true);
        limpiarNumeroSerie();
        limpiarConceptos();
        txtRefTipoCompr.requestFocus();
    }

    private void limpiarConceptos() {
        txtCodigoConcepto.setText("");
        txtDescripcionConcepto.setText("");
        txtMonto.setText(formatoValor);
        txtMonto.setEditable(true);

        chkAporteMonotributo.setEnabled(false);
        chkAporteMonotributo.setSelected(false);
        txtAporteMonotributo.setText(formatoValor);

    }

    private void setEditableComprobante(boolean b) {
        dateComprobante.setEnabled(b);
        txtRefTipoCompr.setEditable(b);
        txtTipoComprobante.setEditable(b);
        // solo se pueden editar los comprobantes que no sean automaticos
        if (comprobante.getTipocomprobante().getOperacion() == Constantes.OP_ENTRADA_SALIDA) {
            cmbTipoProceso.setEnabled(b);
            txtnumSerie1.setEditable(b);
            txtNumSerie2.setEditable(b);
        }

        txtCuit.setEditable(b);
        txtNombre.setEditable(b);
        btnBuscarEntidad.setEnabled(b);
        txtCodigoConcepto.setEditable(b);
//        txtDescripcionConcepto.setEditable(b);
        txtMonto.setEditable(b);
//       

//        System.out.println("monotributo en seteditable " +monotributo);
//        

        controlarPanelMonotributo();
        // controlareditable monotributo

    }
}

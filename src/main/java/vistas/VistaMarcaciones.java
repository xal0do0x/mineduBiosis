/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.lowagie.text.DocumentException;
import com.opencsv.CSVWriter;
import controladores.MarcacionControlador;
import entidades.Empleado;
import entidades.Marcacion;
import vistas.dialogos.DlgEmpleado;
import vistas.modelos.MTMarcacion;
import com.personal.utiles.FormularioUtil;
import com.personal.utiles.ReporteUtil;
import controladores.EmpleadoControlador;
import entidades.Departamento;
import entidades.EmpleadoBiostar;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import org.jdesktop.observablecollections.ObservableCollections;
import pruebareportes.rptEmpleadosSinMarcar;
import utiles.UsuarioActivo;
import vistas.dialogos.DlgOficina;

/**
 *
 * @author fesquivelc
 */
public class VistaMarcaciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaEmpleado
     */
    private List<Marcacion> lista;
    private MarcacionControlador mc;
    private EmpleadoControlador ec;
    private List<Integer> listaEmpleadosOficina = new ArrayList<>();
    private List<Integer> listaEmpleadosSinMarcar = new ArrayList<>();

    public VistaMarcaciones() {
        initComponents();
        mc = new MarcacionControlador();
        inicializar();
        bindeoSalvaje();
        buscar();
        actualizarControlesNavegacion();
        checkboxes();
//        buscar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        grpOpcion = new javax.swing.ButtonGroup();
        grpSeleccion = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new org.jdesktop.swingx.JXTable();
        spHoraInicio = new javax.swing.JSpinner();
        spHoraFin = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        btnEmpleado = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlNavegacion = new javax.swing.JPanel();
        btnPrimero = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        spPagina = new javax.swing.JSpinner();
        txtTotal = new javax.swing.JTextField();
        btnSiguiente = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        cboTamanio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        numeroEmpleadosOficina = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numeroEmpleadosMarcando = new javax.swing.JTextField();
        btnReporteEmpleadosSinMarcar = new javax.swing.JButton();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        radFechas = new javax.swing.JRadioButton();
        radHora = new javax.swing.JRadioButton();
        btnBuscar1 = new javax.swing.JButton();
        radEmpleado = new javax.swing.JRadioButton();
        radOficina = new javax.swing.JRadioButton();
        txtOficina = new javax.swing.JTextField();
        btnOficina = new javax.swing.JButton();
        grpOpcion.add(radHora);
        grpOpcion.add(radFechas);

        grpSeleccion.add(radEmpleado);
        grpSeleccion.add(radOficina);

        setClosable(true);
        setMaximizable(true);
        setTitle("LISTADO DE MARCACIONES SIN PROCESAR");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtBusqueda.setEditable(false);
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(txtBusqueda, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(btnBuscar, gridBagConstraints);

        jScrollPane1.setViewportView(tblEmpleado);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        spHoraInicio.setModel(new javax.swing.SpinnerDateModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(spHoraInicio, gridBagConstraints);

        spHoraFin.setModel(new javax.swing.SpinnerDateModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(spHoraFin, gridBagConstraints);

        jLabel1.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel1, gridBagConstraints);

        btnEmpleado.setText("...");
        btnEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(btnEmpleado, gridBagConstraints);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(btnLimpiar, gridBagConstraints);

        pnlNavegacion.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlNavegacion.add(btnPrimero);

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlNavegacion.add(btnAnterior);

        spPagina.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spPagina.setMinimumSize(new java.awt.Dimension(60, 20));
        spPagina.setPreferredSize(new java.awt.Dimension(60, 20));
        spPagina.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spPaginaStateChanged(evt);
            }
        });
        pnlNavegacion.add(spPagina);

        txtTotal.setEditable(false);
        txtTotal.setColumns(3);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("1");
        pnlNavegacion.add(txtTotal);

        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlNavegacion.add(btnSiguiente);

        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlNavegacion.add(btnUltimo);

        cboTamanio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "25", "40", "50" }));
        cboTamanio.setMinimumSize(new java.awt.Dimension(53, 24));
        cboTamanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTamanioActionPerformed(evt);
            }
        });
        pnlNavegacion.add(cboTamanio);

        jLabel2.setText("#EmpleadosOficina:");
        pnlNavegacion.add(jLabel2);

        numeroEmpleadosOficina.setEnabled(false);
        numeroEmpleadosOficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroEmpleadosOficinaActionPerformed(evt);
            }
        });
        pnlNavegacion.add(numeroEmpleadosOficina);

        jLabel3.setText("#EmpleadosMarcando:");
        pnlNavegacion.add(jLabel3);

        numeroEmpleadosMarcando.setEnabled(false);
        numeroEmpleadosMarcando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroEmpleadosMarcandoActionPerformed(evt);
            }
        });
        pnlNavegacion.add(numeroEmpleadosMarcando);

        btnReporteEmpleadosSinMarcar.setText("-> Reporte");
        btnReporteEmpleadosSinMarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteEmpleadosSinMarcarActionPerformed(evt);
            }
        });
        pnlNavegacion.add(btnReporteEmpleadosSinMarcar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(pnlNavegacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(dcFechaInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(dcFechaFin, gridBagConstraints);

        radFechas.setSelected(true);
        radFechas.setText("Entre fechas:");
        radFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radFechasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel1.add(radFechas, gridBagConstraints);

        radHora.setText("Entre horas:");
        radHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radHoraActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        jPanel1.add(radHora, gridBagConstraints);

        btnBuscar1.setText("Exportar CSV");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(btnBuscar1, gridBagConstraints);

        radEmpleado.setSelected(true);
        radEmpleado.setText("Empleado:");
        radEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radEmpleadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(radEmpleado, gridBagConstraints);

        radOficina.setText("Oficina");
        radOficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radOficinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(radOficina, gridBagConstraints);

        txtOficina.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtOficina, gridBagConstraints);

        btnOficina.setText("...");
        btnOficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOficinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnOficina, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 115;
        gridBagConstraints.ipady = 545;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
//        lblBusqueda.setBusy(true);
        paginaActual = 1;
        buscar();
        actualizarControlesNavegacion();
//        lblBusqueda.setBusy(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
//            lblBusqueda.setBusy(true);
            buscar();
//            lblBusqueda.setBusy(false);
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        // TODO add your handling code here:
        DlgEmpleado dialogo = new DlgEmpleado(this);
        dialogo.setAgregar(false);
        this.empleadoSeleccionado = dialogo.getSeleccionado();
        if (this.empleadoSeleccionado != null) {
            this.txtBusqueda.setText(
                    empleadoSeleccionado.getNroDocumento()
                    + " " + empleadoSeleccionado.getApellidoPaterno()
                    + " " + empleadoSeleccionado.getApellidoMaterno()
                    + " " + empleadoSeleccionado.getNombre());
        }
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        FormularioUtil.limpiarComponente(this.txtBusqueda);
        this.empleadoSeleccionado = null;
        FormularioUtil.limpiarComponente(this.txtOficina);
        this.oficinaSeleccionada = null;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        // TODO add your handling code here:
        primero();
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        anterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void spPaginaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spPaginaStateChanged
        // TODO add your handling code here:
        this.seleccionPagina();
    }//GEN-LAST:event_spPaginaStateChanged

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        siguiente();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        // TODO add your handling code here:
        ultimo();
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void cboTamanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTamanioActionPerformed
        // TODO add your handling code here:
        this.paginaActual = 1;
        buscar();
        this.actualizarControlesNavegacion();
    }//GEN-LAST:event_cboTamanioActionPerformed

    private void radFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radFechasActionPerformed
        // TODO add your handling code here:
        checkboxes();
    }//GEN-LAST:event_radFechasActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
//        imprimir();
        exportar();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void radHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radHoraActionPerformed
        // TODO add your handling code here:
        checkboxes();
    }//GEN-LAST:event_radHoraActionPerformed

    private void radEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radEmpleadoActionPerformed
        // TODO add your handling code here:
        seleccion();
    }//GEN-LAST:event_radEmpleadoActionPerformed

    private void radOficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radOficinaActionPerformed
        // TODO add your handling code here:
        seleccion();
    }//GEN-LAST:event_radOficinaActionPerformed

    private void btnOficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOficinaActionPerformed
        // TODO add your handling code here:
        DlgOficina ofis = new DlgOficina(this);
        oficinaSeleccionada = ofis.getSeleccionado();
        if(oficinaSeleccionada != null){
            txtOficina.setText(oficinaSeleccionada.getNombre());
        }
    }//GEN-LAST:event_btnOficinaActionPerformed

    private void numeroEmpleadosOficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroEmpleadosOficinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroEmpleadosOficinaActionPerformed

    private void numeroEmpleadosMarcandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroEmpleadosMarcandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroEmpleadosMarcandoActionPerformed

    private void btnReporteEmpleadosSinMarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteEmpleadosSinMarcarActionPerformed
        try {
            /**
             * Aca se ejecuta el metodo que envia los parametros a la clase generadora del reporte
             */
            imprimirEmpleadosSinMarcar();
        } catch (IOException ex) {
            Logger.getLogger(VistaMarcaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(VistaMarcaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteEmpleadosSinMarcarActionPerformed

    private Departamento oficinaSeleccionada;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnOficina;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnReporteEmpleadosSinMarcar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JComboBox cboTamanio;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.ButtonGroup grpOpcion;
    private javax.swing.ButtonGroup grpSeleccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numeroEmpleadosMarcando;
    private javax.swing.JTextField numeroEmpleadosOficina;
    private javax.swing.JPanel pnlNavegacion;
    private javax.swing.JRadioButton radEmpleado;
    private javax.swing.JRadioButton radFechas;
    private javax.swing.JRadioButton radHora;
    private javax.swing.JRadioButton radOficina;
    private javax.swing.JSpinner spHoraFin;
    private javax.swing.JSpinner spHoraInicio;
    private javax.swing.JSpinner spPagina;
    private org.jdesktop.swingx.JXTable tblEmpleado;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtOficina;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private Empleado empleadoSeleccionado;
    private Departamento departamentoSeleccionado;
    private void bindeoSalvaje() {
        lista = ObservableCollections.observableList(new ArrayList<Marcacion>());

        String[] columnas = {"Nro de documento", "Empleado", "Fecha", "Hora", "Equipo"};
        MTMarcacion mt = new MTMarcacion(lista, columnas);
        tblEmpleado.setModel(mt);
    }

//    private void buscar() {
//        lista.clear();
//        Date fechaInicio = (Date) spFechaInicio.getValue();
//        Date fechaFin = (Date) spFechaFin.getValue();
//        if (empleadoSeleccionado == null) {
//            lista.addAll(mc.buscarXFecha(fechaInicio, fechaFin));
//        } else {
//            lista.addAll(mc.buscarXFecha(empleadoSeleccionado.getNroDocumento(), fechaInicio, fechaFin));
//        }
//        tblEmpleado.packAll();
//    }
    private void inicializar() {
        FormularioUtil.modeloSpinnerFechaHora(this.spHoraInicio, "HH:mm");
        FormularioUtil.modeloSpinnerFechaHora(this.spHoraFin, "HH:mm");
        Date fechaMax = new Date();
        dcFechaInicio.setMaxSelectableDate(fechaMax);
        dcFechaInicio.setDate(fechaMax);
        dcFechaFin.setMaxSelectableDate(fechaMax);
        dcFechaFin.setDate(fechaMax);

    }

    private int paginaActual = 1;
    private int totalPaginas = 0;
    private int tamanioPagina = 0;

    private void buscar() {
        Date fechaInicio = dcFechaInicio.getDate();
        Date fechaFin = dcFechaFin.getDate();

        tamanioPagina = Integer.parseInt(cboTamanio.getSelectedItem().toString());

        lista.clear();

        lista.addAll(this.listar(this.obtenerDNIEntero(), fechaInicio, fechaFin, paginaActual, tamanioPagina));
        //Numero de empleados por oficina
        numeroEmpleadosOficina.setText(Integer.toString(this.obtenerDNIEntero().size()));
        tblEmpleado.packAll();
    }

    private List<Marcacion> listar(List<Integer> empleados, Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        int total;
        if ((empleadoSeleccionado == null && radEmpleado.isSelected()) || (oficinaSeleccionada == null && radOficina.isSelected())) {
            if(radFechas.isSelected()){
                total = mc.totalXFecha(fechaInicio, fechaFin);
            }else{
                total = mc.totalXFechaXHora(fechaInicio, (Date) spHoraInicio.getValue(),(Date) spHoraFin.getValue());
            }
            
        } else {
            if(radFechas.isSelected()){
                total = mc.totalXFecha(empleados,fechaInicio, fechaFin);
                List<Marcacion> listaMarcaciones = mc.buscarXFechaTotal(this.obtenerDNIEntero(), fechaInicio, fechaFin);
                List<Integer> empleadosMarcando = new ArrayList<>();
//                if(listaEmpleadosOficina!=null){
//                    listaEmpleadosOficina.clear();
//                }                
                for(Marcacion marcacion : listaMarcaciones){
                    if(!empleadosMarcando.contains(marcacion.getEmpleado())){
                        listaEmpleadosOficina.add(marcacion.getEmpleado());
                        empleadosMarcando.add(marcacion.getEmpleado());
                    }
                }
                
                
                numeroEmpleadosMarcando.setText(Integer.toString(empleadosMarcando.size()));
            }else{
                total = mc.totalXFechaIyFXHora(empleados,fechaInicio, fechaFin, (Date) spHoraInicio.getValue(),(Date) spHoraFin.getValue());
                List<Marcacion> listaMarcaciones = mc.buscarXFechaXHoraTotal(this.obtenerDNIEntero(),fechaInicio,fechaFin, (Date) spHoraInicio.getValue(),(Date) spHoraFin.getValue());
                List<Integer> empleadosMarcando = new ArrayList<>();
                listaEmpleadosOficina.clear();
                for(Marcacion marcacion : listaMarcaciones){
                    if(!empleadosMarcando.contains(marcacion.getEmpleado())){
                        listaEmpleadosOficina.add(marcacion.getEmpleado());
                        empleadosMarcando.add(marcacion.getEmpleado());
                    }
                }
                numeroEmpleadosMarcando.setText(Integer.toString(empleadosMarcando.size()));
            }
//            total = mc.totalXEmpleadoXFecha(empleados, fechaInicio, fechaFin);
        }
        if (total % tamanio == 0) {
            totalPaginas = total / tamanio;
        } else {
            totalPaginas = (total / tamanio) + 1;
        }

        if (totalPaginas == 0) {
            totalPaginas = 1;
        }

        if ((empleadoSeleccionado == null && radEmpleado.isSelected()) || (oficinaSeleccionada == null && radOficina.isSelected())) {
            if (radFechas.isSelected()) {
                return this.mc.buscarXFecha(fechaInicio, fechaFin, (pagina - 1) * tamanio, tamanio);
            } else {
                return this.mc.buscarXFechaXHora(fechaInicio, (Date) spHoraInicio.getValue(), (Date) spHoraFin.getValue(), (pagina - 1) * tamanio, tamanio);
            }

        } else {
            if (radFechas.isSelected()) {
                return this.mc.buscarXFecha(empleados, fechaInicio, fechaFin, (pagina - 1) * tamanio, tamanio);
            } else {
                return this.mc.buscarXFechaXHora1(empleados, fechaInicio, fechaFin, (Date) spHoraInicio.getValue(), (Date) spHoraFin.getValue(), (pagina - 1) * tamanio, tamanio);
            }

        }

    }
    
    private List<Integer> obtenerDNIEntero(){
        List<Integer> dnisEnteros = new ArrayList<>();
        if(radEmpleado.isSelected() && empleadoSeleccionado != null){
            
            dnisEnteros.add(Integer.parseInt(empleadoSeleccionado.getNroDocumento()));
        }else if(radOficina.isSelected() && oficinaSeleccionada != null){
            List<EmpleadoBiostar> empleados = oficinaSeleccionada.getEmpleadoList();
            for(EmpleadoBiostar e : empleados){
                dnisEnteros.add(e.getId());
            }
        }
        return dnisEnteros;
    }

    private void siguiente() {
        paginaActual++;
        buscar();
        this.actualizarControlesNavegacion();
    }

    private void ultimo() {
        paginaActual = totalPaginas;
        buscar();
        this.actualizarControlesNavegacion();
    }

    private void primero() {
        paginaActual = 1;
        buscar();
        this.actualizarControlesNavegacion();
    }

    private void anterior() {
        paginaActual--;
        buscar();
        this.actualizarControlesNavegacion();
    }

    private void seleccionPagina() {
        paginaActual = (int) spPagina.getValue();
        buscar();
        this.actualizarControlesNavegacion();
    }

    private void actualizarControlesNavegacion() {
        spPagina.setValue(paginaActual);
        txtTotal.setText(totalPaginas + "");

        SpinnerNumberModel modeloSP = (SpinnerNumberModel) spPagina.getModel();
        Comparable<Integer> maximo = totalPaginas;
        modeloSP.setMaximum(maximo);

        this.btnSiguiente.setEnabled(paginaActual != totalPaginas);
        this.btnUltimo.setEnabled(paginaActual != totalPaginas);

        this.btnAnterior.setEnabled(paginaActual != 1);
        this.btnPrimero.setEnabled(paginaActual != 1);
    }

    private void checkboxes() {
        FormularioUtil.activarComponente(dcFechaInicio, true);
        FormularioUtil.activarComponente(dcFechaFin, radFechas.isSelected());

        FormularioUtil.activarComponente(spHoraInicio, radHora.isSelected());
        FormularioUtil.activarComponente(spHoraFin, radHora.isSelected());
    }

//    private final EmpleadoControlador ec = new EmpleadoControlador();
    private final ReporteUtil rUtil = new ReporteUtil();
    private final DateFormat dfFecha = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm:ss");
//
//    private void imprimir() {
//        String rutaReporte = "reportes/r_marcaciones.jasper";
//        File ficheroReporte = new File(rutaReporte);
//        Map<String, Object> mapa = new HashMap<>();
//        mapa.put("usuario", UsuarioActivo.getUsuario().getLogin());
//        mapa.put("fecha_inicio", dcFechaInicio.getDate());
//        mapa.put("por_fecha", radFechas.isSelected());
//        if (radFechas.isSelected()) {
//            mapa.put("fecha_fin", dcFechaFin.getDate());
//        } else {
//            mapa.put("hora_inicio", (Date) spHoraInicio.getValue());
//            mapa.put("hora_fin", (Date) spHoraFin.getValue());
//        }
//
//        mapa.put("CONEXION_EMPLEADOS", ec.getDao().getConexion());
//        rUtil.setConn(this.mc.getDao().getConexion());
//        rUtil.generarReporte(ficheroReporte, mapa, JOptionPane.getFrameForComponent(this));
//    }

    private void imprimirEmpleadosSinMarcar() throws IOException, DocumentException{
        Calendar cal = Calendar.getInstance();

        String usuario = UsuarioActivo.getUsuario().getLogin();

        List<Empleado> empleados = new ArrayList<>();
        for(Integer i : listaEmpleadosOficina){
            System.out.println("Dni: "+i);
        }
        empleados = ec.buscarPorListaInt(listaEmpleadosOficina);
        List<String> dnis = new ArrayList<>();
        for(Empleado e:empleados){
            dnis.add(e.getNroDocumento());
        }
        String reporte = "";

        int anio;
        int mes;
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        String rangoTitulo = "";
        String rangoValor = "";        


        rptEmpleadosSinMarcar reporteEmpleadosSinMarcar = new rptEmpleadosSinMarcar();
        String grupoOficina = "";
        String tipo = "";
        if(oficinaSeleccionada!=null){
            if (oficinaSeleccionada.getNombre()!=null) {
                grupoOficina = oficinaSeleccionada.getNombre();
                tipo = "O";
            }
        }else{
            System.out.println("No se ingresaron datos");
        }
        
        reporteEmpleadosSinMarcar.crearPdf("RptEmpSinM-"+grupoOficina+ ".pdf", dnis, fechaInicio, fechaFin, grupoOficina, tipo, usuario, (Date) spHoraInicio.getValue(),(Date) spHoraFin.getValue());
    }
    
    private void exportar() {
        try {
            String url = FormularioUtil.chooserFichero(this, "Seleccione donde guardar");
            File fichero = new File(url + ".csv");

            if (!fichero.exists()) {
                try {
                    fichero.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(VistaMarcaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            CSVWriter writer = new CSVWriter(new FileWriter(fichero.getAbsolutePath()), ',');
            List<Marcacion> listado;
            if ((empleadoSeleccionado == null && radEmpleado.isSelected()) || (radOficina == null && radOficina.isSelected())) {
                if (radFechas.isSelected()) {
                    listado = this.mc.buscarXFecha(dcFechaInicio.getDate(), dcFechaFin.getDate(), -1, -1);
                } else {
                    listado = this.mc.buscarXFechaXHora(dcFechaInicio.getDate(), (Date) spHoraInicio.getValue(), (Date) spHoraFin.getValue(), -1, -1);
                }

            } else {
                if (radFechas.isSelected()) {
                    listado = this.mc.buscarXFecha(obtenerDNIEntero(), dcFechaInicio.getDate(), dcFechaFin.getDate(), -1, -1);
                } else {
                    listado = this.mc.buscarXFechaXHora(obtenerDNIEntero(), dcFechaInicio.getDate(), (Date) spHoraInicio.getValue(), (Date) spHoraFin.getValue(), -1, -1);
                }
            }
            String[] linea = new String[5];
            
            for(Marcacion m : listado){
                linea[0] = m.getNombre();
                linea[1] = dfFecha.format(m.getFecha());
                linea[2] = dfHora.format(m.getHora());
                linea[3] = m.getEquipo();
                
                writer.writeNext(linea, true);
            }
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(VistaMarcaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccion() {
        FormularioUtil.activarComponente(this.btnEmpleado, radEmpleado.isSelected());
        FormularioUtil.activarComponente(this.btnOficina, radOficina.isSelected());
    }
}

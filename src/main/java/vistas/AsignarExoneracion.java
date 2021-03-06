/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.AsignacionPermisoControlador;
import controladores.Controlador;
import controladores.EmpleadoControlador;
import controladores.PermisoControlador;
import controladores.TCAnalisisControlador;
import entidades.Empleado;
import entidades.Permiso;
import entidades.TipoPermiso;
import vistas.dialogos.DlgEmpleado;
import vistas.modelos.MTEmpleado;
import com.personal.utiles.FormularioUtil;
import com.personal.utiles.ReporteUtil;
import controladores.CondicionControlador;
import controladores.DetalleCondicionControlador;
import entidades.Condicion;
import entidades.DetalleCondicion;
import java.awt.Component;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import utiles.UsuarioActivo;
import vistas.modelos.MTDetalleCondicion;

/**
 *
 * @author fesquivelc
 */
public class AsignarExoneracion extends javax.swing.JInternalFrame {

    /**
     * Creates new form CRUDPeriodo
     */
    private List<DetalleCondicion> listado;
    private List<Empleado> integrantes;
    private PermisoControlador controlador;
    private EmpleadoControlador ec;
    private CondicionControlador cc;
    private int accion;
    private Empleado empleadoSeleccionado;
    private AsignacionPermisoControlador ac;
    private DetalleCondicionControlador dcc;
    private final ReporteUtil reporteador;

    public AsignarExoneracion() {
        initComponents();
        inicializar();
        bindeoSalvaje();
        reporteador = new ReporteUtil();

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

        opciones = new javax.swing.ButtonGroup();
        pnlListado = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new org.jdesktop.swingx.JXTable();
        lblBusqueda = new org.jdesktop.swingx.JXBusyLabel();
        txtEmpleado = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        spFechaInicio1 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        spFechaFin1 = new javax.swing.JSpinner();
        btnBuscar = new javax.swing.JButton();
        pnlNavegacion = new javax.swing.JPanel();
        btnPrimero = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        spPagina = new javax.swing.JSpinner();
        txtTotal = new javax.swing.JTextField();
        btnSiguiente = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        cboTamanio = new javax.swing.JComboBox();
        pnlDatos = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmpleados = new org.jdesktop.swingx.JXTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        pnlOpcion = new javax.swing.JPanel();
        cboCondicion = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("ASIGNAR CONDICION DE MARCADO A EMPLEADO(S)");
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0};
        layout.rowHeights = new int[] {0};
        getContentPane().setLayout(layout);

        pnlListado.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisos"));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        pnlListado.setLayout(jPanel1Layout);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevo);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 15;
        pnlListado.add(jPanel3, gridBagConstraints);

        tblTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        pnlListado.add(jScrollPane1, gridBagConstraints);

        lblBusqueda.setText("Empleado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(lblBusqueda, gridBagConstraints);

        txtEmpleado.setEditable(false);
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });
        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        pnlListado.add(txtEmpleado, gridBagConstraints);

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(jButton4, gridBagConstraints);

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
        pnlListado.add(btnLimpiar, gridBagConstraints);

        spFechaInicio1.setModel(new javax.swing.SpinnerDateModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(spFechaInicio1, gridBagConstraints);

        jLabel9.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(jLabel9, gridBagConstraints);

        spFechaFin1.setModel(new javax.swing.SpinnerDateModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(spFechaFin1, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlListado.add(btnBuscar, gridBagConstraints);

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
        cboTamanio.setPreferredSize(new java.awt.Dimension(53, 24));
        cboTamanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTamanioActionPerformed(evt);
            }
        });
        pnlNavegacion.add(cboTamanio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 15;
        pnlListado.add(pnlNavegacion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(pnlListado, gridBagConstraints);

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Detalle Condición"));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0};
        pnlDatos.setLayout(jPanel2Layout);

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel1.setText("Tipo condición:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Empleados:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(jLabel2, gridBagConstraints);

        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblEmpleados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jScrollPane3, gridBagConstraints);

        btnAgregar.setText("+");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(btnAgregar, gridBagConstraints);

        btnQuitar.setText("-");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel4.add(btnQuitar, gridBagConstraints);

        jLabel7.setText("Fecha inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel12.setText("Fecha fin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(dcFechaFin, gridBagConstraints);

        dcFechaInicio.setMinimumSize(new java.awt.Dimension(130, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(dcFechaInicio, gridBagConstraints);

        pnlOpcion.setLayout(new java.awt.GridLayout(1, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(pnlOpcion, gridBagConstraints);

        cboCondicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel4.add(cboCondicion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlDatos.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(pnlDatos, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.accion = 0;
        this.controles(accion);
        FormularioUtil.limpiarComponente(this.pnlDatos);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        this.accion = Controlador.NUEVO;
        dcc.prepararCrear();
        this.controles(accion);
        integrantes.clear();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int fila = tblTabla.getSelectedRow();
        if (fila != -1) {
            this.accion = Controlador.MODIFICAR;
            //tipoSeleccionado = this.listado.get(fila);
            dcc.setSeleccionado(this.listado.get(fila));
            //controlador.setSeleccionado(this.listado.get(fila));
            this.controles(accion);
            //FormularioUtil.activarComponente(txtTipoPermiso, false);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (erroresFormulario()) {
            return;
        }
        if (FormularioUtil.dialogoConfirmar(this, accion)) {
            DetalleCondicion seleccionada = dcc.getSeleccionado();

            FormularioUtil.convertirMayusculas(this.pnlDatos);
            System.out.println("Indice: "+cboCondicion.getSelectedIndex());
            System.out.println("Condicion tomada: "+condicionList.get(cboCondicion.getSelectedIndex()).getNombre());
            
            seleccionada.setFechaInicio(dcFechaInicio.getDate());
            if(dcFechaFin.getDate()!= null){
                seleccionada.setFechaFin(dcFechaFin.getDate());
            }else{
                seleccionada.setFechaFin(null);
            }
            seleccionada.setCondicionId(condicionList.get(cboCondicion.getSelectedIndex()));
            for (Empleado ep : integrantes) {
                seleccionada.setEmpleadoNroDocumento(ep.getNroDocumento());
                if(dcc.accion(1)){
                    System.out.println("Guardado");
                }else{
                    System.out.println("Falla al ingresar");
                }
            }        
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        DlgEmpleado dialogo = new DlgEmpleado(this);
        dialogo.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblTablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaMouseReleased
        // TODO add your handling code here:
        int fila = tblTabla.getSelectedRow();
        if (fila != -1) {
            DetalleCondicion detalle = listado.get(fila);
            mostrar(detalle);
            //FormularioUtil.activarComponente(this.btnImprimirTodo, true);
        }
    }//GEN-LAST:event_tblTablaMouseReleased

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        int fila = tblEmpleados.getSelectedRow();
        if (fila != -1) {
            quitarEmpleado(fila);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void txtEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            lblBusqueda.setBusy(true);
            buscar();
            lblBusqueda.setBusy(false);
        }
    }//GEN-LAST:event_txtEmpleadoKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DlgEmpleado dialogo = new DlgEmpleado(this);
        dialogo.setAgregar(false);
        this.empleadoSeleccionado = dialogo.getSeleccionado();
        if (this.empleadoSeleccionado != null) {
            this.txtEmpleado.setText(
                    empleadoSeleccionado.getNroDocumento()
                    + " " + empleadoSeleccionado.getApellidoPaterno()
                    + " " + empleadoSeleccionado.getApellidoMaterno()
                    + " " + empleadoSeleccionado.getNombre());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        FormularioUtil.limpiarComponente(this.txtEmpleado);
        this.empleadoSeleccionado = null;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        System.out.println("BUSCAR");
        lblBusqueda.setBusy(true);
        paginaActual = 1;
        buscar();
        actualizarControlesNavegacion();
        lblBusqueda.setBusy(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

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

    private void tblEmpleadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseReleased
        // TODO add your handling code here:
        int fila;
        if ((fila = tblEmpleados.getSelectedRow()) != -1) {
            mostrarRecord(integrantes.get(fila));
        }
    }//GEN-LAST:event_tblEmpleadosMouseReleased

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JComboBox cboCondicion;
    private javax.swing.JComboBox cboTamanio;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXBusyLabel lblBusqueda;
    private javax.swing.ButtonGroup opciones;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlNavegacion;
    private javax.swing.JPanel pnlOpcion;
    private javax.swing.JSpinner spFechaFin1;
    private javax.swing.JSpinner spFechaInicio1;
    private javax.swing.JSpinner spPagina;
    private org.jdesktop.swingx.JXTable tblEmpleados;
    private org.jdesktop.swingx.JXTable tblTabla;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void mostrar(DetalleCondicion detalle) {
        dcFechaInicio.setDate(detalle.getFechaInicio());
        dcFechaFin.setDate(detalle.getFechaFin());
        cboCondicion.setSelectedItem(detalle.getCondicionId());

        List<String> listaDNI = obtenerListadoDNI(detalle);
        if (!listaDNI.isEmpty()) {
            mostrarIntegrantes(listaDNI);
        }

    }
    private List<Condicion> condicionList;
    private void bindeoSalvaje() {
        listado = new ArrayList<>();
        listado = ObservableCollections.observableList(listado);
        condicionList = cc.buscarTodos();
        integrantes = ObservableCollections.observableList(new ArrayList<Empleado>());

        String[] columnasIntegrantes = {"Nro Documento", "Empleado"};
        JComboBoxBinding bindingCondicion = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ, condicionList, cboCondicion);
        bindingCondicion.bind();
        MTDetalleCondicion mt = new MTDetalleCondicion(listado);
        MTEmpleado mtIntegrantes = new MTEmpleado(integrantes, columnasIntegrantes);
        tblTabla.setModel(mt);
        tblEmpleados.setModel(mtIntegrantes);
        cboCondicion.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Condicion) {
                    value = ((Condicion) value).getNombre();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        actualizarTabla();
    }

    private void actualizarTabla() {
        listado.clear();
        empleadoSeleccionado = null;
        FormularioUtil.limpiarComponente(txtEmpleado);
        paginaActual = 1;
        buscar();
        actualizarControlesNavegacion();
        tblTabla.packAll();
    }

    private void mostrarIntegrantes(List<String> listadoDNI) {
        integrantes.clear();
        integrantes.addAll(ec.buscarPorLista(listadoDNI));
        tblEmpleados.packAll();
    }

    private void inicializar() {
        this.accion = 0;

        controlador = new PermisoControlador();
        ec = new EmpleadoControlador();
        cc = new CondicionControlador();
        ac = new AsignacionPermisoControlador();
        dcc = new DetalleCondicionControlador();
        FormularioUtil.modeloSpinnerFechaHora(spFechaInicio1, "dd/MM/yyyy");
        FormularioUtil.modeloSpinnerFechaHora(spFechaFin1, "dd/MM/yyyy");
        this.controles(accion);
    }

    private void controles(int accion) {
        boolean bandera = accion == Controlador.NUEVO || accion == Controlador.MODIFICAR;

        FormularioUtil.activarComponente(this.pnlListado, !bandera);
        FormularioUtil.activarComponente(this.pnlDatos, bandera);

        if (accion != Controlador.MODIFICAR) {
            FormularioUtil.limpiarComponente(this.pnlDatos);
        }

        checkPorFecha(accion);
    }

    private List<String> obtenerListadoDNI(DetalleCondicion detalle) {
        List<String> listadoDNI = new ArrayList<>();
        
            listadoDNI.add(detalle.getEmpleadoNroDocumento());
        
        return listadoDNI;
    }

    public void agregarEmpleado(Empleado empleado) {
        integrantes.add(empleado);
        dcc.getSeleccionado().setEmpleadoNroDocumento(empleado.getNroDocumento());
    }

    private void quitarEmpleado(int fila) {
        integrantes.remove(fila);
    }

    private int paginaActual = 1;
    private int totalPaginas = 0;
    private int tamanioPagina = 0;

    private void buscar() {
        String busqueda = txtEmpleado.getText();
        tamanioPagina = Integer.parseInt(cboTamanio.getSelectedItem().toString());

        Date fechaInicio = (Date) spFechaInicio1.getValue();
        Date fechaFin = (Date) spFechaFin1.getValue();
        listado.clear();
        List<DetalleCondicion> lista = this.listar(empleadoSeleccionado, fechaInicio, fechaFin, paginaActual, tamanioPagina);
        System.out.println("LISTA: " + lista.size());
        listado.addAll(lista);

        tblTabla.packAll();
    }

    private List<DetalleCondicion> listar(Empleado empleado, Date fechaInicio, Date fechaFin, int pagina, int tamanio) {
        int total;

        if (empleado == null) {
            total = dcc.contarXFecha(fechaInicio, fechaFin);
        } else {
            total = dcc.contarXEmpleadoXFecha(empleado.getNroDocumento(), fechaInicio, fechaFin);
        }

        if (total % tamanio == 0) {
            totalPaginas = total / tamanio;
        } else {
            totalPaginas = (total / tamanio) + 1;
        }

        if (totalPaginas == 0) {
            totalPaginas = 1;
        }

        if (empleado == null) {
            return dcc.buscarXFecha(fechaInicio, fechaFin, (pagina - 1) * tamanio, tamanio);
        } else {
            return dcc.buscarXEmpleadoXFecha(empleado.getNroDocumento(), fechaInicio, fechaFin, (pagina - 1) * tamanio, tamanio);
        }

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
    private final DateFormat dfFecha = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm");

    private void checkPorFecha(int accion) {
        if (accion != 0) {
            FormularioUtil.activarComponente(dcFechaInicio, true);
        }
    }

    private boolean erroresFormulario() {
        int errores = 0;
        Date fechaInicio = dcFechaInicio.getDate();

        String mensaje = "";
        if (integrantes.isEmpty()) {
            errores++;
            mensaje = ">Debe seleccionar uno o mas empleados\n";
        }
        if(dcFechaInicio.getDate()==null){
            errores++;
            mensaje = ">Debes escoger al menos una fecha inicio\n";
        }
        if(dcFechaFin.getDate()!=null){
            Date fechaFin = dcFechaFin.getDate();
            if (fechaInicio.compareTo(fechaFin) > 0) {
                errores++;
                mensaje = ">La fecha de inicio debe ser menor que la fecha de fin\n";
            }
        }
            
        //Traemos los dnis de los empleados
        if(accion==1){
            DetalleCondicion paraComprobar = dcc.getSeleccionado();
            System.out.println("Para comprobar: "+paraComprobar.getEmpleadoNroDocumento());
            //System.out.println(asignacion.getEmpleado());
            List<DetalleCondicion> lista = dcc.buscarXFechaDni(paraComprobar.getEmpleadoNroDocumento(), fechaInicio);
            //System.out.println("Fecha y hora: "+fechaInicio.toString());
            if(lista.isEmpty()){

            }else{
               errores++;
               mensaje = "El empleado "+paraComprobar.getEmpleadoNroDocumento()+" tiene una condición asignada \n Ingrese una fecha fin a la condición anterior \n O ingrese otro rango de fechas \n";
            }

        }

        if (errores > 0) {
            JOptionPane.showMessageDialog(this, "Se ha(n) encontrado el(los) siguiente(s) error(es):\n" + mensaje, "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return errores != 0;
    }

    private void mostrarRecord(Empleado empleado) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(empleado.getFechaInicioContrato());

        Date fInicio = dcFechaInicio.getDate();

    }
}

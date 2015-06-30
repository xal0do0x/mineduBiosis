/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes;

import algoritmo.AnalisisAsistencia;
import com.lowagie.text.DocumentException;
import controladores.AsignacionHorarioControlador;
import controladores.DetalleGrupoControlador;
import controladores.EmpleadoControlador;
import controladores.GrupoHorarioControlador;
import controladores.PeriodoControlador;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.GrupoHorario;
import entidades.Periodo;
import vistas.dialogos.DlgEmpleado;
import vistas.modelos.MTEmpleado;
import com.personal.utiles.FormularioUtil;
import com.personal.utiles.ReporteUtil;
import controladores.DepartamentoControlador;
import controladores.MarcacionControlador;
import entidades.Departamento;
import entidades.EmpleadoBiostar;
import java.awt.Component;
import java.io.File;
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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import utiles.UsuarioActivo;
import vista.reportes.procesos.rptAsistenciaEntrada;
import vista.reportes.procesos.rptAsistenciaTotal;
import vistas.dialogos.DlgOficina;

/**
 *
 * @author Aldo
 */
public class RptAsistenciaTotal extends javax.swing.JInternalFrame {

    /**
     * Creates new form RptRegistroAsistencia
     */
    private final ReporteUtil reporteador;
    private final DateFormat dfFecha;
    private final EmpleadoControlador ec;

    public RptAsistenciaTotal() {
        initComponents();

        ec = new EmpleadoControlador();
        pc = new PeriodoControlador();
        dc = new DepartamentoControlador();
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        reporteador = new ReporteUtil();
//        FormularioUtil.modeloSpinnerFechaHora(spFechaInicio, "dd/MM/yyyy");
//        FormularioUtil.modeloSpinnerFechaHora(spFechaFin, "dd/MM/yyyy");
        inicializar();
        bindeoSalvaje();
        controles();
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

        grpRango = new javax.swing.ButtonGroup();
        grpSeleccion = new javax.swing.ButtonGroup();
        pnlRango = new javax.swing.JPanel();
        radPorFecha = new javax.swing.JRadioButton();
        radMes = new javax.swing.JRadioButton();
        cboMes = new com.toedter.calendar.JMonthChooser();
        cboPeriodo1 = new javax.swing.JComboBox();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        pnlRango1 = new javax.swing.JPanel();
        radPorComp = new javax.swing.JRadioButton();
        radMarcReales = new javax.swing.JRadioButton();
        pnlEmpleados = new javax.swing.JPanel();
        radGrupo = new javax.swing.JRadioButton();
        radPersonalizado = new javax.swing.JRadioButton();
        cboGrupoHorario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new org.jdesktop.swingx.JXTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        radOficina = new javax.swing.JRadioButton();
        txtOficina = new javax.swing.JTextField();
        btnOficina = new javax.swing.JButton();
        pnlBotones = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        pnlTab = new javax.swing.JTabbedPane();
        grpRango.add(radPorFecha);
        grpRango.add(radMes);

        grpSeleccion.add(radGrupo);
        grpSeleccion.add(radPersonalizado);
        grpSeleccion.add(radOficina);

        setClosable(true);
        setMaximizable(true);
        setTitle("REPORTE DE REGISTRO DE ASISTENCIA");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlRango.setBorder(javax.swing.BorderFactory.createTitledBorder("Rango"));
        pnlRango.setLayout(new java.awt.GridBagLayout());

        radPorFecha.setSelected(true);
        radPorFecha.setText("Por fechas:");
        radPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPorFechaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango.add(radPorFecha, gridBagConstraints);

        radMes.setText("Por mes:");
        radMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radMesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango.add(radMes, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango.add(cboMes, gridBagConstraints);

        cboPeriodo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango.add(cboPeriodo1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango.add(dcFechaInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        pnlRango.add(dcFechaFin, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(pnlRango, gridBagConstraints);

        pnlRango1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rango"));
        pnlRango1.setLayout(new java.awt.GridBagLayout());

        radPorComp.setText("Mostrar compensación?");
        radPorComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPorCompActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango1.add(radPorComp, gridBagConstraints);

        radMarcReales.setText("Mostrar marcaciones de salida?");
        radMarcReales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radMarcRealesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlRango1.add(radMarcReales, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(pnlRango1, gridBagConstraints);

        pnlEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección de empleados"));
        pnlEmpleados.setLayout(new java.awt.GridBagLayout());

        radGrupo.setText("Por grupo horario:");
        radGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGrupoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlEmpleados.add(radGrupo, gridBagConstraints);

        radPersonalizado.setSelected(true);
        radPersonalizado.setText("Personalizado:");
        radPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPersonalizadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlEmpleados.add(radPersonalizado, gridBagConstraints);

        cboGrupoHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboGrupoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGrupoHorarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlEmpleados.add(cboGrupoHorario, gridBagConstraints);

        tblTabla.setPreferredScrollableViewportSize(new java.awt.Dimension(0, 300));
        jScrollPane1.setViewportView(tblTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        pnlEmpleados.add(jScrollPane1, gridBagConstraints);

        btnAgregar.setText("+");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlEmpleados.add(btnAgregar, gridBagConstraints);

        btnQuitar.setText("-");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlEmpleados.add(btnQuitar, gridBagConstraints);

        radOficina.setText("Por oficina:");
        radOficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radOficinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlEmpleados.add(radOficina, gridBagConstraints);

        txtOficina.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlEmpleados.add(txtOficina, gridBagConstraints);

        btnOficina.setText("...");
        btnOficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOficinaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        pnlEmpleados.add(btnOficina, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(pnlEmpleados, gridBagConstraints);

        pnlBotones.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("GENERAR REPORTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlBotones.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(pnlBotones, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(pnlTab, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            imprimir();
        } catch (IOException ex) {
            Logger.getLogger(RptAsistenciaTotal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(RptAsistenciaTotal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        DlgEmpleado dialogo = new DlgEmpleado(this);
        dialogo.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void radMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radMesActionPerformed
        // TODO add your handling code here:
        controles();
    }//GEN-LAST:event_radMesActionPerformed

    private void radPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPorFechaActionPerformed
        // TODO add your handling code here:
        controles();
    }//GEN-LAST:event_radPorFechaActionPerformed

    private GrupoHorario grupoSeleccionado;
    private void cboGrupoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGrupoHorarioActionPerformed
        // TODO add your handling code here:
        obtenerGrupo();
    }//GEN-LAST:event_cboGrupoHorarioActionPerformed

    private void radGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGrupoActionPerformed
        // TODO add your handling code here:
        controles();
    }//GEN-LAST:event_radGrupoActionPerformed

    private void radPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPersonalizadoActionPerformed
        // TODO add your handling code here:
        controles();
    }//GEN-LAST:event_radPersonalizadoActionPerformed

    private void radOficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radOficinaActionPerformed
        // TODO add your handling code here:
        controles();
    }//GEN-LAST:event_radOficinaActionPerformed

    private void btnOficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOficinaActionPerformed
        // TODO add your handling code here:
        DlgOficina oficinas = new DlgOficina(this);
        oficinaSeleccionada = oficinas.getSeleccionado();
        if (oficinaSeleccionada != null) {
            txtOficina.setText(oficinaSeleccionada.getNombre());

        }
    }//GEN-LAST:event_btnOficinaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        int fila;
        if((fila = tblTabla.getSelectedRow()) != -1){
            empleadoList.remove(fila);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void radPorCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPorCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radPorCompActionPerformed

    private void radMarcRealesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radMarcRealesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radMarcRealesActionPerformed

    private Departamento oficinaSeleccionada;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnOficina;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cboGrupoHorario;
    private com.toedter.calendar.JMonthChooser cboMes;
    private javax.swing.JComboBox cboPeriodo1;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.ButtonGroup grpRango;
    private javax.swing.ButtonGroup grpSeleccion;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlEmpleados;
    private javax.swing.JPanel pnlRango;
    private javax.swing.JPanel pnlRango1;
    private javax.swing.JTabbedPane pnlTab;
    private javax.swing.JRadioButton radGrupo;
    private javax.swing.JRadioButton radMarcReales;
    private javax.swing.JRadioButton radMes;
    private javax.swing.JRadioButton radOficina;
    private javax.swing.JRadioButton radPersonalizado;
    private javax.swing.JRadioButton radPorComp;
    private javax.swing.JRadioButton radPorFecha;
    private org.jdesktop.swingx.JXTable tblTabla;
    private javax.swing.JTextField txtOficina;
    // End of variables declaration//GEN-END:variables

    private List<Empleado> empleadoList;
    private List<Periodo> periodoList;
    private final PeriodoControlador pc;
    private final DepartamentoControlador dc;

    private void inicializar() {
        JasperViewer jv = new JasperViewer(null);
        pnlTab.add("Vista previa", jv.getContentPane());
        empleadoList = ObservableCollections.observableList(new ArrayList<Empleado>());
        periodoList = pc.buscarTodosOrden();
        grupoList = gc.buscarTodos();
    }

    private void controles() {
//        FormularioUtil.activarComponente(chkMarcaciones, radDetallado.isSelected());

        FormularioUtil.activarComponente(dcFechaInicio, radPorFecha.isSelected());
        FormularioUtil.activarComponente(dcFechaFin, radPorFecha.isSelected());
        FormularioUtil.activarComponente(cboMes, radMes.isSelected());
        FormularioUtil.activarComponente(cboPeriodo1, radMes.isSelected());

        FormularioUtil.activarComponente(cboGrupoHorario, radGrupo.isSelected());
//        FormularioUtil.activarComponente(btnOficina, radGrupo.isSelected());
        FormularioUtil.activarComponente(tblTabla, radPersonalizado.isSelected());
        FormularioUtil.activarComponente(btnAgregar, radPersonalizado.isSelected());
        FormularioUtil.activarComponente(btnQuitar, radPersonalizado.isSelected());
        

        FormularioUtil.activarComponente(btnOficina, radOficina.isSelected());
    }

    private List<GrupoHorario> grupoList;

    private void bindeoSalvaje() {
        MTEmpleado mt = new MTEmpleado(empleadoList);
        tblTabla.setModel(mt);

        BindingGroup bindeo = new BindingGroup();

        JComboBoxBinding binding2 = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ, periodoList, cboPeriodo1);
        JComboBoxBinding binding3 = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ, grupoList, cboGrupoHorario);

        bindeo.addBinding(binding2);
        bindeo.addBinding(binding3);
        bindeo.bind();

        DefaultListCellRenderer renderGrupo = new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value != null) {
                    if (value instanceof GrupoHorario) {
                        value = ((GrupoHorario) value).getNombre();
                    }
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
            }

        };
        DefaultListCellRenderer renderPeriodo = new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value != null) {
                    if (value instanceof Periodo) {
                        value = ((Periodo) value).getAnio();
                    }
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }

        };

        cboPeriodo1.setRenderer(renderPeriodo);
        cboGrupoHorario.setRenderer(renderGrupo);
    }
    private AnalisisAsistencia analisis = new AnalisisAsistencia();
    private final MarcacionControlador mc = new MarcacionControlador();

    private void imprimir() throws IOException, DocumentException {

        Calendar cal = Calendar.getInstance();

        String usuario = UsuarioActivo.getUsuario().getLogin();

        //List<Empleado> empleados;

        List<String> dnis = obtenerDNI();
        //empleados = this.ec.buscarPorLista(dnis);
//        for(Empleado empleado : empleados){
//            System.out.println("Empleado a imprimir: "+empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno());
//        }
//        

        int anio;
        int mes;
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        String rangoTitulo = "";
        String rangoValor = "";
        boolean isSelectedComp = false;
        boolean isSelectedHora = false;
        if (radPorFecha.isSelected()) {
            rangoTitulo = "ENTRE: ";
            fechaInicio = dcFechaInicio.getDate();
            fechaFin = dcFechaFin.getDate();
            rangoValor = dfFecha.format(fechaInicio) + " - " + dfFecha.format(fechaFin);
        } else if (radMes.isSelected()) {
            rangoTitulo = "MES: ";
            anio = periodoList.get(cboPeriodo1.getSelectedIndex()).getAnio();
            mes = cboMes.getMonth();
            cal.set(anio, mes, 1);
            fechaInicio = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            fechaFin = cal.getTime();
            rangoValor = (cboMes.getMonth() + 1) + " / " + anio;
        } 
       
        String grupoOficina = "";
        String tipo = "";
        if(radOficina.isSelected()){
            if(oficinaSeleccionada!=null){
                if (oficinaSeleccionada.getNombre()!=null) {
                    grupoOficina = oficinaSeleccionada.getNombre();
                    tipo = "O";
                }
            }
        }else if(radPersonalizado.isSelected()){
            grupoOficina = dc.buscarXDni(Integer.parseInt(dnis.get(0))).get(0).getNombre();
            tipo = "P";
        }else if(radGrupo.isSelected()){
            grupoOficina = grupoSeleccionado.getNombre();
            tipo="G";
        }
        
        //Validamos si se quiere mostrar la columna de compensaciones y si se quiere ver las horas reales de marcaciones de salida
        if(radPorComp.isSelected()){
            isSelectedComp = true;
        }
        if(radMarcReales.isSelected()){
            isSelectedHora = true;
        }
        
        String fechaImpreso = pruebareportes.ReporteUtil.obtenerFechaFormateada(fechaInicio,"-");
        rptAsistenciaTotal rptAsisE = new rptAsistenciaTotal();
        if(oficinaSeleccionada!=null){
            rptAsisE.crearPdf("RptAstTotal "+oficinaSeleccionada.getNombre().split(" ", 0)[0]+fechaImpreso+".pdf", dnis, fechaInicio, fechaFin, grupoOficina, tipo, usuario, isSelectedComp, isSelectedHora);
        }else{
            rptAsisE.crearPdf("RptAstTotal"+"Personal"+fechaImpreso+".pdf", dnis, fechaInicio, fechaFin, grupoOficina, tipo, usuario, isSelectedComp, isSelectedHora);
        }
        
    }

    boolean bandera = false;

    private List<String> obtenerDNI() {

        List<String> lista = new ArrayList<>();
        if (radGrupo.isSelected()) {
            obtenerGrupo();
            List<DetalleGrupoHorario> detalleGrupo = dgc.buscarXGrupo(grupoSeleccionado);
            for (DetalleGrupoHorario detalle : detalleGrupo) {
                lista.add(detalle.getEmpleado());
            }
        } else if (radPersonalizado.isSelected()) {
            for (Empleado empleado : empleadoList) {
                lista.add(empleado.getNroDocumento());
                System.out.println("Empleado de Oficina: " + empleado.getNombre() + " " + empleado.getNroDocumento());
            }
        } else if (radOficina.isSelected()) {
            List<EmpleadoBiostar> empleadoBiostar = oficinaSeleccionada.getEmpleadoList();
//            for (EmpleadoBiostar empleadoBiostar1 : empleadoBiostar) {
//                System.out.println("Empleado de Oficina: " + empleadoBiostar1.getNombre() + " " + empleadoBiostar1.getId());
//            }
            List<Integer> dniInt = dniInteger(empleadoBiostar);
            List<Empleado> empleados = ec.buscarPorListaEnteros(dniInt);
            for (Empleado empleado : empleados) {
                lista.add(empleado.getNroDocumento());
//                System.out.println("Empleado tomando en cuenta: "+empleado.getNroDocumento());
            }
        }

        return lista;
    }

    private List<Integer> dniInteger(List<EmpleadoBiostar> empleados) {
        List<Integer> dni = new ArrayList<>();
        for (EmpleadoBiostar e : empleados) {
            dni.add(e.getId());
        }
        return dni;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleadoList.add(empleado);
        tblTabla.packAll();
    }

    private GrupoHorarioControlador gc = new GrupoHorarioControlador();
    private DetalleGrupoControlador dgc = new DetalleGrupoControlador();

    private void obtenerGrupo() {
        int seleccionado = cboGrupoHorario.getSelectedIndex();
        if (seleccionado != -1) {
            grupoSeleccionado = this.grupoList.get(seleccionado);
        }
    }
}

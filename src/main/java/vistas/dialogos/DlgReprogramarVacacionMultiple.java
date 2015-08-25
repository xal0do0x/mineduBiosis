/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.dialogos;

import com.personal.utiles.FormularioUtil;
import controladores.Controlador;
import controladores.EmpleadoControlador;
import controladores.VacacionControlador;
import entidades.Empleado;
import entidades.Vacacion;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import utiles.Utilitario;

/**
 *
 * @author Francis
 */
public class DlgReprogramarVacacionMultiple extends javax.swing.JDialog {

    /**
     * Creates new form DlgReprogramarVacacionMultiple
     */
    private final Vacacion vacacionOrigen;
    private final VacacionControlador vacc;
    private final EmpleadoControlador empc;
    private final List<Vacacion> reprogramacionList;

    public DlgReprogramarVacacionMultiple(java.awt.Frame parent, Vacacion vacacionOrigen) {
        super(parent, true);
        initComponents();
        //INSTANCIAMOS
        this.vacacionOrigen = vacacionOrigen;
        this.vacc = new VacacionControlador();
        this.empc = new EmpleadoControlador();
        
        this.reprogramacionList = ObservableCollections.observableList(new ArrayList<Vacacion>());
        List<Vacacion> vacacionList = vacacionOrigen.getVacacionList();
        for(int i = 0; i < vacacionList.size(); i++){
            this.reprogramacionList.add(vacacionList.get(i));
        }
        
        //INICIALIZAMOS
        inicializarComponentes();
        //MOSTRAMOS DATA
        activarComponentes(false);
        mostrarDatosVacacion(vacacionOrigen);
//        actualizarDiasHabilesRestantes();
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
        pnlVacacionDatosGenerales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmpleadoDatos = new javax.swing.JTextField();
        dcVacacionFechaInicio = new com.toedter.calendar.JDateChooser();
        dcVacacionFechaFin = new com.toedter.calendar.JDateChooser();
        dcReprogramacionFechaInterrupcion = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        btnIniciarReprogramacion = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtVacacionDocumento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtReprogramacionDocumento = new javax.swing.JTextField();
        radInterrupcion = new javax.swing.JRadioButton();
        radTotal = new javax.swing.JRadioButton();
        pnlReprogramacionDatos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dcReprogramacionFechaInicio = new com.toedter.calendar.JDateChooser();
        dcReprogramacionFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtVacacionDiasRestantes = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnAgregarReprogramacion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReprogramaciones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        grpOpcion.add(radInterrupcion);
        grpOpcion.add(radTotal);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reprogramación de vacaciones");

        pnlVacacionDatosGenerales.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlVacacionDatosGenerales.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Empleado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Fecha original de inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Fecha original de fin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(jLabel3, gridBagConstraints);

        txtEmpleadoDatos.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(txtEmpleadoDatos, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(dcVacacionFechaInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(dcVacacionFechaFin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(dcReprogramacionFechaInterrupcion, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnIniciarReprogramacion.setText("Iniciar reprogramación");
        btnIniciarReprogramacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarReprogramacionActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciarReprogramacion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlVacacionDatosGenerales.add(jPanel1, gridBagConstraints);

        jLabel8.setText("Documento de vacación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(jLabel8, gridBagConstraints);

        txtVacacionDocumento.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(txtVacacionDocumento, gridBagConstraints);

        jLabel9.setText("Documento de reprogramación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(txtReprogramacionDocumento, gridBagConstraints);

        radInterrupcion.setSelected(true);
        radInterrupcion.setText("Goce de vacaciones hasta:");
        radInterrupcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radInterrupcionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(radInterrupcion, gridBagConstraints);

        radTotal.setText("Reprogramación total de vacaciones");
        radTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTotalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlVacacionDatosGenerales.add(radTotal, gridBagConstraints);

        getContentPane().add(pnlVacacionDatosGenerales, java.awt.BorderLayout.PAGE_START);

        pnlReprogramacionDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlReprogramacionDatos.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Fecha de inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Fecha de fin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(dcReprogramacionFechaInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(dcReprogramacionFechaFin, gridBagConstraints);

        jLabel7.setText("Días hábiles restantes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(jLabel7, gridBagConstraints);

        txtVacacionDiasRestantes.setEditable(false);
        txtVacacionDiasRestantes.setColumns(4);
        txtVacacionDiasRestantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVacacionDiasRestantesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlReprogramacionDatos.add(txtVacacionDiasRestantes, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnAgregarReprogramacion.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAgregarReprogramacion.setText(">>");
        btnAgregarReprogramacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarReprogramacionActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregarReprogramacion);

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        pnlReprogramacionDatos.add(jPanel3, gridBagConstraints);

        getContentPane().add(pnlReprogramacionDatos, java.awt.BorderLayout.LINE_START);

        tblReprogramaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReprogramaciones);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnGuardarCambios.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnGuardarCambios.setText("Guardar cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardarCambios);

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtVacacionDiasRestantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVacacionDiasRestantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVacacionDiasRestantesActionPerformed

    private void btnIniciarReprogramacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarReprogramacionActionPerformed
        // TODO add your handling code here:
        //VERIFICAMOS QUE LA FECHA DE INTERRUPCIÓN CORRESPONDA CON UNA BUENA FECHA    
        this.activarComponentes(true);
        this.actualizarDiasHabilesRestantes();
    }//GEN-LAST:event_btnIniciarReprogramacionActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtVacacionDiasRestantes.getText()) > 0) {
            JOptionPane.showMessageDialog(this, "Debe completar la reprogramación de vacaciones", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("REVISANDO CRUCES");
        if(hayCruce()){
            System.out.println("HAY CRUCES");
            return;
        }
        System.out.println("NO HAY CRUCES");
        
        this.vacacionOrigen.setHayReprogramacion(true);
        this.vacacionOrigen.setDocumentoReprogramacion(txtReprogramacionDocumento.getText());
        this.vacacionOrigen.setFechaInterrupcion(dcReprogramacionFechaInterrupcion.getDate());
        this.vacacionOrigen.setReprogramacionTotal(!radInterrupcion.isSelected());
        vacc.setSeleccionado(this.vacacionOrigen);
        if(vacc.accion(Controlador.MODIFICAR)){
            System.out.println("SE GUARDÓ CORRECTAMENTE");
            FormularioUtil.mensajeExito(this, Controlador.MODIFICAR);
            this.dispose();
        }else{
            System.out.println("HUBO UN ERROR");
            FormularioUtil.mensajeError(this, Controlador.MODIFICAR);
        }
                
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnAgregarReprogramacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarReprogramacionActionPerformed
        // TODO add your handling code here:
        //COMPROBAMOS QUE LAS FECHAS NO SEAN VACÍAS
        if (dcReprogramacionFechaInicio.getDate() == null || dcReprogramacionFechaFin.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Defina una fecha válida", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!dcReprogramacionFechaInicio.getDate().before(dcReprogramacionFechaFin.getDate())) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser menor a la fecha de fin", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.obtenerDiasHabiles(dcReprogramacionFechaInicio.getDate(), dcReprogramacionFechaFin.getDate()) > Integer.parseInt(txtVacacionDiasRestantes.getText())) {
            JOptionPane.showMessageDialog(this, "El intervalo seleccionado excede los días restantes", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vacacion vacacionReprogramada = new Vacacion();
        vacacionReprogramada.setDocumento(txtReprogramacionDocumento.getText());
        vacacionReprogramada.setEmpleado(this.vacacionOrigen.getEmpleado());
        vacacionReprogramada.setFechaInicio(dcReprogramacionFechaInicio.getDate());
        vacacionReprogramada.setFechaFin(dcReprogramacionFechaFin.getDate());
        vacacionReprogramada.setPeriodo(vacacionOrigen.getPeriodo());
        vacacionReprogramada.setVacacionOrigen(vacacionOrigen);
        this.reprogramacionList.add(vacacionReprogramada);
        this.vacacionOrigen.getVacacionList().add(vacacionReprogramada);

        this.actualizarDiasHabilesRestantes();
    }//GEN-LAST:event_btnAgregarReprogramacionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea cancelar esta acción?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int fila = tblReprogramaciones.getSelectedRow();

        if (fila != -1) {
            if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar esta reprogramación?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                return;
            }
            this.reprogramacionList.remove(fila);
            this.vacacionOrigen.getVacacionList().remove(fila);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radInterrupcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radInterrupcionActionPerformed
        // TODO add your handling code here:
        cambioOpcion(false);
    }//GEN-LAST:event_radInterrupcionActionPerformed

    private void radTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTotalActionPerformed
        // TODO add your handling code here:
        cambioOpcion(false);
    }//GEN-LAST:event_radTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //</editor-fold>
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equalsIgnoreCase(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VacacionControlador vacc = new VacacionControlador();
                Vacacion vacacion = vacc.buscarPorId(6489L);
                DlgReprogramarVacacionMultiple dialog = new DlgReprogramarVacacionMultiple(new javax.swing.JFrame(), vacacion);
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
    private javax.swing.JButton btnAgregarReprogramacion;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnIniciarReprogramacion;
    private com.toedter.calendar.JDateChooser dcReprogramacionFechaFin;
    private com.toedter.calendar.JDateChooser dcReprogramacionFechaInicio;
    private com.toedter.calendar.JDateChooser dcReprogramacionFechaInterrupcion;
    private com.toedter.calendar.JDateChooser dcVacacionFechaFin;
    private com.toedter.calendar.JDateChooser dcVacacionFechaInicio;
    private javax.swing.ButtonGroup grpOpcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlReprogramacionDatos;
    private javax.swing.JPanel pnlVacacionDatosGenerales;
    private javax.swing.JRadioButton radInterrupcion;
    private javax.swing.JRadioButton radTotal;
    private javax.swing.JTable tblReprogramaciones;
    private javax.swing.JTextField txtEmpleadoDatos;
    private javax.swing.JTextField txtReprogramacionDocumento;
    private javax.swing.JTextField txtVacacionDiasRestantes;
    private javax.swing.JTextField txtVacacionDocumento;
    // End of variables declaration//GEN-END:variables

    private void activarComponentes(boolean iniciarReprogramacion) {
        FormularioUtil.activarComponente(this.pnlVacacionDatosGenerales, !iniciarReprogramacion);
        FormularioUtil.activarComponente(pnlReprogramacionDatos, iniciarReprogramacion);
        FormularioUtil.activarComponente(this.txtEmpleadoDatos, false);
        FormularioUtil.activarComponente(this.txtVacacionDocumento, false);
        FormularioUtil.activarComponente(dcVacacionFechaInicio, false);
        FormularioUtil.activarComponente(dcVacacionFechaFin, false);
        FormularioUtil.activarComponente(txtVacacionDiasRestantes, false);
        cambioOpcion(iniciarReprogramacion);
    }

    private void inicializarComponentes() {
        Utilitario.ajustarDateChooser(dcVacacionFechaInicio, 12);
        Utilitario.ajustarDateChooser(dcVacacionFechaFin, 12);
        Utilitario.ajustarDateChooser(dcReprogramacionFechaInicio, 12);
        Utilitario.ajustarDateChooser(dcReprogramacionFechaFin, 12);
        Utilitario.ajustarDateChooser(dcReprogramacionFechaInterrupcion, 12);

        dcReprogramacionFechaInterrupcion.setMaxSelectableDate(this.vacacionOrigen.getFechaFin());
        dcReprogramacionFechaInterrupcion.setMinSelectableDate(this.vacacionOrigen.getFechaInicio());

        JTableBinding bindingReprogramacion = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ, this.reprogramacionList, tblReprogramaciones);
        BeanProperty pFechaInicio = BeanProperty.create("fechaInicio");
        BeanProperty pFechaFin = BeanProperty.create("fechaFin");

        bindingReprogramacion.addColumnBinding(pFechaInicio).setColumnName("Fecha inicio").setColumnClass(Date.class).setEditable(false);
        bindingReprogramacion.addColumnBinding(pFechaFin).setColumnName("Fecha fin").setColumnClass(Date.class).setEditable(false);

        bindingReprogramacion.bind();

        FechaRender fechaRender = new FechaRender();

        tblReprogramaciones.getColumnModel().getColumn(0).setCellRenderer(fechaRender);
        tblReprogramaciones.getColumnModel().getColumn(1).setCellRenderer(fechaRender);

    }

    private boolean hayCruce() {
        int cruces = 0;
        String mensaje = "Se encontraron los siguientes cruces de fechas:\n";
        DateFormat dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        for(int i = 0; i < reprogramacionList.size(); i++){
            Date fechaInicio = reprogramacionList.get(i).getFechaInicio();
            Date fechaFin = reprogramacionList.get(i).getFechaFin();
            
            for(int j = 0; j < reprogramacionList.size(); j++){
                Date fechaInicioComp = reprogramacionList.get(j).getFechaInicio();
                Date fechaFinComp = reprogramacionList.get(j).getFechaFin();
                if(i != j){
                    if((fechaInicio.compareTo(fechaInicioComp) <= 0 && fechaFin.compareTo(fechaInicioComp) >= 0) || (fechaInicio.compareTo(fechaInicioComp) >= 0 && fechaInicio.compareTo(fechaFinComp) <= 0)){
                        cruces++;
                        mensaje+= String.format("- Reprogramación desde: %s hasta: %s con reprogramación desde: %s hasta: %s\n", dfFecha.format(fechaInicio),dfFecha.format(fechaFin),dfFecha.format(fechaInicioComp),dfFecha.format(fechaFinComp));
                    }
                }
            }
        }
        
        if(cruces > 0){
            JOptionPane.showMessageDialog(this, mensaje, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
        }
        
        return cruces > 0;
    }

    private void cambioOpcion(boolean iniciarReprogramacion) {
        FormularioUtil.activarComponente(this.dcReprogramacionFechaInterrupcion, this.radInterrupcion.isSelected() && !iniciarReprogramacion);
    }
    
    

    private class FechaRender extends DefaultTableCellRenderer {

        private final DateFormat dfFecha;

        public FechaRender() {
            dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                if (value instanceof Date) {
                    value = dfFecha.format((Date) value);
                }
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private void mostrarDatosVacacion(Vacacion vacacionOrigen) {
        Empleado empleado = empc.buscarPorDni(vacacionOrigen.getEmpleado());
        txtEmpleadoDatos.setText(String.format("%s - %s %s %s", empleado.getNroDocumento(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno(), empleado.getNombre()));
        txtVacacionDocumento.setText(vacacionOrigen.getDocumento());
        dcVacacionFechaInicio.setDate(vacacionOrigen.getFechaInicio());
        dcVacacionFechaFin.setDate(vacacionOrigen.getFechaFin());
    }

    private void actualizarDiasHabilesRestantes() {
        //RECORREMOS FECHA A FECHA Y COMPARAMOS SI ES DIA HABIL O NO
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(radInterrupcion.isSelected() ? dcReprogramacionFechaInterrupcion.getDate() : dcVacacionFechaFin.getDate());
//        calendar.add(Calendar.DATE, -1);

        int acumuladoReprogramacion = acumuladoReprogramacion();
        int diasHabilesOriginal = obtenerDiasHabiles(vacacionOrigen.getFechaInicio(), calendar.getTime());

        int diasRestantes = diasHabilesOriginal - acumuladoReprogramacion;

        txtVacacionDiasRestantes.setText(diasRestantes + "");
    }

    private int acumuladoReprogramacion() {
        int acumulado = 0;
//        List<Vacacion> reprogramacionList = vacacionOrigen.getVacacionList();
        for (Vacacion vacacion : reprogramacionList) {
            acumulado += obtenerDiasHabiles(vacacion.getFechaInicio(), vacacion.getFechaFin());
        }
        return acumulado;
    }

    private int obtenerDiasHabiles(Date fechaInicio, Date fechaFin) {
        Calendar iterador = Calendar.getInstance();
        iterador.setTime(fechaInicio);
        int contador = 0;
        while (!iterador.getTime().after(fechaFin)) {
            if (iterador.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && iterador.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                contador++;
            }
            iterador.add(Calendar.DATE, 1);
        }
        return contador;
    }
}
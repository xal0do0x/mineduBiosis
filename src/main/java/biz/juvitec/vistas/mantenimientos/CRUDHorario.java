/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.mantenimientos;

import biz.juvitec.controladores.Controlador;
import biz.juvitec.controladores.HorarioControlador;
import biz.juvitec.controladores.JornadaControlador;
import biz.juvitec.entidades.Horario;
import biz.juvitec.entidades.Jornada;
import biz.juvitec.vistas.dialogos.DlgJornada;
import biz.juvitec.vistas.modelos.MTHorario;
import biz.juvitec.vistas.modelos.MTJornada;
import com.personal.utiles.FormularioUtil;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.text.DateFormatter;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author fesquivelc
 */
public class CRUDHorario extends javax.swing.JInternalFrame {

    /**
     * Creates new form CRUDJornada
     */
    private int accion;
    private final HorarioControlador horarioControlador;
    private List<Horario> horarioList;
    private Jornada jornadaSeleccionada;

    public CRUDHorario() {
        initComponents();
        horarioControlador = new HorarioControlador();
        bindeoSalvaje();
        accion = 0;
        this.controles(accion);
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

        pnlDatos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtJornada = new javax.swing.JTextField();
        btnJornada = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        chkLunes = new javax.swing.JCheckBox();
        chkMartes = new javax.swing.JCheckBox();
        chkMiercoles = new javax.swing.JCheckBox();
        chkJueves = new javax.swing.JCheckBox();
        chkViernes = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        chkDomingo = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHorario = new org.jdesktop.swingx.JXTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Mantenimiento Horario");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de jornada"));

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setText("Jornada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setText("Días laborales:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel9.setText("Código:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel1.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtCodigo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtNombre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtJornada, gridBagConstraints);

        btnJornada.setText("...");
        btnJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJornadaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel1.add(btnJornada, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        chkLunes.setText("Lunes");
        jPanel3.add(chkLunes);

        chkMartes.setText("Martes");
        jPanel3.add(chkMartes);

        chkMiercoles.setText("Miércoles");
        jPanel3.add(chkMiercoles);

        chkJueves.setText("Jueves");
        jPanel3.add(chkJueves);

        chkViernes.setText("Viernes");
        jPanel3.add(chkViernes);

        chkSabado.setText("Sábado");
        jPanel3.add(chkSabado);

        chkDomingo.setText("Domingo");
        jPanel3.add(chkDomingo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar);

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 127;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 12, 12);
        getContentPane().add(pnlDatos, gridBagConstraints);

        pnlListado.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios"));
        pnlListado.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificar);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlListado.add(jPanel4, gridBagConstraints);

        tblHorario.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHorario.setHorizontalScrollEnabled(true);
        tblHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHorarioMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblHorario);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        pnlListado.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 310;
        gridBagConstraints.ipady = 430;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 0);
        getContentPane().add(pnlListado, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        this.accion = Controlador.NUEVO;
        horarioControlador.prepararCrear();
        this.controles(accion);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int fila = this.tblHorario.getSelectedRow();
        if (fila != -1) {
            this.accion = Controlador.MODIFICAR;
            this.horarioControlador.setSeleccionado(this.horarioList.get(fila));
            this.mostrar(horarioControlador.getSeleccionado());
            this.controles(accion);
            FormularioUtil.activarComponente(txtCodigo, false);
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        this.accion = Controlador.ELIMINAR;
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (FormularioUtil.dialogoConfirmar(this, accion)) {
            Horario seleccionada = horarioControlador.getSeleccionado();
            FormularioUtil.convertirMayusculas(this.pnlDatos);
            if (accion == Controlador.NUEVO) {
                seleccionada.setCodigo(txtCodigo.getText());
            }
            seleccionada.setNombre(txtNombre.getText());
            seleccionada.setJornada(jornadaSeleccionada);
            seleccionada.setLunes(chkLunes.isSelected());
            seleccionada.setMartes(chkMartes.isSelected());
            seleccionada.setMiercoles(chkMiercoles.isSelected());
            seleccionada.setJueves(chkJueves.isSelected());
            seleccionada.setViernes(chkViernes.isSelected());
            seleccionada.setSabado(chkSabado.isSelected());
            seleccionada.setDomingo(chkDomingo.isSelected());          
            
            if(horarioControlador.accion(accion)){
                FormularioUtil.mensajeExito(this, accion);
                this.accion = 0;
                this.controles(accion);
                this.actualizarTabla();                
            }else{
                FormularioUtil.mensajeError(this, accion);
            }                        
            
        } else {
            return;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblHorarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHorarioMouseReleased
        // TODO add your handling code here:
        int fila = tblHorario.getSelectedRow();
        if(fila != -1){
            mostrar(horarioList.get(fila));
        }
        
    }//GEN-LAST:event_tblHorarioMouseReleased

    private void btnJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJornadaActionPerformed
        // TODO add your handling code here:
        DlgJornada dialogo = new DlgJornada(this);
        jornadaSeleccionada = dialogo.getJornadaSeleccionada();
        txtJornada.setText(jornadaSeleccionada.getNombre());
    }//GEN-LAST:event_btnJornadaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.accion = 0;
        this.controles(accion);
        FormularioUtil.limpiarComponente(this.pnlDatos);        
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnJornada;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkDomingo;
    private javax.swing.JCheckBox chkJueves;
    private javax.swing.JCheckBox chkLunes;
    private javax.swing.JCheckBox chkMartes;
    private javax.swing.JCheckBox chkMiercoles;
    private javax.swing.JCheckBox chkSabado;
    private javax.swing.JCheckBox chkViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlListado;
    private org.jdesktop.swingx.JXTable tblHorario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtJornada;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void modeloHoraSpinner(JSpinner spinner) {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
        spinner.setEditor(editor);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 7);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        spinner.setValue(cal.getTime());
    }

    private void modeloFechaSpinner(JSpinner spinner) {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
        spinner.setEditor(editor);
    }

    private void controles(int accion) {
        boolean bandera = accion == Controlador.NUEVO || accion == Controlador.MODIFICAR;

        FormularioUtil.activarComponente(this.pnlListado, !bandera);
        FormularioUtil.activarComponente(this.pnlDatos, bandera);
        
        if(accion != Controlador.MODIFICAR){
            FormularioUtil.limpiarComponente(this.pnlDatos);
        }
        
        

    }

    private void bindeoSalvaje() {
        horarioList = new ArrayList<>();
        horarioList = ObservableCollections.observableList(horarioList);

        String[] columnas = {"Nombre", "Lunes", "Martes", "Miércoles", "Jueves","Viernes","Sábado","Domingo"};

        MTHorario mt = new MTHorario(horarioList, columnas);
        tblHorario.setModel(mt);
        actualizarTabla();
    }

    private void mostrar(Horario seleccionado) {
        txtCodigo.setText(seleccionado.getCodigo());
        txtNombre.setText(seleccionado.getNombre());
        txtJornada.setText(seleccionado.getJornada().getNombre());
        chkLunes.setSelected(seleccionado.isLunes());
        chkMartes.setSelected(seleccionado.isMartes());
        chkMiercoles.setSelected(seleccionado.isMiercoles());
        chkJueves.setSelected(seleccionado.isJueves());
        chkViernes.setSelected(seleccionado.isViernes());
        chkSabado.setSelected(seleccionado.isSabado());
        chkDomingo.setSelected(seleccionado.isDomingo());
    }

    private void actualizarTabla() {
        horarioList.clear();
        horarioList.addAll(horarioControlador.buscarTodos());
        tblHorario.packAll();
    }
}
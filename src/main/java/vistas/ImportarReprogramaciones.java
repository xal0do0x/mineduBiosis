/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.Controlador;
import controladores.EmpleadoControlador;
import controladores.GrupoHorarioControlador;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.GrupoHorario;
import vistas.modelos.MTEmpleado;
import vistas.modelos.MTGrupoHorario;
import com.personal.utiles.FormularioUtil;
import controladores.DetalleGrupoControlador;
import controladores.PeriodoControlador;
import controladores.SaldoVacacionalControlador;
import controladores.TCAnalisisControlador;
import controladores.VacacionControlador;
import entidades.Periodo;
import entidades.SaldoVacacional;
import entidades.Vacacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.observablecollections.ObservableCollections;
import vistas.dialogos.DlgImportarCSV;

/**
 *
 * @author fesquivelc
 */
public class ImportarReprogramaciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form CRUDPeriodo
     */
    private List<GrupoHorario> listado;
    private List<Empleado> integrantes;
    //private GrupoHorarioControlador controlador;
    private PeriodoControlador pc = new PeriodoControlador();
    private EmpleadoControlador ec = new EmpleadoControlador();
    private VacacionControlador vc = new VacacionControlador();
    private List<Vacacion> listaGuardar = new ArrayList<>();
    private List<String[]> listaVacIncorrectas = new ArrayList<>();
    private int accion;

    
    public ImportarReprogramaciones() {
        initComponents();
        inicializar();
        bindeoSalvaje();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblIntegrantes = new org.jdesktop.swingx.JXTable();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("REPROGRAMACION DE VACACIONES A IMPORTAR");
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0};
        layout.rowHeights = new int[] {0};
        getContentPane().setLayout(layout);

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de reprogramaciones de vacaciones"));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0};
        pnlDatos.setLayout(jPanel2Layout);

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel2.setText("Reprogramaciones:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel4.add(jLabel2, gridBagConstraints);

        jScrollPane3.setViewportView(tblIntegrantes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jScrollPane3, gridBagConstraints);

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

        jButton1.setText("Importar Reprogramaciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        pnlDatos.add(jPanel1, new java.awt.GridBagConstraints());

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

       for(Vacacion v : listaGuardar){
           System.out.println("vaca: "+v.getDocumento()+" documento: "+v.getEmpleado());
       }
//            Vacacion seleccionada = this.controlador.getSeleccionado();
//        for(Vacacion vacacion : listaGuardar){
//            vc.setSeleccionado(vacacion);
//            //System.out.println("NUMERO DE REPROGRAMACIONES AL MOMENTO DE GUARDAR: "+vacacion.getVacacionList().size());
//            if (vc.accion(Controlador.MODIFICAR)) {             
//                System.out.println("SE GUARDÓ CORRECTAMENTE");
//                FormularioUtil.mensajeExito(this, Controlador.MODIFICAR);
//                this.dispose();
//            }else{
//                System.out.println("HUBO UN ERROR");
//                FormularioUtil.mensajeError(this, Controlador.MODIFICAR);
//            }
//
//        }     
        if (vc.guardarLote(listaGuardar)) {             
            System.out.println("SE GUARDÓ CORRECTAMENTE");
            FormularioUtil.mensajeExito(this, Controlador.MODIFICAR);
            this.dispose();
        }else{
            System.out.println("HUBO UN ERROR");
            FormularioUtil.mensajeError(this, Controlador.MODIFICAR);
        }
        listaGuardar.clear();
        
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DlgImportarCSV importar = new DlgImportarCSV(this);
        List<String []> info = importar.obtenerDNI();
        //Creacion de vacaciones **CONSIDERAR HACER UNA CLASE MAS QUE PROCESE EL CONJUNTO DE DATOS ANTES DE INGRESARLOS AL LIST QUE SERA GUARDADO**
        int numero = 1;
        List<Vacacion> listaVacaciones = new ArrayList<>();
        List<Vacacion> listaReprogramacionesAll = new ArrayList<>();
        List<Vacacion> listaReprogramaciones = new ArrayList<>();
        List<Vacacion> listaVacReprog = new ArrayList<>();
        boolean banderaVacacion = true;
        Calendar date = Calendar.getInstance();
        
        
       
        for(String[] a : info){
            
            List<Vacacion> listaReprogIngresadas = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            List<String> listaString = new ArrayList<>();
            listaString.addAll(Arrays.asList(a));
            int contadorV = 0;
            //String documento = listaString.get(0);
            //String dni = listaString.get(1);
            String resolucion = "";
            String documento = "";
            String sinad = "";
            String observacion = "";
            Date fechaCalculo = null;
            Date fechaInterrupcion = null;
            Vacacion vacacionOriginal = null;
            String dni = listaString.get(0);
            try {
                fechaCalculo = formatter.parse(listaString.get(1));
            } catch (ParseException ex) {
                Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!listaString.get(14).isEmpty()){
                resolucion = listaString.get(14);
            }
            if(!listaString.get(13).isEmpty()){
                documento = listaString.get(13);
            }
            if(!listaString.get(15).isEmpty()){
                sinad = listaString.get(15);
            }
            if(!listaString.get(16).isEmpty()){
                observacion = listaString.get(16);
            }
            if(!listaString.get(2).isEmpty() && !listaString.get(3).isEmpty()){
                try {
                    //Busqueda de la vacacion a reprogramar
                    Date fechaInicio = formatter.parse(listaString.get(2));
                    vacacionOriginal = vc.buscarXDia(dni, fechaInicio);
                    //listaVacaciones.add(vacacionOriginal);
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Empleado: "+dni);
            if(vacacionOriginal!=null){
                System.out.println("Vacacion a reprogramar: "+vacacionOriginal.getFechaInicio()+" "+vacacionOriginal.getFechaFin());
            }else{
                listaVacIncorrectas.add(a);
                continue;
            }
                
            if(!listaString.get(4).isEmpty()){
                try {
                    //Fecha de interrupcion
                    fechaInterrupcion = formatter.parse(listaString.get(4));
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!listaString.get(5).isEmpty()){
                try {
                    boolean crearNuevo = true;
                    System.out.println("Reprogramacion: "+"Dni: "+dni+" Numero:"+numero+" "+listaString.get(5)+" "+listaString.get(6));
                    Date priVacacionInicio = formatter.parse(listaString.get(5));
                    Date priVacacionFin = formatter.parse(listaString.get(6));
                    if(!listaReprogramaciones.isEmpty() && !listaReprogramacionesAll.isEmpty()){
                        for(Vacacion vacacionVerif : listaReprogramaciones){
                            Date fechaInicio = vacacionVerif.getFechaInicio();
                            Date fechaFin = vacacionVerif.getFechaFin();
                            //System.out.println("Vacacion Reprogramacion: "+priVacacionInicio+" "+priVacacionFin+" "+dni);
                            //System.out.println("Vacacion ya almacenada: "+fechaInicio+" "+fechaFin+" "+vacacionVerif.getEmpleado());    
                            if(priVacacionInicio.compareTo(fechaInicio)==0 && priVacacionFin.compareTo(fechaFin)==0 && vacacionVerif.getEmpleado().equals(dni)){
                                listaReprogIngresadas.add(vacacionVerif);
                                System.out.println("No crea");
                                banderaVacacion = false;
                                crearNuevo = false;
                            }         
                        }
                        if(crearNuevo){
                            System.out.println("Crea una nueva");
                            Vacacion ingreso = this.crearReprogramacion(priVacacionInicio, priVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo, vacacionOriginal);
                            listaReprogramacionesAll.add(ingreso);
                            listaReprogIngresadas.add(ingreso);
                        }
                    }else{
                        Vacacion ingreso = this.crearReprogramacion(priVacacionInicio, priVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                        listaReprogramacionesAll.add(ingreso);
                        listaReprogIngresadas.add(ingreso);
                        banderaVacacion = true;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("d_d");
                }
            }
            if(!listaString.get(7).isEmpty()){
                try {
                    boolean crearNuevo = true;
                    System.out.println("Reprogramacion: "+"Dni: "+dni+" Numero:"+numero+" "+listaString.get(7)+" "+listaString.get(8));
                    Date segVacacionInicio = formatter.parse(listaString.get(7));
                    Date segVacacionFin = formatter.parse(listaString.get(8));  
                    if(!listaReprogramaciones.isEmpty()){
                        for(Vacacion vacacionVerif:listaReprogramaciones){
                            Date fechaInicio = vacacionVerif.getFechaInicio();
                            Date fechaFin = vacacionVerif.getFechaFin();
                           //System.out.println("Vacacion Reprogramacion: "+segVacacionInicio+" "+segVacacionFin+" "+dni);
                            //System.out.println("Vacacion ya almacenada: "+fechaInicio+" "+fechaFin+" "+vacacionVerif.getEmpleado());   
                            if(segVacacionInicio.compareTo(fechaInicio)==0 && segVacacionFin.compareTo(fechaFin)==0 && vacacionVerif.getEmpleado().equals(dni)){
                                listaReprogIngresadas.add(vacacionVerif);
                                System.out.println("No crea");
                                banderaVacacion = false;
                                crearNuevo = false;
                            }//else{
//                                Vacacion ingreso = this.crearReprogramacion(segVacacionInicio, segVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo);
//                                listaReprogramacionesAll.add(ingreso);
//                                listaReprogIngresadas.add(ingreso);
//                                break;
//                            }    
                            
                        }
                        if(crearNuevo){
                            System.out.println("Crea una nueva");
                            Vacacion ingreso = this.crearReprogramacion(segVacacionInicio, segVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                            listaReprogramacionesAll.add(ingreso);
                            listaReprogIngresadas.add(ingreso);
                        }
                    }else{
                        Vacacion ingreso = this.crearReprogramacion(segVacacionInicio, segVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                        listaReprogramacionesAll.add(ingreso);
                        listaReprogIngresadas.add(ingreso);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("d_d");
                }
            }
            if(!listaString.get(9).isEmpty()){
                try {
                    boolean crearNuevo = true;
                    System.out.println("Reprogramacion: "+"Dni: "+dni+" Numero:"+numero+" "+listaString.get(9)+" "+listaString.get(10));
                    Date tercVacacionInicio = formatter.parse(listaString.get(9));
                    Date tercVacacionFin = formatter.parse(listaString.get(10));
                    if(!listaReprogramaciones.isEmpty()){
                        for(Vacacion vacacionVerif:listaReprogramaciones){
                            Date fechaInicio = vacacionVerif.getFechaInicio();
                            Date fechaFin = vacacionVerif.getFechaFin();
                            //System.out.println("Vacacion Reprogramacion: "+tercVacacionInicio+" "+tercVacacionFin+" "+dni);
                            //System.out.println("Vacacion ya almacenada: "+fechaInicio+" "+fechaFin+" "+vacacionVerif.getEmpleado());  
                            if(tercVacacionInicio.compareTo(fechaInicio)==0 && tercVacacionFin.compareTo(fechaFin)==0 && vacacionVerif.getEmpleado().equals(dni)){
                                listaReprogIngresadas.add(vacacionVerif);
                                System.out.println("No crea");
                                banderaVacacion = false;
                                crearNuevo = false;
                            }//else{
//                                Vacacion ingreso = this.crearReprogramacion(tercVacacionInicio, tercVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo);
//                                listaReprogramacionesAll.add(ingreso);
//                                listaReprogIngresadas.add(ingreso);
//                                break;
//                            }   
                            
                        }
                        if(crearNuevo){
                            System.out.println("Crea una nueva");
                            Vacacion ingreso = this.crearReprogramacion(tercVacacionInicio, tercVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                            listaReprogramacionesAll.add(ingreso);
                            listaReprogIngresadas.add(ingreso);
                        }
                    }else{
                        Vacacion ingreso = this.crearReprogramacion(tercVacacionInicio, tercVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                        listaReprogramacionesAll.add(ingreso);
                        listaReprogIngresadas.add(ingreso);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!listaString.get(11).isEmpty()){
                try {
                    boolean crearNuevo = true;
                    System.out.println("Reprogramacion: "+"Dni: "+dni+" Numero:"+numero+" "+listaString.get(11)+" "+listaString.get(12));
                    Date cuartVacacionInicio = formatter.parse(listaString.get(11));
                    Date cuartVacacionFin = formatter.parse(listaString.get(12));
                   if(!listaReprogramaciones.isEmpty()){
                        for(Vacacion vacacionVerif:listaReprogramaciones){
                            Date fechaInicio = vacacionVerif.getFechaInicio();
                            Date fechaFin = vacacionVerif.getFechaFin();
                            //System.out.println("Vacacion Reprogramacion: "+cuartVacacionInicio.toString()+" "+cuartVacacionFin.toString()+" "+dni);
                            //System.out.println("Vacacion ya almacenada: "+fechaInicio.toString()+" "+fechaFin.toString()+" "+vacacionVerif.getEmpleado());  
                            if(cuartVacacionInicio.compareTo(fechaInicio)==0 && cuartVacacionFin.compareTo(fechaFin)==0 && vacacionVerif.getEmpleado().equals(dni)){
                                listaReprogIngresadas.add(vacacionVerif);
                                System.out.println("No crea");
                                banderaVacacion = false;
                                crearNuevo = false;
                            }//else{
//                                Vacacion ingreso = this.crearReprogramacion(cuartVacacionInicio, cuartVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo);
//                                listaReprogramacionesAll.add(ingreso);
//                                listaReprogIngresadas.add(ingreso);
//                                break;
//                            } 
                            
                        }
                        if(crearNuevo){
                            System.out.println("Crea una nueva");
                            Vacacion ingreso = this.crearReprogramacion(cuartVacacionInicio, cuartVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                            listaReprogramacionesAll.add(ingreso);
                            listaReprogIngresadas.add(ingreso);
                        }
                    }else{
                        Vacacion ingreso = this.crearReprogramacion(cuartVacacionInicio, cuartVacacionFin, dni, resolucion, documento, sinad, observacion, fechaCalculo,vacacionOriginal);
                        listaReprogramacionesAll.add(ingreso);
                        listaReprogIngresadas.add(ingreso);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Contador: "+contadorV);
            //if(vacacionOriginal.isReprogramacionTotal()!=null){
            if(!vacacionOriginal.isReprogramacionTotal() || !vacacionOriginal.isHayReprogramacion()){
                if(fechaInterrupcion!=null){
                    //int diferenciaDias = vacacionOriginal.getFechaFin() - vacacionOriginal.getFechaInicio();
                    //Restamos la fecha interrupcion de la fecha fin
                    //Luego esa cantidad la comparamos con la cantidad de dias de la repogrmacion para poder comparar si estan bien el numero de dias
                    //validando eso lo registramos
                    int diasVacaciones = this.restarFechas(fechaInterrupcion,vacacionOriginal.getFechaFin())+1;
                    //if(banderaVacacion){
                        vacacionOriginal.getVacacionList().addAll(listaReprogIngresadas);
                    //}
                    vacacionOriginal.setFechaInterrupcion(fechaInterrupcion);
                    vacacionOriginal.setHayReprogramacion(true);
                    vacacionOriginal.setReprogramacionTotal(false);
                    vacacionOriginal.setDocumentoReprogramacion(documento);
                    System.out.println("Dias para reprogramar parcial: "+diasVacaciones);
                }else{
                    //int diferenciaDias = vacacionOriginal.getFechaFin() - vacacionOriginal.getFechaInicio();
                    //Restamos la fecha inicio de la fecha fin
                    //Luego esa cantidad la comparamos con la cantidad de dias de la repogrmacion para poder comparar si estan bien el numero de dias
                    //validando eso lo registramos
                    int diasVacaciones = this.restarFechas(vacacionOriginal.getFechaInicio(),vacacionOriginal.getFechaFin())+1;
                    //if(banderaVacacion){
                        vacacionOriginal.getVacacionList().addAll(listaReprogIngresadas);
                    //}
                    vacacionOriginal.setHayReprogramacion(true);
                    vacacionOriginal.setReprogramacionTotal(true);
                    vacacionOriginal.setDocumentoReprogramacion(documento);
                    System.out.println("Dias para reprogramar total: "+diasVacaciones);
                }
                
                //listaVacaciones.add(vacacionOriginal);
                listaGuardar.add(vacacionOriginal);
            }else{
               listaVacReprog.add(vacacionOriginal);
            }
            String numeroB = null;
            if(banderaVacacion){
                numeroB = "1";
            }else{
                numeroB = "0";
            }
            System.out.println("Tamaño de lista de reprogramaciones ingresadas para esta vacacion: "+listaReprogIngresadas.size());
            //listaReprogramaciones.clear();
            System.out.println("Tamano de la lista total: "+listaReprogramacionesAll.size());
            System.out.println("BANDERA DE VACACION: "+numeroB);
            listaReprogIngresadas.clear();
            listaReprogramaciones.clear();
            listaReprogramaciones.addAll(listaReprogramacionesAll);
            System.out.println(" ");
            banderaVacacion = true;
        }
        //Verificar si vacacion no esta reprogramada antes
        
        //Validacion de vacaciones
//        for(Vacacion vacacion : listaVacaciones){
//            List<Vacacion> validacion = vc.buscarXEmpleadoXFechaXDni(vacacion.getEmpleado(),vacacion.getFechaInicio());
//            if(!validacion.isEmpty()){
//                System.out.println("Vacacion repetida, no se ingreso vacacion de "+ vacacion.getEmpleado());
//            }else{
//                listaGuardar.add(vacacion);
//            }
//        }
        
        
        //System.out.println("Cantidad Total de vacaciones: "+listaVacaciones.size());
        System.out.println("Cantidad Total de vacaciones a ingresar: "+listaGuardar.size());
        listaReprogramaciones.clear();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlDatos;
    private org.jdesktop.swingx.JXTable tblIntegrantes;
    // End of variables declaration//GEN-END:variables

    private void mostrar(GrupoHorario grupo) {
//        txtCodigo.setText(grupo.getCodigo());
//        txtNombre.setText(grupo.getNombre());
//
//        List<String> listaDNI = obtenerListadoDNI(grupo.getDetalleGrupoHorarioList());
//        if (!listaDNI.isEmpty()) {
//            mostrarIntegrantes(listaDNI);
//        }

    }
    //private List<Periodo> periodoList;
    private void bindeoSalvaje() {
        listado = new ArrayList<>();
        listado = ObservableCollections.observableList(listado);
        //periodoList = pc.buscarTodosOrden();
        integrantes = ObservableCollections.observableList(new ArrayList<Empleado>());

        String[] columnas = {"Código", "Nombre"};
        String[] columnasIntegrantes = {"Nro Documento", "Empleado"};

        MTGrupoHorario mt = new MTGrupoHorario(listado, columnas);
        MTEmpleado mtIntegrantes = new MTEmpleado(integrantes, columnasIntegrantes);
        //tblTabla.setModel(mt);
        tblIntegrantes.setModel(mtIntegrantes);

        actualizarTabla();
    }

    private void actualizarTabla() {
        listado.clear();
//        listado.addAll(controlador.buscarTodos());
//        tblTabla.packAll();
    }

    private void mostrarIntegrantes(List<String> listadoDNI) {
        integrantes.clear();
        integrantes.addAll(ec.buscarPorLista(listadoDNI));
        tblIntegrantes.packAll();
    }

    private void inicializar() {
        this.accion = 1;
        ec = new EmpleadoControlador();
        this.controles(accion);
    }

    private void controles(int accion) {
        boolean bandera = accion == Controlador.NUEVO || accion == Controlador.MODIFICAR;

//        FormularioUtil.activarComponente(this.pnlListado, !bandera);
        FormularioUtil.activarComponente(this.pnlDatos, bandera);

        if (accion != Controlador.MODIFICAR) {
            FormularioUtil.limpiarComponente(this.pnlDatos);

        }
    }

    private List<String> obtenerListadoDNI(List<DetalleGrupoHorario> detalles) {
        List<String> listadoDNI = new ArrayList<>();
        for (DetalleGrupoHorario detalle : detalles) {
            listadoDNI.add(detalle.getEmpleado());
        }
        return listadoDNI;
    }

//    public void agregarEmpleado(Empleado empleado) {
//        if (!integrantes.contains(empleado)) {
//            integrantes.add(empleado);
////            //ec.eliminarAsignacionGrupo(empleado.getNroDocumento());
////            List<DetalleGrupoHorario> listaDetalles;
////            if(!dgh.buscarXEmpleado(empleado).isEmpty()){
////                listaDetalles = dgh.buscarXEmpleado(empleado);
////                if(!listaDetalles.isEmpty()){
////                    for (DetalleGrupoHorario listaDetalle : listaDetalles) {
////                        GrupoHorario grupoHorarioAntiguo = listaDetalle.getGrupoHorario();
////                        tcontrolador.setSeleccionado(grupoHorarioAntiguo);
////                        tcontrolador.getSeleccionado().getDetalleGrupoHorarioList().remove(0);
////                    }
////                }
////            }
//            
//            
//            DetalleGrupoHorario detalle = new DetalleGrupoHorario();
//            detalle.setEmpleado(empleado.getNroDocumento());
//            detalle.setGrupoHorario(controlador.getSeleccionado());
//            
//            controlador.getSeleccionado().getDetalleGrupoHorarioList().add(detalle);
//        }
//
//    }

//    private void quitarEmpleado(int fila) {
//        integrantes.remove(fila);
//        controlador.getSeleccionado().getDetalleGrupoHorarioList().remove(fila);
//    }
    private final TCAnalisisControlador tcac = new TCAnalisisControlador();
    private void retrocederTiempo(List<String> dnis, Date fechaInicio) {
        tcac.retrocederTiempo(dnis, fechaInicio);
    }

    private final SaldoVacacionalControlador svc = new SaldoVacacionalControlador();

    private final Calendar calendar = Calendar.getInstance();
    
    public SaldoVacacional buscarCrear(Empleado empleado, Periodo periodo) {
        SaldoVacacional sv = svc.buscarXPeriodo(empleado.getNroDocumento(), periodo);
        Date fechaContrato = empleado.getFechaInicioContrato();
        calendar.setTime(fechaContrato);
        if (sv == null && periodo.getAnio() > calendar.get(Calendar.YEAR)) {
            //CREAMOS
            sv = new SaldoVacacional();
            //OBTENEMOS SI LE CORRESPONDEN VACACIONES ACORDE A LEY

            if (calendar.get(Calendar.YEAR) < periodo.getAnio()) {
                sv.setDiasRestantes(30);
            } else {
                sv.setDiasRestantes(0);
            }
            calendar.set(Calendar.YEAR, periodo.getAnio());
            sv.setFechaDesde(calendar.getTime());
            calendar.add(Calendar.YEAR, 1);
            sv.setFechaHasta(calendar.getTime());
            sv.setEmpleado(empleado.getNroDocumento());
            sv.setLunesViernes(0);
            sv.setSabado(0);
            sv.setDomingo(0);
            sv.setPeriodo(periodo);
            svc.modificar(sv);
        }
        sv = svc.buscarXPeriodo(empleado.getNroDocumento(), periodo);
        return sv;
    }
    private final Calendar cal = Calendar.getInstance();
     private int[] obtenerSaldos(Empleado empleado, Periodo periodo) {
        List<Vacacion> vacaciones = vc.buscarXEmpleadoXPeriodo(empleado.getNroDocumento(), periodo);
        int[] saldo = new int[3];
        int lunesViernes = 0;
        int sabado = 0;
        int domingo = 0;
        
        for (Vacacion vacacion : vacaciones) {
            Date fechaInicio = vacacion.getFechaInicio();
            Date fechaFin = vacacion.isHayInterrupcion() ? vacacion.getFechaInterrupcion() : vacacion.getFechaFin();

            while (fechaInicio.compareTo(fechaFin) <= 0) {
                cal.setTime(fechaInicio);
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                    sabadoADomingo++;
                } else {
                    lunesViernes++;
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                fechaInicio = cal.getTime();
            }
        }
        int division = lunesViernes / 5;
        sabado = division;
        domingo = division;
        
        saldo[0] = lunesViernes;
        saldo[1] = sabado;
        saldo[2] = domingo;
        return saldo;
    }
    public int restarFechas(Date fechaIn, Date fechaFinal ){
        long in = fechaIn.getTime();
        long fin = fechaFinal.getTime();
        Long diff= (fin-in)/1000;
        return diff.intValue()/86400;
    }
    
    public Vacacion crearReprogramacion(Date fechaInicio, Date fechaFin, String dni, String resolucion,String documento, String sinad,String observacion,Date fechaCalculo,Vacacion vacacionOriginal){
        Calendar date = Calendar.getInstance();
        Vacacion ingreso = new Vacacion();
        ingreso.setResolucion(resolucion);
        ingreso.setDocumento(documento);
        ingreso.setSinad(sinad);
        ingreso.setObservacion(observacion);
        ingreso.setEmpleado(dni);
        ingreso.setFechaInicio(fechaInicio);
        ingreso.setFechaFin(fechaFin);
        ingreso.setHayInterrupcion(false);
        ingreso.setReprogramacionTotal(false);
        ingreso.setVacacionOrigen(vacacionOriginal);
        date.setTime(fechaCalculo);
        ingreso.setPeriodo(pc.buscarPorAnio(date.get(Calendar.YEAR)));
        return ingreso;
    }
}

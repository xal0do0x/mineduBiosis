/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.controladores;

import controladores.AsignacionHorarioControlador;
import controladores.AsignacionPermisoControlador;
import controladores.ContratoControlador;
import controladores.Controlador;
import controladores.DetalleGrupoControlador;
import controladores.EmpleadoControlador;
import controladores.EmpleadoOpcionInfoControlador;
import controladores.EmpleadoTControlador;
import controladores.FeriadoControlador;
import controladores.MarcacionControlador;
import controladores.PermisoControlador;
import controladores.TipoPermisoControlador;
import controladores.VacacionControlador;
import entidades.AsignacionHorario;
import entidades.AsignacionPermiso;
import entidades.Contrato;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.EmpleadoOpcionInfo;
import entidades.EmpleadoT;
import entidades.Horario;
import entidades.Jornada;
import entidades.Marcacion;
import entidades.Permiso;
import entidades.Vacacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.castor.mapping.AbstractMappingLoaderFactory.LOG;
import pruebareportes.ReporteUtil;
import vistas.reportes.beans.ReporteAsistenciaBean;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author OGEPER02
 */
public class ReporteAsistenciaControlador {
    private final EmpleadoTControlador ec = new EmpleadoTControlador();
    private final ContratoControlador cc = new ContratoControlador();
    private final AsignacionHorarioControlador ashc = new AsignacionHorarioControlador();
    private final DetalleGrupoControlador dc = new DetalleGrupoControlador();
    private final AsignacionPermisoControlador aspc = new AsignacionPermisoControlador();
    private final VacacionControlador vc = new VacacionControlador();
    private final PermisoControlador pc = new PermisoControlador();
    private final MarcacionControlador mc = new MarcacionControlador();
    private final EmpleadoOpcionInfoControlador eoc = new EmpleadoOpcionInfoControlador();
    private final FeriadoControlador fc = new FeriadoControlador();
    private final TipoPermisoControlador tpc = new TipoPermisoControlador();
    
    private List<String>[] obtenerLimites(List<String> dnis) {
        List<String>[] limites = new List[4];
        int segmentos = 4;
        
        limites[0] = new ArrayList<>();
        limites[1] = new ArrayList<>();
        limites[2] = new ArrayList<>();
        limites[3] = new ArrayList<>();
        
        int contador = 0;
        for (String dni : dnis) {
            limites[contador].add(dni);
            if (contador < 3) {
                contador++;
            } else {
                contador = 0;
            }
        }
        
        return limites;
    }
    
    private class HiloAnalisis extends Thread {
        
        private List<String> empleadoList;
        private List<ReporteAsistenciaBean> asistenciaDetalladoList;
        private Date fechaInicio;
        private Date fechaFin;
        private int numeroHilo;
        
        public HiloAnalisis(List<String> empleadoList, List<ReporteAsistenciaBean> asistenciaDetalladoList, Date fechaInicio, Date fechaFin) {
            this.empleadoList = empleadoList;
            this.asistenciaDetalladoList = asistenciaDetalladoList;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
        }
        
        public int getNumeroHilo() {
            return numeroHilo;
        }
        
        public void setNumeroHilo(int numeroHilo) {
            this.numeroHilo = numeroHilo;
        }
        
        @Override
        public void run() {
            
            List<ReporteAsistenciaBean> analisis = analisisAsistencia(fechaInicio, fechaFin, empleadoList,false,true);
            System.out.println("ANALISIS TAMANIO: " + analisis.size());
            this.asistenciaDetalladoList.addAll(analisis);
            System.out.println("TERMINANDO HILO " + getNumeroHilo());
        }
    }
    
    public List<ReporteAsistenciaBean> iniciarAnalisis(Date fechaInicio, Date fechaFin, List<String> empleados) {
        List<ReporteAsistenciaBean> asistenciaDetalladaList = new ArrayList<>();
        
        List<String>[] division = obtenerLimites(empleados);
        HiloAnalisis tareas[] = new HiloAnalisis[4];
        int conteo = 0;
        for (List<String> empleadoList : division) {
            if (!empleadoList.isEmpty()) {
                System.out.println("INICIANDO HILO " + conteo + " TAMAÑO " + empleadoList.size() + " " + fechaInicio + " " + fechaFin);
                tareas[conteo] = new HiloAnalisis(empleadoList, asistenciaDetalladaList, fechaInicio, fechaFin);
                tareas[conteo].setNumeroHilo(conteo);
                tareas[conteo].start();
                conteo++;
            }
        }
        
        for (Thread tarea : tareas) {
            try {
                if (tarea != null) {
                    tarea.join();
                }                
            } catch (InterruptedException ex) {
                Logger.getLogger(ReporteAsistenciaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Calendar horaActual = Calendar.getInstance();
        System.out.println("SE TERMINARON LAS TAREAS EN LA HORA "+horaActual.getTime());
        
        return asistenciaDetalladaList;
    }
    
    public List<ReporteAsistenciaBean> analisisAsistencia(Date fechaInicio, Date fechaFin, List<String> dnis,boolean isSelectedComp, boolean isSelectedHoraM){
        List<ReporteAsistenciaBean> asistenciaEmpleados = new ArrayList<>();
        Calendar calC = Calendar.getInstance();       
        for(String dni : dnis){     
            Calendar iterador = Calendar.getInstance();
            iterador.setTime(fechaInicio);
            while(iterador.getTime().compareTo(fechaFin) <= 0){
                ReporteAsistenciaBean registro = new ReporteAsistenciaBean();
                Date fecha = iterador.getTime();
                //Descartar si es sabado o domingo
                System.out.println("Dni: "+dni+" Fecha: "+fecha.toString());
                
                EmpleadoT empleado = ec.buscarPorDni(dni);
                String regimeLaboral = empleado.getRegimenLaboral();
                boolean usarContrato = true;
                
                if(regimeLaboral.equals("D.Ley Nro 276")){
                    usarContrato = false;
                }else if(regimeLaboral.equals("D.Ley Nro 728")){
                    usarContrato = false;
                }else if(regimeLaboral.equals("Ley Nro 24029")){
                    usarContrato = false;
                }else if(regimeLaboral.equals("Ley Nro 29944")){
                    usarContrato = false;
                }else if(regimeLaboral.equals("Ley Nro 30057")){
                    usarContrato = false;
                }else if(regimeLaboral.equals("SIN REGIMEN")){
                    usarContrato = false;
                }
                Contrato contratoEmpleado;
                String situacionCon = "";
                if(usarContrato){
                   contratoEmpleado = cc.buscarContratoXFechaXDni(empleado.getNroDocumento(), fecha);
                   if(contratoEmpleado != null){
                       System.out.println("OK");
                   }else{
                       situacionCon = "";
                   }
                }
                boolean isFeriado = false;
                String feriado = "";
                //Para inicio de contrato
//                if(empleado.getFechaInicioContrato().compareTo(fecha)<=0){
//                    System.out.println("OK");
//                }else{
//                    iterador.add(Calendar.DATE, 1);
//                    continue;
//                }
                /**
                 * Generacion de permiso por onomastico
                 */
                if(isOnosmatico(fecha, empleado)){
                    Calendar fechaPermisoOno =Calendar.getInstance();
                    fechaPermisoOno.setTime(fecha);

                    while(fc.buscarXDia(fechaPermisoOno.getTime())!=null){
                        fechaPermisoOno.add(Calendar.DATE, 1);
                    }
                    while(!ReporteUtil.isDiaLaboral(fechaPermisoOno.getTime())){
                        fechaPermisoOno.add(Calendar.DATE, 1);
                    }
                    if(ReporteUtil.isDiaLaboral(fechaPermisoOno.getTime())){
                        //busca onomastico
                        if(aspc.buscarXDiaOnosmatico(dni, fechaPermisoOno.getTime())!=null){
                            System.out.println("Hay cumpleañosd d_d");
                        }else{
                            System.out.println("Crear Cumple");
                            crearOnomastico(fechaPermisoOno.getTime(), empleado);
                        }
                    }
                }else{
                    System.out.println("No cumple");
                }
                
                if(!ReporteUtil.isDiaLaboral(fecha)){
                    iterador.add(Calendar.DATE, 1);
                    continue;
                }
                if(fc.buscarXDia(fecha)!=null){
                    isFeriado = true;
                    feriado = fc.buscarXDia(fecha).getNombre();
                }
                //Marcaciones del dia a procesar
                List<Marcacion> marcaciones = mc.buscarXFecha(dni,fecha);
                String Asistencia = "";
                String Permiso = "";
                String Vacaciones = "";
                String marcacion = "";
                String marcacion2 = "";
                String condicion = "";
                String compensacion = "";
                Integer tardanza= 0;
                Marcacion primeraMarcacion = new Marcacion();
                Marcacion ultimaMarcacion = new Marcacion();
                

                List<EmpleadoOpcionInfo> infoEmpleado = eoc.buscarTodos(Integer.parseInt(dni));
                if(!infoEmpleado.isEmpty()){
                    if(infoEmpleado.get(0).getSFieldValue5().equals("0") || 
                        infoEmpleado.get(0).getSFieldValue5().equals("") ||
                        infoEmpleado.get(0).getSFieldValue5().equals(" ")){
                        condicion = "NORMAL";
                    }else if(infoEmpleado.get(0).getSFieldValue5().equals("1")){
                        condicion = "Exonerado";
                    }else if(infoEmpleado.get(0).getSFieldValue5().equals("2")){
                        condicion = "Designado";
                    }else if(infoEmpleado.get(0).getSFieldValue5().equals("3")){
                        condicion = "Otra sede";
                    }
                }else{
                    condicion = "Sin dato";
                }
                System.out.println("Condicion: "+condicion);
                //Datos de horario, jornada, empleado
                List<DetalleGrupoHorario> detallesGrupos = dc.buscarXEmpleado(empleado);
                if(condicion.equals("NORMAL")){
                    if(!marcaciones.isEmpty()){
                        //ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        /**
                         *Codigo para el manejo de asignaciones de horario
                         */           
                        
                        if(!detallesGrupos.isEmpty()){
                            DetalleGrupoHorario detalleGrupoEmpleado = detallesGrupos.get(0);
       
                            List<AsignacionHorario> asignaciones = ashc.buscarXGrupo(detalleGrupoEmpleado.getGrupoHorario());
                            //List<AsignacionHorario> asignacionesHorario = ashc.bus
                            List<AsignacionHorario> asignacionesHorarios = ashc.buscarXEmpleadosXAll(dni, fecha);
                            //Declaramos la variable asighorario q usaremos
                            /**
                             * Vemos si tiene una asignacion horario en particular para asignar la jornada q le corresponde, sino la tiene se asigna la jornada por 
                             * defecto del grupo horario general
                             */
                            AsignacionHorario asignacionEmpleado;
                            if(!asignacionesHorarios.isEmpty()){
                                asignacionEmpleado = asignacionesHorarios.get(0);
                            }else{
                                asignacionEmpleado = asignaciones.get(0);
                            }
                            //AsignacionHorario asignacionEmpleado = asignaciones.get(0);
                            Horario horarioEmpleado = asignacionEmpleado.getHorario();
                            registro.setHorarioAsignado(horarioEmpleado);
                            Jornada jornadaEmpleado =  horarioEmpleado.getJornada();
                            
                            Calendar horaM = Calendar.getInstance();
                            Calendar horaJ = Calendar.getInstance();
                            
                            //Validamos marcaciones traidas
                            primeraMarcacion = marcaciones.get(0);
                            if(marcaciones.size()>1){
                                ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                                marcacion2 = ultimaMarcacion.getHora().toString();
                            }else{
                                marcacion2 = "---";
                            }
                            //Prueba para validar dos marcaciones consecutivas en la entrada o la salida
                            if(primeraMarcacion != null && ultimaMarcacion != null ){
//                                if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<=0 
//                                    && primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())<=0){
//                                    System.out.println("primeraMarcacion Acorde");
//                                }
//                                if(ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<=0 
//                                    && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())<=0){
//                                    System.out.println("ultimaMarcacion demasiado cercana al inicio");
//                                    System.out.println("ultimaMarcacion borrada");
//                                    ultimaMarcacion = null;
//                                }
                                //Comparando con salida
                                Calendar comparador = Calendar.getInstance();
                                comparador.setTime(primeraMarcacion.getHora());
                                comparador.add(Calendar.MINUTE,1);
                                if(ultimaMarcacion.getHora()!=null){
                                    if(ultimaMarcacion.getHora().compareTo(primeraMarcacion.getHora())>=0 && 
                                        ultimaMarcacion.getHora().compareTo(comparador.getTime())<=0 ) {
                                        ultimaMarcacion= null;
                                    }
                                }    
                            }
                            //Validacion de asistencia
                            if(ultimaMarcacion!=null){
                            if(ultimaMarcacion.getHora() !=null){
                                if(!isSelectedHoraM){
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                }
                                if(primeraMarcacion.getHora().compareTo(ultimaMarcacion.getHora())<0){
                                    
                                    //Agregado para redondear minutos
                                    Calendar primeraMarc =Calendar.getInstance();
                                    primeraMarc.setTime(primeraMarcacion.getHora());
                                    primeraMarc.set(Calendar.SECOND, 0);
                                    if((primeraMarc.getTime().compareTo(jornadaEmpleado.getTurnoHE())==0 
                                        || primeraMarc.getTime().compareTo(jornadaEmpleado.getTurnoHE())<0) && 
                                        (ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())==0
                                        || ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0)){
                                        Asistencia = " ";
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarc.getTime().compareTo(jornadaEmpleado.getTardanzaHE())<=0 
                                            && primeraMarc.getTime().compareTo(jornadaEmpleado.getTurnoHE())>=0 ){
                                        Asistencia = "TARDANZA";
                                        //Agregado a tardanza
                                        horaM.setTime(primeraMarc.getTime());
                                        horaJ.setTime(jornadaEmpleado.getTurnoHE());
                                        horaM.set(Calendar.YEAR, horaJ.get(Calendar.YEAR));
                                        horaM.set(Calendar.MONTH, horaJ.get(Calendar.MONTH));
                                        horaM.set(Calendar.DAY_OF_MONTH, horaJ.get(Calendar.DAY_OF_MONTH));
                                        System.out.println("HORA M: "+horaM.getTime().toString());
                                        System.out.println("HORA J: "+horaJ.getTime().toString());
                                        int minTardanza = ReporteUtil.restarFechas(horaM.getTime(), horaJ.getTime(), "m");
                                        tardanza += minTardanza;
                                        System.out.println("Minutos de tardnaza del empleado: "+minTardanza);
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarc.getTime().compareTo(jornadaEmpleado.getTardanzaHE())>=0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<=0){
                                        Asistencia = "FALTA";
                                        tardanza += 8*60;
                                        //Cambio hasta tener directiva
                                        //Asistencia = "TARDANZA";
                                    }else if(primeraMarc.getTime().compareTo(jornadaEmpleado.getTardanzaHE())<=0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<=0){
                                        System.out.println("Hora de salida jornada: "+jornadaEmpleado.getTurnoHS().toString());
                                        Asistencia = "FALTA";
                                        tardanza += 8*60;
                                        //Cambio hasta tener directiva
                                        //Asistencia = "TARDANZA";
                                    }else if(primeraMarc.getTime().compareTo(jornadaEmpleado.getTardanzaHE())<=0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>=0){
                                        //Asistencia = "Falta";
                                        //Cambio hasta tener directiva
                                        Asistencia = "TARDANZA";
                                        //Agregado a tardanza
                                        horaM.setTime(primeraMarc.getTime());
                                        horaJ.setTime(jornadaEmpleado.getTurnoHE());
                                        horaM.set(Calendar.YEAR, horaJ.get(Calendar.YEAR));
                                        horaM.set(Calendar.MONTH, horaJ.get(Calendar.MONTH));
                                        horaM.set(Calendar.DAY_OF_MONTH, horaJ.get(Calendar.DAY_OF_MONTH));
                                        System.out.println("HORA M: "+horaM.getTime().toString());
                                        System.out.println("HORA J: "+horaJ.getTime().toString());
                                        int minTardanza = ReporteUtil.restarFechas(horaM.getTime(), horaJ.getTime(), "m");
                                        tardanza += minTardanza;
                                        System.out.println("Minutos de tardnaza del empleado: "+minTardanza);
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarc.getTime().compareTo(jornadaEmpleado.getTardanzaHE())>=0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>=0){
                                        //Asistencia = "Falta";
                                        //Cambio hasta tener directiva
                                        Asistencia = "TARDANZA";
                                        //Agregado a tardanza
                                        horaM.setTime(primeraMarc.getTime());
                                        horaJ.setTime(jornadaEmpleado.getTurnoHE());
                                        horaM.set(Calendar.YEAR, horaJ.get(Calendar.YEAR));
                                        horaM.set(Calendar.MONTH, horaJ.get(Calendar.MONTH));
                                        horaM.set(Calendar.DAY_OF_MONTH, horaJ.get(Calendar.DAY_OF_MONTH));
                                        System.out.println("HORA M: "+horaM.getTime().toString());
                                        System.out.println("HORA J: "+horaJ.getTime().toString());
                                        int minTardanza = ReporteUtil.restarFechas(horaM.getTime(), horaJ.getTime(), "m");
                                        tardanza += minTardanza;
                                        System.out.println("Minutos de tardnaza del empleado: "+minTardanza);
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }
                                    //Para la compensacion
                                    if(isSelectedComp){
                                        if(ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0){
                                            calC.setTime(ultimaMarcacion.getHora());
                                            int horaUM = calC.get(Calendar.HOUR_OF_DAY);
                                            int minUM = calC.get(Calendar.MINUTE);
                                            calC.setTime(jornadaEmpleado.getTurnoHS());
                                            int horaHS = calC.get(Calendar.HOUR_OF_DAY);
                                            int minHS = calC.get(Calendar.MINUTE);
                                            System.out.println("Horas: "+" "+horaUM+" "+minUM+" "+horaHS+" "+minHS);
                                            if(horaUM>horaHS){
                                                if(minUM>minHS){
                                                    compensacion = ""+(horaUM-horaHS)+" H";
                                                }else{
                                                    compensacion = (horaUM-(horaHS+1))+" H";
                                                }
                                            }else{
                                                compensacion = "0 H";
                                            }
                                        }
                                    }        
                                }else{
                                    Asistencia = "FALTA";
                                    tardanza += 8*60;
                                }
                            }else{
                                Asistencia = "FALTA";
                                tardanza += 8*60;
                            }                                
                            }else{
                                Asistencia = "FALTA";
                                tardanza += 8*60;
                            }
                            if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>=0){
                                marcacion2 = primeraMarcacion.getHora().toString();   
                                marcacion = "---";
                            }else{
                                marcacion = primeraMarcacion.getHora().toString();        
                            }
                            
                        }else{
                            Asistencia = "No tiene grupo horario";
                        }
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = "FALTA";
                        tardanza += 8*60;
                    }
                }else if(condicion.equals("Exonerado")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }
                }else if(condicion.equals("Designado")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }
                }else if(condicion.equals("Otra sede")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion.toUpperCase();
                    }
                }else{
                    marcacion = "---";
                    marcacion2 = "---";
                    Asistencia = "Condición sin especificar".toUpperCase();
                }   

                //Validacion de permiso
                //Procesar permisos con horas tbm si no encuentra permisos por fechas 
                AsignacionPermiso asignacionPermisoEmpleadoDia = aspc.buscarXDia(dni, fecha);
                Calendar horaESinCero = Calendar.getInstance();
                Calendar horaFSinCero = Calendar.getInstance();
                if(primeraMarcacion.getHora()!=null){
                    horaESinCero.setTime(primeraMarcacion.getHora());
                    horaESinCero.set(Calendar.SECOND, 0);
                }
                if(ultimaMarcacion!=null){
                    if(ultimaMarcacion.getHora()!=null){
                        horaFSinCero.setTime(ultimaMarcacion.getHora());
                        horaFSinCero.set(Calendar.SECOND, 0);
                    }  
                }
                
                AsignacionPermiso asigPerEntrada = aspc.buscarOnlyHora(dni, horaESinCero.getTime(), fecha);
                AsignacionPermiso asigPerSalida = aspc.buscarOnlyHora(dni, horaFSinCero.getTime(), fecha);
                if(asignacionPermisoEmpleadoDia!=null){
                    Permiso = asignacionPermisoEmpleadoDia.getPermiso().getDocumento().toUpperCase();
                    Asistencia = asignacionPermisoEmpleadoDia.getPermiso().getTipoPermiso().getNombre().toUpperCase();
                    tardanza = 0;
                }else if(asigPerEntrada!=null){
                    Permiso = asigPerEntrada.getPermiso().getDocumento().toUpperCase();
                    Asistencia = asigPerEntrada.getPermiso().getTipoPermiso().getNombre().toLowerCase();
                    tardanza = 0;
                }else if(asigPerSalida!=null){
                    Permiso = asigPerSalida.getPermiso().getDocumento().toUpperCase();
                    Asistencia = asigPerSalida.getPermiso().getTipoPermiso().getNombre().toUpperCase();
                }else{
                    Permiso = "";
                }
                //Validacion de Vacaciones
                Vacacion vacacionEmpleado = vc.buscarXDia(dni, fecha);
                if(vacacionEmpleado!=null){
                    Vacaciones = ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaInicio())+" al "+ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaFin());
                    Asistencia = "VACACIONES";
                    Permiso = vacacionEmpleado.getDocumento().toUpperCase();
                    tardanza = 0;
                    //Cambio para reporte
//                    marcacion = "---";
//                    marcacion2 = "---";
                }else{
                    Vacaciones = "";
                }
                if(isFeriado){
                    Asistencia = feriado;
                }
                iterador.add(Calendar.DATE, 1);
                registro.setDni(dni);
                registro.setNombre(empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre());
                registro.setFechaRegistro(fecha);
                registro.setCondicion(condicion);
                registro.setMarcacionEntrada(marcacion);
                registro.setMarcacionSalida(marcacion2);
                if(situacionCon.equals("Contrato en espera")){
                    registro.setEstado(situacionCon+" - "+Asistencia);
                }else{
                    registro.setEstado(Asistencia);
                }
           
                registro.setObservacion(Permiso);
                registro.setVacaciones(Vacaciones);
                registro.setMinTardanza(tardanza);
                registro.setMinCompensacion(0);
                //registro.setHorarioAsignado(horarioasignadoarriba);
                asistenciaEmpleados.add(registro);

            }
        }
        Calendar horaActual = Calendar.getInstance();
        System.out.println("SE TERMINARON LAS TAREAS EN LA HORA "+horaActual.getTime());
        return asistenciaEmpleados;
    }
     private boolean isOnosmatico(Date fecha, EmpleadoT empleado){
        Calendar fechaOno = Calendar.getInstance();
        fechaOno.setTime(fecha);
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(empleado.getFechaNacimiento());
        fechaNac.set(Calendar.YEAR, fechaOno.get(Calendar.YEAR));
        
        String f1 = ReporteUtil.obtenerFechaFormateada(fechaOno.getTime(), "-");
        String f2 = ReporteUtil.obtenerFechaFormateada(fechaNac.getTime(),"-");
        return f1.equals(f2);
    } 
    
    private boolean crearOnomastico(Date fecha, EmpleadoT empleado){
        pc.prepararCrear();
        Permiso onomastico = pc.getSeleccionado();
        
        //CREAMOS LA ASIGNACION
        AsignacionPermiso ap = new AsignacionPermiso();        
        ap.setEmpleado(empleado.getNroDocumento());
        ap.setPermiso(onomastico);     
        //
        onomastico.getAsignacionPermisoList().add(ap);
        onomastico.setFechaInicio(fecha);
        onomastico.setFechaFin(fecha);
        onomastico.setPorFecha(true);
        onomastico.setTipoPermiso(tpc.buscarPorId("ONO"));
        onomastico.setOpcion('F');
        onomastico.setMotivo("LICENCIA POR ONOMÁSTICO");
        onomastico.setDocumento("LICENCIA POR ONOMÁSTICO");
        
        long diferencia = onomastico.getFechaFin().getTime() - onomastico.getFechaInicio().getTime();
        BigDecimal diferenciaMin = new BigDecimal(diferencia / (60 * 1000 * 60));
        onomastico.setDiferencia(diferenciaMin);
        
        if(pc.accion(Controlador.NUEVO)){
            LOG.info("SE GUARDO EL PERMISO POR ONOMASTICO");
            return true;
        }else{
            LOG.info("HUBO UN ERROR");
            return false;
        }
    }
    
    public int minutosSinGocePorLicencias(String dni, Date fechaInicio, Date fechaFin){
        Calendar cal = Calendar.getInstance();
        Calendar iterador = Calendar.getInstance();
        int diasDescuento = 0;
        int diasDescuentoSimple = 0;
        double minDescuento = 0;
        iterador.setTime(fechaInicio);
        List<Integer> listaPermisos = new ArrayList<>();
        while(iterador.getTime().compareTo(fechaFin)<=0){
            iterador.set(Calendar.HOUR_OF_DAY, 0);
            iterador.set(Calendar.MINUTE, 0);
            iterador.set(Calendar.SECOND, 0);
            AsignacionPermiso asignaciones = aspc.buscarXDiaAll(dni,iterador.getTime());
            System.out.println("Fecha ingresada : "+iterador.getTime());
            if(asignaciones!=null){
                System.out.println("Asignacion: "+asignaciones.getPermiso().toString());
                Permiso permisoProcesar = asignaciones.getPermiso();
                System.out.println("Tiene un permiso"+permisoProcesar.getMotivo());
                if(permisoProcesar.getTipoPermiso().getTipoDescuento()=='S'){
                    if(permisoProcesar.getOpcion()=='F'){
                        if(!listaPermisos.contains(permisoProcesar.getId().intValue())){
                            Date fechaInicio1 = permisoProcesar.getFechaInicio();
                            Date fechaFin1 = permisoProcesar.getFechaFin();
                            if(fechaInicio1.compareTo(fechaFin1)<=0){
                                cal.setTime(fechaInicio1);
                                //int diaInicio = cal.get(Calendar.DAY_OF_MONTH);
                                //cal.setTime(fechaFin1);
                                //int diaFin = cal.get(Calendar.DAY_OF_MONTH);
                                //while(cal.getTime().compareTo(fechaFin1)<=0){
                                    diasDescuento += 1;
                                //}
                                //diasDescuento += diaFin - diaInicio + 1;
                                System.out.println("dias: "+diasDescuento);
                            }
                            //else if(fechaInicio1.compareTo(fechaFin1)==0){
                              //  diasDescuentoSimple += 1;
                            //}
                            //listaPermisos.add(permisoProcesar.getId().intValue());
                        }
                    }else{
                        if(permisoProcesar.getOpcion()=='H'){
                            Date horaInicio1 = permisoProcesar.getHoraInicio();
                            Date horaFin1 = permisoProcesar.getHoraFin();
                            if(horaInicio1.compareTo(horaFin1)<0){
                                minDescuento += ReporteUtil.restarFechas(permisoProcesar.getHoraFin(), permisoProcesar.getHoraInicio(),"m");
                            }
                            System.out.println("Minutos de permisos sin goce por horas: "+minDescuento);
                        }
                   }
                }
            }else{
                System.out.println("No hay registros");
            }
            iterador.add(Calendar.DATE, 1);
        }
        System.out.println("Dias de descuento: "+(diasDescuento+diasDescuentoSimple));
        int minutosT = (diasDescuento+diasDescuentoSimple) * 480;
        return (int) (minutosT + minDescuento);
    }
 
    public int minutosSinGocePorPermisos(String dni, Date fechaInicio, Date fechaFin){
        Calendar cal = Calendar.getInstance();
        Calendar iterador = Calendar.getInstance();
        int diasDescuento = 0;
        int diasDescuentoSimple = 0;
        double minDescuento = 0;
        iterador.setTime(fechaInicio);
        List<Integer> listaPermisos = new ArrayList<>();
        while(iterador.getTime().compareTo(fechaFin)<=0){
            iterador.set(Calendar.HOUR_OF_DAY, 0);
            iterador.set(Calendar.MINUTE, 0);
            iterador.set(Calendar.SECOND, 0);
            AsignacionPermiso asignaciones = aspc.buscarXDiaPP(dni,iterador.getTime());
            System.out.println("Fecha ingresada : "+iterador.getTime());
            if(asignaciones!=null){
                System.out.println("Asignacion: "+asignaciones.getPermiso().toString());
                Permiso permisoProcesar = asignaciones.getPermiso();
                System.out.println("Tiene un permiso"+permisoProcesar.getMotivo());
                if(permisoProcesar.getTipoPermiso().getTipoDescuento()=='S'){
                    if(permisoProcesar.getOpcion()=='F'){
                        if(!listaPermisos.contains(permisoProcesar.getId().intValue())){
                            Date fechaInicio1 = permisoProcesar.getFechaInicio();
                            Date fechaFin1 = permisoProcesar.getFechaFin();
                            if(fechaInicio1.compareTo(fechaFin1)<=0){
                                cal.setTime(fechaInicio1);
                                //int diaInicio = cal.get(Calendar.DAY_OF_MONTH);
                                //cal.setTime(fechaFin1);
                                //int diaFin = cal.get(Calendar.DAY_OF_MONTH);
                                //while(cal.getTime().compareTo(fechaFin1)<=0){
                                    diasDescuento += 1;
                                //}
                                //diasDescuento += diaFin - diaInicio + 1;
                                System.out.println("dias: "+diasDescuento);
                            }
                            //else if(fechaInicio1.compareTo(fechaFin1)==0){
                              //  diasDescuentoSimple += 1;
                            //}
                            //listaPermisos.add(permisoProcesar.getId().intValue());
                        }
                    }else{
                        if(permisoProcesar.getOpcion()=='H'){
                            Date horaInicio1 = permisoProcesar.getHoraInicio();
                            Date horaFin1 = permisoProcesar.getHoraFin();
                            if(horaInicio1.compareTo(horaFin1)<0){
                                minDescuento += ReporteUtil.restarFechas(permisoProcesar.getHoraFin(), permisoProcesar.getHoraInicio(),"m");
                            }
                            System.out.println("Minutos de permisos sin goce por horas: "+minDescuento);
                        }
                   }
                }
            }else{
                System.out.println("No hay registros");
            }
            iterador.add(Calendar.DATE, 1);
        }
        System.out.println("Dias de descuento: "+(diasDescuento+diasDescuentoSimple));
        int minutosT = (diasDescuento+diasDescuentoSimple) * 480;
        return (int) (minutosT + minDescuento);
    }
    
    public void analisisOnomasticos(Date fechaInicio, Date fechaFin, List<String> dnis){  
        for(String dni : dnis){     
            Calendar iterador = Calendar.getInstance();
            iterador.setTime(fechaInicio);
            while(iterador.getTime().compareTo(fechaFin) <= 0){
                Date fecha = iterador.getTime();
                //Descartar si es sabado o domingo
                System.out.println("Dni: "+dni+" Fecha: "+fecha.toString());
                
                EmpleadoT empleado = ec.buscarPorDni(dni);
                /**
                 * Generacion de permiso por onomastico
                 */
                if(isOnosmatico(fecha, empleado)){
                    Calendar fechaPermisoOno =Calendar.getInstance();
                    fechaPermisoOno.setTime(fecha);

                    while(fc.buscarXDia(fechaPermisoOno.getTime())!=null){
                        fechaPermisoOno.add(Calendar.DATE, 1);
                    }
                    while(!ReporteUtil.isDiaLaboral(fechaPermisoOno.getTime())){
                        fechaPermisoOno.add(Calendar.DATE, 1);
                    }
                    if(ReporteUtil.isDiaLaboral(fechaPermisoOno.getTime())){
                        //busca onomastico
                        if(aspc.buscarXDiaOnosmatico(dni, fechaPermisoOno.getTime())!=null){
                            System.out.println("Hay cumpleañosd d_d");
                        }else{
                            System.out.println("Crear Cumple");
                            crearOnomastico(fechaPermisoOno.getTime(), empleado);
                        }
                    }
                }else{
                    System.out.println("No cumple");
                }
                iterador.add(Calendar.DATE, 1);
            }
        }
    }
}
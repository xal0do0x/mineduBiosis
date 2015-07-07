/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.procesos;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controladores.AsignacionHorarioControlador;
import controladores.AsignacionPermisoControlador;
import controladores.Controlador;
import controladores.DetalleGrupoControlador;
import controladores.EmpleadoControlador;
import controladores.EmpleadoOpcionInfoControlador;
import controladores.FeriadoControlador;
import controladores.MarcacionControlador;
import controladores.PermisoControlador;
import controladores.RegistroAsistenciaControlador;
import controladores.TipoPermisoControlador;
import controladores.VacacionControlador;
import entidades.AsignacionHorario;
import entidades.AsignacionPermiso;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.EmpleadoOpcionInfo;
import entidades.Horario;
import entidades.Jornada;
import entidades.Marcacion;
import entidades.Permiso;
import entidades.Vacacion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.castor.mapping.AbstractMappingLoaderFactory.LOG;
import pruebareportes.ReporteUtil;

/**
 *
 * @author Aldo
 */
public class rptAsistenciaTotal {
    private final RegistroAsistenciaControlador rg = new RegistroAsistenciaControlador();
    private final EmpleadoControlador ec = new EmpleadoControlador();
    private final AsignacionHorarioControlador ashc = new AsignacionHorarioControlador();
    private final DetalleGrupoControlador dc = new DetalleGrupoControlador();
    private final AsignacionPermisoControlador aspc = new AsignacionPermisoControlador();
    private final VacacionControlador vc = new VacacionControlador();
    private final PermisoControlador pc = new PermisoControlador();
    private final MarcacionControlador mc = new MarcacionControlador();
    private final EmpleadoOpcionInfoControlador eoc = new EmpleadoOpcionInfoControlador();
    private final FeriadoControlador fc = new FeriadoControlador();
    private final TipoPermisoControlador tpc = new TipoPermisoControlador();
    
    public void crearPdf(String nombreFile,List<String> dnis,Date fechaInicio,Date fechaFin, String oficina,String tipo, String usuario, boolean isSelectedComp, boolean isSelectedHoraM) 
            throws IOException, DocumentException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream(nombreFile));
        documento.open();
        Image cabecera = Image.getInstance("img/cabecera.png");
        cabecera.setAlignment(1);
        documento.add(cabecera);
        String nombreGrupoOficina = "";
        if(tipo=="O"){
            nombreGrupoOficina = "DEPENDENCIA:      ";
        }else if(tipo=="G"){
            nombreGrupoOficina = "GRUPO HORARIO: ";
        }else if(tipo=="P"){
            nombreGrupoOficina = "DEPENDENCIA:      ";
        }
        Font font = new Font(Font.HELVETICA,10,Font.BOLD);
        Chunk nombreReporte = new Chunk("REPORTE DE CONTROL DE ASISTENCIA",new Font(Font.HELVETICA,12,Font.BOLD));
        Chunk labelOficina = new Chunk(nombreGrupoOficina,font);
        Chunk labelFechaInicio = new Chunk("FECHA INICIO:        ",font);
        Chunk labelFechaFin = new Chunk("       FECHA FIN: ",font);
        //Chunk labelUsuario = new Chunk("USUARIO: ",font);
        
        
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.HELVETICA,10));
        Chunk nombreFechaInicio = new Chunk(ReporteUtil.obtenerFechaFormateada(fechaInicio,"/").toUpperCase(),new Font(Font.HELVETICA,10));
        Chunk nombreFechaFin = new Chunk(ReporteUtil.obtenerFechaFormateada(fechaFin, "/").toUpperCase(),new Font(Font.HELVETICA,10));
        //Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.HELVETICA,10));
              
        Paragraph pNombreReporte = new Paragraph(nombreReporte);
        pNombreReporte.setAlignment(1);
        documento.add(pNombreReporte);
        documento.add(ReporteUtil.darEspaciado(25));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        if(tipo=="P"){
            Chunk labelDniEmpleado = new Chunk("DNI:                           ",font);
            Chunk labelNombreEmpleado = new Chunk("NOMBRE:                ",font);
            Empleado empleado = ec.buscarPorDni(dnis.get(0));
            Chunk nombreDni = new Chunk(empleado.getNroDocumento(), new Font(Font.HELVETICA,10));
            Chunk nombreEmpleado = new Chunk(empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre(), new Font(Font.HELVETICA,10));
            documento.add(new Paragraph(ReporteUtil.unirChunks(labelNombreEmpleado, nombreEmpleado)));
            documento.add(ReporteUtil.darEspaciado(15));           
            documento.add(new Paragraph(ReporteUtil.unirChunks(labelDniEmpleado, nombreDni)));
            documento.add(ReporteUtil.darEspaciado(15));
        }
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelFechaInicio,nombreFechaInicio,labelFechaFin,nombreFechaFin)));
        documento.add(ReporteUtil.darEspaciado(15));
        
        //documento.add(new Paragraph(ReporteUtil.unirChunks(labelFechaFin,nombreFechaFin)));
        //documento.add(ReporteUtil.darEspaciado(15));
        //documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(20));
        PdfPTable tabla = new rptAsistenciaTotal().crearTabla(dnis, fechaInicio, fechaFin, isSelectedComp,isSelectedHoraM,tipo);
        documento.add(tabla);
        documento.close();
        try {
            File path = new File (nombreFile);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public PdfPTable crearTabla(List<String> dnis,Date fechaInicio,Date fechaFin,boolean isSelectedComp, boolean isSelectedHoraM,String tipo) throws DocumentException{
        /**
         * Procesamiento para info para generar tablas
         */
        /**
         * Procesamiento para elaborar tabla con datos
         */
        int nroColumnas;
        if(isSelectedComp){
            nroColumnas = 9;
        }else{
            nroColumnas = 8;
        }
        if(tipo=="P"){
            nroColumnas-=2;
        }
        //int nroColumnas = 8;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];
        if(tipo=="P"){
            widthColumna[0]=1;
            widthColumna[1]=1;
            widthColumna[2]=1;
            widthColumna[3]=1;
            widthColumna[4]=2;
            widthColumna[5]=1;
            if(isSelectedComp){
                widthColumna[6]=1;
            }
        }else{
            widthColumna[0]=1;
            widthColumna[1]=4;
            widthColumna[2]=1;
            widthColumna[3]=1;
            widthColumna[4]=1;
            widthColumna[5]=1;
            widthColumna[6]=2;
            widthColumna[7]=1;
            if(isSelectedComp){
                widthColumna[8]=1;
            }
        }
                
        tabla.setWidths(widthColumna);
        
        //Definimos celdas iniciales
        Font fontCabecera = new Font(Font.HELVETICA,8,Font.BOLD);
        Font fontCelda = new Font(Font.HELVETICA,7);
//        HeaderFooter cabecera = new HeaderFooter(new Phrase("This is a header."), false);
//        cabecera.
        
        if(tipo!="P"){
            PdfPCell h0 = new PdfPCell(new Phrase("DNI",fontCabecera));
            h0.setHorizontalAlignment(1);
            h0.setGrayFill(0.7f);
            h0.setColspan(1);
            tabla.addCell(h0);

            PdfPCell h1 = new PdfPCell(new Phrase("NOMBRE EMPLEADO",fontCabecera));
            h1.setHorizontalAlignment(1);
            h1.setGrayFill(0.7f);
            h1.setColspan(1);
            tabla.addCell(h1);
        }
        
        PdfPCell h2 = new PdfPCell(new Phrase("FECHA",fontCabecera));
        h2.setHorizontalAlignment(1);
        h2.setGrayFill(0.7f);
        h2.setColspan(1);
        tabla.addCell(h2);
        
        PdfPCell h3 = new PdfPCell(new Phrase("MARC. ENTR.",fontCabecera));
        h3.setHorizontalAlignment(1);
        h3.setGrayFill(0.7f);
        h3.setColspan(1);
        tabla.addCell(h3);
        
        PdfPCell h4 = new PdfPCell(new Phrase("MARC. SAL.",fontCabecera));
        h4.setHorizontalAlignment(1);
        h4.setGrayFill(0.7f);
        h4.setColspan(1);
        tabla.addCell(h4);
       
        PdfPCell h5 = new PdfPCell(new Phrase("ESTADO",fontCabecera));
        h5.setHorizontalAlignment(1);
        h5.setGrayFill(0.7f);
        h5.setColspan(1);
        tabla.addCell(h5);
        
        PdfPCell h6 = new PdfPCell(new Phrase("OBSERV.",fontCabecera));
        h6.setHorizontalAlignment(1);
        h6.setGrayFill(0.7f);
        h6.setColspan(1);
        tabla.addCell(h6);
        
        PdfPCell h7 = new PdfPCell(new Phrase("PER. VAC",fontCabecera));
        h7.setHorizontalAlignment(1);
        h7.setGrayFill(0.7f);
        h7.setColspan(1);
        tabla.addCell(h7);
        
        if(isSelectedComp){
            PdfPCell h8 = new PdfPCell(new Phrase("COMPENSA",fontCabecera));
            h8.setHorizontalAlignment(1);
            h8.setGrayFill(0.7f);
            h8.setColspan(1);
            tabla.addCell(h8);
        }
        //Hacemos que la primera fila sea la cabecera
        tabla.setHeaderRows(1);
        /**
         * Procesamiento de los datos para generar los registros de la entrada
         */
        
        Calendar calC = Calendar.getInstance();
        for(String dni : dnis){ 
            Calendar iterador = Calendar.getInstance();
            iterador.setTime(fechaInicio);
            while(iterador.getTime().compareTo(fechaFin) <= 0){
                Date fecha = iterador.getTime();
                //Descartar si es sabado o domingo
                System.out.println("Dni: "+dni+" Fecha: "+fecha.toString());
                Empleado empleado = ec.buscarPorDni(dni);
                boolean isFeriado = false;
                String feriado = "";
                //Para inicio de contrato
                if(empleado.getFechaInicioContrato().compareTo(fecha)<=0){
                    System.out.println("OK");
                }else{
                    iterador.add(Calendar.DATE, 1);
                    continue;
                }
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
                        primeraMarcacion = marcaciones.get(0);
                        if(marcaciones.size()>1){
                            ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                            marcacion2 = ultimaMarcacion.getHora().toString();
                        }else{
                            marcacion2 = "---";
                        }
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
                            Jornada jornadaEmpleado =  horarioEmpleado.getJornada();

                            //Validacion de asistencia
                            if(ultimaMarcacion.getHora()!=null){
                                if(!isSelectedHoraM){
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                }
                                if(primeraMarcacion.getHora().compareTo(ultimaMarcacion.getHora())<0){
                                    if((primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())==0 
                                        || primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())<0) && 
                                        (ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())==0
                                        || ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0)){
                                        Asistencia = " ";
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                            && primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())>0 ){
                                        Asistencia = "TARDANZA";
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())>0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<0){
                                        Asistencia = "FALTA";
                                        //Cambio hasta tener directiva
                                        //Asistencia = "TARDANZA";
                                    }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<0){
                                        System.out.println("Hora de salida jornada: "+jornadaEmpleado.getTurnoHS().toString());
                                        Asistencia = "FALTA";
                                        //Cambio hasta tener directiva
                                        //Asistencia = "TARDANZA";
                                    }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0){
                                        //Asistencia = "Falta";
                                        //Cambio hasta tener directiva
                                        Asistencia = "TARDANZA";
                                        //marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())>0 
                                            && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0){
                                        //Asistencia = "Falta";
                                        //Cambio hasta tener directiva
                                        Asistencia = "TARDANZA";
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
                                }
                            }else{
                                Asistencia = "FALTA";
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
                if(ultimaMarcacion.getHora()!=null){
                    horaFSinCero.setTime(ultimaMarcacion.getHora());
                    horaFSinCero.set(Calendar.SECOND, 0);
                }
                
                AsignacionPermiso asigPerEntrada = aspc.buscarOnlyHora(dni, horaESinCero.getTime(), fecha);
                AsignacionPermiso asigPerSalida = aspc.buscarOnlyHora(dni, horaFSinCero.getTime(), fecha);
                if(asignacionPermisoEmpleadoDia!=null){
                    Permiso = asignacionPermisoEmpleadoDia.getPermiso().getDocumento().toUpperCase();
                    Asistencia = asignacionPermisoEmpleadoDia.getPermiso().getTipoPermiso().getNombre().toUpperCase();
                }else if(asigPerEntrada!=null){
                    Permiso = asigPerEntrada.getPermiso().getDocumento().toUpperCase();
                    Asistencia = asigPerEntrada.getPermiso().getTipoPermiso().getNombre().toLowerCase();
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
                }else{
                    Vacaciones = "";
                }
                if(isFeriado){
                    Asistencia = feriado;
                }
                PdfPCell celdaNombre = new PdfPCell(new Phrase(empleado.getNroDocumento(),fontCelda));
                celdaNombre.setMinimumHeight(15f);
                if(tipo!="P"){
                    //DNI
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
                    //Nombre
                    String celda0 = empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre();
                    celdaNombre.setPhrase(new Phrase(celda0,fontCelda));
                    celdaNombre.setHorizontalAlignment(0);
                    tabla.addCell(celdaNombre);
                }
                //Fecha
                String celdaA = ReporteUtil.obtenerFechaFormateada(fecha,"/");
                celdaNombre.setPhrase(new Phrase(celdaA,fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                //Hora Marcacion de entrada
                String celda = marcacion;
                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Hora Marcacion de Salida
                String celdaM = marcacion2;
                celdaNombre.setPhrase(new Phrase(celdaM,fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                //Asistencia
                String celda1 = Asistencia;
                celdaNombre.setPhrase(new Phrase(celda1,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Permiso
                String celda2 = Permiso;
                celdaNombre.setPhrase(new Phrase(celda2,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Vacacion
                String celda3 = Vacaciones;
                celdaNombre.setPhrase(new Phrase(celda3,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Compensacion
                if(isSelectedComp){
                    String celda4 = compensacion;
                    celdaNombre.setPhrase(new Phrase(celda4,fontCelda));
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
                }
                iterador.add(Calendar.DATE, 1);
            }
        }
        return tabla;
    }  
    
    private boolean isOnosmatico(Date fecha, Empleado empleado){
        Calendar fechaOno = Calendar.getInstance();
        fechaOno.setTime(fecha);
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(empleado.getFechaNacimiento());
        fechaNac.set(Calendar.YEAR, fechaOno.get(Calendar.YEAR));
        
        String f1 = ReporteUtil.obtenerFechaFormateada(fechaOno.getTime(), "-");
        String f2 = ReporteUtil.obtenerFechaFormateada(fechaNac.getTime(),"-");
        return f1.equals(f2);
    } 
    
    private boolean crearOnomastico(Date fecha, Empleado empleado){
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
}
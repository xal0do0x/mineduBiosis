/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.reportes.procesos;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controladores.AsignacionHorarioControlador;
import controladores.AsignacionPermisoControlador;
import controladores.DetalleGrupoControlador;
import controladores.EmpleadoControlador;
import controladores.EmpleadoOpcionInfoControlador;
import controladores.MarcacionControlador;
import controladores.PermisoControlador;
import controladores.RegistroAsistenciaControlador;
import controladores.VacacionControlador;
import entidades.AsignacionHorario;
import entidades.AsignacionPermiso;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.EmpleadoOpcionInfo;
import entidades.Horario;
import entidades.Jornada;
import entidades.Marcacion;
import entidades.Vacacion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pruebareportes.ReporteUtil;

/**
 *
 * @author Aldo
 */
public class rptFaltas {
    private final RegistroAsistenciaControlador rg = new RegistroAsistenciaControlador();
    private final EmpleadoControlador ec = new EmpleadoControlador();
    private final AsignacionHorarioControlador ashc = new AsignacionHorarioControlador();
    private final DetalleGrupoControlador dc = new DetalleGrupoControlador();
    private final AsignacionPermisoControlador aspc = new AsignacionPermisoControlador();
    private final VacacionControlador vc = new VacacionControlador();
    private final PermisoControlador pc = new PermisoControlador();
    private final MarcacionControlador mc = new MarcacionControlador();
    private final EmpleadoOpcionInfoControlador eoc = new EmpleadoOpcionInfoControlador();
    
    public void crearPdf(String nombreFile,List<String> dnis,Date fechaInicio,Date fechaFin, String oficina,String tipo, String usuario) 
            throws IOException, DocumentException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream(nombreFile));
        documento.open();
        String nombreGrupoOficina = "";
        if(tipo=="O"){
            nombreGrupoOficina = "OFICINA: ";
        }else{
            nombreGrupoOficina = "GRUPO HORARIO: ";
        }
        Font font = new Font(Font.HELVETICA,10,Font.BOLD);
        Chunk nombreReporte = new Chunk("REPORTE DE FALTAS",font);
        Chunk labelOficina = new Chunk(nombreGrupoOficina,font);
        Chunk labelFechaInicio = new Chunk("FECHA INICIO: ",font);
        Chunk labelFechaFin = new Chunk("FECHA FIN: ",font);
        Chunk labelUsuario = new Chunk("USUARIO: ",font);
      
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.HELVETICA,10));
        Chunk nombreFechaInicio = new Chunk(ReporteUtil.obtenerFechaFormateada(fechaInicio,"/").toUpperCase(),new Font(Font.HELVETICA,10));
        Chunk nombreFechaFin = new Chunk(ReporteUtil.obtenerFechaFormateada(fechaFin, "/").toUpperCase(),new Font(Font.HELVETICA,10));
        Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.HELVETICA,10));
        
        documento.add(new Paragraph(nombreReporte));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelFechaInicio,nombreFechaInicio)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelFechaFin,nombreFechaFin)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(20));
        PdfPTable tabla = new rptFaltas().crearTabla(dnis, fechaInicio, fechaFin);
        documento.add(tabla);
        documento.close();
        try {
            File path = new File (nombreFile);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public PdfPTable crearTabla(List<String> dnis,Date fechaInicio,Date fechaFin) throws DocumentException{
        /**
         * Procesamiento para info para generar tablas
         */
        Calendar fechaInicioC = Calendar.getInstance();
        Calendar fechaFinC = Calendar.getInstance();
        fechaInicioC.setTime(fechaInicio);
        fechaFinC.setTime(fechaFin);
        
        int diaMesInicio,diaMesFin;
        diaMesInicio = fechaInicioC.get(Calendar.DAY_OF_MONTH);
        diaMesFin = fechaFinC.get(Calendar.DAY_OF_MONTH);
        
        // Dias contados desde un inicio y fin fecha
        ArrayList<Integer> listaInt = new ArrayList<>();
        for(int i=diaMesInicio;i<=diaMesFin;i++){
            listaInt.add(i);
        }
        //Ordenamos arraylist
        int indice,menor,aux;
        for (int i = 0; i < listaInt.size(); i++) {
            menor = listaInt.get(i);
            indice = i;
            aux=0;
            for (int j = i+1; j < listaInt.size(); j++) {
                aux = listaInt.get(j);
                indice = aux < menor ? j : indice;
                menor = aux < menor ? aux : menor;
            }
            listaInt.set(indice, listaInt.get(i));
            listaInt.set(i,menor);
        }
        /**
         * Procesamiento para elaborar tabla con datos
         */
        int nroColumnas = 3;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];

        widthColumna[0]=1;
        widthColumna[1]=5;
        widthColumna[2]=1;
        //widthColumna[8]=1;
                
        tabla.setWidths(widthColumna);
        
        //Definimos celdas iniciales
        Font fontCabecera = new Font(Font.HELVETICA,8,Font.BOLD);
        Font fontCelda = new Font(Font.HELVETICA,7);
//        HeaderFooter cabecera = new HeaderFooter(new Phrase("This is a header."), false);
//        cabecera.
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
        
        PdfPCell h2 = new PdfPCell(new Phrase("FECHA",fontCabecera));
        h2.setHorizontalAlignment(1);
        h2.setGrayFill(0.7f);
        h2.setColspan(1);
        tabla.addCell(h2);
        
//        PdfPCell h8 = new PdfPCell(new Phrase("COMPENSA",fontCabecera));
//        h8.setHorizontalAlignment(1);
//        h8.setGrayFill(0.7f);
//        h8.setColspan(1);
//        tabla.addCell(h8);
//        
        tabla.setHeaderRows(1);
        /**
         * Procesamiento de los datos para generar los registros de la entrada
         */
        
        Calendar cal = Calendar.getInstance();
        Calendar calC = Calendar.getInstance();
        for(String dni : dnis){
            //cal.setTime(fechaInicio);
            for(int i=0;i<listaInt.size();i++){
                cal.setTime(fechaInicio);
                int diaMarcacion = cal.get(Calendar.DAY_OF_MONTH)+i;
                System.out.println("Iterador: "+i+" Dni: "+dni);
                cal.set(Calendar.DAY_OF_MONTH, diaMarcacion);
                //Descartar si es sabado o domingo
                if(!ReporteUtil.isDiaLaboral(cal.getTime())){
                    continue;
                }
                //Marcaciones del dia a procesar
                List<Marcacion> marcaciones = mc.buscarXFecha(dni,cal.getTime());
                String Asistencia = "";
                String Permiso = "";
                String Vacaciones = "";
                String marcacion = "";
                String marcacion2 = "";
                String condicion = "";
                String compensacion = "";
                Marcacion primeraMarcacion = new Marcacion();
                Marcacion ultimaMarcacion = new Marcacion();
                Empleado empleado = ec.buscarPorDni(dni);

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
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        marcacion = primeraMarcacion.getHora().toString();
                        marcacion2 = ultimaMarcacion.getHora().toString();
                        
                        if(!detallesGrupos.isEmpty()){
                            DetalleGrupoHorario detalleGrupoEmpleado = detallesGrupos.get(0);
                            List<AsignacionHorario> asignaciones = ashc.buscarXGrupo(detalleGrupoEmpleado.getGrupoHorario());
                            AsignacionHorario asignacionEmpleado = asignaciones.get(0);
                            Horario horarioEmpleado = asignacionEmpleado.getHorario();
                            Jornada jornadaEmpleado =  horarioEmpleado.getJornada();

                            //Validacion de asistencia
                            if(primeraMarcacion.getHora().compareTo(ultimaMarcacion.getHora())<0){
                                if((primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())==0 
                                    || primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())<0) && 
                                    (ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())==0
                                    || ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0)){
                                    Asistencia = "Asistencia Normal";
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    continue;
                                }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                        && primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())>0 ){
                                    Asistencia = "Tardanza";
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    continue;
                                    //Marco falta en entrada y marco antes de tiempo salida
                                }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())>0 
                                        && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<0){
                                    Asistencia = "Falta";
                                    continue;
                                    //Marco tarde entrada y marco antes de tiempo salida
                                }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                        && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())<0){
                                    Asistencia = "Falta";
                                    continue;
                                    //Marco tarde entrada y marco antes de tiempo salida??
                                }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 
                                        && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0){
                                    Asistencia = "Falta";
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    continue;
                                    //Marco falta en la entrada y marco antes de tiempo salida
                                }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())>0 
                                        && ultimaMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHS())>0){
                                    Asistencia = "Falta";
                                    marcacion2 = jornadaEmpleado.getTurnoHS().toString();
                                    continue;
                                }
                            }else{
                                Asistencia = "Falta";
                                
                            }
                        }else{
                            Asistencia = "No tiene grupo horario";
                            if(primeraMarcacion.getHora().compareTo(ultimaMarcacion.getHora())<0){
                                continue;
                            }
                        }
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = "Falta";
                    }
                }else if(condicion.equals("Exonerado")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }
                    break;
                }else if(condicion.equals("Designado")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }
                    break;
                }else if(condicion.equals("Otra sede")){
                    if(!marcaciones.isEmpty()){
                        primeraMarcacion = marcaciones.get(0);
                        ultimaMarcacion = marcaciones.get(marcaciones.size()-1);
                        //marcacion = primeraMarcacion.getHora().toString();
                        //marcacion2 = ultimaMarcacion.getHora().toString();
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }else{
                        marcacion = "---";
                        marcacion2 = "---";
                        Asistencia = condicion;
                    }
                    break;
                }else{
                    marcacion = "---";
                    marcacion2 = "---";
                    Asistencia = "Condición sin especificar";
                }   

                //Validacion de permiso
                //Procesar permisos con horas tbm si no encuentra permisos por fechas 
                AsignacionPermiso asignacionPermisoEmpleadoDia = aspc.buscarXDia(dni, cal.getTime());
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
                
                AsignacionPermiso asigPerEntrada = aspc.buscarOnlyHora(dni, horaESinCero.getTime(), cal.getTime());
                AsignacionPermiso asigPerSalida = aspc.buscarOnlyHora(dni, horaFSinCero.getTime(), cal.getTime());
                if(asignacionPermisoEmpleadoDia!=null){
                    Permiso = asignacionPermisoEmpleadoDia.getPermiso().getTipoPermiso().getNombre().toLowerCase();
                    Asistencia = "Permiso";
                    continue;
                }else if(asigPerEntrada!=null){
                    Permiso = asigPerEntrada.getPermiso().getTipoPermiso().getNombre().toLowerCase();
                    Asistencia = "Permiso";
                    continue;
                }else if(asigPerSalida!=null){
                    Permiso = asigPerSalida.getPermiso().getTipoPermiso().getNombre().toLowerCase();
                    Asistencia = "Permiso";
                    continue;
                }else{
                    Permiso = "";
                }
                //Validacion de Vacaciones
                Vacacion vacacionEmpleado = vc.buscarXDia(dni, cal.getTime());
                if(vacacionEmpleado!=null){
                    Vacaciones = ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaInicio())+" al "+ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaFin());
                    Asistencia = "Vacaciones";
                    continue;
                }else{
                    Vacaciones = "";
                }
                //DNI
                PdfPCell celdaNombre = new PdfPCell(new Phrase(empleado.getNroDocumento(),fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                //Nombre
                String celda0 = empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre();
                celdaNombre.setPhrase(new Phrase(celda0,fontCelda));
                celdaNombre.setHorizontalAlignment(0);
                tabla.addCell(celdaNombre);
                //Fecha
                String celdaA = ReporteUtil.obtenerFechaFormateada(cal.getTime(),"/");
                celdaNombre.setPhrase(new Phrase(celdaA,fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                
            }
        }
        return tabla;
    }    
     
//    public List<Date> prepararFechas(Date fechaInicio, Date fechaFin){
//        return;
//    }
//    
//    public 
}

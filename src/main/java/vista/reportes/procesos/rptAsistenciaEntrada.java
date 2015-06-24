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
import controladores.GrupoHorarioControlador;
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
import entidades.Permiso;
import entidades.RegistroAsistencia;
import entidades.Vacacion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pruebareportes.ReporteUtil;
import pruebareportes.rptTardCx;

/**
 *
 * @author Aldo
 */
public class rptAsistenciaEntrada {
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
        Chunk nombreReporte = new Chunk("REPORTE DE ASISTENCIA DE ENTRADA",font);
        Chunk labelOficina = new Chunk(nombreGrupoOficina,font);
        Chunk labelMes = new Chunk("FECHA: ",font);
        Chunk labelUsuario = new Chunk("USUARIO: ",font);
      
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.HELVETICA,10));
        Chunk nombreMes = new Chunk(ReporteUtil.obtenerFechaFormateada(fechaInicio,"/").toUpperCase(),new Font(Font.HELVETICA,10));
        Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.HELVETICA,10));
        
        documento.add(new Paragraph(nombreReporte));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelMes,nombreMes)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(20));
        PdfPTable tabla = new rptAsistenciaEntrada().crearTabla(dnis, fechaInicio, fechaFin);
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

        /**
         * Procesamiento para elaborar tabla con datos
         */
        int nroColumnas = 6;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];

        widthColumna[0]=1;
        widthColumna[1]=5;
        widthColumna[2]=2;
        widthColumna[3]=2;
        widthColumna[4]=2;
        widthColumna[5]=2;
                
        tabla.setWidths(widthColumna);
        
        //Definimos celdas iniciales
        Font fontCabecera = new Font(Font.HELVETICA,8,Font.BOLD);
        Font fontCelda = new Font(Font.HELVETICA,7);
        
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
        
        PdfPCell h2 = new PdfPCell(new Phrase("MARCACION ENTRADA",fontCabecera));
        h2.setHorizontalAlignment(1);
        h2.setGrayFill(0.7f);
        h2.setColspan(1);
        tabla.addCell(h2);
        
        PdfPCell h3 = new PdfPCell(new Phrase("ASISTENCIA",fontCabecera));
        h3.setHorizontalAlignment(1);
        h3.setGrayFill(0.7f);
        h3.setColspan(1);
        tabla.addCell(h3);
       
        //Agregado de columna de descuento sin goce
        PdfPCell h4 = new PdfPCell(new Phrase("OBSERVACION",fontCabecera));
        h4.setHorizontalAlignment(1);
        h4.setGrayFill(0.7f);
        h4.setColspan(1);
        tabla.addCell(h4);
        
        PdfPCell h5 = new PdfPCell(new Phrase("VACACION",fontCabecera));
        h5.setHorizontalAlignment(1);
        h5.setGrayFill(0.7f);
        h5.setColspan(1);
        tabla.addCell(h5);
        
        /**
         * Procesamiento de los datos para generar los registros de la entrada
         */
        for(String dni : dnis){
            List<Marcacion> marcaciones = mc.buscarXFecha(dni, fechaInicio);
            String Asistencia = "";
            String Permiso = "";
            String Vacaciones = "";
            String marcacion = "";
            String condicion = "";
            System.out.println("DNI: "+dni);
            Empleado empleado = ec.buscarPorDni(dni);
           
            List<EmpleadoOpcionInfo> infoEmpleado = eoc.buscarTodos(Integer.parseInt(dni));
            if(!infoEmpleado.isEmpty()){
                if(infoEmpleado.get(0).getSFieldValue5().equals("0") || 
                    infoEmpleado.get(0).getSFieldValue5().equals("") ||
                    infoEmpleado.get(0).getSFieldValue5().equals(" ")){
                    condicion = "NORMAL";
                }else if(infoEmpleado.get(0).getSFieldValue5().equals("1")){
                    condicion = "EXONERADO";
                }else if(infoEmpleado.get(0).getSFieldValue5().equals("2")){
                    condicion = "DESIGNADO";
                }else if(infoEmpleado.get(0).getSFieldValue5().equals("3")){
                    condicion = "OTRA SEDE";
                }
            }else{
                condicion = "Sin dato";
            }
            System.out.println("Condicion: "+condicion);
            //Datos de horario, jornada, empleado
            List<DetalleGrupoHorario> detallesGrupos = dc.buscarXEmpleado(empleado);
            if(condicion.equals("NORMAL")){
                if(!marcaciones.isEmpty()){
                    Marcacion primeraMarcacion = marcaciones.get(0);
                    marcacion = primeraMarcacion.getHora().toString();
                    if(!detallesGrupos.isEmpty()){
                        DetalleGrupoHorario detalleGrupoEmpleado = detallesGrupos.get(0);
                        List<AsignacionHorario> asignaciones = ashc.buscarXGrupo(detalleGrupoEmpleado.getGrupoHorario());

                        AsignacionHorario asignacionEmpleado = asignaciones.get(0);
                        Horario horarioEmpleado = asignacionEmpleado.getHorario();
                        Jornada jornadaEmpleado =  horarioEmpleado.getJornada();

                        //Validacion de asistencia
                        if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())==0 || primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())<0){
                            Asistencia = "Asistencia Normal";
                        }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())<0 && primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTurnoHE())>0 ){
                            Asistencia = "Tardanza";
                        }else if(primeraMarcacion.getHora().compareTo(jornadaEmpleado.getTardanzaHE())>0){
                            Asistencia = "Falta";
                        }
                    }else{
                        Asistencia = "No tiene grupo horario";
                    }
                }else{
                    marcacion = "Sin marcacion";
                    Asistencia = "Falta";
                }
            }else if(condicion.equals("EXONERADO")){
                marcacion = "Sin marcacion";
                Asistencia = condicion;
            }else if(condicion.equals("DESIGNADO")){
                marcacion = "Sin marcacion";
                Asistencia = condicion;
            }else if(condicion.equals("OTRA SEDE")){
                marcacion = "Sin marcacion";
                Asistencia = condicion;
            }else{
                marcacion = "Sin marcacion";
                Asistencia = "Condición sin especificar";
            }   
                
            //Validacion de permiso
            //Procesar permisos con horas tbm si no encuentra permisos por fechas 
            AsignacionPermiso asignacionPermisoEmpleado = aspc.buscarXDia(dni, fechaInicio);
            if(asignacionPermisoEmpleado!=null){
                Permiso = asignacionPermisoEmpleado.getPermiso().getTipoPermiso().getNombre();
                Asistencia = "Permiso";
            }else{
                Permiso = "Sin permiso";
            }
            //Validacion de Vacaciones
            Vacacion vacacionEmpleado = vc.buscarXDia(dni, fechaInicio);
            if(vacacionEmpleado!=null){
                Vacaciones = ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaInicio())+" al "+ReporteUtil.obtenerFechaDiaMes(vacacionEmpleado.getFechaFin());
                Asistencia = "Vacaciones";
            }else{
                Vacaciones = "No tiene vacaciones";
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
            //Hora Marcacion
            String celda = marcacion;
            celdaNombre.setPhrase(new Phrase(celda,fontCelda));
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
        }
        return tabla;
    }    
     
}

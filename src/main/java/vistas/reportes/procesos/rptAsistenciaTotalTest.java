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
import controladores.EmpleadoControlador;
import entidades.Empleado;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pruebareportes.ReporteUtil;
import vistas.reportes.beans.ReporteAsistenciaBean;
import vistas.reportes.controladores.ReporteAsistenciaControlador;

/**
 *
 * @author Aldo
 */
public class rptAsistenciaTotalTest {
    
    private final EmpleadoControlador ec = new EmpleadoControlador();
    private final ReporteAsistenciaControlador rac = new ReporteAsistenciaControlador();
    
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
        PdfPTable tabla = new rptAsistenciaTotalTest().crearTabla(dnis, fechaInicio, fechaFin, isSelectedComp,isSelectedHoraM,tipo);
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
        List<ReporteAsistenciaBean> listaRegistros= rac.analisisAsistencia(fechaInicio, fechaFin, dnis, isSelectedComp, isSelectedHoraM);
        //List<ReporteAsistenciaBean> listaRegistrosThread = rac.iniciarAnalisis(fechaInicio, fechaFin, dnis);
        for(ReporteAsistenciaBean registro : listaRegistros){ 
            
                PdfPCell celdaNombre = new PdfPCell(new Phrase(registro.getDni(),fontCelda));
                celdaNombre.setMinimumHeight(15f);
                if(tipo!="P"){
                    //DNI
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
                    //Nombre
                    String celda0 = registro.getNombre();
                    celdaNombre.setPhrase(new Phrase(celda0,fontCelda));
                    celdaNombre.setHorizontalAlignment(0);
                    tabla.addCell(celdaNombre);
                }
                //Fecha
                String celdaA = ReporteUtil.obtenerFechaFormateada(registro.getFechaRegistro(),"/");
                celdaNombre.setPhrase(new Phrase(celdaA,fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                //Hora Marcacion de entrada
                String celda = registro.getMarcacionEntrada();
                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Hora Marcacion de Salida
                String celdaM = registro.getMarcacionSalida();
                celdaNombre.setPhrase(new Phrase(celdaM,fontCelda));
                celdaNombre.setHorizontalAlignment(1);
                tabla.addCell(celdaNombre);
                //Asistencia
                String celda1 = registro.getEstado();
                celdaNombre.setPhrase(new Phrase(celda1,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Permiso
                String celda2 = registro.getObservacion();
                celdaNombre.setPhrase(new Phrase(celda2,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Vacacion
                String celda3 = registro.getVacaciones();
                celdaNombre.setPhrase(new Phrase(celda3,fontCelda));
                celdaNombre.setHorizontalAlignment(1);                             
                tabla.addCell(celdaNombre);
                //Compensacion
                if(isSelectedComp){
                    String celda4 = Integer.toString(registro.getMinCompensacion());
                    celdaNombre.setPhrase(new Phrase(celda4,fontCelda));
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
                }
               // iterador.add(Calendar.DATE, 1);
            }
            return tabla;
        }
        
    }  
    

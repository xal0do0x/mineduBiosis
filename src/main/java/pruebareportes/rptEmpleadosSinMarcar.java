/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebareportes;

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
import controladores.EmpleadoControlador;
import controladores.RegistroAsistenciaControlador;
import entidades.Empleado;
import entidades.RegistroAsistencia;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aldo
 */
public class rptEmpleadosSinMarcar {
    private final RegistroAsistenciaControlador rg = new RegistroAsistenciaControlador();
    private final EmpleadoControlador em = new EmpleadoControlador();
    
    public void crearPdf(String nombreFile,List<String> dnis,Date fechaInicio,Date fechaFin, String oficina,String tipo, String usuario, Date horaInicio, Date horaFin) 
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
        Font font = new Font(Font.TIMES_ROMAN,10,Font.BOLD);
        Chunk nombreReporte = new Chunk("REPORTE DE CONSOLIDADO DE TARDANZA",font);
        Chunk labelOficina = new Chunk(nombreGrupoOficina,font);
        Chunk labelMes = new Chunk("MES: ",font);
        Chunk labelHoraInicio = new Chunk("HORA INICIO:",font);
        Chunk labelHoraFin = new Chunk("HORA FIN:", font);
        Chunk labelUsuario = new Chunk("USUARIO: ",font);
    
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.TIMES_ROMAN,10));
        Chunk nombreMes = new Chunk(ReporteUtil.obtenerNombreMes(fechaInicio).toUpperCase(),new Font(Font.TIMES_ROMAN,10));
        Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.TIMES_ROMAN,10));
        Chunk sHoraInicio = new Chunk(horaInicio.toString(),new Font(Font.TIMES_ROMAN,10));
        Chunk sHoraFin = new Chunk(horaFin.toString(),new Font(Font.TIMES_ROMAN,10));
        
        documento.add(new Paragraph(nombreReporte));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelMes,nombreMes)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelHoraInicio,sHoraInicio)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelHoraFin,sHoraFin)));
        documento.add(ReporteUtil.darEspaciado(20));
        
        PdfPTable tabla = new rptEmpleadosSinMarcar().crearTabla(dnis, fechaInicio, fechaFin);
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
        //List<RegistroAsistencia> registros = rg.buscarXEmpleadoXFecha1(dnis, fechaInicio, fechaFin);
        Calendar fechaInicioC = Calendar.getInstance();
        Calendar fechaFinC = Calendar.getInstance();
        fechaInicioC.setTime(fechaInicio);
        fechaFinC.setTime(fechaFin);
        
        int diaMesInicio,diaMesFin;
        diaMesInicio = fechaInicioC.get(Calendar.DAY_OF_MONTH);
        diaMesFin = fechaFinC.get(Calendar.DAY_OF_MONTH);
        //Definir numero de columnas de dias
//        ArrayList<Integer> listaInt = new ArrayList<Integer>();
//        Calendar fechaRegistro = Calendar.getInstance();
//        for(RegistroAsistencia registro:registros){
//            fechaRegistro.setTime(registro.getFecha());
//            Integer diaRegistro = fechaRegistro.get(Calendar.DAY_OF_MONTH);
//            if(listaInt.contains(diaRegistro)!=true){
//                if(diaRegistro>=diaMesInicio && diaRegistro<=diaMesFin){
//                    listaInt.add(diaRegistro);
//                }
//            }
//        }
        //Ordenamos arraylist
//        int indice,menor,aux;
//        for (int i = 0; i < listaInt.size(); i++) {
//            menor = listaInt.get(i);
//            indice = i;
//            aux=0;
//            for (int j = i+1; j < listaInt.size(); j++) {
//                aux = listaInt.get(j);
//                indice = aux < menor ? j : indice;
//                menor = aux < menor ? aux : menor;
//            }
//            listaInt.set(indice, listaInt.get(i));
//            listaInt.set(i,menor);
//        }
        /**
         * Procesamiento para elaborar tabla con datos
         */
        int nroColumnas = 1;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas
//        int[] widthColumna = new int[nroColumnas];
//        for (int i = 0; i < nroColumnas; i++) {
//            if(i==0){
//                widthColumna[i]=6;
//            }else if(i==(nroColumnas-1)){
//                widthColumna[i]=2;
//            }else{
//                widthColumna[i]=1;
//            }
//        }
//        tabla.setWidths(widthColumna);
        //Definimos celdas iniciales
        Font fontCabecera = new Font(Font.TIMES_ROMAN,10,Font.BOLD);
        Font fontCelda = new Font(Font.TIMES_ROMAN,9);
        PdfPCell h1 = new PdfPCell(new Phrase("Nombre del empleado",fontCabecera));
        h1.setHorizontalAlignment(3);
        h1.setGrayFill(0.7f);
        h1.setRowspan(1);
        h1.setColspan(1);
        tabla.addCell(h1);
        
//        PdfPCell h2 = new PdfPCell(new Phrase("Días Hábiles",fontCabecera));
//        h2.setHorizontalAlignment(1);
//        h2.setGrayFill(0.7f);
//        h2.setColspan(nroColumnas-2);
//        tabla.addCell(h2);
//        
//        PdfPCell h3 = new PdfPCell(new Phrase("Total (minutos)",fontCabecera));
//        h3.setHorizontalAlignment(3);
//        h3.setGrayFill(0.7f);
//        h3.setColspan(1);
//        h3.setRowspan(2);
//        tabla.addCell(h3);
        
//        PdfPCell diaColumna = new PdfPCell();
//        for (int i = 0; i < (nroColumnas-2); i++) {
//            String celda = (listaInt.get(i).toString());
//            diaColumna.setPhrase(new Phrase(celda,fontCabecera));
//            diaColumna.setHorizontalAlignment(1);
//            tabla.addCell(diaColumna);
//        }
        Calendar cal = Calendar.getInstance();
        List<Integer> conteoDias = new ArrayList<Integer>();
        
//        for (int i = 0; i < dnis.size(); i++) {
//            
//            List<Empleado> listaEmpleado = new ArrayList<Empleado>();
//            listaEmpleado = em.buscarXPatron(dnis.get(i));
//            Empleado empleado = new Empleado();
//            empleado = listaEmpleado.get(0);
//            BigDecimal totalTardanza= new BigDecimal(0.00);
//            PdfPCell celdaNombre = new PdfPCell(new Phrase(empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre(),fontCelda));
//            celdaNombre.setHorizontalAlignment(0);
//            tabla.addCell(celdaNombre);
//            List<RegistroAsistencia> registrosDni = rg.buscarXEmpleadoXFecha2(dnis.get(i), fechaInicio, fechaFin);
//            System.out.println("Numero de registros: "+registrosDni.size());
//            
////            for (RegistroAsistencia registro : registrosDni) {
////                cal.setTime(registro.getFecha());
////                for (int j = 0; j < listaInt.size(); j++) {
////                    if(cal.get(Calendar.DAY_OF_MONTH)==listaInt.get(j)){
////                        if(registro.getMinTardanza()!=null){
////                            int numero = registro.getMinTardanza().intValue();
////                            String celda = ""+numero;
////                            celdaNombre.setPhrase(new Phrase(celda,fontCelda));
////                            celdaNombre.setHorizontalAlignment(1);
////                            tabla.addCell(celdaNombre);                       
////                            totalTardanza = totalTardanza.add(registro.getMinTardanza());
////                        }else{
////                            String celda = "0.0";
////                            celdaNombre.setPhrase(new Phrase(celda,fontCelda));
////                            celdaNombre.setHorizontalAlignment(1);
////                            tabla.addCell(celdaNombre);
////                        }
////                        conteoDias.add(listaInt.get(j));
////                        break;
////                    }else{
////                        if(conteoDias.contains(listaInt.get(j))){
////                            
////                        }else{
////                            String celda = "n.r";
////                            celdaNombre.setPhrase(new Phrase(celda,fontCelda));
////                            celdaNombre.setHorizontalAlignment(1);
////                            tabla.addCell(celdaNombre);
////                            conteoDias.add(listaInt.get(j));
////                        }                       
////                    }
////                }
////            }
////            if(registrosDni.size()<(nroColumnas-2)){
////                int dfNumRegistros = (nroColumnas-2)-registrosDni.size();
////                for (int j = 0; j < dfNumRegistros; j++) {
////                    String celda = "n.r";
////                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
////                    celdaNombre.setHorizontalAlignment(1);
////                    tabla.addCell(celdaNombre);
////                }
////            }
//           
//            
////            System.out.println("ConteoDias: "+conteoDias.size());
////            System.out.println("Total tardanzas: "+totalTardanza);
////            conteoDias.clear();
////            String tardanzaTotal = totalTardanza.toString();
////            celdaNombre.setPhrase(new Phrase(tardanzaTotal,fontCelda));
////            celdaNombre.setHorizontalAlignment(1);
////            tabla.addCell(celdaNombre);
//        }
        return tabla;
    }
}

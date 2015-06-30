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
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.xml.xmp.PdfA1Schema;
import controladores.AsignacionPermisoControlador;
import controladores.EmpleadoControlador;
import controladores.PermisoControlador;
import controladores.RegistroAsistenciaControlador;
import entidades.AsignacionPermiso;
import entidades.Empleado;
import entidades.Permiso;
import entidades.RegistroAsistencia;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Aldo
 */
public class rptTardCx {
    
    private final RegistroAsistenciaControlador rg = new RegistroAsistenciaControlador();
    private final EmpleadoControlador em = new EmpleadoControlador();
    private final AsignacionPermisoControlador asp = new AsignacionPermisoControlador();
    private final PermisoControlador pc = new PermisoControlador();
    
    public void crearPdf(String nombreFile,List<String> dnis,Date fechaInicio,Date fechaFin, String oficina,String tipo, String usuario) 
            throws IOException, DocumentException{
        Document documento = new Document(PageSize.A4.rotate());
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
        Chunk labelUsuario = new Chunk("USUARIO: ",font);
      
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.TIMES_ROMAN,10));
        Chunk nombreMes = new Chunk(ReporteUtil.obtenerNombreMes(fechaInicio).toUpperCase(),new Font(Font.TIMES_ROMAN,10));
        Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.TIMES_ROMAN,10));
        
        documento.add(new Paragraph(nombreReporte));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelMes,nombreMes)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(20));
        PdfPTable tabla = new rptTardCx().crearTabla(dnis, fechaInicio, fechaFin);
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
        //agrege una columna mas (Descuento sin goce)
        int nroColumnas = listaInt.size()+3;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];
        for (int i = 0; i < nroColumnas; i++) {
            if(i==0){
                widthColumna[i]=6;
            }else if(i==(nroColumnas-1)||i==(nroColumnas-2)){
                widthColumna[i]=2;
            }else{
                widthColumna[i]=1;
            }
        }
        tabla.setWidths(widthColumna);
        //Definimos celdas iniciales
        Font fontCabecera = new Font(Font.TIMES_ROMAN,10,Font.BOLD);
        Font fontCelda = new Font(Font.TIMES_ROMAN,9);
        PdfPCell h1 = new PdfPCell(new Phrase("Nombre del empleado",fontCabecera));
        h1.setHorizontalAlignment(3);
        h1.setGrayFill(0.7f);
        h1.setRowspan(2);
        h1.setColspan(1);
        tabla.addCell(h1);
        
        PdfPCell h2 = new PdfPCell(new Phrase("Días Hábiles",fontCabecera));
        h2.setHorizontalAlignment(1);
        h2.setGrayFill(0.7f);
        h2.setColspan(nroColumnas-3);
        tabla.addCell(h2);
        
        PdfPCell h3 = new PdfPCell(new Phrase("Total (minutos)",fontCabecera));
        h3.setHorizontalAlignment(3);
        h3.setGrayFill(0.7f);
        h3.setColspan(1);
        h3.setRowspan(2);
        tabla.addCell(h3);
       
        //Agregado de columna de descuento sin goce
        PdfPCell h4 = new PdfPCell(new Phrase("Descuento S/G",fontCabecera));
        h4.setHorizontalAlignment(3);
        h4.setGrayFill(0.7f);
        h4.setColspan(1);
        h4.setRowspan(2);
        tabla.addCell(h4);
        
        
        PdfPCell diaColumna = new PdfPCell();
        for (int i = 0; i < (nroColumnas-3); i++) {
            String celda = (listaInt.get(i).toString());
            diaColumna.setPhrase(new Phrase(celda,fontCabecera));
            diaColumna.setHorizontalAlignment(1);
            tabla.addCell(diaColumna);
        }
        Calendar cal = Calendar.getInstance();
        List<Integer> conteoDias = new ArrayList<>();
        int diasDescuento = 0;
        double minDescuento = 0;
        
        for (int i = 0; i < dnis.size(); i++) {
            
            List<Empleado> listaEmpleado = new ArrayList<>();
            listaEmpleado = em.buscarXPatron(dnis.get(i));
            Empleado empleado;
            empleado = listaEmpleado.get(0);
            BigDecimal totalTardanza= new BigDecimal(0.00);
            PdfPCell celdaNombre = new PdfPCell(new Phrase(empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre(),fontCelda));
            celdaNombre.setHorizontalAlignment(0);
            tabla.addCell(celdaNombre);
            List<RegistroAsistencia> registrosDni = rg.buscarXEmpleadoXFecha2(dnis.get(i), fechaInicio, fechaFin);
            System.out.println("Numero de registros: "+registrosDni.size()+" Empleado: "+empleado.getApellidoPaterno()+" "+empleado.getApellidoPaterno()+" "+empleado.getNombre());
            List<Integer> listaPermisos = new ArrayList<>();
            if(!registrosDni.isEmpty()){
                for (RegistroAsistencia registro : registrosDni) {
                    //========================DESCUENTOS LICENCIAS=================================================
                    AsignacionPermiso asignaciones = asp.buscarXDia(empleado.getNroDocumento(),registro.getFecha());
                    if(asignaciones!=null){
                        System.out.println("Asignacion: "+asignaciones.getPermiso().toString());
                        Permiso permisoProcesar = asignaciones.getPermiso();
                        System.out.println("Tiene un permiso"+permisoProcesar.getMotivo());
                        if(permisoProcesar.getTipoPermiso().getTipoDescuento()=='S'){
                            if(permisoProcesar.getOpcion()=='F'){
                                if(!listaPermisos.contains(permisoProcesar.getId().intValue())){
                                    Date fechaInicio1 = permisoProcesar.getFechaInicio();
                                    Date fechaFin1 = permisoProcesar.getFechaFin();
                                    if(fechaInicio1.compareTo(fechaFin1)<0){
                                        cal.setTime(fechaInicio1);
                                        int diaInicio = cal.get(Calendar.DAY_OF_MONTH);
                                        cal.setTime(fechaFin1);
                                        int diaFin = cal.get(Calendar.DAY_OF_MONTH);
                                        diasDescuento += diaFin - diaInicio + 1;
                                    }else if(fechaInicio1.compareTo(fechaFin1)==0){
                                        diasDescuento = 1;
                                    }
                                    listaPermisos.add(permisoProcesar.getId().intValue());
                                }
                            }else{
                                if(permisoProcesar.getOpcion()=='H'){
                                    Date horaInicio1 = permisoProcesar.getHoraInicio();
                                    Date horaFin1 = permisoProcesar.getHoraFin();
                                    if(horaInicio1.compareTo(horaFin1)<0){
                                        minDescuento = permisoProcesar.getHoraFin().getTime()-permisoProcesar.getHoraInicio().getTime();
                                    }
                                }

                            }
                        }
                    }else{
                        System.out.println("No hay registros");
                    }
                    //========================DESCUENTOS LICENCIAS=================================================
                    cal.setTime(registro.getFecha());
                    for (int j = 0; j < listaInt.size(); j++) {
                        if(cal.get(Calendar.DAY_OF_MONTH)==listaInt.get(j)){
                            if(registro.getMinTardanza()!=null){
                                int numero = registro.getMinTardanza().intValue();
                                String celda = ""+numero;
                                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                celdaNombre.setHorizontalAlignment(1);                             
                                tabla.addCell(celdaNombre);                       
                                totalTardanza = totalTardanza.add(registro.getMinTardanza());
                            }else{
                                String celda = "0.0";
                                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                celdaNombre.setHorizontalAlignment(1);
                                tabla.addCell(celdaNombre);
                            }
                            conteoDias.add(listaInt.get(j));
                            break;
                        }else{
                            if(conteoDias.contains(listaInt.get(j))){

                            }else{
                                String celda = "n.r";
                                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                celdaNombre.setHorizontalAlignment(1);
                                tabla.addCell(celdaNombre);
                                conteoDias.add(listaInt.get(j));
                            }                       
                        }
                    }
                }
            }else{
                for(Integer dia: listaInt){
                    String celda = "n.r";
                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
                }
            }
                        
            System.out.println("ConteoDias: "+conteoDias.size());
            System.out.println("Total tardanzas: "+totalTardanza);
            conteoDias.clear();
            //String tardanzaTotal = totalTardanza.toString();
            String hora1 = "";
            if(totalTardanza.intValue() <60){
                hora1 = totalTardanza.intValue()+" min";
            }
            else if(totalTardanza.intValue()>=60 && totalTardanza.intValue()<480){
                int resto = totalTardanza.intValue()%60;
                int totalH =(totalTardanza.intValue()-resto)/60;
                hora1 = totalH+" h " + resto + " min";                
            }else if(totalTardanza.intValue()>=480){
                int restoHoras = totalTardanza.intValue()%480;
                int totalD = (totalTardanza.intValue()-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                hora1 = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(hora1,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
            //Descuento
            int minutosT = diasDescuento * 480;
            int minutosTotal = (int) (minutosT + minDescuento);
            String hora = "";
            if(minutosTotal <60){
                hora = minutosTotal+" min";
            }
            else if(minutosTotal>=60 && minutosTotal<480){
                int resto = minutosTotal%60;
                int totalH =(minutosTotal-resto)/60;
                hora = totalH+" h " + resto + " min";                
            }else if(minutosTotal>=480){
                int restoHoras = minutosTotal%480;
                int totalD = (minutosTotal-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                hora = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(hora,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
        }
        return tabla;
    }
}
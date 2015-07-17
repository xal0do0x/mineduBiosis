/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import pruebareportes.ReporteUtil;
import vistas.reportes.beans.ReporteAsistenciaBean;
import vistas.reportes.beans.RptAsistenciaBean;
import vistas.reportes.controladores.ProcAsistenciaControlador;
import vistas.reportes.controladores.ReporteAsistenciaControlador;

/**
 *
 * @author Aldo
 */
public class rptTardanzaTotal {
    
    private final RegistroAsistenciaControlador rg = new RegistroAsistenciaControlador();
    private final EmpleadoControlador em = new EmpleadoControlador();
    private final AsignacionPermisoControlador asp = new AsignacionPermisoControlador();
    private final PermisoControlador pc = new PermisoControlador();
    private final ReporteAsistenciaControlador rac = new ReporteAsistenciaControlador();
    private final ProcAsistenciaControlador pac = new ProcAsistenciaControlador();
    
    public void crearPdf(String nombreFile,List<String> dnis,Date fechaInicio,Date fechaFin, String oficina,String tipo, String usuario, int departamentoId) 
            throws IOException, DocumentException{
        Document documento = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(documento, new FileOutputStream(nombreFile));
        documento.open();
        Image cabecera = Image.getInstance("img/cabecera.png");
        cabecera.setAlignment(1);
        documento.add(cabecera);
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
        PdfPTable tabla = new rptTardanzaTotal().crearTabla(dnis, fechaInicio, fechaFin, departamentoId);
        documento.add(tabla);
        documento.close();
        try {
            File path = new File (nombreFile);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public PdfPTable crearTabla(List<String> dnis,Date fechaInicio,Date fechaFin, int departamentoId) throws DocumentException{
        /**
         * Procesamiento para info para generar tablas
         */
        //List<RegistroAsistencia> registros = rg.buscarXEmpleadoXFecha1(dnis, fechaInicio, fechaFin);
        Calendar fechaInicioC = Calendar.getInstance();
        Calendar fechaFinC = Calendar.getInstance();
        fechaInicioC.setTime(fechaInicio);
        fechaFinC.setTime(fechaFin);
        
        // Dias contados desde un inicio y fin fecha
        ArrayList<Integer> listaDias = new ArrayList<>();

        while(fechaInicioC.getTime().compareTo(fechaFinC.getTime())<=0){
            if(!ReporteUtil.isDiaLaboral(fechaInicioC.getTime())){
                fechaInicioC.add(Calendar.DATE, 1);
                continue;
            }
            listaDias.add(fechaInicioC.get(Calendar.DATE));
            fechaInicioC.add(Calendar.DATE, 1);
        }
        System.out.println("Numero de dias a agregar: "+listaDias.size());
        for (Integer listaDia : listaDias) {
            System.out.println("Dia numero: "+listaDia);
        }
        /**
         * Procesamiento para elaborar tabla con datos
         */
        //agrege una columna mas (Descuento sin goce)
        int nroColumnas = listaDias.size()+5;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];
        for (int i = 0; i < nroColumnas; i++) {
            if(i==0){
                widthColumna[i]=6;
            }else if(i==(nroColumnas-1)||i==(nroColumnas-2)||i==(nroColumnas-3)||i==(nroColumnas-4)){
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
        h2.setColspan(nroColumnas-5);
        tabla.addCell(h2);
        
        PdfPCell h3 = new PdfPCell(new Phrase("Minutos Tardanza",fontCabecera));
        h3.setHorizontalAlignment(3);
        h3.setGrayFill(0.7f);
        h3.setColspan(1);
        h3.setRowspan(2);
        tabla.addCell(h3);
       
        //Agregado de columna de descuento sin goce
        PdfPCell h4 = new PdfPCell(new Phrase("Faltas",fontCabecera));
        h4.setHorizontalAlignment(3);
        h4.setGrayFill(0.7f);
        h4.setColspan(1);
        h4.setRowspan(2);
        tabla.addCell(h4);
        
        PdfPCell h5 = new PdfPCell(new Phrase("Licencias S.G",fontCabecera));
        h5.setHorizontalAlignment(3);
        h5.setGrayFill(0.7f);
        h5.setColspan(1);
        h5.setRowspan(2);
        tabla.addCell(h5);
        
        PdfPCell h6 = new PdfPCell(new Phrase("Descuento Total",fontCabecera));
        h6.setHorizontalAlignment(3);
        h6.setGrayFill(0.7f);
        h6.setColspan(1);
        h6.setRowspan(2);
        tabla.addCell(h6);
        
        PdfPCell diaColumna = new PdfPCell();
        for (int i = 0; i < (nroColumnas-5); i++) {
            String celda = (listaDias.get(i).toString());
            diaColumna.setPhrase(new Phrase(celda,fontCabecera));
            diaColumna.setHorizontalAlignment(1);
            tabla.addCell(diaColumna);
        }
        tabla.setHeaderRows(2);
        Calendar cal = Calendar.getInstance();
        //List<ReporteAsistenciaBean> listaAsistencia = rac.analisisAsistencia(fechaInicio, fechaFin, dnis, false, false);
        List<RptAsistenciaBean> listaAsistenciaPA = pac.analisisDescuento(departamentoId, fechaInicio, fechaFin);
        List<Integer> conteoDias = new ArrayList<>();
        int minutosTarde = 0;
        int minutosDescuentoPermisos = 0;
        int diasDescuento = 0;
        boolean banderaNombre = true;
        
        PdfPCell celdaNombre = new PdfPCell();
        for(String dni : dnis){
            List<RptAsistenciaBean> registrosDni = new ArrayList<>();
            for(RptAsistenciaBean registro : listaAsistenciaPA){
                if(dni.equals(registro.getDni())){
                    registrosDni.add(registro);                   
                }
            }
            
            for(RptAsistenciaBean registro : registrosDni){
                if(banderaNombre){
                    if(dni.equals(registro.getDni())){
                        celdaNombre.setPhrase(new Phrase(registro.getNombre(),fontCelda));
                        celdaNombre.setHorizontalAlignment(0);
                        tabla.addCell(celdaNombre);
                        banderaNombre = false;
                    }
                }    
                for(Integer dia : listaDias){
                    cal.setTime(registro.getFechaRegistro());
                    if(cal.get(Calendar.DAY_OF_MONTH)==dia){
                            if(!registro.getEstado().equals("FALTA") || registro.getEstado() == null){
                                if(registro.getMinTardanza()!=null){
                                    int numero = registro.getMinTardanza();
                                    String celda = ""+numero;
                                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                    celdaNombre.setHorizontalAlignment(1);                             
                                    tabla.addCell(celdaNombre);                       
                                    minutosTarde += registro.getMinTardanza();
                                }
                            }else if(registro.getEstado().equals("FALTA")){
                                if(registro.getMinTardanza()!=null){
                                    int numero = registro.getMinTardanza();
                                    String celda = ""+numero;
                                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                    celdaNombre.setHorizontalAlignment(1);                             
                                    tabla.addCell(celdaNombre);                       
                                    diasDescuento += 1;
                                }
                            }
                            
                            conteoDias.add(dia);
                            break;
                        }else{
                            if(conteoDias.contains(dia)){

                            }else{
                                String celda = "n.r";
                                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                celdaNombre.setHorizontalAlignment(1);
                                tabla.addCell(celdaNombre);
                                conteoDias.add(dia);
                            }                       
                        }
                }
            }
           
            //Minutos tarde
            String hora1 = "";
            if(minutosTarde <60){
                hora1 = minutosTarde+" min";
            }
            else if(minutosTarde>=60 && minutosTarde<480){
                int resto = minutosTarde%60;
                int totalH =(minutosTarde-resto)/60;
                hora1 = totalH+" h " + resto + " min";                
            }else if(minutosTarde>=480){
                int restoHoras = minutosTarde%480;
                int totalD = (minutosTarde-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                hora1 = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(hora1,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
            //Dias con falta
            String hora2 = "";
            int minutosT = diasDescuento * 480;
            if(minutosT <60){
                hora2 = minutosT+" min";
            }
            else if(minutosT>=60 && minutosT<480){
                int resto = minutosT%60;
                int totalH =(minutosT-resto)/60;
                hora2 = totalH+" h " + resto + " min";                
            }else if(minutosT>=480){
                int restoHoras = minutosT%480;
                int totalD = (minutosT-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                hora2 = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(hora2,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
            //Licencia sin goce
            minutosDescuentoPermisos = rac.minutosSinGocePorPermisos(dni, fechaInicio, fechaFin);
            
            String hora = "";
            if(minutosDescuentoPermisos <60){
                hora = minutosDescuentoPermisos+" min";
            }
            else if(minutosDescuentoPermisos>=60 && minutosDescuentoPermisos<480){
                int resto = minutosDescuentoPermisos%60;
                int totalH =(minutosDescuentoPermisos-resto)/60;
                hora = totalH+" h " + resto + " min";                
            }else if(minutosDescuentoPermisos>=480){
                int restoHoras = minutosDescuentoPermisos%480;
                int totalD = (minutosDescuentoPermisos-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                hora = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(hora,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
            //Total
            String horaT = "";
            int tiempoTotal = minutosTarde+minutosT+minutosDescuentoPermisos;
            if(tiempoTotal <60){
                horaT = tiempoTotal+" min";
            }
            else if(tiempoTotal>=60 && tiempoTotal<480){
                int resto = tiempoTotal%60;
                int totalH =(tiempoTotal-resto)/60;
                horaT = totalH+" h " + resto + " min";                
            }else if(tiempoTotal>=480){
                int restoHoras = tiempoTotal%480;
                int totalD = (tiempoTotal-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                horaT = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            celdaNombre.setPhrase(new Phrase(horaT,fontCelda));
            celdaNombre.setHorizontalAlignment(1);
            tabla.addCell(celdaNombre);
            
            registrosDni.clear();
            banderaNombre = true;
            minutosTarde = 0;
            minutosDescuentoPermisos = 0;
            minutosT = 0;
            diasDescuento = 0;
        }
        return tabla;
    }
}
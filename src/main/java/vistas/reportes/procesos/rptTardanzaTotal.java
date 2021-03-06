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
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controladores.AsignacionPermisoControlador;
import controladores.EmpleadoControlador;
import controladores.PermisoControlador;
import controladores.RegistroAsistenciaControlador;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pruebareportes.ReporteUtil;
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
        Font font = new Font(Font.HELVETICA,10,Font.BOLD);
        Chunk nombreReporte = new Chunk("REPORTE DE CONSOLIDADO DE TARDANZA",font);
        Chunk labelOficina = new Chunk(nombreGrupoOficina,font);
        Chunk labelMes = new Chunk("MES: ",font);
        Chunk labelUsuario = new Chunk("USUARIO: ",font);
      
        Chunk nombreOficina = new Chunk(oficina,new Font(Font.HELVETICA,10));
        Chunk nombreMes = new Chunk(ReporteUtil.obtenerNombreMes(fechaInicio).toUpperCase(),new Font(Font.HELVETICA,10));
        Chunk nombreUsuario = new Chunk(usuario.toUpperCase(), new Font(Font.HELVETICA,10));
        
        documento.add(new Paragraph(nombreReporte));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelOficina,nombreOficina)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelMes,nombreMes)));
        documento.add(ReporteUtil.darEspaciado(15));
        documento.add(new Paragraph(ReporteUtil.unirChunks(labelUsuario,nombreUsuario)));
        documento.add(ReporteUtil.darEspaciado(20));
        PdfPTable tabla = new rptTardanzaTotal().crearTabla(dnis, fechaInicio, fechaFin, departamentoId,oficina,ReporteUtil.obtenerNombreMes(fechaInicio).toUpperCase(),usuario.toUpperCase());
        documento.add(tabla);
        documento.close();
        try {
            File path = new File (nombreFile);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public PdfPTable crearTabla(List<String> dnis,Date fechaInicio,Date fechaFin, int departamentoId,String nombreOficina,String nombreMes,String nombreUsuario) throws DocumentException, FileNotFoundException, IOException{
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
        ArrayList<Date> listaDiasF = new ArrayList<>();
        while(fechaInicioC.getTime().compareTo(fechaFinC.getTime())<=0){
            if(!ReporteUtil.isDiaLaboral(fechaInicioC.getTime())){
                fechaInicioC.add(Calendar.DATE, 1);
                continue;
            }
            listaDias.add(fechaInicioC.get(Calendar.DATE));
            fechaInicioC.add(Calendar.DATE, 1);
        }
        while(fechaInicioC.getTime().compareTo(fechaFinC.getTime())<=0){
            if(!ReporteUtil.isDiaLaboral(fechaInicioC.getTime())){
                fechaInicioC.add(Calendar.DATE, 1);
                continue;
            }
            
        }
        System.out.println("Numero de dias a agregar: "+listaDias.size());
        for (Integer listaDia : listaDias) {
            System.out.println("Dia numero: "+listaDia);
        }       
        /**
         * Procesamiento para elaborar tabla con datos
         */
        //agrege una columna mas (Descuento sin goce)
        int nroColumnas = listaDias.size()+7;
        PdfPTable tabla = new PdfPTable(nroColumnas);
        tabla.setWidthPercentage(100);
        //Asignamos los tamaños a las columnas 
        //MOdifique para tomar en cuenta la nueva columna de dewscuento
        int[] widthColumna = new int[nroColumnas];
        for (int i = 0; i < nroColumnas; i++) {
            if(i==0){
                widthColumna[i]=1;
            }else if(i==1){
                widthColumna[i]=5;
            }else if(i==(nroColumnas-2)||i==(nroColumnas-3)||i==(nroColumnas-4)||i==(nroColumnas-5)){
                widthColumna[i]=2;
            }else if(i==(nroColumnas-1)){
                widthColumna[i]=3;
            }else{
                widthColumna[i]=1;
            }
        }
        tabla.setWidths(widthColumna);
        //Definimos celdas iniciales
        
        /**
         * Procesamiento excel
         */
        /**
         * Procesamiento adicional para generar un excel
         */
        Workbook wb;
        wb = new XSSFWorkbook();
        Map<String, CellStyle> styles = createStyles(wb);
        
        Sheet sheet = wb.createSheet("Descuentos");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        
        //Fila de titulo del excel
        Row titleRow = sheet.createRow(0);
        Row oficinaRow =sheet.createRow(1);
        Row mesRow = sheet.createRow(2);
        titleRow.setHeightInPoints(45);
        oficinaRow.setHeightInPoints(25);
        mesRow.setHeightInPoints(25);
        Cell titleCell = titleRow.createCell(0);
        Cell oficinaCell = oficinaRow.createCell(0);
        Cell mesCell = mesRow.createCell(0);
        Cell usuarioCell = mesRow.createCell(1);
        
        titleCell.setCellValue("REPORTE DE CONSOLIDADO DE TARDANZA");
        titleCell.setCellStyle(styles.get("title"));
        oficinaCell.setCellValue(nombreOficina);
        oficinaCell.setCellStyle(styles.get("subtitle"));
        mesCell.setCellValue(nombreMes);
        mesCell.setCellStyle(styles.get("subtitle"));
        usuarioCell.setCellValue(nombreUsuario);
        usuarioCell.setCellStyle(styles.get("subtitle"));
      
        
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$I$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$I$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$I$3"));
        /**
         * 
         */
        //header row
        Row headerRow = sheet.createRow(3);
        headerRow.setHeightInPoints(25);
        Cell headerCell;
        
        headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Nro");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Nombre empleado");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Dni");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Codigo Modular");
        headerCell.setCellStyle(styles.get("header"));
      
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Minutos tardanza");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Faltas");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Permiso Personal");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Licencia Sin Goce");
        headerCell.setCellStyle(styles.get("header"));
        
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("Descuento Total");
        headerCell.setCellStyle(styles.get("header"));
        //Terminado cabecera de excel    
        Font fontCabecera = new Font(Font.HELVETICA,8,Font.BOLD);
        Font fontCelda = new Font(Font.HELVETICA,7);
        
        PdfPCell h0 = new PdfPCell(new Phrase("Nro",fontCabecera));
        h0.disableBorderSide(Rectangle.BOTTOM);
        h0.setHorizontalAlignment(3);
        h0.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h0.setGrayFill(0.7f);
        h0.setRowspan(1);
        h0.setColspan(1);
        tabla.addCell(h0);
        
        PdfPCell h1 = new PdfPCell(new Phrase("Nombre del ",fontCabecera));
        h1.disableBorderSide(Rectangle.BOTTOM);
        h1.setHorizontalAlignment(3);
        h1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h1.setGrayFill(0.7f);
        h1.setRowspan(1);
        h1.setColspan(1);
        tabla.addCell(h1);
        
        PdfPCell h2 = new PdfPCell(new Phrase("Días Hábiles",fontCabecera));
        h2.setHorizontalAlignment(1);
        h2.setVerticalAlignment(Element.ALIGN_CENTER);
        h2.setGrayFill(0.7f);
        h2.setColspan(nroColumnas-7);
        tabla.addCell(h2);
        
        PdfPCell h3 = new PdfPCell(new Phrase("Minutos",fontCabecera));
        h3.disableBorderSide(Rectangle.BOTTOM);
        h3.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h3.setHorizontalAlignment(3);
        h3.setGrayFill(0.7f);
        h3.setColspan(1);
        h3.setRowspan(1);
        tabla.addCell(h3);
       
        //Agregado de columna de descuento sin goce
        PdfPCell h4 = new PdfPCell(new Phrase("Faltas",fontCabecera));
        h4.disableBorderSide(Rectangle.BOTTOM);
        h4.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h4.setHorizontalAlignment(3);
        h4.setGrayFill(0.7f);
        h4.setColspan(1);
        h4.setRowspan(1);
        tabla.addCell(h4);
        
        PdfPCell hp = new PdfPCell(new Phrase("Permiso",fontCabecera));
        hp.disableBorderSide(Rectangle.BOTTOM);
        hp.setVerticalAlignment(Element.ALIGN_BOTTOM);
        hp.setHorizontalAlignment(3);
        hp.setGrayFill(0.7f);
        hp.setColspan(1);
        hp.setRowspan(1);
        tabla.addCell(hp);
        
        
        PdfPCell h5 = new PdfPCell(new Phrase("Licencia",fontCabecera));
        h5.disableBorderSide(Rectangle.BOTTOM);
        h5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h5.setHorizontalAlignment(3);
        h5.setGrayFill(0.7f);
        h5.setColspan(1);
        h5.setRowspan(1);
        tabla.addCell(h5);
        
        PdfPCell h6 = new PdfPCell(new Phrase("Descuento",fontCabecera));
        h6.disableBorderSide(Rectangle.BOTTOM);
        h6.setVerticalAlignment(Element.ALIGN_BOTTOM);
        h6.setHorizontalAlignment(3);
        h6.setGrayFill(0.7f);
        h6.setColspan(1);
        h6.setRowspan(1);
        tabla.addCell(h6);
        
        PdfPCell hx = new PdfPCell(new Phrase("",fontCabecera));
        hx.disableBorderSide(Rectangle.TOP);
        hx.setHorizontalAlignment(3);
        hx.setVerticalAlignment(Element.ALIGN_BOTTOM);
        hx.setGrayFill(0.7f);
        hx.setRowspan(1);
        hx.setColspan(1);
        tabla.addCell(hx);
        
        PdfPCell h7 = new PdfPCell(new Phrase("empleado",fontCabecera));
        h7.setHorizontalAlignment(3);
        h7.disableBorderSide(Rectangle.TOP);
        h7.setVerticalAlignment(Element.ALIGN_TOP);
        h7.setGrayFill(0.7f);
        h7.setColspan(1);
        tabla.addCell(h7);
        
        PdfPCell diaColumna = new PdfPCell();
        for (int i = 0; i < (nroColumnas-7); i++) {
            String celda = (listaDias.get(i).toString());
            diaColumna.setPhrase(new Phrase(celda,fontCabecera));
            diaColumna.setHorizontalAlignment(1);
            tabla.addCell(diaColumna);
        }
        //Nuevas celdas
        PdfPCell h8 = new PdfPCell(new Phrase("Tardanza",fontCabecera));
        h8.disableBorderSide(Rectangle.TOP);
        h8.setVerticalAlignment(Element.ALIGN_TOP);
        h8.setHorizontalAlignment(3);
        h8.setGrayFill(0.7f);
        h8.setColspan(1);
        tabla.addCell(h8);
        
        PdfPCell h9 = new PdfPCell(new Phrase("",fontCabecera));
        h9.disableBorderSide(Rectangle.TOP);
        h9.setVerticalAlignment(Element.ALIGN_TOP);
        h9.setHorizontalAlignment(3);
        h9.setGrayFill(0.7f);
        h9.setColspan(1);
        tabla.addCell(h9);
        
        PdfPCell hpx = new PdfPCell(new Phrase("personal",fontCabecera));
        hpx.disableBorderSide(Rectangle.TOP);
        hpx.setVerticalAlignment(Element.ALIGN_TOP);
        hpx.setHorizontalAlignment(3);
        hpx.setGrayFill(0.7f);
        hpx.setColspan(1);
        hpx.setRowspan(1);
        tabla.addCell(hpx);
        
        PdfPCell h10 = new PdfPCell(new Phrase("Sin Goce",fontCabecera));
        h10.disableBorderSide(Rectangle.TOP);
        h10.setVerticalAlignment(Element.ALIGN_TOP);
        h10.setHorizontalAlignment(3);
        h10.setGrayFill(0.7f);
        h10.setColspan(1);
        tabla.addCell(h10);
        
        PdfPCell h11 = new PdfPCell(new Phrase("Total",fontCabecera));
        h11.disableBorderSide(Rectangle.TOP);
        h11.setVerticalAlignment(Element.ALIGN_TOP);
        h11.setHorizontalAlignment(3);
        h11.setGrayFill(0.7f);
        h11.setColspan(1);
        tabla.addCell(h11);
        
        //Poner cabecera
        tabla.setHeaderRows(2);
        rac.analisisOnomasticos(fechaInicio, fechaFin, dnis);
        Calendar cal = Calendar.getInstance();
        int diferenciaRegDia = 0;
        int nOrden = 1;
        int iteradorExcel = 4;
        int contador = 0;
        Row empleadoFila = null;
        //List<ReporteAsistenciaBean> listaAsistencia = rac.analisisAsistencia(fechaInicio, fechaFin, dnis, false, false);
        List<RptAsistenciaBean> listaAsistenciaPA = pac.analisisDescuento(departamentoId, fechaInicio, fechaFin);
        List<Integer> conteoDias = new ArrayList<>();
        int minutosTarde = 0;
        int minutosDescuentoPermisos = 0;
        int minutosDescuentoPermisosPP = 0;
        int diasDescuento = 0;
        boolean banderaNombre = true;
        
        List<String> dnis_filtrados = new ArrayList<>();
        for(RptAsistenciaBean rp : listaAsistenciaPA ){
            if(!dnis_filtrados.contains(rp.getDni())){
                dnis_filtrados.add(rp.getDni());
            }
        }
        PdfPCell celdaNombre = new PdfPCell();
        celdaNombre.setMinimumHeight(15f);
        for(String dni : dnis_filtrados){
            List<RptAsistenciaBean> registrosDni = new ArrayList<>();
            for(RptAsistenciaBean registro : listaAsistenciaPA){
                if(dni.equals(registro.getDni())){
                    registrosDni.add(registro);                   
                }
            }
            for(RptAsistenciaBean registro : registrosDni){
                if(banderaNombre){
                    if(dni.equals(registro.getDni())){
                        //Para Excel
                        System.out.println("Iteracion Nro: "+iteradorExcel);
                        empleadoFila = sheet.createRow(iteradorExcel);
                        headerRow.setHeightInPoints(25);
                        Cell empleadoCell;
                        empleadoCell = empleadoFila.createCell(0);
                        empleadoCell.setCellValue(nOrden);
                        empleadoCell.setCellStyle(styles.get("cell"));
                        
                        empleadoCell = empleadoFila.createCell(1);
                        empleadoCell.setCellValue(registro.getNombre());
                        sheet.autoSizeColumn(1);
                        empleadoCell.setCellStyle(styles.get("name"));
                        
                        empleadoCell = empleadoFila.createCell(2);
                        empleadoCell.setCellValue(registro.getDni());
                        empleadoCell.setCellStyle(styles.get("cell"));
                        
                        empleadoCell = empleadoFila.createCell(3);
                        empleadoCell.setCellValue(em.buscarPorDni(registro.getDni()).getCodigoModular());
                        sheet.autoSizeColumn(3);
                        empleadoCell.setCellStyle(styles.get("cell"));
                        //Nro orden
                        celdaNombre.setPhrase(new Phrase(""+nOrden,fontCelda));
                        celdaNombre.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                        celdaNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //celdaNombre.setHorizontalAlignment(0);
                        tabla.addCell(celdaNombre);
                        iteradorExcel++;
                        nOrden ++;
                        //Nombre
                        celdaNombre.setPhrase(new Phrase(registro.getNombre(),fontCelda));
                        celdaNombre.setHorizontalAlignment(0);
                        tabla.addCell(celdaNombre);
                        banderaNombre = false;
                        
                    }
                }    
                for(Integer dia : listaDias){
                    cal.setTime(registro.getFechaRegistro());
                    if(cal.get(Calendar.DAY_OF_MONTH)==dia){
                            if(!registro.getEstado().equals("FALTA")){
                                if(registro.getEstado().equals("PERMISO")){
                                    if(registro.getMinTardanza()!=null){
                                        int numero = registro.getMinTardanza();
                                        String celda;
                                        if(numero == 0){
                                            celda = "P";
                                        }else{
                                           celda = "P-"+numero;
                                        }                                    
                                        celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                        celdaNombre.setHorizontalAlignment(1);                             
                                        tabla.addCell(celdaNombre);                       
                                        minutosTarde += registro.getMinTardanza();
                                        contador++;
                                    }
                                }
                                else if(registro.getEstado().equals("LICENCIA")){
                                    if(registro.getMinTardanza()!=null){
                                        int numero = registro.getMinTardanza();
                                        String celda;
                                        if(numero == 0){
                                            celda = "L";
                                        }else{
                                           celda = "L-"+numero;
                                        }                                    
                                        celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                        celdaNombre.setHorizontalAlignment(1);                             
                                        tabla.addCell(celdaNombre);                       
                                        minutosTarde += registro.getMinTardanza();
                                        contador++;
                                    }
                                }else{
                                    if(registro.getMinTardanza()!=null){
                                    int numero = registro.getMinTardanza();
                                    String celda;
                                    if(numero == 0){
                                        celda = "";
                                    }else{
                                       celda = ""+numero;
                                    }                                    
                                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                    celdaNombre.setHorizontalAlignment(1);                             
                                    tabla.addCell(celdaNombre);                       
                                    minutosTarde += registro.getMinTardanza();
                                    contador++;
                                }
                                
                                }
                                
                            }else if(registro.getEstado().equals("FALTA")){
                                if(registro.getMinTardanza()!=null){
                                    int numero = registro.getMinTardanza();
                                    String celda;
                                    if(numero == 0){
                                        celda = "";
                                    }else{
                                       celda = "F";
                                    } 
                                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                    celdaNombre.setHorizontalAlignment(1);                             
                                    tabla.addCell(celdaNombre);                       
                                    diasDescuento += 1;
                                    contador++;
                                }
                            }
                            
                            conteoDias.add(dia);
                            break;
                        }else{
                            if(conteoDias.contains(dia)){

                            }else{
                                String celda = "--";
                                celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                                celdaNombre.setHorizontalAlignment(1);
                                tabla.addCell(celdaNombre);
                                conteoDias.add(dia);
                                contador++;
                            }                       
                        }
                }
            }
            diferenciaRegDia = listaDias.size() - contador;
            if(diferenciaRegDia > 0){
                for (int i = 0; i < diferenciaRegDia; i++) {
                    String celda = "--";
                    celdaNombre.setPhrase(new Phrase(celda,fontCelda));
                    celdaNombre.setHorizontalAlignment(1);
                    tabla.addCell(celdaNombre);
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
            if(minutosTarde == 0){
                hora1 = "";
            }
            celdaNombre.setPhrase(new Phrase(hora1,fontCelda));
            celdaNombre.setHorizontalAlignment(0);
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
                hora2 = totalD+" días ";
            }
            if(minutosT == 0){
                hora2 = "";
            }
            celdaNombre.setPhrase(new Phrase(hora2,fontCelda));
            celdaNombre.setHorizontalAlignment(0);
            tabla.addCell(celdaNombre);
            //Permisos personales
            minutosDescuentoPermisosPP = rac.minutosSinGocePorPermisos(dni, fechaInicio, fechaFin);
            
            String horaPP = "";
            if(minutosDescuentoPermisosPP <60){
                horaPP = minutosDescuentoPermisosPP+" min";
            }
            else if(minutosDescuentoPermisosPP>=60 && minutosDescuentoPermisosPP<480){
                int resto = minutosDescuentoPermisosPP%60;
                int totalH =(minutosDescuentoPermisosPP-resto)/60;
                horaPP = totalH+" h " + resto + " min";                
            }else if(minutosDescuentoPermisosPP>=480){
                int restoHoras = minutosDescuentoPermisosPP%480;
                int totalD = (minutosDescuentoPermisosPP-restoHoras)/480;
                int restoMinutos = restoHoras%60;
                int totalH = (restoHoras-restoMinutos)/60;
                horaPP = totalD+" d "+ totalH + " h " + restoMinutos + " m";
            }
            if(minutosDescuentoPermisosPP == 0){
                horaPP = "";
            }
            celdaNombre.setPhrase(new Phrase(horaPP,fontCelda));
            celdaNombre.setHorizontalAlignment(0);
            tabla.addCell(celdaNombre);
            //Licencia sin goce
            minutosDescuentoPermisos = rac.minutosSinGocePorLicencias(dni, fechaInicio, fechaFin);
            
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
            if(minutosDescuentoPermisos == 0){
                hora = "";
            }
            celdaNombre.setPhrase(new Phrase(hora,fontCelda));
            celdaNombre.setHorizontalAlignment(0);
            tabla.addCell(celdaNombre);
            //Total
            String horaT = "";
            int tiempoTotal = minutosTarde+minutosT+minutosDescuentoPermisos+minutosDescuentoPermisosPP;
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
                
                if(totalH==0 && restoMinutos==0){
                    horaT = totalD+ " d";
                }else if(totalH!=0 && restoMinutos==0){
                    horaT = totalD+" d "+ totalH + " h ";
                }else if(restoMinutos!=0 && totalH==0){
                    horaT = totalD+" d "+ restoMinutos + " m";
                }else{
                    horaT = totalD+" d "+ totalH + " h " + restoMinutos + " m";
                }
            }
            if(tiempoTotal == 0){
                horaT = "";
            }
            celdaNombre.setPhrase(new Phrase(horaT,fontCelda));
            celdaNombre.setHorizontalAlignment(0);
            tabla.addCell(celdaNombre);
            //Celda
            Cell empleadoCell;

            empleadoCell = empleadoFila.createCell(4);          
            empleadoCell.setCellValue(this.minutosFormateados(minutosTarde));
            empleadoCell.setCellStyle(styles.get("cell"));
            
            empleadoCell = empleadoFila.createCell(5);
            empleadoCell.setCellValue(this.minutosFormateados(minutosT));
            empleadoCell.setCellStyle(styles.get("cell"));
            
            empleadoCell = empleadoFila.createCell(6);
            empleadoCell.setCellValue(this.minutosFormateados(minutosDescuentoPermisosPP));
            empleadoCell.setCellStyle(styles.get("cell"));
            
            empleadoCell = empleadoFila.createCell(7);
            empleadoCell.setCellValue(this.minutosFormateados(minutosDescuentoPermisos));
            empleadoCell.setCellStyle(styles.get("cell"));
            
            int sumaMinutos = minutosTarde+minutosT+minutosDescuentoPermisos+minutosDescuentoPermisosPP;
            empleadoCell = empleadoFila.createCell(8);
            empleadoCell.setCellValue(this.minutosFormateados(sumaMinutos));
            empleadoCell.setCellStyle(styles.get("cell"));
            
            registrosDni.clear();
            banderaNombre = true;
            contador = 0;
            minutosTarde = 0;
            minutosDescuentoPermisos = 0;
            minutosDescuentoPermisosPP = 0;
            minutosT = 0;
            diasDescuento = 0;
            conteoDias.clear();
        }
        // Write the output to a file
        String file = "reporteDescuento"+nombreOficina.split(" ",0)[0]+"_"+nombreMes+".xls";
        if(wb instanceof XSSFWorkbook) file += "x";
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        try {
            File path = new File (file);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return tabla;
        
        
    }
    
     /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        org.apache.poi.ss.usermodel.Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setFont(titleFont);
        styles.put("title", style);

        org.apache.poi.ss.usermodel.Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);
        
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("name", style);
        
        org.apache.poi.ss.usermodel.Font subtitleFont = wb.createFont();
        subtitleFont.setFontHeightInPoints((short)11);
        subtitleFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_NORMAL);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(subtitleFont);
        styles.put("subtitle", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }

    public String minutosFormateados(int minutos){
        int horas=0;
        int minutosF = 0;
        horas = minutos/60;
        minutosF = minutos%60;
        
        String tiempoFormateado = horas+"."+minutosF;
        return tiempoFormateado;
    }
}
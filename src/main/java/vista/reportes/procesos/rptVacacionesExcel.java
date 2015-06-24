/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.reportes.procesos;

import controladores.DepartamentoControlador;
import controladores.EmpleadoControlador;
import controladores.VacacionControlador;
import entidades.Empleado;
import entidades.Vacacion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Aldo
 */
public class rptVacacionesExcel {
    private final EmpleadoControlador ec = new EmpleadoControlador();
    private final VacacionControlador vc = new VacacionControlador();
    private final DepartamentoControlador dc = new DepartamentoControlador();
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static final String[] titles = {
    "DNI", "NOMBRE", "DEPARTAMENTO", "VACACION "};

    public void crearExcel(List<Empleado> empleados, Date fechaInicio, Date fechaFin) throws FileNotFoundException, IOException{
        Workbook wb;
        wb = new XSSFWorkbook();
        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet("Vacaciones");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);  
        sheet.setHorizontallyCenter(true);
        
        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        titleCell.setCellValue("VACACIONES MINEDU "+formateador.format(fechaInicio)+" - "+formateador.format(fechaFin));
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
        
        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;
        for(int i = 0;i<titles.length; i++){
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(titles[i]);
            headerCell.setCellStyle(styles.get("header"));
        }
        
        int rownum = 2;
        for (int i = 0; i < empleados.size(); i++) {
            Row row = sheet.createRow(rownum++);
            for (int j = 0; j < titles.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get("cell"));
                
            }
        }
        for(int i = 0;i<empleados.size(); i++){
            Row row = sheet.getRow(2+i);
            row.getCell(0).setCellValue(empleados.get(i).getNroDocumento());
            row.getCell(1).setCellValue(empleados.get(i).getNombre()+" "+empleados.get(i).getApellidoPaterno());
                    row.getCell(2).setCellValue("Departamento");
            Vacacion vacaciones = vc.buscarXDia(empleados.get(i).getNroDocumento(), fechaInicio);
            if(vacaciones!=null){
               row.getCell(3).setCellValue(formateador.format(vacaciones.getFechaInicio())+" al "+formateador.format(vacaciones.getFechaFin()));
            }else{
                row.getCell(3).setCellValue("No tiene vacaciones");
            }
         
        }
        
        sheet.setColumnWidth(0, 10*256);
        sheet.setColumnWidth(1, 30*256);
        sheet.setColumnWidth(2, 30*256);
        sheet.setColumnWidth(3, 25*256);
        //Write the output to a file
        String file = "rptVacaciones.xls";
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
    }

    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
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
}

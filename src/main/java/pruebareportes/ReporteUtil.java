/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebareportes;

import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import entidades.Horario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Aldo
 */
public class ReporteUtil {
    
    public static final int CENTRO = 1;
    public static Paragraph darEspaciado(int espacio){
        Paragraph parrafoEspacio = new Paragraph();
        parrafoEspacio.setSpacingAfter(espacio);
        return parrafoEspacio;        
    }
    public static String obtenerNombreMes(Date fecha){
        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es","ES"));
        String mes = formateador.format(fecha);
        return mes;
    }
    
    public static String obtenerFechaFormateada(Date fecha, String separador){
        SimpleDateFormat formateador = new SimpleDateFormat("dd"+separador+"MM"+separador+"yyyy");
        String fechaFormateada = formateador.format(fecha);
        return fechaFormateada;
    }
    
    public static String obtenerFechaDiaMes(Date fecha){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM");
        String fechaFormateada = formateador.format(fecha);
        return fechaFormateada;
    }
    
    public static Phrase unirChunks(Chunk... chunks){
        Phrase fraseUnida = new Phrase(chunks.length);
        for (int i = 0; i < chunks.length; i++) {
            fraseUnida.add(chunks[i]);
        }
        return fraseUnida;
    }
    
    public static boolean isDiaLaboral(Date fInicio) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fInicio);
        int nDia = cal.get(Calendar.DAY_OF_WEEK);
        //LOS DIAS VAN DESDE EL 1 AL 7 Y CUENTAN DESDE DOMINGO A SABADO
        switch (nDia) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            default:
                return false;
        }
    }
}

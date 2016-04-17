/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import controladores.ReaderControlador;
import entidades.EventLog;
import entidades.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author Aldo
 */
public class MTEventLog extends ModeloTabla<EventLog>{
    
    private ReaderControlador rc = new ReaderControlador();
    
    public MTEventLog(List<EventLog> datos){
        super(datos);
        this.nombreColumnas = new String[]{"Fecha y hora","Equipo"};
    }
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        EventLog evento = this.datos.get(rowIndex); 
        switch(columnIndex){
            case 0:
                Long l = (long) evento.getnDateTime();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return formato.format(new Date((l*1000)+18000000));
            case 1:
                Reader dispositivo = rc.buscarReaderXID(evento.getnReaderIdn()).get(0);
                return dispositivo.getSName();
                default:
                    return null;
        }
    }
}

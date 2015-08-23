/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import controladores.EmpleadoControlador;
import entidades.DetalleCondicion;
import entidades.Empleado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author OGEPER02
 */
public class MTDetalleCondicion extends ModeloTabla<DetalleCondicion> {
    private final DateFormat dfFecha;
    private final EmpleadoControlador ec = new EmpleadoControlador();
    
    public MTDetalleCondicion(List<DetalleCondicion> datos){
        super(datos);
        this.nombreColumnas = new String[]{"Nombre","Condicion","Fecha Inicio","Fecha Fin"};
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex){
        DetalleCondicion detalle = this.datos.get(rowIndex);
        Empleado e = ec.buscarPorDni(detalle.getEmpleadoNroDocumento());
        switch(columnIndex){
            case 0:
                return detalle.getEmpleadoNroDocumento()+" "+e.getApellidoPaterno()+" "+e.getApellidoMaterno()+" "+e.getNombre();
            case 1:
                return detalle.getCondicionId().getNombre();
            case 2:
                return dfFecha.format(detalle.getFechaInicio());
            case 3:
                if (detalle.getFechaFin() != null){
                    return dfFecha.format(detalle.getFechaFin());
                }else{
                    return "";
                }
            default:
                return null;
        }
    }
}

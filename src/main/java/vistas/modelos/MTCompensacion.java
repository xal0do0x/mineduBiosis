/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import controladores.EmpleadoControlador;
import entidades.CompensacionEspecial;
import entidades.Empleado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Aldo
 */
public class MTCompensacion extends ModeloTabla<CompensacionEspecial>{
    private final DateFormat dfFecha;
    private final EmpleadoControlador ec = new EmpleadoControlador();
    
    public MTCompensacion(List<CompensacionEspecial> datos){
        super(datos);
        this.nombreColumnas = new String[]{"Nombre","Fecha Inicio","Fecha Fin","Documento"};
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex){
        CompensacionEspecial compensacion = this.datos.get(rowIndex);
        Empleado e = ec.buscarPorDni(compensacion.getDni());
        switch(columnIndex){
            case 0:
                return compensacion.getDni()+" "+e.getApellidoPaterno()+" "+e.getApellidoMaterno()+" "+e.getNombre();
            case 1:
                return dfFecha.format(compensacion.getFechaInicio());
            case 2:
                if (compensacion.getFechaFin() != null){
                    return dfFecha.format(compensacion.getFechaFin());
                }else{
                    return "";
                }
            case 3:
                return compensacion.getDocumento();
           default:
               return null;
                
        }
    }
}

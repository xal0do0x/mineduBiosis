/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import entidades.EmpleadoT;
import java.util.List;

/**
 *
 * @author OGEPER02
 */
public class MTEmpleadoT extends ModeloTabla<EmpleadoT>{
    public MTEmpleadoT(List<EmpleadoT> datos){
        super(datos);
        this.nombreColumnas = new String[]{"Nro documento","Nombres y apellidos"};
    }
    
    public MTEmpleadoT(List<EmpleadoT> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        EmpleadoT empleado = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return empleado.getNroDocumento();
            case 1:
                return empleado.getApellidoPaterno()
                        + " "
                        + empleado.getApellidoMaterno()
                        + " "
                        + empleado.getNombre();
                default:
                    return null;
        }
    }
    
}

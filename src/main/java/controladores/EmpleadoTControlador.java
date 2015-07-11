/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOMINEDU;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.EmpleadoT;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OGEPER02
 */
public class EmpleadoTControlador extends Controlador<EmpleadoT> {
     public EmpleadoTControlador() {
        super(EmpleadoT.class, new DAOMINEDU(EmpleadoT.class));
    }
    
    public List<EmpleadoT> buscarXDNI() {
        String jpql = "SELECT e FROM EmpleadoT e";
        
        return this.getDao().buscar(jpql);
    }
    
    public List<EmpleadoT> buscarPorListaEnteros(List<Integer> lista){
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "CAST(e.nroDocumento AS integer) IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public EmpleadoT buscarPorDni(String dni){
        String jpql = "SELECT e FROM EmpleadoT e WHERE e.nroDocumento = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", dni);

        List<EmpleadoT> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
    public List<EmpleadoT> buscarPorLista(List<String> lista) {
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "e.nroDocumento IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOMINEDU;
import entidades.Contrato;
import entidades.Empleado;
import entidades.EmpleadoT;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OGEPER02
 */
public class ContratoControlador extends Controlador<Contrato>{
    
    public ContratoControlador() {
        super(Contrato.class, new DAOMINEDU(Contrato.class));
    }
    
    public List<Contrato> buscarXDNI(String dni) {
        String jpql = "SELECT c FROM Contrato c WHERE c.nroDocumento=:dni";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        return this.getDao().buscar(jpql, mapa);
    }
    
//    public List<Contrato> buscarContratoFechaXDNI(String dni, Date fecha){
//        
//    }
    
    public Contrato buscarContratoXFechaXDni(String dni, Date fecha){
        String jpql = "SELECT c FROM Contrato c WHERE c.nroDocumento = :dni AND :fecha BETWEEN c.fecInicio AND c.fecFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha",fecha);

        List<Contrato> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
}

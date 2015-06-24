/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOBIOSTAR;
import entidades.Departamento;
import entidades.Marcacion;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class DepartamentoControlador extends Controlador<Departamento> {

    public DepartamentoControlador() {
        super(Departamento.class, new DAOBIOSTAR(Departamento.class));
    }

    public List<Departamento> buscarXNombre(String patron){
        String jpql = "SELECT d FROM Departamento d WHERE UPPER(d.nombre) LIKE CONCAT('%',UPPER(:patron),'%')";
        Map<String,Object> param = new HashMap<>();
        param.put("patron", patron);
        
        return this.getDao().buscar(jpql, param);
    }

    public Departamento buscarXDni(String dni){
        String jpql="SELECT d FROM Departamento d, EmpleadoBiostar e WHERE e.id = :dni AND e.departamento = d.id";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        List<Departamento> departamentos = this.getDao().buscar(jpql, mapa, -1, 1);
        if (departamentos.isEmpty()) {
            return null;
        } else {
            return departamentos.get(0);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOBIOSTAR;
import entidades.EmpleadoOpcionInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aldo
 */
public class EmpleadoOpcionInfoControlador extends Controlador<EmpleadoOpcionInfo>{

    public EmpleadoOpcionInfoControlador() {   
        super(EmpleadoOpcionInfo.class, new DAOBIOSTAR(EmpleadoOpcionInfo.class));
    }
    
    public List<EmpleadoOpcionInfo> buscarTodos(Integer dni){
        String jpql = "SELECT e FROM EmpleadoOpcionInfo e,EmpleadoBiostar eb WHERE eb.identificador=e.nUserIdn AND eb.id=:dni";
        Map<String,Object> param = new HashMap<>();
        param.put("dni", dni);
        return this.getDao().buscar(jpql,param);
    } 
}

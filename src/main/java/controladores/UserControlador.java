/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOBIOSTAR;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entidades.User;

/**
 *
 * @author Aldo
 */
public class UserControlador extends Controlador<User> {
    public UserControlador(){
        super(User.class, new DAOBIOSTAR(User.class));
    }
    
    public User buscarPorId(String dni){
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT u FROM User u WHERE CAST(u.sUserID AS INTEGER) = :id ";
        Map<String, Object> mapa = new HashMap();
        mapa.put("id", dniT);
        
        List<User> users = this.getDao().buscar(jpql, mapa);
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }        
    }
}

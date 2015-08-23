/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Condicion;
import java.util.List;

/**
 *
 * @author Aldo
 */
public class CondicionControlador extends Controlador<Condicion> {
    public CondicionControlador(){
        super(Condicion.class);
    }
    
    public List<Condicion> buscarTodos(){
        String jpql = "SELECT c FROM Condicion c ORDER BY c.nombre";
        return this.getDao().buscar(jpql);
    }
}

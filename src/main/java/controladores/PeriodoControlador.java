/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Periodo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class PeriodoControlador extends Controlador<Periodo>{

    public PeriodoControlador() {
        super(Periodo.class);
    }

    public List<Periodo> buscarTodosOrden() {
        String jpql = "SELECT p FROM Periodo p ORDER BY p.anio DESC";
        return this.getDao().buscar(jpql);
    }
    
    public Periodo buscarPorAnio(int anio){
        String jpql = "SELECT p FROM Periodo p WHERE p.anio = :anio";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("anio",anio);
        
        List<Periodo> periodos = this.getDao().buscar(jpql, mapa);
        if (periodos.isEmpty()) {
            return null;
        } else {
            System.out.println("anio: "+periodos.get(0).toString());
            return periodos.get(0);
        }
    }
    
}

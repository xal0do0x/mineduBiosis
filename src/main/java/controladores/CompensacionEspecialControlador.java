/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.CompensacionEspecial;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aldo
 */
public class CompensacionEspecialControlador extends Controlador<CompensacionEspecial>{
    
    public CompensacionEspecialControlador(){
        super(CompensacionEspecial.class);
    }
    
    public List<CompensacionEspecial> buscarXEmpleadoXFecha(String dni,Date fechaInicio,Date fechaFin, int desde, int tamanio){
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT c FROM CompensacionEspecial c WHERE CAST(c.empleadoNroDocumento AS int) = :dni AND "
                + "c.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
                + "c.empleadoNroDocumento = :dni  AND d.fechaInicio >= :fechaInicio AND c.fechaFin IS NULL)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni",dniT);
        mapa.put("fechaInicio",fechaInicio);
        mapa.put("fechaFin",fechaFin);
        return this.getDao().buscar(jpql,mapa,desde,tamanio);
    }
    
    public List<CompensacionEspecial> buscarXFecha(Date fechaInicio,Date fechaFin, int desde, int tamanio){
        String jpql = "SELECT c FROM CompensacionEspecial c WHERE (c.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
                + "( c.fechaInicio >= :fechaInicio AND c.fechaFin IS NULL)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio",fechaInicio);
        mapa.put("fechaFin",fechaFin);
        return this.getDao().buscar(jpql,mapa,desde,tamanio);
    }       
     
    public List<CompensacionEspecial> buscarXFechaDni(String dni,Date fechaInicio){
        int dniT = Integer.parseInt(dni);
        String jpl = "SELECT c FROM CompensacionEspecial c WHERE CAST(c.empleadoNroDocumento as int)  = :dni  AND :fechaInicio BETWEEN c.fechaInicio and c.fechaFin) OR"
                + "c.empleadoNroDocumento  = :dni AND :fechaInicio >= c.fechaInicio and c.fechaFin IS NULL) ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dniT);
        mapa.put("fechaInicio", fechaInicio);
        return this.getDao().buscar(jpl, mapa);
    }
    
     public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(c.id) FROM CompensacionEspecial c WHERE (c.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (c.fechaInicio >= :fechaInicio AND c.fechaFin IS NULL)" ;
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        System.out.println("CONTEO"+conteo);
        return conteo;
    }
    
     public int contarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT COUNT(c.id) FROM CompensacionEspecial c WHERE (CAST(c.empleadoNroDocumento as int) = :dni AND c.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (CAST(c.empleadoNroDocumento as int) = :dni AND c.fechaInicio >= :fechaInicio AND c.fechaFin IS NULL)";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", dniT)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    } 
}

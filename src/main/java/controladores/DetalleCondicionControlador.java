/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.AsignacionPermiso;
import entidades.DetalleCondicion;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OGEPER02
 */
public class DetalleCondicionControlador extends Controlador<DetalleCondicion> {
    public DetalleCondicionControlador(){
        super(DetalleCondicion.class);
    }
    
    public List<DetalleCondicion> buscarXEmpleadoXFecha(String dni,Date fechaInicio,Date fechaFin, int desde, int tamanio){
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT d FROM DetalleCondicion d WHERE CAST(d.empleadoNroDocumento AS int) = :dni AND "
                + "d.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
                + "d.empleadoNroDocumento = :dni  AND d.fechaInicio >= :fechaInicio AND d.fechaFin IS NULL)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni",dniT);
        mapa.put("fechaInicio",fechaInicio);
        mapa.put("fechaFin",fechaFin);
        return this.getDao().buscar(jpql,mapa,desde,tamanio);
    }
    
    public List<DetalleCondicion> buscarXFecha(Date fechaInicio,Date fechaFin, int desde, int tamanio){
        String jpql = "SELECT d FROM DetalleCondicion d WHERE (d.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
                + "( d.fechaInicio >= :fechaInicio AND d.fechaFin IS NULL)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio",fechaInicio);
        mapa.put("fechaFin",fechaFin);
        return this.getDao().buscar(jpql,mapa,desde,tamanio);
    }       
     
    public List<DetalleCondicion> buscarXFechaDni(String dni,Date fechaInicio){
        int dniT = Integer.parseInt(dni);
        String jpl = "SELECT d FROM DetalleCondicion d WHERE CAST(d.empleadoNroDocumento as int)  = :dni  AND :fechaInicio BETWEEN d.fechaInicio and d.fechaFin) OR"
                + "d.empleadoNroDocumento  = :dni AND :fechaInicio >= d.fechaInicio and d.fechaFin IS NULL) ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dniT);
        mapa.put("fechaInicio", fechaInicio);
        return this.getDao().buscar(jpl, mapa);
    }
    
     public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(d.id) FROM DetalleCondicion d WHERE (d.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (d.fechaInicio >= :fechaInicio AND d.fechaFin IS NULL)" ;
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        System.out.println("CONTEO"+conteo);
        return conteo;
    }
     
    public int contarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT COUNT(d.id) FROM DetalleCondicion d WHERE (CAST(d.empleadoNroDocumento as int) = :dni AND d.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (CAST(d.empleadoNroDocumento as int) = :dni AND d.fechaInicio >= :fechaInicio AND d.fechaFin IS NULL)";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", dniT)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    } 
}

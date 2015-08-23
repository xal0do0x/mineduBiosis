/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.AsignacionHorario;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.GrupoHorario;
import entidades.Horario;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author fesquivelc
 */
public class AsignacionHorarioControlador extends Controlador<AsignacionHorario> {

    private final DetalleGrupoControlador dgc;
    public AsignacionHorarioControlador() {
        super(AsignacionHorario.class);
        dgc = new DetalleGrupoControlador();
    }
    
    public List<AsignacionHorario> buscarXGrupos(List<GrupoHorario> grupos){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.grupoHorario in :grupos";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupos", grupos);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXGrupo(GrupoHorario grupo){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.grupoHorario = :grupo";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupo", grupo);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleado(Empleado empleado){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :empleado";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleado", empleado.getNroDocumento());
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleado(List<String> dnis ,Horario horario){
        List<DetalleGrupoHorario> detalleGrupo = dgc.buscarXEmpleados(dnis);
        List<GrupoHorario> grupos = new ArrayList<>();
        
        for(DetalleGrupoHorario detalle : detalleGrupo){
            grupos.add(detalle.getGrupoHorario());
        }
        
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "(a.empleado IN :empleados OR a.grupo) AND a.horario = :horario";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleados", dnis);
        mapa.put("horario", horario);
        return this.getDao().buscar(jpql, mapa);
    }

    public AsignacionHorario buscarXEmpleadoXFechaFinNull(String dni, Date fecha){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :dni  "
                + "AND :fecha <= a.fechaInicio "
                + "AND a.fechaFin IS NULL "
                + "ORDER BY a.fechaInicio DESC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fecha);
        
        List<AsignacionHorario> asig = this.getDao().buscar(jpql, mapa, -1, 1);
        if (asig.isEmpty()) {
            return null;
        } else {
            return asig.get(0);
        }
    }
    
    public AsignacionHorario buscarXEmpleadoXFechaFinNull2(String dni, Date fecha){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :dni  "
                + "AND :fecha > a.fechaInicio "
                + "AND a.fechaFin IS NULL "
                + "ORDER BY a.fechaInicio DESC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fecha);
        
        List<AsignacionHorario> asig = this.getDao().buscar(jpql, mapa, -1, 1);
        if (asig.isEmpty()) {
            return null;
        } else {
            return asig.get(0);
        }
    }
    
    public List<AsignacionHorario> buscarXEmpleadosXFecha(String dni, Date fecha){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :dni  "
                + "AND :fecha BETWEEN a.fechaInicio AND a.fechaFin "
                + "ORDER BY a.fechaFin DESC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fecha);
        
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleadosXAll(String dni, Date fecha){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                +"a.empleado = :dni AND "
                +":fecha BETWEEN a.fechaInicio AND a.fechaFin OR "
                +"a.fechaInicio <= :fecha AND a.fechaFin IS NULL AND "
                +"a.empleado = :dni";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fecha);
        return this.getDao().buscar(jpql, mapa);
    }
    
     public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a.id) FROM AsignacionHorario a WHERE a.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        System.out.println("CONTEO"+conteo);
        return conteo;
    }
     
    public int contarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a.id) FROM AsignacionHorario a WHERE a.empleado = :dni AND a.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", dni)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }
    
    public List<AsignacionHorario> buscarXFecha(Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT a FROM AsignacionHorario a WHERE a.fechaInicio BETWEEN :fechaInicio AND :fechaFin ORDER BY a.fechaInicio";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
    
     public List<AsignacionHorario> buscarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT a FROM AsignacionHorario a WHERE a.empleado = :dni AND a.fechaInicio BETWEEN :fechaInicio AND :fechaFin ORDER BY a.fechaInicio";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
}

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
import java.util.ArrayList;
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
    
     
    public List<EmpleadoT> buscarXPatron(String patron) {
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) "
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa);
    }
     
    public List<EmpleadoT> buscarXPatron(String patron, int desde, int tamanio) {
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) OR e.codigoModular = UPPER(:patron)"
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
    
    public int totalXPatron(String patron){
        String jpql = "SELECT COUNT(e.nroDocumento) FROM EmpleadoT e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron)  OR e.codigoModular = UPPER(:patron)";
        Long cont = (Long)this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("patron", patron).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
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
        int dniT = Integer.parseInt(dni);
        String jpql = "SELECT e FROM EmpleadoT e WHERE CAST(e.nroDocumento as int) = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", dniT);

        List<EmpleadoT> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
    public List<EmpleadoT> buscarPorLista(List<String> lista) {
        List<Integer> listaDniE = new ArrayList<>();
        for(String dni : lista){
            listaDniE.add(Integer.parseInt(dni));
        }
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "CAST(e.nroDocumento as int) IN :lista ORDER BY e.apellidoPaterno,e.apellidoMaterno";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", listaDniE);
        return this.getDao().buscar(jpql, mapa);
    }
    
     public List<EmpleadoT> buscarPorListaInt(List<Integer> lista) {
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + " CAST(e.nroDocumento AS INT) IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }

     public EmpleadoT buscarPorId(int id) {
        String jpql = "SELECT e FROM EmpleadoT e WHERE "
                + "CAST(e.nroDocumento AS integer) = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", id);

        List<EmpleadoT> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
     
    public int buscarXTodos(){
        String jpql = "SELECT COUNT(e.nroDocumento) FROM EmpleadoT e";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    } 
    
    public List<EmpleadoT> buscarTodosX(int cantidad){
        String jpql = "SELECT e FROM EmpleadoT e ";
        return this.getDao().getEntityManager().createQuery(jpql).setMaxResults(cantidad).getResultList();
    }
    
    public List<EmpleadoT> buscarTodosRecursivo(int cantidad, List<String> lista){
        List<Integer> listaDniE = new ArrayList<>();
        for(String dni : lista){
            listaDniE.add(Integer.parseInt(dni));
        }
        String jpql = "SELECT e FROM EmpleadoT e WHERE CAST(e.nroDocumento as int) NOT IN :lista";
        return this.getDao().getEntityManager().createQuery(jpql).setParameter("lista",listaDniE).setMaxResults(cantidad).getResultList();
    }


}

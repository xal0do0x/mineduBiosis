/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOMINEDU;
import entidades.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TemporalType;

/**
 *
 * @author fesquivelc
 */
public class EmpleadoControlador extends Controlador<Empleado> {

    public EmpleadoControlador() {
        super(Empleado.class, new DAOMINEDU(Empleado.class));
    }

    public List<Empleado> buscarXPatron(String patron) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) "
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<Empleado> buscarXPatron(String patron, int desde, int tamanio) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) OR e.codigoModular = UPPER(:patron)"
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
    
    public int totalXPatron(String patron){
        String jpql = "SELECT COUNT(e.nroDocumento) FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,' ',apellidoPaterno,' ',apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron)  OR e.codigoModular = UPPER(:patron)";
        Long cont = (Long)this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("patron", patron).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }

    public List<Empleado> buscarPorLista(List<String> lista) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "e.nroDocumento IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<Empleado> buscarPorListaInt(List<Integer> lista) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + " CAST(e.nroDocumento AS INT) IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<Empleado> buscarPorListaEnteros(List<Integer> lista){
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "CAST(e.nroDocumento AS integer) IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }
 
    public Empleado buscarPorId(int id) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "CAST(e.nroDocumento AS integer) = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", id);

        List<Empleado> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
    
    public Empleado buscarPorDni(String dni){
        String jpql = "SELECT e FROM Empleado e WHERE e.nroDocumento = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", dni);

        List<Empleado> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
    
    public int buscarXTodos(){
        String jpql = "SELECT COUNT(e.nroDocumento) FROM Empleado e";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }
    
    public List<Empleado> buscarTodosX(int cantidad){
        String jpql = "SELECT e FROM Empleado e ";
        return this.getDao().getEntityManager().createQuery(jpql).setMaxResults(cantidad).getResultList();
    }
    
    public List<Empleado> buscarTodosRecursivo(int cantidad, List<String> lista){
        String jpql = "SELECT e FROM Empleado e WHERE e.nroDocumento NOT IN :lista";
        return this.getDao().getEntityManager().createQuery(jpql).setParameter("lista",lista).setMaxResults(cantidad).getResultList();
    }
}

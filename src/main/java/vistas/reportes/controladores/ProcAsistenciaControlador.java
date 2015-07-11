/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.controladores;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author OGEPER02
 */

public class ProcAsistenciaControlador {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @PersistenceContext
    private EntityManager em;
    
    public List<RptAsistenciaBean> analisisAsistencia(int departamento, Date fechaInicio, Date fechaFin){
        try {
            Query q = em.createNamedQuery("{call pa_crear_registro_asistencia(?,?,?)}",RptAsistenciaBean.class).
                                                                        setParameter(1, departamento).
                                                                        setParameter(2, fechaInicio).
                                                                        setParameter(3, fechaFin);
            List<RptAsistenciaBean> registrosAsistencia = q.getResultList();
            return registrosAsistencia;
        } catch (Exception ex) {
            logger.warning("Error"+ex.getMessage());
            return null;
        }finally{
            em.flush();
        }
    }
}

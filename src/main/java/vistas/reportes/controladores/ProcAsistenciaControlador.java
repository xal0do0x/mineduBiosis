/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.controladores;

import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import utiles.Encriptador;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author OGEPER02
 */

public class ProcAsistenciaControlador {
    private static final String PERSISTENCE_UNIT_NAME = "biosis-PU";  
    private static EntityManagerFactory factory;
    
    public List<RptAsistenciaBean> analisisAsistencia(int departamento, Date fechaInicio, Date fechaFin){
        Properties configuracion = PropertiesUtil.cargarProperties("config/biosis-config.properties");
        int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

        String driver = ParametrosUtil.obtenerDriver(tipoBD);
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String password = configuracion.getProperty("password");
        String accion = configuracion.getProperty("action");

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", usuario);
        properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.schema-generation.database.action", accion);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        
        EntityManager em = factory.createEntityManager();
    
        em.getTransaction().begin();
        //StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_asistencia_test",RptAsistenciaBean.class);
        StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_asistencia_test_test",RptAsistenciaBean.class);
        
        sp.registerStoredProcedureParameter("@departamento",Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_inicio", Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_fin", Date.class, ParameterMode.IN);
        
        sp.setParameter("@departamento", departamento);
        sp.setParameter("@fecha_inicio", fechaInicio);
        sp.setParameter("@fecha_fin", fechaFin);
        em.getTransaction().commit();
        System.out.println("Hora de inicio: "+Calendar.getInstance().getTime());
        sp.execute();
        //return sp.getResultList();
        em.close();
        return sp.getResultList();   
    }
    
    public List<RptAsistenciaBean> analisisDescuento(int departamento, Date fechaInicio, Date fechaFin){
        Properties configuracion = PropertiesUtil.cargarProperties("config/biosis-config.properties");
        int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

        String driver = ParametrosUtil.obtenerDriver(tipoBD);
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String password = configuracion.getProperty("password");
        String accion = configuracion.getProperty("action");

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", usuario);
        properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.schema-generation.database.action", accion);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        
        EntityManager em = factory.createEntityManager();
    
        em.getTransaction().begin();
        StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_descuento_test",RptAsistenciaBean.class);
        //StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_descuento",RptAsistenciaBean.class);
        
        sp.registerStoredProcedureParameter("@departamento",Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_inicio", Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_fin", Date.class, ParameterMode.IN);
        
        sp.setParameter("@departamento", departamento);
        sp.setParameter("@fecha_inicio", fechaInicio);
        sp.setParameter("@fecha_fin", fechaFin);
        em.getTransaction().commit();
        System.out.println("Hora de inicio: "+Calendar.getInstance().getTime());
        sp.execute();
        //return sp.getResultList();
        em.close();
        return sp.getResultList();   
    }
    
    public List<RptAsistenciaBean> analisisAsistenciaP(String dni, Date fechaInicio, Date fechaFin){
        Properties configuracion = PropertiesUtil.cargarProperties("config/biosis-config.properties");
        int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

        String driver = ParametrosUtil.obtenerDriver(tipoBD);
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String password = configuracion.getProperty("password");
        String accion = configuracion.getProperty("action");

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", usuario);
        properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.schema-generation.database.action", accion);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        
        EntityManager em = factory.createEntityManager();
    
        em.getTransaction().begin();
        //StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_asistencia_personal",RptAsistenciaBean.class);
        StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_asistencia_personal_test",RptAsistenciaBean.class);
        sp.registerStoredProcedureParameter("@dni_empleado",String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_inicio", Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_fin", Date.class, ParameterMode.IN);
        
        sp.setParameter("@dni_empleado", dni);
        sp.setParameter("@fecha_inicio", fechaInicio);
        sp.setParameter("@fecha_fin", fechaFin);
        em.getTransaction().commit();
        System.out.println("Hora de inicio: "+Calendar.getInstance().getTime());
        sp.execute();
        //return sp.getResultList();
        em.close();
        return sp.getResultList();   
    }
    
    public List<RptAsistenciaBean> analisisCompensacion(int departamento, Date fechaInicio, Date fechaFin){
        Properties configuracion = PropertiesUtil.cargarProperties("config/biosis-config.properties");
        int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

        String driver = ParametrosUtil.obtenerDriver(tipoBD);
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String password = configuracion.getProperty("password");
        String accion = configuracion.getProperty("action");

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", usuario);
        properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.schema-generation.database.action", accion);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        
        EntityManager em = factory.createEntityManager();
    
        em.getTransaction().begin();
        StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_compensacion",RptAsistenciaBean.class);
        //StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_reporte_descuento",RptAsistenciaBean.class);
        
        sp.registerStoredProcedureParameter("@departamento",Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_inicio", Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("@fecha_fin", Date.class, ParameterMode.IN);
        
        sp.setParameter("@departamento", departamento);
        sp.setParameter("@fecha_inicio", fechaInicio);
        sp.setParameter("@fecha_fin", fechaFin);
        em.getTransaction().commit();
        System.out.println("Hora de inicio: "+Calendar.getInstance().getTime());
        sp.execute();
        //return sp.getResultList();
        em.close();
        return sp.getResultList();   
    }
}

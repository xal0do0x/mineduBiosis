/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.controladores;


import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import controladores.AsignacionHorarioControlador;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import pruebareportes.ReporteUtil;
import utiles.Encriptador;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author OGEPER02
 */
public class pruebaProcAsistencia {   
    private static final String PERSISTENCE_UNIT_NAME = "biosis-PU";  
    private static EntityManagerFactory factory;
    public static void main(String[] args) throws ParseException {
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
        int departamento = 150;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = sdf.parse("2015-05-11");
        Date fecha2 = sdf.parse("2015-05-17");
        
        em.getTransaction().begin();
        StoredProcedureQuery sp = em.createStoredProcedureQuery("pa_crear_registro_asistencia",RptAsistenciaBean.class);
        
        sp.registerStoredProcedureParameter("departamento",Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("fecha_inicio", Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("fecha_fin", Date.class, ParameterMode.IN);
        
        sp.setParameter("departamento", departamento);
        sp.setParameter("fecha_inicio", fecha1);
        sp.setParameter("fecha_fin", fecha2);
        
        sp.execute();
        
        List<RptAsistenciaBean> lista = sp.getResultList();
        
        if(!lista.isEmpty()){
            System.out.println("Hay data");
        }else{
            System.out.println("No hay data");
        }
        for(RptAsistenciaBean rp: lista){
            System.out.println("Nombre: "+rp.getNombre()+" Dni: "+rp.getDni()+" FeCHA: "+rp.getFechaRegistro());
        }
    }
}

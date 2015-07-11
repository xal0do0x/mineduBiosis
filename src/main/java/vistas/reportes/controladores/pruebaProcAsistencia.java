/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.controladores;


import controladores.AsignacionHorarioControlador;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import pruebareportes.ReporteUtil;
import vistas.reportes.beans.RptAsistenciaBean;

/**
 *
 * @author OGEPER02
 */
public class pruebaProcAsistencia {   


    public static void main(String[] args) throws ParseException {
        AsignacionHorarioControlador asig = new AsignacionHorarioControlador();
        int departamento = 271;
        SimpleDateFormat f = new SimpleDateFormat("yyyY-MM-dD");
        Date fechaInicio = f.parse("2015-06-26");
        Date fechaFin = f.parse("2015-06-30");
       
        // get result
       List<RptAsistenciaBean> registros = asig.analisisAsistencia(departamento, fechaInicio, fechaFin);
        
        for(RptAsistenciaBean r : registros){
            System.out.println("Registro: "+r.getNombre());
        }
            

    }
}

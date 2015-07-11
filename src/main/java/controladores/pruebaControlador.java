/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Contrato;
import entidades.EmpleadoT;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author OGEPER02
 */
public class pruebaControlador {
    public static void main(String[] args) {
        String dni = "09611640";
        ContratoControlador  cc = new ContratoControlador();
//        List<Contrato> lista = cc.buscarXDNI(dni);
//        for(Contrato c : lista){
//            System.out.println("Contrato: "+c.getFecInicio()+" "+c.getFecFin());
//        }
        Contrato uno = cc.buscarContratoXFechaXDni(dni, Calendar.getInstance().getTime());
        System.out.println("Contrato: "+uno.getFecInicio()+" "+uno.getFecFin());
            
    }
}

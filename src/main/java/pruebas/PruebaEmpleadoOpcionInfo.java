/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controladores.EmpleadoOpcionInfoControlador;
import entidades.EmpleadoOpcionInfo;
import java.util.List;

/**
 *
 * @author Aldo
 */
public class PruebaEmpleadoOpcionInfo {
    private static final EmpleadoOpcionInfoControlador eoc = new EmpleadoOpcionInfoControlador();
    
    public static void main(String[] args) {
        Integer dni = 1;
        List<EmpleadoOpcionInfo> infoEmpleado = eoc.buscarTodos(dni);
        for(EmpleadoOpcionInfo e : infoEmpleado){
            if(!e.getSFieldValue5().equals(" ")&& !e.getSFieldValue5().equals("")){
                String resultado = "";
                if(e.getSFieldValue5().equals("0")){
                    resultado = "NORMAL";
                }else if(e.getSFieldValue5().equals("1")){
                    resultado = "EXONERADO";
                }else if(e.getSFieldValue5().equals("2")){
                    resultado = "DESIGNADO";
                }else if(e.getSFieldValue5().equals("3")){
                    resultado = "OTRA SEDE";
                }
                System.out.println("Info: "+resultado);
            }else{
                System.out.println("No tiene datos.");
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOBIOSTAR;
import entidades.EventLog;
import entidades.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aldo
 */
public class ReaderControlador extends Controlador<Reader>{
    
    public ReaderControlador(){
        super(Reader.class, new DAOBIOSTAR(EventLog.class));
    }
    
    public List<Reader> buscarReaderXID(int idReader){
        String jpql = "SELECT r FROM Reader r WHERE r.nReaderIdn = :idReader";
        Map<String, Object> mapa = new HashMap();
        mapa.put("idReader", idReader);
        
        return this.getDao().buscar(jpql, mapa); 
    }
}

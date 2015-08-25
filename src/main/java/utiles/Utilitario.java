/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

/**
 *
 * @author Francis
 */
public class Utilitario {
    public static void ajustarDateChooser(JDateChooser dateChooser, int tamanioColumnas){
        JTextFieldDateEditor editorFechainicio = ((JTextFieldDateEditor) dateChooser.getDateEditor());
        editorFechainicio.setColumns(tamanioColumnas);
    }
}

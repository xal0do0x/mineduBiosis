/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.reportes;

import java.util.Date;

/**
 *
 * @author Aldo
 */
public class rptAsistenciaTotal {
    private String dni;
    private String nombreCompleto;
    private Date fecha;
    private Date marcacionInicial;
    private Date marcacionFinal;
    private String asistencia;
    private String observacion;
    private String vacaciones;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getMarcacionInicial() {
        return marcacionInicial;
    }

    public void setMarcacionInicial(Date marcacionInicial) {
        this.marcacionInicial = marcacionInicial;
    }

    public Date getMarcacionFinal() {
        return marcacionFinal;
    }

    public void setMarcacionFinal(Date marcacionFinal) {
        this.marcacionFinal = marcacionFinal;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(String vacaciones) {
        this.vacaciones = vacaciones;
    }
    
    
}

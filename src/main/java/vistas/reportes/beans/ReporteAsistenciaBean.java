/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.beans;

import entidades.Horario;
import entidades.Permiso;
import entidades.Vacacion;
import java.util.Date;

/**
 *
 * @author OGEPER02
 */
public class ReporteAsistenciaBean {
    private String dni;
    private String nombre;
    private Date fechaRegistro;
    private String condicion;
    private String MarcacionEntrada;
    private String MarcacionSalida;
    private String estado;
    private String observacion;
    private String vacaciones;
    private Integer minTardanza;
    private Integer minCompensacion;
    private Horario horarioAsignado;

    public ReporteAsistenciaBean(){
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getMarcacionEntrada() {
        return MarcacionEntrada;
    }

    public void setMarcacionEntrada(String MarcacionEntrada) {
        this.MarcacionEntrada = MarcacionEntrada;
    }

    public String getMarcacionSalida() {
        return MarcacionSalida;
    }

    public void setMarcacionSalida(String MarcacionSalida) {
        this.MarcacionSalida = MarcacionSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Integer getMinTardanza() {
        return minTardanza;
    }

    public void setMinTardanza(Integer minTardanza) {
        this.minTardanza = minTardanza;
    }

    public Integer getMinCompensacion() {
        return minCompensacion;
    }

    public void setMinCompensacion(Integer minCompensacion) {
        this.minCompensacion = minCompensacion;
    }

    public Horario getHorarioAsignado() {
        return horarioAsignado;
    }

    public void setHorarioAsignado(Horario horarioAsignado) {
        this.horarioAsignado = horarioAsignado;
    } 
}

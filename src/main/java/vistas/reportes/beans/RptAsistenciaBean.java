/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.reportes.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OGEPER02
 */
@Entity
public class RptAsistenciaBean implements Serializable {
    
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="dni_empleado",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String dni;
    @Column(name="nombre",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(name="fecha_registro",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaRegistro;
    @Column(name="condicion",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String condicion;
    @Column(name="marcacion_entrada",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String MarcacionEntrada;
    @Column(name="marcacion_salida",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String MarcacionSalida;
    @Column(name="estado",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String estado;
    @Column(name="observacion",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String observacion;
    @Column(name="vacacion",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String vacaciones;
    @Column(name="min_tardanza",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private Integer minTardanza;
    @Column(name="compensacion",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private Integer minCompensacion;


    public RptAsistenciaBean(){
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}

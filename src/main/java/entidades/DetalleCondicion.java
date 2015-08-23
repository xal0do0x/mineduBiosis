/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OGEPER02
 */
@Entity
@Table(name = "detalle_condicion")
public class DetalleCondicion implements Serializable {
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "empleado_nro_documento")
    private String empleadoNroDocumento;
    @JoinColumn(name = "condicion_id", referencedColumnName = "id", insertable = true, nullable = false, unique = false, updatable = true)
    @ManyToOne(optional = false, targetEntity = Condicion.class)
    private Condicion condicionId;
    //@ManyToOne(optional = false, targetEntity = Periodo.class)
    //@JoinColumn(name = "periodo_anio", referencedColumnName = "anio", insertable = true, nullable = false, unique = false, updatable = true)
    //private Periodo periodo;
    public DetalleCondicion() {
    }

    public DetalleCondicion(Long id) {
        this.id = id;
    }

    public DetalleCondicion(Long id, Date fechaInicio, String empleadoNroDocumento) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.empleadoNroDocumento = empleadoNroDocumento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEmpleadoNroDocumento() {
        return empleadoNroDocumento;
    }

    public void setEmpleadoNroDocumento(String empleadoNroDocumento) {
        this.empleadoNroDocumento = empleadoNroDocumento;
    }

    public Condicion getCondicionId() {
        return condicionId;
    }

    public void setCondicionId(Condicion condicionId) {
        this.condicionId = condicionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCondicion)) {
            return false;
        }
        DetalleCondicion other = (DetalleCondicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleCondicion[ id=" + id + " ]";
    }
    
}

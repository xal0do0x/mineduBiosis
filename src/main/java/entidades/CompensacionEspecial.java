/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OGEPER02
 */
@Entity
@Table(name = "compensacion_especial")
public class CompensacionEspecial implements Serializable {
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "dni")
    private String empleadoNroDocumento;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "documento")
    private String documento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compensacionId")
    private List<VariacionDia> variacionDiaList;

    public CompensacionEspecial() {
    }

    public CompensacionEspecial(Long id) {
        this.id = id;
    }

    public CompensacionEspecial(Long id, String dni, Date fechaInicio) {
        this.id = id;
        this.empleadoNroDocumento = dni;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return empleadoNroDocumento;
    }

    public void setDni(String dni) {
        this.empleadoNroDocumento = dni;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
        if (!(object instanceof CompensacionEspecial)) {
            return false;
        }
        CompensacionEspecial other = (CompensacionEspecial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CompensacionEspecial[ id=" + id + " ]";
    }

    @XmlTransient
    public List<VariacionDia> getVariacionDiaList() {
        return variacionDiaList;
    }

    public void setVariacionDiaList(List<VariacionDia> variacionDiaList) {
        this.variacionDiaList = variacionDiaList;
    }
    
}

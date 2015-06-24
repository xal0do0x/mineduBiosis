/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "TB_USER_CUSTOMFIELD")
public class EmpleadoOpcionCampo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nFieldIdn")
    private Integer nFieldIdn;
    @Basic(optional = false)
    @Column(name = "sFieldName")
    private String sFieldName;
    @Basic(optional = false)
    @Column(name = "nFieldType")
    private int nFieldType;
    @Basic(optional = false)
    @Column(name = "nDataType")
    private int nDataType;
    @Basic(optional = false)
    @Column(name = "nEssential")
    private short nEssential;
    @Basic(optional = false)
    @Column(name = "nValueIdn")
    private int nValueIdn;
    @Basic(optional = false)
    @Column(name = "sData")
    private String sData;

    public EmpleadoOpcionCampo() {
    }

    public EmpleadoOpcionCampo(Integer nFieldIdn) {
        this.nFieldIdn = nFieldIdn;
    }

    public EmpleadoOpcionCampo(Integer nFieldIdn, String sFieldName, int nFieldType, int nDataType, short nEssential, int nValueIdn, String sData) {
        this.nFieldIdn = nFieldIdn;
        this.sFieldName = sFieldName;
        this.nFieldType = nFieldType;
        this.nDataType = nDataType;
        this.nEssential = nEssential;
        this.nValueIdn = nValueIdn;
        this.sData = sData;
    }

    public Integer getNFieldIdn() {
        return nFieldIdn;
    }

    public void setNFieldIdn(Integer nFieldIdn) {
        this.nFieldIdn = nFieldIdn;
    }

    public String getSFieldName() {
        return sFieldName;
    }

    public void setSFieldName(String sFieldName) {
        this.sFieldName = sFieldName;
    }

    public int getNFieldType() {
        return nFieldType;
    }

    public void setNFieldType(int nFieldType) {
        this.nFieldType = nFieldType;
    }

    public int getNDataType() {
        return nDataType;
    }

    public void setNDataType(int nDataType) {
        this.nDataType = nDataType;
    }

    public short getNEssential() {
        return nEssential;
    }

    public void setNEssential(short nEssential) {
        this.nEssential = nEssential;
    }

    public int getNValueIdn() {
        return nValueIdn;
    }

    public void setNValueIdn(int nValueIdn) {
        this.nValueIdn = nValueIdn;
    }

    public String getSData() {
        return sData;
    }

    public void setSData(String sData) {
        this.sData = sData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nFieldIdn != null ? nFieldIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoOpcionCampo)) {
            return false;
        }
        EmpleadoOpcionCampo other = (EmpleadoOpcionCampo) object;
        if ((this.nFieldIdn == null && other.nFieldIdn != null) || (this.nFieldIdn != null && !this.nFieldIdn.equals(other.nFieldIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EmpleadoOpcionCampo[ nFieldIdn=" + nFieldIdn + " ]";
    }
    
}

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aldo
 */
@Entity
@Table(name = "TB_EVENT_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventData.findAll", query = "SELECT e FROM EventData e"),
    @NamedQuery(name = "EventData.findByNEventIdn", query = "SELECT e FROM EventData e WHERE e.nEventIdn = :nEventIdn"),
    @NamedQuery(name = "EventData.findBySName", query = "SELECT e FROM EventData e WHERE e.sName = :sName"),
    @NamedQuery(name = "EventData.findBySDescription", query = "SELECT e FROM EventData e WHERE e.sDescription = :sDescription")})
public class EventData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nEventIdn")
    private Integer nEventIdn;
    @Basic(optional = false)
    @Column(name = "sName")
    private String sName;
    @Basic(optional = false)
    @Column(name = "sDescription")
    private String sDescription;

    public EventData() {
    }

    public EventData(Integer nEventIdn) {
        this.nEventIdn = nEventIdn;
    }

    public EventData(Integer nEventIdn, String sName, String sDescription) {
        this.nEventIdn = nEventIdn;
        this.sName = sName;
        this.sDescription = sDescription;
    }

    public Integer getNEventIdn() {
        return nEventIdn;
    }

    public void setNEventIdn(Integer nEventIdn) {
        this.nEventIdn = nEventIdn;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSDescription() {
        return sDescription;
    }

    public void setSDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEventIdn != null ? nEventIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventData)) {
            return false;
        }
        EventData other = (EventData) object;
        if ((this.nEventIdn == null && other.nEventIdn != null) || (this.nEventIdn != null && !this.nEventIdn.equals(other.nEventIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EventData[ nEventIdn=" + nEventIdn + " ]";
    }
    
}

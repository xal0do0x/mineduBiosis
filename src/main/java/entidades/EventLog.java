/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "TB_EVENT_LOG")
@XmlRootElement
public class EventLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nEventLogIdn")
    private Integer nEventLogIdn;
    @Basic(optional = false)
    @Column(name = "nDateTime")
    private int nDateTime;
    @Basic(optional = false)
    @Column(name = "nReaderIdn")
    private int nReaderIdn;
    @Basic(optional = false)
    @Column(name = "nEventIdn")
    private int nEventIdn;
    @Basic(optional = false)
    @Column(name = "nUserID")
    private int nUserID;
    @Basic(optional = false)
    @Column(name = "nIsLog")
    private short nIsLog;
    @Basic(optional = false)
    @Column(name = "nTNAEvent")
    private short nTNAEvent;
    @Basic(optional = false)
    @Column(name = "nIsUseTA")
    private short nIsUseTA;
    @Basic(optional = false)
    @Column(name = "nType")
    private short nType;

    public EventLog() {
    }

    public EventLog(Integer nEventLogIdn) {
        this.nEventLogIdn = nEventLogIdn;
    }

    public EventLog(Integer nEventLogIdn,int nDateTime,int nReaderIdn, int nEventIdn, int nUserID, short nIsLog, short nTNAEvent, short nIsUseTA, short nType) {
        this.nEventLogIdn = nEventLogIdn;
        this.nDateTime = nDateTime;
        this.nReaderIdn = nReaderIdn;
        this.nEventIdn = nEventIdn;
        this.nUserID = nUserID;
        this.nIsLog = nIsLog;
        this.nTNAEvent = nTNAEvent;
        this.nIsUseTA = nIsUseTA;
        this.nType = nType;
    }

    public Integer getNEventLogIdn() {
        return nEventLogIdn;
    }

    public void setNEventLogIdn(Integer nEventLogIdn) {
        this.nEventLogIdn = nEventLogIdn;
    }

    public int getnDateTime() {
        return nDateTime;
    }

    public void setnDateTime(int nDateTime) {
        this.nDateTime = nDateTime;
    }

    public int getnReaderIdn() {
        return nReaderIdn;
    }

    public void setnReaderIdn(int nReaderIdn) {
        this.nReaderIdn = nReaderIdn;
    }

    public int getnEventIdn() {
        return nEventIdn;
    }

    public void setnEventIdn(int nEventIdn) {
        this.nEventIdn = nEventIdn;
    }

    public int getnUserID() {
        return nUserID;
    }

    public void setnUserID(int nUserID) {
        this.nUserID = nUserID;
    }

    public short getNIsLog() {
        return nIsLog;
    }

    public void setNIsLog(short nIsLog) {
        this.nIsLog = nIsLog;
    }

    public short getNTNAEvent() {
        return nTNAEvent;
    }

    public void setNTNAEvent(short nTNAEvent) {
        this.nTNAEvent = nTNAEvent;
    }

    public short getNIsUseTA() {
        return nIsUseTA;
    }

    public void setNIsUseTA(short nIsUseTA) {
        this.nIsUseTA = nIsUseTA;
    }

    public short getNType() {
        return nType;
    }

    public void setNType(short nType) {
        this.nType = nType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEventLogIdn != null ? nEventLogIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventLog)) {
            return false;
        }
        EventLog other = (EventLog) object;
        if ((this.nEventLogIdn == null && other.nEventLogIdn != null) || (this.nEventLogIdn != null && !this.nEventLogIdn.equals(other.nEventLogIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EventLog[ nEventLogIdn=" + nEventLogIdn + " ]";
    }
    
}

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
@Table(name = "TB_READER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reader.findAll", query = "SELECT r FROM Reader r"),
    @NamedQuery(name = "Reader.findByNReaderIdn", query = "SELECT r FROM Reader r WHERE r.nReaderIdn = :nReaderIdn"),
    @NamedQuery(name = "Reader.findBySName", query = "SELECT r FROM Reader r WHERE r.sName = :sName"),
    @NamedQuery(name = "Reader.findByNType", query = "SELECT r FROM Reader r WHERE r.nType = :nType"),
    @NamedQuery(name = "Reader.findByNDeptIdn", query = "SELECT r FROM Reader r WHERE r.nDeptIdn = :nDeptIdn"),
    @NamedQuery(name = "Reader.findBySIP", query = "SELECT r FROM Reader r WHERE r.sIP = :sIP"),
    @NamedQuery(name = "Reader.findBySMacAddress", query = "SELECT r FROM Reader r WHERE r.sMacAddress = :sMacAddress"),
    @NamedQuery(name = "Reader.findByNConnType", query = "SELECT r FROM Reader r WHERE r.nConnType = :nConnType"),
    @NamedQuery(name = "Reader.findBySDescription", query = "SELECT r FROM Reader r WHERE r.sDescription = :sDescription")})
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nReaderIdn")
    private Integer nReaderIdn;
    @Basic(optional = false)
    @Column(name = "sName")
    private String sName;
    @Basic(optional = false)
    @Column(name = "nType")
    private int nType;
    @Basic(optional = false)
    @Column(name = "nDeptIdn")
    private int nDeptIdn;
    @Basic(optional = false)
    @Column(name = "sIP")
    private String sIP;
    @Basic(optional = false)
    @Column(name = "sMacAddress")
    private String sMacAddress;
    @Basic(optional = false)
    @Column(name = "nConnType")
    private int nConnType;
    @Basic(optional = false)
    @Column(name = "sDescription")
    private String sDescription;

    public Reader() {
    }

    public Reader(Integer nReaderIdn) {
        this.nReaderIdn = nReaderIdn;
    }

    public Reader(Integer nReaderIdn, String sName, int nType, int nDeptIdn, String sIP, String sMacAddress, int nConnType, String sDescription) {
        this.nReaderIdn = nReaderIdn;
        this.sName = sName;
        this.nType = nType;
        this.nDeptIdn = nDeptIdn;
        this.sIP = sIP;
        this.sMacAddress = sMacAddress;
        this.nConnType = nConnType;
        this.sDescription = sDescription;
    }

    public Integer getNReaderIdn() {
        return nReaderIdn;
    }

    public void setNReaderIdn(Integer nReaderIdn) {
        this.nReaderIdn = nReaderIdn;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public int getNType() {
        return nType;
    }

    public void setNType(int nType) {
        this.nType = nType;
    }

    public int getNDeptIdn() {
        return nDeptIdn;
    }

    public void setNDeptIdn(int nDeptIdn) {
        this.nDeptIdn = nDeptIdn;
    }

    public String getSIP() {
        return sIP;
    }

    public void setSIP(String sIP) {
        this.sIP = sIP;
    }

    public String getSMacAddress() {
        return sMacAddress;
    }

    public void setSMacAddress(String sMacAddress) {
        this.sMacAddress = sMacAddress;
    }

    public int getNConnType() {
        return nConnType;
    }

    public void setNConnType(int nConnType) {
        this.nConnType = nConnType;
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
        hash += (nReaderIdn != null ? nReaderIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reader)) {
            return false;
        }
        Reader other = (Reader) object;
        if ((this.nReaderIdn == null && other.nReaderIdn != null) || (this.nReaderIdn != null && !this.nReaderIdn.equals(other.nReaderIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Reader[ nReaderIdn=" + nReaderIdn + " ]";
    }
    
}

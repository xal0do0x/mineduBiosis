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
@Table(name = "TB_USER_DEPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDept.findAll", query = "SELECT u FROM UserDept u"),
    @NamedQuery(name = "UserDept.findByNDepartmentIdn", query = "SELECT u FROM UserDept u WHERE u.nDepartmentIdn = :nDepartmentIdn"),
    @NamedQuery(name = "UserDept.findBySName", query = "SELECT u FROM UserDept u WHERE u.sName = :sName"),
    @NamedQuery(name = "UserDept.findBySDepartment", query = "SELECT u FROM UserDept u WHERE u.sDepartment = :sDepartment"),
    @NamedQuery(name = "UserDept.findByNDepth", query = "SELECT u FROM UserDept u WHERE u.nDepth = :nDepth"),
    @NamedQuery(name = "UserDept.findByNParentIdn", query = "SELECT u FROM UserDept u WHERE u.nParentIdn = :nParentIdn")})
public class UserDept implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nDepartmentIdn")
    private Integer nDepartmentIdn;
    @Basic(optional = false)
    @Column(name = "sName")
    private String sName;
    @Basic(optional = false)
    @Column(name = "sDepartment")
    private String sDepartment;
    @Basic(optional = false)
    @Column(name = "nDepth")
    private short nDepth;
    @Basic(optional = false)
    @Column(name = "nParentIdn")
    private int nParentIdn;

    public UserDept() {
    }

    public UserDept(Integer nDepartmentIdn) {
        this.nDepartmentIdn = nDepartmentIdn;
    }

    public UserDept(Integer nDepartmentIdn, String sName, String sDepartment, short nDepth, int nParentIdn) {
        this.nDepartmentIdn = nDepartmentIdn;
        this.sName = sName;
        this.sDepartment = sDepartment;
        this.nDepth = nDepth;
        this.nParentIdn = nParentIdn;
    }

    public Integer getNDepartmentIdn() {
        return nDepartmentIdn;
    }

    public void setNDepartmentIdn(Integer nDepartmentIdn) {
        this.nDepartmentIdn = nDepartmentIdn;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSDepartment() {
        return sDepartment;
    }

    public void setSDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public short getNDepth() {
        return nDepth;
    }

    public void setNDepth(short nDepth) {
        this.nDepth = nDepth;
    }

    public int getNParentIdn() {
        return nParentIdn;
    }

    public void setNParentIdn(int nParentIdn) {
        this.nParentIdn = nParentIdn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nDepartmentIdn != null ? nDepartmentIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDept)) {
            return false;
        }
        UserDept other = (UserDept) object;
        if ((this.nDepartmentIdn == null && other.nDepartmentIdn != null) || (this.nDepartmentIdn != null && !this.nDepartmentIdn.equals(other.nDepartmentIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.UserDept[ nDepartmentIdn=" + nDepartmentIdn + " ]";
    }
    
}

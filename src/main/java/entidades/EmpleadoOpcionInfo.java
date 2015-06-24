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
@Table(name = "TB_USER_CUSTOMINFO")
public class EmpleadoOpcionInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nUserIdn")
    private Integer nUserIdn;
    @Column(name = "sFieldValue1")
    private String sFieldValue1;
    @Column(name = "sFieldValue2")
    private String sFieldValue2;
    @Column(name = "sFieldValue3")
    private String sFieldValue3;
    @Column(name = "sFieldValue4")
    private String sFieldValue4;
    @Column(name = "sFieldValue5")
    private String sFieldValue5;
    @Column(name = "sFieldValue6")
    private String sFieldValue6;
    @Column(name = "sFieldValue7")
    private String sFieldValue7;
    @Column(name = "sFieldValue8")
    private String sFieldValue8;
    @Column(name = "sFieldValue9")
    private String sFieldValue9;
    @Column(name = "sFieldValue10")
    private String sFieldValue10;
    @Column(name = "sFieldValue11")
    private String sFieldValue11;
    @Column(name = "sFieldValue12")
    private String sFieldValue12;
    @Column(name = "sFieldValue13")
    private String sFieldValue13;
    @Column(name = "sFieldValue14")
    private String sFieldValue14;
    @Column(name = "sFieldValue15")
    private String sFieldValue15;
    @Column(name = "sFieldValue16")
    private String sFieldValue16;
    @Column(name = "sFieldValue17")
    private String sFieldValue17;
    @Column(name = "sFieldValue18")
    private String sFieldValue18;
    @Column(name = "sFieldValue19")
    private String sFieldValue19;
    @Column(name = "sFieldValue20")
    private String sFieldValue20;
    @Basic(optional = false)
    @Column(name = "nEncryption")
    private int nEncryption;

    public EmpleadoOpcionInfo() {
    }

    public EmpleadoOpcionInfo(Integer nUserIdn) {
        this.nUserIdn = nUserIdn;
    }

    public EmpleadoOpcionInfo(Integer nUserIdn, int nEncryption) {
        this.nUserIdn = nUserIdn;
        this.nEncryption = nEncryption;
    }

    public Integer getNUserIdn() {
        return nUserIdn;
    }

    public void setNUserIdn(Integer nUserIdn) {
        this.nUserIdn = nUserIdn;
    }

    public String getSFieldValue1() {
        return sFieldValue1;
    }

    public void setSFieldValue1(String sFieldValue1) {
        this.sFieldValue1 = sFieldValue1;
    }

    public String getSFieldValue2() {
        return sFieldValue2;
    }

    public void setSFieldValue2(String sFieldValue2) {
        this.sFieldValue2 = sFieldValue2;
    }

    public String getSFieldValue3() {
        return sFieldValue3;
    }

    public void setSFieldValue3(String sFieldValue3) {
        this.sFieldValue3 = sFieldValue3;
    }

    public String getSFieldValue4() {
        return sFieldValue4;
    }

    public void setSFieldValue4(String sFieldValue4) {
        this.sFieldValue4 = sFieldValue4;
    }

    public String getSFieldValue5() {
        return sFieldValue5;
    }

    public void setSFieldValue5(String sFieldValue5) {
        this.sFieldValue5 = sFieldValue5;
    }

    public String getSFieldValue6() {
        return sFieldValue6;
    }

    public void setSFieldValue6(String sFieldValue6) {
        this.sFieldValue6 = sFieldValue6;
    }

    public String getSFieldValue7() {
        return sFieldValue7;
    }

    public void setSFieldValue7(String sFieldValue7) {
        this.sFieldValue7 = sFieldValue7;
    }

    public String getSFieldValue8() {
        return sFieldValue8;
    }

    public void setSFieldValue8(String sFieldValue8) {
        this.sFieldValue8 = sFieldValue8;
    }

    public String getSFieldValue9() {
        return sFieldValue9;
    }

    public void setSFieldValue9(String sFieldValue9) {
        this.sFieldValue9 = sFieldValue9;
    }

    public String getSFieldValue10() {
        return sFieldValue10;
    }

    public void setSFieldValue10(String sFieldValue10) {
        this.sFieldValue10 = sFieldValue10;
    }

    public String getSFieldValue11() {
        return sFieldValue11;
    }

    public void setSFieldValue11(String sFieldValue11) {
        this.sFieldValue11 = sFieldValue11;
    }

    public String getSFieldValue12() {
        return sFieldValue12;
    }

    public void setSFieldValue12(String sFieldValue12) {
        this.sFieldValue12 = sFieldValue12;
    }

    public String getSFieldValue13() {
        return sFieldValue13;
    }

    public void setSFieldValue13(String sFieldValue13) {
        this.sFieldValue13 = sFieldValue13;
    }

    public String getSFieldValue14() {
        return sFieldValue14;
    }

    public void setSFieldValue14(String sFieldValue14) {
        this.sFieldValue14 = sFieldValue14;
    }

    public String getSFieldValue15() {
        return sFieldValue15;
    }

    public void setSFieldValue15(String sFieldValue15) {
        this.sFieldValue15 = sFieldValue15;
    }

    public String getSFieldValue16() {
        return sFieldValue16;
    }

    public void setSFieldValue16(String sFieldValue16) {
        this.sFieldValue16 = sFieldValue16;
    }

    public String getSFieldValue17() {
        return sFieldValue17;
    }

    public void setSFieldValue17(String sFieldValue17) {
        this.sFieldValue17 = sFieldValue17;
    }

    public String getSFieldValue18() {
        return sFieldValue18;
    }

    public void setSFieldValue18(String sFieldValue18) {
        this.sFieldValue18 = sFieldValue18;
    }

    public String getSFieldValue19() {
        return sFieldValue19;
    }

    public void setSFieldValue19(String sFieldValue19) {
        this.sFieldValue19 = sFieldValue19;
    }

    public String getSFieldValue20() {
        return sFieldValue20;
    }

    public void setSFieldValue20(String sFieldValue20) {
        this.sFieldValue20 = sFieldValue20;
    }

    public int getNEncryption() {
        return nEncryption;
    }

    public void setNEncryption(int nEncryption) {
        this.nEncryption = nEncryption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nUserIdn != null ? nUserIdn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoOpcionInfo)) {
            return false;
        }
        EmpleadoOpcionInfo other = (EmpleadoOpcionInfo) object;
        if ((this.nUserIdn == null && other.nUserIdn != null) || (this.nUserIdn != null && !this.nUserIdn.equals(other.nUserIdn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EmpleadoOpcionInfo[ nUserIdn=" + nUserIdn + " ]";
    }
    
}

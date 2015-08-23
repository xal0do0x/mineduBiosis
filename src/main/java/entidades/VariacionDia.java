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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author OGEPER02
 */
@Entity
@Table(name = "variacion_dia")
public class VariacionDia implements Serializable {
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "minutos_entrada")
    private Integer minutosEntrada;
    @Column(name = "minutos_salida")
    private Integer minutosSalida;
    @Basic(optional = false)
    @Column(name = "aumenta_entrada")
    private boolean aumentaEntrada;
    @Basic(optional = false)
    @Column(name = "aumenta_salida")
    private boolean aumentaSalida;
    @Basic(optional = false)
    @Column(name = "disminuye_entrada")
    private boolean disminuyeEntrada;
    @Basic(optional = false)
    @Column(name = "disminuye_salida")
    private boolean disminuyeSalida;
    @Basic(optional = false)
    @Column(name = "dia")
    private int dia;
    @JoinColumn(name = "compensacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CompensacionEspecial compensacionId;

    public VariacionDia() {
    }

    public VariacionDia(Long id) {
        this.id = id;
    }

    public VariacionDia(Long id, boolean aumentaEntrada, boolean aumentaSalida, boolean disminuyeEntrada, boolean disminuyeSalida, int dia) {
        this.id = id;
        this.aumentaEntrada = aumentaEntrada;
        this.aumentaSalida = aumentaSalida;
        this.disminuyeEntrada = disminuyeEntrada;
        this.disminuyeSalida = disminuyeSalida;
        this.dia = dia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinutosEntrada() {
        return minutosEntrada;
    }

    public void setMinutosEntrada(Integer minutosEntrada) {
        this.minutosEntrada = minutosEntrada;
    }

    public Integer getMinutosSalida() {
        return minutosSalida;
    }

    public void setMinutosSalida(Integer minutosSalida) {
        this.minutosSalida = minutosSalida;
    }

    public boolean getAumentaEntrada() {
        return aumentaEntrada;
    }

    public void setAumentaEntrada(boolean aumentaEntrada) {
        this.aumentaEntrada = aumentaEntrada;
    }

    public boolean getAumentaSalida() {
        return aumentaSalida;
    }

    public void setAumentaSalida(boolean aumentaSalida) {
        this.aumentaSalida = aumentaSalida;
    }

    public boolean getDisminuyeEntrada() {
        return disminuyeEntrada;
    }

    public void setDisminuyeEntrada(boolean disminuyeEntrada) {
        this.disminuyeEntrada = disminuyeEntrada;
    }

    public boolean getDisminuyeSalida() {
        return disminuyeSalida;
    }

    public void setDisminuyeSalida(boolean disminuyeSalida) {
        this.disminuyeSalida = disminuyeSalida;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public CompensacionEspecial getCompensacionId() {
        return compensacionId;
    }

    public void setCompensacionId(CompensacionEspecial compensacionId) {
        this.compensacionId = compensacionId;
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
        if (!(object instanceof VariacionDia)) {
            return false;
        }
        VariacionDia other = (VariacionDia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VariacionDia[ id=" + id + " ]";
    }
    
}

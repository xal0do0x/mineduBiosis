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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vacacion implements Serializable {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_interrupcion", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInterrupcion;
    @Column(name = "empleado_nro_documento", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private String empleado;
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String documento;
    @Column(name = "hay_interrupcion", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic
    private boolean hayInterrupcion;
    @Column(name = "fecha_fin", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaFin;
    //Nuevos campos agregados @Aldo
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String resolucion;
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String sinad;
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String observacion;
    //
    @ManyToOne(optional = false, targetEntity = Periodo.class)
    @JoinColumn(name = "periodo_anio", referencedColumnName = "anio", insertable = true, nullable = false, unique = false, updatable = true)
    private Periodo periodo;
    @Column(name = "fecha_inicio", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;

    /*
     PROPIEDADES PARA TRABAJAR CON REPOGRAMACION DE VACACIONES
     */
    @Column(name = "hay_reprogramacion")
    private boolean hayReprogramacion;
    @JoinColumn(name="vacacion_id",referencedColumnName="id")
    @OneToOne(optional = true, targetEntity = Vacacion.class)
    private Vacacion vacacionOrigen;
    @Column(name = "documento_reprogramacion")
    private String documentoReprogramacion;
    
    @OneToMany(mappedBy = "vacacionOrigen", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vacacion> vacacionList;
    
    @Column(name = "reprogramacion_total", nullable = true)
    private Boolean reprogramacionTotal;

    public Boolean isReprogramacionTotal() {
        return reprogramacionTotal;
    }

    public void setReprogramacionTotal(Boolean reprogramacionTotal) {
        this.reprogramacionTotal = reprogramacionTotal;
    }

    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }

    public boolean isHayReprogramacion() {
        return hayReprogramacion;
    }

    public void setHayReprogramacion(boolean hayReprogramacion) {
        this.hayReprogramacion = hayReprogramacion;
    }

    public Vacacion getVacacionOrigen() {
        return vacacionOrigen;
    }

    public void setVacacionOrigen(Vacacion vacacionOrigen) {
        this.vacacionOrigen = vacacionOrigen;
    }

    public String getDocumentoReprogramacion() {
        return documentoReprogramacion;
    }

    public void setDocumentoReprogramacion(String documentoReprogramacion) {
        this.documentoReprogramacion = documentoReprogramacion;
    }
    
    

    public Vacacion() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInterrupcion() {
        return this.fechaInterrupcion;
    }

    public void setFechaInterrupcion(Date fechaInterrupcion) {
        this.fechaInterrupcion = fechaInterrupcion;
    }

    public String getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean isHayInterrupcion() {
        return this.hayInterrupcion;
    }

    public void setHayInterrupcion(boolean hayInterrupcion) {
        this.hayInterrupcion = hayInterrupcion;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Periodo getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getSinad() {
        return sinad;
    }

    public void setSinad(String sinad) {
        this.sinad = sinad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}

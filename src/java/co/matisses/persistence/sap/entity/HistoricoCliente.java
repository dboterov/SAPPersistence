package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "Baru_HistoricoClientes")
public class HistoricoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "factura")
    private String factura;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "refCorta")
    private String refCorta;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "refProv")
    private String refProv;
    @Column(name = "cantidadVenta")
    private Double cantidadVenta;
    @Column(name = "devolucion")
    private String devolucion;
    @Column(name = "fechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Column(name = "cantidadDevolucion")
    private Double cantidadDevolucion;
    @Column(name = "fechaFactura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Column(name = "codDpto")
    private String codDpto;
    @Column(name = "nomDpto")
    private String nomDpto;
    @Column(name = "codGrupo")
    private String codGrupo;
    @Column(name = "nomGrupo")
    private String nomGrupo;
    @Column(name = "codSubgrupo")
    private String codSubgrupo;
    @Column(name = "nomSubgrupo")
    private String nomSubgrupo;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "proveedorExterior")
    private String proveedorExterior;
    @Column(name = "precioSinIva")
    private Double precioSinIva;
    @Column(name = "costo")
    private Double costo;
    @Column(name = "porcentajeUtilidad")
    private Double porcentajeUtilidad;
    @Column(name = "nit")
    private String nit;
    @Column(name = "comentarios")
    private String comentario;

    public HistoricoCliente() {
    }

    public HistoricoCliente(Integer id, String fuente, String factura, String referencia, String refCorta, String descripcion, String refProv, Double cantidadVenta,
            String devolucion, Date fechaDevolucion, Double cantidadDevolucion, Date fechaFactura, String codDpto, String nomDpto, String codGrupo, String nomGrupo,
            String codSubgrupo, String nomSubgrupo, String modelo, String proveedorExterior, Double precioSinIva, Double costo, Double porcentajeUtilidad, String nit,
            String comentario) {
        this.id = id;
        this.fuente = fuente;
        this.factura = factura;
        this.referencia = referencia;
        this.refCorta = refCorta;
        this.descripcion = descripcion;
        this.refProv = refProv;
        this.cantidadVenta = cantidadVenta;
        this.devolucion = devolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.cantidadDevolucion = cantidadDevolucion;
        this.fechaFactura = fechaFactura;
        this.codDpto = codDpto;
        this.nomDpto = nomDpto;
        this.codGrupo = codGrupo;
        this.nomGrupo = nomGrupo;
        this.codSubgrupo = codSubgrupo;
        this.nomSubgrupo = nomSubgrupo;
        this.modelo = modelo;
        this.proveedorExterior = proveedorExterior;
        this.precioSinIva = precioSinIva;
        this.costo = costo;
        this.porcentajeUtilidad = porcentajeUtilidad;
        this.nit = nit;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getRefCorta() {
        return refCorta;
    }

    public void setRefCorta(String refCorta) {
        this.refCorta = refCorta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRefProv() {
        return refProv;
    }

    public void setRefProv(String refProv) {
        this.refProv = refProv;
    }

    public Double getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(Double cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public String getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(String devolucion) {
        this.devolucion = devolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Double getCantidadDevolucion() {
        return cantidadDevolucion;
    }

    public void setCantidadDevolucion(Double cantidadDevolucion) {
        this.cantidadDevolucion = cantidadDevolucion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getCodDpto() {
        return codDpto;
    }

    public void setCodDpto(String codDpto) {
        this.codDpto = codDpto;
    }

    public String getNomDpto() {
        return nomDpto;
    }

    public void setNomDpto(String nomDpto) {
        this.nomDpto = nomDpto;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNomGrupo() {
        return nomGrupo;
    }

    public void setNomGrupo(String nomGrupo) {
        this.nomGrupo = nomGrupo;
    }

    public String getCodSubgrupo() {
        return codSubgrupo;
    }

    public void setCodSubgrupo(String codSubgrupo) {
        this.codSubgrupo = codSubgrupo;
    }

    public String getNomSubgrupo() {
        return nomSubgrupo;
    }

    public void setNomSubgrupo(String nomSubgrupo) {
        this.nomSubgrupo = nomSubgrupo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProveedorExterior() {
        return proveedorExterior;
    }

    public void setProveedorExterior(String proveedorExterior) {
        this.proveedorExterior = proveedorExterior;
    }

    public Double getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(Double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPorcentajeUtilidad() {
        return porcentajeUtilidad;
    }

    public void setPorcentajeUtilidad(Double porcentajeUtilidad) {
        this.porcentajeUtilidad = porcentajeUtilidad;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        if (!(object instanceof HistoricoCliente)) {
            return false;
        }
        HistoricoCliente other = (HistoricoCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.dwb.entity.HistoricoCliente[ id=" + id + " ]";
    }
}

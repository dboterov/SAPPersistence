package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OWHS")
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a")})
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "WhsCode")
    private String whsCode;
    @Size(max = 100)
    @Column(name = "WhsName")
    private String whsName;
    @Column(name = "IntrnalKey")
    private Integer intrnalKey;
    @Size(max = 60)
    @Column(name = "U_NOMBRE_REPORTES")
    private String uNombreReportes;
    @Size(max = 10)
    @Column(name = "U_visualizar")
    private String uvisualizar;
    @Size(max = 5)
    @Column(name = "U_Velocidad")
    private String uVelocidad;
    @Size(max = 5)
    @Column(name = "U_Prioridad")
    private String uPrioridad;
    @Size(max = 20)
    @Column(name = "U_nombrextablet")
    private String unombrextablet;
    @Size(max = 2)
    @Column(name = "U_Estado")
    private String estado;
    @Size(max = 7)
    @Column(name = "U_PlacaVehiculo")
    private String uPlacaVehiculo;
    @Size(max = 10)
    @Column(name = "U_CodeBars")
    private String uCodeBars;
    @Size(max = 10)
    @Column(name = "U_CodigoVentas")
    private String uCodigoVentas;
    @Column(name = "U_Ciudad")
    private String codigoCiudad;
    @Column(name = "City")
    private String city;
    @Column(name = "Street")
    private String street;
    @Column(name = "BalInvntAc")
    private Integer balInvntAc;
    @Column(name = "U_BodegaClientes")
    private String uBodegaClientes;

    public Almacen() {
    }

    public Almacen(String whsCode) {
        this.whsCode = whsCode;
    }

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public String getWhsName() {
        return whsName;
    }

    public void setWhsName(String whsName) {
        this.whsName = whsName;
    }

    public Integer getIntrnalKey() {
        return intrnalKey;
    }

    public void setIntrnalKey(Integer intrnalKey) {
        this.intrnalKey = intrnalKey;
    }

    public String getUNombreReportes() {
        return uNombreReportes;
    }

    public void setUNombreReportes(String uNombreReportes) {
        this.uNombreReportes = uNombreReportes;
    }

    public String getUvisualizar() {
        return uvisualizar;
    }

    public void setUvisualizar(String uvisualizar) {
        this.uvisualizar = uvisualizar;
    }

    public String getUVelocidad() {
        return uVelocidad;
    }

    public void setUVelocidad(String uVelocidad) {
        this.uVelocidad = uVelocidad;
    }

    public String getUPrioridad() {
        return uPrioridad;
    }

    public void setUPrioridad(String uPrioridad) {
        this.uPrioridad = uPrioridad;
    }

    public String getUnombrextablet() {
        return unombrextablet;
    }

    public void setUnombrextablet(String unombrextablet) {
        this.unombrextablet = unombrextablet;
    }

    public String getEstado() {
        return estado;
    }

    public void setUEstado(String estado) {
        this.estado = estado;
    }

    public String getUPlacaVehiculo() {
        return uPlacaVehiculo;
    }

    public void setUPlacaVehiculo(String uPlacaVehiculo) {
        this.uPlacaVehiculo = uPlacaVehiculo;
    }

    public String getUCodeBars() {
        return uCodeBars;
    }

    public void setUCodeBars(String uCodeBars) {
        this.uCodeBars = uCodeBars;
    }

    public String getUCodigoVentas() {
        return uCodigoVentas;
    }

    public void setUCodigoVentas(String uCodigoVentas) {
        this.uCodigoVentas = uCodigoVentas;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBalInvntAc() {
        return balInvntAc;
    }

    public void setBalInvntAc(Integer balInvntAc) {
        this.balInvntAc = balInvntAc;
    }

    public String getuBodegaClientes() {
        return uBodegaClientes;
    }

    public void setuBodegaClientes(String uBodegaClientes) {
        this.uBodegaClientes = uBodegaClientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (whsCode != null ? whsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.whsCode == null && other.whsCode != null) || (this.whsCode != null && !this.whsCode.equals(other.whsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.Almacen[ whsCode=" + whsCode + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "dbo.[@BARU_SERIESXALMACEN]")
public class BaruSeriesAlmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "U_WhsCode")
    private String uWhsCode;
    @Column(name = "U_SeriesName")
    private String uSeriesName;
    @Column(name = "U_Type")
    private String uType;
    @Column(name = "U_State")
    private String uState;
    @Column(name = "U_Serie")
    private String uSerie;
    @Column(name = "U_CodVentas")
    private String uCodVentas;
    @Column(name = "U_CodLogistica")
    private String uCodLogistica;
    @Column(name = "U_CodRuta")
    private String uCodRuta;
    @Column(name = "U_CodProyecto")
    private String uCodProyecto;

    public BaruSeriesAlmacen() {
    }

    public BaruSeriesAlmacen(String code) {
        this.code = code;
    }

    public BaruSeriesAlmacen(String code, String name, String uWhsCode, String uSeriesName, String uType, String uState, String uSerie, String uCodVentas, String uCodLogistica, String uCodRuta, String uCodProyecto) {
        this.code = code;
        this.name = name;
        this.uWhsCode = uWhsCode;
        this.uSeriesName = uSeriesName;
        this.uType = uType;
        this.uState = uState;
        this.uSerie = uSerie;
        this.uCodVentas = uCodVentas;
        this.uCodLogistica = uCodLogistica;
        this.uCodRuta = uCodRuta;
        this.uCodProyecto = uCodProyecto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuWhsCode() {
        return uWhsCode;
    }

    public void setuWhsCode(String uWhsCode) {
        this.uWhsCode = uWhsCode;
    }

    public String getuSeriesName() {
        return uSeriesName;
    }

    public void setuSeriesName(String uSeriesName) {
        this.uSeriesName = uSeriesName;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuState() {
        return uState;
    }

    public void setuState(String uState) {
        this.uState = uState;
    }

    public String getuSerie() {
        return uSerie;
    }

    public void setuSerie(String uSerie) {
        this.uSerie = uSerie;
    }

    public String getuCodVentas() {
        return uCodVentas;
    }

    public void setuCodVentas(String uCodVentas) {
        this.uCodVentas = uCodVentas;
    }

    public String getuCodLogistica() {
        return uCodLogistica;
    }

    public void setuCodLogistica(String uCodLogistica) {
        this.uCodLogistica = uCodLogistica;
    }

    public String getuCodRuta() {
        return uCodRuta;
    }

    public void setuCodRuta(String uCodRuta) {
        this.uCodRuta = uCodRuta;
    }

    public String getuCodProyecto() {
        return uCodProyecto;
    }

    public void setuCodProyecto(String uCodProyecto) {
        this.uCodProyecto = uCodProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaruSeriesAlmacen)) {
            return false;
        }
        BaruSeriesAlmacen other = (BaruSeriesAlmacen) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruSeriesAlmacen[ code=" + code + " ]";
    }
}

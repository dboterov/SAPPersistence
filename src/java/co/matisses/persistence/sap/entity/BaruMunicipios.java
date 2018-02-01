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
@Table(name = "dbo.[@BARU_MUNICIPIOS]")
public class BaruMunicipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "U_rutaRelacionada")
    private Integer uRutaRelacionada;

    public BaruMunicipios() {
    }

    public BaruMunicipios(String code) {
        this.code = code;
    }

    public BaruMunicipios(String code, String name, Integer uRutaRelacionada) {
        this.code = code;
        this.name = name;
        this.uRutaRelacionada = uRutaRelacionada;
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

    public Integer getuRutaRelacionada() {
        return uRutaRelacionada;
    }

    public void setuRutaRelacionada(Integer uRutaRelacionada) {
        this.uRutaRelacionada = uRutaRelacionada;
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
        if (!(object instanceof BaruMunicipios)) {
            return false;
        }
        BaruMunicipios other = (BaruMunicipios) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruMunicipios[ code=" + code + " ]";
    }
}

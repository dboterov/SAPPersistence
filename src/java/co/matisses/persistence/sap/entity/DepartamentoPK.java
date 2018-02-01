package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dbotero
 */
@Embeddable
public class DepartamentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Country")
    private String country;

    public DepartamentoPK() {
    }

    public DepartamentoPK(String code, String country) {
        this.code = code;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        hash += (country != null ? country.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoPK)) {
            return false;
        }
        DepartamentoPK other = (DepartamentoPK) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        if ((this.country == null && other.country != null) || (this.country != null && !this.country.equals(other.country))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.DepartamentoPK[ code=" + code + ", country=" + country + " ]";
    }

}

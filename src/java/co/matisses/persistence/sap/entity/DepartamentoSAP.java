package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OCST")
@NamedQueries({
    @NamedQuery(name = "DepartamentoSAP.findAll", query = "SELECT d FROM DepartamentoSAP d")})
@XmlRootElement
public class DepartamentoSAP implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DepartamentoPK departamentoPK;
    @Column(name = "Name")
    private String name;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "eCode")
    private Short eCode;

    public DepartamentoSAP() {
    }

    public DepartamentoSAP(DepartamentoPK departamentoPK) {
        this.departamentoPK = departamentoPK;
    }

    public DepartamentoSAP(String code, String country) {
        this.departamentoPK = new DepartamentoPK(code, country);
    }

    public DepartamentoPK getDepartamentoPK() {
        return departamentoPK;
    }

    public void setDepartamentoPK(DepartamentoPK departamentoPK) {
        this.departamentoPK = departamentoPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Short getECode() {
        return eCode;
    }

    public void setECode(Short eCode) {
        this.eCode = eCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamentoPK != null ? departamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoSAP)) {
            return false;
        }
        DepartamentoSAP other = (DepartamentoSAP) object;
        if ((this.departamentoPK == null && other.departamentoPK != null) || (this.departamentoPK != null && !this.departamentoPK.equals(other.departamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.Departamento[ departamentoPK=" + departamentoPK + " ]";
    }
    
}

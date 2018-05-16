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
@Table(name = "OSLP")
public class SalesPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SlpCode")
    private Short slpCode;
    @Basic(optional = false)
    @Column(name = "SlpName")
    private String slpName;
    @Column(name = "Active")
    private Character active;

    public SalesPerson() {
    }

    public SalesPerson(Short slpCode) {
        this.slpCode = slpCode;
    }

    public SalesPerson(Short slpCode, String slpName, Character active) {
        this.slpCode = slpCode;
        this.slpName = slpName;
        this.active = active;
    }

    public Short getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(Short slpCode) {
        this.slpCode = slpCode;
    }

    public String getSlpName() {
        return slpName;
    }

    public void setSlpName(String slpName) {
        this.slpName = slpName;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slpCode != null ? slpCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesPerson)) {
            return false;
        }
        SalesPerson other = (SalesPerson) object;
        if ((this.slpCode == null && other.slpCode != null) || (this.slpCode != null && !this.slpCode.equals(other.slpCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.SalesPerson[ slpCode=" + slpCode + " ]";
    }
}

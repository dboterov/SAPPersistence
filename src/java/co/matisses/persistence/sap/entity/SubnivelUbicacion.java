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
@Table(name = "OBSL")
public class SubnivelUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AbsEntry")
    private Long absEntry;
    @Basic(optional = false)
    @Column(name = "FldAbs")
    private Long fldAbs;
    @Basic(optional = false)
    @Column(name = "SLCode")
    private String slCode;

    public SubnivelUbicacion() {
    }

    public SubnivelUbicacion(Long absEntry) {
        this.absEntry = absEntry;
    }

    public SubnivelUbicacion(Long absEntry, Long fldAbs, String slCode) {
        this.absEntry = absEntry;
        this.fldAbs = fldAbs;
        this.slCode = slCode;
    }

    public Long getAbsEntry() {
        return absEntry;
    }

    public void setAbsEntry(Long absEntry) {
        this.absEntry = absEntry;
    }

    public Long getFldAbs() {
        return fldAbs;
    }

    public void setFldAbs(Long fldAbs) {
        this.fldAbs = fldAbs;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (absEntry != null ? absEntry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubnivelUbicacion)) {
            return false;
        }
        SubnivelUbicacion other = (SubnivelUbicacion) object;
        if ((this.absEntry == null && other.absEntry != null) || (this.absEntry != null && !this.absEntry.equals(other.absEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.SubnivelUbicacion[ AbsEntry=" + absEntry + " ]";
    }
}

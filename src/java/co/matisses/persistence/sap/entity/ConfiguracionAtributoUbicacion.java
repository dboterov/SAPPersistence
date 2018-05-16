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
@Table(name = "OBFC")
public class ConfiguracionAtributoUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AbsEntry")
    private Long absEntry;
    @Basic(optional = false)
    @Column(name = "FldType")
    private Character fldType;
    @Basic(optional = false)
    @Column(name = "FldNum")
    private Integer fldNum;
    @Basic(optional = false)
    @Column(name = "DispName")
    private String dispName;

    public ConfiguracionAtributoUbicacion() {
    }

    public ConfiguracionAtributoUbicacion(Long absEntry) {
        this.absEntry = absEntry;
    }

    public ConfiguracionAtributoUbicacion(Long absEntry, Character fldType, Integer fldNum, String dispName) {
        this.absEntry = absEntry;
        this.fldType = fldType;
        this.fldNum = fldNum;
        this.dispName = dispName;
    }

    public Long getAbsEntry() {
        return absEntry;
    }

    public void setAbsEntry(Long absEntry) {
        this.absEntry = absEntry;
    }

    public Character getFldType() {
        return fldType;
    }

    public void setFldType(Character fldType) {
        this.fldType = fldType;
    }

    public Integer getFldNum() {
        return fldNum;
    }

    public void setFldNum(Integer fldNum) {
        this.fldNum = fldNum;
    }

    public String getDispName() {
        return dispName;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName;
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
        if (!(object instanceof ConfiguracionAtributoUbicacion)) {
            return false;
        }
        ConfiguracionAtributoUbicacion other = (ConfiguracionAtributoUbicacion) object;
        if ((this.absEntry == null && other.absEntry != null) || (this.absEntry != null && !this.absEntry.equals(other.absEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ConfiguracionAtributoUbicacion[ AbsEntry=" + absEntry + " ]";
    }
}

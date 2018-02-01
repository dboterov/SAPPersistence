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
@Table(name = "OHED")
public class ClaseFormacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "edType")
    private Integer edType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "descriptio")
    private String descriptio;
    @Column(name = "Locked")
    private Character locked;

    public ClaseFormacion() {
    }

    public ClaseFormacion(Integer edType) {
        this.edType = edType;
    }

    public ClaseFormacion(Integer edType, String name, String descriptio, Character locked) {
        this.edType = edType;
        this.name = name;
        this.descriptio = descriptio;
        this.locked = locked;
    }

    public Integer getEdType() {
        return edType;
    }

    public void setEdType(Integer edType) {
        this.edType = edType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptio() {
        return descriptio;
    }

    public void setDescriptio(String descriptio) {
        this.descriptio = descriptio;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (edType != null ? edType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseFormacion)) {
            return false;
        }
        ClaseFormacion other = (ClaseFormacion) object;
        if ((this.edType == null && other.edType != null) || (this.edType != null && !this.edType.equals(other.edType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ClaseFormacion[ edType=" + edType + " ]";
    }
}

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
@Table(name = "OHTY")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "name")
    private String name;
    @Column(name = "descriptio")
    private String descriptio;
    @Column(name = "locked")
    private Character locked;

    public Rol() {
    }

    public Rol(Integer typeID) {
        this.typeID = typeID;
    }

    public Rol(Integer typeID, String name, String descriptio, Character locked) {
        this.typeID = typeID;
        this.name = name;
        this.descriptio = descriptio;
        this.locked = locked;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
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
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Rol[ typeID=" + typeID + " ]";
    }
}

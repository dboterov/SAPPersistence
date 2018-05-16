package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "OHPS")
public class PosicionEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "posID")
    private Integer posID;
    @Column(name = "name")
    private String name;
    @Column(name = "descriptio")
    private String descriptio;
    @Column(name = "LocFields")
    private Character locFields;

    public PosicionEmpleado() {
    }

    public PosicionEmpleado(Integer posID) {
        this.posID = posID;
    }

    public PosicionEmpleado(Integer posID, String name, String descriptio, Character locFields) {
        this.posID = posID;
        this.name = name;
        this.descriptio = descriptio;
        this.locFields = locFields;
    }

    public Integer getPosID() {
        return posID;
    }

    public void setPosID(Integer posID) {
        this.posID = posID;
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

    public Character getLocFields() {
        return locFields;
    }

    public void setLocFields(Character locFields) {
        this.locFields = locFields;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posID != null ? posID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionEmpleado)) {
            return false;
        }
        PosicionEmpleado other = (PosicionEmpleado) object;
        if ((this.posID == null && other.posID != null) || (this.posID != null && !this.posID.equals(other.posID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.PosicionEmpleado[ posID=" + posID + " ]";
    }
}

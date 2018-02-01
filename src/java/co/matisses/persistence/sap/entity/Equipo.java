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
@Table(name = "OHTM")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "teamID")
    private Integer teamID;
    @Column(name = "name")
    private String name;
    @Column(name = "descriptio")
    private String descriptio;

    public Equipo() {
    }

    public Equipo(Integer teamID) {
        this.teamID = teamID;
    }

    public Equipo(Integer teamID, String name, String descriptio) {
        this.teamID = teamID;
        this.name = name;
        this.descriptio = descriptio;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teamID != null ? teamID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.teamID == null && other.teamID != null) || (this.teamID != null && !this.teamID.equals(other.teamID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.EquipoEmpleado[ teamID=" + teamID + " ]";
    }
}

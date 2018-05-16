package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ygil
 */
@Embeddable
public class EquipoEmpleadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "teamID")
    private int teamID;
    @Basic(optional = false)
    @Column(name = "empID")
    private int empID;

    public EquipoEmpleadoPK() {
    }

    public EquipoEmpleadoPK(int teamID, int empID) {
        this.teamID = teamID;
        this.empID = empID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.teamID;
        hash = 13 * hash + this.empID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EquipoEmpleadoPK other = (EquipoEmpleadoPK) obj;
        if (this.teamID != other.teamID) {
            return false;
        }
        if (this.empID != other.empID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EquipoEmpleadoPK{" + "teamID=" + teamID + ", empID=" + empID + '}';
    }
}

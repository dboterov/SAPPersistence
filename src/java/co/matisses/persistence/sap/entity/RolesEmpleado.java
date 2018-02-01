package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "HEM6")
public class RolesEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesEmpleadoPK rolesEmpleadoPK;
    @Column(name = "roleID")
    private Integer roleID;
    @Column(name = "LogInstanc")
    private Integer logInstanc;

    public RolesEmpleado() {
    }

    public RolesEmpleado(RolesEmpleadoPK rolesEmpleadoPK) {
        this.rolesEmpleadoPK = rolesEmpleadoPK;
    }

    public RolesEmpleado(RolesEmpleadoPK rolesEmpleadoPK, Integer roleID, Integer logInstanc) {
        this.rolesEmpleadoPK = rolesEmpleadoPK;
        this.roleID = roleID;
        this.logInstanc = logInstanc;
    }

    public RolesEmpleadoPK getRolesEmpleadoPK() {
        return rolesEmpleadoPK;
    }

    public void setRolesEmpleadoPK(RolesEmpleadoPK rolesEmpleadoPK) {
        this.rolesEmpleadoPK = rolesEmpleadoPK;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesEmpleadoPK != null ? rolesEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesEmpleado)) {
            return false;
        }
        RolesEmpleado other = (RolesEmpleado) object;
        if ((this.rolesEmpleadoPK == null && other.rolesEmpleadoPK != null) || (this.rolesEmpleadoPK != null && !this.rolesEmpleadoPK.equals(other.rolesEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.RolesEmpleado[ rolesEmpleadoPK=" + rolesEmpleadoPK + " ]";
    }
}

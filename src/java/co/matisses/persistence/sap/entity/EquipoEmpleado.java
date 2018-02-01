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
@Table(name = "HTM1")
public class EquipoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoEmpleadoPK equipoEmpleadoPK;
    @Column(name = "line")
    private Short line;
    @Column(name = "role")
    private Character role;

    public EquipoEmpleado() {
    }

    public EquipoEmpleado(EquipoEmpleadoPK equiposEmpleadosPK) {
        this.equipoEmpleadoPK = equiposEmpleadosPK;
    }

    public EquipoEmpleado(EquipoEmpleadoPK equiposEmpleadosPK, Short line, Character role) {
        this.equipoEmpleadoPK = equiposEmpleadosPK;
        this.line = line;
        this.role = role;
    }

    public EquipoEmpleadoPK getEquipoEmpleadoPK() {
        return equipoEmpleadoPK;
    }

    public void setEquipoEmpleadoPK(EquipoEmpleadoPK equipoEmpleadoPK) {
        this.equipoEmpleadoPK = equipoEmpleadoPK;
    }

    public Short getLine() {
        return line;
    }

    public void setLine(Short line) {
        this.line = line;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoEmpleadoPK != null ? equipoEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoEmpleado)) {
            return false;
        }
        EquipoEmpleado other = (EquipoEmpleado) object;
        if ((this.equipoEmpleadoPK == null && other.equipoEmpleadoPK != null) || (this.equipoEmpleadoPK != null && !this.equipoEmpleadoPK.equals(other.equipoEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.EquipoEmpleado[ equiposEmpleadosPK=" + equipoEmpleadoPK + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "dbo.[@BARU_DECORADORES]")
public class BaruDecoradores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "U_Nit")
    private String uNit;
    @Column(name = "U_FechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date uFechaIngreso;
    @Column(name = "U_Estado")
    private String uEstado;
    @Column(name = "U_Tipo")
    private String uTipo;

    public BaruDecoradores() {
    }

    public BaruDecoradores(String code) {
        this.code = code;
    }

    public BaruDecoradores(String code, String name, String uNit, Date uFechaIngreso, String uEstado) {
        this.code = code;
        this.name = name;
        this.uNit = uNit;
        this.uFechaIngreso = uFechaIngreso;
        this.uEstado = uEstado;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuNit() {
        return uNit;
    }

    public void setuNit(String uNit) {
        this.uNit = uNit;
    }

    public Date getuFechaIngreso() {
        return uFechaIngreso;
    }

    public void setuFechaIngreso(Date uFechaIngreso) {
        this.uFechaIngreso = uFechaIngreso;
    }

    public String getuEstado() {
        return uEstado;
    }

    public void setuEstado(String uEstado) {
        this.uEstado = uEstado;
    }

    public String getuTipo() {
        return uTipo;
    }

    public void setuTipo(String uTipo) {
        this.uTipo = uTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaruDecoradores)) {
            return false;
        }
        BaruDecoradores other = (BaruDecoradores) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruDecoradores[ code=" + code + " ]";
    }
}

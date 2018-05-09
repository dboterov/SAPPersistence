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
@Table(name = "[@BARU_AVERIASXMAT]")
public class BaruAveriasMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "U_Material")
    private String uMaterial;
    @Column(name = "U_Problema")
    private String uProblema;

    public BaruAveriasMaterial() {
    }

    public BaruAveriasMaterial(String code) {
        this.code = code;
    }

    public BaruAveriasMaterial(String code, String name, String uMaterial, String uProblema) {
        this.code = code;
        this.name = name;
        this.uMaterial = uMaterial;
        this.uProblema = uProblema;
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

    public String getUMaterial() {
        return uMaterial;
    }

    public void setUMaterial(String uMaterial) {
        this.uMaterial = uMaterial;
    }

    public String getUProblema() {
        return uProblema;
    }

    public void setUProblema(String uProblema) {
        this.uProblema = uProblema;
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
        if (!(object instanceof BaruAveriasMaterial)) {
            return false;
        }
        BaruAveriasMaterial other = (BaruAveriasMaterial) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruAveriasMaterial[ code=" + code + " ]";
    }
}

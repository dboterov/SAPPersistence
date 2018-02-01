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
@Table(name = "dbo.[@BARU_MATERIALES]")
public class BaruMateriales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "U_Cuidados")
    private String uCuidados;
    @Basic(optional = false)
    @Column(name = "U_Parte")
    private String uParte;
    @Basic(optional = false)
    @Column(name = "U_NombreWeb")
    private String uNombreWeb;

    public BaruMateriales() {
    }

    public BaruMateriales(String code) {
        this.code = code;
    }

    public BaruMateriales(String code, String name, String uCuidados, String uParte, String uNombreWeb) {
        this.code = code;
        this.name = name;
        this.uCuidados = uCuidados;
        this.uParte = uParte;
        this.uNombreWeb = uNombreWeb;
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

    public String getuCuidados() {
        return uCuidados;
    }

    public void setuCuidados(String uCuidados) {
        this.uCuidados = uCuidados;
    }

    public String getuParte() {
        return uParte;
    }

    public void setuParte(String uParte) {
        this.uParte = uParte;
    }

    public String getuNombreWeb() {
        return uNombreWeb;
    }

    public void setuNombreWeb(String uNombreWeb) {
        this.uNombreWeb = uNombreWeb;
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
        if (!(object instanceof BaruMateriales)) {
            return false;
        }
        BaruMateriales other = (BaruMateriales) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruMateriales[ Code=" + code + " ]";
    }
}

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
@Table(name = "dbo.[@BARU_COLXART]")
public class BaruColorArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "U_color")
    private String uColor;
    @Basic(optional = false)
    @Column(name = "U_articulo")
    private String uArticulo;
    @Column(name = "U_principal")
    private String uPrincipal;

    public BaruColorArticulo() {
    }

    public BaruColorArticulo(String code) {
        this.code = code;
    }

    public BaruColorArticulo(String code, String name, String uColor, String uArticulo, String uPrincipal) {
        this.code = code;
        this.name = name;
        this.uColor = uColor;
        this.uArticulo = uArticulo;
        this.uPrincipal = uPrincipal;
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

    public String getuColor() {
        return uColor;
    }

    public void setuColor(String uColor) {
        this.uColor = uColor;
    }

    public String getuArticulo() {
        return uArticulo;
    }

    public void setuArticulo(String uArticulo) {
        this.uArticulo = uArticulo;
    }

    public String getuPrincipal() {
        return uPrincipal;
    }

    public void setuPrincipal(String uPrincipal) {
        this.uPrincipal = uPrincipal;
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
        if (!(object instanceof BaruColorArticulo)) {
            return false;
        }
        BaruColorArticulo other = (BaruColorArticulo) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruColxArt[ Code=" + code + " ]";
    }
}

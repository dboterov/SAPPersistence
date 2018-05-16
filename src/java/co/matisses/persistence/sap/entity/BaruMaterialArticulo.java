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
@Table(name = "dbo.[@BARU_MATXART]")
public class BaruMaterialArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "U_itemCode")
    private String uItemCode;
    @Basic(optional = false)
    @Column(name = "U_matCode")
    private String uMatCode;

    public BaruMaterialArticulo() {
    }

    public BaruMaterialArticulo(String code) {
        this.code = code;
    }

    public BaruMaterialArticulo(String code, String name, String uItemCode, String uMatCode) {
        this.code = code;
        this.name = name;
        this.uItemCode = uItemCode;
        this.uMatCode = uMatCode;
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

    public String getuItemCode() {
        return uItemCode;
    }

    public void setuItemCode(String uItemCode) {
        this.uItemCode = uItemCode;
    }

    public String getuMatCode() {
        return uMatCode;
    }

    public void setuMatCode(String uMatCode) {
        this.uMatCode = uMatCode;
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
        if (!(object instanceof BaruMaterialArticulo)) {
            return false;
        }
        BaruMaterialArticulo other = (BaruMaterialArticulo) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruMaterialArticulo[ Code=" + code + " ]";
    }
}

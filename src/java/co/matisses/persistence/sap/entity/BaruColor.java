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
@Table(name = "[@BARUCOLOR]")
public class BaruColor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "U_colorgenerico")
    private String colorGenerico;
    @Column(name = "U_cod_hexa")
    private String codigoHexa;

    public BaruColor() {
    }

    public BaruColor(String code) {
        this.code = code;
    }

    public BaruColor(String code, String name, String colorGenerico, String codigoHexa) {
        this.code = code;
        this.name = name;
        this.colorGenerico = colorGenerico;
        this.codigoHexa = codigoHexa;
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

    public String getColorGenerico() {
        return colorGenerico;
    }

    public void setColorGenerico(String colorGenerico) {
        this.colorGenerico = colorGenerico;
    }

    public String getCodigoHexa() {
        return codigoHexa;
    }

    public void setCodigoHexa(String codigoHexa) {
        this.codigoHexa = codigoHexa;
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
        if (!(object instanceof BaruColor)) {
            return false;
        }
        BaruColor other = (BaruColor) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruColor[ code=" + code + " ]";
    }
}

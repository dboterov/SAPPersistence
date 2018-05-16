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
@Table(name = "dbo.[@BARU_COMB_PPAL]")
public class BaruCombinacionPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "U_modelo")
    private String uModelo;
    @Column(name = "U_Referencia")
    private String uReferencia;
    @Column(name = "U_Orden")
    private String uOrden;

    public BaruCombinacionPrincipal() {
    }

    public BaruCombinacionPrincipal(String code) {
        this.code = code;
    }

    public BaruCombinacionPrincipal(String code, String name, String uModelo, String uReferencia, String uOrden) {
        this.code = code;
        this.name = name;
        this.uModelo = uModelo;
        this.uReferencia = uReferencia;
        this.uOrden = uOrden;
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

    public String getuModelo() {
        return uModelo;
    }

    public void setuModelo(String uModelo) {
        this.uModelo = uModelo;
    }

    public String getuReferencia() {
        return uReferencia;
    }

    public void setuReferencia(String uReferencia) {
        this.uReferencia = uReferencia;
    }

    public String getuOrden() {
        return uOrden;
    }

    public void setuOrden(String uOrden) {
        this.uOrden = uOrden;
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
        if (!(object instanceof BaruCombinacionPrincipal)) {
            return false;
        }
        BaruCombinacionPrincipal other = (BaruCombinacionPrincipal) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruCombinacionPrincipal[ Code=" + code + " ]";
    }
}

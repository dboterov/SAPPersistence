package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dbotero
 */
@Embeddable
public class SaldoItemInventarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ItemCode")
    private String itemCode;
    @JoinColumn(name = "whsCode", referencedColumnName = "whsCode")
    @ManyToOne(optional = false)
    private Almacen whsCode;

    public SaldoItemInventarioPK() {
    }

    public SaldoItemInventarioPK(String itemCode, Almacen whsCode) {
        this.itemCode = itemCode;
        this.whsCode = whsCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Almacen getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(Almacen whsCode) {
        this.whsCode = whsCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        hash += (whsCode != null ? whsCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaldoItemInventarioPK)) {
            return false;
        }
        SaldoItemInventarioPK other = (SaldoItemInventarioPK) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        if ((this.whsCode == null && other.whsCode != null) || (this.whsCode != null && !this.whsCode.equals(other.whsCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.SaldoItemInventarioPK[ itemCode=" + itemCode + ", whsCode=" + whsCode + " ]";
    }
    
}

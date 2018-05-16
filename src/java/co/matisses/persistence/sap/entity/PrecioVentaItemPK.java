package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dbotero
 */
@Embeddable
public class PrecioVentaItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ItemCode")
    private String itemCode;
    @Basic(optional = false)
    @Column(name = "PriceList")
    private short priceList;

    public PrecioVentaItemPK() {
    }

    public PrecioVentaItemPK(String itemCode, short priceList) {
        this.itemCode = itemCode;
        this.priceList = priceList;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public short getPriceList() {
        return priceList;
    }

    public void setPriceList(short priceList) {
        this.priceList = priceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        hash += (int) priceList;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioVentaItemPK)) {
            return false;
        }
        PrecioVentaItemPK other = (PrecioVentaItemPK) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        if (this.priceList != other.priceList) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.PrecioVentaItemPK[ itemCode=" + itemCode + ", priceList=" + priceList + " ]";
    }

}

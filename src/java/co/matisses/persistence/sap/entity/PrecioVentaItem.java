package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "ITM1")
@NamedQueries({
    @NamedQuery(name = "PrecioVentaItem.findAll", query = "SELECT p FROM PrecioVentaItem p")})
public class PrecioVentaItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrecioVentaItemPK precioVentaItemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "Ovrwritten")
    private Character ovrwritten;
    @Column(name = "Factor")
    private BigDecimal factor;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "ObjType")
    private String objType;

    public PrecioVentaItem() {
    }

    public PrecioVentaItem(PrecioVentaItemPK precioVentaItemPK) {
        this.precioVentaItemPK = precioVentaItemPK;
    }

    public PrecioVentaItem(String itemCode, short priceList) {
        this.precioVentaItemPK = new PrecioVentaItemPK(itemCode, priceList);
    }

    public PrecioVentaItemPK getPrecioVentaItemPK() {
        return precioVentaItemPK;
    }

    public void setPrecioVentaItemPK(PrecioVentaItemPK precioVentaItemPK) {
        this.precioVentaItemPK = precioVentaItemPK;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Character getOvrwritten() {
        return ovrwritten;
    }

    public void setOvrwritten(Character ovrwritten) {
        this.ovrwritten = ovrwritten;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precioVentaItemPK != null ? precioVentaItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioVentaItem)) {
            return false;
        }
        PrecioVentaItem other = (PrecioVentaItem) object;
        if ((this.precioVentaItemPK == null && other.precioVentaItemPK != null) || (this.precioVentaItemPK != null && !this.precioVentaItemPK.equals(other.precioVentaItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.PrecioVentaItem[ precioVentaItemPK=" + precioVentaItemPK + " ]";
    }

}

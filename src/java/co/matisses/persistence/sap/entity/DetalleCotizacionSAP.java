package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "QUT1")
public class DetalleCotizacionSAP implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleCotizacionSAPPK detalleCotizacionSAPPK;
    @Column(name = "LineStatus")
    private Character lineStatus;
    @Column(name = "ItemCode")
    private String itemCode;
    @Column(name = "Dscription")
    private String dscription;
    @Column(name = "Quantity")
    private BigDecimal quantity;
    @Column(name = "ShipDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipDate;
    @Column(name = "OpenQty")
    private BigDecimal openQty;
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "LineTotal")
    private BigDecimal lineTotal;
    @Column(name = "WhsCode")
    private String whsCode;
    @Column(name = "SlpCode")
    private Short slpCode;
    @Column(name = "DocDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
    @Column(name = "TaxCode")
    private String taxCode;

    public DetalleCotizacionSAP() {
    }

    public DetalleCotizacionSAPPK getDetalleCotizacionSAPPK() {
        return detalleCotizacionSAPPK;
    }

    public void setDetalleCotizacionSAPPK(DetalleCotizacionSAPPK detalleCotizacionSAPPK) {
        this.detalleCotizacionSAPPK = detalleCotizacionSAPPK;
    }

    public Character getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Character lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDscription() {
        return dscription;
    }

    public void setDscription(String dscription) {
        this.dscription = dscription;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public BigDecimal getOpenQty() {
        return openQty;
    }

    public void setOpenQty(BigDecimal openQty) {
        this.openQty = openQty;
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

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public Short getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(Short slpCode) {
        this.slpCode = slpCode;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCotizacionSAPPK != null ? detalleCotizacionSAPPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCotizacionSAP)) {
            return false;
        }
        DetalleCotizacionSAP other = (DetalleCotizacionSAP) object;
        if ((this.detalleCotizacionSAPPK == null && other.detalleCotizacionSAPPK != null) || (this.detalleCotizacionSAPPK != null && !this.detalleCotizacionSAPPK.equals(other.detalleCotizacionSAPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.DetalleCotizacionSAP[ detalleCotizacionSAPPK=" + detalleCotizacionSAPPK + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "RDR1")
public class DetalleOrdenVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DocEntry")
    private int docEntry;
    @Column(name = "LineNum")
    private int lineNum;
    @Column(name = "ItemCode")
    private String itemCode;
    @Column(name = "Dscription")
    private String dscription;
    @Column(name = "Quantity")
    private BigDecimal quantity;
    @Column(name = "LineStatus")
    private Character lineStatus;

    public DetalleOrdenVenta() {
    }

    public DetalleOrdenVenta(int docEntry) {
        this.docEntry = docEntry;
    }

    public DetalleOrdenVenta(int docEntry, int lineNum, String itemCode, String dscription, BigDecimal quantity, Character lineStatus) {
        this.docEntry = docEntry;
        this.lineNum = lineNum;
        this.itemCode = itemCode;
        this.dscription = dscription;
        this.quantity = quantity;
        this.lineStatus = lineStatus;
    }

    public int getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(int docEntry) {
        this.docEntry = docEntry;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
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

    public Character getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Character lineStatus) {
        this.lineStatus = lineStatus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.docEntry;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleOrdenVenta other = (DetalleOrdenVenta) obj;
        if (this.docEntry != other.docEntry) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.DetalleOrdenVenta[ DocEntry=" + docEntry + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OCRC")
@XmlRootElement
public class TarjetaCreditoSAP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CreditCard")
    private Integer creditCard;
    @Basic(optional = false)
    @Column(name = "CardName")
    private String cardName;
    @Basic(optional = false)
    @Column(name = "AcctCode")
    private String acctCode;
    @Basic(optional = false)
    @Column(name = "Phone")
    private String whsCode;
    @Basic(optional = false)
    @Column(name = "CompanyId")
    private String terminalName;

    public TarjetaCreditoSAP() {
    }

    public TarjetaCreditoSAP(Integer creditCard) {
        this.creditCard = creditCard;
    }

    public TarjetaCreditoSAP(Integer creditCard, String cardName, String acctCode, String whsCode, String terminalName) {
        this.creditCard = creditCard;
        this.cardName = cardName;
        this.acctCode = acctCode;
        this.whsCode = whsCode;
        this.terminalName = terminalName;
    }

    public Integer getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Integer creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.creditCard);
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
        final TarjetaCreditoSAP other = (TarjetaCreditoSAP) obj;
        if (!Objects.equals(this.creditCard, other.creditCard)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaCreditoSAP{" + "creditCard=" + creditCard + ", cardName=" + cardName + ", acctCode=" + acctCode + ", whsCode=" + whsCode + ", terminalName=" + terminalName + '}';
    }

}

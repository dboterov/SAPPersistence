package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dbotero
 */
@Embeddable
public class TipoRetencionClientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CardCode")
    private String cardCode;
    @Basic(optional = false)
    @Column(name = "WTCode")
    private String wtCode;

    public TipoRetencionClientePK() {
    }

    public TipoRetencionClientePK(String cardCode, String wtCode) {
        this.cardCode = cardCode;
        this.wtCode = wtCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getWtCode() {
        return wtCode;
    }

    public void setWtCode(String wtCode) {
        this.wtCode = wtCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.cardCode);
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
        final TipoRetencionClientePK other = (TipoRetencionClientePK) obj;
        if (!Objects.equals(this.cardCode, other.cardCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoRetencionClientePK{" + "cardCode=" + cardCode + ", wtCode=" + wtCode + '}';
    }
}

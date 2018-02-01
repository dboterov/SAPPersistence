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
public class DireccionSocioDeNegociosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @Column(name = "CardCode")
    private String cardCode;
    @Basic(optional = false)
    @Column(name = "AdresType")
    private char adresType;

    public DireccionSocioDeNegociosPK() {
    }

    public DireccionSocioDeNegociosPK(String address, String cardCode, char adresType) {
        this.address = address;
        this.cardCode = cardCode;
        this.adresType = adresType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public char getAdresType() {
        return adresType;
    }

    public void setAdresType(char adresType) {
        this.adresType = adresType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (address != null ? address.hashCode() : 0);
        hash += (cardCode != null ? cardCode.hashCode() : 0);
        hash += (int) adresType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionSocioDeNegociosPK)) {
            return false;
        }
        DireccionSocioDeNegociosPK other = (DireccionSocioDeNegociosPK) object;
        if ((this.address == null && other.address != null) || (this.address != null && !this.address.equals(other.address))) {
            return false;
        }
        if ((this.cardCode == null && other.cardCode != null) || (this.cardCode != null && !this.cardCode.equals(other.cardCode))) {
            return false;
        }
        if (this.adresType != other.adresType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.DireccionSocioDeNegociosPK[ address=" + address + ", cardCode=" + cardCode + ", adresType=" + adresType + " ]";
    }
    
}

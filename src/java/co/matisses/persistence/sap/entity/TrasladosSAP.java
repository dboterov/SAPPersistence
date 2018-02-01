package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "OWTR")
public class TrasladosSAP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Basic(optional = false)
    @Column(name = "DocNum")
    private int docNum;
    @Column(name = "DocType")
    private Character docType;
    @Column(name = "DocStatus")
    private Character docStatus;
    @Column(name = "ObjType")
    private String objType;
    @Column(name = "DocDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
    @Column(name = "DocDueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDueDate;
    @Column(name = "CardCode")
    private String cardCode;
    @Column(name = "CardName")
    private String cardName;
    @Column(name = "NumAtCard")
    private String numAtCard;
    @Column(name = "SlpCode")
    private Short slpCode;
    @Column(name = "Series")
    private Short series;

    public TrasladosSAP() {
    }

    public TrasladosSAP(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public TrasladosSAP(Integer docEntry, int docNum, Character docType, Character docStatus, String objType, Date docDate, Date docDueDate, String cardCode,
            String cardName, String numAtCard, Short slpCode, Short series) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.docType = docType;
        this.docStatus = docStatus;
        this.objType = objType;
        this.docDate = docDate;
        this.docDueDate = docDueDate;
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.numAtCard = numAtCard;
        this.slpCode = slpCode;
        this.series = series;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public Character getDocType() {
        return docType;
    }

    public void setDocType(Character docType) {
        this.docType = docType;
    }

    public Character getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Character docStatus) {
        this.docStatus = docStatus;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getDocDueDate() {
        return docDueDate;
    }

    public void setDocDueDate(Date docDueDate) {
        this.docDueDate = docDueDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getNumAtCard() {
        return numAtCard;
    }

    public void setNumAtCard(String numAtCard) {
        this.numAtCard = numAtCard;
    }

    public Short getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(Short slpCode) {
        this.slpCode = slpCode;
    }

    public Short getSeries() {
        return series;
    }

    public void setSeries(Short series) {
        this.series = series;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docEntry != null ? docEntry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrasladosSAP)) {
            return false;
        }
        TrasladosSAP other = (TrasladosSAP) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.TrasladosSAP[ docEntry=" + docEntry + " ]";
    }
}

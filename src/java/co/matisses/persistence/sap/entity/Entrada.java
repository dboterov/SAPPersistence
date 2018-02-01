package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "OIGN")
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Column(name = "DocNum")
    private Integer docNum;
    @Column(name = "Ref2")
    private String ref2;
    @Column(name = "UserSign")
    private Integer userSign;

    public Entrada() {
    }

    public Entrada(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Entrada(Integer docEntry, Integer docNum, String ref2, Integer userSign) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.ref2 = ref2;
        this.userSign = userSign;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public Integer getUserSign() {
        return userSign;
    }

    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
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
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Entrada[ DocEntry=" + docEntry + " ]";
    }
}

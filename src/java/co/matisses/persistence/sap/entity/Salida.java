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
@Table(name = "OIGE")
public class Salida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Column(name = "DocNum")
    private Integer docNum;

    public Salida() {
    }

    public Salida(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public Salida(Integer docEntry, Integer docNum) {
        this.docEntry = docEntry;
        this.docNum = docNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docEntry != null ? docEntry.hashCode() : 0);
        return hash;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salida)) {
            return false;
        }
        Salida other = (Salida) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Salida[ DocEntry=" + docEntry + " ]";
    }
}

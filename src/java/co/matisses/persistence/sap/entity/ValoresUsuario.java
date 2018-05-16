package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "UFD1")
public class ValoresUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TableID")
    private String tableID;
    @Basic(optional = false)
    @Column(name = "FieldID")
    private short fieldID;
    @Id
    @Basic(optional = false)
    @Column(name = "IndexID")
    private short indexID;
    @Column(name = "FldValue")
    private String fldValue;
    @Column(name = "Descr")
    private String descr;

    public ValoresUsuario() {
    }

    public ValoresUsuario(String tableID) {
        this.tableID = tableID;
    }

    public ValoresUsuario(String tableID, short fieldID, short indexID, String fldValue, String descr) {
        this.tableID = tableID;
        this.fieldID = fieldID;
        this.indexID = indexID;
        this.fldValue = fldValue;
        this.descr = descr;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public short getFieldID() {
        return fieldID;
    }

    public void setFieldID(short fieldID) {
        this.fieldID = fieldID;
    }

    public short getIndexID() {
        return indexID;
    }

    public void setIndexID(short indexID) {
        this.indexID = indexID;
    }

    public String getFldValue() {
        return fldValue;
    }

    public void setFldValue(String fldValue) {
        this.fldValue = fldValue;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableID != null ? tableID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValoresUsuario)) {
            return false;
        }
        ValoresUsuario other = (ValoresUsuario) object;
        if ((this.tableID == null && other.tableID != null) || (this.tableID != null && !this.tableID.equals(other.tableID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ValoresUsuario[ TableID=" + tableID + " ]";
    }
}

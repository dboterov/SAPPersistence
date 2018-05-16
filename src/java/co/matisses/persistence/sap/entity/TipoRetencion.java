package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OWHT")
@NamedQueries({
    @NamedQuery(name = "TipoRetencion.findAll", query = "SELECT t FROM TipoRetencion t")})
public class TipoRetencion implements Serializable, Comparable<TipoRetencion> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WTCode")
    private String wtCode;
    @Basic(optional = false)
    @Column(name = "WTName")
    private String wtName;
    @Basic(optional = false)
    @Column(name = "Category")
    private String category;
    @Basic(optional = false)
    @Column(name = "Inactive")
    private String inactive;

    public TipoRetencion() {
    }

    public TipoRetencion(String wtCode) {
        this.wtCode = wtCode;
    }

    public TipoRetencion(String wtCode, String wtName, String category, String inactive) {
        this.wtCode = wtCode;
        this.wtName = wtName;
        this.category = category;
        this.inactive = inactive;
    }

    public String getWtCode() {
        return wtCode;
    }

    public void setWtCode(String wtCode) {
        this.wtCode = wtCode;
    }

    public String getWtName() {
        return wtName;
    }

    public void setWtName(String wtName) {
        this.wtName = wtName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.wtCode);
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
        final TipoRetencion other = (TipoRetencion) obj;
        if (!Objects.equals(this.wtCode, other.wtCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoRetencion{" + "wtCode=" + wtCode + ", wtName=" + wtName + ", category=" + category + ", inactive=" + inactive + "}";
    }

    @Override
    public int compareTo(TipoRetencion o) {
        return this.wtName.compareTo(o.getWtName());
    }
}

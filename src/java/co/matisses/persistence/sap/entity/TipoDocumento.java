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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "[@BPCO_TD]")
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")})
public class TipoDocumento implements Serializable, Comparable<TipoDocumento> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Size(max = 30)
    @Column(name = "U_TicpoDocICA")
    private String uTicpoDocICA;

    public TipoDocumento() {
    }

    public TipoDocumento(String code) {
        this.code = code;
    }

    public TipoDocumento(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUTicpoDocICA() {
        return uTicpoDocICA;
    }

    public void setUTicpoDocICA(String uTicpoDocICA) {
        this.uTicpoDocICA = uTicpoDocICA;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.code);
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
        final TipoDocumento other = (TipoDocumento) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.TipoDocumento[ code=" + code + " ]";
    }

    @Override
    public int compareTo(TipoDocumento o) {
        return this.name.compareTo(o.getName());
    }
}

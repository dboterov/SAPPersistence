package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "CRD4")
@NamedQueries({
    @NamedQuery(name = "TipoRetencionCliente.findAll", query = "SELECT t FROM TipoRetencionCliente t")})
public class TipoRetencionCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoRetencionClientePK tipoRetencionClientePK;
    @Column(name = "LogInstanc")
    private Integer logInstanc;

    public TipoRetencionCliente() {
    }

    public TipoRetencionCliente(TipoRetencionClientePK tipoRetencionClientePK) {
        this.tipoRetencionClientePK = tipoRetencionClientePK;
    }

    public TipoRetencionCliente(TipoRetencionClientePK tipoRetencionClientePK, Integer logInstanc) {
        this.tipoRetencionClientePK = tipoRetencionClientePK;
        this.logInstanc = logInstanc;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public void setTipoRetencionClientePK(TipoRetencionClientePK tipoRetencionClientePK) {
        this.tipoRetencionClientePK = tipoRetencionClientePK;
    }

    public TipoRetencionClientePK getTipoRetencionClientePK() {
        return tipoRetencionClientePK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tipoRetencionClientePK);
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
        final TipoRetencionCliente other = (TipoRetencionCliente) obj;
        if (!Objects.equals(this.tipoRetencionClientePK, other.tipoRetencionClientePK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoRetencionCliente{" + "tipoRetencionClientePK=" + tipoRetencionClientePK + ", logInstanc=" + logInstanc + '}';
    }
}

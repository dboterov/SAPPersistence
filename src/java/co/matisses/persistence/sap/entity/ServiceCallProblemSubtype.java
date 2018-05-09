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
@Table(name = "OPST")
public class ServiceCallProblemSubtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProSubTyId")
    private Integer proSubTyId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;

    public ServiceCallProblemSubtype() {
    }

    public ServiceCallProblemSubtype(Integer proSubTyId) {
        this.proSubTyId = proSubTyId;
    }

    public ServiceCallProblemSubtype(Integer proSubTyId, String name, String description) {
        this.proSubTyId = proSubTyId;
        this.name = name;
        this.description = description;
    }

    public Integer getProSubTyId() {
        return proSubTyId;
    }

    public void setProSubTyId(Integer proSubTyId) {
        this.proSubTyId = proSubTyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proSubTyId != null ? proSubTyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallProblemSubtype)) {
            return false;
        }
        ServiceCallProblemSubtype other = (ServiceCallProblemSubtype) object;
        if ((this.proSubTyId == null && other.proSubTyId != null) || (this.proSubTyId != null && !this.proSubTyId.equals(other.proSubTyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallProblemSubtype[ proSubTyId=" + proSubTyId + " ]";
    }
}

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
@Table(name = "OSCP")
public class ServiceCallProblemTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "prblmTypID")
    private Integer problemTypeId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;

    public ServiceCallProblemTypes() {
    }

    public ServiceCallProblemTypes(Integer problemTypeId) {
        this.problemTypeId = problemTypeId;
    }

    public ServiceCallProblemTypes(Integer problemTypeId, String name, String description) {
        this.problemTypeId = problemTypeId;
        this.name = name;
        this.description = description;
    }

    public Integer getProblemTypeId() {
        return problemTypeId;
    }

    public void setProblemTypeId(Integer problemTypeId) {
        this.problemTypeId = problemTypeId;
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
        hash += (problemTypeId != null ? problemTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallProblemTypes)) {
            return false;
        }
        ServiceCallProblemTypes other = (ServiceCallProblemTypes) object;
        if ((this.problemTypeId == null && other.problemTypeId != null) || (this.problemTypeId != null && !this.problemTypeId.equals(other.problemTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallProblemTypes[ problemTypeId=" + problemTypeId + " ]";
    }
}

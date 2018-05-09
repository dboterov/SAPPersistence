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
@Table(name = "OSCT")
public class ServiceCallTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "callTypeID")
    private Integer callTypeId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;

    public ServiceCallTypes() {
    }

    public ServiceCallTypes(Integer callTypeId) {
        this.callTypeId = callTypeId;
    }

    public ServiceCallTypes(Integer callTypeId, String name, String description) {
        this.callTypeId = callTypeId;
        this.name = name;
        this.description = description;
    }

    public Integer getCallTypeId() {
        return callTypeId;
    }

    public void setCallTypeId(Integer callTypeId) {
        this.callTypeId = callTypeId;
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
        hash += (callTypeId != null ? callTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallTypes)) {
            return false;
        }
        ServiceCallTypes other = (ServiceCallTypes) object;
        if ((this.callTypeId == null && other.callTypeId != null) || (this.callTypeId != null && !this.callTypeId.equals(other.callTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallTypes[ callTypeId=" + callTypeId + " ]";
    }
}

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
@Table(name = "OSCS")
public class ServiceCallStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "statusID")
    private Integer statusId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;
    @Column(name = "Locked")
    private Character locked;

    public ServiceCallStatus() {
    }

    public ServiceCallStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public ServiceCallStatus(Integer statusId, String name, String description, Character locked) {
        this.statusId = statusId;
        this.name = name;
        this.description = description;
        this.locked = locked;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallStatus)) {
            return false;
        }
        ServiceCallStatus other = (ServiceCallStatus) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallStatus[ statusId=" + statusId + " ]";
    }
}

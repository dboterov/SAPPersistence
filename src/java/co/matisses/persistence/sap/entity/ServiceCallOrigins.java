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
@Table(name = "OSCO")
public class ServiceCallOrigins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "originID")
    private Integer originId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;
    @Column(name = "Locked")
    private Character locked;

    public ServiceCallOrigins() {
    }

    public ServiceCallOrigins(Integer originId) {
        this.originId = originId;
    }

    public ServiceCallOrigins(Integer originId, String name, String description, Character locked) {
        this.originId = originId;
        this.name = name;
        this.description = description;
        this.locked = locked;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
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
        hash += (originId != null ? originId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallOrigins)) {
            return false;
        }
        ServiceCallOrigins other = (ServiceCallOrigins) object;
        if ((this.originId == null && other.originId != null) || (this.originId != null && !this.originId.equals(other.originId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallOrigins[ originId=" + originId + " ]";
    }
}

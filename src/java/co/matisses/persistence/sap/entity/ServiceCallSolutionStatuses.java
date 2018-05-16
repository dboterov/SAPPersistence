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
@Table(name = "OSST")
public class ServiceCallSolutionStatuses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Number")
    private Long number;
    @Column(name = "Name")
    private String name;
    @Column(name = "Descriptio")
    private String description;

    public ServiceCallSolutionStatuses() {
    }

    public ServiceCallSolutionStatuses(Long number) {
        this.number = number;
    }

    public ServiceCallSolutionStatuses(Long number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallSolutionStatuses)) {
            return false;
        }
        ServiceCallSolutionStatuses other = (ServiceCallSolutionStatuses) object;
        if ((this.number == null && other.number != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallSolutionStatuses[ number=" + number + " ]";
    }
}

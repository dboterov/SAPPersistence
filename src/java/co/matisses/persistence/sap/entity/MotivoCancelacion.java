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
@Table(name = "OHTR")
public class MotivoCancelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reasonID")
    private Integer reasonID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "descriptio")
    private String descriptio;

    public MotivoCancelacion() {
    }

    public MotivoCancelacion(Integer reasonID) {
        this.reasonID = reasonID;
    }

    public MotivoCancelacion(Integer reasonID, String name, String descriptio) {
        this.reasonID = reasonID;
        this.name = name;
        this.descriptio = descriptio;
    }

    public Integer getReasonID() {
        return reasonID;
    }

    public void setReasonID(Integer reasonID) {
        this.reasonID = reasonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptio() {
        return descriptio;
    }

    public void setDescriptio(String descriptio) {
        this.descriptio = descriptio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reasonID != null ? reasonID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoCancelacion)) {
            return false;
        }
        MotivoCancelacion other = (MotivoCancelacion) object;
        if ((this.reasonID == null && other.reasonID != null) || (this.reasonID != null && !this.reasonID.equals(other.reasonID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.MotivoCancelacion[ reasonID=" + reasonID + " ]";
    }

}

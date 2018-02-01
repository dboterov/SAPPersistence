package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "HEM4")
public class EmpleoAnterior implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpleoAnteriorPK empleoAnteriorPK;
    @Column(name = "fromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "toDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "employer")
    private String employer;
    @Column(name = "position")
    private String position;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "LogInstanc")
    private Integer logInstanc;

    public EmpleoAnterior() {
    }

    public EmpleoAnterior(EmpleoAnteriorPK empleoAnteriorPK) {
        this.empleoAnteriorPK = empleoAnteriorPK;
    }

    public EmpleoAnterior(EmpleoAnteriorPK empleoAnteriorPK, Date fromDate, Date toDate, String employer, String position, String remarks, Integer logInstanc) {
        this.empleoAnteriorPK = empleoAnteriorPK;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employer = employer;
        this.position = position;
        this.remarks = remarks;
        this.logInstanc = logInstanc;
    }

    public EmpleoAnteriorPK getEmpleoAnteriorPK() {
        return empleoAnteriorPK;
    }

    public void setEmpleoAnteriorPK(EmpleoAnteriorPK empleoAnteriorPK) {
        this.empleoAnteriorPK = empleoAnteriorPK;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleoAnteriorPK != null ? empleoAnteriorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleoAnterior)) {
            return false;
        }
        EmpleoAnterior other = (EmpleoAnterior) object;
        if ((this.empleoAnteriorPK == null && other.empleoAnteriorPK != null) || (this.empleoAnteriorPK != null && !this.empleoAnteriorPK.equals(other.empleoAnteriorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.EmpleoAnterior[ empleoAnteriorPK=" + empleoAnteriorPK + " ]";
    }
}

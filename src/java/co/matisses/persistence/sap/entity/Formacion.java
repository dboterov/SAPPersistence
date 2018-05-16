package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "HEM2")
public class Formacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormacionPK formacionPK;
    @Column(name = "fromDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "toDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "type")
    private Integer type;
    @Column(name = "institute")
    private String institute;
    @Column(name = "major")
    private String major;
    @Column(name = "diploma")
    private String diploma;
    @Column(name = "LogInstanc")
    private Integer logInstanc;

    public Formacion() {
    }

    public Formacion(FormacionPK formacionPK) {
        this.formacionPK = formacionPK;
    }

    public Formacion(FormacionPK formacionPK, Date fromDate, Date toDate, Integer type, String institute, String major, String diploma, Integer logInstanc) {
        this.formacionPK = formacionPK;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.type = type;
        this.institute = institute;
        this.major = major;
        this.diploma = diploma;
        this.logInstanc = logInstanc;
    }

    public FormacionPK getFormacionPK() {
        return formacionPK;
    }

    public void setFormacionPK(FormacionPK formacionPK) {
        this.formacionPK = formacionPK;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
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
        hash += (formacionPK != null ? formacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.formacionPK == null && other.formacionPK != null) || (this.formacionPK != null && !this.formacionPK.equals(other.formacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Formacion[ formacionPK=" + formacionPK + " ]";
    }
}

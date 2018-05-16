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
@Table(name = "HEM3")
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ValoracionPK valoracionPK;
    @Column(name = "date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "reviewDesc")
    private String reviewDesc;
    @Column(name = "manager")
    private Integer manager;
    @Column(name = "grade")
    private String grade;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "LogInstanc")
    private Integer logInstanc;

    public Valoracion() {
    }

    public Valoracion(ValoracionPK valoracionPK) {
        this.valoracionPK = valoracionPK;
    }

    public Valoracion(ValoracionPK valoracionPK, Date date, String reviewDesc, Integer manager, String grade, String remarks, Integer logInstanc) {
        this.valoracionPK = valoracionPK;
        this.date = date;
        this.reviewDesc = reviewDesc;
        this.manager = manager;
        this.grade = grade;
        this.remarks = remarks;
        this.logInstanc = logInstanc;
    }

    public ValoracionPK getValoracionPK() {
        return valoracionPK;
    }

    public void setValoracionPK(ValoracionPK valoracionPK) {
        this.valoracionPK = valoracionPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        hash += (valoracionPK != null ? valoracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.valoracionPK == null && other.valoracionPK != null) || (this.valoracionPK != null && !this.valoracionPK.equals(other.valoracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Valoracion[ valoracionPK=" + valoracionPK + " ]";
    }
}

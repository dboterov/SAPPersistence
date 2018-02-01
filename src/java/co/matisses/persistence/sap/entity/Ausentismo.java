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
@Table(name = "HEM1")
public class Ausentismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AusentismoPK ausentismoPK;
    @Column(name = "fromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "toDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "reason")
    private String reason;
    @Column(name = "approvedBy")
    private String approvedBy;
    @Column(name = "cnfrmrNum")
    private Integer cnfrmrNum;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "U_type")
    private String utype;
    @Column(name = "U_horas")
    private Integer uhoras;
    @Column(name = "U_minutos")
    private Integer uminutos;
    @Column(name = "U_dias")
    private Integer udias;

    public Ausentismo() {
    }

    public Ausentismo(AusentismoPK ausentismoPK) {
        this.ausentismoPK = ausentismoPK;
    }

    public Ausentismo(AusentismoPK ausentismoPK, Date fromDate, Date toDate, String reason, String approvedBy, Integer cnfrmrNum, Integer logInstanc, String utype, Integer uhoras, Integer uminutos, Integer udias) {
        this.ausentismoPK = ausentismoPK;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.approvedBy = approvedBy;
        this.cnfrmrNum = cnfrmrNum;
        this.logInstanc = logInstanc;
        this.utype = utype;
        this.uhoras = uhoras;
        this.uminutos = uminutos;
        this.udias = udias;
    }

    public AusentismoPK getAusentismoPK() {
        return ausentismoPK;
    }

    public void setAusentismoPK(AusentismoPK ausentismoPK) {
        this.ausentismoPK = ausentismoPK;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getCnfrmrNum() {
        return cnfrmrNum;
    }

    public void setCnfrmrNum(Integer cnfrmrNum) {
        this.cnfrmrNum = cnfrmrNum;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Integer getUhoras() {
        return uhoras;
    }

    public void setUhoras(Integer uhoras) {
        this.uhoras = uhoras;
    }

    public Integer getUminutos() {
        return uminutos;
    }

    public void setUminutos(Integer uminutos) {
        this.uminutos = uminutos;
    }

    public Integer getUdias() {
        return udias;
    }

    public void setUdias(Integer udias) {
        this.udias = udias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ausentismoPK != null ? ausentismoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ausentismo)) {
            return false;
        }
        Ausentismo other = (Ausentismo) object;
        if ((this.ausentismoPK == null && other.ausentismoPK != null) || (this.ausentismoPK != null && !this.ausentismoPK.equals(other.ausentismoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Ausentismo[ ausentismoPK=" + ausentismoPK + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "OSLT")
public class ServiceCallSolutions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SltCode")
    private Integer sltCode;
    @Column(name = "ItemCode")
    private String itemCode;
    @Column(name = "StatusNum")
    private Integer statusNum;
    @Column(name = "Owner")
    private Integer owner;
    @Column(name = "CreatedBy")
    private Integer createdBy;
    @Column(name = "DateCreate")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Column(name = "UpdateBy")
    private Integer updateBy;
    @Column(name = "DateUpdate")
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "Symptom")
    private String symptom;
    @Column(name = "Cause")
    private String cause;
    @Column(name = "Descriptio")
    private String descriptio;
    @Column(name = "Attachment")
    private String attachment;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "Transfered")
    private Character transfered;
    @Column(name = "Instance")
    private Integer instance;

    public ServiceCallSolutions() {
    }

    public ServiceCallSolutions(Integer sltCode) {
        this.sltCode = sltCode;
    }

    public ServiceCallSolutions(Integer sltCode, String itemCode, Integer statusNum, Integer owner, Integer createdBy, Date dateCreate, Integer updateBy, Date dateUpdate,
            String subject, String symptom, String cause, String descriptio, String attachment, Integer atcEntry, Character transfered, Integer instance) {
        this.sltCode = sltCode;
        this.itemCode = itemCode;
        this.statusNum = statusNum;
        this.owner = owner;
        this.createdBy = createdBy;
        this.dateCreate = dateCreate;
        this.updateBy = updateBy;
        this.dateUpdate = dateUpdate;
        this.subject = subject;
        this.symptom = symptom;
        this.cause = cause;
        this.descriptio = descriptio;
        this.attachment = attachment;
        this.atcEntry = atcEntry;
        this.transfered = transfered;
        this.instance = instance;
    }

    public Integer getSltCode() {
        return sltCode;
    }

    public void setSltCode(Integer sltCode) {
        this.sltCode = sltCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Integer statusNum) {
        this.statusNum = statusNum;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescriptio() {
        return descriptio;
    }

    public void setDescriptio(String descriptio) {
        this.descriptio = descriptio;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Integer atcEntry) {
        this.atcEntry = atcEntry;
    }

    public Character getTransfered() {
        return transfered;
    }

    public void setTransfered(Character transfered) {
        this.transfered = transfered;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sltCode != null ? sltCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallSolutions)) {
            return false;
        }
        ServiceCallSolutions other = (ServiceCallSolutions) object;
        if ((this.sltCode == null && other.sltCode != null) || (this.sltCode != null && !this.sltCode.equals(other.sltCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallSolutions[ sltCode=" + sltCode + " ]";
    }
}

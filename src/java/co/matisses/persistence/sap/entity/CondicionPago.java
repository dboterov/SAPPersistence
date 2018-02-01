package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "OCTG")
public class CondicionPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GroupNum")
    private Integer groupNum;
    @Basic(optional = false)
    @Column(name = "PymntGroup")
    private String pymntGroup;
    @Basic(optional = true)
    @Column(name = "PayDuMonth")
    private Character payDuMonth;
    @Basic(optional = true)
    @Column(name = "ExtraMonth")
    private Integer extraMonth;
    @Basic(optional = true)
    @Column(name = "ExtraDays")
    private Integer extraDays;
    @Basic(optional = true)
    @Column(name = "PaymntsNum")
    private Integer paymntsNum;
    @Basic(optional = true)
    @Column(name = "CredLimit")
    private BigDecimal credLimit;
    @Basic(optional = true)
    @Column(name = "VolumDscnt")
    private BigDecimal volumDscnt;
    @Basic(optional = true)
    @Column(name = "LatePyChrg")
    private BigDecimal latePyChrg;
    @Basic(optional = true)
    @Column(name = "ObligLimit")
    private BigDecimal obligLimit;
    @Basic(optional = true)
    @Column(name = "ListNum")
    private Integer listNum;
    @Basic(optional = true)
    @Column(name = "Payments")
    private Character payments;
    @Basic(optional = true)
    @Column(name = "NumOfPmnts")
    private Integer numOfPmnts;
    @Basic(optional = true)
    @Column(name = "Payment1")
    private BigDecimal payment1;
    @Basic(optional = true)
    @Column(name = "DataSource")
    private Character dataSource;
    @Basic(optional = true)
    @Column(name = "UserSign")
    private Integer userSign;
    @Basic(optional = true)
    @Column(name = "OpenRcpt")
    private Character openRcpt;
    @Basic(optional = true)
    @Column(name = "DiscCode")
    private String discCode;
    @Basic(optional = true)
    @Column(name = "DunningCod")
    private String dunningCod;
    @Basic(optional = true)
    @Column(name = "BslineDate")
    private Character bslineDate;
    @Basic(optional = true)
    @Column(name = "InstNum")
    private Integer instNum;
    @Basic(optional = true)
    @Column(name = "TolDays")
    private Integer tolDays;
    @Basic(optional = true)
    @Column(name = "VATFirst")
    private Character vATFirst;
    @Basic(optional = true)
    @Column(name = "CrdMthd")
    private Character crdMthd;

    public CondicionPago() {
    }

    public CondicionPago(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public CondicionPago(Integer groupNum, String pymntGroup, Character payDuMonth, Integer extraMonth, Integer extraDays, Integer paymntsNum, BigDecimal credLimit,
            BigDecimal volumDscnt, BigDecimal latePyChrg, BigDecimal obligLimit, Integer listNum, Character payments, Integer numOfPmnts, BigDecimal payment1,
            Character dataSource, Integer userSign, Character openRcpt, String discCode, String dunningCod, Character bslineDate, Integer instNum, Integer tolDays, Character vATFirst, Character crdMthd) {
        this.groupNum = groupNum;
        this.pymntGroup = pymntGroup;
        this.payDuMonth = payDuMonth;
        this.extraMonth = extraMonth;
        this.extraDays = extraDays;
        this.paymntsNum = paymntsNum;
        this.credLimit = credLimit;
        this.volumDscnt = volumDscnt;
        this.latePyChrg = latePyChrg;
        this.obligLimit = obligLimit;
        this.listNum = listNum;
        this.payments = payments;
        this.numOfPmnts = numOfPmnts;
        this.payment1 = payment1;
        this.dataSource = dataSource;
        this.userSign = userSign;
        this.openRcpt = openRcpt;
        this.discCode = discCode;
        this.dunningCod = dunningCod;
        this.bslineDate = bslineDate;
        this.instNum = instNum;
        this.tolDays = tolDays;
        this.vATFirst = vATFirst;
        this.crdMthd = crdMthd;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getPymntGroup() {
        return pymntGroup;
    }

    public void setPymntGroup(String pymntGroup) {
        this.pymntGroup = pymntGroup;
    }

    public Character getPayDuMonth() {
        return payDuMonth;
    }

    public void setPayDuMonth(Character payDuMonth) {
        this.payDuMonth = payDuMonth;
    }

    public Integer getExtraMonth() {
        return extraMonth;
    }

    public void setExtraMonth(Integer extraMonth) {
        this.extraMonth = extraMonth;
    }

    public Integer getExtraDays() {
        return extraDays;
    }

    public void setExtraDays(Integer extraDays) {
        this.extraDays = extraDays;
    }

    public Integer getPaymntsNum() {
        return paymntsNum;
    }

    public void setPaymntsNum(Integer paymntsNum) {
        this.paymntsNum = paymntsNum;
    }

    public BigDecimal getCredLimit() {
        return credLimit;
    }

    public void setCredLimit(BigDecimal credLimit) {
        this.credLimit = credLimit;
    }

    public BigDecimal getVolumDscnt() {
        return volumDscnt;
    }

    public void setVolumDscnt(BigDecimal volumDscnt) {
        this.volumDscnt = volumDscnt;
    }

    public BigDecimal getLatePyChrg() {
        return latePyChrg;
    }

    public void setLatePyChrg(BigDecimal latePyChrg) {
        this.latePyChrg = latePyChrg;
    }

    public BigDecimal getObligLimit() {
        return obligLimit;
    }

    public void setObligLimit(BigDecimal obligLimit) {
        this.obligLimit = obligLimit;
    }

    public Integer getListNum() {
        return listNum;
    }

    public void setListNum(Integer listNum) {
        this.listNum = listNum;
    }

    public Character getPayments() {
        return payments;
    }

    public void setPayments(Character payments) {
        this.payments = payments;
    }

    public Integer getNumOfPmnts() {
        return numOfPmnts;
    }

    public void setNumOfPmnts(Integer numOfPmnts) {
        this.numOfPmnts = numOfPmnts;
    }

    public BigDecimal getPayment1() {
        return payment1;
    }

    public void setPayment1(BigDecimal payment1) {
        this.payment1 = payment1;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getUserSign() {
        return userSign;
    }

    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
    }

    public Character getOpenRcpt() {
        return openRcpt;
    }

    public void setOpenRcpt(Character openRcpt) {
        this.openRcpt = openRcpt;
    }

    public String getDiscCode() {
        return discCode;
    }

    public void setDiscCode(String discCode) {
        this.discCode = discCode;
    }

    public String getDunningCod() {
        return dunningCod;
    }

    public void setDunningCod(String dunningCod) {
        this.dunningCod = dunningCod;
    }

    public Character getBslineDate() {
        return bslineDate;
    }

    public void setBslineDate(Character bslineDate) {
        this.bslineDate = bslineDate;
    }

    public Integer getInstNum() {
        return instNum;
    }

    public void setInstNum(Integer instNum) {
        this.instNum = instNum;
    }

    public Integer getTolDays() {
        return tolDays;
    }

    public void setTolDays(Integer tolDays) {
        this.tolDays = tolDays;
    }

    public Character getvATFirst() {
        return vATFirst;
    }

    public void setvATFirst(Character vATFirst) {
        this.vATFirst = vATFirst;
    }

    public Character getCrdMthd() {
        return crdMthd;
    }

    public void setCrdMthd(Character crdMthd) {
        this.crdMthd = crdMthd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupNum != null ? groupNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CondicionPago)) {
            return false;
        }
        CondicionPago other = (CondicionPago) object;
        if ((this.groupNum == null && other.groupNum != null) || (this.groupNum != null && !this.groupNum.equals(other.groupNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.CondicionPago[ GroupNum=" + groupNum + " ]";
    }
}

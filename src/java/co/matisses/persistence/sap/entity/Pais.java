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
@Table(name = "OCRY")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "AddrFormat")
    private Short addrFormat;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "IsEC")
    private Character isEC;
    @Column(name = "ReportCode")
    private String reportCode;
    @Column(name = "TaxIdDigts")
    private Short taxIdDigts;
    @Column(name = "BnkCodDgts")
    private Integer bnkCodDgts;
    @Column(name = "BnkBchDgts")
    private Integer bnkBchDgts;
    @Column(name = "BnkActDgts")
    private Integer bnkActDgts;
    @Column(name = "BnkCtKDgts")
    private Integer bnkCtKDgts;
    @Column(name = "ValDomAcct")
    private String valDomAcct;
    @Column(name = "ValIban")
    private Character valIban;
    @Column(name = "IsBlackLst")
    private Character isBlackLst;
    @Column(name = "UICCode")
    private String uICCode;

    public Pais() {
    }

    public Pais(String code) {
        this.code = code;
    }

    public Pais(String code, String name, Short addrFormat, Short userSign, Character isEC, String reportCode, Short taxIdDigts, Integer bnkCodDgts, Integer bnkBchDgts,
            Integer bnkActDgts, Integer bnkCtKDgts, String valDomAcct, Character valIban, Character isBlackLst, String uICCode) {
        this.code = code;
        this.name = name;
        this.addrFormat = addrFormat;
        this.userSign = userSign;
        this.isEC = isEC;
        this.reportCode = reportCode;
        this.taxIdDigts = taxIdDigts;
        this.bnkCodDgts = bnkCodDgts;
        this.bnkBchDgts = bnkBchDgts;
        this.bnkActDgts = bnkActDgts;
        this.bnkCtKDgts = bnkCtKDgts;
        this.valDomAcct = valDomAcct;
        this.valIban = valIban;
        this.isBlackLst = isBlackLst;
        this.uICCode = uICCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAddrFormat() {
        return addrFormat;
    }

    public void setAddrFormat(Short addrFormat) {
        this.addrFormat = addrFormat;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Character getIsEC() {
        return isEC;
    }

    public void setIsEC(Character isEC) {
        this.isEC = isEC;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Short getTaxIdDigts() {
        return taxIdDigts;
    }

    public void setTaxIdDigts(Short taxIdDigts) {
        this.taxIdDigts = taxIdDigts;
    }

    public Integer getBnkCodDgts() {
        return bnkCodDgts;
    }

    public void setBnkCodDgts(Integer bnkCodDgts) {
        this.bnkCodDgts = bnkCodDgts;
    }

    public Integer getBnkBchDgts() {
        return bnkBchDgts;
    }

    public void setBnkBchDgts(Integer bnkBchDgts) {
        this.bnkBchDgts = bnkBchDgts;
    }

    public Integer getBnkActDgts() {
        return bnkActDgts;
    }

    public void setBnkActDgts(Integer bnkActDgts) {
        this.bnkActDgts = bnkActDgts;
    }

    public Integer getBnkCtKDgts() {
        return bnkCtKDgts;
    }

    public void setBnkCtKDgts(Integer bnkCtKDgts) {
        this.bnkCtKDgts = bnkCtKDgts;
    }

    public String getValDomAcct() {
        return valDomAcct;
    }

    public void setValDomAcct(String valDomAcct) {
        this.valDomAcct = valDomAcct;
    }

    public Character getValIban() {
        return valIban;
    }

    public void setValIban(Character valIban) {
        this.valIban = valIban;
    }

    public Character getIsBlackLst() {
        return isBlackLst;
    }

    public void setIsBlackLst(Character isBlackLst) {
        this.isBlackLst = isBlackLst;
    }

    public String getuICCode() {
        return uICCode;
    }

    public void setuICCode(String uICCode) {
        this.uICCode = uICCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Pais[ Code=" + code + " ]";
    }
}

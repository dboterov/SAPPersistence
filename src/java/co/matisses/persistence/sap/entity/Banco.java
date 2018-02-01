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
@Table(name = "ODSC")
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AbsEntry")
    private Integer absEntry;
    @Column(name = "BankCode")
    private String bankCode;
    @Column(name = "BankName")
    private String bankName;
    @Column(name = "DfltAcct")
    private String dfltAcct;
    @Column(name = "DfltBranch")
    private String dfltBranch;
    @Column(name = "NextChckNo")
    private Integer nextChckNo;
    @Column(name = "Locked")
    private Character locked;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "SwiftNum")
    private String swiftNum;
    @Column(name = "IBAN")
    private String iBAN;
    @Column(name = "CountryCod")
    private String countryCod;
    @Column(name = "PostOffice")
    private Character postOffice;
    @Column(name = "AliasName")
    private String aliasName;
    @Column(name = "DfltActKey")
    private Integer dfltActKey;
    @Column(name = "NextNum")
    private Integer nextNum;
    @Column(name = "BsPstDate")
    private Character bsPstDate;
    @Column(name = "BsValDate")
    private Character bsValDate;
    @Column(name = "BnkOpCode")
    private Integer bnkOpCode;
    @Column(name = "BsDocDate")
    private Character bsDocDate;

    public Banco() {
    }

    public Banco(Integer absEntry) {
        this.absEntry = absEntry;
    }

    public Banco(Integer absEntry, String bankCode, String bankName, String dfltAcct, String dfltBranch, Integer nextChckNo, Character locked, Character dataSource, Short userSign,
            String swiftNum, String iBAN, String countryCod, Character postOffice, String aliasName, Integer dfltActKey, Integer nextNum, Character bsPstDate, Character bsValDate,
            Integer bnkOpCode, Character bsDocDate) {
        this.absEntry = absEntry;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.dfltAcct = dfltAcct;
        this.dfltBranch = dfltBranch;
        this.nextChckNo = nextChckNo;
        this.locked = locked;
        this.dataSource = dataSource;
        this.userSign = userSign;
        this.swiftNum = swiftNum;
        this.iBAN = iBAN;
        this.countryCod = countryCod;
        this.postOffice = postOffice;
        this.aliasName = aliasName;
        this.dfltActKey = dfltActKey;
        this.nextNum = nextNum;
        this.bsPstDate = bsPstDate;
        this.bsValDate = bsValDate;
        this.bnkOpCode = bnkOpCode;
        this.bsDocDate = bsDocDate;
    }

    public Integer getAbsEntry() {
        return absEntry;
    }

    public void setAbsEntry(Integer absEntry) {
        this.absEntry = absEntry;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDfltAcct() {
        return dfltAcct;
    }

    public void setDfltAcct(String dfltAcct) {
        this.dfltAcct = dfltAcct;
    }

    public String getDfltBranch() {
        return dfltBranch;
    }

    public void setDfltBranch(String dfltBranch) {
        this.dfltBranch = dfltBranch;
    }

    public Integer getNextChckNo() {
        return nextChckNo;
    }

    public void setNextChckNo(Integer nextChckNo) {
        this.nextChckNo = nextChckNo;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public String getSwiftNum() {
        return swiftNum;
    }

    public void setSwiftNum(String swiftNum) {
        this.swiftNum = swiftNum;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getCountryCod() {
        return countryCod;
    }

    public void setCountryCod(String countryCod) {
        this.countryCod = countryCod;
    }

    public Character getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(Character postOffice) {
        this.postOffice = postOffice;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getDfltActKey() {
        return dfltActKey;
    }

    public void setDfltActKey(Integer dfltActKey) {
        this.dfltActKey = dfltActKey;
    }

    public Integer getNextNum() {
        return nextNum;
    }

    public void setNextNum(Integer nextNum) {
        this.nextNum = nextNum;
    }

    public Character getBsPstDate() {
        return bsPstDate;
    }

    public void setBsPstDate(Character bsPstDate) {
        this.bsPstDate = bsPstDate;
    }

    public Character getBsValDate() {
        return bsValDate;
    }

    public void setBsValDate(Character bsValDate) {
        this.bsValDate = bsValDate;
    }

    public Integer getBnkOpCode() {
        return bnkOpCode;
    }

    public void setBnkOpCode(Integer bnkOpCode) {
        this.bnkOpCode = bnkOpCode;
    }

    public Character getBsDocDate() {
        return bsDocDate;
    }

    public void setBsDocDate(Character bsDocDate) {
        this.bsDocDate = bsDocDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (absEntry != null ? absEntry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.absEntry == null && other.absEntry != null) || (this.absEntry != null && !this.absEntry.equals(other.absEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Banco[ absEntry=" + absEntry + " ]";
    }
}

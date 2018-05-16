package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OHEM")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empID")
    private Integer empID;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "sex")
    private Character sex;
    @Column(name = "jobTitle")
    private String jobTitle;
    @Column(name = "type")
    private Integer type;
    @Column(name = "dept")
    private Short dept;
    @Column(name = "branch")
    private Short branch;
    @Column(name = "workStreet")
    private String workStreet;
    @Column(name = "workBlock")
    private String workBlock;
    @Column(name = "workZip")
    private String workZip;
    @Column(name = "workCity")
    private String workCity;
    @Column(name = "workCounty")
    private String workCounty;
    @Column(name = "workCountr")
    private String workCountr;
    @Column(name = "workState")
    private String workState;
    @Column(name = "manager")
    private Integer manager;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "salesPrson")
    private Integer salesPrson;
    @Column(name = "officeTel")
    private String officeTel;
    @Column(name = "officeExt")
    private String officeExt;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "pager")
    private String pager;
    @Column(name = "homeTel")
    private String homeTel;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "status")
    private Integer status;
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "salaryUnit")
    private Character salaryUnit;
    @Column(name = "emplCost")
    private BigDecimal emplCost;
    @Column(name = "empCostUnt")
    private Character empCostUnt;
    @Column(name = "termDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date termDate;
    @Column(name = "termReason")
    private Integer termReason;
    @Column(name = "bankCode")
    private String bankCode;
    @Column(name = "bankBranch")
    private String bankBranch;
    @Column(name = "bankBranNo")
    private String bankBranNo;
    @Column(name = "bankAcount")
    private String bankAcount;
    @Column(name = "homeStreet")
    private String homeStreet;
    @Column(name = "homeBlock")
    private String homeBlock;
    @Column(name = "homeZip")
    private String homeZip;
    @Column(name = "homeCity")
    private String homeCity;
    @Column(name = "homeCounty")
    private String homeCounty;
    @Column(name = "homeCountr")
    private String homeCountr;
    @Column(name = "homeState")
    private String homeState;
    @Column(name = "birthDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(name = "brthCountr")
    private String brthCountr;
    @Column(name = "martStatus")
    private Character martStatus;
    @Column(name = "nChildren")
    private Short nChildren;
    @Column(name = "govID")
    private String govID;
    @Column(name = "citizenshp")
    private String citizenshp;
    @Column(name = "passportNo")
    private String passportNo;
    @Column(name = "passportEx")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passportEx;
    @Column(name = "picture")
    private String picture;
    @Column(name = "remark")
    private String remark;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "salaryCurr")
    private String salaryCurr;
    @Column(name = "empCostCur")
    private String empCostCur;
    @Column(name = "WorkBuild")
    private String workBuild;
    @Column(name = "HomeBuild")
    private String homeBuild;
    @Column(name = "position")
    private Integer position;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "AddrTypeW")
    private String addrTypeW;
    @Column(name = "AddrTypeH")
    private String addrTypeH;
    @Column(name = "StreetNoW")
    private String streetNoW;
    @Column(name = "StreetNoH")
    private String streetNoH;
    @Column(name = "DispMidNam")
    private Character dispMidNam;
    @Column(name = "NamePos")
    private Character namePos;
    @Column(name = "DispComma")
    private Character dispComma;
    @Column(name = "CostCenter")
    private String costCenter;
    @Column(name = "CompanyNum")
    private String companyNum;
    @Column(name = "VacPreYear")
    private Integer vacPreYear;
    @Column(name = "VacCurYear")
    private Integer vacCurYear;
    @Column(name = "MunKey")
    private String munKey;
    @Column(name = "TaxClass")
    private String taxClass;
    @Column(name = "InTaxLiabi")
    private String inTaxLiabi;
    @Column(name = "EmTaxCCode")
    private String emTaxCCode;
    @Column(name = "RelPartner")
    private String relPartner;
    @Column(name = "ExemptAmnt")
    private BigDecimal exemptAmnt;
    @Column(name = "ExemptUnit")
    private String exemptUnit;
    @Column(name = "AddiAmnt")
    private BigDecimal addiAmnt;
    @Column(name = "AddiUnit")
    private String addiUnit;
    @Column(name = "TaxOName")
    private String taxOName;
    @Column(name = "TaxONum")
    private String taxONum;
    @Column(name = "HeaInsName")
    private String heaInsName;
    @Column(name = "HeaInsCode")
    private String heaInsCode;
    @Column(name = "HeaInsType")
    private String heaInsType;
    @Column(name = "SInsurNum")
    private String sInsurNum;
    @Column(name = "StatusOfP")
    private String statusOfP;
    @Column(name = "StatusOfE")
    private String statusOfE;
    @Column(name = "BCodeDateV")
    private String bCodeDateV;
    @Column(name = "DevBAOwner")
    private Character devBAOwner;
    @Column(name = "FNameSP")
    private String fNameSP;
    @Column(name = "SurnameSP")
    private String surnameSP;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "UserSign2")
    private Short userSign2;
    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "PersGroup")
    private String persGroup;
    @Column(name = "JTCode")
    private String jTCode;
    @Column(name = "ExtEmpNo")
    private String extEmpNo;
    @Column(name = "BirthPlace")
    private String birthPlace;
    @Column(name = "PymMeth")
    private String pymMeth;
    @Column(name = "ExemptCurr")
    private String exemptCurr;
    @Column(name = "AddiCurr")
    private String addiCurr;
    @Column(name = "STDCode")
    private Integer sTDCode;
    @Column(name = "FatherName")
    private String fatherName;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "CRC")
    private String crc;
    @Column(name = "ContResp")
    private Character contResp;
    @Column(name = "RepLegal")
    private Character repLegal;
    @Column(name = "DirfDeclar")
    private Character dirfDeclar;
    @Column(name = "UF_CRC")
    private String ufCrc;
    @Column(name = "IDType")
    private String iDType;
    @Column(name = "Active")
    private Character active;
    @Column(name = "BPLId")
    private Integer bPLId;
    @Column(name = "ManualNUM")
    private String manualNUM;
    @Column(name = "PassIssue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passIssue;
    @Column(name = "PassIssuer")
    private String passIssuer;
    @Column(name = "QualCode")
    private String qualCode;
    @Column(name = "PRWebAccss")
    private Character pRWebAccss;
    @Column(name = "PrePRWeb")
    private Character prePRWeb;
    @Column(name = "U_U_Arp")
    private String uUArp;
    @Column(name = "U_U_Eps")
    private String uUEps;
    @Column(name = "U_U_CaComp")
    private String uUCaComp;
    @Column(name = "U_U_Pens")
    private String uUPens;
    @Column(name = "U_Cert")
    private String uCert;
    @Column(name = "U_Cesantias")
    private String uCesantias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "U_tipocontrato")
    private String utipocontrato;
    @Column(name = "U_codigoRevisado")
    private String ucodigoRevisado;
    @Column(name = "U_CorreoCorp")
    private String uCorreoCorp;
    @Column(name = "U_UltCursoAltura")
    @Temporal(TemporalType.DATE)
    private Date uUltCursoAltura;
    @Column(name = "U_MotivoCursoAlt")
    private String uMotivoCursoAlt;

    public Empleado() {
    }

    public Empleado(Integer empID) {
        this.empID = empID;
    }

    public Empleado(Integer empID, String utipocontrato) {
        this.empID = empID;
        this.utipocontrato = utipocontrato;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Short getDept() {
        return dept;
    }

    public void setDept(Short dept) {
        this.dept = dept;
    }

    public Short getBranch() {
        return branch;
    }

    public void setBranch(Short branch) {
        this.branch = branch;
    }

    public String getWorkStreet() {
        return workStreet;
    }

    public void setWorkStreet(String workStreet) {
        this.workStreet = workStreet;
    }

    public String getWorkBlock() {
        return workBlock;
    }

    public void setWorkBlock(String workBlock) {
        this.workBlock = workBlock;
    }

    public String getWorkZip() {
        return workZip;
    }

    public void setWorkZip(String workZip) {
        this.workZip = workZip;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkCounty() {
        return workCounty;
    }

    public void setWorkCounty(String workCounty) {
        this.workCounty = workCounty;
    }

    public String getWorkCountr() {
        return workCountr;
    }

    public void setWorkCountr(String workCountr) {
        this.workCountr = workCountr;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSalesPrson() {
        return salesPrson;
    }

    public void setSalesPrson(Integer salesPrson) {
        this.salesPrson = salesPrson;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeExt() {
        return officeExt;
    }

    public void setOfficeExt(String officeExt) {
        this.officeExt = officeExt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Character getSalaryUnit() {
        return salaryUnit;
    }

    public void setSalaryUnit(Character salaryUnit) {
        this.salaryUnit = salaryUnit;
    }

    public BigDecimal getEmplCost() {
        return emplCost;
    }

    public void setEmplCost(BigDecimal emplCost) {
        this.emplCost = emplCost;
    }

    public Character getEmpCostUnt() {
        return empCostUnt;
    }

    public void setEmpCostUnt(Character empCostUnt) {
        this.empCostUnt = empCostUnt;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public Integer getTermReason() {
        return termReason;
    }

    public void setTermReason(Integer termReason) {
        this.termReason = termReason;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankBranNo() {
        return bankBranNo;
    }

    public void setBankBranNo(String bankBranNo) {
        this.bankBranNo = bankBranNo;
    }

    public String getBankAcount() {
        return bankAcount;
    }

    public void setBankAcount(String bankAcount) {
        this.bankAcount = bankAcount;
    }

    public String getHomeStreet() {
        return homeStreet;
    }

    public void setHomeStreet(String homeStreet) {
        this.homeStreet = homeStreet;
    }

    public String getHomeBlock() {
        return homeBlock;
    }

    public void setHomeBlock(String homeBlock) {
        this.homeBlock = homeBlock;
    }

    public String getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(String homeCounty) {
        this.homeCounty = homeCounty;
    }

    public String getHomeCountr() {
        return homeCountr;
    }

    public void setHomeCountr(String homeCountr) {
        this.homeCountr = homeCountr;
    }

    public String getHomeState() {
        return homeState;
    }

    public void setHomeState(String homeState) {
        this.homeState = homeState;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBrthCountr() {
        return brthCountr;
    }

    public void setBrthCountr(String brthCountr) {
        this.brthCountr = brthCountr;
    }

    public Character getMartStatus() {
        return martStatus;
    }

    public void setMartStatus(Character martStatus) {
        this.martStatus = martStatus;
    }

    public Short getNChildren() {
        return nChildren;
    }

    public void setNChildren(Short nChildren) {
        this.nChildren = nChildren;
    }

    public String getGovID() {
        return govID;
    }

    public void setGovID(String govID) {
        this.govID = govID;
    }

    public String getCitizenshp() {
        return citizenshp;
    }

    public void setCitizenshp(String citizenshp) {
        this.citizenshp = citizenshp;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Date getPassportEx() {
        return passportEx;
    }

    public void setPassportEx(Date passportEx) {
        this.passportEx = passportEx;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getSalaryCurr() {
        return salaryCurr;
    }

    public void setSalaryCurr(String salaryCurr) {
        this.salaryCurr = salaryCurr;
    }

    public String getEmpCostCur() {
        return empCostCur;
    }

    public void setEmpCostCur(String empCostCur) {
        this.empCostCur = empCostCur;
    }

    public String getWorkBuild() {
        return workBuild;
    }

    public void setWorkBuild(String workBuild) {
        this.workBuild = workBuild;
    }

    public String getHomeBuild() {
        return homeBuild;
    }

    public void setHomeBuild(String homeBuild) {
        this.homeBuild = homeBuild;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Integer atcEntry) {
        this.atcEntry = atcEntry;
    }

    public String getAddrTypeW() {
        return addrTypeW;
    }

    public void setAddrTypeW(String addrTypeW) {
        this.addrTypeW = addrTypeW;
    }

    public String getAddrTypeH() {
        return addrTypeH;
    }

    public void setAddrTypeH(String addrTypeH) {
        this.addrTypeH = addrTypeH;
    }

    public String getStreetNoW() {
        return streetNoW;
    }

    public void setStreetNoW(String streetNoW) {
        this.streetNoW = streetNoW;
    }

    public String getStreetNoH() {
        return streetNoH;
    }

    public void setStreetNoH(String streetNoH) {
        this.streetNoH = streetNoH;
    }

    public Character getDispMidNam() {
        return dispMidNam;
    }

    public void setDispMidNam(Character dispMidNam) {
        this.dispMidNam = dispMidNam;
    }

    public Character getNamePos() {
        return namePos;
    }

    public void setNamePos(Character namePos) {
        this.namePos = namePos;
    }

    public Character getDispComma() {
        return dispComma;
    }

    public void setDispComma(Character dispComma) {
        this.dispComma = dispComma;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public Integer getVacPreYear() {
        return vacPreYear;
    }

    public void setVacPreYear(Integer vacPreYear) {
        this.vacPreYear = vacPreYear;
    }

    public Integer getVacCurYear() {
        return vacCurYear;
    }

    public void setVacCurYear(Integer vacCurYear) {
        this.vacCurYear = vacCurYear;
    }

    public String getMunKey() {
        return munKey;
    }

    public void setMunKey(String munKey) {
        this.munKey = munKey;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getInTaxLiabi() {
        return inTaxLiabi;
    }

    public void setInTaxLiabi(String inTaxLiabi) {
        this.inTaxLiabi = inTaxLiabi;
    }

    public String getEmTaxCCode() {
        return emTaxCCode;
    }

    public void setEmTaxCCode(String emTaxCCode) {
        this.emTaxCCode = emTaxCCode;
    }

    public String getRelPartner() {
        return relPartner;
    }

    public void setRelPartner(String relPartner) {
        this.relPartner = relPartner;
    }

    public BigDecimal getExemptAmnt() {
        return exemptAmnt;
    }

    public void setExemptAmnt(BigDecimal exemptAmnt) {
        this.exemptAmnt = exemptAmnt;
    }

    public String getExemptUnit() {
        return exemptUnit;
    }

    public void setExemptUnit(String exemptUnit) {
        this.exemptUnit = exemptUnit;
    }

    public BigDecimal getAddiAmnt() {
        return addiAmnt;
    }

    public void setAddiAmnt(BigDecimal addiAmnt) {
        this.addiAmnt = addiAmnt;
    }

    public String getAddiUnit() {
        return addiUnit;
    }

    public void setAddiUnit(String addiUnit) {
        this.addiUnit = addiUnit;
    }

    public String getTaxOName() {
        return taxOName;
    }

    public void setTaxOName(String taxOName) {
        this.taxOName = taxOName;
    }

    public String getTaxONum() {
        return taxONum;
    }

    public void setTaxONum(String taxONum) {
        this.taxONum = taxONum;
    }

    public String getHeaInsName() {
        return heaInsName;
    }

    public void setHeaInsName(String heaInsName) {
        this.heaInsName = heaInsName;
    }

    public String getHeaInsCode() {
        return heaInsCode;
    }

    public void setHeaInsCode(String heaInsCode) {
        this.heaInsCode = heaInsCode;
    }

    public String getHeaInsType() {
        return heaInsType;
    }

    public void setHeaInsType(String heaInsType) {
        this.heaInsType = heaInsType;
    }

    public String getSInsurNum() {
        return sInsurNum;
    }

    public void setSInsurNum(String sInsurNum) {
        this.sInsurNum = sInsurNum;
    }

    public String getStatusOfP() {
        return statusOfP;
    }

    public void setStatusOfP(String statusOfP) {
        this.statusOfP = statusOfP;
    }

    public String getStatusOfE() {
        return statusOfE;
    }

    public void setStatusOfE(String statusOfE) {
        this.statusOfE = statusOfE;
    }

    public String getBCodeDateV() {
        return bCodeDateV;
    }

    public void setBCodeDateV(String bCodeDateV) {
        this.bCodeDateV = bCodeDateV;
    }

    public Character getDevBAOwner() {
        return devBAOwner;
    }

    public void setDevBAOwner(Character devBAOwner) {
        this.devBAOwner = devBAOwner;
    }

    public String getFNameSP() {
        return fNameSP;
    }

    public void setFNameSP(String fNameSP) {
        this.fNameSP = fNameSP;
    }

    public String getSurnameSP() {
        return surnameSP;
    }

    public void setSurnameSP(String surnameSP) {
        this.surnameSP = surnameSP;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPersGroup() {
        return persGroup;
    }

    public void setPersGroup(String persGroup) {
        this.persGroup = persGroup;
    }

    public String getJTCode() {
        return jTCode;
    }

    public void setJTCode(String jTCode) {
        this.jTCode = jTCode;
    }

    public String getExtEmpNo() {
        return extEmpNo;
    }

    public void setExtEmpNo(String extEmpNo) {
        this.extEmpNo = extEmpNo;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPymMeth() {
        return pymMeth;
    }

    public void setPymMeth(String pymMeth) {
        this.pymMeth = pymMeth;
    }

    public String getExemptCurr() {
        return exemptCurr;
    }

    public void setExemptCurr(String exemptCurr) {
        this.exemptCurr = exemptCurr;
    }

    public String getAddiCurr() {
        return addiCurr;
    }

    public void setAddiCurr(String addiCurr) {
        this.addiCurr = addiCurr;
    }

    public Integer getSTDCode() {
        return sTDCode;
    }

    public void setSTDCode(Integer sTDCode) {
        this.sTDCode = sTDCode;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public Character getContResp() {
        return contResp;
    }

    public void setContResp(Character contResp) {
        this.contResp = contResp;
    }

    public Character getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(Character repLegal) {
        this.repLegal = repLegal;
    }

    public Character getDirfDeclar() {
        return dirfDeclar;
    }

    public void setDirfDeclar(Character dirfDeclar) {
        this.dirfDeclar = dirfDeclar;
    }

    public String getUfCrc() {
        return ufCrc;
    }

    public void setUfCrc(String ufCrc) {
        this.ufCrc = ufCrc;
    }

    public String getIDType() {
        return iDType;
    }

    public void setIDType(String iDType) {
        this.iDType = iDType;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    public Integer getBPLId() {
        return bPLId;
    }

    public void setBPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    public String getManualNUM() {
        return manualNUM;
    }

    public void setManualNUM(String manualNUM) {
        this.manualNUM = manualNUM;
    }

    public Date getPassIssue() {
        return passIssue;
    }

    public void setPassIssue(Date passIssue) {
        this.passIssue = passIssue;
    }

    public String getPassIssuer() {
        return passIssuer;
    }

    public void setPassIssuer(String passIssuer) {
        this.passIssuer = passIssuer;
    }

    public String getQualCode() {
        return qualCode;
    }

    public void setQualCode(String qualCode) {
        this.qualCode = qualCode;
    }

    public Character getPRWebAccss() {
        return pRWebAccss;
    }

    public void setPRWebAccss(Character pRWebAccss) {
        this.pRWebAccss = pRWebAccss;
    }

    public Character getPrePRWeb() {
        return prePRWeb;
    }

    public void setPrePRWeb(Character prePRWeb) {
        this.prePRWeb = prePRWeb;
    }

    public String getUUArp() {
        return uUArp;
    }

    public void setUUArp(String uUArp) {
        this.uUArp = uUArp;
    }

    public String getUUEps() {
        return uUEps;
    }

    public void setUUEps(String uUEps) {
        this.uUEps = uUEps;
    }

    public String getUUCaComp() {
        return uUCaComp;
    }

    public void setUUCaComp(String uUCaComp) {
        this.uUCaComp = uUCaComp;
    }

    public String getUUPens() {
        return uUPens;
    }

    public void setUUPens(String uUPens) {
        this.uUPens = uUPens;
    }

    public String getUCert() {
        return uCert;
    }

    public void setUCert(String uCert) {
        this.uCert = uCert;
    }

    public String getUCesantias() {
        return uCesantias;
    }

    public void setUCesantias(String uCesantias) {
        this.uCesantias = uCesantias;
    }

    public String getUtipocontrato() {
        return utipocontrato;
    }

    public void setUtipocontrato(String utipocontrato) {
        this.utipocontrato = utipocontrato;
    }

    public String getUcodigoRevisado() {
        return ucodigoRevisado;
    }

    public void setUcodigoRevisado(String ucodigoRevisado) {
        this.ucodigoRevisado = ucodigoRevisado;
    }

    public String getUCorreoCorp() {
        return uCorreoCorp;
    }

    public void setUCorreoCorp(String uCorreoCorp) {
        this.uCorreoCorp = uCorreoCorp;
    }

    public Date getuUltCursoAltura() {
        return uUltCursoAltura;
    }

    public void setuUltCursoAltura(Date uUltCursoAltura) {
        this.uUltCursoAltura = uUltCursoAltura;
    }

    public String getuMotivoCursoAlt() {
        return uMotivoCursoAlt;
    }

    public void setuMotivoCursoAlt(String uMotivoCursoAlt) {
        this.uMotivoCursoAlt = uMotivoCursoAlt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empID != null ? empID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empID == null && other.empID != null) || (this.empID != null && !this.empID.equals(other.empID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Empleado[ empID=" + empID + " ]";
    }
}

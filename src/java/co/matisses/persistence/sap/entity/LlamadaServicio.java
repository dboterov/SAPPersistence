package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author dbotero
 */
@Entity
@Table(name = "OSCL")
public class LlamadaServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "callID")
    private Integer callID;
    @Column(name = "subject")
    private String subject;
    @Column(name = "customer")
    private String customer;
    @Column(name = "custmrName")
    private String custmrName;
    @Column(name = "contctCode")
    private Integer contctCode;
    @Column(name = "manufSN")
    private String manufSN;
    @Column(name = "internalSN")
    private String internalSN;
    @Column(name = "contractID")
    private Integer contractID;
    @Column(name = "cntrctDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntrctDate;
    @Column(name = "resolDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolDate;
    @Column(name = "resolTime")
    private Short resolTime;
    @Column(name = "free_1")
    private Character free1;
    @Column(name = "free_2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date free2;
    @Column(name = "origin")
    private Short origin;
    @Column(name = "itemCode")
    private String itemCode;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "itemGroup")
    private Short itemGroup;
    @Column(name = "status")
    private Short status;
    @Column(name = "priority")
    private Character priority;
    @Column(name = "callType")
    private Short callType;
    @Column(name = "problemTyp")
    private Short problemTyp;
    @Column(name = "assignee")
    private Short assignee;
    @Column(name = "descrption")
    private String descrption;
    @Column(name = "objType")
    private String objType;
    @Column(name = "logInstanc")
    private Integer logInstanc;
    @Column(name = "userSign")
    private Short userSign;
    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "createTime")
    private Short createTime;
    @Column(name = "closeDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeDate;
    @Column(name = "closeTime")
    private Short closeTime;
    @Column(name = "userSign2")
    private Short userSign2;
    @Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "SCL1Count")
    private Integer sCL1Count;
    @Column(name = "SCL2Count")
    private Integer sCL2Count;
    @Column(name = "isEntitled")
    private Character isEntitled;
    @Column(name = "insID")
    private Integer insID;
    @Column(name = "technician")
    private Integer technician;
    @Column(name = "resolution")
    private String resolution;
    @Column(name = "Scl1NxtLn")
    private Integer scl1NxtLn;
    @Column(name = "Scl2NxtLn")
    private Integer scl2NxtLn;
    @Column(name = "Scl3NxtLn")
    private Integer scl3NxtLn;
    @Column(name = "Scl4NxtLn")
    private Integer scl4NxtLn;
    @Column(name = "Scl5NxtLn")
    private Integer scl5NxtLn;
    @Column(name = "isQueue")
    private Character isQueue;
    @Column(name = "Queue")
    private String queue;
    @Column(name = "resolOnDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolOnDat;
    @Column(name = "resolOnTim")
    private Short resolOnTim;
    @Column(name = "respByDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respByDate;
    @Column(name = "respByTime")
    private Short respByTime;
    @Column(name = "respOnDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respOnDate;
    @Column(name = "respOnTime")
    private Short respOnTime;
    @Column(name = "respAssign")
    private Short respAssign;
    @Column(name = "AssignDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;
    @Column(name = "AssignTime")
    private Short assignTime;
    @Column(name = "UpdateTime")
    private Short updateTime;
    @Column(name = "responder")
    private Short responder;
    @Column(name = "Transfered")
    private Character transfered;
    @Basic(optional = false)
    @Column(name = "Instance")
    private Short instance;
    @Basic(optional = false)
    @Column(name = "DocNum")
    private int docNum;
    @Column(name = "Series")
    private Short series;
    @Column(name = "Handwrtten")
    private Character handwrtten;
    @Basic(optional = false)
    @Column(name = "PIndicator")
    private String pIndicator;
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "StartTime")
    private Integer startTime;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "EndTime")
    private Integer endTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Duration")
    private BigDecimal duration;
    @Column(name = "DurType")
    private Character durType;
    @Column(name = "Reminder")
    private Character reminder;
    @Column(name = "RemQty")
    private BigDecimal remQty;
    @Column(name = "RemType")
    private Character remType;
    @Column(name = "RemDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date remDate;
    @Column(name = "RemSent")
    private Character remSent;
    @Column(name = "RemTime")
    private Short remTime;
    @Column(name = "Location")
    private Short location;
    @Column(name = "AddrName")
    private String addrName;
    @Column(name = "AddrType")
    private Character addrType;
    @Column(name = "Street")
    private String street;
    @Column(name = "City")
    private String city;
    @Column(name = "Room")
    private String room;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "DisplInCal")
    private Character displInCal;
    @Column(name = "SupplCode")
    private String supplCode;
    @Column(name = "U_Causa")
    private String uCausa;
    @Column(name = "U_CateGa")
    private String uCateGa;
    @Column(name = "U_ProExt")
    private String uProExt;
    @Column(name = "U_Num_factura")
    private String uNumfactura;
    @Column(name = "U_TipoServ")
    private String uTipoServ;
    @Basic(optional = false)
    @Column(name = "U_NWR_RMA")
    private String uNwrRma;
    @Column(name = "U_NWR_PO")
    private String uNwrPo;
    @Column(name = "U_NWR_RMA_Type")
    private Short uNWRRMAType;

    public LlamadaServicio() {
    }

    public LlamadaServicio(Integer callID) {
        this.callID = callID;
    }

    public LlamadaServicio(Integer callID, short instance, int docNum, String pIndicator, String uNwrRma) {
        this.callID = callID;
        this.instance = instance;
        this.docNum = docNum;
        this.pIndicator = pIndicator;
        this.uNwrRma = uNwrRma;
    }

    public Integer getCallID() {
        return callID;
    }

    public void setCallID(Integer callID) {
        this.callID = callID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustmrName() {
        return custmrName;
    }

    public void setCustmrName(String custmrName) {
        this.custmrName = custmrName;
    }

    public Integer getContctCode() {
        return contctCode;
    }

    public void setContctCode(Integer contctCode) {
        this.contctCode = contctCode;
    }

    public String getManufSN() {
        return manufSN;
    }

    public void setManufSN(String manufSN) {
        this.manufSN = manufSN;
    }

    public String getInternalSN() {
        return internalSN;
    }

    public void setInternalSN(String internalSN) {
        this.internalSN = internalSN;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public Date getCntrctDate() {
        return cntrctDate;
    }

    public void setCntrctDate(Date cntrctDate) {
        this.cntrctDate = cntrctDate;
    }

    public Date getResolDate() {
        return resolDate;
    }

    public void setResolDate(Date resolDate) {
        this.resolDate = resolDate;
    }

    public Short getResolTime() {
        return resolTime;
    }

    public void setResolTime(Short resolTime) {
        this.resolTime = resolTime;
    }

    public Character getFree1() {
        return free1;
    }

    public void setFree1(Character free1) {
        this.free1 = free1;
    }

    public Date getFree2() {
        return free2;
    }

    public void setFree2(Date free2) {
        this.free2 = free2;
    }

    public Short getOrigin() {
        return origin;
    }

    public void setOrigin(Short origin) {
        this.origin = origin;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Short getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(Short itemGroup) {
        this.itemGroup = itemGroup;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Character getPriority() {
        return priority;
    }

    public void setPriority(Character priority) {
        this.priority = priority;
    }

    public Short getCallType() {
        return callType;
    }

    public void setCallType(Short callType) {
        this.callType = callType;
    }

    public Short getProblemTyp() {
        return problemTyp;
    }

    public void setProblemTyp(Short problemTyp) {
        this.problemTyp = problemTyp;
    }

    public Short getAssignee() {
        return assignee;
    }

    public void setAssignee(Short assignee) {
        this.assignee = assignee;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Short createTime) {
        this.createTime = createTime;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Short getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Short closeTime) {
        this.closeTime = closeTime;
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

    public Integer getSCL1Count() {
        return sCL1Count;
    }

    public void setSCL1Count(Integer sCL1Count) {
        this.sCL1Count = sCL1Count;
    }

    public Integer getSCL2Count() {
        return sCL2Count;
    }

    public void setSCL2Count(Integer sCL2Count) {
        this.sCL2Count = sCL2Count;
    }

    public Character getIsEntitled() {
        return isEntitled;
    }

    public void setIsEntitled(Character isEntitled) {
        this.isEntitled = isEntitled;
    }

    public Integer getInsID() {
        return insID;
    }

    public void setInsID(Integer insID) {
        this.insID = insID;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getScl1NxtLn() {
        return scl1NxtLn;
    }

    public void setScl1NxtLn(Integer scl1NxtLn) {
        this.scl1NxtLn = scl1NxtLn;
    }

    public Integer getScl2NxtLn() {
        return scl2NxtLn;
    }

    public void setScl2NxtLn(Integer scl2NxtLn) {
        this.scl2NxtLn = scl2NxtLn;
    }

    public Integer getScl3NxtLn() {
        return scl3NxtLn;
    }

    public void setScl3NxtLn(Integer scl3NxtLn) {
        this.scl3NxtLn = scl3NxtLn;
    }

    public Integer getScl4NxtLn() {
        return scl4NxtLn;
    }

    public void setScl4NxtLn(Integer scl4NxtLn) {
        this.scl4NxtLn = scl4NxtLn;
    }

    public Integer getScl5NxtLn() {
        return scl5NxtLn;
    }

    public void setScl5NxtLn(Integer scl5NxtLn) {
        this.scl5NxtLn = scl5NxtLn;
    }

    public Character getIsQueue() {
        return isQueue;
    }

    public void setIsQueue(Character isQueue) {
        this.isQueue = isQueue;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Date getResolOnDat() {
        return resolOnDat;
    }

    public void setResolOnDat(Date resolOnDat) {
        this.resolOnDat = resolOnDat;
    }

    public Short getResolOnTim() {
        return resolOnTim;
    }

    public void setResolOnTim(Short resolOnTim) {
        this.resolOnTim = resolOnTim;
    }

    public Date getRespByDate() {
        return respByDate;
    }

    public void setRespByDate(Date respByDate) {
        this.respByDate = respByDate;
    }

    public Short getRespByTime() {
        return respByTime;
    }

    public void setRespByTime(Short respByTime) {
        this.respByTime = respByTime;
    }

    public Date getRespOnDate() {
        return respOnDate;
    }

    public void setRespOnDate(Date respOnDate) {
        this.respOnDate = respOnDate;
    }

    public Short getRespOnTime() {
        return respOnTime;
    }

    public void setRespOnTime(Short respOnTime) {
        this.respOnTime = respOnTime;
    }

    public Short getRespAssign() {
        return respAssign;
    }

    public void setRespAssign(Short respAssign) {
        this.respAssign = respAssign;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Short getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Short assignTime) {
        this.assignTime = assignTime;
    }

    public Short getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Short updateTime) {
        this.updateTime = updateTime;
    }

    public Short getResponder() {
        return responder;
    }

    public void setResponder(Short responder) {
        this.responder = responder;
    }

    public Character getTransfered() {
        return transfered;
    }

    public void setTransfered(Character transfered) {
        this.transfered = transfered;
    }

    public short getInstance() {
        return instance;
    }

    public void setInstance(short instance) {
        this.instance = instance;
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public Short getSeries() {
        return series;
    }

    public void setSeries(Short series) {
        this.series = series;
    }

    public Character getHandwrtten() {
        return handwrtten;
    }

    public void setHandwrtten(Character handwrtten) {
        this.handwrtten = handwrtten;
    }

    public String getPIndicator() {
        return pIndicator;
    }

    public void setPIndicator(String pIndicator) {
        this.pIndicator = pIndicator;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Character getDurType() {
        return durType;
    }

    public void setDurType(Character durType) {
        this.durType = durType;
    }

    public Character getReminder() {
        return reminder;
    }

    public void setReminder(Character reminder) {
        this.reminder = reminder;
    }

    public BigDecimal getRemQty() {
        return remQty;
    }

    public void setRemQty(BigDecimal remQty) {
        this.remQty = remQty;
    }

    public Character getRemType() {
        return remType;
    }

    public void setRemType(Character remType) {
        this.remType = remType;
    }

    public Date getRemDate() {
        return remDate;
    }

    public void setRemDate(Date remDate) {
        this.remDate = remDate;
    }

    public Character getRemSent() {
        return remSent;
    }

    public void setRemSent(Character remSent) {
        this.remSent = remSent;
    }

    public Short getRemTime() {
        return remTime;
    }

    public void setRemTime(Short remTime) {
        this.remTime = remTime;
    }

    public Short getLocation() {
        return location;
    }

    public void setLocation(Short location) {
        this.location = location;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public Character getAddrType() {
        return addrType;
    }

    public void setAddrType(Character addrType) {
        this.addrType = addrType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Character getDisplInCal() {
        return displInCal;
    }

    public void setDisplInCal(Character displInCal) {
        this.displInCal = displInCal;
    }

    public String getSupplCode() {
        return supplCode;
    }

    public void setSupplCode(String supplCode) {
        this.supplCode = supplCode;
    }

    public String getUCausa() {
        return uCausa;
    }

    public void setUCausa(String uCausa) {
        this.uCausa = uCausa;
    }

    public String getUCateGa() {
        return uCateGa;
    }

    public void setUCateGa(String uCateGa) {
        this.uCateGa = uCateGa;
    }

    public String getUProExt() {
        return uProExt;
    }

    public void setUProExt(String uProExt) {
        this.uProExt = uProExt;
    }

    public String getUNumfactura() {
        return uNumfactura;
    }

    public void setUNumfactura(String uNumfactura) {
        this.uNumfactura = uNumfactura;
    }

    public String getUTipoServ() {
        return uTipoServ;
    }

    public void setUTipoServ(String uTipoServ) {
        this.uTipoServ = uTipoServ;
    }

    public String getUNwrRma() {
        return uNwrRma;
    }

    public void setUNwrRma(String uNwrRma) {
        this.uNwrRma = uNwrRma;
    }

    public String getUNwrPo() {
        return uNwrPo;
    }

    public void setUNwrPo(String uNwrPo) {
        this.uNwrPo = uNwrPo;
    }

    public Short getUNWRRMAType() {
        return uNWRRMAType;
    }

    public void setUNWRRMAType(Short uNWRRMAType) {
        this.uNWRRMAType = uNWRRMAType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (callID != null ? callID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LlamadaServicio)) {
            return false;
        }
        LlamadaServicio other = (LlamadaServicio) object;
        if ((this.callID == null && other.callID != null) || (this.callID != null && !this.callID.equals(other.callID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.LlamadaDeServicio[ callID=" + callID + " ]";
    }

}

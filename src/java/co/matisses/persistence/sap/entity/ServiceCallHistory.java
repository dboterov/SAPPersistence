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
 * @author ygil
 */
@Entity
@Table(name = "ASCL")
public class ServiceCallHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "logInstanc")
    private Integer logInstanc;
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
    @Temporal(TemporalType.DATE)
    private Date cntrctDate;
    @Column(name = "resolDate")
    @Temporal(TemporalType.DATE)
    private Date resolDate;
    @Column(name = "resolTime")
    private Integer resolTime;
    @Column(name = "free_1")
    private Character free1;
    @Column(name = "free_2")
    @Temporal(TemporalType.DATE)
    private Date free2;
    @Column(name = "origin")
    private Integer origin;
    @Column(name = "itemCode")
    private String itemCode;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "itemGroup")
    private Integer itemGroup;
    @Column(name = "status")
    private Integer status;
    @Column(name = "priority")
    private Character priority;
    @Column(name = "callType")
    private Integer callType;
    @Column(name = "problemTyp")
    private Integer problemTyp;
    @Column(name = "assignee")
    private Integer assignee;
    @Column(name = "descrption")
    private String descrption;
    @Column(name = "objType")
    private String objType;
    @Column(name = "userSign")
    private Integer userSign;
    @Column(name = "createDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "createTime")
    private Integer createTime;
    @Column(name = "closeDate")
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @Column(name = "closeTime")
    private Integer closeTime;
    @Column(name = "userSign2")
    private Integer userSign2;
    @Column(name = "updateDate")
    @Temporal(TemporalType.DATE)
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
    @Temporal(TemporalType.DATE)
    private Date resolOnDat;
    @Column(name = "resolOnTim")
    private Integer resolOnTim;
    @Column(name = "respByDate")
    @Temporal(TemporalType.DATE)
    private Date respByDate;
    @Column(name = "respByTime")
    private Integer respByTime;
    @Column(name = "respOnDate")
    @Temporal(TemporalType.DATE)
    private Date respOnDate;
    @Column(name = "respOnTime")
    private Integer respOnTime;
    @Column(name = "respAssign")
    private Integer respAssign;
    @Column(name = "AssignDate")
    @Temporal(TemporalType.DATE)
    private Date assignDate;
    @Column(name = "AssignTime")
    private Integer assignTime;
    @Column(name = "UpdateTime")
    private Integer updateTime;
    @Column(name = "responder")
    private Integer responder;
    @Column(name = "Transfered")
    private Character transfered;
    @Column(name = "Instance")
    private Integer instance;
    @Column(name = "DocNum")
    private Integer docNum;
    @Column(name = "Series")
    private Integer series;
    @Column(name = "Handwrtten")
    private Character handwrtten;
    @Column(name = "PIndicator")
    private String pIndicator;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "StartTime")
    private Integer startTime;
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "EndTime")
    private Integer endTime;
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
    @Temporal(TemporalType.DATE)
    private Date remDate;
    @Column(name = "RemSent")
    private Character remSent;
    @Column(name = "RemTime")
    private Integer remTime;
    @Column(name = "Location")
    private Integer location;
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
    @Column(name = "Attachment")
    private String attachment;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "NumAtCard")
    private String numAtCard;
    @Column(name = "ProSubType")
    private Integer proSubType;
    @Column(name = "BPType")
    private Character bPType;
    @Column(name = "Telephone")
    private String telephone;
    @Column(name = "BPPhone1")
    private String bPPhone1;
    @Column(name = "BPPhone2")
    private String bPPhone2;
    @Column(name = "BPCellular")
    private String bPCellular;
    @Column(name = "BPFax")
    private String bPFax;
    @Column(name = "BPShipCode")
    private String bPShipCode;
    @Column(name = "BPShipAddr")
    private String bPShipAddr;
    @Column(name = "BPBillCode")
    private String bPBillCode;
    @Column(name = "BPBillAddr")
    private String bPBillAddr;
    @Column(name = "BPTerrit")
    private Integer bPTerrit;
    @Column(name = "BPE_Mail")
    private String bPE_Mail;
    @Column(name = "BPProjCode")
    private String bPProjCode;
    @Column(name = "BPContact")
    private String bPContact;
    @Column(name = "OwnerCode")
    private Integer ownerCode;
    @Column(name = "U_Causa")
    private String uCausa;
    @Column(name = "U_CateGa")
    private String uCateGa;
    @Column(name = "U_ProExt")
    private String uProExt;
    @Column(name = "U_Num_factura")
    private String uNum_factura;
    @Column(name = "U_TipoServ")
    private String uTipoServ;
    @Column(name = "U_NWR_RMA")
    private String uNWR_RMA;
    @Column(name = "U_NWR_PO")
    private String uNWR_PO;
    @Column(name = "U_NWR_RMA_Type")
    private Integer uNWR_RMA_Type;
    @Column(name = "U_ComentSoporte")
    private String uComentSoporte;
    @Column(name = "U_Material")
    private String uMaterial;
    @Column(name = "U_ProblemaMaterial")
    private String uProblemaMaterial;
    @Column(name = "U_crearSoporte")
    private String ucrearSoporte;

    public ServiceCallHistory() {
    }

    public ServiceCallHistory(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public ServiceCallHistory(Integer logInstanc, Integer callID, String subject, String customer, String custmrName, Integer contctCode, String manufSN, String internalSN,
            Integer contractID, Date cntrctDate, Date resolDate, Integer resolTime, Character free1, Date free2, Integer origin, String itemCode, String itemName, Integer itemGroup,
            Integer status, Character priority, Integer callType, Integer problemTyp, Integer assignee, String descrption, String objType, Integer userSign, Date createDate,
            Integer createTime, Date closeDate, Integer closeTime, Integer userSign2, Date updateDate, Integer sCL1Count, Integer sCL2Count, Character isEntitled, Integer insID,
            Integer technician, String resolution, Integer scl1NxtLn, Integer scl2NxtLn, Integer scl3NxtLn, Integer scl4NxtLn, Integer scl5NxtLn, Character isQueue, String queue,
            Date resolOnDat, Integer resolOnTim, Date respByDate, Integer respByTime, Date respOnDate, Integer respOnTime, Integer respAssign, Date assignDate, Integer assignTime,
            Integer updateTime, Integer responder, Character transfered, Integer instance, Integer docNum, Integer series, Character handwrtten, String pIndicator, Date startDate,
            Integer startTime, Date endDate, Integer endTime, BigDecimal duration, Character durType, Character reminder, BigDecimal remQty, Character remType, Date remDate,
            Character remSent, Integer remTime, Integer location, String addrName, Character addrType, String street, String city, String room, String state, String country,
            Character displInCal, String supplCode, String attachment, Integer atcEntry, String numAtCard, Integer proSubType, Character bPType, String telephone, String bPPhone1,
            String bPPhone2, String bPCellular, String bPFax, String bPShipCode, String bPShipAddr, String bPBillCode, String bPBillAddr, Integer bPTerrit, String bPE_Mail,
            String bPProjCode, String bPContact, Integer ownerCode, String uCausa, String uCateGa, String uProExt, String uNum_factura, String uTipoServ, String uNWR_RMA, String uNWR_PO,
            Integer uNWR_RMA_Type, String uComentSoporte, String uMaterial, String uProblemaMaterial, String ucrearSoporte) {
        this.logInstanc = logInstanc;
        this.callID = callID;
        this.subject = subject;
        this.customer = customer;
        this.custmrName = custmrName;
        this.contctCode = contctCode;
        this.manufSN = manufSN;
        this.internalSN = internalSN;
        this.contractID = contractID;
        this.cntrctDate = cntrctDate;
        this.resolDate = resolDate;
        this.resolTime = resolTime;
        this.free1 = free1;
        this.free2 = free2;
        this.origin = origin;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemGroup = itemGroup;
        this.status = status;
        this.priority = priority;
        this.callType = callType;
        this.problemTyp = problemTyp;
        this.assignee = assignee;
        this.descrption = descrption;
        this.objType = objType;
        this.userSign = userSign;
        this.createDate = createDate;
        this.createTime = createTime;
        this.closeDate = closeDate;
        this.closeTime = closeTime;
        this.userSign2 = userSign2;
        this.updateDate = updateDate;
        this.sCL1Count = sCL1Count;
        this.sCL2Count = sCL2Count;
        this.isEntitled = isEntitled;
        this.insID = insID;
        this.technician = technician;
        this.resolution = resolution;
        this.scl1NxtLn = scl1NxtLn;
        this.scl2NxtLn = scl2NxtLn;
        this.scl3NxtLn = scl3NxtLn;
        this.scl4NxtLn = scl4NxtLn;
        this.scl5NxtLn = scl5NxtLn;
        this.isQueue = isQueue;
        this.queue = queue;
        this.resolOnDat = resolOnDat;
        this.resolOnTim = resolOnTim;
        this.respByDate = respByDate;
        this.respByTime = respByTime;
        this.respOnDate = respOnDate;
        this.respOnTime = respOnTime;
        this.respAssign = respAssign;
        this.assignDate = assignDate;
        this.assignTime = assignTime;
        this.updateTime = updateTime;
        this.responder = responder;
        this.transfered = transfered;
        this.instance = instance;
        this.docNum = docNum;
        this.series = series;
        this.handwrtten = handwrtten;
        this.pIndicator = pIndicator;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.duration = duration;
        this.durType = durType;
        this.reminder = reminder;
        this.remQty = remQty;
        this.remType = remType;
        this.remDate = remDate;
        this.remSent = remSent;
        this.remTime = remTime;
        this.location = location;
        this.addrName = addrName;
        this.addrType = addrType;
        this.street = street;
        this.city = city;
        this.room = room;
        this.state = state;
        this.country = country;
        this.displInCal = displInCal;
        this.supplCode = supplCode;
        this.attachment = attachment;
        this.atcEntry = atcEntry;
        this.numAtCard = numAtCard;
        this.proSubType = proSubType;
        this.bPType = bPType;
        this.telephone = telephone;
        this.bPPhone1 = bPPhone1;
        this.bPPhone2 = bPPhone2;
        this.bPCellular = bPCellular;
        this.bPFax = bPFax;
        this.bPShipCode = bPShipCode;
        this.bPShipAddr = bPShipAddr;
        this.bPBillCode = bPBillCode;
        this.bPBillAddr = bPBillAddr;
        this.bPTerrit = bPTerrit;
        this.bPE_Mail = bPE_Mail;
        this.bPProjCode = bPProjCode;
        this.bPContact = bPContact;
        this.ownerCode = ownerCode;
        this.uCausa = uCausa;
        this.uCateGa = uCateGa;
        this.uProExt = uProExt;
        this.uNum_factura = uNum_factura;
        this.uTipoServ = uTipoServ;
        this.uNWR_RMA = uNWR_RMA;
        this.uNWR_PO = uNWR_PO;
        this.uNWR_RMA_Type = uNWR_RMA_Type;
        this.uComentSoporte = uComentSoporte;
        this.uMaterial = uMaterial;
        this.uProblemaMaterial = uProblemaMaterial;
        this.ucrearSoporte = ucrearSoporte;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
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

    public Integer getResolTime() {
        return resolTime;
    }

    public void setResolTime(Integer resolTime) {
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

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
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

    public Integer getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(Integer itemGroup) {
        this.itemGroup = itemGroup;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Character getPriority() {
        return priority;
    }

    public void setPriority(Character priority) {
        this.priority = priority;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }

    public Integer getProblemTyp() {
        return problemTyp;
    }

    public void setProblemTyp(Integer problemTyp) {
        this.problemTyp = problemTyp;
    }

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
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

    public Integer getUserSign() {
        return userSign;
    }

    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Integer userSign2) {
        this.userSign2 = userSign2;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getsCL1Count() {
        return sCL1Count;
    }

    public void setsCL1Count(Integer sCL1Count) {
        this.sCL1Count = sCL1Count;
    }

    public Integer getsCL2Count() {
        return sCL2Count;
    }

    public void setsCL2Count(Integer sCL2Count) {
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

    public Integer getResolOnTim() {
        return resolOnTim;
    }

    public void setResolOnTim(Integer resolOnTim) {
        this.resolOnTim = resolOnTim;
    }

    public Date getRespByDate() {
        return respByDate;
    }

    public void setRespByDate(Date respByDate) {
        this.respByDate = respByDate;
    }

    public Integer getRespByTime() {
        return respByTime;
    }

    public void setRespByTime(Integer respByTime) {
        this.respByTime = respByTime;
    }

    public Date getRespOnDate() {
        return respOnDate;
    }

    public void setRespOnDate(Date respOnDate) {
        this.respOnDate = respOnDate;
    }

    public Integer getRespOnTime() {
        return respOnTime;
    }

    public void setRespOnTime(Integer respOnTime) {
        this.respOnTime = respOnTime;
    }

    public Integer getRespAssign() {
        return respAssign;
    }

    public void setRespAssign(Integer respAssign) {
        this.respAssign = respAssign;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Integer getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Integer assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getResponder() {
        return responder;
    }

    public void setResponder(Integer responder) {
        this.responder = responder;
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

    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Character getHandwrtten() {
        return handwrtten;
    }

    public void setHandwrtten(Character handwrtten) {
        this.handwrtten = handwrtten;
    }

    public String getpIndicator() {
        return pIndicator;
    }

    public void setpIndicator(String pIndicator) {
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

    public Integer getRemTime() {
        return remTime;
    }

    public void setRemTime(Integer remTime) {
        this.remTime = remTime;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
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

    public String getNumAtCard() {
        return numAtCard;
    }

    public void setNumAtCard(String numAtCard) {
        this.numAtCard = numAtCard;
    }

    public Integer getProSubType() {
        return proSubType;
    }

    public void setProSubType(Integer proSubType) {
        this.proSubType = proSubType;
    }

    public Character getbPType() {
        return bPType;
    }

    public void setbPType(Character bPType) {
        this.bPType = bPType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getbPPhone1() {
        return bPPhone1;
    }

    public void setbPPhone1(String bPPhone1) {
        this.bPPhone1 = bPPhone1;
    }

    public String getbPPhone2() {
        return bPPhone2;
    }

    public void setbPPhone2(String bPPhone2) {
        this.bPPhone2 = bPPhone2;
    }

    public String getbPCellular() {
        return bPCellular;
    }

    public void setbPCellular(String bPCellular) {
        this.bPCellular = bPCellular;
    }

    public String getbPFax() {
        return bPFax;
    }

    public void setbPFax(String bPFax) {
        this.bPFax = bPFax;
    }

    public String getbPShipCode() {
        return bPShipCode;
    }

    public void setbPShipCode(String bPShipCode) {
        this.bPShipCode = bPShipCode;
    }

    public String getbPShipAddr() {
        return bPShipAddr;
    }

    public void setbPShipAddr(String bPShipAddr) {
        this.bPShipAddr = bPShipAddr;
    }

    public String getbPBillCode() {
        return bPBillCode;
    }

    public void setbPBillCode(String bPBillCode) {
        this.bPBillCode = bPBillCode;
    }

    public String getbPBillAddr() {
        return bPBillAddr;
    }

    public void setbPBillAddr(String bPBillAddr) {
        this.bPBillAddr = bPBillAddr;
    }

    public Integer getbPTerrit() {
        return bPTerrit;
    }

    public void setbPTerrit(Integer bPTerrit) {
        this.bPTerrit = bPTerrit;
    }

    public String getbPE_Mail() {
        return bPE_Mail;
    }

    public void setbPE_Mail(String bPE_Mail) {
        this.bPE_Mail = bPE_Mail;
    }

    public String getbPProjCode() {
        return bPProjCode;
    }

    public void setbPProjCode(String bPProjCode) {
        this.bPProjCode = bPProjCode;
    }

    public String getbPContact() {
        return bPContact;
    }

    public void setbPContact(String bPContact) {
        this.bPContact = bPContact;
    }

    public Integer getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Integer ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getuCausa() {
        return uCausa;
    }

    public void setuCausa(String uCausa) {
        this.uCausa = uCausa;
    }

    public String getuCateGa() {
        return uCateGa;
    }

    public void setuCateGa(String uCateGa) {
        this.uCateGa = uCateGa;
    }

    public String getuProExt() {
        return uProExt;
    }

    public void setuProExt(String uProExt) {
        this.uProExt = uProExt;
    }

    public String getuNum_factura() {
        return uNum_factura;
    }

    public void setuNum_factura(String uNum_factura) {
        this.uNum_factura = uNum_factura;
    }

    public String getuTipoServ() {
        return uTipoServ;
    }

    public void setuTipoServ(String uTipoServ) {
        this.uTipoServ = uTipoServ;
    }

    public String getuNWR_RMA() {
        return uNWR_RMA;
    }

    public void setuNWR_RMA(String uNWR_RMA) {
        this.uNWR_RMA = uNWR_RMA;
    }

    public String getuNWR_PO() {
        return uNWR_PO;
    }

    public void setuNWR_PO(String uNWR_PO) {
        this.uNWR_PO = uNWR_PO;
    }

    public Integer getuNWR_RMA_Type() {
        return uNWR_RMA_Type;
    }

    public void setuNWR_RMA_Type(Integer uNWR_RMA_Type) {
        this.uNWR_RMA_Type = uNWR_RMA_Type;
    }

    public String getuComentSoporte() {
        return uComentSoporte;
    }

    public void setuComentSoporte(String uComentSoporte) {
        this.uComentSoporte = uComentSoporte;
    }

    public String getuMaterial() {
        return uMaterial;
    }

    public void setuMaterial(String uMaterial) {
        this.uMaterial = uMaterial;
    }

    public String getuProblemaMaterial() {
        return uProblemaMaterial;
    }

    public void setuProblemaMaterial(String uProblemaMaterial) {
        this.uProblemaMaterial = uProblemaMaterial;
    }

    public String getUcrearSoporte() {
        return ucrearSoporte;
    }

    public void setUcrearSoporte(String ucrearSoporte) {
        this.ucrearSoporte = ucrearSoporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logInstanc != null ? logInstanc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCallHistory)) {
            return false;
        }
        ServiceCallHistory other = (ServiceCallHistory) object;
        if ((this.logInstanc == null && other.logInstanc != null) || (this.logInstanc != null && !this.logInstanc.equals(other.logInstanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.ServiceCallHistory[ logInstanc=" + logInstanc + " ]";
    }
}

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
@Table(name = "OCLG")
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ClgCode")
    private Integer clgCode;
    @Column(name = "CardCode")
    private String cardCode;
    @Column(name = "Notes")
    private String notes;
    @Column(name = "CntctDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntctDate;
    @Column(name = "CntctTime")
    private Integer cntctTime;
    @Column(name = "Recontact")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recontact;
    @Column(name = "Closed")
    private Character closed;
    @Column(name = "CloseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeDate;
    @Column(name = "ContactPer")
    private String contactPer;
    @Column(name = "Tel")
    private String tel;
    @Column(name = "Fax")
    private String fax;
    @Column(name = "CntctSbjct")
    private Integer cntctSbjct;
    @Column(name = "Transfered")
    private Character transfered;
    @Column(name = "DocType")
    private String docType;
    @Column(name = "DocNum")
    private String docNum;
    @Column(name = "DocEntry")
    private String docEntry;
    @Column(name = "Attachment")
    private String attachment;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "AttendUser")
    private Integer attendUser;
    @Column(name = "CntctCode")
    private Integer cntctCode;
    @Column(name = "UserSign")
    private Integer userSign;
    @Column(name = "SlpCode")
    private Integer slpCode;
    @Column(name = "Action")
    private Character action;
    @Column(name = "Details")
    private String details;
    @Column(name = "CntctType")
    private Integer cntctType;
    @Column(name = "Location")
    private Integer location;
    @Column(name = "BeginTime")
    private Integer beginTime;
    @Column(name = "Duration")
    private BigDecimal duration;
    @Column(name = "DurType")
    private Character durType;
    @Column(name = "ENDTime")
    private Integer eNDTime;
    @Column(name = "Priority")
    private Character priority;
    @Column(name = "Reminder")
    private Character reminder;
    @Column(name = "RemQty")
    private BigDecimal remQty;
    @Column(name = "RemType")
    private Character remType;
    @Column(name = "OprId")
    private Integer oprId;
    @Column(name = "OprLine")
    private Integer oprLine;
    @Column(name = "RemDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date remDate;
    @Column(name = "RemTime")
    private Integer remTime;
    @Column(name = "RemSented")
    private Character remSented;
    @Column(name = "Instance")
    private Integer instance;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "status")
    private Integer status;
    @Column(name = "personal")
    private Character personal;
    @Column(name = "inactive")
    private Character inactive;
    @Column(name = "tentative")
    private Character tentative;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "room")
    private String room;
    @Column(name = "parentType")
    private String parentType;
    @Column(name = "parentId")
    private Integer parentId;
    @Column(name = "prevActvty")
    private Integer prevActvty;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "RecurPat")
    private Character recurPat;
    @Column(name = "EndType")
    private Character endType;
    @Column(name = "SeStartDat")
    private Date seStartDat;
    @Column(name = "SeEndDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seEndDat;
    @Column(name = "MaxOccur")
    private Integer maxOccur;
    @Column(name = "Interval")
    private Integer interval;
    @Column(name = "Sunday")
    private Character sunday;
    @Column(name = "Monday")
    private Character monday;
    @Column(name = "Tuesday")
    private Character tuesday;
    @Column(name = "Wednesday")
    private Character wednesday;
    @Column(name = "Thursday")
    private Character thursday;
    @Column(name = "Friday")
    private Character friday;
    @Column(name = "Saturday")
    private Character saturday;
    @Column(name = "SubOption")
    private Character subOption;
    @Column(name = "DayInMonth")
    private Integer dayInMonth;
    @Column(name = "Month")
    private Integer month;
    @Column(name = "DayOfWeek")
    private Integer dayOfWeek;
    @Column(name = "Week")
    private Integer week;
    @Column(name = "SeriesNum")
    private Integer seriesNum;
    @Column(name = "OrigDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date origDate;
    @Column(name = "IsRemoved")
    private Character isRemoved;
    @Column(name = "LastRemind")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRemind;
    @Column(name = "AssignedBy")
    private Integer assignedBy;
    @Column(name = "AddrName")
    private String addrName;
    @Column(name = "AddrType")
    private Character addrType;
    @Column(name = "AttendEmpl")
    private Integer attendEmpl;
    @Column(name = "NextDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextDate;
    @Column(name = "NextTime")
    private Integer nextTime;
    @Column(name = "OwnerCode")
    private Integer ownerCode;

    public Actividad() {
    }

    public Actividad(Integer clgCode) {
        this.clgCode = clgCode;
    }

    public Actividad(Integer clgCode, String cardCode, String notes, Date cntctDate, Integer cntctTime, Date recontact, Character closed, Date closeDate, String contactPer,
            String tel, String fax, Integer cntctSbjct, Character transfered, String docType, String docNum, String docEntry, String attachment, Character dataSource,
            Integer attendUser, Integer cntctCode, Integer userSign, Integer slpCode, Character action, String details, Integer cntctType, Integer location, Integer beginTime,
            BigDecimal duration, Character durType, Integer eNDTime, Character priority, Character reminder, BigDecimal remQty, Character remType, Integer oprId, Integer oprLine,
            Date remDate, Integer remTime, Character remSented, Integer instance, Date endDate, Integer status, Character personal, Character inactive, Character tentative,
            String street, String city, String country, String state, String room, String parentType, Integer parentId, Integer prevActvty, Integer atcEntry, Character recurPat,
            Character endType, Date seStartDat, Date seEndDat, Integer maxOccur, Integer interval, Character sunday, Character monday, Character tuesday, Character wednesday,
            Character thursday, Character friday, Character saturday, Character subOption, Integer dayInMonth, Integer month, Integer dayOfWeek, Integer week, Integer seriesNum,
            Date origDate, Character isRemoved, Date lastRemind, Integer assignedBy, String addrName, Character addrType, Integer attendEmpl, Date nextDate, Integer nextTime, Integer ownerCode) {
        this.clgCode = clgCode;
        this.cardCode = cardCode;
        this.notes = notes;
        this.cntctDate = cntctDate;
        this.cntctTime = cntctTime;
        this.recontact = recontact;
        this.closed = closed;
        this.closeDate = closeDate;
        this.contactPer = contactPer;
        this.tel = tel;
        this.fax = fax;
        this.cntctSbjct = cntctSbjct;
        this.transfered = transfered;
        this.docType = docType;
        this.docNum = docNum;
        this.docEntry = docEntry;
        this.attachment = attachment;
        this.dataSource = dataSource;
        this.attendUser = attendUser;
        this.cntctCode = cntctCode;
        this.userSign = userSign;
        this.slpCode = slpCode;
        this.action = action;
        this.details = details;
        this.cntctType = cntctType;
        this.location = location;
        this.beginTime = beginTime;
        this.duration = duration;
        this.durType = durType;
        this.eNDTime = eNDTime;
        this.priority = priority;
        this.reminder = reminder;
        this.remQty = remQty;
        this.remType = remType;
        this.oprId = oprId;
        this.oprLine = oprLine;
        this.remDate = remDate;
        this.remTime = remTime;
        this.remSented = remSented;
        this.instance = instance;
        this.endDate = endDate;
        this.status = status;
        this.personal = personal;
        this.inactive = inactive;
        this.tentative = tentative;
        this.street = street;
        this.city = city;
        this.country = country;
        this.state = state;
        this.room = room;
        this.parentType = parentType;
        this.parentId = parentId;
        this.prevActvty = prevActvty;
        this.atcEntry = atcEntry;
        this.recurPat = recurPat;
        this.endType = endType;
        this.seStartDat = seStartDat;
        this.seEndDat = seEndDat;
        this.maxOccur = maxOccur;
        this.interval = interval;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.subOption = subOption;
        this.dayInMonth = dayInMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.week = week;
        this.seriesNum = seriesNum;
        this.origDate = origDate;
        this.isRemoved = isRemoved;
        this.lastRemind = lastRemind;
        this.assignedBy = assignedBy;
        this.addrName = addrName;
        this.addrType = addrType;
        this.attendEmpl = attendEmpl;
        this.nextDate = nextDate;
        this.nextTime = nextTime;
        this.ownerCode = ownerCode;
    }

    public Integer getClgCode() {
        return clgCode;
    }

    public void setClgCode(Integer clgCode) {
        this.clgCode = clgCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCntctDate() {
        return cntctDate;
    }

    public void setCntctDate(Date cntctDate) {
        this.cntctDate = cntctDate;
    }

    public Integer getCntctTime() {
        return cntctTime;
    }

    public void setCntctTime(Integer cntctTime) {
        this.cntctTime = cntctTime;
    }

    public Date getRecontact() {
        return recontact;
    }

    public void setRecontact(Date recontact) {
        this.recontact = recontact;
    }

    public Character getClosed() {
        return closed;
    }

    public void setClosed(Character closed) {
        this.closed = closed;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getContactPer() {
        return contactPer;
    }

    public void setContactPer(String contactPer) {
        this.contactPer = contactPer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getCntctSbjct() {
        return cntctSbjct;
    }

    public void setCntctSbjct(Integer cntctSbjct) {
        this.cntctSbjct = cntctSbjct;
    }

    public Character getTransfered() {
        return transfered;
    }

    public void setTransfered(Character transfered) {
        this.transfered = transfered;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(String docEntry) {
        this.docEntry = docEntry;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getAttendUser() {
        return attendUser;
    }

    public void setAttendUser(Integer attendUser) {
        this.attendUser = attendUser;
    }

    public Integer getCntctCode() {
        return cntctCode;
    }

    public void setCntctCode(Integer cntctCode) {
        this.cntctCode = cntctCode;
    }

    public Integer getUserSign() {
        return userSign;
    }

    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
    }

    public Integer getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(Integer slpCode) {
        this.slpCode = slpCode;
    }

    public Character getAction() {
        return action;
    }

    public void setAction(Character action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCntctType() {
        return cntctType;
    }

    public void setCntctType(Integer cntctType) {
        this.cntctType = cntctType;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
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

    public Integer geteNDTime() {
        return eNDTime;
    }

    public void seteNDTime(Integer eNDTime) {
        this.eNDTime = eNDTime;
    }

    public Character getPriority() {
        return priority;
    }

    public void setPriority(Character priority) {
        this.priority = priority;
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

    public Integer getOprId() {
        return oprId;
    }

    public void setOprId(Integer oprId) {
        this.oprId = oprId;
    }

    public Integer getOprLine() {
        return oprLine;
    }

    public void setOprLine(Integer oprLine) {
        this.oprLine = oprLine;
    }

    public Date getRemDate() {
        return remDate;
    }

    public void setRemDate(Date remDate) {
        this.remDate = remDate;
    }

    public Integer getRemTime() {
        return remTime;
    }

    public void setRemTime(Integer remTime) {
        this.remTime = remTime;
    }

    public Character getRemSented() {
        return remSented;
    }

    public void setRemSented(Character remSented) {
        this.remSented = remSented;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Character getPersonal() {
        return personal;
    }

    public void setPersonal(Character personal) {
        this.personal = personal;
    }

    public Character getInactive() {
        return inactive;
    }

    public void setInactive(Character inactive) {
        this.inactive = inactive;
    }

    public Character getTentative() {
        return tentative;
    }

    public void setTentative(Character tentative) {
        this.tentative = tentative;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPrevActvty() {
        return prevActvty;
    }

    public void setPrevActvty(Integer prevActvty) {
        this.prevActvty = prevActvty;
    }

    public Integer getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Integer atcEntry) {
        this.atcEntry = atcEntry;
    }

    public Character getRecurPat() {
        return recurPat;
    }

    public void setRecurPat(Character recurPat) {
        this.recurPat = recurPat;
    }

    public Character getEndType() {
        return endType;
    }

    public void setEndType(Character endType) {
        this.endType = endType;
    }

    public Date getSeStartDat() {
        return seStartDat;
    }

    public void setSeStartDat(Date seStartDat) {
        this.seStartDat = seStartDat;
    }

    public Date getSeEndDat() {
        return seEndDat;
    }

    public void setSeEndDat(Date seEndDat) {
        this.seEndDat = seEndDat;
    }

    public Integer getMaxOccur() {
        return maxOccur;
    }

    public void setMaxOccur(Integer maxOccur) {
        this.maxOccur = maxOccur;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Character getSunday() {
        return sunday;
    }

    public void setSunday(Character sunday) {
        this.sunday = sunday;
    }

    public Character getMonday() {
        return monday;
    }

    public void setMonday(Character monday) {
        this.monday = monday;
    }

    public Character getTuesday() {
        return tuesday;
    }

    public void setTuesday(Character tuesday) {
        this.tuesday = tuesday;
    }

    public Character getWednesday() {
        return wednesday;
    }

    public void setWednesday(Character wednesday) {
        this.wednesday = wednesday;
    }

    public Character getThursday() {
        return thursday;
    }

    public void setThursday(Character thursday) {
        this.thursday = thursday;
    }

    public Character getFriday() {
        return friday;
    }

    public void setFriday(Character friday) {
        this.friday = friday;
    }

    public Character getSaturday() {
        return saturday;
    }

    public void setSaturday(Character saturday) {
        this.saturday = saturday;
    }

    public Character getSubOption() {
        return subOption;
    }

    public void setSubOption(Character subOption) {
        this.subOption = subOption;
    }

    public Integer getDayInMonth() {
        return dayInMonth;
    }

    public void setDayInMonth(Integer dayInMonth) {
        this.dayInMonth = dayInMonth;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(Integer seriesNum) {
        this.seriesNum = seriesNum;
    }

    public Date getOrigDate() {
        return origDate;
    }

    public void setOrigDate(Date origDate) {
        this.origDate = origDate;
    }

    public Character getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Character isRemoved) {
        this.isRemoved = isRemoved;
    }

    public Date getLastRemind() {
        return lastRemind;
    }

    public void setLastRemind(Date lastRemind) {
        this.lastRemind = lastRemind;
    }

    public Integer getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Integer assignedBy) {
        this.assignedBy = assignedBy;
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

    public Integer getAttendEmpl() {
        return attendEmpl;
    }

    public void setAttendEmpl(Integer attendEmpl) {
        this.attendEmpl = attendEmpl;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getNextTime() {
        return nextTime;
    }

    public void setNextTime(Integer nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Integer ownerCode) {
        this.ownerCode = ownerCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clgCode != null ? clgCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.clgCode == null && other.clgCode != null) || (this.clgCode != null && !this.clgCode.equals(other.clgCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Actividad[ clgCode=" + clgCode + " ]";
    }
}

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
@Table(name = "NNM1")
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ObjectCode")
    private Long objectCode;
    @Id
    @Basic(optional = false)
    @Column(name = "Series")
    private Integer series;
    @Column(name = "SeriesName")
    private String seriesName;
    @Column(name = "InitialNum")
    private Integer initialNum;
    @Column(name = "NextNumber")
    private Integer nextNumber;
    @Column(name = "LastNum")
    private Integer lastNum;
    @Column(name = "BeginStr")
    private String beginStr;
    @Column(name = "EndStr")
    private String endStr;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "GroupCode")
    private Integer groupCode;
    @Column(name = "Locked")
    private Character locked;
    @Column(name = "YearTransf")
    private Character yearTransf;
    @Column(name = "Indicator")
    private String indicator;
    @Column(name = "Template")
    private String template;
    @Column(name = "NumSize")
    private Integer numSize;
    @Column(name = "FolioPref")
    private String folioPref;
    @Column(name = "NextFolio")
    private Integer nextFolio;
    @Column(name = "DocSubType")
    private String docSubType;
    @Column(name = "DefESeries")
    private Integer defESeries;
    @Column(name = "IsDigSerie")
    private Character isDigSerie;
    @Column(name = "SeriesType")
    private Character seriesType;
    @Column(name = "IsManual")
    private Character isManual;
    @Column(name = "BPLId")
    private Integer bPLId;
    @Column(name = "IsForCncl")
    private Character isForCncl;
    @Column(name = "AtDocType")
    private String atDocType;
    @Column(name = "IsElAuth")
    private Character isElAuth;

    public Serie() {
    }

    public Serie(Integer series) {
        this.series = series;
    }

    public Serie(Long objectCode, Integer series, String seriesName, Integer initialNum, Integer nextNumber, Integer lastNum, String beginStr, String endStr,
            String remark, Integer groupCode, Character locked, Character yearTransf, String indicator, String template, Integer numSize, String folioPref,
            Integer nextFolio, String docSubType, Integer defESeries, Character isDigSerie, Character seriesType, Character isManual, Integer bPLId,
            Character isForCncl, String atDocType, Character isElAuth) {
        this.objectCode = objectCode;
        this.series = series;
        this.seriesName = seriesName;
        this.initialNum = initialNum;
        this.nextNumber = nextNumber;
        this.lastNum = lastNum;
        this.beginStr = beginStr;
        this.endStr = endStr;
        this.remark = remark;
        this.groupCode = groupCode;
        this.locked = locked;
        this.yearTransf = yearTransf;
        this.indicator = indicator;
        this.template = template;
        this.numSize = numSize;
        this.folioPref = folioPref;
        this.nextFolio = nextFolio;
        this.docSubType = docSubType;
        this.defESeries = defESeries;
        this.isDigSerie = isDigSerie;
        this.seriesType = seriesType;
        this.isManual = isManual;
        this.bPLId = bPLId;
        this.isForCncl = isForCncl;
        this.atDocType = atDocType;
        this.isElAuth = isElAuth;
    }

    public Long getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(Long objectCode) {
        this.objectCode = objectCode;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Integer getInitialNum() {
        return initialNum;
    }

    public void setInitialNum(Integer initialNum) {
        this.initialNum = initialNum;
    }

    public Integer getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Integer nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Integer getLastNum() {
        return lastNum;
    }

    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    public String getBeginStr() {
        return beginStr;
    }

    public void setBeginStr(String beginStr) {
        this.beginStr = beginStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(Integer groupCode) {
        this.groupCode = groupCode;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    public Character getYearTransf() {
        return yearTransf;
    }

    public void setYearTransf(Character yearTransf) {
        this.yearTransf = yearTransf;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getNumSize() {
        return numSize;
    }

    public void setNumSize(Integer numSize) {
        this.numSize = numSize;
    }

    public String getFolioPref() {
        return folioPref;
    }

    public void setFolioPref(String folioPref) {
        this.folioPref = folioPref;
    }

    public Integer getNextFolio() {
        return nextFolio;
    }

    public void setNextFolio(Integer nextFolio) {
        this.nextFolio = nextFolio;
    }

    public String getDocSubType() {
        return docSubType;
    }

    public void setDocSubType(String docSubType) {
        this.docSubType = docSubType;
    }

    public Integer getDefESeries() {
        return defESeries;
    }

    public void setDefESeries(Integer defESeries) {
        this.defESeries = defESeries;
    }

    public Character getIsDigSerie() {
        return isDigSerie;
    }

    public void setIsDigSerie(Character isDigSerie) {
        this.isDigSerie = isDigSerie;
    }

    public Character getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(Character seriesType) {
        this.seriesType = seriesType;
    }

    public Character getIsManual() {
        return isManual;
    }

    public void setIsManual(Character isManual) {
        this.isManual = isManual;
    }

    public Integer getbPLId() {
        return bPLId;
    }

    public void setbPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    public Character getIsForCncl() {
        return isForCncl;
    }

    public void setIsForCncl(Character isForCncl) {
        this.isForCncl = isForCncl;
    }

    public String getAtDocType() {
        return atDocType;
    }

    public void setAtDocType(String atDocType) {
        this.atDocType = atDocType;
    }

    public Character getIsElAuth() {
        return isElAuth;
    }

    public void setIsElAuth(Character isElAuth) {
        this.isElAuth = isElAuth;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (series != null ? series.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.series == null && other.series != null) || (this.series != null && !this.series.equals(other.series))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Serie[ series=" + series + " ]";
    }
}

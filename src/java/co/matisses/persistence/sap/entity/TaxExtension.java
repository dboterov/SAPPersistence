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
@Table(name = "QUT12")
public class TaxExtension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Column(name = "StreetS")
    private String streetS;
    @Column(name = "BlockS")
    private String blockS;
    @Column(name = "BuildingS")
    private String buildingS;
    @Column(name = "CityS")
    private String cityS;
    @Column(name = "CountyS")
    private String countyS;
    @Column(name = "StateS")
    private String stateS;
    @Column(name = "CountryS")
    private String countryS;
    @Column(name = "StreetB")
    private String streetB;
    @Column(name = "BlockB")
    private String blockB;
    @Column(name = "BuildingB")
    private String buildingB;
    @Column(name = "CityB")
    private String cityB;
    @Column(name = "CountyB")
    private String countyB;
    @Column(name = "StateB")
    private String stateB;
    @Column(name = "CountryB")
    private String countryB;

    public TaxExtension() {
    }

    public TaxExtension(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public TaxExtension(Integer docEntry, String streetS, String blockS, String buildingS, String cityS, String countyS, String stateS, String countryS,
            String streetB, String blockB, String buildingB, String cityB, String countyB, String stateB, String countryB) {
        this.docEntry = docEntry;
        this.streetS = streetS;
        this.blockS = blockS;
        this.buildingS = buildingS;
        this.cityS = cityS;
        this.countyS = countyS;
        this.stateS = stateS;
        this.countryS = countryS;
        this.streetB = streetB;
        this.blockB = blockB;
        this.buildingB = buildingB;
        this.cityB = cityB;
        this.countyB = countyB;
        this.stateB = stateB;
        this.countryB = countryB;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public String getStreetS() {
        return streetS;
    }

    public void setStreetS(String streetS) {
        this.streetS = streetS;
    }

    public String getBlockS() {
        return blockS;
    }

    public void setBlockS(String blockS) {
        this.blockS = blockS;
    }

    public String getBuildingS() {
        return buildingS;
    }

    public void setBuildingS(String buildingS) {
        this.buildingS = buildingS;
    }

    public String getCityS() {
        return cityS;
    }

    public void setCityS(String cityS) {
        this.cityS = cityS;
    }

    public String getCountyS() {
        return countyS;
    }

    public void setCountyS(String countyS) {
        this.countyS = countyS;
    }

    public String getStateS() {
        return stateS;
    }

    public void setStateS(String stateS) {
        this.stateS = stateS;
    }

    public String getCountryS() {
        return countryS;
    }

    public void setCountryS(String countryS) {
        this.countryS = countryS;
    }

    public String getStreetB() {
        return streetB;
    }

    public void setStreetB(String streetB) {
        this.streetB = streetB;
    }

    public String getBlockB() {
        return blockB;
    }

    public void setBlockB(String blockB) {
        this.blockB = blockB;
    }

    public String getBuildingB() {
        return buildingB;
    }

    public void setBuildingB(String buildingB) {
        this.buildingB = buildingB;
    }

    public String getCityB() {
        return cityB;
    }

    public void setCityB(String cityB) {
        this.cityB = cityB;
    }

    public String getCountyB() {
        return countyB;
    }

    public void setCountyB(String countyB) {
        this.countyB = countyB;
    }

    public String getStateB() {
        return stateB;
    }

    public void setStateB(String stateB) {
        this.stateB = stateB;
    }

    public String getCountryB() {
        return countryB;
    }

    public void setCountryB(String countryB) {
        this.countryB = countryB;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docEntry != null ? docEntry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxExtension)) {
            return false;
        }
        TaxExtension other = (TaxExtension) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.TaxExtension[ docEntry=" + docEntry + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "CRD1")
@NamedQueries({
    @NamedQuery(name = "DireccionSocioDeNegocios.findAll", query = "SELECT d FROM DireccionSocioDeNegocios d")})
public class DireccionSocioDeNegocios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DireccionSocioDeNegociosPK direccionSocioDeNegociosPK;
    @Column(name = "Street")
    private String street;
    @Column(name = "Block")
    private String block;
    @Column(name = "ZipCode")
    private String zipCode;
    @Column(name = "City")
    private String city;
    @Column(name = "County")
    private String county;
    @Column(name = "Country")
    private String country;
    @Column(name = "State")
    private String state;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "ObjType")
    private String objType;
    @Column(name = "LicTradNum")
    private String licTradNum;
    @Column(name = "LineNum")
    private Integer lineNum;
    @Column(name = "TaxCode")
    private String taxCode;
    @Column(name = "Building")
    private String building;
    @Column(name = "Address2")
    private String address2;
    @Column(name = "Address3")
    private String address3;
    @Column(name = "AddrType")
    private String addrType;
    @Column(name = "StreetNo")
    private String streetNo;
    @Column(name = "AltCrdName")
    private String altCrdName;
    @Column(name = "AltTaxId")
    private String altTaxId;
    @Column(name = "TaxOffice")
    private String taxOffice;
    @Column(name = "U_Municipio")
    private String uMunicipio;

    public DireccionSocioDeNegocios() {
    }

    public DireccionSocioDeNegocios(DireccionSocioDeNegociosPK direccionSocioDeNegociosPK) {
        this.direccionSocioDeNegociosPK = direccionSocioDeNegociosPK;
    }

    public DireccionSocioDeNegocios(String address, String cardCode, char adresType) {
        this.direccionSocioDeNegociosPK = new DireccionSocioDeNegociosPK(address, cardCode, adresType);
    }

    public DireccionSocioDeNegociosPK getDireccionSocioDeNegociosPK() {
        return direccionSocioDeNegociosPK;
    }

    public void setDireccionSocioDeNegociosPK(DireccionSocioDeNegociosPK direccionSocioDeNegociosPK) {
        this.direccionSocioDeNegociosPK = direccionSocioDeNegociosPK;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getLicTradNum() {
        return licTradNum;
    }

    public void setLicTradNum(String licTradNum) {
        this.licTradNum = licTradNum;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getAltCrdName() {
        return altCrdName;
    }

    public void setAltCrdName(String altCrdName) {
        this.altCrdName = altCrdName;
    }

    public String getAltTaxId() {
        return altTaxId;
    }

    public void setAltTaxId(String altTaxId) {
        this.altTaxId = altTaxId;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getUMunicipio() {
        return uMunicipio;
    }

    public void setUMunicipio(String uMunicipio) {
        this.uMunicipio = uMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (direccionSocioDeNegociosPK != null ? direccionSocioDeNegociosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionSocioDeNegocios)) {
            return false;
        }
        DireccionSocioDeNegocios other = (DireccionSocioDeNegocios) object;
        if ((this.direccionSocioDeNegociosPK == null && other.direccionSocioDeNegociosPK != null) || (this.direccionSocioDeNegociosPK != null && !this.direccionSocioDeNegociosPK.equals(other.direccionSocioDeNegociosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.DireccionSocioDeNegocios[ direccionSocioDeNegociosPK=" + direccionSocioDeNegociosPK + " ]";
    }
}
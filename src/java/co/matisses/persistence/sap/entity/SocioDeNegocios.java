package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OCRD")
@NamedQueries({
    @NamedQuery(name = "SocioDeNegocios.findAll", query = "SELECT s FROM SocioDeNegocios s")})
@XmlRootElement
public class SocioDeNegocios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CardCode")
    private String cardCode;
    @Column(name = "CardName")
    private String razonSocial;
    @Column(name = "CardType")
    private Character cardType;
    @Column(name = "Balance")
    private BigDecimal balance;
    @Column(name = "U_BPCO_1Apellido")
    private String apellido1;
    @Column(name = "U_BPCO_2Apellido")
    private String apellido2;
    @Column(name = "U_BPCO_Nombre")
    private String nombres;
    @Column(name = "U_EsAutorret")
    private String autorretenedor;
    @Column(name = "SlpCode")
    private String slpCode;
    @Column(name = "BillToDef")
    private String direccionEstandarFactura;
    @Column(name = "ShipToDef")
    private String direccionEstandarEntrega;
    @Column(name = "LicTradNum")
    private String licTradNum;
    @Column(name = "E_Mail")
    private String email;
    @Column(name = "WTLiable")
    private String sujetoRetencion;
    @Column(name = "CntctPrsn")
    private String contacto;
    @Column(name = "U_BPCO_BPExt")
    private String nacionalidad;
    @Column(name = "U_BPCO_RTC")
    private String regimenTributario;
    @Column(name = "U_BPCO_TDC")
    private String tipoDocumento;
    @Column(name = "U_BPCO_TP")
    private String tipoPersona;
    @Column(name = "U_BPCO_CS")
    private String uBpcoCs;
    @Column(name = "U_Sexo")
    private String sexo;
    @Column(name = "Cellular")
    private String celular;
    @Column(name = "Phone1")
    private String phone1;
    @Column(name = "U_BPCO_Address")
    private String direccion;
    @Temporal(TemporalType.DATE)
    @Column(name = "U_FechaNacimiento")
    private Date fechaNacimiento;

    public SocioDeNegocios() {
    }

    public SocioDeNegocios(String cardCode) {
        this.cardCode = cardCode;
    }

    public Character getCardType() {
        return cardType;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getuBpcoCs() {
        return uBpcoCs;
    }

    public void setuBpcoCs(String uBpcoCs) {
        this.uBpcoCs = uBpcoCs;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAutorretenedor() {
        return autorretenedor;
    }

    public void setAutorretenedor(String autorretenedor) {
        this.autorretenedor = autorretenedor;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSujetoRetencion() {
        return sujetoRetencion;
    }

    public void setSujetoRetencion(String sujetoRetencion) {
        this.sujetoRetencion = sujetoRetencion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(String slpCode) {
        this.slpCode = slpCode;
    }

    public String getLicTradNum() {
        return licTradNum;
    }

    public void setLicTradNum(String licTradNum) {
        this.licTradNum = licTradNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRegimenTributario() {
        return regimenTributario;
    }

    public void setRegimenTributario(String regimenTributario) {
        this.regimenTributario = regimenTributario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccionEstandarEntrega() {
        return direccionEstandarEntrega;
    }

    public void setDireccionEstandarEntrega(String direccionEstandarEntrega) {
        this.direccionEstandarEntrega = direccionEstandarEntrega;
    }

    public String getDireccionEstandarFactura() {
        return direccionEstandarFactura;
    }

    public void setDireccionEstandarFactura(String direccionEstandarFactura) {
        this.direccionEstandarFactura = direccionEstandarFactura;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.cardCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SocioDeNegocios other = (SocioDeNegocios) obj;
        return Objects.equals(this.cardCode, other.cardCode);
    }
}

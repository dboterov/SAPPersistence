package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jguisao
 */
@Entity
@Table(name = "OCPR")
@NamedQueries({
    @NamedQuery(name = "PersonaContacto.findAll", query = "SELECT p FROM PersonaContacto p")})
@XmlRootElement
public class PersonaContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CntctCode")
    private Integer cntctCode;
    @Column(name = "Name")
    private String name;
    @Column(name = "CardCode")
    private String cardCode;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Address")
    private String address;
    @Column(name = "Tel1")
    private String tel1;
    @Column(name = "Cellolar")
    private String cellolar;
    @Column(name = "E_MailL")
    private String eMailL;

    public PersonaContacto() {
    }

    public Integer getCntctCode() {
        return cntctCode;
    }

    public void setCntctCode(Integer CntctCode) {
        this.cntctCode = CntctCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getCellolar() {
        return cellolar;
    }

    public void setCellolar(String cellolar) {
        this.cellolar = cellolar;
    }

    public String geteMailL() {
        return eMailL;
    }

    public void seteMailL(String eMailL) {
        this.eMailL = eMailL;
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
        return Objects.equals(this.cardCode, other.getCardCode());
    }
}

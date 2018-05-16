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
@Table(name = "OCLT")
public class TipoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private Long code;
    @Column(name = "Name")
    private String name;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "UserSign")
    private Long userSign;
    @Column(name = "Active")
    private Character active;

    public TipoActividad() {
    }

    public TipoActividad(Long code) {
        this.code = code;
    }

    public TipoActividad(Long code, String name, Character dataSource, Long userSign, Character active) {
        this.code = code;
        this.name = name;
        this.dataSource = dataSource;
        this.userSign = userSign;
        this.active = active;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Long getUserSign() {
        return userSign;
    }

    public void setUserSign(Long userSign) {
        this.userSign = userSign;
    }

    public Character getActive() {
        return active;
    }

    public void setActive(Character active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActividad)) {
            return false;
        }
        TipoActividad other = (TipoActividad) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.TipoActividad[ id=" + code + " ]";
    }
}

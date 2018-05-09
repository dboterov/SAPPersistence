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
@Table(name = "OCLS")
public class TemaActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private Long code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type")
    private Integer type;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "UserSign")
    private Long userSign;
    @Column(name = "Active")
    private Character active;

    public TemaActividad() {
    }

    public TemaActividad(Long code) {
        this.code = code;
    }

    public TemaActividad(Long code, String name, Integer type, Character dataSource, Long userSign, Character active) {
        this.code = code;
        this.name = name;
        this.type = type;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        if (!(object instanceof TemaActividad)) {
            return false;
        }
        TemaActividad other = (TemaActividad) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.TemaActividad[ code=" + code + " ]";
    }
}

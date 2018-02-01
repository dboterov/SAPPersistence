package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "OUDP")
public class CentroCostosEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Code")
    private Integer code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "UserSign")
    private Integer userSign;
    @Column(name = "Father")
    private String father;

    public CentroCostosEmpleados() {
    }

    public CentroCostosEmpleados(Integer code) {
        this.code = code;
    }

    public CentroCostosEmpleados(Integer code, String name, String remarks, Integer userSign, String father) {
        this.code = code;
        this.name = name;
        this.remarks = remarks;
        this.userSign = userSign;
        this.father = father;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getUserSign() {
        return userSign;
    }

    public void setUserSign(Integer userSign) {
        this.userSign = userSign;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
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
        if (!(object instanceof CentroCostosEmpleados)) {
            return false;
        }
        CentroCostosEmpleados other = (CentroCostosEmpleados) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.CentroCostosEmpleados[ Code=" + code + " ]";
    }
}

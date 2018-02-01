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
@Table(name = "OUSR")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USERID")
    private Integer userID;
    @Column(name = "USER_CODE")
    private String userCode;
    @Column(name = "Locked")
    private Character Locked;

    public Usuario() {
    }

    public Usuario(Integer userID) {
        this.userID = userID;
    }

    public Usuario(Integer userID, String userCode, Character Locked) {
        this.userID = userID;
        this.userCode = userCode;
        this.Locked = Locked;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Character getLocked() {
        return Locked;
    }

    public void setLocked(Character Locked) {
        this.Locked = Locked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.Usuario[ USERID=" + userID + " ]";
    }
}

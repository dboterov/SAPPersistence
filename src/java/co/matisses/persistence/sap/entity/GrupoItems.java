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

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OITB")
@NamedQueries({@NamedQuery(name = "GrupoItems.findAll", query = "SELECT i FROM GrupoItems i")})
public class GrupoItems implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "ItmsGrpCod")
    private Integer itmsGrpCod;
    @Column(name = "ItmsGrpNam")
    private String itmsGrpNam;

    public GrupoItems() {
    }
    
    public GrupoItems(Integer itmsGrpCod) {
        this.itmsGrpCod = itmsGrpCod;
    }

    public GrupoItems(Integer itmsGrpCod, String itmsGrpNam) {
        this.itmsGrpCod = itmsGrpCod;
        this.itmsGrpNam = itmsGrpNam;
    }

    public Integer getItmsGrpCod() {
        return itmsGrpCod;
    }

    public void setItmsGrpCod(Integer itmsGrpCod) {
        this.itmsGrpCod = itmsGrpCod;
    }

    public String getItmsGrpNam() {
        return itmsGrpNam;
    }

    public void setItmsGrpNam(String itmsGrpNam) {
        this.itmsGrpNam = itmsGrpNam;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.itmsGrpCod);
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
        final GrupoItems other = (GrupoItems) obj;
        if (!Objects.equals(this.itmsGrpCod, other.itmsGrpCod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoItems{" + "itmsGrpCod=" + itmsGrpCod + ", itmsGrpNam=" + itmsGrpNam + '}';
    }
}

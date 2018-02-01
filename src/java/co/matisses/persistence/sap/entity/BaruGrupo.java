package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "[@BARU_GRUPO]")
@NamedQueries({
    @NamedQuery(name = "BaruGrupo.findAll", query = "SELECT b FROM BaruGrupo b")})
public class BaruGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Size(max = 20)
    @Column(name = "U_Cod_Dep")
    private String uCodDep;
    @Size(max = 100)
    @Column(name = "U_Nom_Dep")
    private String uNomDep;
    @Size(max = 10)
    @Column(name = "U_Web")
    private String uWeb;
    @Size(max = 78)
    @Column(name = "U_url_title")
    private String uurltitle;
    @Size(max = 1073741823)
    @Column(name = "U_metadescription")
    private String umetadescription;

    public BaruGrupo() {
    }

    public BaruGrupo(String code) {
        this.code = code;
    }

    public BaruGrupo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUCodDep() {
        return uCodDep;
    }

    public void setUCodDep(String uCodDep) {
        this.uCodDep = uCodDep;
    }

    public String getUNomDep() {
        return uNomDep;
    }

    public void setUNomDep(String uNomDep) {
        this.uNomDep = uNomDep;
    }

    public String getUWeb() {
        return uWeb;
    }

    public void setUWeb(String uWeb) {
        this.uWeb = uWeb;
    }

    public String getUurltitle() {
        return uurltitle;
    }

    public void setUurltitle(String uurltitle) {
        this.uurltitle = uurltitle;
    }

    public String getUmetadescription() {
        return umetadescription;
    }

    public void setUmetadescription(String umetadescription) {
        this.umetadescription = umetadescription;
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
        if (!(object instanceof BaruGrupo)) {
            return false;
        }
        BaruGrupo other = (BaruGrupo) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruGrupo[ code=" + code + " ]";
    }
    
}

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
@Table(name = "dbo.[@BARU_DET_DOC_PDTE]")
public class BaruDetalleDocumentoPendiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "U_CodeDocPdte")
    private String uCodeDocPdte;
    @Basic(optional = false)
    @Column(name = "U_TipoDocumento")
    private String uTipoDocumento;
    @Basic(optional = false)
    @Column(name = "U_NumeroDocumento")
    private Integer uNumeroDocumento;

    public BaruDetalleDocumentoPendiente() {
    }

    public BaruDetalleDocumentoPendiente(String code) {
        this.code = code;
    }

    public BaruDetalleDocumentoPendiente(String code, String name, String uCodeDocPdte, String uTipoDocumento, Integer uNumeroDocumento) {
        this.code = code;
        this.name = name;
        this.uCodeDocPdte = uCodeDocPdte;
        this.uTipoDocumento = uTipoDocumento;
        this.uNumeroDocumento = uNumeroDocumento;
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

    public String getuCodeDocPdte() {
        return uCodeDocPdte;
    }

    public void setuCodeDocPdte(String uCodeDocPdte) {
        this.uCodeDocPdte = uCodeDocPdte;
    }

    public String getuTipoDocumento() {
        return uTipoDocumento;
    }

    public void setuTipoDocumento(String uTipoDocumento) {
        this.uTipoDocumento = uTipoDocumento;
    }

    public Integer getuNumeroDocumento() {
        return uNumeroDocumento;
    }

    public void setuNumeroDocumento(Integer uNumeroDocumento) {
        this.uNumeroDocumento = uNumeroDocumento;
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
        if (!(object instanceof BaruDetalleDocumentoPendiente)) {
            return false;
        }
        BaruDetalleDocumentoPendiente other = (BaruDetalleDocumentoPendiente) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruDetalleDocumentoPendiente[ Code=" + code + " ]";
    }
}

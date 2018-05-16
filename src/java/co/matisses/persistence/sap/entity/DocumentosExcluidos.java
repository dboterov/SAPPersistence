package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "[@BARU_DOC_EXCLUIDOS]")
public class DocumentosExcluidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "U_DocNum")
    private String docNum;
    @Basic(optional = false)
    @Column(name = "U_FechaExclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExclusion;
    @Basic(optional = false)
    @Column(name = "U_UsuarioExcluye")
    private String usuarioExcluye;
    @Basic(optional = false)
    @Column(name = "U_TipoDocumento")
    private String tipoDocumento;

    public DocumentosExcluidos() {
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

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public Date getFechaExclusion() {
        return fechaExclusion;
    }

    public void setFechaExclusion(Date fechaExclusion) {
        this.fechaExclusion = fechaExclusion;
    }

    public String getUsuarioExcluye() {
        return usuarioExcluye;
    }

    public void setUsuarioExcluye(String usuarioExcluye) {
        this.usuarioExcluye = usuarioExcluye;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.code);
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
        final DocumentosExcluidos other = (DocumentosExcluidos) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DocumentosExcluidos{" + "code=" + code + ", name=" + name + ", docNum=" + docNum + ", fechaExclusion=" + fechaExclusion + ", usuarioExcluye=" + usuarioExcluye + ", tipoDocumento=" + tipoDocumento + '}';
    }
}

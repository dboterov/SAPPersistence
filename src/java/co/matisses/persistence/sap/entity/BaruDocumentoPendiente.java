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
@Table(name = "dbo.[@BARU_DOC_PENDIENTE]")
public class BaruDocumentoPendiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "U_NumeroDocumento")
    private Integer uNumeroDocumento;
    @Basic(optional = false)
    @Column(name = "U_TipoDocumento")
    private String uTipoDocumento;
    @Basic(optional = false)
    @Column(name = "U_Estado")
    private String uEstado;
    @Basic(optional = false)
    @Column(name = "U_Intentos")
    private Integer uIntentos;
    @Basic(optional = true)
    @Column(name = "U_UltimoIntento")
    private String uUltimoIntento;
    @Basic(optional = true)
    @Column(name = "U_Procesando")
    private String uProcesando;

    public BaruDocumentoPendiente() {
    }

    public BaruDocumentoPendiente(String code) {
        this.code = code;
    }

    public BaruDocumentoPendiente(String code, String name, Integer uNumeroDocumento, String uTipoDocumento, String uEstado, Integer uIntentos, String uUltimoIntento) {
        this.code = code;
        this.name = name;
        this.uNumeroDocumento = uNumeroDocumento;
        this.uTipoDocumento = uTipoDocumento;
        this.uEstado = uEstado;
        this.uIntentos = uIntentos;
        this.uUltimoIntento = uUltimoIntento;
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

    public Integer getuNumeroDocumento() {
        return uNumeroDocumento;
    }

    public void setuNumeroDocumento(Integer uNumeroDocumento) {
        this.uNumeroDocumento = uNumeroDocumento;
    }

    public String getuTipoDocumento() {
        return uTipoDocumento;
    }

    public void setuTipoDocumento(String uTipoDocumento) {
        this.uTipoDocumento = uTipoDocumento;
    }

    public String getuEstado() {
        return uEstado;
    }

    public void setuEstado(String uEstado) {
        this.uEstado = uEstado;
    }

    public Integer getuIntentos() {
        return uIntentos;
    }

    public void setuIntentos(Integer uIntentos) {
        this.uIntentos = uIntentos;
    }

    public String getuUltimoIntento() {
        return uUltimoIntento;
    }

    public void setuUltimoIntento(String uUltimoIntento) {
        this.uUltimoIntento = uUltimoIntento;
    }

    public String getuProcesando() {
        return uProcesando;
    }

    public void setuProcesando(String uProcesando) {
        this.uProcesando = uProcesando;
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
        if (!(object instanceof BaruDocumentoPendiente)) {
            return false;
        }
        BaruDocumentoPendiente other = (BaruDocumentoPendiente) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.BaruDocumentoPendiente[ Code=" + code + " ]";
    }
}

package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ygil
 */
@Embeddable
public class ValoracionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "empID")
    private int empID;
    @Basic(optional = false)
    @Column(name = "line")
    private short line;

    public ValoracionPK() {
    }

    public ValoracionPK(int empID, short line) {
        this.empID = empID;
        this.line = line;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public short getLine() {
        return line;
    }

    public void setLine(short line) {
        this.line = line;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.empID;
        hash = 23 * hash + this.line;
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
        final ValoracionPK other = (ValoracionPK) obj;
        if (this.empID != other.empID) {
            return false;
        }
        if (this.line != other.line) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ValoracionPK{" + "empID=" + empID + ", line=" + line + '}';
    }
}

package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ReciboCaja;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ReciboCajaFacade extends AbstractFacade<ReciboCaja> {
    
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(ReciboCajaFacade.class.getSimpleName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ReciboCajaFacade() {
        super(ReciboCaja.class);
    }
    
    public List<Object[]> obtenerPagosFactura(Integer docEntry) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT rec.DocNum AS ReciboCaja, ");
        sb.append("       rec.CashSum AS ValorEfectivo, ");
        sb.append("       ISNULL(recTarjetas.CreditSum, 0) AS ValorTarjetas, ");
        sb.append("       ISNULL(recCheques.CheckSum, 0) AS ValorCheque, ");
        sb.append("       ISNULL(rec.TrsfrSum, 0) AS ValorCruces, ");
        sb.append("       CONVERT(VARCHAR(100), usuario.U_NAME) AS Usuario ");
        sb.append("FROM   RCT2 recFac ");
        sb.append("INNER  JOIN ORCT rec ON rec.DocNum = recFac.DocNum ");
        sb.append("INNER  JOIN OUSR usuario ON usuario.USERID = rec.UserSign ");
        sb.append("LEFT   JOIN RCT3 recTarjetas ON recTarjetas.DocNum = recFac.DocNum ");
        sb.append("LEFT   JOIN RCT1 recCheques ON recCheques.DocNum = recFac.DocNum ");
        sb.append("WHERE  recFac.DocEntry = ");
        sb.append(docEntry);
        
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
    
    public ReciboCaja obtenerReciboCaja(String docNumFactura) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT * ");
        sb.append("FROM   ORCT enc ");
        sb.append("INNER  JOIN RCT2 fac ON fac.DocNum = enc.DocNum ");
        sb.append("INNER  JOIN OINV inv ON inv.DocEntry = fac.DocEntry ");
        sb.append("WHERE  inv.DocNum = '");
        sb.append(docNumFactura);
        sb.append("'");
        
        try {
            return (ReciboCaja) em.createNativeQuery(sb.toString(), ReciboCaja.class).getSingleResult();
        } catch (NoResultException e) {
            log.log(Level.WARNING, "No existe un recibo de caja para la factura {0}", docNumFactura);
            return null;
        } catch (Exception e) {
            log.log(Level.SEVERE, "No se pudo consultar el recibo de caja para la factura " + docNumFactura, e);
            return null;
        }
    }
    
    public Long obtenerIdAsiento(Long docEntryRecibo, String cuenta) {
        StringBuilder sb = new StringBuilder();
        sb.append("select entr.TransId from ojdt entr inner join JDT1 line on line.TransId = entr.TransId ");
        sb.append("where entr.Ref1 = '");
        sb.append(docEntryRecibo);
        sb.append("'and line.Account = '");
        sb.append(cuenta);
        sb.append("'");
        try {
            return ((Integer) em.createNativeQuery(sb.toString()).setMaxResults(1).getSingleResult()).longValue();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar el id del asiento. ", e);
            return -1L;
        }
    }
}

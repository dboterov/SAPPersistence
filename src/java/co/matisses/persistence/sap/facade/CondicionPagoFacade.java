package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.CondicionPago;
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
public class CondicionPagoFacade extends AbstractFacade<CondicionPago> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(CondicionPagoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CondicionPagoFacade() {
        super(CondicionPago.class);
    }

    public List<CondicionPago> obtenerCondicionesPagoFactura() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OCTG ");
        sb.append("WHERE  GroupNum <> -1 ");

        try {
            return em.createNativeQuery(sb.toString(), CondicionPago.class).getResultList();
        } catch (NoResultException e) {
            console.log(Level.SEVERE, "No se encontraron condiciones de pago");
            return null;
        } catch (Exception e) {
            console.log(Level.SEVERE, "Ocurrio un error al consultar las condiciones de pago. ", e);
            return null;
        }
    }
}

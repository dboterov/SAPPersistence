package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Entrada;
import co.matisses.persistence.sap.entity.Entrada_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class EntradaFacade extends AbstractFacade<Entrada> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(EntradaFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntradaFacade() {
        super(Entrada.class);
    }

    public List<Entrada> obtenerEntradasFactura(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entrada> cq = cb.createQuery(Entrada.class);
        Root<Entrada> entrada = cq.from(Entrada.class);

        cq.where(cb.equal(entrada.get(Entrada_.ref2), docNum.toString()));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "Error al consultar la entrada de la factura.  ", e);
            return null;
        }
    }
}

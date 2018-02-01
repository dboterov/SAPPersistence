package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruMaterialArticulo;
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
public class BaruMaterialArticuloFacade extends AbstractFacade<BaruMaterialArticulo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruMaterialArticuloFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruMaterialArticuloFacade() {
        super(BaruMaterialArticulo.class);
    }

    public List<BaruMaterialArticulo> obtenerMaterialesArticulo(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruMaterialArticulo> cq = cb.createQuery(BaruMaterialArticulo.class);
        Root<BaruMaterialArticulo> material = cq.from(BaruMaterialArticulo.class);

        cq.where(cb.equal(material.get("uItemCode"), referencia));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public int obtenerSiguienteCodigo() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT MAX(CONVERT(INT, code)) ");
        sb.append("FROM   [@BARU_MATXART] ");

        try {
            return (int) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }
}

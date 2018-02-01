package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruParametrosWeb;
import co.matisses.persistence.sap.entity.BaruParametrosWeb_;
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
public class BaruParametrosWebFacade extends AbstractFacade<BaruParametrosWeb> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruParametrosWebFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruParametrosWebFacade() {
        super(BaruParametrosWeb.class);
    }

    public String obtenerCuentaGeneralAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<BaruParametrosWeb> parametros = cq.from(BaruParametrosWeb.class);

        cq.where(cb.equal(parametros.get(BaruParametrosWeb_.code), "cajaGeneralMatisses" + almacen));
        cq.select(parametros.get(BaruParametrosWeb_.name));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}

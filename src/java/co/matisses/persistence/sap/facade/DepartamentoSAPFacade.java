package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DepartamentoSAP;
import co.matisses.persistence.sap.entity.DepartamentoSAP_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
@LocalBean
public class DepartamentoSAPFacade extends AbstractFacade<DepartamentoSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(DepartamentoSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoSAPFacade() {
        super(DepartamentoSAP.class);
    }

    public List<DepartamentoSAP> obtenerDepartamentosColombia() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(DepartamentoSAP.class);
        Root departamento = cq.from(DepartamentoSAP.class);

        cq.where(cb.equal(departamento.get("departamentoPK").get("country"), "CO"));
        cq.orderBy(cb.asc(departamento.get(DepartamentoSAP_.name)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los departamentos de Colombia. ", e);
        }
        return null;
    }
}

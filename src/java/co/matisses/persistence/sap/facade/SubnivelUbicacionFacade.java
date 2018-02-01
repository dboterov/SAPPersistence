package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.SubnivelUbicacion;
import co.matisses.persistence.sap.entity.SubnivelUbicacion_;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author ygil
 */
@Stateless
public class SubnivelUbicacionFacade extends AbstractFacade<SubnivelUbicacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(SubnivelUbicacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubnivelUbicacionFacade() {
        super(SubnivelUbicacion.class);
    }

    public boolean validarExistenciSubNivel(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SubnivelUbicacion> cq = cb.createQuery(SubnivelUbicacion.class);
        Root<SubnivelUbicacion> subnivel = cq.from(SubnivelUbicacion.class);

        cq.where(cb.equal(subnivel.get(SubnivelUbicacion_.slCode), code));

        try {
            SubnivelUbicacion sub = em.createQuery(cq).getSingleResult();
            if (sub != null && sub.getAbsEntry() != null && sub.getAbsEntry() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            console.log(Level.SEVERE, "No se encontro la propiedad solicitada.", e);
            return false;
        }
    }
}

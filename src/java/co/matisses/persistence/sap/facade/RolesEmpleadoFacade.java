package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.RolesEmpleado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class RolesEmpleadoFacade extends AbstractFacade<RolesEmpleado> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(RolesEmpleadoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesEmpleadoFacade() {
        super(RolesEmpleado.class);
    }

    public RolesEmpleado obtenerRolEmpleado(Integer empID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RolesEmpleado> cq = cb.createQuery(RolesEmpleado.class);
        Root<RolesEmpleado> rol = cq.from(RolesEmpleado.class);

        cq.where(cb.equal(rol.get("rolesEmpleadoPK").get("empID"), empID));

        try {
            return em.createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron roles para el empleado con id {0}", empID);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el rol del empleado. ", e);
        }
        return null;
    }
}

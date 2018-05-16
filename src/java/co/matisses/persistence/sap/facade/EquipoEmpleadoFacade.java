package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.EquipoEmpleado;
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
public class EquipoEmpleadoFacade extends AbstractFacade<EquipoEmpleado> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(EquipoEmpleadoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoEmpleadoFacade() {
        super(EquipoEmpleado.class);
    }

    public EquipoEmpleado obtenerEquipoEmpleado(Integer empID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EquipoEmpleado> cq = cb.createQuery(EquipoEmpleado.class);
        Root<EquipoEmpleado> equipo = cq.from(EquipoEmpleado.class);

        cq.where(cb.equal(equipo.get("equipoEmpleadoPK").get("empID"), empID));

        try {
            return em.createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontro equipo para el empleado con id {0}", empID);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el equipo para el empleado. ", e);
        }
        return null;
    }
}

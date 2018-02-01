package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.SucursalEmpleado;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class SucursalEmpleadoFacade extends AbstractFacade<SucursalEmpleado> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(SucursalEmpleadoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalEmpleadoFacade() {
        super(SucursalEmpleado.class);
    }
}

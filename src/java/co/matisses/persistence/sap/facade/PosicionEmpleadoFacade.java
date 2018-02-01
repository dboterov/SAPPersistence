package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.PosicionEmpleado;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class PosicionEmpleadoFacade extends AbstractFacade<PosicionEmpleado> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSULE = Logger.getLogger(PosicionEmpleadoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PosicionEmpleadoFacade() {
        super(PosicionEmpleado.class);
    }
}

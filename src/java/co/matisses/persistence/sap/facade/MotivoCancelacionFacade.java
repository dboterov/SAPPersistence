package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.MotivoCancelacion;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class MotivoCancelacionFacade extends AbstractFacade<MotivoCancelacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(MotivoCancelacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoCancelacionFacade() {
        super(MotivoCancelacion.class);
    }
}

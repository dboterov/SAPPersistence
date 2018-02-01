package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ClaseFormacion;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ClaseFormacionFacade extends AbstractFacade<ClaseFormacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(ClaseFormacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClaseFormacionFacade() {
        super(ClaseFormacion.class);
    }
}

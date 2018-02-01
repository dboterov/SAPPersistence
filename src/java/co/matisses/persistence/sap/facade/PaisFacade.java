package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Pais;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(PaisFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }
}

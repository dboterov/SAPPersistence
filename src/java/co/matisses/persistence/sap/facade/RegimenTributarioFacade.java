package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.RegimenTributario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dbotero
 */
@Stateless
public class RegimenTributarioFacade extends AbstractFacade<RegimenTributario> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegimenTributarioFacade() {
        super(RegimenTributario.class);
    }
    
}

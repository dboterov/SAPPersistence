package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.GrupoItems;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dbotero
 */
@Stateless
@LocalBean
public class GrupoItemsFacade extends AbstractFacade<GrupoItems> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoItemsFacade() {
        super(GrupoItems.class);
    }
    
}

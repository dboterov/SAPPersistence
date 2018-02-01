package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruMarca;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dbotero
 */
@Stateless
public class BaruMarcaFacade extends AbstractFacade<BaruMarca> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruMarcaFacade() {
        super(BaruMarca.class);
    }
    
}

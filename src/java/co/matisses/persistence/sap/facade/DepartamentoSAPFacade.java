package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DepartamentoSAP;
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
public class DepartamentoSAPFacade extends AbstractFacade<DepartamentoSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoSAPFacade() {
        super(DepartamentoSAP.class);
    }

}

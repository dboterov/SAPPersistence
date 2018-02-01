package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DocumentosExcluidos;
import co.matisses.persistence.sap.entity.DocumentosExcluidos_;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class DocumentosExcluidosFacade extends AbstractFacade<DocumentosExcluidos> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(DocumentosExcluidosFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentosExcluidosFacade() {
        super(DocumentosExcluidos.class);
    }

    public boolean verificarDocumentoExcluidoExiste(String documento, String tipo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocumentosExcluidos> cq = cb.createQuery(DocumentosExcluidos.class);
        Root<DocumentosExcluidos> docExcluido = cq.from(DocumentosExcluidos.class);

        cq.where(cb.and(cb.equal(docExcluido.get(DocumentosExcluidos_.docNum), documento),
                cb.equal(docExcluido.get(DocumentosExcluidos_.tipoDocumento), tipo)));

        try {
            if ((em.createQuery(cq).getResultList()).size() > 0) {
                return true;
            }
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
        }
        return false;
    }
}

package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruDocumentoPendiente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruDocumentoPendienteFacade extends AbstractFacade<BaruDocumentoPendiente> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(BaruDocumentoPendienteFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruDocumentoPendienteFacade() {
        super(BaruDocumentoPendiente.class);
    }

    public List<BaruDocumentoPendiente> obtenerDocumentosPendientes(Character procesando) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   [@BARU_DOC_PENDIENTE] ");
        sb.append("WHERE  (U_Procesando = '");
        sb.append(procesando);
        sb.append("' ");
        sb.append("OR     U_Procesando IS NULL) ");
        sb.append("AND    U_Estado IN ('P', 'E') ");

        try {
            return em.createNativeQuery(sb.toString(), BaruDocumentoPendiente.class).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}

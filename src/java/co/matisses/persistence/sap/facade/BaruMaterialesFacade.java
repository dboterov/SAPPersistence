package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruMateriales;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruMaterialesFacade extends AbstractFacade<BaruMateriales> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruMaterialesFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruMaterialesFacade() {
        super(BaruMateriales.class);
    }

    public List<BaruMateriales> obtenerMaterialesArticulo(String referencia) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT materiales.* ");
        sb.append("FROM   [@BARU_MATXART] materialesArticulo ");
        sb.append("INNER  JOIN [@BARU_MATERIALES] materiales ON materiales.Code = materialesArticulo.U_matCode ");
        sb.append("WHERE  materialesArticulo.U_itemCode = '");
        sb.append(referencia);
        sb.append("' ORDER BY materiales.Name");

        try {
            return em.createNativeQuery(sb.toString(), BaruMateriales.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public String obtenerSiguienteID() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR, MAX(CONVERT(NUMERIC, CODE)) + 1) ");
        sb.append("FROM   [@BARU_MATERIALES] ");

        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al obtener el siguiente ID de material. ", e);
        }
        return null;
    }
}

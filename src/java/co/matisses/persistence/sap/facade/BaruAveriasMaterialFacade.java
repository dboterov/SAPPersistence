package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruAveriasMaterial;
import co.matisses.persistence.sap.entity.BaruAveriasMaterial_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruAveriasMaterialFacade extends AbstractFacade<BaruAveriasMaterial> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruAveriasMaterialFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruAveriasMaterialFacade() {
        super(BaruAveriasMaterial.class);
    }

    public List<BaruAveriasMaterial> obtenerAveriasMaterial(String material) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(BaruAveriasMaterial.class);
        Root averia = cq.from(BaruAveriasMaterial.class);

        cq.where(cb.equal(averia.get(BaruAveriasMaterial_.uMaterial), material));
        cq.orderBy(cb.asc(averia.get(BaruAveriasMaterial_.uProblema)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las averias por material. ", e);
        }
        return null;
    }

    public List<String> obtenerAverias() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(100), U_Problema) AS U_Problema ");
        sb.append("FROM   [@BARU_AVERIASXMAT] ");
        sb.append("ORDER  BY U_Problema ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las averias. ", e);
        }
        return null;
    }

    public Integer obtenerSiguienteID() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT MAX(CONVERT(INT, Code)) + 1 ");
        sb.append("FROM   [@BARU_AVERIASXMAT] ");

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el siguiente id de averia. ", e);
            return 1;
        }
    }
}

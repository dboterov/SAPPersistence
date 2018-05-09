package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruAverias;
import co.matisses.persistence.sap.entity.BaruAverias_;
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
public class BaruAveriasFacade extends AbstractFacade<BaruAverias> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruAveriasFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruAveriasFacade() {
        super(BaruAverias.class);
    }

    public BaruAverias obtenerAveria(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(BaruAverias.class);
        Root averia = cq.from(BaruAverias.class);

        cq.where(cb.equal(averia.get(BaruAverias_.name), name));

        try {
            return (BaruAverias) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la averia por nombre. ", e);
        }
        return null;
    }

    public List<BaruAverias> obtenerAveriaPorMaterial(String codigoMaterial) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT a.Code, a.Name ");
        sb.append("FROM  [@BARU_AVERIAS] a ");
        sb.append("INNER JOIN [@BARU_AVERIASXMAT] am ON a.Code = am.U_Problema ");
        sb.append("WHERE am.U_Material = '");
        sb.append(codigoMaterial);
        sb.append("' ORDER BY a.Name ASC");

        try {
            return em.createNativeQuery(sb.toString(), BaruAverias.class).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las averias por material. ", e);
        }
        return null;
    }
}

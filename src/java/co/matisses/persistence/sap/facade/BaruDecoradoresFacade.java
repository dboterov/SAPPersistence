package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruDecoradores;
import co.matisses.persistence.sap.entity.BaruDecoradores_;
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
public class BaruDecoradoresFacade extends AbstractFacade<BaruDecoradores> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruDecoradoresFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruDecoradoresFacade() {
        super(BaruDecoradores.class);
    }

    public List<BaruDecoradores> obtenerDecoradoresActivos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruDecoradores> cq = cb.createQuery(BaruDecoradores.class);
        Root<BaruDecoradores> decoradores = cq.from(BaruDecoradores.class);

        cq.where(cb.equal(decoradores.get(BaruDecoradores_.uEstado), "A"));
        cq.orderBy(cb.asc(decoradores.get(BaruDecoradores_.name)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<BaruDecoradores> obtenerPlanificadoresActivos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruDecoradores> cq = cb.createQuery(BaruDecoradores.class);
        Root<BaruDecoradores> decoradores = cq.from(BaruDecoradores.class);

        cq.where(cb.equal(decoradores.get(BaruDecoradores_.uEstado), "A"),
                cb.equal(decoradores.get(BaruDecoradores_.uTipo), "P"));
        cq.orderBy(cb.asc(decoradores.get(BaruDecoradores_.name)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public String consultarCodigoDecorador(String documento) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<BaruDecoradores> root = cq.from(BaruDecoradores.class);

        cq.where(cb.and(cb.equal(root.get(BaruDecoradores_.uNit), documento)),
                cb.equal(root.get(BaruDecoradores_.uEstado), "A"));
        cq.select(root.get(BaruDecoradores_.code));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el codigo del decorador ", e);
        }
        return null;
    }

    public String consultarCodigoDecorador(String documento, String tipo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<BaruDecoradores> root = cq.from(BaruDecoradores.class);

        cq.where(cb.and(cb.equal(root.get(BaruDecoradores_.uNit), documento)),
                cb.equal(root.get(BaruDecoradores_.uEstado), "A"),
                cb.equal(root.get(BaruDecoradores_.uTipo), tipo));
        cq.select(root.get(BaruDecoradores_.code));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el codigo del decorador ", e);
        }
        return null;
    }

    public Integer obtenerProximoID() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(INT, MAX(code)) AS id FROM   [@BARU_DECORADORES] ");

        try {
            return ((Integer) em.createNativeQuery(sb.toString()).getSingleResult()) + 1;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el proximo id para la tabla de decoradores. ", e);
        }
        return 0;
    }
}

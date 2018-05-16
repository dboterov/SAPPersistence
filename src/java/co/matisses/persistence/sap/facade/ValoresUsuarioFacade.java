package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ValoresUsuario;
import co.matisses.persistence.sap.entity.ValoresUsuario_;
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
public class ValoresUsuarioFacade extends AbstractFacade<ValoresUsuario> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(ValoresUsuarioFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoresUsuarioFacade() {
        super(ValoresUsuario.class);
    }

    public List<ValoresUsuario> listaTipoContrato() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 6));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para los tipos de contrato");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para los tipos de contrato. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaMotivosCursoAltura() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 10));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para los motivos de curso de altura");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para los motivos de curso de altura. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaTiposAusentismo() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "HEM1"), cb.equal(valor.get(ValoresUsuario_.fieldID), 0));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para los tipos de ausentismo");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para los tipos de ausentismo. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaArps() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 0));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para las ARP");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para las ARP. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaEpss() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 1));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para las EPS");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para las EPS. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaCajasCompesacion() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 2));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para las CAJAS DE COMPESACION");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para las CAJAS DE COMPESACION. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaPensiones() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 3));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para las PENSIONES");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para las PENSIONES. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listaCesantias() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), "OHEM"), cb.equal(valor.get(ValoresUsuario_.fieldID), 5));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron valores de usuario para las CESANTIAS");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario para las CESANTIAS. ", e);
        }
        return null;
    }

    public List<ValoresUsuario> listarValoresUsuario(Integer fieldID, String tabla) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ValoresUsuario> cq = cb.createQuery(ValoresUsuario.class);
        Root<ValoresUsuario> valor = cq.from(ValoresUsuario.class);

        cq.where(cb.equal(valor.get(ValoresUsuario_.tableID), tabla), cb.equal(valor.get(ValoresUsuario_.fieldID), fieldID));
        cq.orderBy(cb.asc(valor.get(ValoresUsuario_.descr)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los valores de usuario. ", e);
        }
        return null;
    }
}

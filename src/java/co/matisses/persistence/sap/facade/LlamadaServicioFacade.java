package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.LlamadaServicio;
import co.matisses.persistence.sap.entity.LlamadaServicio_;
import java.util.ArrayList;
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
 * @author dbotero
 */
@Stateless
public class LlamadaServicioFacade extends AbstractFacade<LlamadaServicio> {

    private static final Logger CONSOLE = Logger.getLogger(LlamadaServicioFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LlamadaServicioFacade() {
        super(LlamadaServicio.class);
    }

    public LlamadaServicio findByDocNum(Integer callID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LlamadaServicio> cq = cb.createQuery(LlamadaServicio.class);
        Root<LlamadaServicio> root = cq.from(LlamadaServicio.class);

        cq.where(cb.equal(root.get("callID"), callID));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la solicitud de garantia. ", e);
        }
        return null;
    }

    public List<Short> getLastAssignedRequests(Integer howMany) {
        StringBuilder sb = new StringBuilder();

        sb.append("SEELCT TOP ").append(howMany.intValue()).append(" assignee ");
        sb.append("FROM   OSCL ");
        sb.append("ORDER  BY createDate DESC, createTime DESC");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las ultimas solicitudes de servicio asignadas. ", e);
        }

        return new ArrayList<>();
    }

    public List<Object[]> getStatusChanges(Integer docNum) {
        StringBuilder sb = new StringBuilder();

        sb.append("select oscs.statusID ");
        sb.append(", cast(oscs.Name as varchar(20)) name ");
        sb.append(", DATEADD(MINUTE, cast(substring(cast(UpdateTime as varchar(4)), 1, case when len(cast(UpdateTime as varchar(4))) = 4 then 2 else 1 end) as int)*60 + ");
        sb.append(" cast(substring(cast(UpdateTime as varchar(4)), case when len(cast(UpdateTime as varchar(4))) = 4 then 3 else 2 end, 2) as int), ");
        sb.append(" cast(updateDate as datetime)) UpdateDate ");
        sb.append("from   ASCL ");
        sb.append("inner join OSCS on oscs.statusID = ascl.status ");
        sb.append("where  DocNum = ");
        sb.append(docNum);
        sb.append(" union ");
        sb.append("select oscs.statusID ");
        sb.append(" ,cast(oscs.Name as varchar(20)) name ");
        sb.append(", DATEADD(MINUTE, cast(substring(cast(UpdateTime as varchar(4)), 1, case when len(cast(UpdateTime as varchar(4))) = 4 then 2 else 1 end) as int)*60 + ");
        sb.append(" cast(substring(cast(UpdateTime as varchar(4)), case when len(cast(UpdateTime as varchar(4))) = 4 then 3 else 2 end, 2) as int), ");
        sb.append(" cast(updateDate as datetime)) UpdateDate ");
        sb.append("from   OSCL ");
        sb.append("inner join OSCS on oscs.statusID = oscl.status ");
        sb.append("where  DocNum = ");
        sb.append(docNum);
        sb.append(" order by UpdateDate");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, ". ", e);
        }

        return new ArrayList<>();
    }

    public LlamadaServicio obtenerLlamadaServicio(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(LlamadaServicio.class);
        Root llamada = cq.from(LlamadaServicio.class);

        cq.where(cb.equal(llamada.get(LlamadaServicio_.docNum), docNum));

        try {
            return (LlamadaServicio) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la llamada de servicio. ", e);
        }
        return null;
    }

    public List<LlamadaServicio> obtenerLlamadaServicioParametro(String campo, String parametro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(LlamadaServicio.class);
        Root llamada = cq.from(LlamadaServicio.class);

        cq.where(cb.like(llamada.get(campo), "%" + parametro + "%"));
        cq.orderBy(cb.asc(llamada.get(LlamadaServicio_.callID)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la llamada de servicio por parametro. ", e);
        }
        return null;
    }

    public List<LlamadaServicio> obtenerLlamadaAbiertas() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(LlamadaServicio.class);
        Root llamada = cq.from(LlamadaServicio.class);

        cq.where(cb.equal(llamada.get(LlamadaServicio_.status), -3));
        cq.orderBy(cb.asc(llamada.get(LlamadaServicio_.callID)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la llamada de servicio abiertas. ", e);
        }
        return null;
    }
}

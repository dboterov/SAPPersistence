package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.LlamadaDeServicio;
import java.util.ArrayList;
import java.util.List;
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
public class LlamadaDeServicioFacade extends AbstractFacade<LlamadaDeServicio> {

    private static final Logger log = Logger.getLogger(LlamadaDeServicioFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LlamadaDeServicioFacade() {
        super(LlamadaDeServicio.class);
    }

    public LlamadaDeServicio findByDocNum(Integer callID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LlamadaDeServicio> cq = cb.createQuery(LlamadaDeServicio.class);
        Root<LlamadaDeServicio> root = cq.from(LlamadaDeServicio.class);
        cq.where(cb.equal(root.get("callID"), callID));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la solicitud de garantia. ", e);
        }
        return null;
    }

    public List<Short> getLastAssignedRequests(Integer howMany) {
        StringBuilder sb = new StringBuilder();
        sb.append("select top ");
        sb.append(howMany.intValue());
        sb.append(" assignee ");
        sb.append("from OSCL ");
        sb.append("order by createDate desc, createTime desc");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al obtener las ultimas solicitudes de servicio asignadas. ", e);
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
            log.log(Level.SEVERE, ". ", e);
        }

        return new ArrayList<>();
    }
}

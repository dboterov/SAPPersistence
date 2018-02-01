package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruColorArticulo;
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
 * @author ygil
 */
@Stateless
public class BaruColorArticuloFacade extends AbstractFacade<BaruColorArticulo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruColorArticuloFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruColorArticuloFacade() {
        super(BaruColorArticulo.class);
    }

    public List<BaruColorArticulo> obtenerColoresArticulo(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruColorArticulo> cq = cb.createQuery(BaruColorArticulo.class);
        Root<BaruColorArticulo> color = cq.from(BaruColorArticulo.class);

        cq.where(cb.equal(color.get("uArticulo"), referencia));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public int obtenerSiguienteCodigo() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT MAX(CONVERT(INT, code)) ");
        sb.append("FROM   [@BARU_COLXART] ");

        try {
            return (int) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public List<Object[]> listarColores() {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(col.Code as varchar(10)) colCode, ");
        sb.append("cast(col.Name as varchar(30)) colName, cast(col.U_colorgenerico as varchar(10)) genCode,");
        sb.append("cast(col.U_cod_hexa as varchar(10)) colHexa, cast(gen.Name as varchar(30)) genName ");
        sb.append("from [@BARUCOLOR] col ");
        sb.append("inner join [@BARU_COLOR_GENERICO] gen on gen.Code = col.U_colorgenerico");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los colores en SAP. ", e);
            return new ArrayList<>();
        }
    }
}

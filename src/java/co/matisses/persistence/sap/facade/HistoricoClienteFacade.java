package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.HistoricoCliente;
import java.math.BigDecimal;
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
public class HistoricoClienteFacade extends AbstractFacade<HistoricoCliente> {

    private static final Logger CONSOLE = Logger.getLogger(ItemInventarioFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoClienteFacade() {
        super(HistoricoCliente.class);
    }

    public List<HistoricoCliente> obtenerHistoricoCliente(String parametro, String valor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<HistoricoCliente> cq = cb.createQuery(HistoricoCliente.class);
        Root<HistoricoCliente> ventas = cq.from(HistoricoCliente.class);

        cq.where(cb.like(ventas.<String>get(parametro), "%" + valor + "%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el historico del cliente. ", e);
            return null;
        }
    }

    public Double obtenerTotalSinIVA(String nit, String factura) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT SUM(precioSinIva * cantidadVenta) ");
        sb.append("FROM   Baru_HistoricoClientes ");
        sb.append("WHERE  nit = '");
        sb.append(nit);
        sb.append("' AND  factura = '");
        sb.append(factura);
        sb.append("' ");

        try {
            return ((BigDecimal) em.createNativeQuery(sb.toString()).getSingleResult()).doubleValue();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el total sin iva. ", e);
            return 0.0;
        }
    }
}

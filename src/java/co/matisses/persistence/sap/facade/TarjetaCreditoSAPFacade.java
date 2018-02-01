package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TarjetaCreditoSAP;
import co.matisses.persistence.sap.entity.TarjetaCreditoSAP_;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class TarjetaCreditoSAPFacade extends AbstractFacade<TarjetaCreditoSAP> {

    private final static Logger log = Logger.getLogger(TarjetaCreditoSAPFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaCreditoSAPFacade() {
        super(TarjetaCreditoSAP.class);
    }

    public List<TarjetaCreditoSAP> consultartarjetasP2P() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TarjetaCreditoSAP> cq = cb.createQuery(TarjetaCreditoSAP.class);
        Root<TarjetaCreditoSAP> root = cq.from(TarjetaCreditoSAP.class);
        cq.where(cb.like(root.<String>get("cardName"), "%P2P"));
        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar las tarjetas habilitadas para Place to Pay. ", e);
            return null;
        }
    }

    public List<TarjetaCreditoSAP> consultarTarjetasAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TarjetaCreditoSAP> cq = cb.createQuery(TarjetaCreditoSAP.class);
        Root<TarjetaCreditoSAP> tarjeta = cq.from(TarjetaCreditoSAP.class);

        cq.where(cb.equal(tarjeta.get(TarjetaCreditoSAP_.whsCode), almacen),
                cb.notLike(tarjeta.<String>get(TarjetaCreditoSAP_.cardName), "%complementos%"));
        cq.orderBy(cb.asc(tarjeta.get(TarjetaCreditoSAP_.cardName)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar las tarjetas del almacen.", e);
            return null;
        }
    }

    public List<TarjetaCreditoSAP> obtenerDatafonosDisponiblesTarjeta(String franquicia, String almacen) {
        StringBuilder sb = new StringBuilder();

        if (almacen.substring(0, 2).equals("09")) {
            sb.append("SELECT tarjetas.* ");
            sb.append("FROM   OWHS camiones ");
            sb.append("INNER  JOIN OWHS tiendas ON tiendas.U_Ciudad = camiones.U_Ciudad ");
            sb.append("INNER  JOIN OCRC tarjetas ON tarjetas.Phone = tiendas.WhsCode ");
            sb.append("WHERE  camiones.WhsCode = '");
            sb.append(almacen);
            sb.append("' ");
            sb.append("AND    tarjetas.CardName LIKE '%");
            sb.append(franquicia);
            sb.append("%' ");
            sb.append("AND    tarjetas.CardName NOT LIKE '%complementos%' ");
            sb.append("AND    tarjetas.CardName NOT LIKE '%P2P%' ");
            sb.append("ORDER  BY tarjetas.CardName");
        } else {
            sb.append("SELECT * ");
            sb.append("FROM   OCRC ");
            sb.append("WHERE  CardName LIKE '%");
            sb.append(franquicia);
            sb.append("%' ");
            sb.append("AND    (Phone = '' OR Phone IS NULL OR Phone = '");
            sb.append(almacen);
            sb.append("') ");
            sb.append("AND    CardName NOT LIKE '%complementos%' ");
            sb.append("ORDER  BY CardName");
        }

        try {
            return em.createNativeQuery(sb.toString(), TarjetaCreditoSAP.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<TarjetaCreditoSAP> obtenerTarjetasSucursal(String whsCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(TarjetaCreditoSAP.class);
        Root<TarjetaCreditoSAP> root = cq.from(TarjetaCreditoSAP.class);

        List<String> whsCodes = new ArrayList<>();

        whsCodes.add(whsCode);
        whsCodes.add("0000");

        Expression<String> exp = root.get("whsCode");
        Predicate predicate = exp.in(whsCodes);

        cq.where(cb.and(predicate, cb.like(root.<String>get("terminalName"), "%POS")));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "No se encontraron franquicias para el almacen {0}", whsCode);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar las franquicias para la sucursal. ", e);
        }
        return null;
    }
}

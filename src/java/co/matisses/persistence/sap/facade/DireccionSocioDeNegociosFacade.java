package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DireccionSocioDeNegocios;
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
public class DireccionSocioDeNegociosFacade extends AbstractFacade<DireccionSocioDeNegocios> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(DireccionSocioDeNegociosFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionSocioDeNegociosFacade() {
        super(DireccionSocioDeNegocios.class);
    }

    public List<DireccionSocioDeNegocios> findByCardCode(String cardCode) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DireccionSocioDeNegocios> cq = cb.createQuery(DireccionSocioDeNegocios.class);
            Root<DireccionSocioDeNegocios> direccion = cq.from(DireccionSocioDeNegocios.class);
            cq.where(cb.equal(direccion.get("direccionSocioDeNegociosPK").get("cardCode"), cardCode));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> obtenerCelularesCliente(String cardCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR(MAX), Building) AS Celular ");
        sb.append("FROM   CRD1 ");
        sb.append("WHERE  CardCode = '");
        sb.append(cardCode);
        sb.append("' ");
        sb.append("AND    CONVERT(VARCHAR(MAX), Building) <> '' ");
        sb.append("GROUP  BY CONVERT(VARCHAR(MAX), Building) ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<String> obtenerDirecciones(String cardCode, String tipo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(String.class);
        Root direccion = cq.from(DireccionSocioDeNegocios.class);

        cq.where(cb.equal(direccion.get("direccionSocioDeNegociosPK").get("cardCode"), cardCode), cb.equal(direccion.get("direccionSocioDeNegociosPK").get("adresType"), tipo.charAt(0)));
        cq.select(direccion.get("direccionSocioDeNegociosPK").get("address"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las direcciones del cliente. ", e);
        }
        return null;
    }

    public DireccionSocioDeNegocios obtenerDireccion(String cardCode, String name, String type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(DireccionSocioDeNegocios.class);
        Root direccion = cq.from(DireccionSocioDeNegocios.class);

        cq.where(cb.and(cb.equal(direccion.get("direccionSocioDeNegociosPK").get("cardCode"), cardCode),
                cb.equal(direccion.get("direccionSocioDeNegociosPK").get("address"), name),
                cb.equal(direccion.get("direccionSocioDeNegociosPK").get("adresType"), type.charAt(0))));

        try {
            return (DireccionSocioDeNegocios) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la direccion. ", e);
        }
        return null;
    }
}

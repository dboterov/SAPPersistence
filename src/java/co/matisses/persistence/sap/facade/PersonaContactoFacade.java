package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.PersonaContacto;
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
 * @author jguisao
 */
@Stateless
public class PersonaContactoFacade extends AbstractFacade<PersonaContacto> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruDecoradoresFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaContactoFacade() {
        super(PersonaContacto.class);
    }

    public PersonaContacto obtenerPersonaContacto(String cardCode) {
        if (!cardCode.toUpperCase().endsWith("CL")) {
            cardCode = cardCode.concat("CL");
        }
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<PersonaContacto> cq = cb.createQuery(PersonaContacto.class);
            Root<PersonaContacto> root = cq.from(PersonaContacto.class);
            cq.where(cb.equal(root.get("cardCode"), cardCode));

            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error obteniendo persona contacto. ", e);
            return null;
        }
    }
}
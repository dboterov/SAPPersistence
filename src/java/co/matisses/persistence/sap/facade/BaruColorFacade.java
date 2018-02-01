package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruColor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruColorFacade extends AbstractFacade<BaruColor> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruColorFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruColorFacade() {
        super(BaruColor.class);
    }

    public String obtenerSiguienteID() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR, MAX(CONVERT(NUMERIC, CODE)) + 1) ");
        sb.append("FROM   [@BARUCOLOR] ");

        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el siguiente ID de color. ", e);
        }
        return null;
    }
}

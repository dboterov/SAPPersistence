package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Equipo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(EquipoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public Equipo obtenerEquipoEmpleado(Integer empID) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT equipo.* ");
        sb.append("FROM   OHTM equipo ");
        sb.append("INNER  JOIN HTM1 equipoEmpleado ON equipoEmpleado.teamID = equipo.teamID ");
        sb.append("WHERE  equipoEmpleado.empID = ");
        sb.append(empID);

        try {
            return (Equipo) em.createNativeQuery(sb.toString(), Equipo.class).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}

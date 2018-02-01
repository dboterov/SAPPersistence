package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.CentroCostosEmpleados;
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
public class CentroCostosEmpleadosFacade extends AbstractFacade<CentroCostosEmpleados> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(CentroCostosEmpleadosFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CentroCostosEmpleadosFacade() {
        super(CentroCostosEmpleados.class);
    }

    public CentroCostosEmpleados validarCentroEmpleado(Integer slpCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT centro.* ");
        sb.append("FROM   OUDP centro ");
        sb.append("INNER  JOIN OHEM empleado ON empleado.dept = centro.Code ");
        sb.append("WHERE  salesPrson = ");
        sb.append(slpCode);

        try {
            return (CentroCostosEmpleados) em.createNativeQuery(sb.toString(), CentroCostosEmpleados.class).getSingleResult();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de centro de consto para el empleado con slpCode {0}", slpCode);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el centro de costos para el empleado. ", e);
        }
        return null;
    }
}

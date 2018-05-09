package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.dto.FiltroDTO;
import co.matisses.persistence.sap.entity.BaruColor;
import java.util.HashMap;
import java.util.List;
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

    public List<String> obtenerReferenciasColor(HashMap<FiltroDTO, List<String>> parametros) {
        StringBuilder sb = new StringBuilder();

        parametros.keySet().stream().filter((f) -> (f.getCodigoColumna().equals("color_especifico"))).map((f) -> {
            sb.append("SELECT CONVERT(VARCHAR, U_articulo) Referencia ");
            sb.append("FROM   [@BARU_COLOR_GENERICO] cg ");
            sb.append("INNER  JOIN [@BARUCOLOR] col ON col.U_colorgenerico = cg.Code ");
            sb.append("INNER  JOIN [@BARU_COLXART] cm ON cm.U_color = col.Code ");
            sb.append("INNER  JOIN OITM ref ON ref.ItemCode = cm.U_articulo ");
            sb.append("WHERE  col.Code = ");

            parametros.get(f).stream().forEach((fila) -> {
                sb.append(fila);
            });

            return f;
        }).forEach((_item) -> {
            sb.append("AND    ref.OnHand > 0 ");
        });

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las referencias por color. ", e);
        }
        return null;
    }
}

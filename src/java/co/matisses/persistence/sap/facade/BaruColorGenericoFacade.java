package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.dto.FiltroDTO;
import co.matisses.persistence.sap.entity.BaruColorGenerico;
import java.util.List;
import java.util.Map;
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
public class BaruColorGenericoFacade extends AbstractFacade<BaruColorGenerico> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(BaruColorGenericoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruColorGenericoFacade() {
        super(BaruColorGenerico.class);
    }

    public List<String> obtenerReferenciasColorGenerico(Map<FiltroDTO, List<String>> parametros) {
        StringBuilder sb = new StringBuilder();
        parametros.keySet().stream().filter((f) -> (f.getCodigoColumna().equals("color"))).map((f) -> {
            sb.append("SELECT CONVERT(VARCHAR, U_articulo) Referencia ");
            sb.append("FROM   [@BARU_COLOR_GENERICO] cg ");
            sb.append("INNER  JOIN [@BARUCOLOR] col ON col.U_colorgenerico = cg.Code ");
            sb.append("INNER  JOIN [@BARU_COLXART] cm ON cm.U_color = col.Code ");
            sb.append("INNER  JOIN OITM ref ON ref.ItemCode = cm.U_articulo ");
            sb.append("WHERE  cg.Code = ");

            parametros.get(f).stream().forEach((fila) -> {
                sb.append(fila);
            });

            return f;
        }).forEach((_item) -> {
            sb.append(" AND    ref.OnHand > 0 ");
        });

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las referencias por color generico. ", e);
        }
        return null;
    }
}

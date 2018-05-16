package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ConfiguracionAtributoUbicacion;
import java.util.List;
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
public class ConfiguracionAtributoUbicacionFacade extends AbstractFacade<ConfiguracionAtributoUbicacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(ConfiguracionAtributoUbicacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfiguracionAtributoUbicacionFacade() {
        super(ConfiguracionAtributoUbicacion.class);
    }

    public List<Object[]> obtenerDatosAtributo(String alias, String fechaInicio, String fechaFin, String cliente, String idDemostracionWeb, String facturaAsociada) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT 'Alias' AS Tipo, ");
        sb.append("       SUM(case DispName WHEN 'Alias' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("       (SELECT AbsEntry ");
        sb.append("        FROM   OBAT ");
        sb.append("        WHERE  AttrValue = '");
        sb.append(alias);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(alias);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Alias' ");
        sb.append("GROUP  BY AbsEntry ");
        sb.append("UNION  ALL ");
        sb.append("SELECT 'Fecha Inicio' AS Tipo, ");
        sb.append("	  SUM(case DispName WHEN 'Fecha Inicio DEMO' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("	  (SELECT AbsEntry ");
        sb.append("	   FROM   OBAT ");
        sb.append("	   WHERE  AttrValue = '");
        sb.append(fechaInicio);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(fechaInicio);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Fecha Inicio DEMO' ");
        sb.append("GROUP  BY AbsEntry ");
        sb.append("UNION  ALL ");
        sb.append("SELECT 'Fecha Fin' AS Tipo, ");
        sb.append("	  SUM(case DispName WHEN 'Fecha Fin DEMO' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("	  (SELECT AbsEntry ");
        sb.append("	   FROM   OBAT ");
        sb.append("	   WHERE  AttrValue = '");
        sb.append(fechaFin);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(fechaFin);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Fecha Fin DEMO' ");
        sb.append("GROUP  BY AbsEntry ");
        sb.append("UNION  ALL ");
        sb.append("SELECT 'Cliente' AS Tipo, ");
        sb.append("	  SUM(case DispName WHEN 'Cliente' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("	  (SELECT AbsEntry ");
        sb.append("	   FROM   OBAT ");
        sb.append("	   WHERE  AttrValue = '");
        sb.append(cliente);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(cliente);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Cliente' ");
        sb.append("GROUP  BY AbsEntry ");
        sb.append("UNION  ALL ");
        sb.append("SELECT 'Id Demostración Web' AS Tipo, ");
        sb.append("	  SUM(case DispName WHEN 'Id Demostración Web' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("	  (SELECT AbsEntry ");
        sb.append("	   FROM   OBAT ");
        sb.append("	   WHERE  AttrValue = '");
        sb.append(idDemostracionWeb);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(idDemostracionWeb);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Id Demostración Web' ");
        sb.append("GROUP  BY AbsEntry ");
        sb.append("UNION  ALL ");
        sb.append("SELECT 'Factura Asociada' AS Tipo, ");
        sb.append("	  SUM(case DispName WHEN 'Factura Asociada' THEN atributo.AbsEntry ELSE 0 END) AS Id, ");
        sb.append("	  (SELECT AbsEntry ");
        sb.append("	   FROM   OBAT ");
        sb.append("	   WHERE  AttrValue = '");
        sb.append(facturaAsociada);
        sb.append("' ");
        sb.append("	   AND    FldAbs = atributo.AbsEntry) AS AbsEntry, ");
        sb.append("       '");
        sb.append(facturaAsociada);
        sb.append("' AS Valor ");
        sb.append("FROM   OBFC atributo ");
        sb.append("WHERE  atributo.DispName = 'Factura Asociada' ");
        sb.append("GROUP  BY AbsEntry ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }
}

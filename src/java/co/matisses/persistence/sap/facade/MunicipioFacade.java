package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Municipio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dbotero
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    private static final Logger console = Logger.getLogger(MunicipioFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public List<Object[]> listarMunicipiosDepartamentos() {
        StringBuilder query = new StringBuilder();
        query.append("select cast(m.Code as varchar(5)) as codigo, cast(m.Name as varchar(30)) as nombre, ");
        query.append("cast(d.Code as varchar(2)) as codDepartamento, cast(d.Name as varchar(20)) as nomDepartamento ");
        query.append("from [@BPCO_MU] m ");
        query.append("inner join [@BPCO_DEP] d on d.Code = SUBSTRING(m.Code,1,2)");
        try {
            return em.createNativeQuery(query.toString()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Object[] buscarMunicipioPorNombre(String nombreMpio, String nombreDpto) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(m.Code as varchar(5)) cityCode, cast(m.Name as varchar(50)) cityName, ");
        sb.append("cast(d.Code as varchar(2)) depCode, cast(d.Name as varchar(50)) depName ");
        sb.append("from [@BPCO_MU] m inner join [@BPCO_DEP] d on d.Code = SUBSTRING(m.Code,1,2) ");
        sb.append("where m.Name like '%");
        sb.append(nombreMpio);
        sb.append("%' and d.Name like '%");
        sb.append(nombreDpto);
        sb.append("%'");
        try {
            return (Object[]) em.createNativeQuery(sb.toString()).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            console.log(Level.SEVERE, "Ocurrio un error al consultar el municipio. ", e);
            return null;
        }
    }
}

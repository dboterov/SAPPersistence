package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruMunicipios;
import co.matisses.persistence.sap.entity.BaruMunicipios_;
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
 * @author ygil
 */
@Stateless
public class BaruMunicipiosFacade extends AbstractFacade<BaruMunicipios> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruMunicipiosFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruMunicipiosFacade() {
        super(BaruMunicipios.class);
    }

    public BaruMunicipios obtenerMunicipioDireccion(Integer codCiudad) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruMunicipios> cq = cb.createQuery(BaruMunicipios.class);
        Root<BaruMunicipios> municipio = cq.from(BaruMunicipios.class);

        cq.where(cb.equal(municipio.get(BaruMunicipios_.code), codCiudad));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public BaruMunicipios obtenerMunicipio(String nombCiudad) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruMunicipios> cq = cb.createQuery(BaruMunicipios.class);
        Root<BaruMunicipios> municipio = cq.from(BaruMunicipios.class);

        cq.where(cb.equal(municipio.get(BaruMunicipios_.name), nombCiudad));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar el municipio por el nombre, sin datos");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar el municipio por el nombre. ", e);
        }
        return null;
    }
}

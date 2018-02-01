package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Usuario;
import co.matisses.persistence.sap.entity.Usuario_;
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
 * @author ygil
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(UsuarioFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> obtenerUsuariosActivos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);

        cq.where(cb.equal(usuario.get(Usuario_.Locked), "N".charAt(0)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron usuarios activos");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los usuarios activos. ", e);
        }
        return null;
    }
}

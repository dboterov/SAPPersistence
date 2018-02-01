package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Empleado;
import co.matisses.persistence.sap.entity.Empleado_;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
 * @author dbotero
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    private static final Logger log = Logger.getLogger(EmpleadoFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public List<Empleado> consultarCumpleanosDia() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> emp = cq.from(Empleado.class);
        cq.where(cb.and(cb.equal(emp.get("active"), 'Y'), cb.isNotNull(emp.get("birthDate"))));

        try {
            List<Empleado> activos = em.createQuery(cq).getResultList();
            List<Empleado> cumplen = new ArrayList<>();
            GregorianCalendar cal = new GregorianCalendar();
            int mes = cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            for (Empleado empleado : activos) {
                GregorianCalendar fechaCumple = new GregorianCalendar();
                fechaCumple.setTime(empleado.getBirthDate());
                if (fechaCumple.get(Calendar.MONTH) == mes && fechaCumple.get(Calendar.DAY_OF_MONTH) == dia) {
                    cumplen.add(empleado);
                    log.log(Level.INFO, "Hoy esta de cumpleanos [{0}]", empleado.getFirstName());
                }
            }
            return cumplen;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los cumpleanos del dia. ", e);
            return new ArrayList<>();
        }
    }

    public List<Empleado> consultarEmpleadosCcyga() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> root = cq.from(Empleado.class);

        cq.where(cb.and(cb.equal(root.get("dept"), 10),
                cb.equal(root.get("active"), 'Y'),
                cb.isNotNull(root.get("ucodigoRevisado")),
                cb.notEqual(root.get("ucodigoRevisado"), "")));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }

    public String consultarNombreEmpleado(String codRevisado) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR(50), firstName + CASE WHEN middleName IS NULL THEN '' ELSE ' ' + middleName END) AS nombre ");
        sb.append("FROM   OHEM ");
        sb.append("WHERE  U_codigoRevisado = ");
        sb.append(codRevisado);

        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Empleado> listCustomerServiceEmployees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> root = cq.from(Empleado.class);
        cq.where(cb.and(cb.equal(root.get("active"), 'Y'), cb.equal(root.get("position"), 11)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al listar los empleados de servicio al cliente. ", e);
            return new ArrayList<>();
        }
    }

    public Empleado obtenerEmpleadoCodVentas(String codVentas) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> root = cq.from(Empleado.class);
        cq.where(cb.and(cb.equal(root.get("salesPrson"), codVentas)));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public Empleado obtenerEmpleadoDocumento(String documento) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> empleado = cq.from(Empleado.class);

        cq.where(cb.equal(empleado.get("officeExt"), documento));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public Empleado obtenerEmpleadoCorreo(String correo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> empleado = cq.from(Empleado.class);

        cq.where(cb.equal(empleado.get("uCorreoCorp"), correo));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object[]> obtenerEmpleadosVentas(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(b.Code AS VARCHAR(8)) codigoTienda, ");
        sb.append("       CAST(b.Remarks AS VARCHAR(40)) nombreTienda, ");
        sb.append("       CAST(e.officeExt AS VARCHAR(20)) cedula, ");
        sb.append("       CAST(CONCAT(e.firstName, ' ', e.middleName) AS VARCHAR(100)) nombre, ");
        sb.append("       CAST(e.empID AS VARCHAR(4)) codAsesor, ");
        sb.append("       CAST(RTRIM(CONCAT(e.lastName, ' ', e.firstName, ' ', e.middleName)) AS VARCHAR(100)) AS nombreCompleto, ");
        sb.append("       CAST(dep.Remarks AS VARCHAR(10)) sucursal, ");
        sb.append("       CAST(e.salesPrson AS VARCHAR(3)) codVentas, ");
        sb.append("       CAST(b.Name AS VARCHAR(10)) sucursalVenta, ");
        sb.append("       CAST(slp.SlpName AS VARCHAR(100)) slpName ");
        sb.append("FROM   OHEM e ");
        sb.append("INNER  JOIN OUBR b ON b.Code = e.branch ");
        sb.append("INNER  JOIN OHPS pos ON pos.posID = e.position AND pos.posID IN (7, 75, 26) ");
        sb.append("INNER  JOIN OUDP dep ON dep.Code = e.dept ");
        sb.append("INNER  JOIN OSLP slp ON slp.SlpCode = e.salesPrson ");
        sb.append("WHERE  e.Active = 'Y' ");
        sb.append("AND    dep.Remarks LIKE '0%' ");
        if (almacen != null && !almacen.isEmpty()) {
            sb.append("AND    dep.Remarks = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append("ORDER  BY b.Remarks DESC, e.firstName ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Empleado> obtenerEmpleadosJefes(Integer empID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> empleado = cq.from(Empleado.class);

        cq.where(cb.equal(empleado.get(Empleado_.active), "Y".charAt(0)), cb.notEqual(empleado.get(Empleado_.empID), empID));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "No se encontraron empleados activos para listar como jefes.");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los empleados para jefes. ", e);
        }
        return null;
    }

    public List<Empleado> obtenerEmpleado(String campo, String valor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        Root<Empleado> empleado = cq.from(Empleado.class);

        cq.where(cb.like(empleado.<String>get(campo), "%" + valor + "%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Empleado> obtenerEmpleadosMes(Integer mes) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OHEM emp ");
        sb.append("WHERE  MONTH(birthDate) = ");
        sb.append(mes);
        sb.append(" AND   emp.Active = 'Y' ");
        sb.append("ORDER  BY DAY(birthDate) ");

        try {
            return em.createNativeQuery(sb.toString(), Empleado.class).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los empleados del mes", e);
        }
        return null;
    }
}

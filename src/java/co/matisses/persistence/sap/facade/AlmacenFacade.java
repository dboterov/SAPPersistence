package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Almacen;
import co.matisses.persistence.sap.entity.Almacen_;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class AlmacenFacade extends AbstractFacade<Almacen> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(AlmacenFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlmacenFacade() {
        super(Almacen.class);
    }

    public List<Almacen> almacenesActivos() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Almacen> cq = cb.createQuery(Almacen.class);
            Root<Almacen> almacenes = cq.from(Almacen.class);
            cq.where(cb.and(cb.equal(almacenes.get("estado"), "S"), cb.like(almacenes.<String>get("whsCode"), "0%")));

            List<Almacen> result = em.createQuery(cq).getResultList();
            return result;
        } catch (Exception e) {
            error("Ocurrio un error al consultar los almacenes disponibles. ", e);
            return null;
        }
    }

    public List<Almacen> cargarTiendas(boolean soloActivas) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Almacen> cq = cb.createQuery(Almacen.class);
            Root<Almacen> almacenes = cq.from(Almacen.class);
            if (soloActivas) {
                cq.where(cb.and(cb.equal(almacenes.get("estado"), "S"), cb.like(almacenes.<String>get("whsCode"), "0%")));
            } else {
                cq.where(cb.like(almacenes.<String>get("whsCode"), "0%"));
            }

            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            error("Ocurrio un error al consultar los almacenes de venta disponibles. ", e);
            return null;
        }
    }

    private void error(String msg, Throwable e) {
        log.log(Level.SEVERE, msg, e);
    }

    public String obtenerNombreTabletTienda(String tienda) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Almacen> cq = cb.createQuery(Almacen.class);
        Root<Almacen> almacen = cq.from(Almacen.class);

        cq.where(cb.equal(almacen.get("whsCode"), tienda));

        try {
            return em.createQuery(cq).getSingleResult().getUnombrextablet();
        } catch (Exception e) {
            error("No se obtuvo el nombre solicitado", e);
            return "";
        }
    }

    public List<Almacen> obtenerAlmacenesBaru() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Almacen> cq = cb.createQuery(Almacen.class);
        Root<Almacen> almacenes = cq.from(Almacen.class);

        cq.where(cb.equal(almacenes.get("estado"), "S"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object[] cargarDatosFacturaWeb() {
        StringBuilder sb = new StringBuilder();
        sb.append("select top 1 ");
        sb.append("cast(serie.U_Serie as varchar) codSerie, ");
        sb.append("cast(resolucion.BeginStr as varchar) prefijo, ");
        sb.append("cast(serie.U_CodVentas as varchar) ventas, ");
        sb.append("cast(serie.U_CodLogistica as varchar) logistica, ");
        sb.append("cast(serie.U_CodRuta as varchar) ruta, ");
        sb.append("cast(serie.U_CodProyecto as varchar) proyecto, ");
        sb.append("cast(wuid.U_WUID as varchar(max)) wuid ");
        sb.append("from [@BARU_SERIESXALMACEN] serie ");
        sb.append("inner join [@OK1_WUID] wuid on wuid.U_SerieUsuario = serie.U_Serie ");
        sb.append("inner join NNM1 resolucion on resolucion.Series = serie.U_Serie and resolucion.Locked = 'N' ");
        sb.append("where  serie.U_WhsCode = '9999' ");
        sb.append("and serie.U_Type = '13' ");
        sb.append("and serie.U_State = 'Y' ");
        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la configuracion de factura WEB. ", e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object[] cargarDatosFacturaMercadoLibre() {
        StringBuilder sb = new StringBuilder();
        sb.append("select top 1 ");
        sb.append("cast(serie.U_Serie as varchar) codSerie, ");
        sb.append("cast(resolucion.BeginStr as varchar) prefijo, ");
        sb.append("cast(serie.U_CodVentas as varchar) ventas, ");
        sb.append("cast(serie.U_CodLogistica as varchar) logistica, ");
        sb.append("cast(serie.U_CodRuta as varchar) ruta, ");
        sb.append("cast(serie.U_CodProyecto as varchar) proyecto, ");
        sb.append("cast(wuid.U_WUID as varchar(max)) wuid ");
        sb.append("from [@BARU_SERIESXALMACEN] serie ");
        sb.append("inner join [@OK1_WUID] wuid on wuid.U_SerieUsuario = serie.U_Serie ");
        sb.append("inner join NNM1 resolucion on resolucion.Series = serie.U_Serie and resolucion.Locked = 'N' ");
        sb.append("where  serie.U_WhsCode = '9998' ");
        sb.append("and serie.U_Type = '13' ");
        sb.append("and serie.U_State = 'Y' ");
        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la configuracion de factura WEB. ", e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object[] cargarDatosFacturacionAlmacen(String whsCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("select top 1 cast(almacen.U_Ciudad as varchar) ciudad, cast(serie.U_Serie as varchar) codSerie, ");
        sb.append("cast(serie.U_SeriesName as varchar) nomSerie, cast(serie.U_CodVentas as varchar) ventas, ");
        sb.append("cast(serie.U_CodLogistica as varchar) logistica, cast(serie.U_CodRuta as varchar) ruta, ");
        sb.append("cast(serie.U_CodProyecto as varchar) proyecto, ");
        sb.append("cast(wuid.U_WUID as varchar(max)) wuid, cast(recibo.U_Serie as varchar) serieRecibo, ");
        sb.append("cast(resolucion.InitialNum as varchar) desde, ");
        sb.append("cast(resolucion.LastNum as varchar) hasta, ");
        sb.append("cast(resolucion.Remark as varchar) numero, ");
        sb.append("cast(resolucion.BeginStr as varchar) prefijo, ");
        sb.append("cast(b.AbsEntry as varchar) BinAbs, ");
        sb.append("cast(resAnulacion.Series as varchar(20)) serieAnulacion ");
        sb.append("from OWHS almacen ");
        sb.append("inner join [@BARU_SERIESXALMACEN] serie on serie.U_WhsCode = almacen.WhsCode and serie.U_Type = '13P' and serie.U_State = 'Y' ");
        sb.append("inner join [@BARU_SERIESXALMACEN] recibo on recibo.U_WhsCode= almacen.WhsCode and recibo.U_Type = '24P' and recibo.U_State = 'Y' ");
        sb.append("inner join [@OK1_WUID] wuid on wuid.U_SerieUsuario = serie.U_Serie ");
        sb.append("inner join NNM1 resolucion on resolucion.Series = serie.U_Serie and resolucion.Locked = 'N' ");
        sb.append("inner join NNM1 resAnulacion on resAnulacion.ObjectCode = 14 and resAnulacion.SeriesName = 'NC' and resAnulacion.Locked = 'N' ");
        sb.append("left join OBIN b on b.WhsCode = almacen.WhsCode and b.BinCode = CONCAT(almacen.WhsCode,'COMPLEMENTOS') ");
        sb.append("where  almacen.WhsCode = '");
        sb.append(whsCode);
        sb.append("'");

        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la configuracion de factura POS para el almacen [" + whsCode + "]. ", e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object[] cargarDatosFacturacionAlmacenHotSale(String whsCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("select top 1 cast(almacen.U_Ciudad as varchar) ciudad, cast(serie.U_Serie as varchar) codSerie, ");
        sb.append("cast(serie.U_SeriesName as varchar) nomSerie, cast(serie.U_CodVentas as varchar) ventas, ");
        sb.append("cast(serie.U_CodLogistica as varchar) logistica, cast(serie.U_CodRuta as varchar) ruta, ");
        sb.append("cast(serie.U_CodProyecto as varchar) proyecto, ");
        sb.append("cast(wuid.U_WUID as varchar(max)) wuid, cast(recibo.U_Serie as varchar) serieRecibo, ");
        sb.append("cast(resolucion.InitialNum as varchar) desde, ");
        sb.append("cast(resolucion.LastNum as varchar) hasta, ");
        sb.append("cast(resolucion.Remark as varchar) numero, ");
        sb.append("cast(resolucion.BeginStr as varchar) prefijo, ");
        sb.append("cast(b.AbsEntry as varchar) BinAbs, ");
        sb.append("cast(resAnulacion.Series as varchar(20)) serieAnulacion ");
        sb.append("from OWHS almacen ");
        sb.append("inner join [@BARU_SERIESXALMACEN] serie on serie.U_WhsCode = almacen.WhsCode and serie.U_Type = '13' and serie.U_State = 'Y' ");
        sb.append("inner join [@BARU_SERIESXALMACEN] recibo on recibo.U_WhsCode= almacen.WhsCode and recibo.U_Type = '24' and recibo.U_State = 'Y' ");
        sb.append("inner join [@OK1_WUID] wuid on wuid.U_SerieUsuario = serie.U_Serie ");
        sb.append("inner join NNM1 resolucion on resolucion.Series = serie.U_Serie and resolucion.Locked = 'N' ");
        sb.append("inner join NNM1 resAnulacion on resAnulacion.ObjectCode = 14 and resAnulacion.SeriesName = 'NC' and resAnulacion.Locked = 'N' ");
        sb.append("left join OBIN b on b.WhsCode = almacen.WhsCode and b.BinCode = CONCAT(almacen.WhsCode,'COMPLEMENTOS') ");
        sb.append("where  almacen.WhsCode = '");
        sb.append(whsCode);
        sb.append("'");

        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la configuracion de factura POS para el almacen [" + whsCode + "]. ", e);
            return null;
        }
    }

    public List<Object[]> consultarInfoAlmacenes() {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(alm.WhsCode as varchar(8)) WhsCode, cast(ub.AbsEntry as int) BinAbs, cast(alm.U_BodegaClientes as varchar(8)) BodegaClientes ");
        sb.append("from   OBIN ub ");
        sb.append("inner join OWHS alm on alm.WhsCode = ub.WhsCode ");
        sb.append("where  ub.BinCode like '%TM' ");
        sb.append("and    alm.Inactive = 'N' ");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los IDs de las ubicaciones TM por almacen. ", e);
            return new ArrayList<>();
        }
    }

    public String consultarCuentaInventario(String whsCode) {
        StringBuilder query = new StringBuilder();
        query.append("select cast(a.BalInvntAc as varchar(10)) cuenta ");
        query.append("from   OWHS a ");
        query.append("where  a.WhsCode = '");
        query.append(whsCode);
        query.append("'");
        try {
            return (String) em.createNativeQuery(query.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la cuenta de inventario para el almacen. ", e);
            return null;
        }
    }

    public Object[] consultarDatosSalida(String whsCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(a.DecreasAc as varchar(10)) acc, cast(i.itemcode as varchar(20)) itemCode ");
        sb.append("from OITM i ");
        sb.append("inner join OITW s on s.ItemCode = i.ItemCode and s.OnHand > 0 and s.WhsCode = CONCAT('IN','0203') ");
        sb.append("inner join OWHS a on a.WhsCode = s.WhsCode ");
        sb.append("where i.ItmsGrpCod = '113' ");
        sb.append("and   i.U_Grupo = '046' ");
        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la informacion para la salida de insumos (tarjeta regalo). ", e);
            return null;
        }
    }

    public Object[] obtenerParametrosFacturaMobiliario(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT TOP 1 CAST(almacen.U_Ciudad AS VARCHAR) ciudad, ");
        sb.append("       CAST(serie.U_Serie AS VARCHAR) codSerie, ");
        sb.append("       CAST(serie.U_SeriesName AS VARCHAR) nomSerie, ");
        sb.append("       CAST(serie.U_CodVentas AS VARCHAR) ventas, ");
        sb.append("       CAST(serie.U_CodLogistica AS VARCHAR) logistica, ");
        sb.append("       CAST(serie.U_CodProyecto AS VARCHAR) proyecto, ");
        sb.append("       CAST(wuid.U_WUID AS VARCHAR(MAX)) wuid, ");
        sb.append("       CAST(recibo.U_Serie AS VARCHAR) serieRecibo, ");
        sb.append("       CAST(resolucion.InitialNum AS VARCHAR) desde, ");
        sb.append("       CAST(resolucion.LastNum AS VARCHAR) hasta, ");
        sb.append("       CAST(resolucion.Remark AS VARCHAR) numero, ");
        sb.append("       CAST(resolucion.BeginStr AS VARCHAR) prefijo, ");
        sb.append("       CAST(resAnulacion.Series AS VARCHAR(20)) serieAnulacion ");
        sb.append("FROM   OWHS almacen ");
        sb.append("INNER  JOIN [@BARU_SERIESXALMACEN] serie ON serie.U_WhsCode = almacen.WhsCode AND serie.U_Type = '13' AND serie.U_State = 'Y' ");
        sb.append("INNER  JOIN [@BARU_SERIESXALMACEN] recibo ON recibo.U_WhsCode= almacen.WhsCode AND recibo.U_Type = '24' AND recibo.U_State = 'Y' ");
        sb.append("INNER  JOIN [@OK1_WUID] wuid ON wuid.U_SerieUsuario = serie.U_Serie ");
        sb.append("INNER  JOIN NNM1 resolucion ON resolucion.Series = serie.U_Serie AND resolucion.Locked = 'N' ");
        sb.append("INNER  JOIN NNM1 resAnulacion ON resAnulacion.ObjectCode = 14 AND resAnulacion.SeriesName = 'NC' AND resAnulacion.Locked = 'N' ");
        sb.append("WHERE  almacen.WhsCode = '");
        sb.append(almacen);
        sb.append("' ");

        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Almacen> obtenerAlmacenesPallet() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT almacen.WhsCode, ");
        sb.append("       almacen.WhsName, ");
        sb.append("       almacen.IntrnalKey, ");
        sb.append("       almacen.U_NOMBRE_REPORTES, ");
        sb.append("       almacen.U_visualizar, ");
        sb.append("       almacen.U_Velocidad, ");
        sb.append("       almacen.U_Prioridad, ");
        sb.append("       almacen.U_nombrextablet, ");
        sb.append("       almacen.U_Estado, ");
        sb.append("       almacen.U_PlacaVehiculo, ");
        sb.append("       almacen.U_CodeBars, ");
        sb.append("       almacen.U_CodigoVentas, ");
        sb.append("       almacen.U_Ciudad, ");
        sb.append("       almacen.City, ");
        sb.append("       almacen.Street, ");
        sb.append("       almacen.BalInvntAc, ");
        sb.append("       almacen.U_BodegaClientes ");
        sb.append("FROM   OWHS almacen ");
        sb.append("INNER  JOIN OBIN ubicacion on ubicacion.WhsCode = almacen.WhsCode ");
        sb.append("WHERE  Attr9Val = 'Sí' ");

        try {
            return em.createNativeQuery(sb.toString(), Almacen.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<String> obtenerAlmacenesDemoSinUbicacionAsesor(String codigoVentas, String slot) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(20), WhsCode) AS WhsCode ");
        sb.append("FROM   OBIN ");
        sb.append("WHERE  SL1Code <> '");
        sb.append(codigoVentas);
        sb.append("' ");
        sb.append("AND    WhsCode LIKE 'DM%' ");
        sb.append("AND    WhsCode NOT IN (SELECT DISTINCT WhsCode ");
        sb.append("			  FROM   OBIN ");
        sb.append("			  WHERE  SL1Code = '");
        sb.append(codigoVentas);
        sb.append("' ");
        sb.append("                       AND    SL2Code = '");
        sb.append(slot);
        sb.append("') ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<String> obtenerAlmacenesDemoAsesor(String codigoVentas) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(20), WhsCode) AS WhsCode ");
        sb.append("FROM   OBIN ");
        sb.append("WHERE  SL1Code = '");
        sb.append(codigoVentas);
        sb.append("' ");
        sb.append("AND    WhsCode LIKE 'DM%' ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Almacen> obtenerAlmacenes(String whsCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Almacen> cq = cb.createQuery(Almacen.class);
        Root<Almacen> almacen = cq.from(Almacen.class);

        cq.where(cb.like(almacen.get(Almacen_.whsCode), "%" + whsCode + "%"), cb.equal(almacen.get(Almacen_.estado), "S"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar los almacenes. ", e);
        }
        return null;
    }
}

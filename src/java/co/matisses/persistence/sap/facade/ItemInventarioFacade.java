package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.dto.FiltroDTO;
import co.matisses.persistence.sap.entity.ItemInventario;
import co.matisses.persistence.sap.entity.ItemInventario_;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author dbotero
 */
@Stateless
@LocalBean
public class ItemInventarioFacade extends AbstractFacade<ItemInventario> {

    private static final Logger CONSOLE = Logger.getLogger(ItemInventarioFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemInventarioFacade() {
        super(ItemInventario.class);
    }

    private String construirBaseMercadolibre(boolean soloMedellin) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM OITM itm ");
        sb.append("INNER JOIN OITW saldo ON saldo.ItemCode = itm.ItemCode AND saldo.OnHand > 0 AND saldo.WhsCode LIKE '0%' ");
        if (soloMedellin) {
            sb.append("INNER JOIN OWHS alm ON alm.WhsCode = saldo.WhsCode AND alm.U_Ciudad LIKE '05%' ");
            sb.append("WHERE itm.ItmsGrpCod in (104,107,124,125,126,127,128,129) ");
        } else {
            /*Bogota solo mobiliario*/
            sb.append("INNER JOIN OWHS alm ON alm.WhsCode = saldo.WhsCode AND alm.U_Ciudad LIKE '11%' ");
            sb.append("WHERE itm.ItmsGrpCod = 107 ");
        }
        sb.append("and itm.U_Grupo is not null and itm.U_SubGrupo is not null ");
        sb.append("and itm.U_modelo is not null and itm.SHeight1 is not null ");
        sb.append("and itm.SLength1 is not null and itm.SWidth1 is not null ");
        sb.append("and itm.SWeight1 is not null ");
        sb.append("and itm.U_descripciona is not null and itm.SHeight1 > 0 ");
        sb.append("and itm.SLength1 > 0 and itm.SWidth1 > 0 ");
        sb.append("and itm.SWeight1 > 0 and itm.U_fotosOK = 1 ");
        sb.append("and itm.U_U_Hab_Des = 'tYES' ");
        sb.append("and len(itm.ItemCode) = 20 ");
        //sb.append("and (itm.U_ID_MERCADOLIBRE is null or itm.U_ID_MERCADOLIBRE = '') ");
        return sb.toString();
    }

    public List<String> consultarReferenciasMercadolibre(boolean soloMedellin) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT itm.ItemCode ");
        sb.append(construirBaseMercadolibre(soloMedellin));
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener la lista de referencias para mercadolibre. ", e);
        }
        return new ArrayList<>();
    }

    public Integer consultarSaldoParaMercadolibre(String referencia, boolean soloMedellin) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ISNULL(CONVERT(INT,SUM(saldo.OnHand)),0) saldoTotal ");
        sb.append(construirBaseMercadolibre(soloMedellin));
        sb.append("and itm.ItemCode = '");
        sb.append(referencia);
        sb.append("' ");

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo para Mercado Libre. ", e);
        }
        return 0;
    }

    public List<Object[]> obtenerItemsCcygaPorReferencia(List<String> referencias) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(saldo.ItemCode as varchar(20)) referencia, ");
        sb.append("cast(itm.U_U_Ref_Pro as varchar(100)) refProv, ");
        sb.append("cast(itm.ItemName as varchar(100)) descripcion, ");
        sb.append("cast(saldo.OnHand as int) cantidad, ");
        sb.append("case when saldo.whscode like 'CL%' then 'true' else 'false' end cliente ");
        sb.append("from   OITW saldo ");
        sb.append("inner join OITM itm on itm.ItemCode = saldo.ItemCode ");
        sb.append("where  itm.itemCode in ( ");
        for (String referencia : referencias) {
            sb.append("'");
            sb.append(referencia);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")and    (saldo.WhsCode like '99%' or saldo.WhsCode like 'CL99%') ");
        sb.append("and    saldo.OnHand > 0");
        CONSOLE.log(Level.FINE, sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo de taller. ", e);
        }
        return null;
    }

    public int consultarTotalItemsCcyga(String parametroBusqueda) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT COUNT(*) ");
        sb.append("FROM   OITM articulo ");
        sb.append("INNER  JOIN OITW saldo ON saldo.ItemCode = articulo.ItemCode ");
        sb.append("WHERE  (saldo.WhsCode LIKE '99%' ");
        sb.append("OR     saldo.WhsCode LIKE 'CL99%' ");
        sb.append("OR     saldo.WhsCode LIKE 'DM99%') ");
        if (parametroBusqueda != null && !parametroBusqueda.isEmpty()) {
            sb.append("AND    (saldo.WhsCode like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("OR     articulo.ItemCode like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("OR     articulo.U_U_Ref_Pro like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("OR     articulo.ItemName like '%");
            sb.append(parametroBusqueda);
            sb.append("%') ");
        }
        sb.append("AND    saldo.OnHand > 0 ");

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public List<Object[]> obtenerItemsCcyga(int pagina, int registrosPagina, String parametroBusqueda) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT CAST(saldo.ItemCode AS VARCHAR(20)) referencia, ");
        sb.append("               CAST(itm.U_U_Ref_Pro AS VARCHAR(100)) refProv, ");
        sb.append("               CAST(itm.ItemName AS VARCHAR(100)) descripcion, ");
        sb.append("               CAST(saldo.OnHand AS INT) cantidad, ");
        sb.append("               CASE WHEN saldo.whscode LIKE 'CL%' THEN 'true' ELSE 'false' END cliente, ");
        sb.append("               CASE WHEN saldo.whscode LIKE 'DM%' THEN 'true' ELSE 'false' END demo ");
        sb.append("        FROM   OITW saldo ");
        sb.append("        INNER  JOIN OITM itm ON itm.ItemCode = saldo.ItemCode ");
        sb.append("        WHERE  (saldo.WhsCode LIKE '99%' ");
        sb.append("        OR     saldo.WhsCode LIKE 'CL99%' ");
        sb.append("        OR     saldo.WhsCode LIKE 'DM99%') ");
        if (parametroBusqueda != null && !parametroBusqueda.isEmpty()) {
            sb.append("        AND    (saldo.WhsCode like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("        OR     itm.ItemCode like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("        OR     itm.U_U_Ref_Pro like '%");
            sb.append(parametroBusqueda);
            sb.append("%' ");
            sb.append("        OR     itm.ItemName like '%");
            sb.append(parametroBusqueda);
            sb.append("%') ");
        }
        sb.append("        AND    saldo.OnHand > 0) t ");
        sb.append("ORDER  BY referencia ");
        sb.append("OFFSET ");
        sb.append(registrosPagina);
        sb.append(" * (");
        sb.append(pagina);
        sb.append(" - 1) ROW FETCH NEXT ");
        sb.append(registrosPagina);
        sb.append(" ROWS ONLY");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo de taller. ", e);
            return null;
        }
    }

    private StringBuilder getGenericQueryHeader1() {
        StringBuilder sb = new StringBuilder();
        sb.append("select convert(varchar,itm.ItemCode) itemcode ");
        sb.append(", convert(varchar,itm.FrgnName) itemName ");
        sb.append(", convert(varchar,itm.U_U_NomWeb) nomWeb ");
        sb.append(", convert(varchar(MAX),replace(U_U_Pal_Cla,char(13),' ')) palCla ");
        sb.append(", convert(int, precio.Price) precio ");
        sb.append(", convert(varchar(4),itm.U_SubGrupo) subgrupo ");
        sb.append(", convert(int,itm.SHeight1) alto ");
        sb.append(", convert(int,itm.SLength1) largo ");
        sb.append(", convert(int,itm.SWidth1) ancho ");
        sb.append(", convert(int,itm.SWeight1) peso ");
        sb.append(", saldo.almacen ");
        sb.append(", saldo.cantidad ");
        sb.append(", convert(varchar,itm.U_modelo) modelo ");
        sb.append(", convert(varchar,color.Code) colorCode ");
        sb.append(", convert(varchar,color.Name) colorName ");
        sb.append(", convert(varchar,color.U_cod_hexa) colorHexa ");
        sb.append(", convert(varchar,material.Code) materialCode ");
        sb.append(", convert(varchar,material.U_nombreWeb) materialName ");
        sb.append(", convert(varchar(max),material.U_Cuidados) materialCare ");
        sb.append(", convert(varchar(max),itm.U_descripciona) descripcion ");
        sb.append(", convert(datetime,itm.U_U_Act_Qn) fechaNuevo ");
        sb.append(", convert(varchar,itm.U_id_youtube) id_youtube ");
        sb.append(", convert(varchar(max),itm.U_DescCorta) descripcionCorta ");
        //sb.append(", cast(getdate() as date) fechaReprocesar ");
        sb.append(", convert(datetime,itm.U_reprocesarfotos) fechaReprocesar ");
        //sb.append(", case when (select top 1 comb.U_referencia from [@BARU_COMB_PPAL] comb inner join OITW saldo on saldo.ItemCode = comb.U_referencia where comb.U_modelo = itm.U_modelo and saldo.OnHand > 0 and saldo.WhsCode like '0%' order by comb.U_orden asc) = itm.ItemCode then '1' else '0' end combinacionPpal ");
        sb.append(", case when (case when combi.U_modelo is null then (select top 1 itemcode from oitm where u_modelo in (select saldo.U_modelo)) else ");
        sb.append("(select top 1 comb.U_referencia from [@BARU_COMB_PPAL] comb inner join OITW saldo on saldo.ItemCode = comb.U_referencia where comb.U_modelo = itm.U_modelo and saldo.OnHand > 0 and saldo.WhsCode like '0%' order by comb.U_orden asc) end ) = itm.ItemCode then '1' else '0' end combinacionPpal ");
        sb.append(", convert(varchar(4),isnull(marca.Code, '0001')) codMarca ");
        sb.append(", convert(varchar(20),isnull(marca.Name, 'Matisses')) nomMarca ");
        sb.append(", convert(varchar(4),itm.U_Grupo) grupo ");
        sb.append(", convert(varchar(4),itm.ItmsGrpCod) departamento ");
        sb.append(", convert(varchar(20),dep.ItmsGrpNam) nombreDepartamento ");
        sb.append(", convert(varchar(20),gr.Name) nombreGrupo ");
        sb.append(", convert(varchar(20),sub.U_descripcion) nombreSubgrupo ");

        return sb;
    }

    private StringBuilder getGenericQueryHeader2() {
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct ");
        sb.append("convert(varchar,itm.ItemCode) itemcode, ");
        sb.append("convert(varchar,itm.FrgnName) itemName, ");
        sb.append("convert(varchar,itm.U_U_NomWeb) nomWeb, ");
        sb.append("convert(varchar,itm.U_U_Pal_Cla) palCla, ");
        sb.append("convert(int,precio.Price) precio, ");
        sb.append("convert(varchar,itm.U_SubGrupo) subgrupo, ");
        sb.append("convert(int,itm.SHeight1) alto, ");
        sb.append("convert(int,itm.SLength1) largo, ");
        sb.append("convert(int,itm.SWidth1) ancho, ");
        sb.append("convert(int,itm.SWeight1) peso, ");
        sb.append("case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end almacen, ");
        sb.append("convert(int,saldo.OnHand) cantidad, ");
        sb.append("convert(varchar,itm.U_modelo) modelo, ");
        sb.append("convert(varchar,color.Code) colorCode, ");
        sb.append("convert(varchar,color.Name) colorName, ");
        sb.append("convert(varchar,color.U_cod_hexa) colorHexa, ");
        sb.append("convert(varchar,material.Code) materialCode, ");
        sb.append("convert(varchar,material.U_nombreWeb) materialName, ");
        sb.append("convert(varchar(max),material.U_Cuidados) materialCare, ");
        sb.append("convert(varchar(max),itm.U_descripciona) descripcion, ");
        sb.append("convert(datetime,itm.U_U_Act_Qn) fechaNuevo, ");
        sb.append("convert(varchar,itm.U_id_youtube) id_youtube, ");
        sb.append("convert(varchar(max),itm.U_DescCorta) descripcionCorta, ");
        sb.append("convert(datetime,itm.U_reprocesarfotos) fechaReprocesar, ");
        sb.append("case when (select top 1 comb.U_referencia from [@BARU_COMB_PPAL] comb inner join OITW saldo on saldo.ItemCode = comb.U_referencia where comb.U_modelo = itm.U_modelo and saldo.OnHand > 0 and saldo.WhsCode like '0%' order by comb.U_orden asc) = itm.ItemCode then '1' else '0' end combinacionPpal, ");
        sb.append("convert(varchar(4),isnull(marca.Code, '0001')) codMarca, ");
        sb.append("convert(varchar(20),isnull(marca.Name, 'Matisses')) nomMarca ");
        return sb;
    }

    public List<Object> getWebEnabledItem(String itemcode) {
        return getWebEnabledItem(itemcode, false);
    }

    public List<Object> getWebEnabledItem(String itemcode, boolean withStockOnly) {
        StringBuilder sb = getGenericQueryHeader1();
        sb.append("from   OITM itm ");
        sb.append("inner join ");
        sb.append("(select saldo.itemcode, k.U_modelo, case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end almacen ");
        sb.append(", sum(convert(int,saldo.OnHand)) cantidad ");
        sb.append("from OITW saldo ");
        sb.append("inner join OITM k on k.ItemCode=saldo.ItemCode ");
        sb.append("where saldo.WhsCode like '0%' ");
        if (withStockOnly) {
            sb.append("and    saldo.OnHand > 0 ");
        }
        sb.append("group by saldo.ItemCode, k.U_modelo ");
        sb.append(", case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end ");
        sb.append(") saldo on saldo.ItemCode = itm.ItemCode ");
        sb.append("inner join ITM1 precio on precio.ItemCode = itm.ItemCode ");

        sb.append("inner join OITB dep on dep.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("inner join [@BARU_GRUPO] gr on gr.Code = itm.U_Grupo and gr.U_Web = '1' ");
        sb.append("inner join [@BARU_SUBGRUPO] sub on sub.Code = itm.U_SubGrupo and sub.U_WEB = '1' ");

        sb.append("left join [@BARU_COLXART] colores on colores.U_articulo = itm.ItemCode and colores.U_principal = '1' ");
        sb.append("left join [@BARUCOLOR] color on color.Code = colores.U_color ");
        sb.append("left join [@BARU_MATXART] materiales on materiales.U_itemCode = itm.ItemCode ");
        sb.append("left join [@BARU_MATERIALES] material on material.Code = materiales.U_matCode ");
        sb.append("left join [@BARU_MARCAS] marca on marca.Code = itm.U_CodigoMarca ");
        sb.append("left join (select * from [@BARU_COMB_PPAL]) combi on combi.U_modelo = itm.U_modelo ");
        sb.append("where ");
        if (itemcode != null) {
            sb.append("itm.ItemCode = '");
            sb.append(itemcode);
            sb.append("' and ");
        }
        sb.append("itm.U_U_Hab_Des = 'tYES' ");
        //sb.append("and    itm.U_U_NomWeb is not null ");
        sb.append("and    itm.ItmsGrpCod in (124,125,107,104) ");
        sb.append("and    itm.U_Grupo is not null ");
        sb.append("and    itm.U_SubGrupo is not null ");
        sb.append("and    itm.U_modelo is not null ");
        sb.append("and    itm.SHeight1 is not null ");
        sb.append("and    itm.SLength1 is not null ");
        sb.append("and    itm.SWidth1 is not null ");
        sb.append("and    itm.SWeight1 is not null ");
        sb.append("and    itm.U_DescCorta is not null ");
        sb.append("and    itm.U_descripciona is not null ");
        sb.append("and    itm.SHeight1 > 0 ");
        sb.append("and    itm.SLength1 > 0 ");
        sb.append("and    itm.SWidth1 > 0 ");
        sb.append("and    itm.SWeight1 > 0 ");
        sb.append("and    precio.PriceList = 2 ");
        //sb.append("and    saldo.WhsCode like '0%' ");
        //sb.append("and    saldo.OnHand > 0 ");
        sb.append("and    itm.U_fotosOK = 1 ");
        sb.append("and    color.Code is not null ");
        sb.append("and    itm.U_CodigoMarca IS NOT NULL ");
        sb.append("and    LEN(itm.U_CodigoMarca) > 0 ");

        CONSOLE.log(Level.INFO, sb.toString());

        try {
            List<Object> datos = em.createNativeQuery(sb.toString()).getResultList();
            return datos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Se usa para consultar la informacion de un producto para cargar a la BD
     * Mongo del sitio web
     *
     * @param itemCode
     * @param soloConSaldo
     * @return
     */
    public List<Object[]> consultarItem(String itemCode, boolean soloConSaldo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select convert(varchar,itm.ItemCode) itemcode ");
        sb.append(", convert(varchar(max),itm.FrgnName) itemName ");
        sb.append(", (select precio.Price from ITM1 precio where precio.PriceList = 2 and precio.ItemCode = itm.ItemCode) precioConIVA ");
        sb.append(", (select precio.Price from ITM1 precio where precio.PriceList = 1 and precio.ItemCode = itm.ItemCode) precioSinIVA ");
        sb.append(", (select rate from OSTC tax where tax.Code = itm.TaxCodeAR) tarifaIVA ");
        sb.append(", convert(varchar(4),itm.ItmsGrpCod) departamento ");
        sb.append(", convert(varchar(50),dep.ItmsGrpNam) nombreDepartamento ");
        sb.append(", convert(varchar(4),itm.U_Grupo) grupo ");
        sb.append(", convert(varchar(50),gr.Name) nombreGrupo ");
        sb.append(", convert(varchar(4),itm.U_SubGrupo) subgrupo ");
        sb.append(", convert(varchar(50),sub.U_descripcion) nombreSubgrupo ");
        sb.append(", convert(int,itm.SHeight1) alto ");
        sb.append(", convert(int,itm.SLength1) largo ");
        sb.append(", convert(int,itm.SWidth1) ancho ");
        sb.append(", convert(int,itm.SWeight1) peso ");
        sb.append(", convert(varchar(10), saldo.WhsCode) almacen ");
        sb.append(", case when itm.U_U_Hab_Des = 'tNO' then 0 else convert(int, saldo.OnHand) end cantidad ");
        sb.append(", convert(varchar(50),REPLACE(itm.U_modelo,'/','-')) modelo ");
        sb.append(", convert(varchar(10),color.Code) colorCode ");
        sb.append(", convert(varchar(50),color.Name) colorName ");
        sb.append(", convert(varchar(8),color.U_cod_hexa) colorHexa ");
        sb.append(", convert(varchar(10),material.Code) materialCode ");
        sb.append(", convert(varchar(50),material.U_nombreWeb) materialName ");
        sb.append(", convert(varchar(max),material.U_Cuidados) materialCare ");
        sb.append(", convert(varchar(max),itm.U_descripciona) descripcion ");
        sb.append(", convert(datetime,itm.U_U_Act_Qn) fechaNuevo ");
        sb.append(", convert(varchar(4),isnull(marca.Code, '0001')) codMarca ");
        sb.append(", convert(varchar(20),isnull(marca.Name, 'Matisses')) nomMarca ");
        sb.append(", convert(varchar(40),itm.U_Coleccion) coleccion ");
        sb.append(", convert(varchar(15),isnull(U_ID_MERCADOLIBRE,'')) U_ID_MERCADOLIBRE ");
        sb.append(", CONVERT(VARCHAR(1), ISNULL(U_PrecioVisibleWeb, 'Y')) U_PresioVisibleWeb ");
        sb.append("from OITM itm ");
        sb.append("inner join OITB dep on dep.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("inner join [@BARU_GRUPO] gr on gr.Code = itm.U_Grupo and gr.U_Web = '1' ");
        sb.append("inner join [@BARU_SUBGRUPO] sub on sub.Code = itm.U_SubGrupo and sub.U_WEB = '1' ");
        sb.append("inner join OITW saldo on saldo.ItemCode = itm.ItemCode and saldo.WhsCode like '0%' ");
        if (soloConSaldo) {
            sb.append("and saldo.OnHand > 0 ");
        }
        sb.append("inner join OIBQ saldoUbi on saldoUbi.ItemCode = itm.ItemCode and saldoUbi.WhsCode = saldo.WhsCode ");
        sb.append("inner join OBIN ubicacion on ubicacion.AbsEntry = saldoUbi.BinAbs ");
        sb.append("left join [@BARU_COLXART] colores on colores.U_articulo = itm.ItemCode and colores.U_principal = '1' ");
        sb.append("left join [@BARUCOLOR] color on color.Code = colores.U_color ");
        sb.append("left join [@BARU_MATXART] materiales on materiales.U_itemCode = itm.ItemCode ");
        sb.append("left join [@BARU_MATERIALES] material on material.Code = materiales.U_matCode ");
        sb.append("left join [@BARU_MARCAS] marca on marca.Code = itm.U_CodigoMarca ");
        sb.append("where itm.ItemCode = '");
        sb.append(itemCode);
        sb.append("' and ubicacion.Attr1Val = 'No' ");
        //sb.append("and itm.U_U_Hab_Des = 'tYES' ");
        sb.append("and    itm.ItmsGrpCod in (124,125,107,104,129,130) ");
        sb.append("and    itm.U_Grupo is not null ");
        sb.append("and    itm.U_SubGrupo is not null ");
        sb.append("and    itm.U_modelo is not null ");
        sb.append("and    itm.SHeight1 is not null ");
        sb.append("and    itm.SLength1 is not null ");
        sb.append("and    itm.SWidth1 is not null ");
        sb.append("and    itm.SWeight1 is not null ");
        sb.append("and    itm.U_descripciona is not null ");
        sb.append("and    itm.SHeight1 > 0 ");
        sb.append("and    itm.SLength1 > 0 ");
        sb.append("and    itm.SWidth1 > 0 ");
        sb.append("and    itm.SWeight1 > 0 ");
        sb.append("and    itm.U_fotosOK = 1 ");
        sb.append("and    color.Code is not null ");
        sb.append("and    itm.U_CodigoMarca IS NOT NULL ");
        //TODO: Excluir almacenes de Bogota por cierre de tienda.
        sb.append("and    saldo.WhsCode NOT IN ('0301', '0104', '0831', '0813') ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el item " + itemCode, e);
            return new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> consultarReferenciasModelos(String modelos) {
        List<String> referencias;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT CONVERT(varchar(20),ItemCode) ItemCode ");
        sb.append("FROM OITM ");
        sb.append("WHERE U_modelo IN ('");
        sb.append(modelos);
        sb.append("')");

        try {
            referencias = (List<String>) em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return referencias;
    }

    public Integer consultarSaldoItemTaller(String referencia, int tipoAlmacen) {
        String prefijoAlmacen = tipoAlmacen == 0 ? "" : (tipoAlmacen == 1 ? "CL" : "DM");
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(OnHand as int) cantidad ");
        sb.append("from OITW ");
        sb.append("where  ItemCode = '");
        sb.append(referencia);
        sb.append("' and WhsCode = '");
        sb.append(prefijoAlmacen);
        sb.append("9901'");
        try {
            return (int) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            return -1;
        }
    }

    public ItemInventario getItemNamAndDesc(String referencia) {
        ItemInventario resultado = new ItemInventario();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CONVERT(VARCHAR(MAX), FrgnName) nombre, CONVERT(VARCHAR(MAX), U_descripciona) descripcion, ");
        sb.append("CONVERT(VARCHAR(MAX), U_DescCorta) descCorta ");
        sb.append("FROM   OITM ");
        sb.append("WHERE  ItemCode = '");
        sb.append(referencia);
        sb.append("' ");

        try {
            List<Object[]> filas = em.createNativeQuery(sb.toString()).getResultList();
            for (Object[] fila : filas) {
                resultado.setFrgnName((String) fila[0]);
                resultado.setUdescripciona((String) fila[1]);
                resultado.setUDescCorta((String) fila[2]);
                break;
            }
            return resultado;
        } catch (Exception e) {
            return new ItemInventario();
        }
    }

    public ItemInventario getItemBasicInfo(String referencia) {
        ItemInventario resultado = new ItemInventario();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CONVERT(VARCHAR(MAX), itemCode) referencia, CONVERT(VARCHAR(MAX), U_U_ref_pro) refProv, CONVERT(VARCHAR(MAX), ItemName) nombre ");
        sb.append("FROM   OITM ");
        sb.append("WHERE  ItemCode = '");
        sb.append(referencia);
        sb.append("' ");

        try {
            List<Object[]> filas = em.createNativeQuery(sb.toString()).getResultList();
            for (Object[] fila : filas) {
                resultado.setItemCode((String) fila[0]);
                resultado.setUURefPro((String) fila[1]);
                resultado.setItemName((String) fila[2]);
                break;
            }
            return resultado;
        } catch (Exception e) {
            return new ItemInventario();
        }
    }

    public List<Object> getWebEnabledItemByModel(String model, boolean withStockOnly) {
        StringBuilder sb = getGenericQueryHeader1();
        sb.append("from   OITM itm ");
        sb.append("inner join ");
        sb.append("(select saldo.itemcode, k.U_modelo, case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end almacen ");
        sb.append(", sum(convert(int,saldo.OnHand)) cantidad ");
        sb.append("from OITW saldo ");
        sb.append("inner join OITM k on k.ItemCode=saldo.ItemCode ");
        sb.append("where saldo.WhsCode like '0%' ");
        if (withStockOnly) {
            sb.append("and    saldo.OnHand > 0 ");
        }
        sb.append("group by saldo.ItemCode, k.U_modelo ");
        sb.append(", case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end ");
        sb.append(") saldo on saldo.ItemCode = itm.ItemCode ");
        sb.append("inner join ITM1 precio on precio.ItemCode = itm.ItemCode ");
        sb.append("left join [@BARU_COLXART] colores on colores.U_articulo = itm.ItemCode and colores.U_principal = '1' ");
        sb.append("left join [@BARUCOLOR] color on color.Code = colores.U_color ");
        sb.append("left join [@BARU_MATXART] materiales on materiales.U_itemCode = itm.ItemCode ");
        sb.append("left join [@BARU_MATERIALES] material on material.Code = materiales.U_matCode ");
        sb.append("left join [@BARU_COMB_PPAL] combinacionPpal on combinacionPpal.U_referencia = itm.ItemCode ");
        sb.append("left join [@BARU_MARCAS] marca on marca.Code = itm.U_CodigoMarca ");
        sb.append("left join (select * from [@BARU_COMB_PPAL]) combi on combi.U_modelo = itm.U_modelo ");
        sb.append("where  itm.U_modelo = '");
        sb.append(model);
        sb.append("' and    itm.U_U_Hab_Des = 'tYES' ");
        sb.append("and    precio.PriceList = 2 ");
        //sb.append("and    itm.U_U_NomWeb is not null ");
        sb.append("and    itm.SHeight1 is not null ");
        sb.append("and    itm.SLength1 is not null ");
        sb.append("and    itm.SWidth1 is not null ");
        sb.append("and    itm.SWeight1 is not null ");
        sb.append("and    itm.U_DescCorta is not null ");
        sb.append("and    itm.U_descripciona is not null ");
        sb.append("and    itm.SHeight1 > 0 ");
        sb.append("and    itm.SLength1 > 0 ");
        sb.append("and    itm.SWidth1 > 0 ");
        sb.append("and    itm.SWeight1 > 0 ");
        sb.append("and    itm.U_CodigoMarca IS NOT NULL ");
        sb.append("and    LEN(itm.U_CodigoMarca) > 0 ");
        sb.append(getExclusionsCondition().toString());

        try {
            CONSOLE.info(sb.toString());
            List<Object> datos = em.createNativeQuery(sb.toString()).getResultList();
            return datos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Retorna la porcion de query que excluye de las consultas los productos
     * que no se deben presentar en el sitio web
     *
     * @return
     */
    private StringBuilder getExclusionsCondition() {
        StringBuilder sb = new StringBuilder();
        sb.append("AND itm.ItemCode NOT IN ('11800000000000000347','11800000000000000348','11800000000000000349','11800000000000000350',");
        sb.append("'11800000000000000351','11800000000000000352','11800000000000000353','11800000000000000354','11800000000000000355',");
        sb.append("'11800000000000000356','13300000000000000082','13300000000000000083') ");
        return sb;
    }

    public List<String> obtenerCambiosItem(Integer minutos) {
        StringBuilder sb = new StringBuilder();
        sb.append("select convert(varchar(20),saldo.ItemCode) itemCode ");
        sb.append("from   OITW saldo ");
        sb.append("inner join OITM itm on itm.ItemCode = saldo.ItemCode ");
        sb.append("inner  join (");
        sb.append("select distinct ItemCode, warehouse ");
        sb.append("from   OINM ");
        sb.append("where  Warehouse like '0%' ");
        sb.append("and    DATEDIFF(mi,DocDate + CONVERT(datetime,'1900-01-01 ' + SUBSTRING(");
        sb.append("convert(varchar(4),RIGHT('0000' + convert(varchar,doctime),4)),1,2) ");
        sb.append("+ ':' + SUBSTRING(convert(varchar(4),RIGHT('0000' + convert");
        sb.append("(varchar,doctime),4)),3,4)),GETDATE()) <= ");
        sb.append(minutos);
        sb.append(") cambios on cambios.itemcode = saldo.itemcode and cambios.warehouse = saldo.whscode union ");
        sb.append("select distinct CONVERT(VARCHAR(20), item.ItemCode) itemCode ");
        sb.append("from   OITM item ");
        sb.append("inner  join OITW s on item.ItemCode = s.ItemCode ");
        sb.append("where  s.WhsCode like '0%' and DATEDIFF(mi,U_fechaModificacion + CONVERT(datetime,'1900-01-01 ' + SUBSTRING(");
        sb.append("convert(varchar(4),RIGHT('0000' + convert(varchar,CONVERT(time,U_fechaModificacion)),4)),1,2) ");
        sb.append("+ ':' + SUBSTRING(convert(varchar(4),RIGHT('0000' + convert");
        sb.append("(varchar,CONVERT(time,U_fechaModificacion)),4)),3,4)),GETDATE()) <= ");
        sb.append(minutos);
        sb.append(" order by 1");

        CONSOLE.log(Level.INFO, sb.toString());
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se obtuvo ningun cambio en los items", e.getMessage());
            return null;
        }
    }

    public List<Object> listChanges(Integer minutes) {
        StringBuilder sb = new StringBuilder();
        sb.append("select convert(varchar(20),saldo.ItemCode) itemCode, convert(varchar(10),saldo.WhsCode), CONVERT(int,saldo.onhand) saldo ");
        sb.append("from   OITW saldo ");
        sb.append("inner  join ( ");
        sb.append("select distinct ItemCode, warehouse ");
        sb.append("from   OINM ");
        sb.append("where  Warehouse like '0%' ");
        sb.append("and    DATEDIFF(mi,DocDate + CONVERT(datetime,'1900-01-01 ' + SUBSTRING( ");
        sb.append("convert(varchar(4),RIGHT('0000' + convert(varchar,doctime),4)),1,2) ");
        sb.append("+ ':' + SUBSTRING(convert(varchar(4),RIGHT('0000' + convert");
        sb.append("(varchar,doctime),4)),3,4)),GETDATE()) <= ");
        sb.append(minutes);
        sb.append(") cambios on cambios.itemcode = saldo.itemcode and cambios.warehouse = saldo.whscode ");

        CONSOLE.log(Level.FINEST, sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Object> getWebEnabledStock(String itemcode) {
        StringBuilder sb = new StringBuilder();

        sb.append("select convert(varchar(4), whsCode) whsCode, convert(int,onHand) onHand ");
        sb.append("from   OITW ");
        sb.append("where  itemCode = '");
        sb.append(itemcode);
        sb.append("' and  whsCode like '0%' and onHand > 0 ");

        try {
            List<Object> datos = em.createNativeQuery(sb.toString()).getResultList();
            return datos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Object> listDetailedLastDayChanges() {
        StringBuilder sb = getGenericQueryHeader1();
        sb.append("from   OINM movimiento ");
        sb.append("inner join OITM itm on itm.ItemCode = movimiento.ItemCode ");
        sb.append("inner join ");
        sb.append("(select saldo.itemcode, k.U_modelo, case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end almacen ");
        sb.append(", sum(convert(int,saldo.OnHand)) cantidad ");
        sb.append("from OITW saldo ");
        sb.append("inner join OITM k on k.ItemCode=saldo.ItemCode ");
        sb.append("where saldo.WhsCode like '0%' ");
        sb.append("and    saldo.OnHand > 0 ");
        sb.append("group by saldo.ItemCode, k.U_modelo ");
        sb.append(", case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end ");
        sb.append(") saldo on saldo.ItemCode = itm.ItemCode ");
        sb.append("inner join ITM1 precio on precio.ItemCode = itm.ItemCode ");
        sb.append("left join [@BARU_COLXART] colores on colores.U_articulo = itm.ItemCode and colores.U_principal = '1' ");
        sb.append("left join [@BARUCOLOR] color on color.Code = colores.U_color ");
        sb.append("left join [@BARU_MATXART] materiales on materiales.U_itemCode = itm.ItemCode ");
        sb.append("left join [@BARU_MATERIALES] material on material.Code = materiales.U_matCode  ");
        sb.append("left join [@BARU_COMB_PPAL] combinacionPpal on combinacionPpal.U_referencia = itm.ItemCode ");
        sb.append("left join [@BARU_MARCAS] marca on marca.Code = itm.U_CodigoMarca ");
        sb.append("left join (select * from [@BARU_COMB_PPAL]) combi on combi.U_modelo = itm.U_modelo ");
        sb.append("where  datediff(mi,movimiento.DocDate + CONVERT(datetime,'1900-01-01 ' + substring( convert(varchar(4),right('0000' + convert(varchar,movimiento.doctime),4)),1,2) + ':' + substring(convert(varchar(4),right('0000' + convert(varchar,movimiento.doctime),4)),3,4)),getdate()) <= 1440 ");
        sb.append("and    itm.U_U_Hab_Des = 'tYES' ");
        sb.append("and    precio.PriceList = 2 ");
        //sb.append("and    saldo.WhsCode like '0%' ");
        //sb.append("and    saldo.OnHand > 0 ");
        //sb.append("and    itm.U_U_NomWeb is not null ");
        sb.append("and    itm.SHeight1 is not null ");
        sb.append("and    itm.SLength1 is not null ");
        sb.append("and    itm.SWidth1 is not null ");
        sb.append("and    itm.SWeight1 is not null ");
        sb.append("and    itm.U_DescCorta is not null ");
        sb.append("and    itm.U_descripciona is not null ");
        sb.append("and    itm.SHeight1 > 0 ");
        sb.append("and    itm.SLength1 > 0 ");
        sb.append("and    itm.SWidth1 > 0 ");
        sb.append("and    itm.SWeight1 > 0 ");
        sb.append("and    itm.U_CodigoMarca IS NOT NULL ");
        sb.append("and    LEN(itm.U_CodigoMarca) > 0 ");

        try {
            List<Object> datos = em.createNativeQuery(sb.toString()).getResultList();
            return datos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Object> listItemsWithStock(List<String> references) {
        StringBuilder sb = getGenericQueryHeader1();
        sb.append("from   OITM itm  ");
        sb.append("inner join ");
        sb.append("(select saldo.itemcode, k.U_modelo, case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end almacen ");
        sb.append(", sum(convert(int,saldo.OnHand)) cantidad ");
        sb.append("from OITW saldo ");
        sb.append("inner join OITM k on k.ItemCode=saldo.ItemCode ");
        sb.append("where saldo.WhsCode like '0%' ");
        sb.append("and    saldo.OnHand > 0 ");
        sb.append("group by saldo.ItemCode, k.U_modelo ");
        sb.append(", case when convert(varchar,saldo.WhsCode) like '09%' then '0101' when convert(varchar,saldo.WhsCode) like '01%' then '0101' else convert(varchar,saldo.WhsCode) end ");
        sb.append(") saldo on saldo.ItemCode = itm.ItemCode ");
        sb.append("inner join ITM1 precio on precio.ItemCode = itm.ItemCode  ");
        sb.append("left join [@BARU_COLXART] colores on colores.U_articulo = itm.ItemCode and colores.U_principal = '1'  ");
        sb.append("left join [@BARUCOLOR] color on color.Code = colores.U_color  ");
        sb.append("left join [@BARU_MATXART] materiales on materiales.U_itemCode = itm.ItemCode  ");
        sb.append("left join [@BARU_MATERIALES] material on material.Code = materiales.U_matCode   ");
        sb.append("left join [@BARU_GRUPO] grupo on grupo.Code = itm.U_Grupo ");
        sb.append("left join [@BARU_SUBGRUPO] subgrupo on subgrupo.Code = itm.U_SubGrupo ");
        sb.append("left join OITB dpto on dpto.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("left join [@BARU_COMB_PPAL] combinacionPpal on combinacionPpal.U_referencia = itm.ItemCode ");
        sb.append("left join [@BARU_MARCAS] marca on marca.Code = itm.U_CodigoMarca ");
        sb.append("left join (select * from [@BARU_COMB_PPAL]) combi on combi.U_modelo = itm.U_modelo ");
        sb.append("where  itm.U_U_Hab_Des = 'tYES'  ");
        sb.append("and    precio.PriceList = 2  ");
        //sb.append("and    saldo.WhsCode like '0%'  ");
        //sb.append("and    saldo.OnHand > 0  ");
        //sb.append("and    itm.U_U_NomWeb is not null  ");
        sb.append("and    itm.U_Subgrupo is not null  ");
        sb.append("and    itm.SHeight1 is not null ");
        sb.append("and    itm.SLength1 is not null ");
        sb.append("and    itm.SWidth1 is not null ");
        sb.append("and    itm.SWeight1 is not null ");
        sb.append("and    itm.U_DescCorta is not null ");
        sb.append("and    itm.U_descripciona is not null ");
        sb.append("and    itm.SHeight1 > 0 ");
        sb.append("and    itm.SLength1 > 0 ");
        sb.append("and    itm.SWidth1 > 0 ");
        sb.append("and    itm.SWeight1 > 0 ");
        sb.append("and    itm.U_CodigoMarca IS NOT NULL ");
        sb.append("and    LEN(itm.U_CodigoMarca) > 0 ");

        if (references != null && !references.isEmpty()) {
            sb.append("and    itm.ItemCode IN(");
            for (String reference : references) {
                sb.append("'");
                sb.append(reference);
                sb.append("', ");
            }
            sb.replace(sb.length() - 2, sb.length(), ")");
        }

        CONSOLE.log(Level.INFO, sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private String construirCondicionesComunes(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("     FROM   OITM itm ");
        sb.append("     INNER JOIN OITM itm2 on itm2.U_modelo = itm.U_modelo ");
        sb.append("     INNER  JOIN OITB AS dpto ON dpto.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("	LEFT   JOIN ITM1 precio ON precio.ItemCode = itm.ItemCode AND precio.PriceList = 2 ");
        sb.append("	LEFT   JOIN [@BARU_MARCAS] marca ON marca.Code = itm.U_CodigoMarca ");
        sb.append("	LEFT   JOIN (SELECT s1.itemcode, ");
        sb.append("			    CAST(SUM(s1.OnHand) AS INT) saldo ");
        sb.append("		     FROM   OITW s1 ");
        sb.append("		     WHERE  LEN(s1.ItemCode) = 20 ");
        sb.append("		     AND    s1.WhsCode like '0%' ");
        sb.append("		     AND    s1.OnHand > 0 ");
        // Filtro por almacen
        if (filtros.containsKey("SUCURSAL")) {
            if (!filtros.get("SUCURSAL").isEmpty()) {
                sb.append("AND    s1.WhsCode IN (");
                for (String almacen : filtros.get("SUCURSAL")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }
        sb.append("		     GROUP  BY s1.ItemCode) saldo ON saldo.ItemCode = itm.ItemCode ");
        sb.append("	INNER  JOIN (SELECT s1.itemcode, ");
        sb.append("			    CAST(SUM(s1.OnHand) AS INT) saldo ");
        sb.append("		     FROM   OITW s1 ");
        sb.append("		     WHERE  LEN(s1.ItemCode) = 20 ");
        sb.append("		     AND    s1.WhsCode like '0%' ");
        sb.append("		     AND    s1.OnHand > 0 ");
        // Filtro por almacen
        if (filtros.containsKey("SUCURSAL")) {
            if (!filtros.get("SUCURSAL").isEmpty()) {
                sb.append("AND    s1.WhsCode IN (");
                for (String almacen : filtros.get("SUCURSAL")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }
        sb.append("		     GROUP  BY s1.ItemCode) saldo2 ON saldo2.ItemCode = itm2.ItemCode ");
        sb.append("	LEFT   JOIN [@BARU_GRUPO] grupo ON grupo.Code = itm.U_Grupo ");
        sb.append("	LEFT   JOIN [@BARU_SUBGRUPO] subgrupo ON subgrupo.Code = itm.U_SubGrupo ");
        sb.append("	LEFT   JOIN [@BARU_COLXART] colorArticulo ON colorArticulo.U_articulo = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARUCOLOR] color ON color.Code = colorArticulo.U_color ");
        sb.append("	LEFT   JOIN [@BARU_COLOR_GENERICO] colorGenerico ON colorGenerico.Code = color.U_colorgenerico ");
        sb.append("	LEFT   JOIN [@BARU_MATXART] materialArticulo ON materialArticulo.U_itemCode = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARU_MATERIALES] material ON material.Code = materialArticulo.U_matCode ");
        sb.append("	LEFT   JOIN (SELECT s2.itemcode, s2.WhsCode ");
        sb.append("		     FROM   OITW s2 ");
        sb.append("		     WHERE  LEN(s2.ItemCode) = 20 ");
        //Filtrar solo productos con saldo
        if (conSaldo) {
            sb.append("		     AND    s2.OnHand > 0 ");
        }
        sb.append("		    ) almacenes ON almacenes.ItemCode = itm.ItemCode ");
        sb.append("	WHERE  itm.SellItem = 'Y' ");
        sb.append("	AND    itm.InvntItem = 'Y' ");
        sb.append("	AND    itm.PrchseItem = 'Y' ");
        sb.append("	AND    itm.ItemType = 'I' ");
        sb.append("	AND    LEN(itm.ItemCode) = 20 ");
        // Filtro por referencia
        if (filtros.containsKey("REFERENCIA")) {
            if (!filtros.get("REFERENCIA").isEmpty()) {
                sb.append("AND    itm.ItemCode IN (");
                for (String ref : filtros.get("REFERENCIA")) {
                    sb.append("'");
                    sb.append(ref);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        // Filtro por marca
        if (filtros.containsKey("MARCA")) {
            if (!filtros.get("MARCA").isEmpty()) {
                sb.append("AND    marca.Name IN (");
                for (String marca : filtros.get("MARCA")) {
                    sb.append("'");
                    sb.append(marca);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        // Filtro por proveedor
        if (filtros.containsKey("PROVEEDOR")) {
            if (!filtros.get("PROVEEDOR").isEmpty()) {
                sb.append("AND    ( ");
                for (String prov : filtros.get("PROVEEDOR")) {
                    sb.append("itm.ItemCode like '");
                    sb.append(prov);
                    sb.append("%' OR ");
                }
                sb.replace(sb.length() - 3, sb.length(), ") ");
            }
        }

        // Filtro por almacen
        if (filtros.containsKey("ALMACEN")) {
            if (!filtros.get("ALMACEN").isEmpty()) {
                sb.append("AND    saldo.WhsCode IN (");
                for (String almacen : filtros.get("ALMACEN")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por color
        if (filtros.containsKey("COLOR")) {
            if (!filtros.get("COLOR").isEmpty()) {
                sb.append("AND    colorGenerico.Name IN (");
                for (String almacen : filtros.get("COLOR")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por color
        if (filtros.containsKey("DEPARTAMENTO")) {
            if (!filtros.get("DEPARTAMENTO").isEmpty()) {
                sb.append("AND    dpto.U_Nombre_Filtros IN (");
                for (String dpto : filtros.get("DEPARTAMENTO")) {
                    sb.append("'");
                    sb.append(dpto);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por grupo
        if (filtros.containsKey("GRUPO")) {
            if (!filtros.get("GRUPO").isEmpty()) {
                sb.append("AND    grupo.Name IN (");
                for (String grupo : filtros.get("GRUPO")) {
                    sb.append("'");
                    sb.append(grupo);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por subgrupo
        if (filtros.containsKey("SUBGRUPO")) {
            if (!filtros.get("SUBGRUPO").isEmpty()) {
                sb.append("AND    subgrupo.U_descripcion IN (");
                for (String almacen : filtros.get("SUBGRUPO")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por material
        if (filtros.containsKey("MATERIAL")) {
            if (!filtros.get("MATERIAL").isEmpty()) {
                sb.append("AND    material.Name IN (");
                for (String almacen : filtros.get("MATERIAL")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtrar solo productos con saldo
        if (conSaldo) {
            sb.append("AND    saldo.saldo > 0 ");
        }

        //Filtrar por texto usando las columnas FrgnName y Ref_Pro
        if (filtros.containsKey("TEXTO")) {
            if (!filtros.get("TEXTO").isEmpty()) {
                sb.append("AND (");
                for (String filtro : filtros.get("TEXTO")) {
                    sb.append("itm.FrgnName LIKE '%");
                    sb.append(filtro);
                    sb.append("%' OR itm.U_U_Ref_Pro LIKE '%");
                    sb.append(filtro);
                    sb.append("%' OR itm.ItemCode = '");
                    sb.append(filtro);
                    sb.append("' OR ");
                }
                sb.replace(sb.length() - 3, sb.length(), ") ");
            }
        }

        //Filtrar por precio
        if (filtros.containsKey("PRECIO") && filtros.get("PRECIO").size() == 2) {
            sb.append("AND precio.Price BETWEEN ");
            sb.append(filtros.get("PRECIO").get(0));
            sb.append(" AND ");
            sb.append(filtros.get("PRECIO").get(1));
            sb.append(" ");
        }

        //Filtrar por coleccion
        if (filtros.containsKey("COLECCIÓN") && !filtros.get("COLECCIÓN").isEmpty()) {
            sb.append("AND    itm.U_Coleccion IN (");
            for (String coleccion : filtros.get("COLECCIÓN")) {
                sb.append("'");
                sb.append(coleccion);
                sb.append("', ");
            }
            sb.replace(sb.length() - 2, sb.length(), ") ");
        }

        return sb.toString();
    }

    private String construirCondicionesComunesV2(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("     FROM   OITM itm ");
        sb.append("     INNER  JOIN OITM itm2 on itm2.U_modelo = itm.U_modelo ");
        sb.append("     INNER  JOIN OITB AS dpto ON dpto.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("	LEFT   JOIN ITM1 precio ON precio.ItemCode = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARU_MARCAS] marca ON marca.Code = itm.U_CodigoMarca ");
        sb.append("	INNER  JOIN OITW saldo ON saldo.ItemCode = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARU_GRUPO] grupo ON grupo.Code = itm.U_Grupo ");
        sb.append("	LEFT   JOIN [@BARU_SUBGRUPO] subgrupo ON subgrupo.Code = itm.U_SubGrupo ");
        sb.append("	LEFT   JOIN [@BARU_COLXART] colorArticulo ON colorArticulo.U_articulo = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARUCOLOR] color ON color.Code = colorArticulo.U_color ");
        sb.append("	LEFT   JOIN [@BARU_COLOR_GENERICO] colorGenerico ON colorGenerico.Code = color.U_colorgenerico ");
        sb.append("	LEFT   JOIN [@BARU_MATXART] materialArticulo ON materialArticulo.U_itemCode = itm.ItemCode ");
        sb.append("	LEFT   JOIN [@BARU_MATERIALES] material ON material.Code = materialArticulo.U_matCode ");
        sb.append("	LEFT   JOIN OITW almacenes ON almacenes.ItemCode = itm.ItemCode ");
        if (conSaldo) {
            sb.append("		AND almacenes.OnHand > 0 ");
        }
        sb.append("	WHERE  itm.SellItem = 'Y' ");
        sb.append("	AND    itm.InvntItem = 'Y' ");
        sb.append("	AND    itm.PrchseItem = 'Y' ");
        sb.append("	AND    itm.ItemType = 'I' ");
        sb.append("	AND    LEN(itm.ItemCode) = 20 ");
        sb.append("	AND    precio.PriceList = 2 ");
        sb.append("	AND    saldo.OnHand > 0 ");
        sb.append("	AND    almacenes.OnHand > 0 ");
        sb.append("	AND    saldo.WhsCode NOT LIKE 'CL%' ");
        sb.append("	AND    saldo.WhsCode NOT LIKE 'DM%' ");
        sb.append("	AND    almacenes.WhsCode NOT LIKE 'CL%' ");
        sb.append("	AND    almacenes.WhsCode NOT LIKE 'DM%' ");
        // Filtro por almacen
        if (filtros.containsKey("SUCURSAL")) {
            if (!filtros.get("SUCURSAL").isEmpty()) {
                sb.append("AND saldo.WhsCode IN (");
                filtros.get("SUCURSAL").stream().map((almacen) -> {
                    sb.append("'");
                    sb.append(almacen);
                    return almacen;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }
        // Filtro por referencia
        if (filtros.containsKey("REFERENCIA")) {
            if (!filtros.get("REFERENCIA").isEmpty()) {
                sb.append("AND    itm.ItemCode IN (");
                filtros.get("REFERENCIA").stream().map((ref) -> {
                    sb.append("'");
                    sb.append(ref);
                    return ref;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        // Filtro por marca
        if (filtros.containsKey("MARCA")) {
            if (!filtros.get("MARCA").isEmpty()) {
                sb.append("AND    marca.Name IN (");
                filtros.get("MARCA").stream().map((marca) -> {
                    sb.append("'");
                    sb.append(marca);
                    return marca;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        // Filtro por proveedor
        if (filtros.containsKey("PROVEEDOR")) {
            if (!filtros.get("PROVEEDOR").isEmpty()) {
                sb.append("AND    ( ");
                filtros.get("PROVEEDOR").stream().map((prov) -> {
                    sb.append("itm.ItemCode like '");
                    sb.append(prov);
                    return prov;
                }).forEach((_item) -> {
                    sb.append("%' OR ");
                });
                sb.replace(sb.length() - 3, sb.length(), ") ");
            }
        }

        // Filtro por almacen
        if (filtros.containsKey("ALMACEN")) {
            if (!filtros.get("ALMACEN").isEmpty()) {
                sb.append("AND    saldo.WhsCode IN (");
                filtros.get("ALMACEN").stream().map((almacen) -> {
                    sb.append("'");
                    sb.append(almacen);
                    return almacen;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por color
        if (filtros.containsKey("COLOR")) {
            if (!filtros.get("COLOR").isEmpty()) {
                sb.append("AND    colorGenerico.Name IN (");
                filtros.get("COLOR").stream().map((almacen) -> {
                    sb.append("'");
                    sb.append(almacen);
                    return almacen;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por color
        if (filtros.containsKey("DEPARTAMENTO")) {
            if (!filtros.get("DEPARTAMENTO").isEmpty()) {
                sb.append("AND    dpto.U_Nombre_Filtros IN (");
                filtros.get("DEPARTAMENTO").stream().map((dpto) -> {
                    sb.append("'");
                    sb.append(dpto);
                    return dpto;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por grupo
        if (filtros.containsKey("GRUPO")) {
            if (!filtros.get("GRUPO").isEmpty()) {
                sb.append("AND    grupo.Name IN (");
                filtros.get("GRUPO").stream().map((grupo) -> {
                    sb.append("'");
                    sb.append(grupo);
                    return grupo;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por subgrupo
        if (filtros.containsKey("SUBGRUPO")) {
            if (!filtros.get("SUBGRUPO").isEmpty()) {
                sb.append("AND    subgrupo.U_descripcion IN (");
                filtros.get("SUBGRUPO").stream().map((almacen) -> {
                    sb.append("'");
                    sb.append(almacen);
                    return almacen;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por material
        if (filtros.containsKey("MATERIAL")) {
            if (!filtros.get("MATERIAL").isEmpty()) {
                sb.append("AND    material.Name IN (");
                filtros.get("MATERIAL").stream().map((almacen) -> {
                    sb.append("'");
                    sb.append(almacen);
                    return almacen;
                }).forEach((_item) -> {
                    sb.append("', ");
                });
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtrar por texto usando las columnas FrgnName y Ref_Pro
        if (filtros.containsKey("TEXTO")) {
            if (!filtros.get("TEXTO").isEmpty()) {
                sb.append("AND (");
                filtros.get("TEXTO").stream().map((filtro) -> {
                    sb.append("itm.FrgnName LIKE '%");
                    sb.append(filtro);
                    return filtro;
                }).map((filtro) -> {
                    sb.append("%' OR itm.U_U_Ref_Pro LIKE '%");
                    sb.append(filtro);
                    return filtro;
                }).map((filtro) -> {
                    sb.append("%' OR itm.ItemCode = '");
                    sb.append(filtro);
                    return filtro;
                }).forEach((_item) -> {
                    sb.append("' OR ");
                });
                sb.replace(sb.length() - 3, sb.length(), ") ");
            }
        }

        //Filtrar por precio
        if (filtros.containsKey("PRECIO") && filtros.get("PRECIO").size() == 2) {
            sb.append("AND precio.Price BETWEEN ");
            sb.append(filtros.get("PRECIO").get(0));
            sb.append(" AND ");
            sb.append(filtros.get("PRECIO").get(1));
            sb.append(" ");
        }

        //Filtrar por coleccion
        if (filtros.containsKey("COLECCIÓN") && !filtros.get("COLECCIÓN").isEmpty()) {
            sb.append("AND    itm.U_Coleccion IN (");
            filtros.get("COLECCIÓN").stream().map((coleccion) -> {
                sb.append("'");
                sb.append(coleccion);
                return coleccion;
            }).forEach((_item) -> {
                sb.append("', ");
            });
            sb.replace(sb.length() - 2, sb.length(), ") ");
        }

        return sb.toString();
    }

    private String construirCondicionesComunesVista(Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();
        if (filtros.isEmpty()) {
            return "";
        }
        sb.append("where ");
        boolean and = false;
        // Filtro por referencia
        if (filtros.containsKey("REF")) {
            if (!filtros.get("REFERENCIA").isEmpty()) {
                if (and) {
                    sb.append("AND ItemCode IN (");
                } else {
                    sb.append("ItemCode IN (");
                    and = true;
                }

                for (String ref : filtros.get("REF")) {
                    sb.append("'");
                    sb.append(ref);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        // Filtro por marca
        if (filtros.containsKey("MAR")) {
            if (!filtros.get("MAR").isEmpty()) {
                if (and) {
                    sb.append("AND codMarca IN (");
                } else {
                    sb.append("codMarca IN (");
                    and = true;
                }
                for (String marca : filtros.get("MAR")) {
                    sb.append("'");
                    sb.append(marca);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por color
        if (filtros.containsKey("COL")) {
            if (!filtros.get("COL").isEmpty()) {
                if (and) {
                    sb.append("AND codColor IN (");
                } else {
                    sb.append("codColor IN (");
                    and = true;
                }
                for (String almacen : filtros.get("COL")) {
                    sb.append("'");
                    sb.append(almacen);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por departamento
        if (filtros.containsKey("DEP")) {
            if (!filtros.get("DEP").isEmpty()) {
                if (and) {
                    sb.append("AND codDpto IN (");
                } else {
                    sb.append("codDpto IN (");
                    and = true;
                }
                for (String grupo : filtros.get("DEP")) {
                    sb.append("'");
                    sb.append(grupo);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por grupo
        if (filtros.containsKey("GRU")) {
            if (!filtros.get("GRU").isEmpty()) {
                if (and) {
                    sb.append("AND codGrupo IN (");
                } else {
                    sb.append("codGrupo IN (");
                    and = true;
                }
                for (String grupo : filtros.get("GRU")) {
                    sb.append("'");
                    sb.append(grupo);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por subgrupo
        if (filtros.containsKey("SUB")) {
            if (!filtros.get("SUB").isEmpty()) {
                if (and) {
                    sb.append("AND codSubgrupo IN (");
                } else {
                    sb.append("codSubgrupo IN (");
                    and = true;
                }
                for (String subgrupo : filtros.get("SUB")) {
                    sb.append("'");
                    sb.append(subgrupo);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtro por material
        if (filtros.containsKey("MAT")) {
            if (!filtros.get("MAT").isEmpty()) {
                if (and) {
                    sb.append("AND codMaterial IN (");
                } else {
                    sb.append("codMaterial IN (");
                    and = true;
                }
                for (String material : filtros.get("MAT")) {
                    sb.append("'");
                    sb.append(material);
                    sb.append("', ");
                }
                sb.replace(sb.length() - 2, sb.length(), ") ");
            }
        }

        //Filtrar por texto usando las columnas FrgnName y Ref_Pro
        if (filtros.containsKey("TEX")) {
            if (!filtros.get("TEX").isEmpty()) {
                if (and) {
                    sb.append("AND (");
                } else {
                    sb.append("(");
                    and = true;
                }
                for (String filtro : filtros.get("TEX")) {
                    sb.append("FrgnName LIKE '%");
                    sb.append(filtro);
                    sb.append("%' OR U_U_Ref_Pro LIKE '%");
                    sb.append(filtro);
                    sb.append("%' OR ItemCode = '");
                    sb.append(filtro);
                    sb.append("' OR ");
                }
                sb.replace(sb.length() - 3, sb.length(), ") ");
            }
        }

        //Filtrar por precio
        if (filtros.containsKey("PRE") && filtros.get("PRE").size() == 2) {
            if (and) {
                sb.append("AND precio BETWEEN ");
            } else {
                sb.append("precio BETWEEN ");
                and = true;
            }
            String minimo = filtros.get("PRE").get(0);
            String maximo = filtros.get("PRE").get(1);
            sb.append(minimo == null || minimo.trim().isEmpty() ? "0" : minimo);
            sb.append(" AND ");
            sb.append(maximo == null || maximo.trim().isEmpty() ? "500000000" : maximo);
            sb.append(" ");
        }

        //Filtrar por coleccion
        if (filtros.containsKey("CLC") && !filtros.get("CLC").isEmpty()) {
            if (and) {
                sb.append("AND coleccion IN( ");
            } else {
                sb.append("coleccion IN( ");
            }
            for (String coleccion : filtros.get("CLC")) {
                sb.append("'");
                sb.append(coleccion);
                sb.append("', ");
            }
            sb.replace(sb.length() - 2, sb.length(), ") ");
        }

        return sb.toString();
    }

    public int consultarTotalRegistrosVista(Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(DISTINCT ItemCode) registros ");
        sb.append("from itemsconsaldo ");
        sb.append(construirCondicionesComunesVista(filtros));

        try {
            CONSOLE.log(Level.FINE, sb.toString());
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el total de articulos con los parametros [{0}]", filtros);
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public int consultarTotalRegistros(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT COUNT(DISTINCT itm.ItemCode) registros ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));

        try {
            CONSOLE.log(Level.FINE, sb.toString());
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el total de articulos con los parametros [{0}]", filtros);
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List consultarProductosVista(int pagina, int registrosPagina, Map<String, List<String>> filtros, String orderBy, String sortOrder) {
        //Si no existe un criterio para ordenar, se ordena por fecha de nuevos primero
        if (orderBy == null || orderBy.trim().isEmpty()) {
            orderBy = "NUEVO";
            sortOrder = "DESC";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ItemCode, FrgnName, U_U_Ref_Pro, precio, saldo, nuevoDesde, descripcion FROM ( ");
        sb.append("select ItemCode, FrgnName, U_U_Ref_Pro, precio, sum(saldo) saldo, nuevoDesde, descripcion ");
        sb.append("from itemsconsaldo ");
        sb.append(construirCondicionesComunesVista(filtros));
        sb.append("group by ItemCode, FrgnName, U_U_Ref_Pro, precio, nuevoDesde, descripcion ");
        sb.append(") productos ORDER BY ");
        switch (orderBy) {
            case "REFERENCIA":
                sb.append("ItemCode ");
                sb.append(sortOrder);
                break;
            case "SALDO":
                sb.append("saldo ");
                sb.append(sortOrder);
                break;
            case "PRECIO":
                sb.append("precio ");
                sb.append(sortOrder);
                break;
            case "NUEVO":
                sb.append("nuevoDesde ");
                sb.append(sortOrder);
                break;
            case "NOMBRE":
                sb.append("FrgnName ");
                sb.append(sortOrder);
                break;
            default:
                break;
        }
        sb.append(" OFFSET ");
        sb.append((pagina - 1) < 0 ? 0 : (pagina - 1));
        sb.append(" * ");
        sb.append(registrosPagina);
        sb.append(" ROWS FETCH NEXT ");
        sb.append(registrosPagina);
        sb.append(" ROWS ONLY ");

        try {
            CONSOLE.log(Level.FINE, sb.toString());
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar articulos con los parametros [{0}],[{1}],[{2}]", new Object[]{pagina, registrosPagina, filtros});
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List consultarProductos(int pagina, int registrosPagina, Map<String, List<String>> filtros, String orderBy, String sortOrder, boolean conSaldo) {
        //Si no existe un criterio para ordenar, se ordena por fecha de nuevos primero
        if (orderBy == null || orderBy.trim().isEmpty()) {
            orderBy = "NUEVO";
            sortOrder = "DESC";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ItemCode, FrgnName, U_U_Ref_Pro, precio, CONVERT(INT, saldo) AS saldo, nuevoDesde, descripcion, COUNT(DISTINCT ItemCodeCombinacion) colores ");
        sb.append("FROM   (SELECT ItemCode, FrgnName, U_U_Ref_Pro, precio, SUM(saldo) AS saldo, nuevoDesde, descripcion, ItemCodeCombinacion ");
        sb.append("        FROM (SELECT DISTINCT CAST(itm.ItemCode AS VARCHAR(20)) ItemCode, ");
        sb.append("		  CAST(itm.FrgnName AS VARCHAR(60)) FrgnName, ");
        sb.append("		  CAST(itm.U_U_Ref_Pro AS VARCHAR(80)) U_U_Ref_Pro, ");
        sb.append("		  CAST(precio.Price AS INT) precio, ");
        sb.append("		  saldo.OnHand AS saldo, ");
        sb.append("		  saldo.WhsCode AS almacen, ");
        sb.append("		  ISNULL(itm.U_U_Act_Qn, DATEADD(YEAR, -8, GETDATE())) nuevoDesde, ");
        sb.append("		  CAST(itm.U_DescCorta as VARCHAR(MAX)) descripcion, ");
        sb.append("		  CAST(itm2.ItemCode AS VARCHAR(20)) ItemCodeCombinacion ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("	   GROUP  BY itm.ItemCode, itm.FrgnName, itm.U_U_Ref_Pro, precio.Price, saldo.OnHand, itm.U_U_Act_Qn, itm.U_DescCorta, itm2.ItemCode, saldo.whscode) AS T1 ");
        //Filtrar solo productos con saldo
        if (conSaldo) {
            sb.append("WHERE  saldo > 0 ");
        }
        sb.append("	  GROUP  BY ItemCode, FrgnName, U_U_Ref_Pro, precio, nuevoDesde, descripcion, ItemCodeCombinacion) productos ");
        sb.append("GROUP  BY ItemCode, FrgnName, U_U_Ref_Pro, precio, saldo, nuevoDesde, descripcion ");
        sb.append("ORDER  BY ");
        switch (orderBy) {
            case "REFERENCIA":
                sb.append("ItemCode ");
                sb.append(sortOrder);
                break;
            case "SALDO":
                sb.append("saldo ");
                sb.append(sortOrder);
                break;
            case "PRECIO":
                sb.append("precio ");
                sb.append(sortOrder);
                break;
            case "NUEVO":
                sb.append("nuevoDesde ");
                sb.append(sortOrder);
                break;
            case "NOMBRE":
                sb.append("FrgnName ");
                sb.append(sortOrder);
                break;
            default:
                break;
        }
        sb.append(" OFFSET ");
        sb.append((pagina - 1) < 0 ? 0 : (pagina - 1));
        sb.append(" * ");
        sb.append(registrosPagina);
        sb.append(" ROWS FETCH NEXT ");
        sb.append(registrosPagina);
        sb.append(" ROWS ONLY ");

        try {
            CONSOLE.log(Level.INFO, sb.toString());
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar articulos con los parametros [{0}],[{1}],[{2}]", new Object[]{pagina, registrosPagina, filtros});
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public Integer getItemPrice(String itemCode, boolean conSaldo) {
        if (!conSaldo) {
            return getItemPrice(itemCode);
        }
        if (itemCode == null || itemCode.trim().isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CAST(prc.Price AS INT) Price ");
        sb.append("FROM ITM1 prc WHERE prc.ItemCode = '");
        sb.append(itemCode);
        sb.append("' AND prc.PriceList = 2 ");

        try {
            return (Integer) em.createNativeQuery(sb.toString()).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, sb.toString());
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los precios de los productos. ", e);
        }
        return null;
    }

    public Integer getItemPrice(String itemCode) {
        if (itemCode == null || itemCode.trim().isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CAST(prc.Price AS INT) Price ");
        sb.append("FROM OITM itm ");
        sb.append("INNER JOIN ITM1 prc ON prc.ItemCode = itm.ItemCode AND prc.PriceList = 2 ");
        sb.append("INNER JOIN OITW saldo ON saldo.ItemCode = itm.ItemCode AND saldo.OnHand > 0 AND saldo.WhsCode LIKE '0%' ");
        sb.append("WHERE itm.ItemCode = '");
        sb.append(itemCode);
        sb.append("'");
        try {
            return (Integer) em.createNativeQuery(sb.toString()).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, sb.toString());
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los precios de los productos. ", e);
        }
        return null;
    }

    public Object[] getItemPriceAndTax(String itemCode, boolean conSaldo) {
        if (itemCode == null || itemCode.trim().isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CAST(prc.Price AS INT) Price, CAST(tax.Rate AS int) Tax ");
        sb.append("FROM OITM itm ");
        sb.append("INNER JOIN ITM1 prc ON prc.ItemCode = itm.ItemCode AND prc.PriceList = 2 ");
        if (conSaldo) {
            sb.append("INNER JOIN OITW saldo ON saldo.ItemCode = itm.ItemCode AND saldo.OnHand > 0 AND saldo.WhsCode LIKE '0%' ");
        }
        sb.append("INNER JOIN OSTC tax on tax.Code = itm.TaxCodeAR ");
        sb.append("WHERE itm.ItemCode = '");
        sb.append(itemCode);
        sb.append("'");
        try {
            return (Object[]) em.createNativeQuery(sb.toString()).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return new Object[]{0, 0};
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, sb.toString());
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los precios de los productos. ", e);
        }
        return null;
    }

    public List<Object[]> getItemsPrices(List<String> itemCodes) {
        if (itemCodes == null || itemCodes.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CAST(itm.ItemCode AS VARCHAR) ItemCode, CAST(prc.Price AS INT) Price ");
        sb.append("FROM OITM itm ");
        sb.append("INNER JOIN ITM1 prc ON prc.ItemCode = itm.ItemCode AND prc.PriceList = 2 ");
        sb.append("WHERE itm.ItemCode IN (");
        for (String itemCode : itemCodes) {
            sb.append("'");
            sb.append(itemCode);
            sb.append("',");
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        try {
            List<Object[]> precios = em.createNativeQuery(sb.toString()).getResultList();
            CONSOLE.log(Level.FINE, "Se obtuvieron [{0}] precios para [{1}] referencias enviadas", new Object[]{precios.size(), itemCodes.size()});
            return precios;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, sb.toString());
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los precios de los productos. ", e);
        }
        return null;
    }

    public List<ItemInventario> obtenerReferenciasParametro(String parametro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemInventario> cq = cb.createQuery(ItemInventario.class);
        Root<ItemInventario> item = cq.from(ItemInventario.class);
        Predicate disjuncion = cb.disjunction();

        disjuncion.getExpressions().add(cb.like(item.<String>get("itemCode"), "%" + validarReferencia(parametro) + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("itemName"), "%" + parametro + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("frgnName"), "%" + parametro + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("uURefPro"), "%" + parametro + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("uURefAduana"), "%" + parametro + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("umodelo"), "%" + parametro + "%"));
        disjuncion.getExpressions().add(cb.like(item.<String>get("uCodigoEan"), "%" + parametro + "%"));

        cq.where(disjuncion);

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    private String validarReferencia(String parametro) {
        if ((parametro.contains("*") || parametro.contains("%") || parametro.contains(".")) && parametro.length() > 4) {
            parametro = parametro.substring(0, 3) + parametro.substring(4);
            parametro = StringUtils.rightPad(parametro.substring(0, 3), 20 - parametro.substring(3).length(), "0") + parametro.substring(3);
        }
        return parametro;
    }

    public ItemInventario buscarXEan(String codigoEan) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemInventario> cq = cb.createQuery(ItemInventario.class);
        Root<ItemInventario> item = cq.from(ItemInventario.class);

        cq.where(cb.equal(item.get("uCodigoEan"), codigoEan));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<String> obtenerReferenciasConSaldo() {
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct cast(ItemCode as varchar(20)) from OITW ");
        sb.append("where OnHand > 0 and WhsCode like '0%' order by 1 ");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las referencias con saldo. ", e);
            return new ArrayList<>();
        }
    }

    public List<String> obtenerReferenciasConSaldoMED() {
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct cast(ItemCode as varchar(20)) from OITW ");
        sb.append("where OnHand > 0 and WhsCode like '0%' and ItemCode NOT IN ");
        sb.append("(select distinct cast(ItemCode as varchar(20)) from OITW ");
        //TODO: Se condiciona almacenes por cierre de tienda Bogota. 2018-01-15.
        sb.append("where OnHand > 0 and WhsCode IN ('0301', '0104', '0831', '0813')) order by 1 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los items con saldo solo en medellin. ");
        }
        return new ArrayList<>();
    }

    public List<String> obtenerReferenciasSimilares(List<String> referencias) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(itm2.ItemCode as varchar(20)) itemcode from OITM itm ");
        sb.append("inner join OITM itm2 on itm2.U_modelo = itm.U_modelo ");
        sb.append("where itm.ItemCode in (");
        for (String referencia : referencias) {
            sb.append("'");
            sb.append(referencia);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") ");
        try {
            return (List<String>) em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las referencias del mismo modelo. ", e);
            return new ArrayList<>();
        }
    }

    public List<ItemInventario> obtenerReferenciasModelo(String modelo) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OITM ");
        sb.append("WHERE  U_modelo = '");
        sb.append(modelo);
        sb.append("' AND  U_Bloqueado IS NULL ");
        sb.append("OR     U_Bloqueado <> 'Y' ");

        try {
            return em.createNativeQuery(sb.toString(), ItemInventario.class).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public boolean bloquearItem(String referencia) {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE OITM ");
        sb.append("SET    U_Bloqueado = 'Y' ");
        sb.append("WHERE  ItemCode = '");
        sb.append(referencia);
        sb.append("'");

        try {
            em.createNativeQuery(sb.toString()).executeUpdate();
            return true;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public List<Object> obtenerFiltros(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM (");
        sb.append("SELECT DISTINCT CAST('COL' as VARCHAR(MAX)) as tipo, CONVERT(VARCHAR, colorGenerico.Name) AS valor ");
        sb.append(construirCondicionesComunes(conSaldo, filtros));
        sb.append(" UNION ");
        sb.append("SELECT distinct CAST('MAT' as VARCHAR(MAX)) as tipo, CONVERT(VARCHAR, material.U_nombreWeb) AS valor ");
        sb.append(construirCondicionesComunes(conSaldo, filtros));
        sb.append(" UNION ");
        sb.append("SELECT distinct CAST('SUB' as VARCHAR(MAX)) as tipo, CONVERT(VARCHAR, subgrupo.U_descripcion) AS valor ");
        sb.append(construirCondicionesComunes(conSaldo, filtros));
        sb.append(" UNION ");
        sb.append("SELECT distinct CAST('GRU' as VARCHAR(MAX)) as tipo, CONVERT(VARCHAR, grupo.Name) AS valor ");
        sb.append(construirCondicionesComunes(conSaldo, new HashMap<String, List<String>>()));
        sb.append(" UNION ");
        sb.append("SELECT distinct CAST('MAR' as VARCHAR(MAX)) as tipo, CONVERT(VARCHAR, marca.Name) AS valor ");
        sb.append(construirCondicionesComunes(conSaldo, filtros));
        sb.append(") AS filtros ");
        sb.append("WHERE  valor IS NOT NULL ");
        CONSOLE.info(sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object[]> obtenerFiltrosVista(Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct ");
        sb.append("codDpto, nombreDpto, codGrupo, nombreGrupo, codSubgrupo, nombreSubgrupo ");
        sb.append(", codMarca, nombreMarca, codColor, nombreColor, codMaterial, nombreMaterial, coleccion, proveedor ");
        sb.append("from ItemsConSaldo ");
        sb.append(construirCondicionesComunesVista(filtros));
        CONSOLE.info(sb.toString());
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosSucursal(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, almacenes.WhsCode) AS almacen ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS codigosAlmacenes ");
        sb.append("WHERE  codigosAlmacenes.almacen IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosColor(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, colorGenerico.Name) AS nombre ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS colores ");
        sb.append("WHERE  colores.nombre IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosMateriales(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, material.Name) AS nombre ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS materiales ");
        sb.append("WHERE  materiales.nombre IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosProveedor(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, LEFT(itm.ItemCode,3)) AS proveedor ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS proveedores ");
        sb.append("WHERE  proveedores.proveedor IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosSubGrupo(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, subgrupo.U_descripcion) AS descripcion ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS subgrupos ");
        sb.append("WHERE  subgrupos.descripcion IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosGrupo(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, grupo.Name) AS nombre ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS grupos ");
        sb.append("WHERE  grupos.nombre IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object> obtenerFiltrosMarca(boolean conSaldo, Map<String, List<String>> filtros) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   (SELECT DISTINCT CONVERT(VARCHAR, marca.Name) AS nombre ");
        sb.append(construirCondicionesComunesV2(conSaldo, filtros));
        sb.append("       ) AS marcas ");
        sb.append("WHERE  marcas.nombre IS NOT NULL ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object[]> obtenerFiltrosDisponibles() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CAST('COL' AS VARCHAR(3)) AS tipo, ");
        sb.append("       CAST(colGen.Name AS VARCHAR(100)) AS valor, ");
        sb.append("	  CAST(colGen.Code AS VARCHAR(3)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN [@BARU_COLXART] colArt ON colArt.U_articulo = itm.ItemCode AND colArt.U_principal = 1 ");
        sb.append("INNER  JOIN [@BARUCOLOR] col ON col.Code = colArt.U_color ");
        sb.append("INNER  JOIN [@BARU_COLOR_GENERICO] colGen ON colGen.Code = col.U_colorgenerico ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('MAT' AS VARCHAR(3)) AS tipo, ");
        sb.append("	  CAST(mat.Name AS VARCHAR(100)) AS valor, ");
        sb.append("	  CAST(mat.Code AS VARCHAR(3)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN [@BARU_MATXART] matArt ON matArt.U_itemCode = itm.ItemCode ");
        sb.append("INNER  JOIN [@BARU_MATERIALES] mat ON mat.Code = matArt.U_matCode ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('DEP' AS VARCHAR(3)) AS tipo, ");
        sb.append("       CAST(dep.U_Nombre_Filtros AS varchar(100)) AS valor, ");
        sb.append("       CAST(dep.ItmsGrpCod AS varchar(50)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER JOIN OITW saldo on saldo.ItemCode = itm.ItemCode and saldo.OnHand > 0 and saldo.WhsCode like '0%' ");
        sb.append("INNER  JOIN OITB dep ON dep.ItmsGrpCod = itm.ItmsGrpCod ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    itm.SellItem = 'Y' ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('GRU' AS VARCHAR(3)) AS tipo, ");
        sb.append("	  CAST(gr.Name AS VARCHAR(100)) AS valor, ");
        sb.append("	  CAST(gr.Code AS VARCHAR(100)) AS info, ");
        sb.append("       CAST('DEP' AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(gr.U_Cod_Dep AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN [@BARU_GRUPO] gr ON gr.Code = itm.U_Grupo ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("AND    gr.U_Web = '1' ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('SUB' AS VARCHAR(3)) AS tipo, ");
        sb.append("	  CAST(sub.U_descripcion AS varchar(100)) AS valor, ");
        sb.append("	  CAST(sub.Code AS varchar(3)) AS info, ");
        sb.append("       CAST('GRU' AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(sub.U_Cod_Grupo AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN [@BARU_SUBGRUPO] sub ON sub.Code = itm.U_SubGrupo ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("AND    sub.U_Web = '1' ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('PRO' AS VARCHAR(3)) AS tipo, ");
        sb.append("	  CAST(SUBSTRING(itm.ItemCode, 1, 3) AS VARCHAR(3)) AS valor, ");
        sb.append("	  CAST('' AS VARCHAR(1)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('SUC' AS VARCHAR(3)) AS tipo, ");
        sb.append("	  CAST(alm.WhsCode AS VARCHAR(30)) AS valor, ");
        sb.append("	  CAST('' AS VARCHAR(1)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN OITW alm ON alm.ItemCode = itm.ItemCode ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    alm.OnHand > 0 ");
        sb.append("AND    alm.WhsCode NOT LIKE '%CL%' ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('MAR' AS VARCHAR(3)) AS tipo, ");
        sb.append("       CAST(mar.Name AS VARCHAR(100)) AS valor, ");
        sb.append("       CAST(mar.Code AS VARCHAR(6)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN [@BARU_MARCAS] mar ON mar.Code = itm.U_CodigoMarca ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("UNION ");
        sb.append("SELECT DISTINCT CAST('CLC' AS VARCHAR(3)) AS tipo, ");
        sb.append("       CAST(itm.U_Coleccion AS VARCHAR(100)) AS valor, ");
        sb.append("       CAST('' AS VARCHAR(5)) AS info, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS tipoPadre, ");
        sb.append("       CAST(NULL AS VARCHAR(5)) AS codigoPadre ");
        sb.append("FROM   OITM itm ");
        sb.append("WHERE  itm.OnHand > 0 ");
        sb.append("AND    LEN(itm.ItemCode) = 20 ");
        sb.append("AND    itm.U_Coleccion IS NOT NULL ");
        sb.append("ORDER  BY tipo,valor ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public String obtenerItemCode(String idMercadolibre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<ItemInventario> root = cq.from(ItemInventario.class);
        cq.where(cb.equal(root.get(ItemInventario_.uIdMercadoLibre), idMercadolibre));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la referencia a partir del id mercadolibre. ", e);
        }
        return null;
    }

    public boolean actualizarIdMercadoLibre(String itemCode, String idMercadoLibre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<ItemInventario> cu = cb.createCriteriaUpdate(ItemInventario.class);
        Root<ItemInventario> root = cu.from(ItemInventario.class);
        cu.set(ItemInventario_.uIdMercadoLibre, idMercadoLibre);
        cu.where(cb.equal(root.get(ItemInventario_.itemCode), itemCode));
        try {
            em.createQuery(cu).executeUpdate();
            return true;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al actualizar el id de mercadolibre. ", e);
            return false;
        }
    }

    public String obtenerNombreMarca(String codigo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select CAST(Name as varchar(50)) from [@BARU_MARCAS] where Code = '");
        sb.append(codigo);
        sb.append("'");
        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No fue posible consultar el nombre de la marca. ", e);
            return "Matisses";
        }
    }

    public List<Object[]> consultarSaldoItemPOS(String whsCode, List<String> itemCodes) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(q.ItemCode as varchar(20)) itemCode, cast(q.WhsCode as varchar(8)) whsCode, cast(q.OnHandQty as int) cantidad ");
        sb.append(", b.AbsEntry, cast(b.BinCode as varchar(max)) binCode from [@BARU_UBICACION_POS] up ");
        sb.append("inner join OBIN b on b.WhsCode = up.U_whsVenta and b.BinCode = CONCAT(up.U_whsVenta,up.U_binCodeVenta) ");
        sb.append("inner join OIBQ q on q.BinAbs = b.AbsEntry and q.OnHandQty > 0 and q.ItemCode IN('");
        for (String itemCode : itemCodes) {
            sb.append(itemCode);
            sb.append("','");
        }
        sb.append("') where up.U_whsOrigen = '");
        sb.append(whsCode);
        sb.append("' order by q.ItemCode, q.WhsCode, q.OnHandQty desc");

        CONSOLE.log(Level.INFO, sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo para los items enviados. ", e);
            return new ArrayList<>();
        }
    }

    public ItemInventario getItemByItemCodeWithStock(String itemCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct cast(itm.ItemCode as varchar(20)) ItemCode, cast(itm.ItemName as varchar(100)) ItemName from OITM itm ");
        sb.append("inner join OITW saldo on saldo.ItemCode = itm.ItemCode and saldo.OnHand > 0 and saldo.WhsCode like '0%'");
        sb.append("where itm.ItemCode = '");
        sb.append(itemCode);
        sb.append("'");
        Object[] datos = (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        ItemInventario entidad = new ItemInventario();
        entidad.setItemCode((String) datos[0]);
        entidad.setItemName((String) datos[1]);
        return entidad;
    }

    public List<String> obtenerItemsSimilares(String itemCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(20), itm2.ItemCode) AS ItemCode ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN OITM itm2 ON itm2.U_modelo = itm.U_modelo ");
        sb.append("INNER  JOIN OITW saldo ON saldo.ItemCode = itm2.ItemCode AND saldo.OnHand > 0 AND saldo.WhsCode LIKE '0%' ");
        sb.append("WHERE  itm.ItemCode = '");
        sb.append(itemCode);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron items similares para la referencia {0}", itemCode);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los items similares. ", e);
        }
        return null;
    }

    public List<Object[]> obtenerDatosItemPOS(String whsCode, String reference) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(i.ItemCode AS VARCHAR(20)) itemCode ");
        sb.append("       , CAST(i.ItemName AS VARCHAR(60)) itemName ");
        sb.append("       , CAST(i.U_U_Ref_Pro AS VARCHAR(50)) ref_prov ");
        sb.append("       , CAST(i.TaxCodeAR AS VARCHAR) taxCode ");
        sb.append("       , CAST(t.Rate AS INT) iva ");
        sb.append("       , CAST(p.Price AS INT) precio ");
        sb.append("       , CAST(q.WhsCode AS VARCHAR(8)) whsCode ");
        sb.append("       , CAST(q.OnHandQty AS INT) cantidad ");
        sb.append("       , b.AbsEntry ");
        sb.append("       , CAST(b.BinCode AS VARCHAR(MAX)) binCode ");
        sb.append("FROM   OITM i ");
        sb.append("INNER  JOIN ITM1 p ON p.ItemCode = i.ItemCode ");
        sb.append("INNER  JOIN OSTC t ON t.Code = i.TaxCodeAR ");
        sb.append("INNER  JOIN [@BARU_UBICACION_POS] up ON up.U_whsOrigen = '");
        sb.append(whsCode);
        sb.append("' INNER JOIN OBIN b ON b.WhsCode = up.U_whsVenta AND b.BinCode = CONCAT(up.U_whsVenta, up.U_binCodeVenta) ");
        sb.append("INNER  JOIN OIBQ q ON q.BinAbs = b.AbsEntry AND q.OnHandQty > 0 AND q.ItemCode = i.ItemCode ");
        sb.append("WHERE  i.ItemCode = '");
        sb.append(reference);
        sb.append("' AND    p.PriceList = 2 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos del item para POS.");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los datos del item para POS. ", e);
        }
        return null;
    }

    public List<Object[]> obtenerEstadoItems(String reference) {
        try {
            return em.createNativeQuery("select * from FN_ESTADO_PRODUCTOS_WEB(?)").setParameter(1, reference).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.WARNING, "No se encontro el articulo especificado. {0}", reference);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al ejecutar la consulta de estado de articulos. ", e);
        }
        return null;
    }

    public List<Object[]> llamarEstadoItems(String reference) {
        try {
            return em.createNativeQuery("{call SP_CONSULTAR_PRODUCTOS_WEB(?)}").setParameter(1, reference).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al ejecutar el procedimiento almacenado. ", e);
            return null;
        }
    }

    public List<Object[]> obtenerEmpaqueVenta(String whsCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(a.DecreasAc AS VARCHAR) acc, ");
        sb.append("       CAST(i.itemcode AS VARCHAR) itemCode, ");
        sb.append("       CAST(i.FrgnName AS VARCHAR) itemName, ");
        sb.append("       CAST(a.WhsCode AS VARCHAR) whsCode,");
        sb.append("       CAST(i.U_U_Ref_Pro AS VARCHAR(40)) refProv ");
        sb.append("FROM   OITM i ");
        sb.append("INNER  JOIN OITW s ON s.ItemCode = i.ItemCode AND s.OnHand > 0 AND s.WhsCode = CONCAT('IN', '");
        sb.append(whsCode);
        sb.append("') ");
        sb.append("INNER  JOIN OWHS a ON a.WhsCode = s.WhsCode ");
        sb.append("WHERE  i.ItmsGrpCod = '111' ");
        sb.append("AND    i.U_Grupo = '036' ");
        sb.append("ORDER  BY i.U_U_Ref_Pro ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontro material de empaque para el almacen {0}", whsCode);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Error al obtener el material de empaque. ", e);
        }
        return null;
    }

    public List<String> obternerItemSinCodeBars() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(varchar(20),s.ItemCode) AS ItemCode ");
        sb.append("FROM   OITM s ");
        sb.append("WHERE  s.CodeBars IS NULL AND LEN(s.ItemCode) = 20 AND SellItem = 'Y'");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron items para calcular codeBars");
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Error al obtener items para calcular codeBars");
        }
        return null;
    }

    public boolean validarModelo(String modelo) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR, U_modelo) Modelo ");
        sb.append("FROM   OITM ");
        sb.append("WHERE  U_modelo = '");
        sb.append(modelo);
        sb.append("' ");

        try {
            List<String> tmp = em.createNativeQuery(sb.toString()).getResultList();

            if (tmp.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al validar el modelo. ", e);
        }
        return false;
    }

    public List<String> obtenerProveedores() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR, CardCode) proveedor ");
        sb.append("FROM   OITM ");
        sb.append("WHERE  LEN(ItemCode) = 20 ");
        sb.append("AND    CardCode IS NOT NULL");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }

    public List<String> obtenerReferenciasParametros(Map<FiltroDTO, List<String>> parametros) {
        boolean ejecutar = false;
        boolean primera = true;
        List<String> referencias;
        StringBuilder sb = new StringBuilder();

        for (FiltroDTO columna : parametros.keySet()) {
            if (columna.isTipoReferencia()) {
                ejecutar = true;
                if (primera) {
                    sb.append("SELECT DISTINCT CONVERT(varchar(20),ItemCode) ItemCode ");
                    sb.append("FROM OITM ");
                    sb.append("WHERE ");
                    primera = false;
                } else {
                    sb.append("AND ");
                }

                sb.append(columna.getCodigoColumna());
                if (parametros.get(columna).size() > 1) {
                    sb.append(" IN (");
                    for (String valor : parametros.get(columna)) {
                        if (columna.getTipo().equalsIgnoreCase("String") || columna.getTipo().equalsIgnoreCase("Date")) {
                            sb.append("'");
                            sb.append(valor);
                            if (columna.getSufijo() != null) {
                                sb.append(columna.getSufijo());
                            }
                            sb.append("'");
                            sb.append(",");
                        } else if (columna.getTipo().equalsIgnoreCase("Integer")) {
                            sb.append(valor);
                            if (columna.getSufijo() != null) {
                                sb.append(columna.getSufijo());
                            }
                            sb.append(",");
                        }
                    }

                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(") ");
                } else if (columna.getTipo().equalsIgnoreCase("String") || columna.getTipo().equalsIgnoreCase("Date")) {
                    sb.append("=");
                    sb.append("'");
                    sb.append(parametros.get(columna).get(0));
                    if (columna.getSufijo() != null) {
                        sb.append(columna.getSufijo());
                    }

                    sb.append("' ");
                } else if (columna.getTipo().equalsIgnoreCase("Integer")) {
                    sb.append("=");
                    sb.append(parametros.get(columna).get(0));
                    if (columna.getSufijo() != null) {
                        sb.append(columna.getSufijo());
                    }
                }
            }
        }

        if (ejecutar) {
            try {
                referencias = (List<String>) em.createNativeQuery(sb.toString()).getResultList();
            } catch (NoResultException e) {
                return null;
            } catch (Exception e) {
                CONSOLE.log(Level.SEVERE, "Ocurrio un error al referencias por parametros. ", e);
                return null;
            }
        } else {
            return new ArrayList<>();
        }
        return referencias;
    }

    public List<String> consultarReferenciasProveedor(String proveedor) {
        List<String> referencias;
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(varchar(20),ItemCode) ItemCode ");
        sb.append("FROM OITM ");
        sb.append("WHERE ItemCode LIKE '");
        sb.append(proveedor);
        sb.append("%' AND LEN(ItemCode) = 20 ORDER BY ItemCode");

        try {
            referencias = (List<String>) em.createNativeQuery(sb.toString()).getResultList();
            CONSOLE.log(Level.INFO, "Se encontraron [0] referencias para el proveedor [1]", new Object[]{referencias.size(), proveedor});
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron referencias para el proveedor [0]", proveedor);
            return new ArrayList<String>();
        }

        return referencias;
    }
}

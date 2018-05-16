package co.matisses.persistence.sap.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleCotizacionSAP.class)
public abstract class DetalleCotizacionSAP_ {

	public static volatile SingularAttribute<DetalleCotizacionSAP, BigDecimal> quantity;
	public static volatile SingularAttribute<DetalleCotizacionSAP, BigDecimal> lineTotal;
	public static volatile SingularAttribute<DetalleCotizacionSAP, Short> slpCode;
	public static volatile SingularAttribute<DetalleCotizacionSAP, String> itemCode;
	public static volatile SingularAttribute<DetalleCotizacionSAP, Date> docDate;
	public static volatile SingularAttribute<DetalleCotizacionSAP, Date> shipDate;
	public static volatile SingularAttribute<DetalleCotizacionSAP, String> taxCode;
	public static volatile SingularAttribute<DetalleCotizacionSAP, String> dscription;
	public static volatile SingularAttribute<DetalleCotizacionSAP, Character> lineStatus;
	public static volatile SingularAttribute<DetalleCotizacionSAP, DetalleCotizacionSAPPK> detalleCotizacionSAPPK;
	public static volatile SingularAttribute<DetalleCotizacionSAP, BigDecimal> price;
	public static volatile SingularAttribute<DetalleCotizacionSAP, BigDecimal> openQty;
	public static volatile SingularAttribute<DetalleCotizacionSAP, String> currency;
	public static volatile SingularAttribute<DetalleCotizacionSAP, String> whsCode;

}


package co.matisses.persistence.sap.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrecioVentaItem.class)
public abstract class PrecioVentaItem_ {

	public static volatile SingularAttribute<PrecioVentaItem, BigDecimal> price;
	public static volatile SingularAttribute<PrecioVentaItem, Character> ovrwritten;
	public static volatile SingularAttribute<PrecioVentaItem, PrecioVentaItemPK> precioVentaItemPK;
	public static volatile SingularAttribute<PrecioVentaItem, String> currency;
	public static volatile SingularAttribute<PrecioVentaItem, Integer> logInstanc;
	public static volatile SingularAttribute<PrecioVentaItem, BigDecimal> factor;
	public static volatile SingularAttribute<PrecioVentaItem, String> objType;

}


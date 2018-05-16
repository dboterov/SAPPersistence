package co.matisses.persistence.sap.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleOrdenVenta.class)
public abstract class DetalleOrdenVenta_ {

	public static volatile SingularAttribute<DetalleOrdenVenta, Character> lineStatus;
	public static volatile SingularAttribute<DetalleOrdenVenta, Integer> docEntry;
	public static volatile SingularAttribute<DetalleOrdenVenta, BigDecimal> quantity;
	public static volatile SingularAttribute<DetalleOrdenVenta, String> itemCode;
	public static volatile SingularAttribute<DetalleOrdenVenta, Integer> lineNum;
	public static volatile SingularAttribute<DetalleOrdenVenta, String> dscription;

}


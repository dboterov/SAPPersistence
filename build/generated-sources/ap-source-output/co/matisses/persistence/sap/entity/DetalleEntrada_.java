package co.matisses.persistence.sap.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleEntrada.class)
public abstract class DetalleEntrada_ {

	public static volatile SingularAttribute<DetalleEntrada, Integer> docEntry;
	public static volatile SingularAttribute<DetalleEntrada, BigDecimal> quantity;
	public static volatile SingularAttribute<DetalleEntrada, String> itemCode;
	public static volatile SingularAttribute<DetalleEntrada, Integer> lineNum;
	public static volatile SingularAttribute<DetalleEntrada, String> dscription;

}


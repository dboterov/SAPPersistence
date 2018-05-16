package co.matisses.persistence.sap.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SaldoUbicacion.class)
public abstract class SaldoUbicacion_ {

	public static volatile SingularAttribute<SaldoUbicacion, Integer> absEntry;
	public static volatile SingularAttribute<SaldoUbicacion, UbicacionSAP> ubicacion;
	public static volatile SingularAttribute<SaldoUbicacion, String> itemCode;
	public static volatile SingularAttribute<SaldoUbicacion, BigDecimal> onHandQty;
	public static volatile SingularAttribute<SaldoUbicacion, String> whsCode;
	public static volatile SingularAttribute<SaldoUbicacion, Character> freezed;
	public static volatile SingularAttribute<SaldoUbicacion, Integer> freezeDoc;

}


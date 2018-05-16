package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmpleoAnterior.class)
public abstract class EmpleoAnterior_ {

	public static volatile SingularAttribute<EmpleoAnterior, Date> fromDate;
	public static volatile SingularAttribute<EmpleoAnterior, EmpleoAnteriorPK> empleoAnteriorPK;
	public static volatile SingularAttribute<EmpleoAnterior, Date> toDate;
	public static volatile SingularAttribute<EmpleoAnterior, String> employer;
	public static volatile SingularAttribute<EmpleoAnterior, String> position;
	public static volatile SingularAttribute<EmpleoAnterior, Integer> logInstanc;
	public static volatile SingularAttribute<EmpleoAnterior, String> remarks;

}


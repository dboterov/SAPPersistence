package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Formacion.class)
public abstract class Formacion_ {

	public static volatile SingularAttribute<Formacion, Date> fromDate;
	public static volatile SingularAttribute<Formacion, String> major;
	public static volatile SingularAttribute<Formacion, FormacionPK> formacionPK;
	public static volatile SingularAttribute<Formacion, Date> toDate;
	public static volatile SingularAttribute<Formacion, String> institute;
	public static volatile SingularAttribute<Formacion, String> diploma;
	public static volatile SingularAttribute<Formacion, Integer> logInstanc;
	public static volatile SingularAttribute<Formacion, Integer> type;

}


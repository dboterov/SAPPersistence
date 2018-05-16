package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Valoracion.class)
public abstract class Valoracion_ {

	public static volatile SingularAttribute<Valoracion, Date> date;
	public static volatile SingularAttribute<Valoracion, ValoracionPK> valoracionPK;
	public static volatile SingularAttribute<Valoracion, Integer> manager;
	public static volatile SingularAttribute<Valoracion, String> grade;
	public static volatile SingularAttribute<Valoracion, String> reviewDesc;
	public static volatile SingularAttribute<Valoracion, Integer> logInstanc;
	public static volatile SingularAttribute<Valoracion, String> remarks;

}


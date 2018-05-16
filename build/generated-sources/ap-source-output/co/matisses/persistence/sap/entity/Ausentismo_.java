package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ausentismo.class)
public abstract class Ausentismo_ {

	public static volatile SingularAttribute<Ausentismo, Date> fromDate;
	public static volatile SingularAttribute<Ausentismo, String> reason;
	public static volatile SingularAttribute<Ausentismo, Integer> uminutos;
	public static volatile SingularAttribute<Ausentismo, Integer> udias;
	public static volatile SingularAttribute<Ausentismo, Date> toDate;
	public static volatile SingularAttribute<Ausentismo, AusentismoPK> ausentismoPK;
	public static volatile SingularAttribute<Ausentismo, String> approvedBy;
	public static volatile SingularAttribute<Ausentismo, String> utype;
	public static volatile SingularAttribute<Ausentismo, Integer> cnfrmrNum;
	public static volatile SingularAttribute<Ausentismo, Integer> logInstanc;
	public static volatile SingularAttribute<Ausentismo, Integer> uhoras;

}


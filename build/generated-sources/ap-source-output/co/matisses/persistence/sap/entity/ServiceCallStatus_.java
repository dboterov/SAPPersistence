package co.matisses.persistence.sap.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceCallStatus.class)
public abstract class ServiceCallStatus_ {

	public static volatile SingularAttribute<ServiceCallStatus, Integer> statusId;
	public static volatile SingularAttribute<ServiceCallStatus, String> name;
	public static volatile SingularAttribute<ServiceCallStatus, String> description;
	public static volatile SingularAttribute<ServiceCallStatus, Character> locked;

}


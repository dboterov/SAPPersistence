package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrasladosSAP.class)
public abstract class TrasladosSAP_ {

	public static volatile SingularAttribute<TrasladosSAP, Integer> docNum;
	public static volatile SingularAttribute<TrasladosSAP, Integer> docEntry;
	public static volatile SingularAttribute<TrasladosSAP, Character> docStatus;
	public static volatile SingularAttribute<TrasladosSAP, String> cardName;
	public static volatile SingularAttribute<TrasladosSAP, Date> docDueDate;
	public static volatile SingularAttribute<TrasladosSAP, Character> docType;
	public static volatile SingularAttribute<TrasladosSAP, Short> slpCode;
	public static volatile SingularAttribute<TrasladosSAP, Short> series;
	public static volatile SingularAttribute<TrasladosSAP, String> cardCode;
	public static volatile SingularAttribute<TrasladosSAP, Date> docDate;
	public static volatile SingularAttribute<TrasladosSAP, String> objType;
	public static volatile SingularAttribute<TrasladosSAP, String> numAtCard;

}


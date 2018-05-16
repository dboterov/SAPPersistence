package co.matisses.persistence.sap.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DocumentosExcluidos.class)
public abstract class DocumentosExcluidos_ {

	public static volatile SingularAttribute<DocumentosExcluidos, String> docNum;
	public static volatile SingularAttribute<DocumentosExcluidos, String> tipoDocumento;
	public static volatile SingularAttribute<DocumentosExcluidos, String> code;
	public static volatile SingularAttribute<DocumentosExcluidos, String> usuarioExcluye;
	public static volatile SingularAttribute<DocumentosExcluidos, String> name;
	public static volatile SingularAttribute<DocumentosExcluidos, Date> fechaExclusion;

}


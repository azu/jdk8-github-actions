package se.nrm.taxon.eclipselinkeexample;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import se.nrm.taxon.eclipselinkeexample.Image;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-01-15T12:59:03")
@StaticMetamodel(Licence.class)
public class Licence_ { 

    public static volatile SetAttribute<Licence, Image> images;
    public static volatile SingularAttribute<Licence, String> name;
    public static volatile SingularAttribute<Licence, Integer> id;
    public static volatile SingularAttribute<Licence, String> abbrev;
    public static volatile SingularAttribute<Licence, String> uri;
    public static volatile SingularAttribute<Licence, String> issuer;

}
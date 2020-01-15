package se.nrm.taxon.eclipselinkeexample;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import se.nrm.taxon.eclipselinkeexample.Licence;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-01-15T13:12:56")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SetAttribute<Image, Licence> licenses;
    public static volatile SingularAttribute<Image, Long> imageId;
    public static volatile SingularAttribute<Image, String> copyrightOwner;
    public static volatile SingularAttribute<Image, String> title;

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.taxon.eclipselinkeexample;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingimar
 */
@Entity
@Table(name = "licence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Licence.FIND_ALL, query = "SELECT l FROM Licence l"),
    @NamedQuery(name = "Licence.findById", query = "SELECT l FROM Licence l WHERE l.id = :id"),
    @NamedQuery(name = Licence.LICENCE_BY_ABBREV, query = "SELECT l FROM Licence l WHERE l.abbrev = :abbrev"),
    @NamedQuery(name = "Licence.findByIssuer", query = "SELECT l FROM Licence l WHERE l.issuer = :issuer"),
    @NamedQuery(name = "Licence.findByUri", query = "SELECT l FROM Licence l WHERE l.uri = :uri"),
    @NamedQuery(name = "Licence.findByName", query = "SELECT l FROM Licence l WHERE l.name = :name")})
public class Licence implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String LICENCE_BY_ABBREV = "Licence.findByAbbrev";

    public static final String FIND_ALL = "Licence.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LICENCE_ID")
    private Integer id;

    @Column(name = "abbrev")
    private String abbrev;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "uri")
    private String uri;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "licenses")
    private Set<Image> images = new HashSet<>();

    public Licence() {
    }

    public Licence(Integer id) {
        this.id = id;
    }

    public Licence(String abbrev) {
        this.abbrev = abbrev;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licence)) {
            return false;
        }
        Licence other = (Licence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ id=" + id + " ]" + "[ abbrev=" + abbrev + " ]" + "[ nr of images :=" + images.size() + " ]";
    }

}

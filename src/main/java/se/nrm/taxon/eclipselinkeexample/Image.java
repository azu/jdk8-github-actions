/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.taxon.eclipselinkeexample;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "IMAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Image.FIND_ALL, query = "SELECT i FROM Image i"),
    @NamedQuery(name = Image.FIND_BY_TITLE, query = "SELECT i FROM Image i WHERE i.title = :title")
})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_TITLE = "Image.findByTitle";

    public static final String FIND_ALL = "Image.findAll";

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue
    private Long imageId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "COPYRIGHT_OWNER")
    private String copyrightOwner;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "IMAGE_X_LICENSE",
            joinColumns = {
                @JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "LICENCE_ID")})
    private Set<Licence> licenses = new HashSet<>();

    public Image() {
    }

    public Image(String title, String copyrightOwner) {
        this.title = title;
        this.copyrightOwner = copyrightOwner;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyrightOwner() {
        return copyrightOwner;
    }

    public void setCopyrightOwner(String copyrightOwner) {
        this.copyrightOwner = copyrightOwner;
    }

    public Set<Licence> getLicenses() {
        return licenses;
    }

    public void setLicenses(Set<Licence> licenses) {
        this.licenses = licenses;
    }


     @Override
    public String toString() {
        return "[ id=" + this.imageId + " ]" + "[ title=" + this.title + " ]" 
                + "[ nr of licences := " + this.licenses.size() + " ]";
    }
}

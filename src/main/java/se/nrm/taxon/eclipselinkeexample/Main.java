/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.taxon.eclipselinkeexample;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * http://viralpatel.net/blogs/hibernate-many-to-many-annotation-mapping-tutorial/
 *
 * @author ingimar
 */
public class Main {

    private static final String PERSISTENCE_UNIT_NAME = "todos";

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
//        createDummyImages(em);
//        showImages(em);
//        showLicenses(em);
//        Licence license = getLicense(em, "CC BY-SA");
//        System.out.println("licence is "+license);
//        createDummyImages(em);
//        showEmp(em);
        Image image = getImage(em, "citronfjäril");
        System.out.println(image);
        
        boolean linkImageWithLicence = linkImageWithLicence(em, "CC BY-SA", "citronfjäril");
        em.getTransaction().commit();
        em.close();
    }

    private static void createTodo(EntityManager em) {
        Todo todo = new Todo();
        todo.setSummary("This is a test");
        todo.setDescription("This is a test");
        em.persist(todo);
    }

    private static void showTodo(EntityManager em) {
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todoList = q.getResultList();
        for (Todo todo : todoList) {
            System.out.println(todo);
        }
        
        System.out.println("Size: " + todoList.size());
    }

    private static void createDummyImages(EntityManager em) {
        try {
            Image citron = new Image("Citronfjäril".toLowerCase(), "Erlingsson".toLowerCase());
            Image sorg = new Image("Sorgmantel".toLowerCase(), "Karlsson".toLowerCase());

            em.persist(citron);
            em.persist(sorg);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static boolean linkImageWithLicence(EntityManager em, String licenseAbbr, String imageTitle) {
        Licence license = getLicense(em, licenseAbbr);
        Image image = getImage(em, imageTitle);
        image.getMeetings().add(license);
        em.merge(image);
        return true;
    }

    private static Licence getLicense(EntityManager em, String abbrevation) {
        Query namedQuery = em.createNamedQuery(Licence.LICENCE_BY_ABBREV);
        namedQuery.setParameter("abbrev", abbrevation);
        Licence licence = (Licence) namedQuery.getSingleResult();
        return licence;
    }

    private static Image getImage(EntityManager em, String title) {
        Query namedQuery = em.createNamedQuery(Image.FIND_BY_TITLE);
        namedQuery.setParameter("title", title.trim());
        Image image = (Image) namedQuery.getSingleResult();
        return image;
    }

    private static void showLicenses(EntityManager em) {
        Query q = em.createNamedQuery(Licence.FIND_ALL);
        List<Licence> todoList = q.getResultList();
        for (Licence lic : todoList) {
            System.out.println(lic);
        }
    }

    private static void showImages(EntityManager em) {
        Query q = em.createNamedQuery(Image.FIND_ALL);
        List<Image> list = q.getResultList();
        for (Image image : list) {
            System.out.println(image.getTitle());
            Set<Licence> lic = image.getMeetings();
            for (Licence meeting : lic) {
                System.out.println("--" + meeting.getAbbrev());
            }
        }
    }

    private static EntityManager getEntityManager() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        return em;
    }
}

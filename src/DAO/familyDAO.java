/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.JPAutil;
import java.util.List;
import javax.persistence.EntityManager;
import models.family;

/**
 *
 * @author islam-bilisim
 */
public class familyDAO {

    public boolean addfam(family f) {
        if (isNationalIdExits(f.getNational_id())) {
            System.out.println("Error: this Id is already taken");
            return false;
        }
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    private boolean isNationalIdExits(String national_id) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            Long count = em.createQuery("select count(f) from family f where f.national_id = :nid", Long.class)
                    .setParameter("nid", national_id)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public boolean updateFam(family fam) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.merge(fam);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deletefam(family fam) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            family f = em.merge(fam);
            em.remove(f);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<family> findAll() {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select f from family f", family.class).getResultList();
        } finally {
            em.close();
        }
    }

    public family findById(int family_id) {

        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            family f = em.find(family.class, family_id);
            return f;
        } finally {
            em.close();
        }
    }

    public List<family> vulnerability() {
        EntityManager em = null;

        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select f from family order by case f.vulnerability_level when 'High' then 1 "
                    + "when 'medium' then 2 when 'low' then 3 end", family.class)
                    .getResultList();

        } finally {
            em.close();
        }

    }

    public List<family> neverserved() {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select f from family f where f.last_aid_date is null", family.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}

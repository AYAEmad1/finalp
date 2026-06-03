/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.JPAutil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.organization;

/**
 *
 * @author islam-bilisim
 */
public class organizationDAO {

    public boolean addorg(organization org) {
        EntityManager em = null;

        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.persist(org);
            em.getTransaction().commit();
            return true;
        } catch (Exception c) {
            return false;
        } finally {
            em.close();
        }

    }

    public boolean updateorg(organization org) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.merge(org);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteoreg(organization org) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            organization mo = em.merge(org);
            em.remove(mo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<organization> findAll() {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select org from organization org", organization.class).getResultList();
        } finally {
            em.close();
        }
    }

}

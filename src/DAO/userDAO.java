/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.JPAutil;
import config.DBConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import models.user;

/**
 *
 * @author islam-bilisim
 */
public class userDAO {


    public static boolean adduser(user u) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }

    }

    public static boolean updateuser(user u) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<user> findAll() {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select u from user u where u.role = 'coordinator'", user.class)
                    .getResultList();
        } finally {
            em.close();
        }

    }

    public static user login(String name, String password) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            return em.createQuery("select u from user u where u.name = :user and u.password= :pass", user.class)
                    .setParameter("user", name)
                    .setParameter("pass", password)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean deleteuser(user u) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            em.getTransaction().begin();
            user ur = em.merge(u);
            em.remove(ur);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public boolean isExist(String name, String email) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            long count = em.createQuery("select count(u) from user u where u.user_name = :user or u.email = :email", long.class)
                    .setParameter("user_name", name)
                    .setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

}

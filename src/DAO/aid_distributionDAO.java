/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JPA.JPAutil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.aidDistribution;

import models.family;

/**
 *
 * @author islam-bilisim
 */
public class aid_distributionDAO {

    public boolean addAid(aidDistribution aid) {

        family fam = aid.getFamily();

        if (fam != null
                && (fam.getVulnerability_level().equalsIgnoreCase("Medium")
                || fam.getVulnerability_level().equalsIgnoreCase("Low"))) {

            if (Checkdups(fam.getFamily_id(), aid.getAid_type(), aid.getDistribution_date())) {
                System.out.println("Rejected!");
                return false;
            }

            EntityManager em = null;
            try {
                em = JPAutil.getEntityManager();
                em.getTransaction().begin();

                em.persist(aid);

                if (fam != null) {
                    family managedFamily = em.find(family.class, fam.getFamily_id());
                    managedFamily.setLast_aid_date(aid.getDistribution_date());
                    em.merge(managedFamily);
                }

                em.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                ex.printStackTrace();
                return false;
            } finally {
                if (em != null && em.isOpen()) {
                    em.close();
                }
            }
        
  
    
    
    }
    }
    
    public List<aidDistribution> findAll() {
        EntityManager em = JPAutil.getEntityManager();
        try {

            return em.createQuery("select d from aid_distribution d", aidDistribution.class)
                    .getResultList();

        } finally {
            em.close();
        }

    }

    public List<aidDistribution> findbyorg(int org_id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            TypedQuery<aidDistribution> query = em.createQuery("select d from aid_distribution d where d.org_id = :oid order by "
                    + "d.distribution_date desc", aidDistribution.class);
            query.setParameter("oid", org_id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public String upadateaid(aidDistribution aid) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aid);
            family fam = em.find(family.class, aid.getFamily().getFamily_id());
            if (fam != null) {
                fam.setLast_aid_date(aid.getDistribution_date());
                em.merge(fam);
            }
            em.getTransaction().commit();
            return "SUCCESS";

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
            return "ERROR: " + ex.getMessage();
        } finally {
            em.close();
        }
    }

    public String deletedist(aidDistribution aid) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            aidDistribution d = em.merge(aid);
            em.remove(d);
            em.getTransaction().commit();
            return "SUCCESS";
        } catch (Exception ex) {
            return "FAILED";
        } finally {
            em.close();
        }
    }

    public List<aidDistribution> getdistbyfamily(int family_id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            TypedQuery<aidDistribution> query = em.createQuery("select d from aid_distribution d where d.family.family_id= :fid",
                     aidDistribution.class);
            query.setParameter("fid", family_id);
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public List<aidDistribution> findbycoor(int user_id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            TypedQuery<aidDistribution> query = em.createQuery(
                    "select d from aid_distribution d where d.distributed_by.user_id = :uid", aidDistribution.class);
            query.setParameter("uid", user_id);
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public List<aidDistribution> searchbyType(String aid_type) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            TypedQuery<aidDistribution> query = em.createQuery(
                    "select d from aid_distribution d where d.type = :atype", aidDistribution.class);
            query.setParameter("atype", aid_type);
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public boolean Checkdups(int family_id, String aid_type, LocalDate distribution_Date) {
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            LocalDate thirty = distribution_Date.minusDays(30);
            String query = "select count(ad) from aidDistribution ad where ad.family.family_id = :fmily_id and ad.aid_type = :aid_type and "
                    + " ad.distribution_date between :startDate and :endDate";

            Long count = em.createQuery(query, Long.class)
                    .setParameter("family_id", family_id)
                    .setParameter("aid_type", aid_type)
                    .setParameter("startDate", thirty)
                    .setParameter("endDate", distribution_Date)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

}

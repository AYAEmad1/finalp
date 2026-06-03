/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author islam-bilisim
 */
public class JPAutil {
     private static EntityManagerFactory emf;
    
    private JPAutil(){}
    
    
    private static EntityManagerFactory getEMF(){
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("GHADSPU");
        return emf;
    }
    
    public static EntityManager getEntityManager(){
        return getEMF().createEntityManager();
    }
    
    public static void closeEMF(){
        if(emf != null)
            emf.close();
    }
    
    
    

}

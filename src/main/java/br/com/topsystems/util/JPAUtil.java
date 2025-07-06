package br.com.topsystems.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory;
    
    static {
        try {
            factory = Persistence.createEntityManagerFactory("produtos-pu");
        } catch (Exception e) {
            System.err.println("Erro ao criar EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    
    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }
}

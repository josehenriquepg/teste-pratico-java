package br.com.topsystems.dao;

import br.com.topsystems.entity.Product;
import br.com.topsystems.util.JPAUtil;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class ProductDAO {
	
	public void save(Product product) {
		EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (product.getId() == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar produto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
	}
	
	public void delete(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            product = em.find(Product.class, product.getId());
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
	
	public Product searchById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }
	
	public List<Product> listAllProducts() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.description", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
	
	public List<Product> searchByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE UPPER(p.description) LIKE UPPER(:description) ORDER BY p.description", Product.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
	
	public List<Product> searchByCode(String code) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productCode = :productCode ORDER BY p.description", Product.class);
            query.setParameter("codigo", code);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
	
	public List<Product> searchWithFilters(String name, String code) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            StringBuilder jpql = new StringBuilder("SELECT p FROM Product p WHERE 1=1");
            
            if (name != null && !name.trim().isEmpty()) {
                jpql.append(" AND UPPER(p.description) LIKE UPPER(:description)");
            }
            
            if (code != null && !code.trim().isEmpty()) {
                jpql.append(" AND p.productCode = :productCode");
            }
            
            jpql.append(" ORDER BY p.description");
            
            TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);
            
            if (name != null && !name.trim().isEmpty()) {
                query.setParameter("nome", "%" + name + "%");
            }
            
            if (code != null && !code.trim().isEmpty()) {
                query.setParameter("codigo", code);
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
	
	public List<String> listByCode() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery("SELECT DISTINCT p.productCode FROM Product p ORDER BY p.productCode", String.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

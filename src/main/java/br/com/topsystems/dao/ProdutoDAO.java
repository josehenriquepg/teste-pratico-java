package br.com.topsystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.topsystems.entity.Produto;
import br.com.topsystems.util.HibernateUtil;

public class ProdutoDAO {

	private EntityManagerFactory emf;

	public ProdutoDAO() {
		this.emf = Persistence.createEntityManagerFactory("produtos-pu");
	}

	public void salvar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null)
				transacao.rollback();
			throw ex;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Produto p ORDER BY p.descricao");
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> pesquisarDescricao(String descricao) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = (Query) em.createQuery(
					"SELECT p FROM Produto p WHERE UPPER(p.descricao) LIKE UPPER(:descricao) ORDER BY p.descricao");
			query.setParameter("descricao", "%" + descricao + "%");
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao pesquisar produtos: " + e.getMessage(), e);
		} finally {
			em.close();
		}
	}

	public void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null)
				transacao.rollback();
			throw ex;
		} finally {
			sessao.close();
		}
	}

	public void editar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(produto);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null)
				transacao.rollback();
			throw ex;
		} finally {
			sessao.close();
		}
	}
}

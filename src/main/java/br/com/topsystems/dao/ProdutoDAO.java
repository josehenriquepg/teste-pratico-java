package br.com.topsystems.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.com.topsystems.entity.Produto;
import br.com.topsystems.util.HibernateUtil;

public class ProdutoDAO {
	
	public void salvar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) transacao.rollback();
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		
		try {
			Query consulta = sessao.getNamedQuery("Produto.listar");
			produtos = consulta.list();			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return produtos;
	}
	
	public Produto buscar(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;
		
		try {
			Query consulta = sessao.getNamedQuery("Produto.buscarPorID");
			consulta.setParameter("id", id);
			produto = (Produto) consulta.uniqueResult();			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return produto;
	}
	
	public void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) transacao.rollback();
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
			if (transacao != null) transacao.rollback();
			throw ex;
		} finally {
			sessao.close();
		}
	}
}

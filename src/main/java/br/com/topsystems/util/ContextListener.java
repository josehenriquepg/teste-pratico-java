package br.com.topsystems.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	// Quando Finaliza o TomCat
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
	}

	// Quando Inicia o TomCat
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Vai Perceber que o Hibernate não Foi inicializado e criará uma Sessão.
		HibernateUtil.getSessionFactory().openSession();
	}

}
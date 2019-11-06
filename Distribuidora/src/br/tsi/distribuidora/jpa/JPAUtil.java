package br.tsi.distribuidora.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-distribuidora");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}

package br.tsi.distribuidora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.tsi.distribuidora.jpa.JPAUtil;

public class DAO<T> {
	private final  Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void adiciona(T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void remove(T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualiza(T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	public T BuscaPorID (Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		T t = (T) em.find(classe, id);
		em.close();
		return t;
	}
	
	public List<T> listaTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		//Define query para recuperar todas as informações de uma tabela no banco.
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}
}

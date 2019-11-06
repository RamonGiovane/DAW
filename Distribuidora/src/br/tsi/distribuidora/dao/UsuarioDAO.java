package br.tsi.distribuidora.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.tsi.distribuidora.jpa.JPAUtil;
import br.tsi.distribuidora.modelo.Usuario;

public class UsuarioDAO {
	public boolean existeUsuario(Usuario usuario) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("from Usuario u where u.nome = :pNome and u.senha = :pSenha");
		
		query.setParameter("pNome", usuario.getNome());
		query.setParameter("pSenha", usuario.getSenha());
		
		boolean encontrado = !query.getResultList().isEmpty();
		manager.getTransaction().commit();
		return encontrado;
	}
}

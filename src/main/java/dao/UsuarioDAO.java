package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Usuario;
import util.JPAUtil;

public class UsuarioDAO {

	public static void salvar(Usuario user) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletar(Usuario user) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		user = em.find(Usuario.class, user.getId());
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Usuario> listarTodos() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogada j");
		List<Usuario> lista = q.getResultList();
		em.close();
		return lista;
	}	
	
	public static Usuario login(String login) {
		Usuario user = null;
		
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
		q.setParameter("login", login);
		user = (Usuario) q.getSingleResult();
		em.close();
		
		return user;
    }
}

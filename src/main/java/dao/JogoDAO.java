package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Jogo;
import util.JPAUtil;

public class JogoDAO {

	public static void salvar(Jogo jg) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jg);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletar(Jogo jg) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jg = em.find(Jogo.class, jg.getId());
		em.remove(jg);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Jogo> listarTodos() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j");
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}	
	
	public static List<Jogo> buscarPorTime(String time) {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createNamedQuery("buscarPorTime");
		q.setParameter("time", time);
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}
}

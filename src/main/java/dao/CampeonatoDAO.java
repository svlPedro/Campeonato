package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Campeonato;
import util.JPAUtil;

public class CampeonatoDAO {

	public static void salvar(Campeonato camp) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(camp);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletar(Campeonato camp) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		camp = em.find(Campeonato.class, camp.getId());
		em.remove(camp);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Campeonato> listarTodos() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select c from Campeonato c");
		List<Campeonato> lista = q.getResultList();
		em.close();
		return lista;
	}	
	
	public static Campeonato buscarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Campeonato campeonato = em.find(Campeonato.class, id);
		em.close();
		return campeonato;
	}
}

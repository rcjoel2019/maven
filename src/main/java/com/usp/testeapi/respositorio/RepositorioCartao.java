package com.usp.testeapi.respositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.usp.testeapi.entidade.Cartao;

public class RepositorioCartao {

	EntityManagerFactory emf;
	EntityManager em;
	
	public RepositorioCartao(){
		//emf = Persistence.createEntityManagerFactory("mohr");
		//em = emf.createEntityManager();
	}
	
	public void salvar(Cartao c) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		//em.persist(c); simplesmente cria um objeto novo, independente da situa��o
		em.merge(c);//se j� existe, atualiza
		em.getTransaction().commit();
		emf.close();
	}
	
	public Cartao obterPorId(int id) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Cartao cartao = em.find(Cartao.class, id);
		em.getTransaction().commit();
		emf.close();
		return cartao;
	}
	
	public void remover(Cartao c) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(c)) {
			em.remove(c);
		}else {
			Cartao cc = em.getReference(c.getClass(), c.getId());
			em.remove(cc);
		}
		em.getTransaction().commit();
		emf.close();
	}
	@SuppressWarnings("unchecked")
	public List<Cartao> listarTodos(){
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query consulta = em.createQuery("select cartao from Cartao cartao");
		List<Cartao> cartoes = consulta.getResultList();
		em.getTransaction().commit();
		emf.close();
		return cartoes;
	}
}

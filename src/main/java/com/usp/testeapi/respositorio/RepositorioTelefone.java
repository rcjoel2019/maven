package com.usp.testeapi.respositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.usp.testeapi.entidade.Telefone;

public class RepositorioTelefone {
	EntityManagerFactory emf;
	EntityManager em;
	
	public RepositorioTelefone(){
		//emf = Persistence.createEntityManagerFactory("mohr");
		//em = emf.createEntityManager();
	}
	
	public void salvar(Telefone f) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		//em.persist(c); simplesmente cria um objeto novo, independente da situa��o
		em.merge(f);//se j� existe, atualiza
		em.getTransaction().commit();
		emf.close();
	}
	
	public Telefone obterPorId(int id) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Telefone telefone = em.find(Telefone.class, id);
		em.getTransaction().commit();
		emf.close();
		return telefone;
	}
	
	public void remover(Telefone f) {
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(f)) {
			em.remove(f);
		}else {
			Telefone ff = em.getReference(f.getClass(), f.getId());
			em.remove(ff);
		}
		em.getTransaction().commit();
		emf.close();
	}
	@SuppressWarnings("unchecked")
	public List<Telefone> listarTodos(){
		emf = Persistence.createEntityManagerFactory("mohr");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query consulta = em.createQuery("select t from Telefone t");
		List<Telefone> telefones = consulta.getResultList();
		em.getTransaction().commit();
		emf.close();
		return telefones;
	}
}

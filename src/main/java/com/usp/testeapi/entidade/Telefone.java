package com.usp.testeapi.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="telefones")
public class Telefone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String numero;
	private String ddd;
	
	@SuppressWarnings("deprecation")
	
	@ManyToOne
	@JoinColumn(name="cartao", nullable=false)
	@ForeignKey(name="cartao_fk")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Cartao cartao = new Cartao();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getCartao() {
		return cartao.toString();
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", ddd=" + ddd + ", cartao=" + cartao.toString() + "]";
	}
	
	
	
}

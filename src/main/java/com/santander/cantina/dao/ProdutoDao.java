package com.santander.cantina.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.santander.cantina.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void salvar(Produto produto) {
		em.persist(produto);
	}

	public void atualizar(Produto produto) {
		em.merge(produto);
	}

	public void excluir(Produto produto) {
		em.remove(produto);
	}

	public List<Produto> buscaProdutoPorNome(String nome) {
		return em.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE :nome", Produto.class).setParameter("nome", "%" + nome + "%").getResultList();
	}
}

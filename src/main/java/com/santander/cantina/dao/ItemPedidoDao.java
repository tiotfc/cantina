package com.santander.cantina.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.santander.cantina.modelo.ItemPedido;
import com.santander.cantina.modelo.TopItemPedido;

public class ItemPedidoDao {

	private EntityManager em;

	public ItemPedidoDao(EntityManager em) {
		this.em = em;
	}

	public void salvar(ItemPedido itemPedido) {
		em.persist(itemPedido);
	}

	public void atualizar(ItemPedido itemPedido) {
		em.merge(itemPedido);
	}

	public void excluir(ItemPedido itemPedido) {
		em.remove(itemPedido);
	}

	public List<TopItemPedido> listaProdutosMaisVendidos() {
		String jpql = "select new com.santander.cantina.modelo.TopItemPedido(p.nome, count(p.nome) * ip.quantidade as total_pedido, p.preco)"
				+ " from ItemPedido ip, Produto p" + " where p.id = ip.produto" + " group by p.nome, ip.valor"
				+ " order by total_pedido desc";

		return em.createQuery(jpql, TopItemPedido.class).getResultList();
	}
	
	public TopItemPedido produtoMaisVendido(int quantidadeRetorno) {
		String jpql = "select new com.santander.cantina.modelo.TopItemPedido(p.nome, count(p.nome) * ip.quantidade as total_pedido, p.preco)"
				+ " from ItemPedido ip, Produto p" + " where p.id = ip.produto" + " group by p.nome, ip.valor"
				+ " order by total_pedido desc";

		return em.createQuery(jpql, TopItemPedido.class).setMaxResults(quantidadeRetorno).getSingleResult();
	}
}

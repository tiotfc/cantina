package com.santander.cantina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.santander.cantina.modelo.Cliente;
import com.santander.cantina.modelo.TotalCompraCliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void salvar(Cliente cliente) {
		em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		em.merge(cliente);
	}

	public void excluir(Cliente cliente) {
		em.remove(cliente);
	}

	public List<Cliente> listarTodas() {
		return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}

	public List<TotalCompraCliente> totalDespesaPorMesAno() {
		String jpql = "SELECT new com.santander.cantina.modelo.TotalCompraCliente(c.nome,sum(p.valorTotal),c.cpf) "
				+ "FROM Cliente c, Pedido p " + "WHERE p.cliente = c.id "
				+ "GROUP BY p.cliente.id, MONTH(p.dataCriacao), YEAR(p.dataCriacao) " + "ORDER BY p.dataCriacao";
		return em.createQuery(jpql, TotalCompraCliente.class).getResultList();
	}

	public List<TotalCompraCliente> totalDespesaCliente() {
		String jpql = "SELECT new com.santander.cantina.modelo.TotalCompraCliente(c.nome,sum(p.valorTotal),c.cpf) "
				+ "FROM Pedido p JOIN p.cliente c GROUP BY p.cliente ORDER BY c.nome";
		return em.createQuery(jpql, TotalCompraCliente.class).getResultList();
	}

	public List<Cliente> buscarClientes(String nome, String cpf) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
		Root<Cliente> root = cq.from(Cliente.class);
		Predicate[] predicate = new Predicate[2];
		predicate[0] = cb.equal(root.get("nome"), nome);
		predicate[1] = cb.like(root.get("cpf"), "%" + cpf + "%");
		cq.select(root).where(predicate);
		return em.createQuery(cq).getResultList();
	}

}

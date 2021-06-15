package com.santander.cantina.modelo;

import java.math.BigDecimal;

public class TopItemPedido {

	private String nome;
	private Long quantidade;
	private BigDecimal preco;
	
	public TopItemPedido(String nome, Long quantidade, BigDecimal preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	
	public String getNome() {
		return nome;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	
}

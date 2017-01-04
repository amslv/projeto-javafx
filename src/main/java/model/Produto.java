package model;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
public class Produto {

	private String id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double valorCompra;
	private Double valorVenda;
	
	public Produto() {}
	
	public Produto(String nome, String descricao, Integer quantidade, Double valorCompra, Double valorVenda) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	@Override
	public String toString() {
		return this.nome;
	}
}

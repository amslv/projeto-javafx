package model;

import java.util.ArrayList;
import java.util.List;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
public class Fornecedor {

	private String id;
	private String nomeFantasia;
	private String cnpj;
	private String email;
	private Endereco endereco;
	private List<Produto> produtosFornecidos;
	
	public Fornecedor() {
		this.produtosFornecidos = new ArrayList<>();
		this.endereco = new Endereco();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Produto> getProdutosFornecidos() {
		return produtosFornecidos;
	}
	public void setProdutosFornecidos(List<Produto> produtosFornecidos) {
		this.produtosFornecidos = produtosFornecidos;
	}
}

package dao;

import model.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {

	public void update(Produto produto) {
		Produto oldProd = session.load(Produto.class, produto.getId());
		oldProd.setNome(produto.getNome());
		oldProd.setDescricao(produto.getDescricao());
		oldProd.setQuantidade(produto.getQuantidade());
		oldProd.setValorCompra(produto.getValorCompra());
		oldProd.setValorVenda(produto.getValorVenda());
		session.saveChanges();
	}
	
}
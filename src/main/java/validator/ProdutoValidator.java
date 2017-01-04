package validator;

import java.util.List;

import dao.ProdutoDAO;
import exception.DuplicatedEntryException;
import exception.InvalidAmountException;
import model.Produto;

public class ProdutoValidator {
	
	private ProdutoDAO dao;
	
	public ProdutoValidator() {
		dao = new ProdutoDAO();
	}
	
	
	public void ehUnico(Produto produto) throws DuplicatedEntryException {
		List<Produto> produtos = dao.getAll();
		for (Produto produto2 : produtos) {
			if (produto2.getNome().equalsIgnoreCase(produto.getNome()) ||
				produto2.getDescricao().equalsIgnoreCase(produto.getDescricao())) {
				throw new DuplicatedEntryException("Já existe um produto cadastrado com este(s) mesmo(s) dado(s)!");
			}
		}
	}

	public void ehQuantidadeValida(Integer quantidade) throws InvalidAmountException {
		if (quantidade <= 0) {
			throw new InvalidAmountException("Quantidade inválida!");
		}
	}
	
	public void ehValorValido(Double valor) throws InvalidAmountException {
		if (valor <= 0d) {
			throw new InvalidAmountException("Valor inválido!");
		}
	}
}

package service;

import java.util.List;

import dao.ProdutoDAO;
import exception.DuplicatedEntryException;
import exception.EasyStockException;
import exception.InvalidAmountException;
import exception.RequiredFieldException;
import model.Produto;
import validator.ProdutoValidator;
import validator.Validator;

public class ProdutoService {

	private ProdutoValidator produtoValidator;
	private Validator validator;
	private ProdutoDAO dao;

	public ProdutoService() {
		produtoValidator = new ProdutoValidator();
		validator = new Validator();
		dao = new ProdutoDAO();
	}

	public void save(Produto produto) throws EasyStockException {
		try {
			validator.ehVazio(produto.getNome());
			validator.ehVazio(produto.getDescricao());
			produtoValidator.ehQuantidadeValida(produto.getQuantidade());
			produtoValidator.ehValorValido(produto.getValorCompra());
			produtoValidator.ehValorValido(produto.getValorVenda());
			produtoValidator.ehUnico(produto);
			dao.save(produto);
		} catch (DuplicatedEntryException e) {
			throw new EasyStockException(e.getMessage());
		} catch (RequiredFieldException e) {
			throw new EasyStockException(e.getMessage());
		} catch (InvalidAmountException e) {
			throw new EasyStockException(e.getMessage());
		}
	}

	public void update(Produto produto) throws EasyStockException {
		try {
			validator.ehVazio(produto.getNome());
			validator.ehVazio(produto.getDescricao());
			produtoValidator.ehQuantidadeValida(produto.getQuantidade());
			produtoValidator.ehValorValido(produto.getValorCompra());
			produtoValidator.ehValorValido(produto.getValorVenda());
			dao.update(produto);
		} catch (RequiredFieldException e) {
			throw new EasyStockException(e.getMessage());
		} catch (InvalidAmountException e) {
			throw new EasyStockException(e.getMessage());
		}
	}

	public void delete(Produto produto) throws EasyStockException {
		List<Produto> produtoes = dao.getAll();
		if (!produtoes.contains(produto)) {
			throw new EasyStockException("Este produto não existe!");
		} else {
			dao.remove(produto);
		}
	}

	public List<Produto> getAll() {
		return dao.getAll();
	}

}

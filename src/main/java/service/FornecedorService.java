package service;

import java.util.List;

import dao.FornecedorDAO;
import exception.DuplicatedEntryException;
import exception.EasyStockException;
import exception.EmailException;
import exception.RequiredFieldException;
import model.Fornecedor;
import validator.FornecedorValidator;
import validator.Validator;

public class FornecedorService {

	private FornecedorValidator fornecedorValidator;
	private Validator validator;
	private FornecedorDAO dao;

	public FornecedorService() {
		fornecedorValidator = new FornecedorValidator();
		validator = new Validator();
		dao = new FornecedorDAO();
	}

	public void save(Fornecedor fornecedor) throws EasyStockException {
		try {
			validator.ehVazio(fornecedor.getNomeFantasia());
			validator.ehVazio(fornecedor.getCnpj());
			validator.ehVazio(fornecedor.getEmail());
			fornecedorValidator.ehEmailValido(fornecedor.getEmail());
			fornecedorValidator.ehUnico(fornecedor);
			dao.save(fornecedor);
		} catch (EmailException e) {
			throw new EasyStockException(e.getMessage());
		} catch (DuplicatedEntryException e) {
			throw new EasyStockException(e.getMessage());
		} catch (RequiredFieldException e) {
			throw new EasyStockException(e.getMessage());
		}
	}

	public void update(Fornecedor fornecedor) throws EasyStockException {
		try {
			validator.ehVazio(fornecedor.getNomeFantasia());
			validator.ehVazio(fornecedor.getCnpj());
			validator.ehVazio(fornecedor.getEmail());
			fornecedorValidator.ehEmailValido(fornecedor.getEmail());
			dao.update(fornecedor);
		} catch (EmailException e) {
			throw new EasyStockException(e.getMessage());
		} catch (RequiredFieldException e) {
			throw new EasyStockException(e.getMessage());
		}
	}

	public void delete(Fornecedor fornecedor) throws EasyStockException {
		List<Fornecedor> fornecedores = dao.getAll();
		if (!fornecedores.contains(fornecedor)) {
			throw new EasyStockException("Este fornecedor não existe!");
		} else {
			dao.remove(fornecedor);
		}
	}

	public List<Fornecedor> getAll() {
		return dao.getAll();
	}

}

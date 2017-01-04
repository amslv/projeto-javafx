package dao;

import model.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor> {

	public void update(Fornecedor fornecedor) {
		Fornecedor oldForn = session.load(Fornecedor.class, fornecedor.getId());
		oldForn.setNomeFantasia(fornecedor.getNomeFantasia());
		oldForn.setCnpj(fornecedor.getCnpj());
		oldForn.setEmail(fornecedor.getEmail());
		oldForn.setEndereco(fornecedor.getEndereco());
		oldForn.setProdutosFornecidos(fornecedor.getProdutosFornecidos());
		session.saveChanges();
	}
	
}

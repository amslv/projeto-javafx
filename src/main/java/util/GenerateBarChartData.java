package util;

import java.util.HashMap;

import dao.FornecedorDAO;
import model.Fornecedor;

public class GenerateBarChartData {
	
	private static FornecedorDAO dao = new FornecedorDAO();

	public static HashMap<String, Integer> generateData() {
		HashMap<String, Integer> fornecedorData = new HashMap<>();
		for (Fornecedor fornecedor : dao.getAll()) {
			fornecedorData.put(fornecedor.getNomeFantasia(), fornecedor.getProdutosFornecidos().size());
		}
		return fornecedorData;
	}
}

package validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.FornecedorDAO;
import exception.DuplicatedEntryException;
import exception.EmailException;
import model.Fornecedor;

public class FornecedorValidator {
	
	private FornecedorDAO dao;
	
	public FornecedorValidator() {
		dao = new FornecedorDAO();
	}
	
	private final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	public void ehEmailValido(String email) throws EmailException {
		Matcher matcher = pattern.matcher(email);
	    if (!matcher.matches()) {
	    	throw new EmailException("E-mail inválido");
	    }
	}
	
	public void ehUnico(Fornecedor fornecedor) throws DuplicatedEntryException {
		List<Fornecedor> fornecedores = dao.getAll();
		for (Fornecedor fornecedor2 : fornecedores) {
			if (fornecedor2.getNomeFantasia().equalsIgnoreCase(fornecedor.getNomeFantasia()) ||
				fornecedor2.getCnpj().equals(fornecedor.getCnpj()) ||
				fornecedor2.getEmail().equalsIgnoreCase(fornecedor.getEmail())) {
				throw new DuplicatedEntryException("Já foi cadastrado um fornecedor com esse(s) mesmo(s) dado(s)"); 
			}
		}
	}
}

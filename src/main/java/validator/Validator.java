package validator;

import exception.RequiredFieldException;

public class Validator {

	public void ehVazio(String atributo) throws RequiredFieldException {
		if (atributo.length() < 3 || atributo == null || atributo.isEmpty()) {
			throw new RequiredFieldException("Valor informado é inválido!");
		}
	}
	
}

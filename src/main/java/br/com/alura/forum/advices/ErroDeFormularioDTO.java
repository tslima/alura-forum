package br.com.alura.forum.advices;

public class ErroDeFormularioDTO {
	
	private final String field;
	private final String message;
	
	public ErroDeFormularioDTO(final String field, final String message) {
		super();
		this.field = field;
		this.message = message;
	}
	
	public String getField() {
		return field;
	}
	public String getMessage() {
		return message;
	}

}

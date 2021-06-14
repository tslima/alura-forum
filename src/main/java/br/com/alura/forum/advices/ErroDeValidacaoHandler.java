package br.com.alura.forum.advices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErroDeFormularioDTO> handle(final MethodArgumentNotValidException exception) {
		return exception.getBindingResult().getFieldErrors().stream()
				.map(error -> {	
					final String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
					return new ErroDeFormularioDTO(error.getField() , mensagem);})
				.collect(Collectors.toList());
	}
}

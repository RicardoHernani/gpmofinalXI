package com.ricardochaves.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ricardochaves.domain.Usuario;
import com.ricardochaves.form.UsuarioForm;
import com.ricardochaves.repositories.UsuarioRepository;
import com.ricardochaves.resources.exception.FieldMessage;

public class UsuarioInsertNomeValidator implements ConstraintValidator<UsuarioInsertNome, UsuarioForm> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioInsertNome ann) {
	}

	@Override
	public boolean isValid(UsuarioForm objForm, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = usuarioRepository.findByNome(objForm.getNome());
		if (aux != null) {
			list.add(new FieldMessage("Nome", "Esse nome de Usuário já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
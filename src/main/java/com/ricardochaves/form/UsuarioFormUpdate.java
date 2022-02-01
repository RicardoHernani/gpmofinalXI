package com.ricardochaves.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


public class UsuarioFormUpdate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="A senha é de preenchimento obrigatório")
	@Length(min=6, max=10, message="A senha deve conter entre 6 e 10 caracteres")
	private String senha;
	
	
	public UsuarioFormUpdate() {
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
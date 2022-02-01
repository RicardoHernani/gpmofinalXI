package com.ricardochaves.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="O email é de preenchimento obrigatório")
	@Email(message="O email deve ser válido")
	private String email;
	
	public EmailForm() {	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

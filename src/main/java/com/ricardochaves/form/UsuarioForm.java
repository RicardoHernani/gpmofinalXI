package com.ricardochaves.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ricardochaves.domain.Usuario;
import com.ricardochaves.validation.UsuarioInsertEmail;
import com.ricardochaves.validation.UsuarioInsertNome;

@UsuarioInsertEmail
@UsuarioInsertNome
public class UsuarioForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="O nome de usuário é de preenchimento obrigatório")
	@Length(min=3, max=10, message="O nome de usuário deve conter entre 3 e 10 caracteres")
	private String nome;
	
	@NotEmpty(message="O email é de preenchimento obrigatório")
	@Email(message="O email deve ser válido")
	private String email;
	
	@NotEmpty(message="A senha é de preenchimento obrigatório")
	@Length(min=6, max=10, message="A senha deve conter entre 6 e 10 caracteres")
	private String senha;
	
	public UsuarioForm() {
	}
	
	public UsuarioForm(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		senha = obj.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}

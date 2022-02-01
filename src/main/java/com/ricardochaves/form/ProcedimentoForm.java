package com.ricardochaves.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProcedimentoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message="O tipo do procedimento é de preenchimento obrigatório!")
	private Integer tipo;
	
	@NotNull(message="O prêmio do procedimento é de preenchimento obrigatório!")
	private Integer premio;
	
	@NotNull(message="A identificação da cirurgia a qual o procedimento pertence é de preenchimento obrigatório!")
	private Integer cirurgiaId;
	
	@NotNull(message="O código da referência é de preenchimento obrigatório!")
	private Integer referenciaCodigo;
	
	public ProcedimentoForm() {
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getPremio() {
		return premio;
	}

	public void setPremio(Integer premio) {
		this.premio = premio;
	}

	public Integer getCirurgiaId() {
		return cirurgiaId;
	}

	public void setCirurgiaId(Integer cirurgiaId) {
		this.cirurgiaId = cirurgiaId;
	}

	public Integer getReferenciaCodigo() {
		return referenciaCodigo;
	}

	public void setReferenciaCodigo(Integer referenciaCodigo) {
		this.referenciaCodigo = referenciaCodigo;
	}
	
}

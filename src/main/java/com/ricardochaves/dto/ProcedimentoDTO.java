package com.ricardochaves.dto;

import java.io.Serializable;

import com.ricardochaves.domain.Procedimento;
import com.ricardochaves.domain.Referencia;

public class ProcedimentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tipo;
	private Integer premio;
	
	private Referencia referencia;
	
	
	public ProcedimentoDTO() {
	}

	public ProcedimentoDTO(Procedimento obj) {
		super();
		this.tipo = obj.getTipo().getCod();
		this.premio = obj.getPremio().getCod();
		this.referencia = obj.getReferencia();
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {  //Preciso mudar os getters e setters como fiz na classe Procedimento??
		this.tipo = tipo;
	}

	public Integer getPremio() {
		return premio;
	}

	public void setPremio(Integer premio) {
		this.premio = premio;
	}

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}
	
	
	
	
	
}

package com.ricardochaves.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Referencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo;
	
	private String descricao;
	private BigDecimal pontos;
	private BigDecimal valor;
	
	public Referencia() {
	}

	public Referencia(Integer codigo, String descricao, BigDecimal pontos, BigDecimal valor) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.pontos = pontos;
		this.valor = valor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPontos() {
		return pontos;
	}

	public void setPontos(BigDecimal pontos) {
		this.pontos = pontos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Referencia other = (Referencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
package com.ricardochaves.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.domain.Procedimento;

public class CirurgiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer matricula;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate data;
	
	private Integer usuarioid;
	private String nome;
	
	private Set<Procedimento> procedimentos = new HashSet<Procedimento>();
	
	private BigDecimal subTotalPontos;
	private BigDecimal subTotalValor;
	
	public CirurgiaDTO() {
	}
	
	public CirurgiaDTO(Cirurgia obj) {
		id = obj.getId();
		matricula = obj.getMatricula();
		data = obj.getData();
		usuarioid = obj.getUsuario().getId();
		nome = obj.getUsuario().getNome();
		procedimentos = obj.getProcedimentos();
		subTotalPontos = obj.getSubTotalPontos();
		subTotalValor = obj.getSubTotalValor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Integer usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public BigDecimal getSubTotalPontos() {
		return subTotalPontos;
	}

	public void setSubTotalPontos(BigDecimal subTotalPontos) {
		this.subTotalPontos = subTotalPontos;
	}

	public BigDecimal getSubTotalValor() {
		return subTotalValor;
	}

	public void setSubTotalValor(BigDecimal subTotalValor) {
		this.subTotalValor = subTotalValor;
	}

	
	
}

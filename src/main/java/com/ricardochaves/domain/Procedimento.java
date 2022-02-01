package com.ricardochaves.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ricardochaves.enums.PremioProcedimento;
import com.ricardochaves.enums.TipoProcedimento;

@Entity
public class Procedimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer tipo;
	private Integer premio;
	
	@JsonIgnore
	@ManyToOne
	private Cirurgia cirurgia;
	
	@OneToOne
	private Referencia referencia;
	
	public Procedimento() {
	}

	public Procedimento(Integer id, TipoProcedimento tipo, PremioProcedimento premio, Cirurgia cirurgia, Referencia referencia) {
		super();
		this.id = id;
		this.tipo = tipo.getCod();
		this.premio = premio.getCod();
		this.cirurgia = cirurgia;
		this.referencia = referencia;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoProcedimento getTipo() {
		return TipoProcedimento.toEnum(tipo);
	}

	public void setTipo(TipoProcedimento tipo) {
		this.tipo = tipo.getCod();
	}

	public PremioProcedimento getPremio() {
		return PremioProcedimento.toEnum(premio);
	}

	public void setPremio(PremioProcedimento premio) {
		this.premio = premio.getCod();
	}

	public Cirurgia getCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(Cirurgia cirurgia) {
		this.cirurgia = cirurgia;
	}
	
	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Procedimento other = (Procedimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

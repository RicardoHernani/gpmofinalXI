package com.ricardochaves.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cirurgia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer matricula;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate data;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "cirurgia", cascade=CascadeType.ALL)
	private Set<Procedimento> procedimentos = new HashSet<Procedimento>();
	
	public Cirurgia() {
	}

	public Cirurgia(Integer id, Integer matricula, LocalDate data, Usuario usuario) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.data = data;
		this.usuario = usuario;
	}
											//Cálculo de subtotais de Pontos e Valor para 
	public BigDecimal getSubTotalPontos() { //cada cirurgia
		double subTotalPontos = 0;
		for (Procedimento pro : procedimentos) {
			if((pro.getTipo().getCod()==1 || pro.getTipo().getCod()==2) && pro.getPremio().getCod()==2) {
				subTotalPontos += (pro.getReferencia().getPontos()).doubleValue();
			} else subTotalPontos += 0;
		}
		return new BigDecimal(subTotalPontos).round(new MathContext(2, RoundingMode.HALF_EVEN));
	}
	
	public BigDecimal getSubTotalValor() {
		double subTotalValor = 0;
		for (Procedimento pro : procedimentos) {
			if(pro.getTipo().getCod()==1 && pro.getPremio().getCod()==1) {
				subTotalValor += (pro.getReferencia().getValor()).doubleValue();
			} else if (pro.getTipo().getCod()==2 && pro.getPremio().getCod()==1) {
				subTotalValor += (pro.getReferencia().getValor()).doubleValue()*0.5;
			} else subTotalValor += 0;
		}
		return  new BigDecimal(subTotalValor).round(new MathContext(2, RoundingMode.HALF_EVEN));
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
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
		Cirurgia other = (Cirurgia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}

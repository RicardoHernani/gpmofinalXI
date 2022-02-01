package com.ricardochaves.enums;

public enum TipoProcedimento {

	PRINCIPAL(1, "Procedimento principal"),
	SECUNDARIO(2, "Procedimento secundário");
	
	private int cod;
	private String descricao;
	
	private TipoProcedimento(int cod, String descricao) {   //O construtor de tipo enumerado é sempre private
		this.cod = cod;										//E o nome do tipo enumerado não muda mais uma vez que foi instanciado.
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoProcedimento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoProcedimento x : TipoProcedimento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("O tipo de procedimento é inválido: " + cod);
	}
	
}

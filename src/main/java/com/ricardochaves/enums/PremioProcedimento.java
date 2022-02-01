package com.ricardochaves.enums;

public enum PremioProcedimento {

	DINHEIRO(1, "Dinheiro"),
	TAREFABASICA(2, "Tarefa básica");
	
	private int cod;
	private String descricao;
	
	private PremioProcedimento(int cod, String descricao) {      //O construtor de tipo enumerado é sempre private
		this.cod = cod;								 			//E o nome do tipo enumerado não muda mais uma vez que foi instanciado.
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static PremioProcedimento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (PremioProcedimento x : PremioProcedimento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("O prêmio do procedimento é inválido: " + cod);
	}
	
}
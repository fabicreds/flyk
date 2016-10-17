package com.tcc.flyk.entity.enumerator;

public enum StatusCompromissoEnum {
	SOLICITADO(1, "Pretendido"), RECURUSADA(2, "Recusado"), ORCADO(3, "Orçado"),  MARCADO(4, "Marcado"), CANCELADO(5, "Cancelado"), REALIZADO(6, "Realizado");

	private int codigo;
	
	private String descricao;

	private StatusCompromissoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}

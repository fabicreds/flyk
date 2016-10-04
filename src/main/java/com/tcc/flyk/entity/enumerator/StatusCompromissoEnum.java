package com.tcc.flyk.entity.enumerator;

public enum StatusCompromissoEnum {
	SOLICITADO(1, "Pretendido"), ORCADO(2, "Orçado"),  MARCADO(3, "Marcado"), CANCELADO(4, "Cancelado"), REALIZADO(5, "Realizado");

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

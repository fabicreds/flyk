package com.tcc.flyk.entity.enumerator;

public enum StatusCompromissoEnum {
	PRETENDIDO(1, "Pretendido"), MARCADO(2, "Marcado"), CANCELADO(3, "Cancelado"), REALIZADO(4, "Realizado");

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

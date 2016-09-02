package com.tcc.flyk.entity.enumerator;

public enum PrivacidadeEnum {
	PUBLICO(1,"Publico"), APENAS_AMIGOS(2, "Apenas amigos"), PRIVADO(3, "Privado");

	private int codigo;
	private String descricao;
	
	private PrivacidadeEnum(int codigo, String descricao) {
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

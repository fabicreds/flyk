package com.tcc.flyk.entity.enumerator;

public enum StatusAmizadeEnum {
	
	ATIVA(1, "Ativa"), INATIVA(2, "Inativa"), SOLICITADA(3, "Solicitada");
	
	private int codigo;
	private String descricao;
	
	private StatusAmizadeEnum(int codigo, String descricao){
		this.setCodigo(codigo);
		this.setDescricao(descricao);
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

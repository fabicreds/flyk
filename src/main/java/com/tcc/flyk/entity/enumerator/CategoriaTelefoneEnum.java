package com.tcc.flyk.entity.enumerator;

public enum CategoriaTelefoneEnum {
	
	FIXO(1, "Fixo"), COMERCIAL(2, "Comercial"), MOVEL(3, "Movel");
	
	private int codigo;
	private String descricao;
	
	private CategoriaTelefoneEnum (int codigo, String descricao){
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

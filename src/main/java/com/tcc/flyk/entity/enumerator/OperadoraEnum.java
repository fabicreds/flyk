package com.tcc.flyk.entity.enumerator;

public enum OperadoraEnum {
	
	CLARO(1, "Claro"), VIVO(2, "Vivo"), TIM(3, "Tim"), OI(4, "Oi"), NEXTEL(5, "Nextel"), OUTROS(6, "Outros");
	
	private int codigo;
	private String descricao;
	
	private OperadoraEnum(int codigo, String descricao){
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

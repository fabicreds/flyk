package com.tcc.flyk.entity.enumerator;

public enum TipoCadastroEnum {
	
	CLIENTE(1, "Cliente"), PRESTADOR(2, "Prestador"), PREMIUM(3, "Premium"), ADMINISTRADOR(4, "Administrador");
	
	private int codigo;
	
	private String descricao;

	private TipoCadastroEnum(int codigo, String descricao) {
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

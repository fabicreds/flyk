package com.tcc.flyk.entity.enumerator;

public enum StatusAmizadeEnum {
	
	ATIVA(1), INATIVA(2);
	
	private int codigo;
	
	private StatusAmizadeEnum(int codigo){
		this.setCodigo(codigo);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}

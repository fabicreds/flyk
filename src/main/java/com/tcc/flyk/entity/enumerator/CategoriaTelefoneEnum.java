package com.tcc.flyk.entity.enumerator;

public enum CategoriaTelefoneEnum {
	
	FIXO(1), COMERCIAL(2), MOVEL(3);
	
	private int codigo;
	
	private CategoriaTelefoneEnum (int codigo){
		this.codigo = codigo;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
}

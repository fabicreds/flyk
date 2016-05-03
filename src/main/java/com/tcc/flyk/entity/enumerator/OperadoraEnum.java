package com.tcc.flyk.entity.enumerator;

public enum OperadoraEnum {
	
	CLARO(1), VIVO(2), TIM(3), OI(4), NEXTEL(5);
	
	private int codigo;
	
	private OperadoraEnum(int codigo){
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	

}

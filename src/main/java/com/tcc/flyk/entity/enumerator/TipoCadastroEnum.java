package com.tcc.flyk.entity.enumerator;

public enum TipoCadastroEnum {
	
	CLIENTE(1), PRESTADOR(2), PREMIUM(3), ADMINISTRADOR(4);
	
	private int codigo;

	private TipoCadastroEnum(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public TipoCadastroEnum definirTipoCadastro(int codigo){
		switch(codigo){
			case 1: return TipoCadastroEnum.CLIENTE;
			case 2: return TipoCadastroEnum.PRESTADOR;
			case 3: return TipoCadastroEnum.PREMIUM;
			case 4: return TipoCadastroEnum.ADMINISTRADOR;
			default: return TipoCadastroEnum.CLIENTE;
		}
	}

}

package com.tcc.flyk.entity.enumerator;

public enum CategoriaServicoEnum {

	ENCANADOR(1,"Encanador"), CONSTRUCAO(1, "Contrução");
	
	private int codigo;
	private String descricao;
	
	private CategoriaServicoEnum(int codigo, String descricao){
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

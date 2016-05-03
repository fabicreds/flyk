package com.tcc.flyk.entity;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

public class Servico {
	
	private int codigo;
	
	private String descricao;
	
	private CategoriaServicoEnum categoriaServico;

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

	public CategoriaServicoEnum getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServicoEnum categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

}

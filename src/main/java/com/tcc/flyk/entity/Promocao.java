package com.tcc.flyk.entity;

import java.util.Date;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

public class Promocao {
	
	private int codigo;
	
	private Float valorPromocional;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private String descricao;
	
	private String nomePromocao;
	
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomePromocao() {
		return nomePromocao;
	}

	public void setNomePromocao(String nomePromocao) {
		this.nomePromocao = nomePromocao;
	}

	private CategoriaServicoEnum categoriaServico;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Float getValorPromocional() {
		return valorPromocional;
	}

	public void setValorPromocional(Float valorPromocional) {
		this.valorPromocional = valorPromocional;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public CategoriaServicoEnum getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServicoEnum categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

}

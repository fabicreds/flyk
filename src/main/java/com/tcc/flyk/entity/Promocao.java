package com.tcc.flyk.entity;

import java.util.ArrayList;
import java.util.Date;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

public class Promocao {
	
	private int codigo;
	
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private String descricao;
	
	private String nomePromocao;
	
	private ArrayList<Preco> listaPreco = new ArrayList<Preco> ();

	
	
	public ArrayList<Preco> getListaPreco() {
		return listaPreco;
	}

	public void setListaPreco(ArrayList<Preco> listaPreco) {
		this.listaPreco = listaPreco;
	}

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

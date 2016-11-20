package com.tcc.flyk.entity;

import java.util.Date;
import java.util.List;

public class Promocao {

	private String id;

	private Date dataInicio;

	private Date dataFim;

	private String descricao;

	private String nomePromocao;

	private List<Preco> listaPreco;

	private String status;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Preco> getListaPreco() {
		return listaPreco;
	}

	public void setListaPreco(List<Preco> listaPreco) {
		this.listaPreco = listaPreco;
	}

}

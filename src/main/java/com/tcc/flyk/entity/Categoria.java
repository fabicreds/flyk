package com.tcc.flyk.entity;

import java.util.Date;

public class Categoria {
	//ID da categoria
	private String id;
	
	//Nome da categoria
	private String nomeCategoria;
	
	//Descricao da categoria
	private String descricaoCategoria;
	
	//Inicio da vig�ncia da categoria
	private Date inicioVigencia;
	
	//Status da categoria
	private String statusCategoria;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getStatusCategoria() {
		return statusCategoria;
	}

	public void setStatusCategoria(String statusCategoria) {
		this.statusCategoria = statusCategoria;
	}

	

	
}

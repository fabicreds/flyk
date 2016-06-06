package com.tcc.flyk.entity;

import java.util.Date;

public class Categoria {
	//ID da categoria
	private String id;
	
	//Nome da categoria
	private String nome_categoria;
	
	//Descricao da categoria
	private String descricao_categoria;
	
	//Inicio da vigência da categoria
	private Date inicio_vigencia;
	
	//Fim da vigência da categoria
	private Date fim_vigencia;
	
	//Status da categoria
	private String status_categoria;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}

	public String getDescricao_categoria() {
		return descricao_categoria;
	}

	public void setDescricao_categoria(String descricao_categoria) {
		this.descricao_categoria = descricao_categoria;
	}

	public Date getInicio_vigencia() {
		return inicio_vigencia;
	}

	public void setInicio_vigencia(Date inicio_vigencia) {
		this.inicio_vigencia = inicio_vigencia;
	}

	public Date getFim_vigencia() {
		return fim_vigencia;
	}

	public void setFim_vigencia(Date fim_vigencia) {
		this.fim_vigencia = fim_vigencia;
	}

	public String getStatus_categoria() {
		return status_categoria;
	}

	public void setStatus_categoria(String status_categoria) {
		this.status_categoria = status_categoria;
	}


	
}

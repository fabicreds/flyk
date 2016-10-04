package com.tcc.flyk.entity;

import java.util.Date;

public class Contrato {
	
	private Cliente cliente;
	
	private Prestador prestador;
	
	private Categoria servico;
	
	private AvaliacaoPrestador avaliacaoPrestador;
	
	private Date dataAvaliacaoServico; //Data que o cliente avaliou
	
	private int avaliacaoContratante;
	
	private Double custo;
	
	private String descricaoServico;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Categoria getServico() {
		return servico;
	}

	public void setServico(Categoria servico) {
		this.servico = servico;
	}

	public AvaliacaoPrestador getAvaliacaoPrestador() {
		return avaliacaoPrestador;
	}

	public void setAvaliacaoPrestador(AvaliacaoPrestador avaliacaoPrestador) {
		this.avaliacaoPrestador = avaliacaoPrestador;
	}

	public int getAvaliacaoContratante() {
		return avaliacaoContratante;
	}

	public void setAvaliacaoContratante(int avaliacaoContratante) {
		this.avaliacaoContratante = avaliacaoContratante;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}


	public Date getDataAvaliacaoServico() {
		return dataAvaliacaoServico;
	}

	public void setDataAvaliacaoServico(Date dataAvaliacaoServico) {
		this.dataAvaliacaoServico = dataAvaliacaoServico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	
	
	
}

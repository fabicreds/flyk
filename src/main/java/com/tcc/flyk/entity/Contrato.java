package com.tcc.flyk.entity;

public class Contrato {
	
	private Cliente cliente;
	
	private Prestador prestador;
	
	private Servico servico;
	
	private AvaliacaoPrestador avaliacaoPrestador;
	
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public AvaliacaoPrestador getAvaliacaoPrestador() {
		return avaliacaoPrestador;
	}

	public void setAvaliacaoPrestador(AvaliacaoPrestador avaliacaoPrestador) {
		this.avaliacaoPrestador = avaliacaoPrestador;
	}

	public AvaliacaoContratante getAvaliacaoContratante() {
		return avaliacaoContratante;
	}

	public void setAvaliacaoContratante(AvaliacaoContratante avaliacaoContratante) {
		this.avaliacaoContratante = avaliacaoContratante;
	}

	private AvaliacaoContratante avaliacaoContratante;

}

package com.tcc.flyk.entity;

import java.util.Date;
import java.util.List;

public class Indicacao {
	
	private List<Cliente> cliente;
	
	private Prestador prestador;
    
	private Date dataRecomendacao;

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Date getDataRecomendacao() {
		return dataRecomendacao;
	}

	public void setDataRecomendacao(Date dataRecomendacao) {
		this.dataRecomendacao = dataRecomendacao;
	}

	
	
}

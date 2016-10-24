package com.tcc.flyk.entity;

import java.util.List;

public class Prestador extends Cliente{
	
	private String cnpj;
	
	private List<Categoria> listaCategoriaServicosPrestados;
	
	private List<Compromisso> listaContratosServicosPrestados;

	//lista de id de clientes que recomendaram o prestador
	private List<String> listaRecomendacoesRecebidas;
	
	//valor pago pelo perfil premium
	private Double valorPremium;
	
	// se o perfil for PREMIUM, peso na busca deve ser maior para perfil ficar em destaque
	private int pesoBusca;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj; 
	}

	public List<Categoria> getListaCategoriaServicosPrestados() {
		return listaCategoriaServicosPrestados;
	}

	public void setListaCategoriaServicosPrestados(List<Categoria> listaCategoriaServicosPrestados) {
		this.listaCategoriaServicosPrestados = listaCategoriaServicosPrestados;
	}

	public List<Compromisso> getListaContratosServicosPrestados() {
		return listaContratosServicosPrestados;
	}

	public void setListaContratosServicosPrestados(List<Compromisso> listaContratosServicosPrestados) {
		this.listaContratosServicosPrestados = listaContratosServicosPrestados;
	}

	public Double getValorPremium() {
		return valorPremium;
	}

	public void setValorPremium(Double valorPremium) {
		this.valorPremium = valorPremium;
	}

	public int getPesoBusca() {
		return pesoBusca;
	}

	public void setPesoBusca(int pesoBusca) {
		this.pesoBusca = pesoBusca;
	}


	public List<String> getListaRecomendacoesRecebidas() {
		return listaRecomendacoesRecebidas;
	}

	public void setListaRecomendacoesRecebidas(List<String> listaRecomendacoesRecebidas) {
		this.listaRecomendacoesRecebidas = listaRecomendacoesRecebidas;
	}
}

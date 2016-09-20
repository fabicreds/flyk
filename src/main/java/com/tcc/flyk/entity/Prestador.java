package com.tcc.flyk.entity;

import java.util.List;

public class Prestador extends Cliente{
	
	private String cnpj;
	
	private List<Categoria> listaServicos;
	
	private List<Compromisso> listaServicosPrestados;

	private List<Cliente> listaRecomendacoesRecebidas;
	
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

	public List<Categoria> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<Categoria> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public List<Compromisso> getListaServicosPrestados() {
		return listaServicosPrestados;
	}

	public void setListaServicosPrestados(List<Compromisso> listaServicosPrestados) {
		this.listaServicosPrestados = listaServicosPrestados;
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


	public List<Cliente> getListaRecomendacoesRecebidas() {
		return listaRecomendacoesRecebidas;
	}

	public void setListaRecomendacoesRecebidas(List<Cliente> listaRecomendacoesRecebidas) {
		this.listaRecomendacoesRecebidas = listaRecomendacoesRecebidas;
	}
}

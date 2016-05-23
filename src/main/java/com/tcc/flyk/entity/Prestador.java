package com.tcc.flyk.entity;

import java.util.List;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

public class Prestador{
	
	private String cnpj;
	
	private List<CategoriaServicoEnum> listaServicos;
	
	private List<Cliente> listaRecomendacao;
	
	private List<Compromisso> listaServicosContratados;
	
	//valor pago pelo perfil premium
	private Float valorPremium;
	
	// se o perfil for PREMIUM, peso na busca deve ser maior para perfil ficar em destaque
	private int pesoBusca;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<CategoriaServicoEnum> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<CategoriaServicoEnum> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public List<Cliente> getListaRecomendacao() {
		return listaRecomendacao;
	}

	public void setListaRecomendacao(List<Cliente> listaRecomendacao) {
		this.listaRecomendacao = listaRecomendacao;
	}

	public List<Compromisso> getListaServicosContratados() {
		return listaServicosContratados;
	}

	public void setListaServicosContratados(List<Compromisso> listaServicosContratados) {
		this.listaServicosContratados = listaServicosContratados;
	}

	public Float getValorPremium() {
		return valorPremium;
	}

	public void setValorPremium(Float valorPremium) {
		this.valorPremium = valorPremium;
	}

	public int getPesoBusca() {
		return pesoBusca;
	}

	public void setPesoBusca(int pesoBusca) {
		this.pesoBusca = pesoBusca;
	}

}

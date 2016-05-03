package com.tcc.flyk.entity;

import java.util.List;

public class Prestador extends Cliente{
	
	private List<Servico> listaServicos;
	
	private Perfil perfil;
	
	private List<Cliente> listaRecomendacao;

	public List<Servico> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<Servico> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Cliente> getListaRecomendacao() {
		return listaRecomendacao;
	}

	public void setListaRecomendacao(List<Cliente> listaRecomendacao) {
		this.listaRecomendacao = listaRecomendacao;
	}

}

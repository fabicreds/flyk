package com.tcc.flyk.entity;

public class Perfil {
	
	//indica se o perfil do cliente/prestador e Prestador Premium
	private boolean indicadorPrestadorPremio;
	
	//1 - ativo e 0 - inativo
	private boolean status;
	
	//valor pago pelo perfil, caso seja um perfil premium
	private Float valor;
	
	// se o perfil for PREMIUM, peso na busca deve ser maior para perfil ficar em destaque
	private int pesoBusca;

	public boolean isIndicadorPrestadorPremio() {
		return indicadorPrestadorPremio;
	}

	public void setIndicadorPrestadorPremio(boolean indicadorPrestadorPremio) {
		this.indicadorPrestadorPremio = indicadorPrestadorPremio;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public int getPesoBusca() {
		return pesoBusca;
	}

	public void setPesoBusca(int pesoBusca) {
		this.pesoBusca = pesoBusca;
	}

	
}

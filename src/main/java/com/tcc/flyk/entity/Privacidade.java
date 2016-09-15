package com.tcc.flyk.entity;

import com.tcc.flyk.entity.enumerator.PrivacidadeEnum;

public class Privacidade {
	
	private PrivacidadeEnum exibeCPF;
	
	private PrivacidadeEnum exibeEndereco;
	
	private PrivacidadeEnum exibeTelefone;
	
	private PrivacidadeEnum exibeAgenda;

	public PrivacidadeEnum getExibeCPF() {
		return exibeCPF;
	}

	public void setExibeCPF(PrivacidadeEnum exibeCPF) {
		this.exibeCPF = exibeCPF;
	}
	
	public void setExibeCPF(int codigo) {
		this.exibeCPF = definePrivacidade(codigo);
	}

	public PrivacidadeEnum getExibeEndereco() {
		return exibeEndereco;
	}

	public void setExibeEndereco(PrivacidadeEnum exibeEndereco) {
		this.exibeEndereco = exibeEndereco;
	}
	
	public void setExibeEndereco(int codigo) {
		this.exibeEndereco = definePrivacidade(codigo);
	}

	public PrivacidadeEnum getExibeTelefone() {
		return exibeTelefone;
	}

	public void setExibeTelefone(PrivacidadeEnum exibeTelefone) {
		this.exibeTelefone = exibeTelefone;
	}
	
	public void setExibeTelefone(int codigo) {
		this.exibeTelefone = definePrivacidade(codigo);
	}

	public PrivacidadeEnum getExibeAgenda() {
		return exibeAgenda;
	}

	public void setExibeAgenda(PrivacidadeEnum exibeAgenda) {
		this.exibeAgenda = exibeAgenda;
	}
	
	public void setExibeAgenda(int codigo) {
		this.exibeAgenda = definePrivacidade(codigo);
	}
	
	private PrivacidadeEnum definePrivacidade(int codigo){
		switch(codigo){
		case 1: return PrivacidadeEnum.PUBLICO;
		case 2: return PrivacidadeEnum.APENAS_AMIGOS;
		case 3: return PrivacidadeEnum.PRIVADO;
		default: return PrivacidadeEnum.PRIVADO;
		}
	}
}

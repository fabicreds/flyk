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

	public PrivacidadeEnum getExibeEndereco() {
		return exibeEndereco;
	}

	public void setExibeEndereco(PrivacidadeEnum exibeEndereco) {
		this.exibeEndereco = exibeEndereco;
	}

	public PrivacidadeEnum getExibeTelefone() {
		return exibeTelefone;
	}

	public void setExibeTelefone(PrivacidadeEnum exibeTelefone) {
		this.exibeTelefone = exibeTelefone;
	}

	public PrivacidadeEnum getExibeAgenda() {
		return exibeAgenda;
	}

	public void setExibeAgenda(PrivacidadeEnum exibeAgenda) {
		this.exibeAgenda = exibeAgenda;
	}
	
	

}

package com.tcc.flyk.entity;

import com.tcc.flyk.entity.enumerator.CategoriaTelefoneEnum;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

public class Telefone {

	
	private int ddd;
	
	private int numero;
	
	private CategoriaTelefoneEnum categoriaTelefone;
	
	private OperadoraEnum operadora;

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public CategoriaTelefoneEnum getCategoriaTelefone() {
		return categoriaTelefone;
	}

	public void setCategoriaTelefone(CategoriaTelefoneEnum categoriaTelefone) {
		this.categoriaTelefone = categoriaTelefone;
	}

	public OperadoraEnum getOperadora() {
		return operadora;
	}

	public void setOperadora(OperadoraEnum operadora) {
		this.operadora = operadora;
	}

	
}

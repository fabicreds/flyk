package com.tcc.flyk.entity;

import com.tcc.flyk.entity.enumerator.CategoriaTelefoneEnum;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

public class Telefone {

	private int ddd;

	private String numero;

	private CategoriaTelefoneEnum categoriaTelefone;

	private OperadoraEnum operadora;

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}


	public CategoriaTelefoneEnum getCategoriaTelefone() {
		return categoriaTelefone;
	}

	public void setCategoriaTelefone(CategoriaTelefoneEnum categoriaTelefone) {
		this.categoriaTelefone = categoriaTelefone;
	}

	public void setCategoriaTelefone(int codigo) {
		switch (codigo) {
		case 1:
			this.categoriaTelefone = CategoriaTelefoneEnum.FIXO;
			break;
		case 2:
			this.categoriaTelefone = CategoriaTelefoneEnum.COMERCIAL;
			break;
		case 3:
			this.categoriaTelefone = CategoriaTelefoneEnum.MOVEL;
			break;
		default:
			this.categoriaTelefone = CategoriaTelefoneEnum.FIXO;
		}
	}

	public OperadoraEnum getOperadora() {
		return operadora;
	}

	public void setOperadora(OperadoraEnum operadora) {
		this.operadora = operadora;
	}

	public void setOperadora(int codigo) {
		switch (codigo) {
		case 1:
			this.operadora = OperadoraEnum.CLARO;
			break;
		case 2:
			this.operadora = OperadoraEnum.VIVO;
			break;
		case 3:
			this.operadora = OperadoraEnum.TIM;
			break;
		case 4:
			this.operadora = OperadoraEnum.OI;
			break;
		case 5:
			this.operadora = OperadoraEnum.NEXTEL;
			break;
		case 6:
			this.operadora = OperadoraEnum.OUTROS;
			break;
		default:
			this.operadora = OperadoraEnum.OUTROS;
			break;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}

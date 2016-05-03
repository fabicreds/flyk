package com.tcc.flyk.entity;

import java.util.Date;

import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;


public class Amizade {
	
	private Date dataInicioAmizade;
	
	private Cliente amigo;
	
	private StatusAmizadeEnum statusEnum;

	public Date getDataInicioAmizade() {
		return dataInicioAmizade;
	}

	public void setDataInicioAmizade(Date dataInicioAmizade) {
		this.dataInicioAmizade = dataInicioAmizade;
	}

	public StatusAmizadeEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusAmizadeEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Cliente getAmigo() {
		return amigo;
	}

	public void setAmigo(Cliente amigo) {
		this.amigo = amigo;
	}
	
	
	

}

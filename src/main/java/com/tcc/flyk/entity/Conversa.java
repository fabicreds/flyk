package com.tcc.flyk.entity;

import java.util.Date;

public class Conversa {
	
	//id do cliente que esta enviando a mensagem
	private String idOrigem;

	//id do cliente que esta recebendo a msg	
	private String idDestino;

	//A MENSAGEM
	private String msg;
	
	private Date data;

	public String getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(String idOrigem) {
		this.idOrigem = idOrigem;
	}

	public String getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}

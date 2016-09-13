package com.tcc.flyk.entity;

import java.util.Date;

public class Conversa {
	
	//id do cliente que esta enviando a mensagem
	private String idUsuario;

	//A MENSAGEM
	private String msg;
	
	private Date data;
	
	private String flagEnviadoRecebido;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setidUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getflagEnviadoRecebido() {
		return flagEnviadoRecebido;
	}

	public void setflagEnviadoRecebido(String flagEnviadoRecebido) {
		this.flagEnviadoRecebido = flagEnviadoRecebido;
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

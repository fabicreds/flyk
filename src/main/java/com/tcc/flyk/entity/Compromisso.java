package com.tcc.flyk.entity;

import java.util.Date;

public class Compromisso {
	
	private Date dataInclusao;
	
	private Contrato contrato;
	
	//se o objeto de contrato estiver vazio, o indicador de ferias deve estar S
	private boolean indicadorFerias;
	
	private Date dataInicio;
	
	private Date dataFim;

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public boolean isIndicadorFerias() {
		return indicadorFerias;
	}

	public void setIndicadorFerias(boolean indicadorFerias) {
		this.indicadorFerias = indicadorFerias;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}

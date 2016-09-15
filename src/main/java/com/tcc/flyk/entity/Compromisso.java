package com.tcc.flyk.entity;

import java.util.Date;

import com.tcc.flyk.entity.enumerator.StatusCompromissoEnum;

public class Compromisso {
	
	private Contrato contrato;
	
	//se o objeto de contrato estiver vazio, o indicador de ferias deve estar S
	private boolean indicadorFerias;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private StatusCompromissoEnum status;

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

	public StatusCompromissoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusCompromissoEnum status) {
		this.status = status;
	}
	
	public void setStatus(int codigo) {
			switch (codigo) {
			case 1:
				this.status =  StatusCompromissoEnum.PRETENDIDO;break;
			case 2:
				this.status =  StatusCompromissoEnum.MARCADO;break;
			case 3:
				this.status =  StatusCompromissoEnum.CANCELADO;break;
			case 4:
				this.status =  StatusCompromissoEnum.REALIZADO;break;
			default:
				this.status =  StatusCompromissoEnum.PRETENDIDO;break;
		}	 
	}

	
}

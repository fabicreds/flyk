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
				this.status =  StatusCompromissoEnum.SOLICITADO;break;
			case 2:
				this.status =  StatusCompromissoEnum.RECURUSADA;break;
			case 3:
				this.status =  StatusCompromissoEnum.ORCADO;break;
			case 4:
				this.status =  StatusCompromissoEnum.MARCADO;break;
			case 5:
				this.status =  StatusCompromissoEnum.CANCELADO;break;
			case 6:
				this.status =  StatusCompromissoEnum.REALIZADO;break;
			default:
				this.status =  StatusCompromissoEnum.SOLICITADO;break;
		}	 
	}

	public int compararCompromissos(Compromisso comp){
		int retorno = 0;
		if(this.contrato!=null && comp.getContrato()!=null){
			//ID DO CLIENTE É DIFERENTE
			if(this.contrato.getCliente()!=null && comp.getContrato().getCliente()!=null ){
				if(!this.contrato.getCliente().getId().equals(comp.getContrato().getCliente().getId())){
					retorno++;
				}
			}
			//ID DO PRESTADOR É DIFERENTE
			if(this.contrato.getPrestador()!=null && comp.getContrato().getPrestador()!=null ){
				if(!this.contrato.getPrestador().getId().equals(comp.getContrato().getPrestador().getId())){
					retorno++;
				}
			}
			
			//ID DO SERVIÇO DIFERENTE
			if(this.contrato.getServico()!=null && comp.getContrato().getServico()!=null ){
				if(!this.contrato.getServico().getId().equals(comp.getContrato().getServico().getId())){
					retorno++;
				}
			}
			
		}
		
		//DATA DO INICIO DIFERENTE
		if(this.dataInicio!=null && comp.getDataInicio()!=null){
			if(!this.dataInicio.equals(comp.getDataInicio())){
				retorno++;
			}
		}
		
		//DATA DO FIM DIFERENTE
		if(this.dataFim!=null && comp.getDataFim()!=null){
			if(!this.dataFim.equals(comp.getDataFim())){
				retorno++;
			}
		}
		
		return retorno;
	}
}

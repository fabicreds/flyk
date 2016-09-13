package com.tcc.flyk.util;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Compromisso;

@Component
public class CompromissoUtil {

	@Resource
	private ContratoUtil contratoUtil;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public JSONObject toJSON(Compromisso compromisso) {
		JSONObject jObjt = new JSONObject();
		if (compromisso.getContrato() != null) {
			jObjt.put("contrato", contratoUtil.toJSON(compromisso.getContrato()));
		}
		jObjt.put("indicadorFerias", compromisso.isIndicadorFerias());
		if (compromisso.getDataInicio() != null) {
			jObjt.put("dataInicio", format.format(compromisso.getDataInicio()));
		}
		if (compromisso.getDataFim() != null) {
			jObjt.put("dataFim", format.format(compromisso.getDataFim()));
		}
		if (compromisso.getStatus() != null) {
			jObjt.put("status", compromisso.getStatus().getCodigo());
			jObjt.put("statusDescricao", compromisso.getStatus().getDescricao());
		}
		return jObjt;
	}
}

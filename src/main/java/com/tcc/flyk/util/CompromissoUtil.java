package com.tcc.flyk.util;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Compromisso;

@Component
public class CompromissoUtil {

	@Resource
	private ContratoUtil contratoUtil;

	public JSONObject toJSON(Compromisso compromisso) {
		JSONObject jObjt = new JSONObject();
		if (compromisso.getDataInclusao() != null) {
			jObjt.put("dataInclusao", compromisso.getDataInclusao());
		}
		if (compromisso.getContrato() != null) {
			jObjt.put("contrato", contratoUtil.toJSON(compromisso.getContrato()));
		}
		jObjt.put("indicadorFerias", compromisso.isIndicadorFerias());
		if (compromisso.getDataInicio() != null) {
			jObjt.put("dataInicio", compromisso.getDataInicio());
		}
		if (compromisso.getDataFim() != null) {
			jObjt.put("dataFim", compromisso.getDataFim());
		}
		if (compromisso.getStatus() != null) {
			jObjt.put("status", compromisso.getStatus().getDescricao());
		}
		return jObjt;
	}
}

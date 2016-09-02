package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Privacidade;

@Component
public class PrivacidadeUtil {

	public JSONObject toJSON(Privacidade privacidade) {
		JSONObject jObjt = new JSONObject();
		if (privacidade.getExibeCPF() != null) {
			jObjt.put("exibeCPF", privacidade.getExibeCPF().getDescricao());
		}
		if (privacidade.getExibeEndereco() != null) {
			jObjt.put("exibeEndereco", privacidade.getExibeEndereco().getDescricao());
		}
		if (privacidade.getExibeTelefone() != null) {
			jObjt.put("exibeTelefone", privacidade.getExibeTelefone().getDescricao());
		}
		if (privacidade.getExibeAgenda() != null) {
			jObjt.put("exibeAgenda", privacidade.getExibeAgenda().getDescricao());
		}
		return jObjt;
	}
}

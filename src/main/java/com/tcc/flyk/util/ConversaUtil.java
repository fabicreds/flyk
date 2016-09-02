package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Conversa;

@Component
public class ConversaUtil {

	public JSONObject toJSON(Conversa conversa) {
		JSONObject jObjt = new JSONObject();
		if (conversa.getIdOrigem() != null) {
			jObjt.put("idOrigem", conversa.getIdOrigem());
		}
		if (conversa.getIdDestino() != null) {
			jObjt.put("idDestino", conversa.getIdDestino());
		}
		if (conversa.getMsg() != null) {
			jObjt.put("msg", conversa.getMsg());
		}
		if (conversa.getData() != null) {
			jObjt.put("data", conversa.getData());
		}
		return jObjt;
	}

}

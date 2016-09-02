package com.tcc.flyk.util;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;

@Component
public class AmizadeUtil {

	@Resource
	private ClienteUtil clienteUtil;

	public JSONObject toJSON(Amizade amizade) {
		JSONObject jObjt = new JSONObject();
		if (amizade.getDataInicioAmizade() != null) {
			jObjt.put("dataInicioAmizade", amizade.getDataInicioAmizade());
		}
		if (amizade.getAmigo() != null) {
			jObjt.put("amigo", clienteUtil.toJSON(amizade.getAmigo()));
		}
		if (amizade.getStatusEnum() != null) {
			jObjt.put("statusEnum", amizade.getStatusEnum().getDescricao());
		}
		return jObjt;
	}
}

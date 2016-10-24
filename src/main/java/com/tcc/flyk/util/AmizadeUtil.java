package com.tcc.flyk.util;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;

@Component
public class AmizadeUtil {

	@Resource
	private ClienteUtil clienteUtil;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public JSONObject toJSON(Amizade amizade) {
		JSONObject jObjt = new JSONObject();
		if (amizade.getDataInicioAmizade() != null) {
			jObjt.put("dataInicioAmizade", format.format(amizade.getDataInicioAmizade()));
		}
		if (amizade.getAmigo() != null) {
			jObjt.put("amigo", clienteUtil.toJSON(amizade.getAmigo()));
		}
		if (amizade.getStatusEnum() != null) {
			jObjt.put("status", amizade.getStatusEnum().getCodigo());
			jObjt.put("statusDescricao", amizade.getStatusEnum().getDescricao());
		}
		jObjt.put("isRecomendacaoDada", amizade.isRecomendacaoDada());
		jObjt.put("isRecomendacaoRecebida", amizade.isRecomendacaoRecebida());
		return jObjt;
	}
}

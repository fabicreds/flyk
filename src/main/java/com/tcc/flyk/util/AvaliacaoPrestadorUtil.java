package com.tcc.flyk.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.AvaliacaoPrestador;

@Component
public class AvaliacaoPrestadorUtil {

	public JSONObject toJSON(AvaliacaoPrestador avaliacao) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("avaliacaoTempo", avaliacao.getAvaliacaoPontualidade());
		jObjt.put("avaliacaoQualidade", avaliacao.getAvaliacaoQualidade());
		jObjt.put("avaliacaoPreco", avaliacao.getAvaliacaoPreco());
		jObjt.put("avaliacaoProfissionalismo", avaliacao.getAvaliacaoProfissionalismo());

		return jObjt;
	}

}

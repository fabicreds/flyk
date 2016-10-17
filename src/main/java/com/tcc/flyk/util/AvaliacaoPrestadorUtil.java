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
		jObjt.put("mediaAvaliacao", mediaAvaliacao(avaliacao));
		return jObjt;
	}

	public AvaliacaoPrestador toAvaliacaoPrestador(JSONObject json){
		AvaliacaoPrestador avaliacao = new AvaliacaoPrestador();
		if(!json.isNull("avaliacaoTempo")){
			avaliacao.setAvaliacaoPontualidade(json.getInt("avaliacaoTempo"));
		}
		if(!json.isNull("avaliacaoQualidade")){
			avaliacao.setAvaliacaoQualidade(json.getInt("avaliacaoQualidade"));
		}
		if(!json.isNull("avaliacaoPreco")){
			avaliacao.setAvaliacaoPreco(json.getInt("avaliacaoPreco"));
		}
		if(!json.isNull("avaliacaoProfissionalismo")){
			avaliacao.setAvaliacaoProfissionalismo(json.getInt("avaliacaoProfissionalismo"));
		}
		return avaliacao;
	}
	
	private float mediaAvaliacao(AvaliacaoPrestador avaliacao){
		int soma = avaliacao.getAvaliacaoPontualidade()+ avaliacao.getAvaliacaoPreco()+avaliacao.getAvaliacaoProfissionalismo() + avaliacao.getAvaliacaoQualidade();
		
		return soma/4f;
	}
}

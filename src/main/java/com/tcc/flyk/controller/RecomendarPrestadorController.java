package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.RecomendarPrestadorService;

@Controller
public class RecomendarPrestadorController {
	
	@Autowired
	private RecomendarPrestadorService service;

	@RequestMapping(value = "/recomendarPrestador", method = RequestMethod.POST)
	public @ResponseBody String recomendarPrestador(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo")) {
				return service.recomendarPrestador(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao atualizar a lista de amigos!");
		}
	}
	
	@RequestMapping(value = "/desfazerRecomendacaoPrestador", method = RequestMethod.POST)
	public @ResponseBody String desfazerRecomendacaoPrestador(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo")) {
				return service.desfazerRecomendacaoPrestador(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao atualizar a lista de amigos!");
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}
}

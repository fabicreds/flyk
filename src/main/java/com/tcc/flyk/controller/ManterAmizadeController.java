package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.ManterAmizadeService;

@Controller
public class ManterAmizadeController {
	
	@Autowired
	private ManterAmizadeService service;
	
	@RequestMapping(value = "/desfazerAmizade", method = RequestMethod.POST)
	public @ResponseBody String desfazerAmizade(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo")) {
				return service.desfazerAmizade(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao atualizar a lista de amigos!");
		}
	}
	
	@RequestMapping(value = "/solicitarAmizade", method = RequestMethod.POST)
	public @ResponseBody String solicitarAmizade(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo")) {
				return service.solicitarAmizade(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao atualizar a lista de amigos!");
		}
	}
	
	@RequestMapping(value = "/aceitarAmizade", method = RequestMethod.POST)
	public @ResponseBody String aceitarAmizade(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo")) {
				return service.aceitarAmizade(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"));
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

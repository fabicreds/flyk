package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.ConversaService;

@Controller
public class TalkWithFriendController {
	
	@Autowired
	private ConversaService service;

	@RequestMapping(value = "/talkWithFriend", method = RequestMethod.GET)
	public String talkWithFriend() {
		return "talkWithFriend";
	}
	
	@RequestMapping(value = "/enviarNovaMensagem", method = RequestMethod.POST)
	public @ResponseBody String contratarServico(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idCliente") && !jsonRequest.isNull("idAmigo") && !jsonRequest.isNull("mensagem")) {
				return service.enviarMensagem(jsonRequest.getString("idCliente"),jsonRequest.getString("idAmigo"), jsonRequest.getString("mensagem"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar página de perfil do amigo");
		}
	}
	
	@RequestMapping(value = "/mostrarConversa", method = RequestMethod.POST)
	public @ResponseBody String mostrarConversa(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idCliente") && !jsonRequest.isNull("idAmigo")) {
				return service.mostrarConversa(jsonRequest.getString("idCliente"),jsonRequest.getString("idAmigo"));
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar página de perfil do amigo");
		}
	}	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}
}

package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.ContatoPageService;

@Controller
public class ContatoPageController {
	
	@Autowired
	ContatoPageService service;
	
	@RequestMapping(value = "/contato", method = RequestMethod.GET)
	public String telaContato() {

		return "contato";
	}
	
	@RequestMapping(value = "/enviarEmail", method = RequestMethod.POST)
	public @ResponseBody String enviarEmail(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);
		
		try {
			if (!jsonRequest.isNull("nome") && !jsonRequest.isNull("email") && !jsonRequest.isNull("assunto")&& !jsonRequest.isNull("mensagem")) {
				return service.enviarEmail(jsonRequest.getString("nome"),jsonRequest.getString("email"), jsonRequest.getString("assunto"), jsonRequest.getString("mensagem"));
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

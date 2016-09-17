package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.AmizadeService;

@Controller
@RequestMapping(value = "/friendsPage")
public class FriendsPageController {
	
	@Autowired
	AmizadeService amizadeService;

	@RequestMapping(method = RequestMethod.GET)
	public String telaAmigosInicial() {
		return "friendsPage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String telaAmigos(@RequestBody String request) {
		JSONObject json = new JSONObject(request);
		try{
		if(!json.isNull("idUsuario") && !json.isNull("tipoUsuario")){
			return amizadeService.montarListaAmigos(json.getString("idUsuario"));
		}else{
			return mensagemErro("Dados Necessários indisponíveis!");
		}
		}catch(Exception e){
			return mensagemErro("Erro ao acessar página de perfil");
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

}

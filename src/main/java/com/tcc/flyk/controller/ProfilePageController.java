package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.ProfilePageService;
import com.tcc.flyk.util.TipoCadastroEnumUtil;

@Controller
@RequestMapping(value = "/profilePage")
public class ProfilePageController {
	
	@Autowired
	private ProfilePageService profilePageService;
	
	@Resource
	private TipoCadastroEnumUtil tipoCadastroEnumUtil;
	
	@RequestMapping(method = RequestMethod.GET)
	public String telaPerfilInicial() {

		return "profilePage";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String telaPerfil(@RequestBody String request) {
		JSONObject json = new JSONObject(request);
		try{
		if(!json.isNull("idUsuario") && !json.isNull("tipoUsuario")){
			return profilePageService.montarDadosPerfil(json.getString("idUsuario"), tipoCadastroEnumUtil.definirTipoCadastro(json.getInt("tipoUsuario")));
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

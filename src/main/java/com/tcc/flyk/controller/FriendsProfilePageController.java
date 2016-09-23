package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.FriendsProfilePageService;
import com.tcc.flyk.util.TipoCadastroEnumUtil;

@Controller
@RequestMapping(value = "/friendsProfilePage")
public class FriendsProfilePageController {

	@Autowired
	private FriendsProfilePageService friendsProfilePageService;

	@Resource
	private TipoCadastroEnumUtil tipoCadastroEnumUtil;

	@RequestMapping(method = RequestMethod.GET)
	public String paginaPerfilAmigoInicial() {
		return "friendsProfilePage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String paginaPerfilAmigo(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idUsuarioLogado") && !jsonRequest.isNull("idAmigo") && !jsonRequest.isNull("tipoUsuarioAmigo")) {
				return friendsProfilePageService.montarDadosPerfilAmigo(jsonRequest.getString("idUsuarioLogado"),jsonRequest.getString("idAmigo"),tipoCadastroEnumUtil.definirTipoCadastro(jsonRequest.getInt("tipoUsuarioAmigo")));
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

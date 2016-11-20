package com.tcc.flyk.controller;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.service.PromocaoService;
import com.tcc.flyk.util.PromocaoUtil;

@Controller

public class CadastroPromocaoController {
	
	@Resource 
	private PromocaoUtil promocaoUtil;

	@Autowired
	private PromocaoService promService;

	@RequestMapping(value = "/confirmaPromocao", method = RequestMethod.GET)
	public String abreConfirmaPromocao() {
		return "confirmaPromocao";
	}

	@RequestMapping(value = "/cadastroPromocao", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String CadastrarNovaPromocao(@RequestBody String prom, ModelMap model) {

		JSONObject promJson = new JSONObject(prom);

		return promJson.toString();

	}

	@RequestMapping(value = "/confirmaPromocao", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = "application/json")
	public @ResponseBody String ConfirmarCadastroPromocao(@RequestBody String prom, ModelMap model) {

		boolean retorno = true;
		retorno = promService.cadastrarPromocao(promocaoUtil.convertJSON(prom));

		JSONObject msg = new JSONObject();

		if (retorno) {

			msg.put("sucesso", "Ok");
			msg.put("msgSucesso", "Cadastro efetuado com sucesso!");

		} else {
			msg.put("error", "erro");
		}

		return msg.toString();

	}

	@RequestMapping(value = "/adminPagePromocoes", method = RequestMethod.POST)
	public @ResponseBody String telaPromocoes(@RequestBody String request) {
		try {
			List<Promocao> listaPromocao = promService.consultarTodasPromocoes();
			return mensagemSucesso(listaPromocao);
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar página de perfil");
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}
	
	private String mensagemSucesso(List<Promocao> listaPromocao) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("listaPromocao", promocaoUtil.listaPromocaoJSON(listaPromocao));
		return jObjt.toString();
	}

}

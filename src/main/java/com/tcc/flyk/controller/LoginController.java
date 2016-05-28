package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.form.EfetuarLoginForm;
import com.tcc.flyk.service.EfetuarLoginService;
import com.tcc.flyk.util.EfetuarLoginUtil;

@Controller
public class LoginController {

	@Autowired
	private EfetuarLoginService service;

	@Resource
	private EfetuarLoginUtil util;

	@RequestMapping(value = "/efetuarLogin", method = RequestMethod.POST)
	public @ResponseBody String efetuarLogin(@RequestBody String request) {
		try {
			EfetuarLoginForm form = util.convertJSONToForm(request);
			int autorizado = 0;
			if (form != null) {
				autorizado = service.efetuarLogin(form);
			}
			switch (autorizado) {
				case 1: return mensagemSucesso();
				case 2: return mensagemErro("Usuário inativo!");
				case 3: return mensagemErro("Usuário ou senha incorreto!");
				case 4: return mensagemErro("Erro no processamento!");
				case 5: return mensagemErro("Usuário ou senha incorreto!");
				default: return mensagemErro("Erro no processamento!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro no processamento!");
		}
	}

	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

	private String mensagemSucesso() {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		return jObjt.toString();
	}

}

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
import com.tcc.flyk.persistence.banco;
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
			String mensagem = "";
			if (form != null) {
				mensagem = service.efetuarLogin(form);
				
			}
			return mensagem;
		} catch (Exception e) {
			return mensagemErro("Erro no processamento!" + e.getLocalizedMessage() + e.getMessage() + e.getClass() + e.getStackTrace());
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

	

}

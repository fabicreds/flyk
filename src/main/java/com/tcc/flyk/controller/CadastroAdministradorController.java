package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.CadastroAdminstradorService;
import com.tcc.flyk.util.AdministradorUtil;

@Controller
@RequestMapping(value = "/cadastroAdministrador")
public class CadastroAdministradorController {
	
	@Autowired
	private CadastroAdminstradorService cadastroService;
	
	@Resource
	private AdministradorUtil util;

	@RequestMapping(method  = RequestMethod.POST )
	public @ResponseBody String CadastrarNovoAdministrador(@RequestBody String adm){
		boolean retorno = true;
		
		retorno = cadastroService.CadastrarNovoAdministrador(util.convertJSON(adm));
		
		if(retorno){
			JSONObject jObjt = new JSONObject();
			jObjt.put("retorno", "sucesso");
			jObjt.put("mensagem", "Usuário cadastrado com sucesso");
			return jObjt.toString();
		}else{
			JSONObject jObjt = new JSONObject();
			jObjt.put("retorno", "erro");
			jObjt.put("mensagem", "Usuário já cadastrado");
			return jObjt.toString();
		}
	}
	
	
}

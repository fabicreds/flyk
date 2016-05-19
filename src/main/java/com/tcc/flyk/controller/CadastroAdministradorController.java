package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.service.CadastroAdminstradorService;

@Controller
@RequestMapping(value = "/cadastroAdministrador")
public class CadastroAdministradorController {
	
	@Autowired
	private CadastroAdminstradorService cadastroService;	

	@RequestMapping(method  = RequestMethod.POST )
	public String CadastrarNovoAdministrador(@RequestBody String adm){
		boolean retorno = true;
		
		retorno = cadastroService.CadastrarNovoAdministrador(convertJSON(adm));
		
		if(retorno){
			
			return "adminPage";
		}else{
			return "home";
		}
	}
	
	private Administrador convertJSON(String adm){
		Administrador admin = new Administrador();
		JSONObject jObjt = new JSONObject(adm);
		try{
			admin.setNome(jObjt.getString("nome"));
			admin.setUsuario(jObjt.getString("usuario"));
			admin.setSenha(jObjt.getString("senha"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return admin;
	}
}

package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.service.CadastroAdminstradorService;

@Controller
@RequestMapping(value = "/cadastroAdministrador")
public class CadastroAdministradorController {
	
	private CadastroAdminstradorService cadastroService;	

	@RequestMapping(method  = RequestMethod.POST )
	public String CadastrarNovoAdministrador(@ModelAttribute Administrador adm){
		boolean retorno = true;
		
		retorno = cadastroService.CadastrarNovoAdministrador(adm);
		
		if(retorno){
			return "adminPage";
		}else{
			return "home";
		}
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public String teste(){
		System.out.println("TESTE");
		return "adminPage";
	}
}

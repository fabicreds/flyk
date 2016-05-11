package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CadastroController {
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String iniciarTelaCadastro() {

		return "cadastro";
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String realizarCadastro() {
		System.out.println("Cadastro de usu�rio");
		return "cadastro";
	}

}

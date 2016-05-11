package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cadastro")
public class CadastroController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String iniciarTelaCadastro() {

		return "cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String realizarCadastro() {
		System.out.println("Cadastro de usuário");
		return "cadastro";
	}

}

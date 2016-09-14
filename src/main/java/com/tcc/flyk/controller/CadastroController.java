package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/cadastro")
public class CadastroController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String iniciarTelaCadastro() {

		return "cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String realizarCadastro(@RequestBody String cliente) {
		System.out.println("Cadastro de usuário");
		return "cadastro";
	}

}

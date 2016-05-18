package com.tcc.flyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tcc.flyk.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String telaPrincipal() {

		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String iniciarTelaLogin() {

		return "login";
	}
	
	@RequestMapping(value = "/cadastro")
	public String iniciarTelaCadastro() {

		return "cadastro";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String telaHome() {

		return "home";
	}



}

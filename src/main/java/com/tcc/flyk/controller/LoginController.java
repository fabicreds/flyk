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
	public String iniciarTelaLogin() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String efetuarLogin(@RequestParam String usuario, @RequestParam String senha, ModelMap model) {
		if (!loginService.validateUser(usuario, senha)) {
			model.addAttribute("errorMessage", "Usuario ou senha invalidos!");
			return "login";
		}

		model.addAttribute("usuario", usuario);
		return "perfil";
	}

	@RequestMapping(value = "/resgatarSenha", method = RequestMethod.POST)
	public String resgatarSenha(@RequestParam String usuario, ModelMap model) {
		model.addAttribute("errorMessage", "Resgatar senha!");
		return "login";
	}

}

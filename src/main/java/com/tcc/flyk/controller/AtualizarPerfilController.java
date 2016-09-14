package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/profilePageEdit")
public class AtualizarPerfilController {
	
		@RequestMapping(method = RequestMethod.GET)
		public String iniciarTelaCadastro() {
			return "profilePageEdit";
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public String realizarCadastro() {
			return "cadastro";
		}


}

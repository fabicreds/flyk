package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/servicosContratados")
public class ServicosContratadosController {

	@RequestMapping(method = RequestMethod.GET)
	public String telaServicosContratadosInicial() {
		return "servicosContratados";
	}
}

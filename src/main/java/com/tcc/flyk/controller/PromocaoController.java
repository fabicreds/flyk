package com.tcc.flyk.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.service.PromocaoService;
import com.tcc.flyk.entity.enumerator.*;

@Controller
public class PromocaoController {
	
	@Autowired
	PromocaoService promocaoService;

	@RequestMapping(value = "/promocao", method = RequestMethod.GET)
	public String iniciarTelaLogin(ModelMap model) {
		

		
		     model.addAttribute("catList", CategoriaServicoEnum.values());
		     
	     

		return "promocao";
	}
	
	@RequestMapping(value = "/promocao", method = RequestMethod.POST)
	public String exibirPromocao(@RequestParam String nome, @RequestParam float valorPromocional, ModelMap model) {
		
		//TODO: Pesquisar como passar o objeto para sessao

		model.addAttribute("nomePromocao", nome);
		model.addAttribute("valorPromocional", valorPromocional);
		
		Promocao prom = new Promocao();		
		
	     prom.setNomePromocao(nome);
	     prom.setValorPromocional(valorPromocional);	     
	     promocaoService.cadastrarPromocao(prom);

	     
	    
		

		return "promocao";
	}


}

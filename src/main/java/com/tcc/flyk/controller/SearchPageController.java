package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.service.SearchService;

@Controller
//@RequestMapping(value = "/searchPage")


public class SearchPageController {
	
	@Autowired
	private SearchService searchService = new SearchService();
	
	
	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
	public String telaBuscaInicial() {
		return "searchPage";
	}
	
	@RequestMapping(value = "/efetuarBusca", method = RequestMethod.POST, consumes = {
	"application/json;charset=UTF-8" }, produces = "application/json")
	public @ResponseBody String realizaBusca(@RequestBody String busca) {
		JSONObject jsonArrayCli = new JSONObject();
		
		JSONObject JSONBusca = new JSONObject(busca);
		
		String teste = JSONBusca.getString("valorBusca");
		System.out.println(teste);
		
		jsonArrayCli=searchService.efetuaBusca(teste);
		System.out.println("retorno sera" + jsonArrayCli.toString());

		return jsonArrayCli.toString();
	}

}

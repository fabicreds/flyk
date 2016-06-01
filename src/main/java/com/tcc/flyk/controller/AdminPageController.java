package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

@Controller
public class AdminPageController {
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String iniciarTelaAdmin(ModelMap model) {
		
		
	     model.addAttribute("catList", CategoriaServicoEnum.values());

		return "adminPage";
	}
	@RequestMapping(value = "/getValueCatList", method = RequestMethod.GET)
	public @ResponseBody String preencherListaCat(@RequestBody String prom, ModelMap model) {
		
		
		JSONObject listaCat = new JSONObject();
		listaCat.put("categoria", "Manicure");
		listaCat.put("cat", "Cabeleireiro");

		return listaCat.toString();
	}


}

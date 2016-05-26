package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;

@Controller
public class AdminPageController {
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String iniciarTelaAdmin(ModelMap model) {
	     model.addAttribute("catList", CategoriaServicoEnum.values());

		return "adminPage";
	}
	


}

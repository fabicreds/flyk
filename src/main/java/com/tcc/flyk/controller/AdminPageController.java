package com.tcc.flyk.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.tcc.flyk.entity.enumerator.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminPageController {
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String iniciarTelaAdmin(ModelMap model) {
	     model.addAttribute("catList", CategoriaServicoEnum.values());

		return "adminPage";
	}
	


}

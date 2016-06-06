package com.tcc.flyk.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
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

		ArrayList<JSONObject> listaCat = new <JSONObject>ArrayList();

		
		   //
		
		
		JSONObject listaCatJson = new JSONObject();
		    
		listaCatJson.put("id", 1);
		listaCatJson.put("nome", "Fotografia");
		
		listaCat.add(listaCatJson);
		
		JSONObject listaCatJson2 = new JSONObject();
	    
		listaCatJson2.put("id", 2);
		listaCatJson2.put("nome", "Manicure");
		   
		listaCat.add(listaCatJson2);

		    
		  //  catList.add(listaCat);
		
		  JSONArray jsonAraay = new JSONArray();
		  
		  

		  jsonAraay.put(listaCatJson2);
		  jsonAraay.put(listaCatJson);

		
        
		return jsonAraay.toString();
	}


}

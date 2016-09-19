package com.tcc.flyk.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.service.CategoriaService;

@Controller
public class AdminPageController {

	private CategoriaService categService = new CategoriaService();

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String iniciarTelaAdmin(ModelMap model) {
		return "adminPage";
	}

	@RequestMapping(value = "/getValueCatList", method = RequestMethod.GET)
	public @ResponseBody String preencherListaCat(@RequestBody String prom, ModelMap model) {

		JSONArray arrayCatJson = new JSONArray();

		List<Categoria> listaCateg = categService.consultarTodasCategorias();

		for (int i = 0; i < listaCateg.size(); i++) {
			JSONObject listaCatJson = new JSONObject();
			listaCatJson.put("id", i);
			listaCatJson.put("nome", listaCateg.get(i).getNomeCategoria());
			arrayCatJson.put(listaCatJson);
		}

		JSONObject listaCatJson = new JSONObject();

		listaCatJson.put("id", 1);
		listaCatJson.put("nome", "Fotografia");

		// listaCat.add(listaCatJson);

		JSONObject listaCatJson2 = new JSONObject();

		listaCatJson2.put("id", 2);
		listaCatJson2.put("nome", "Manicure");

		// listaCat.add(listaCatJson2);

		// arrayCatJson.put(listaCatJson2);
		// arrayCatJson.put(listaCatJson);

		return arrayCatJson.toString();
	}

}

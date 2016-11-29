package com.tcc.flyk.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.service.SearchService;

@Controller
// @RequestMapping(value = "/searchPage")

public class SearchPageController {

	@Autowired
	private SearchService searchService = new SearchService();

	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
	public String telaBuscaInicial() {
		return "searchPage";
	}

	@RequestMapping(value = "/efetuarBusca", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = "application/json")
	public @ResponseBody String realizaBusca(@RequestBody String JSONParametrosBusca) {

		System.out.println(" JSON BUSCA" + JSONParametrosBusca);

		JSONObject jsonArrayClientes = new JSONObject();

		JSONObject JSONBusca = new JSONObject(JSONParametrosBusca);

		String stringParametroBusca, cidade;

		if (!JSONBusca.isNull("cidade")) {

			cidade = JSONBusca.getString("cidade");
		} else {
			cidade = "";

		}

		if (!JSONBusca.isNull("stringBusca")) {

			stringParametroBusca = JSONBusca.getString("stringBusca");
		} else
			stringParametroBusca = "";

		/*
		 * Verificar se campo idCateg é nulo Se sim, ou seja, se o usuario não
		 * selecionou nenhuma categoria, buscar por Cliente.
		 */
		if (JSONBusca.isNull("idCateg")) {
			jsonArrayClientes = searchService.efetuaBusca(stringParametroBusca);
		}
		/*
		 * Obter lista de categorias, buscar prestadores da categoria informada
		 */
		else {

			// JSONObject listaCategoria = new JSONObject("idCateg");
			JSONArray listaCategoria = new JSONArray(JSONBusca.getString("idCateg"));

			List<String> listaCategorias = new ArrayList<String>();

			for (int i = 0; i < listaCategoria.length(); i++) {
				JSONObject jsonCategoria = listaCategoria.getJSONObject(i);
				String idCategoria = jsonCategoria.getString("id");
				Categoria categoria = new Categoria();
				categoria.setId(idCategoria);
				listaCategorias.add(idCategoria);
			}

			// jsonArrayClientes = searchService.efetuaBusca(listaCategorias, 1,
			// stringParametroBusca);

			List<Prestador> listaPrestadores = new ArrayList<Prestador>();

			System.out.println(cidade);

			jsonArrayClientes = searchService.buscaServico(listaCategorias, 1, stringParametroBusca, cidade);
		}

		return jsonArrayClientes.toString();
	}

}

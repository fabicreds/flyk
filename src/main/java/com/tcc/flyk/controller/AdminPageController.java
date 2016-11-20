package com.tcc.flyk.controller;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.service.CategoriaService;
import com.tcc.flyk.util.CategoriaUtil;

@Controller
public class AdminPageController {

	@Autowired
	private CategoriaService categService;
	
	@Resource
	private CategoriaUtil categoriaUtil;

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String iniciarTelaAdmin(ModelMap model) {
		return "adminPage";
	}

	@RequestMapping(value = "/getValueCatList", method = RequestMethod.GET)
	public @ResponseBody String preencherListaCat(@RequestBody String prom, ModelMap model) {

		List<Categoria> listaCateg = categService.consultarTodasCategoriasAtivas();

		return mensagemSucesso(listaCateg);
	}

	@RequestMapping(value = "/adminPageCategorias", method = RequestMethod.GET)
	public String adminPageCategorias(ModelMap model) {
		return "adminPageCategorias";
	}

	@RequestMapping(value = "/adminPagePromocoes", method = RequestMethod.GET)
	public String adminPagePromocoes(ModelMap model) {
		return "adminPagePromocoes";
	}

	@RequestMapping(value = "/adminPageCategorias", method = RequestMethod.POST)
	public @ResponseBody String telaCategorias(@RequestBody String request) {
		try {
			List<Categoria> listaCateg = categService.consultarTodasCategorias();
			return mensagemSucesso(listaCateg);
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar página de perfil");
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}
	
	private String mensagemSucesso(List<Categoria> listaCateg) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("listaCategoria", categoriaUtil.listaCategoriaJSON(listaCateg));
		return jObjt.toString();
	}
}

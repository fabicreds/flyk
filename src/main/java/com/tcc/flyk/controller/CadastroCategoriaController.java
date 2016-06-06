package com.tcc.flyk.controller;


import java.util.Date;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.persistence.banco;
import com.tcc.flyk.service.CategoriaService;

@Controller
@RequestMapping(value = "/cadastroServicos")
public class CadastroCategoriaController {

	@RequestMapping(method  = RequestMethod.POST )
	public @ResponseBody String CadastrarNovaCategoria(@RequestBody String JSONN){
		JSONObject objeto = new JSONObject(JSONN);
		
		
		//Instancia uma nova categoria
		Categoria novaCategoria = new Categoria();
		novaCategoria.setNome_categoria(objeto.getString("nome"));
		novaCategoria.setDescricao_categoria(objeto.getString("descricao"));

		JSONObject ret = new JSONObject();
		CategoriaService servico = new CategoriaService();
		if(servico.CadastrarNovaCategoria(novaCategoria)){
			ret.put("retorno", "sucesso");
			ret.put("mensagem", "Categoria cadastrada com sucesso");
		}else{
			ret.put("retorno", "erro");
			ret.put("mensagem", "Categoria não foi gravada");
		}
		
		//Retorna para o .js
		return ret.toString();
	}
}

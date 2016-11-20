package com.tcc.flyk.controller;


import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.service.CategoriaService;
//import static java.util.Arrays.asList;
//import java.util.List;//CLASSE DE TESTE
import com.tcc.flyk.util.CategoriaUtil;

@Controller
public class CadastroCategoriaController {
	
	@Autowired
	private CategoriaService categService;
	
	@Resource
	private CategoriaUtil categoriaUtil;

	@RequestMapping(value = "/cadastroServicos", method  = RequestMethod.POST )
	public @ResponseBody String cadastrarNovaCategoria(@RequestBody String JSONN){
		JSONObject objeto = new JSONObject(JSONN);
		
		
		//Instancia uma nova categoria
		Categoria novaCategoria = new Categoria();
		novaCategoria.setNomeCategoria(objeto.getString("nome"));
		novaCategoria.setDescricaoCategoria(objeto.getString("descricao"));

		JSONObject ret = new JSONObject();
		CategoriaService servico = new CategoriaService();
		
		/*List<Categoria> teste = servico.ConsultarTodasCategorias();
		for(int i=0;i<teste.size();i++){
			System.out.println(teste.get(i).getNomeCategoria());
		}
		
		if(1==1){
		//*/if(servico.cadastrarNovaCategoria(novaCategoria)){
			ret.put("retorno", "sucesso");
			ret.put("mensagem", "Categoria cadastrada com sucesso");
		}else{
			ret.put("retorno", "erro");
			ret.put("mensagem", "Categoria nï¿½o foi gravada");
		}
		
		//Retorna para o .js
		return ret.toString();
	}
	
	@RequestMapping(value = "/atualizarCategoria", method = RequestMethod.POST)
	public @ResponseBody String telaCategorias(@RequestBody String request) {
		JSONObject json = new JSONObject(request);
		try {
			if(!json.isNull("idCategoria") && !json.isNull("acao")){
				List<Categoria> listaCateg = categService.atualizarCategoria(json.getString("idCategoria"), json.getInt("acao"));
				return mensagemSucesso(listaCateg);
			}else{
				return mensagemErro("Erro ao acessar atualizar a Categoria de Serviço");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar atualizar a Categoria de Serviço");
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

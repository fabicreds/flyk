package com.tcc.flyk.controller;


import org.json.JSONObject;
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

@Controller
@RequestMapping(value = "/cadastroServicos")
public class CadastroCategoriaController {

	@RequestMapping(method  = RequestMethod.POST )
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
			ret.put("mensagem", "Categoria n�o foi gravada");
		}
		
		//Retorna para o .js
		return ret.toString();
	}
}

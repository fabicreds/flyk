package com.tcc.flyk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;

import com.tcc.flyk.entity.Preco;
import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.service.PromocaoService;

@Controller

public class CadastroPromocaoController {


	@Autowired
	private PromocaoService cadastroPromService;	
	
	@RequestMapping(value = "/confirmaPromocao", method = RequestMethod.GET)
	public String abreConfirmaPromocao() {
		return "confirmaPromocao";
	}


	@RequestMapping(value = "/cadastroPromocao", method  = RequestMethod.POST , produces="application/json")
		public @ResponseBody String CadastrarNovaPromocao(@RequestBody String prom, ModelMap model) {

			JSONObject promJson = new JSONObject(prom);		
		
		
		return promJson.toString();

	}
	
	@RequestMapping(value = "/confirmaPromocao", method  = RequestMethod.POST ,consumes = {"application/json;charset=UTF-8"}, produces="application/json")
	public @ResponseBody String ConfirmarCadastroPromocao(@RequestBody String prom, ModelMap model) {

        boolean retorno = true;	
		retorno = cadastroPromService.cadastrarPromocao((convertJSON(prom)));
    
		JSONObject msg = new JSONObject();

		if(retorno){
			
			msg.put("sucesso", "Ok");
			msg.put("msgSucesso", "Cadastro efetuado com sucesso!");
			
			
			
		}else{
			msg.put("error", "erro");
		}
		 Promocao pr   = convertJSON(prom);
		//return msg.toString();
		
		//msg.put("sucesso", pr.getListaPreco().get(2).getValor());
		return msg.toString();
		
	}
	
	private Promocao convertJSON(String prom){
		Promocao promo = new Promocao();
		Preco preco = new Preco();
		JSONObject jObjt = new JSONObject(prom);
		 ArrayList<Preco> arrayPrecoObj = new ArrayList<Preco>();

		try{

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date dataInicio = formato.parse(jObjt.getString("dataini"));
				Date dataFim = formato.parse(jObjt.getString("datafim"));
				promo.setNomePromocao(jObjt.getString("nomeprom"));
				promo.setDescricao(jObjt.getString("descrprom"));
				promo.setDataFim(dataFim);
				promo.setDataInicio(dataInicio);
				
				JSONObject  arrayPreco = new JSONObject(prom);
				
				
				String cat = arrayPreco.getString("listJson");
				
				 JSONArray jsonArr = new JSONArray(cat);
				 JSONObject jsonObj = new JSONObject();
				
			        for (int i = 0; i < jsonArr.length(); i++)
			        {
			            jsonObj = jsonArr.getJSONObject(i);
			            Preco p = new Preco();
			            p.setCategoria(jsonObj.getString("nome"));
			            Float val = Float.parseFloat(jsonObj.getString("valorpromocional"));
			            p.setValor(val);    	
			            arrayPrecoObj.add(p);		           
			        }
			        
				

			        
			        
			        promo.setListaPreco(arrayPrecoObj);
			        
			        
				
						
				
				
		

				

		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return promo;
	}

}

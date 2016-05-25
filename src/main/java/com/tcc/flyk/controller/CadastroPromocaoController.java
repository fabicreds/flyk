package com.tcc.flyk.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;
import com.tcc.flyk.service.PromocaoService;

@Controller
@RequestMapping(value = "/cadastroPromocao")

public class CadastroPromocaoController {


	@Autowired
	private PromocaoService cadastroPromService;	

	@RequestMapping(method  = RequestMethod.POST )
	public String CadastrarNovaPromocao(@RequestBody String prom){
		boolean retorno = true;
	     

		retorno = cadastroPromService.cadastrarPromocao((convertJSON(prom)));
		
		if(retorno){
			
			return "adminPage";
			
		}else{
			return "home";
		}
	}
	
	private Promocao convertJSON(String prom){
		Promocao promo = new Promocao();
		JSONObject jObjt = new JSONObject(prom);
		try{

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date dataInicio = formato.parse(jObjt.getString("dataini"));
				Date dataFim = formato.parse(jObjt.getString("datafim"));
				float valorProm = Float.parseFloat(jObjt.getString("valorpromocional"));
			promo.setNomePromocao(jObjt.getString("nomeprom"));
			promo.setDescricao(jObjt.getString("descrprom"));
			promo.setValorPromocional(valorProm);
			promo.setDataFim(dataFim);
			promo.setDataInicio(dataInicio);

			//Retornar json com msg de erro e validar.
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return promo;
	}
	

}

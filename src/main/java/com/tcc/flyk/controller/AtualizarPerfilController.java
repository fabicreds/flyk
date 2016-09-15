package com.tcc.flyk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.service.AtualizaPerfilService;
import com.tcc.flyk.util.ClienteUtil;

@Controller
public class AtualizarPerfilController {
	
	@Autowired
	private AtualizaPerfilService atualizaPerfilService = new AtualizaPerfilService();

	@RequestMapping(value = "/profilePageEdit", method = RequestMethod.GET)
		public String iniciarTelaCadastro() {

			return "profilePageEdit";
		}
		
		
		@RequestMapping(value = "/atualizarPerfil", method = RequestMethod.POST,consumes = {"application/json;charset=UTF-8"}, produces="application/json")
		public @ResponseBody String PreenchePerfil(@RequestBody String perfil) {
	        
			JSONObject dadosPerfil = new JSONObject(perfil);
		
			Cliente cli = new Cliente();
			
			ClienteUtil util = new ClienteUtil();
			
			cli = atualizaPerfilService.atualizaPerfil(dadosPerfil.getJSONObject("cliente").getString("id"), dadosPerfil);		
		
			
			dadosPerfil.put("cliente" , util.toJSON(cli));			
			
			Cliente cliAtualizado = new Cliente();
			
			cliAtualizado = util.toCliente(dadosPerfil);
			
			JSONObject jsonCliente = new JSONObject();
			
			jsonCliente.put("cliente", util.toJSON(cliAtualizado));
			
			return jsonCliente.toString();
			

		}


}

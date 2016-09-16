package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.service.ClienteService;
import com.tcc.flyk.util.ClienteUtil;

@Controller
@RequestMapping(value = "/cadastroCliente")
public class CadastroClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@Resource
	private ClienteUtil clienteUtil;

	@RequestMapping(method  = RequestMethod.POST )
	public @ResponseBody String CadastrarNovoCliente(@RequestBody String JSONN){
		JSONObject objeto = new JSONObject(JSONN);
		
		//Instancia um novo cliente
		Cliente novoCliente = clienteUtil.toCliente(objeto);

		//Instancia o retorno
		JSONObject ret = new JSONObject();
		
		if(servico.cadastrarNovoCliente(novoCliente)){
			ret.put("retorno", "sucesso");
			ret.put("mensagem", "Cliente cadastrado com sucesso!");
		}else{
			ret.put("retorno", "erro");
			ret.put("mensagem", "Cliente não cadastrado.");
		}
		
		//Retorna para o .js
		return ret.toString();
	}
}

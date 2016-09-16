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
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.service.ClienteService;
import com.tcc.flyk.util.ClienteUtil;

@Controller
@RequestMapping(value = "/cadastroCliente")
public class CadastroClienteController {

	@Autowired
	private ClienteService servico;

	@Resource
	private ClienteUtil clienteUtil;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String CadastrarNovoCliente(@RequestBody String JSONN) {
		JSONObject objeto = new JSONObject(JSONN);

		// Instancia um novo cliente
		Cliente novoCliente = clienteUtil.toCliente(objeto);
		String retorno = null;
		// Instancia o retorno
		JSONObject ret = new JSONObject();
		try {
			if (!objeto.isNull("indicadorPrestador")) {
				if (objeto.getBoolean("indicadorPrestador")) {
					novoCliente.setStatus("A");
					if (!objeto.isNull("tipoPrestador") && objeto.getString("tipoPrestador").equals("free")) {
						novoCliente.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
					} else {
						novoCliente.setTipoCadastro(TipoCadastroEnum.PREMIUM);
					}
					retorno = servico.cadastrarNovoCliente(novoCliente);
				} else {
					novoCliente.setStatus("A");
					novoCliente.setTipoCadastro(TipoCadastroEnum.CLIENTE);
					retorno = servico.cadastrarNovoCliente(novoCliente);
				}
			}

			// caso n�o ocorra erro no processamento, o retorno � nulo
			if (retorno == null) {
				ret.put("retorno", "sucesso");
				ret.put("mensagem", "Cliente cadastrado com sucesso!");
			} else {
				ret.put("retorno", "erro");
				ret.put("mensagem", retorno);
			}
		} catch (Exception e) {
			ret.put("retorno", "erro");
			ret.put("mensagem", "Cliente n�o cadastrado.");
			return ret.toString();
		}
		// Retorna para o .js
		return ret.toString();
	}
}

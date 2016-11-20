package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.service.ClienteService;
import com.tcc.flyk.service.PrestadorService;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Controller
public class CadastroClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PrestadorService prestadorService;

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@RequestMapping(value = "/cadastroCliente", method = RequestMethod.GET)
	public String iniciarTelaCadastro() {
		return "cadastro";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String iniciarTelaCadastroCliente() {
		return "cadastro";
	}
	

	@RequestMapping(value = "/cadastroCliente", method = RequestMethod.POST)
	public @ResponseBody String cadastrarNovoCliente(@RequestBody String JSONN) {
		JSONObject objeto = new JSONObject(JSONN);
		JSONObject perfilPrestador = new JSONObject();
		if (!objeto.isNull("prestador")) {
			perfilPrestador = (JSONObject) objeto.get("prestador");
		}
		String retorno = null;
		// Instancia o retorno
		JSONObject ret = new JSONObject();
		try {
			if (!perfilPrestador.isNull("flag")) {
				if (perfilPrestador.getBoolean("flag")) {
					// Instancia um novo prestador
					Prestador novoPrestador = prestadorUtil.toPrestador(objeto);

					novoPrestador.setStatus("A");

					if (!perfilPrestador.isNull("type") && perfilPrestador.getString("type").equals("free")) {
						novoPrestador.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
					} else {
						novoPrestador.setTipoCadastro(TipoCadastroEnum.PREMIUM);
					}
					retorno = prestadorService.cadastrarNovoPrestador(novoPrestador);
				}
			}else {// Instancia um novo cliente
				Cliente novoCliente = clienteUtil.toCliente(objeto);
				novoCliente.setStatus("A");
				novoCliente.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				Privacidade privacidade = new Privacidade ();
				privacidade.setExibeCPF(2);
				privacidade.setExibeAgenda(2);
				privacidade.setExibeEndereco(2);
				privacidade.setExibeTelefone(2);
				novoCliente.setPrivacidade(privacidade);
				
				retorno = clienteService.cadastrarNovoCliente(novoCliente);
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
			ret.put("mensagem", "Cliente n�o cadastrado." + e.toString());
			return ret.toString();
		}
		// Retorna para o .js
		return ret.toString();
	}

	@RequestMapping(value = "/consultaCategoriaCadastradasCadastro", method = RequestMethod.POST)
	public @ResponseBody String consultaCategoriaCadastradasCadastro() {
		JSONObject ret = new JSONObject();
		try {
			JSONArray listaCategorias = prestadorService.consultaCategoriaCadastradasCadastro();
			ret.put("retorno", "sucesso");
			ret.put("listaCategorias", listaCategorias);
		} catch (Exception e) {
			ret.put("retorno", "erro");
		}
		return ret.toString();
	}
}

package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.service.ContratarServicoService;
import com.tcc.flyk.util.CompromissoUtil;

@Controller
public class ContratarServicoController {

	@Resource
	private CompromissoUtil compromissoUtil;
	
	@Autowired
	private ContratarServicoService service;
	
	@RequestMapping(value = "/contratarServico", method = RequestMethod.GET)
	public String carregarTelaContratarServico() {
		return "contratarServico";
	}

	@RequestMapping(value = "/contratarServico", method = RequestMethod.POST)
	public @ResponseBody String contratarServico(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);

		try {
			if (!jsonRequest.isNull("idCliente") && !jsonRequest.isNull("idPrestador") && !jsonRequest.isNull("idCategoriaServico")) {
				Compromisso compromisso = compromissoUtil.toCompromisso(jsonRequest);
				return service.contratarServico(jsonRequest.getString("idCliente"),jsonRequest.getString("idPrestador"), compromisso);
			} else {
				return mensagemErro("Dados Necessários indisponíveis!");
			}
		} catch (Exception e) {
			return mensagemErro("Erro ao acessar página de perfil do amigo");
		}
	}

	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

}

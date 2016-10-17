package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.AvaliacaoPrestador;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.service.AvaliarServicoService;
import com.tcc.flyk.util.AvaliacaoPrestadorUtil;
import com.tcc.flyk.util.CompromissoUtil;

@Controller
public class AvaliarServicoController {
	
	@Autowired
	private AvaliarServicoService service;
	
	@Resource
	private CompromissoUtil compromissoUtil;
	
	@Resource
	private AvaliacaoPrestadorUtil avaliacaoPrestadorUtil;
	
	@RequestMapping(value = "/avaliarPrestador", method = RequestMethod.GET)
	public String carregarTelaAvaliarPrestador() {
		return "avaliarPrestador";
	}
	
	@RequestMapping(value = "/avaliarCliente", method = RequestMethod.GET)
	public String carregarTelaAvaliarCliente() {
		return "avaliarCliente";
	}
	
	@RequestMapping(value = "/avaliarPrestador", method = RequestMethod.POST)
	public @ResponseBody String avaliarPrestador(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);
		
		try{
			if(!jsonRequest.isNull("idCliente") && !jsonRequest.isNull("idPrestador") && !jsonRequest.isNull("compromisso") && !jsonRequest.isNull("avaliacao")){
				Compromisso compromisso = compromissoUtil.toCompromisso(jsonRequest.getJSONObject("compromisso"));
				AvaliacaoPrestador avaliacao = avaliacaoPrestadorUtil.toAvaliacaoPrestador(jsonRequest.getJSONObject("avaliacao"));
				return service.avaliarPrestador(jsonRequest.getString("idCliente"), jsonRequest.getString("idPrestador"), compromisso, avaliacao);
			}else{
				return mensagemErro("Erro ao Avaliar Prestador");
			}
		}catch(Exception e){
			return mensagemErro("Erro ao Avaliar Prestador");
		}
		
	}
	
	@RequestMapping(value = "/avaliarCliente", method = RequestMethod.POST)
	public @ResponseBody String avaliarCliente(@RequestBody String request) {
		JSONObject jsonRequest = new JSONObject(request);
		try{
			if(!jsonRequest.isNull("idCliente") && !jsonRequest.isNull("idPrestador") && !jsonRequest.isNull("compromisso") && !jsonRequest.isNull("avaliacao")){
				Compromisso compromisso = compromissoUtil.toCompromisso(jsonRequest.getJSONObject("compromisso"));
				return service.avaliarCliente(jsonRequest.getString("idCliente"), jsonRequest.getString("idPrestador"), compromisso, jsonRequest.getInt("avaliacao"));
			}else{
				return mensagemErro("Erro ao Avaliar Cliente");
			}
		}catch(Exception e){
			return mensagemErro("Erro ao Avaliar Cliente");
		}
	}
	
	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}
}

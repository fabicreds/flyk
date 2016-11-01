package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class ConversaService {
	
	@Resource
	private ClienteUtil util;
	
	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	private PrestadorDAO preDAO = new PrestadorDAOImpl();
	
	public String enviarMensagem(String idOrigem, Conversa mensagem) {
		List<Conversa> listaMensagensEnviadasPeloEnviador  =  cliDAO.consultarListaConversa(idOrigem);
		List<Conversa> listaMensagensRecebidasPeloRecebedor = cliDAO.consultarListaConversa(mensagem.getIdUsuario());
		
		//Adiciona a mensagem enviada na lista do usuário origem
		if (listaMensagensEnviadasPeloEnviador == null) {
			listaMensagensEnviadasPeloEnviador = new ArrayList<Conversa>();
		}
		listaMensagensEnviadasPeloEnviador.add(mensagem);
		cliDAO.atualizarListaConversa(idOrigem, listaMensagensEnviadasPeloEnviador);
		
		//Adiciona a mensagem recebida na lista do usuário destino
		if (listaMensagensRecebidasPeloRecebedor == null) {
			listaMensagensRecebidasPeloRecebedor = new ArrayList<Conversa>();
		}
		Conversa mensagemDestino = new Conversa();
		mensagemDestino.setData(mensagem.getData());
		mensagemDestino.setflagEnviadoRecebido("R");
		mensagemDestino.setidUsuario(idOrigem);
		mensagemDestino.setMsg(mensagem.getMsg());
		listaMensagensRecebidasPeloRecebedor.add(mensagemDestino);
		cliDAO.atualizarListaConversa(mensagem.getIdUsuario(), listaMensagensRecebidasPeloRecebedor);

		//return mensagemSucesso(listaMensagensEnviadasPeloEnviador);
		return "";
	}
	
	/*
	private String mensagemSucesso(List<Conversa> listaMensagensEnviadasPeloEnviador) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		if(util!=null){
		jObjt.put("mensagens_de_conversa", util.listaConversaJSON(listaMensagensEnviadasPeloEnviador));
		}
		return jObjt.toString();
	}
	*/
}

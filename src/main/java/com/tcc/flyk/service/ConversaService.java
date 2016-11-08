package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ConversaUtil;

@Service
public class ConversaService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	@Resource
	private ConversaUtil conversaUtil;

	public String enviarMensagem(String idCliente, String idAmigo, String mensagem) {
		List<Conversa> listaMensagensEnviadasPeloEnviador = cliDAO.consultarListaConversa(idCliente);
		List<Conversa> listaMensagensRecebidasPeloRecebedor = cliDAO.consultarListaConversa(idAmigo);

		// CRIAÇÃO DO OBJETO DE CONVERSA - USUARIO LOGADO
		Conversa conversaEnvio = new Conversa();
		conversaEnvio.setidUsuario(idAmigo);
		conversaEnvio.setData(new Date());
		conversaEnvio.setflagEnviadoRecebido("E");
		conversaEnvio.setMsg(mensagem);

		// Adiciona a mensagem enviada na lista do usuário origem
		if (listaMensagensEnviadasPeloEnviador == null) {
			listaMensagensEnviadasPeloEnviador = new ArrayList<Conversa>();
		}
		listaMensagensEnviadasPeloEnviador.add(conversaEnvio);
		cliDAO.atualizarListaConversa(idCliente, listaMensagensEnviadasPeloEnviador);

		// Adiciona a mensagem recebida na lista do usuário destino
		if (listaMensagensRecebidasPeloRecebedor == null) {
			listaMensagensRecebidasPeloRecebedor = new ArrayList<Conversa>();
		}

		// ALTERAÇÃO DO OBJETO DE CONVERSA - AMIGO
		Conversa conversaRecebendo = new Conversa();
		conversaRecebendo.setData(new Date());
		conversaRecebendo.setMsg(mensagem);
		conversaRecebendo.setidUsuario(idCliente);
		conversaRecebendo.setflagEnviadoRecebido("R");

		listaMensagensRecebidasPeloRecebedor.add(conversaRecebendo);
		cliDAO.atualizarListaConversa(idAmigo, listaMensagensRecebidasPeloRecebedor);

		listaMensagensEnviadasPeloEnviador = cliDAO.consultarListaConversaAmigo(idCliente, idAmigo);
		return mensagemSucesso(idAmigo, listaMensagensEnviadasPeloEnviador);
	}

	public String mostrarConversa(String idCliente, String idAmigo) {
		List<Conversa> listaConversa = cliDAO.consultarListaConversaAmigo(idCliente, idAmigo);
		
		return mensagemSucesso(idAmigo, listaConversa);
	}

	private String mensagemSucesso(String idAmigo, List<Conversa> conversas) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("mensagem", "Serviço contrato com sucesso!");
		jObjt.put("amigo", idAmigo);
		jObjt.put("numMensagens", conversas.size());
		jObjt.put("conversaAmigo", conversaUtil.listaMensagensConversaJSON(conversas));
		return jObjt.toString();
	}

}

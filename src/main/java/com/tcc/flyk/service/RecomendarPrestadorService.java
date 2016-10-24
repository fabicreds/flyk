package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class RecomendarPrestadorService {
	
	@Resource
	private ClienteUtil util;
	
	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();
	
	public String recomendarPrestador(String idUsuarioLogado, String idPrestador) {
		List<String> listaRecomendacoesDadas  =  cliDAO.consultarRecomendacoesDadasById(idUsuarioLogado);
		List<String> listaRecomendacoesRecebidas = prestadorDAO.consultarRecomendacoesRecebidasById(idPrestador);
		
		if (listaRecomendacoesDadas != null) {
			//se o prestador já não estiver na lista, é adicionado
			if(!listaRecomendacoesDadas.contains(idPrestador)){
				listaRecomendacoesDadas.add(idPrestador);
				
			}
		}else{
			listaRecomendacoesDadas  = new ArrayList<String>();
			listaRecomendacoesDadas.add(idPrestador);
		}
		cliDAO.atualizarRecomendacaoDadas(idUsuarioLogado, listaRecomendacoesDadas);
		
		if (listaRecomendacoesRecebidas != null) {
			//se o prestador já não estiver na lista, é adicionado
			if(!listaRecomendacoesRecebidas.contains(idUsuarioLogado)){
				listaRecomendacoesRecebidas.add(idUsuarioLogado);
			}
		}else{
			listaRecomendacoesRecebidas  = new ArrayList<String>();
			listaRecomendacoesRecebidas.add(idUsuarioLogado);
		}
		prestadorDAO.atualizarRecomendacaoRecebidas(idPrestador, listaRecomendacoesRecebidas);
		
		return mensagemSucesso(listaRecomendacoesDadas);
	}
	
	private String mensagemSucesso(List<String> listaRecomendacoes) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		if(util!=null){
		jObjt.put("listaAmigos", util.listaPrestadoresRecomendadosJSON(listaRecomendacoes));
		}
		return jObjt.toString();
	}

}

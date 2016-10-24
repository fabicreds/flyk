package com.tcc.flyk.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class FriendsProfilePageService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Autowired
	private ProfileService profileService;

	public String montarDadosPerfilAmigo(String idUsuarioLogado, String idAmigo, TipoCadastroEnum tipoCadastroAmigo) {
		if (tipoCadastroAmigo == TipoCadastroEnum.CLIENTE) {
			Cliente cliente = new Cliente();
			cliente = cliDAO.consultaClientePorId(idAmigo);
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(cliente);
			// buscando os detalhes do servicos contratados
			profileService.buscarDadosContratos(cliente);
			
			profileService.buscarRecomendacoesDadas(cliente);

			// buscarStatusAmizade
			StatusAmizadeEnum statusAmizade = profileService.buscasStatusAmizade(idUsuarioLogado, idAmigo);

			return mensagemSucesso(cliente, statusAmizade);
		} else {
			// busca pelos Dados do Prestador
			Prestador prestador = new Prestador();
			prestador = prestadorDAO.consultaPrestadorPorId(idAmigo);
			// buscando os detalhes dos servicos do prestador
			profileService.buscarListaServicos(prestador);
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(prestador);
			// buscando os detalhes dos servicos contratados pelo prestador
			profileService.buscarDadosContratos(prestador);
			
			profileService.buscarDadosContratosPrestados(prestador);
			
			profileService.buscarRecomendacoesDadas(prestador);
			
			profileService.buscarRecomendacoesRecebidas(prestador);

			// buscarStatusAmizade
			StatusAmizadeEnum statusAmizade = profileService.buscasStatusAmizade(idUsuarioLogado, idAmigo);
			

			return mensagemSucesso(prestador, statusAmizade);
		}

	}

	private String mensagemSucesso(Cliente cliente, StatusAmizadeEnum statusAmizade) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("amigo", clienteUtil.toJSON(cliente));
		jObjt.put("statusAmizade", statusAmizade.getCodigo());
		jObjt.put("statusAmizadeDescricao", statusAmizade.getDescricao());
		return jObjt.toString();
	}

	private String mensagemSucesso(Prestador prestador, StatusAmizadeEnum statusAmizade) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("amigo", prestadorUtil.toJSON(prestador));
		jObjt.put("statusAmizade", statusAmizade.getCodigo());
		jObjt.put("statusAmizadeDescricao", statusAmizade.getDescricao());
		return jObjt.toString();
	}



}

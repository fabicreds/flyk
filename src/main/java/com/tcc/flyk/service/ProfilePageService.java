package com.tcc.flyk.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class ProfilePageService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Autowired
	private ProfileService profileService;

	public String montarDadosPerfil(String id, TipoCadastroEnum tipoCadastro) {

		if (tipoCadastro == TipoCadastroEnum.CLIENTE) {
			Cliente cliente = new Cliente();
			cliente = cliDAO.consultaClientePorId(id);
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(cliente);
			// buscando os detalhes do servicos contratados
			profileService.buscarDadosContratos(cliente);
			
			profileService.buscarRecomendacoesDadas(cliente);

			return mensagemSucesso(cliente);
		} else {
			// busca pelos Dados do Prestador
			Prestador prestador = new Prestador();
			prestador = prestadorDAO.consultaPrestadorPorId(id);
			// buscando os detalhes dos servicos do prestador
			profileService.buscarListaServicos(prestador);
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(prestador);
			// buscando os detalhes dos servicos contratados pelo prestador
			profileService.buscarDadosContratos(prestador);

			profileService.buscarDadosContratosPrestados(prestador);
			
			profileService.buscarRecomendacoesDadas(prestador);
			
			profileService.buscarRecomendacoesRecebidas(prestador);

			return mensagemSucesso(prestador);
		}

	}

	private String mensagemSucesso(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("usuario", cliente.getEmail());
		jObjt.put("tipoCadastro", cliente.getTipoCadastro().getCodigo());
		jObjt.put("tipoCadastroDescricao", cliente.getTipoCadastro().getDescricao());
		jObjt.put("idUsuario", cliente.getId());
		jObjt.put("cliente", clienteUtil.toJSON(cliente));
		return jObjt.toString();
	}

	private String mensagemSucesso(Prestador prestador) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("usuario", prestador.getEmail());
		jObjt.put("tipoCadastro", prestador.getTipoCadastro().getCodigo());
		jObjt.put("tipoCadastroDescricao", prestador.getTipoCadastro().getDescricao());
		jObjt.put("idUsuario", prestador.getId());
		jObjt.put("cliente", prestadorUtil.toJSON(prestador));
		return jObjt.toString();
	}

}

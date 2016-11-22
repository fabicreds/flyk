package com.tcc.flyk.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.enumerator.PrivacidadeEnum;
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

	public String montarDadosPerfil(String idUsuarioLogado, String idUsuarioConsulta, TipoCadastroEnum tipoCadastro) {
		String informacaoPrivada = "Informação privada";
		
		if (tipoCadastro == TipoCadastroEnum.CLIENTE) {
			Cliente cliente = new Cliente();
			cliente = cliDAO.consultaClientePorId(idUsuarioConsulta);

			//Verifica se o usuário logado é amigo do usuário consulta
			boolean flagAmigo = false;
			if (cliente.getListaAmigos() != null && idUsuarioLogado != idUsuarioConsulta) {
				for (Amizade amizade : cliente.getListaAmigos()) {
					if(amizade.getAmigo().getId()==idUsuarioLogado){
						flagAmigo = true;
					}
				}
			}
			
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(cliente);
			
			// buscando os detalhes do servicos contratados
			if(cliente.getPrivacidade().getExibeAgenda()==PrivacidadeEnum.PUBLICO||(cliente.getPrivacidade().getExibeAgenda()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta){
				profileService.buscarDadosContratos(cliente);	
			}

			profileService.buscarRecomendacoesDadas(cliente);

			//Privacidade CPF
			if(!(cliente.getPrivacidade().getExibeCPF()==PrivacidadeEnum.PUBLICO||(cliente.getPrivacidade().getExibeCPF()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				cliente.setCPF(informacaoPrivada);
			}
			
			//Privacidade endereço
			if(!(cliente.getPrivacidade().getExibeEndereco()==PrivacidadeEnum.PUBLICO||(cliente.getPrivacidade().getExibeEndereco()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				cliente.setEndereco(null);
			}
			
			//Privacidade telefone
			if(!(cliente.getPrivacidade().getExibeTelefone()==PrivacidadeEnum.PUBLICO||(cliente.getPrivacidade().getExibeTelefone()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				cliente.setListaTelefone(null);
			}
			
			return mensagemSucesso(cliente);
		} else {
			// busca pelos Dados do Prestador
			Prestador prestador = new Prestador();
			prestador = prestadorDAO.consultaPrestadorPorId(idUsuarioConsulta);
			

			//Verifica se o usuário logado é amigo do usuário consulta
			boolean flagAmigo = false;
			if (prestador.getListaAmigos() != null && idUsuarioLogado != idUsuarioConsulta) {
				for (Amizade amizade : prestador.getListaAmigos()) {
					if(amizade.getAmigo().getId()==idUsuarioLogado){
						flagAmigo = true;
					}
				}
			}
			
			
			// buscando os detalhes dos servicos do prestador
			profileService.buscarListaServicos(prestador);
			// buscando os detalhes dos Amigos
			profileService.buscarDadosAmigos(prestador);
			// buscando os detalhes dos servicos contratados pelo prestador
			if(prestador.getPrivacidade().getExibeAgenda()==PrivacidadeEnum.PUBLICO||(prestador.getPrivacidade().getExibeAgenda()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta){
				profileService.buscarDadosContratos(prestador);	
			}

			profileService.buscarDadosContratosPrestados(prestador);
			
			profileService.buscarRecomendacoesDadas(prestador);
			
			profileService.buscarRecomendacoesRecebidas(prestador);


			//Privacidade CNPJ
			if(!(prestador.getPrivacidade().getExibeCPF()==PrivacidadeEnum.PUBLICO||(prestador.getPrivacidade().getExibeCPF()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				prestador.setCPF("");
				prestador.setCnpj(informacaoPrivada);
			}
			
			//Privacidade endereço
			if(!(prestador.getPrivacidade().getExibeEndereco()==PrivacidadeEnum.PUBLICO||(prestador.getPrivacidade().getExibeEndereco()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				prestador.setEndereco(null);
			}
			
			//Privacidade telefone
			if(!(prestador.getPrivacidade().getExibeTelefone()==PrivacidadeEnum.PUBLICO||(prestador.getPrivacidade().getExibeTelefone()==PrivacidadeEnum.APENAS_AMIGOS && flagAmigo)||idUsuarioLogado==idUsuarioConsulta)){
				prestador.setListaTelefone(null);
			}
			return mensagemSucesso(prestador);
		}

		
		/*
		-privacidade_bloco_cpf_cnpj (1 - Público, 2 - Visível apenas para amigos, 3 - Privado)
		-privacidade_bloco_endereco(1 - Público, 2 - Visível apenas para amigos, 3 - Privado)
		-privacidade_bloco_telefone(1 - Público, 2 - Visível apenas para amigos, 3 - Privado)
		-privacidade_bloco_servicos_contratados(1 - Público, 2 - Visível apenas para amigos, 3 - Privado)
		-privacidade_bloco_servicos_prestados(1 - Público, 2 - Visível apenas para amigos, 3 - Privado)
		*/
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

package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class ManterAmizadeService {

	@Resource
	private ClienteUtil util;

	private ClienteDAOImpl cliDAO = new ClienteDAOImpl();

	public String desfazerAmizade(String idUsuarioLogado, String idAmigo) {
		List<Amizade> listaAmigosUsuarioLogado = cliDAO.consultarAmigosById(idUsuarioLogado);
		List<Amizade> listaAmigosAmigoSelecionado = cliDAO.consultarAmigosById(idAmigo);

		// removendo da lista de amigos, o idAmigo selecionado
		if (listaAmigosUsuarioLogado != null) {
			listaAmigosUsuarioLogado = removerAmigo(listaAmigosUsuarioLogado, idAmigo);
			cliDAO.atualizarListaAmigos(idUsuarioLogado, listaAmigosUsuarioLogado);
		}
		// removendo da lista de amigos do amigo, o id do usuario logado
		if (listaAmigosAmigoSelecionado != null) {
			listaAmigosAmigoSelecionado = removerAmigo(listaAmigosAmigoSelecionado, idUsuarioLogado);
			cliDAO.atualizarListaAmigos(idAmigo, listaAmigosAmigoSelecionado);
		}

		buscarDadosAmigos(listaAmigosUsuarioLogado);

		return mensagemSucesso(listaAmigosUsuarioLogado);
	}
	
	public String solicitarAmizade(String idUsuarioLogado, String idAmigo) {
		List<Amizade> listaAmigosUsuarioLogado = cliDAO.consultarAmigosById(idUsuarioLogado);
		List<Amizade> listaAmigosAmigoSelecionado = cliDAO.consultarAmigosById(idAmigo);

		
		// adicionando amizade com status de solicitacao de amizade enviada no usuarioLogado
		Amizade amizadeSolicitada = new Amizade();
		Cliente amigoAmizadeSolicitada = new Cliente();
		amigoAmizadeSolicitada.setId(idAmigo);
		amizadeSolicitada.setAmigo(amigoAmizadeSolicitada);
		amizadeSolicitada.setDataInicioAmizade(new Date());
		amizadeSolicitada.setStatusEnum(StatusAmizadeEnum.SOLICITACAO_ENVIADA);
		if (listaAmigosUsuarioLogado != null) {
			listaAmigosUsuarioLogado.add(amizadeSolicitada);
			cliDAO.atualizarListaAmigos(idUsuarioLogado, listaAmigosUsuarioLogado);
		}else{
			listaAmigosUsuarioLogado = new ArrayList<Amizade>();
			listaAmigosUsuarioLogado.add(amizadeSolicitada);
			cliDAO.atualizarListaAmigos(idUsuarioLogado, listaAmigosUsuarioLogado);
		}
		
		
		// adicionando amizade com status de solicitacao de amizade recebida no amigo
		Amizade amizadeRecebida = new Amizade();
		Cliente amigoAmizadeRecebida = new Cliente();
		amigoAmizadeRecebida.setId(idUsuarioLogado);
		amizadeRecebida.setAmigo(amigoAmizadeRecebida);
		amizadeRecebida.setDataInicioAmizade(new Date());
		amizadeRecebida.setStatusEnum(StatusAmizadeEnum.SOLICITACAO_RECEBIDA);
		if (listaAmigosAmigoSelecionado != null) {
			listaAmigosAmigoSelecionado.add(amizadeRecebida);
			cliDAO.atualizarListaAmigos(idAmigo, listaAmigosAmigoSelecionado);
		}else{
			listaAmigosAmigoSelecionado = new ArrayList<Amizade>();
			listaAmigosAmigoSelecionado.add(amizadeRecebida);
			cliDAO.atualizarListaAmigos(idAmigo, listaAmigosAmigoSelecionado);
		}
		
		buscarDadosAmigos(listaAmigosUsuarioLogado);

		return mensagemSucesso(listaAmigosUsuarioLogado);
	}
	
	public String aceitarAmizade(String idUsuarioLogado, String idAmigo) {
		List<Amizade> listaAmigosUsuarioLogado = cliDAO.consultarAmigosById(idUsuarioLogado);
		List<Amizade> listaAmigosAmigoSelecionado = cliDAO.consultarAmigosById(idAmigo);

		// removendo da lista de amigos, o idAmigo selecionado
		if (listaAmigosUsuarioLogado != null) {
			aceitarAmizade(listaAmigosUsuarioLogado, idAmigo);
			cliDAO.atualizarListaAmigos(idUsuarioLogado, listaAmigosUsuarioLogado);
		}
		// removendo da lista de amigos do amigo, o id do usuario logado
		if (listaAmigosAmigoSelecionado != null) {
			aceitarAmizade(listaAmigosAmigoSelecionado, idUsuarioLogado);
			cliDAO.atualizarListaAmigos(idAmigo, listaAmigosAmigoSelecionado);
		}

		buscarDadosAmigos(listaAmigosUsuarioLogado);

		return mensagemSucesso(listaAmigosUsuarioLogado);
	}

	private void aceitarAmizade(List<Amizade> listaAmigos, String idAmigo) {
		if (listaAmigos != null) {
			for (Amizade amizade : listaAmigos) {
				if (amizade.getAmigo() != null) {
					if (amizade.getAmigo().getId().equals(idAmigo)) {
						amizade.setStatusEnum(StatusAmizadeEnum.ATIVA);
					}
				}
			}
		}
	}
	private List<Amizade> removerAmigo(List<Amizade> listaAmigos, String idAmigo) {
		List<Amizade> retorno = new ArrayList<Amizade>();
		if (listaAmigos != null) {
			for (Amizade amizade : listaAmigos) {
				if (amizade.getAmigo() != null) {
					if (!amizade.getAmigo().getId().equals(idAmigo)) {
						retorno.add(amizade);
					}
				}
			}
		}
		return retorno;
	}

	private void buscarDadosAmigos(List<Amizade> listaAmigos) {
		if (listaAmigos != null) {
			for (Amizade amizade : listaAmigos) {
				if (amizade.getAmigo() != null) {
					if (amizade.getStatusEnum() != StatusAmizadeEnum.INATIVA) {
						Usuario usuario = cliDAO.consultaLoginById(amizade.getAmigo().getId());
						if (usuario != null) {
							amizade.getAmigo().setNome(usuario.getNome());
							amizade.getAmigo().setEmail(usuario.getEmail());
							amizade.getAmigo().setUsuario(usuario.getUsuario());
							amizade.getAmigo().setTipoCadastro(usuario.getTipoCadastro());
						}
					} else {
						// apenas enviar amizades com status ativo ou solicitada
						listaAmigos.remove(amizade);
					}
				}
			}
		}

	}

	private String mensagemSucesso(List<Amizade> listaAmigos) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("listaAmigos", util.listaAmigosJSON(listaAmigos));
		if (listaAmigos == null) {
			jObjt.put("numAmigos", 0);
		} else {
			jObjt.put("numAmigos", listaAmigos.size());
		}
		return jObjt.toString();
	}

}

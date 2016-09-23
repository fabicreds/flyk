package com.tcc.flyk.service;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class FriendsProfilePageService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	private List<Categoria> listaCategoriasCadastradas;

	public String montarDadosPerfilAmigo(String idUsuarioLogado, String idAmigo, TipoCadastroEnum tipoCadastroAmigo) {
		listaCategoriasCadastradas = categoriaDAO.consultarTodasCategorias();

		if (tipoCadastroAmigo == TipoCadastroEnum.CLIENTE) {
			Cliente cliente = new Cliente();
			cliente = cliDAO.consultaClientePorId(idAmigo);
			// buscando os detalhes dos Amigos
			buscarDadosAmigos(cliente);
			// buscando os detalhes do servicos contratados
			buscarDadosContratos(cliente);

			// buscarStatusAmizade
			StatusAmizadeEnum statusAmizade = buscasStatusAmizade(idUsuarioLogado, idAmigo);

			return mensagemSucesso(cliente, statusAmizade);
		} else {
			// busca pelos Dados do Prestador
			Prestador prestador = new Prestador();
			prestador = prestadorDAO.consultaPrestadorPorId(idAmigo);
			// buscando os detalhes dos servicos do prestador
			buscarListaServicos(prestador);
			// buscando os detalhes dos Amigos
			buscarDadosAmigos(prestador);
			// buscando os detalhes dos servicos contratados pelo prestador
			buscarDadosContratos(prestador);

			// buscarStatusAmizade
			StatusAmizadeEnum statusAmizade = buscasStatusAmizade(idUsuarioLogado, idAmigo);

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

	private void buscarDadosAmigos(Cliente cliente) {
		if (cliente.getListaAmigos() != null) {
			for (Amizade amizade : cliente.getListaAmigos()) {
				if (amizade.getAmigo() != null) {
					Usuario usuario = cliDAO.consultaLoginById(amizade.getAmigo().getId());
					if (usuario != null) {
						amizade.getAmigo().setNome(usuario.getNome());
						amizade.getAmigo().setEmail(usuario.getEmail());
						amizade.getAmigo().setUsuario(usuario.getUsuario());
						amizade.getAmigo().setTipoCadastro(usuario.getTipoCadastro());
					}
				}
			}
		}

	}

	private void buscarDadosContratos(Cliente cliente) {

		if (cliente.getListaServicosContratados() != null) {
			for (Compromisso compromisso : cliente.getListaServicosContratados()) {
				if (compromisso.getContrato() != null) {
					if (compromisso.getContrato().getPrestador() != null) {
						Usuario usuario = cliDAO.consultaLoginById(compromisso.getContrato().getPrestador().getId());
						if (usuario != null) {
							compromisso.getContrato().getPrestador().setNome(usuario.getNome());
							compromisso.getContrato().getPrestador().setEmail(usuario.getEmail());
							compromisso.getContrato().getPrestador().setUsuario(usuario.getUsuario());
							compromisso.getContrato().getPrestador().setTipoCadastro(usuario.getTipoCadastro());
						}
					}

					if (compromisso.getContrato().getServico() != null) {
						for (Categoria cat : listaCategoriasCadastradas) {
							if (cat.getId().equals(compromisso.getContrato().getServico().getId())) {
								if (cat.getNomeCategoria() != null) {
									compromisso.getContrato().getServico().setNomeCategoria(cat.getNomeCategoria());
								}
								if (cat.getDescricaoCategoria() != null) {
									compromisso.getContrato().getServico()
											.setDescricaoCategoria(cat.getDescricaoCategoria());
								}
								if (cat.getStatusCategoria() != null) {
									compromisso.getContrato().getServico().setStatusCategoria(cat.getStatusCategoria());
								}
								if (cat.getInicioVigencia() != null) {
									compromisso.getContrato().getServico().setInicioVigencia(cat.getInicioVigencia());
								}
							}
						}
					}
				}
			}
		}
	}

	private void buscarListaServicos(Prestador prestador) {
		if (prestador.getListaCategoriaServicosPrestados() != null && listaCategoriasCadastradas != null) {
			for (Categoria servico : prestador.getListaCategoriaServicosPrestados()) {
				for (Categoria cat : listaCategoriasCadastradas) {
					if (cat.getId().equals(servico.getId())) {
						if (cat.getNomeCategoria() != null) {
							servico.setNomeCategoria(cat.getNomeCategoria());
						}
						if (cat.getDescricaoCategoria() != null) {
							servico.setDescricaoCategoria(cat.getDescricaoCategoria());
						}
						if (cat.getStatusCategoria() != null) {
							servico.setStatusCategoria(cat.getStatusCategoria());
						}
						if (cat.getInicioVigencia() != null) {
							servico.setInicioVigencia(cat.getInicioVigencia());
						}
					}
				}
			}
		}
	}

	private StatusAmizadeEnum buscasStatusAmizade(String idUsuarioLogado, String idAmigo) {
		List<Amizade> listaAmigosUsuarioLogado = cliDAO.consultarAmigosById(idUsuarioLogado);
		for (Amizade amizade : listaAmigosUsuarioLogado) {
			if (amizade.getAmigo() != null) {
				if (amizade.getAmigo().getId().equals(idAmigo)) {
					return amizade.getStatusEnum();
				}
			}
		}
		return StatusAmizadeEnum.INATIVA;
	}

}

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
public class ProfilePageService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	@Resource
	private PrestadorUtil prestadorUtil;
	
	private List<Categoria> listaCategoriasCadastradas;

	public String montarDadosPerfil(String id, TipoCadastroEnum tipoCadastro) {
		listaCategoriasCadastradas = categoriaDAO.consultarTodasCategorias();

		if (tipoCadastro == TipoCadastroEnum.CLIENTE) {
			Cliente cliente = new Cliente();
			cliente = cliDAO.consultaClientePorId(id);
			// buscando os detalhes dos Amigos
			buscarDadosAmigos(cliente);
			// buscando os detalhes do servicos contratados
			buscarDadosContratos(cliente);
			
                            			return mensagemSucesso(cliente);
		} else {
			// busca pelos Dados do Prestador
			Prestador prestador = new Prestador();
			prestador = prestadorDAO.consultaPrestadorPorId(id);
			// buscando os detalhes dos servicos do prestador
			buscarListaServicos(prestador);
			// buscando os detalhes dos Amigos
			buscarDadosAmigos(prestador);
			// buscando os detalhes dos servicos contratados pelo prestador
			buscarDadosContratos(prestador);
			
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
									compromisso.getContrato().getServico()
											.setStatusCategoria(cat.getStatusCategoria());
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
}

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
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class ProfilePageService {

	private ClienteDAOImpl cliDAO = new ClienteDAOImpl();

	private CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

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
			prestador = cliDAO.consultaPrestadorPorId(id);
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
		jObjt.put("cliente", clienteUtil.toJSON(cliente));
		return jObjt.toString();
	}

	private String mensagemSucesso(Prestador prestador) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("usuario", prestador.getEmail());
		jObjt.put("tipoCadastro", prestador.getTipoCadastro().getCodigo());
		jObjt.put("tipoCadastroDescricao", prestador.getTipoCadastro().getDescricao());
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

		if (cliente.getAgenda() != null) {
			for (Compromisso compromisso : cliente.getAgenda()) {
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
								if (cat.getNome_categoria() != null) {
									compromisso.getContrato().getServico().setNome_categoria(cat.getNome_categoria());
								}
								if (cat.getDescricao_categoria() != null) {
									compromisso.getContrato().getServico()
											.setDescricao_categoria(cat.getDescricao_categoria());
								}
								if (cat.getStatus_categoria() != null) {
									compromisso.getContrato().getServico()
											.setStatus_categoria(cat.getStatus_categoria());
								}
								if (cat.getInicio_vigencia() != null) {
									compromisso.getContrato().getServico().setInicio_vigencia(cat.getInicio_vigencia());
								}
								if (cat.getFim_vigencia() != null) {
									compromisso.getContrato().getServico().setFim_vigencia(cat.getFim_vigencia());
								}
							}
						}
					}
				}
			}
		}
	}

	private void buscarListaServicos(Prestador prestador) {
		if (prestador.getListaServicos() != null && listaCategoriasCadastradas != null) {
			for (Categoria servico : prestador.getListaServicos()) {
				for (Categoria cat : listaCategoriasCadastradas) {
					if (cat.getId().equals(servico.getId())) {
						if (cat.getNome_categoria() != null) {
							servico.setNome_categoria(cat.getNome_categoria());
						}
						if (cat.getDescricao_categoria() != null) {
							servico.setDescricao_categoria(cat.getDescricao_categoria());
						}
						if (cat.getStatus_categoria() != null) {
							servico.setStatus_categoria(cat.getStatus_categoria());
						}
						if (cat.getInicio_vigencia() != null) {
							servico.setInicio_vigencia(cat.getInicio_vigencia());
						}
						if (cat.getFim_vigencia() != null) {
							servico.setFim_vigencia(cat.getFim_vigencia());
						}
					}
				}
			}
		}
	}
}

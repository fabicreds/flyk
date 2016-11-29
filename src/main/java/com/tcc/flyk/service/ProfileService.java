package com.tcc.flyk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

@Service
public class ProfileService {
	
	private List<Categoria> listaCategoriasCadastradas;
	
	private ClienteDAO cliDAO = new ClienteDAOImpl();
	
	private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
	
	public void buscarDadosAmigos(Cliente cliente) {
		if (cliente.getListaAmigos() != null) {
			for (Amizade amizade : cliente.getListaAmigos()) {
				if (amizade.getAmigo() != null) {
					Usuario usuario = cliDAO.consultaLoginById(amizade.getAmigo().getId());
					if (usuario != null) {
						amizade.getAmigo().setNome(usuario.getNome());
						amizade.getAmigo().setEmail(usuario.getEmail());
						amizade.getAmigo().setUsuario(usuario.getUsuario());
						amizade.getAmigo().setTipoCadastro(usuario.getTipoCadastro());
						amizade.getAmigo().setFotoPerfil(usuario.getFotoPerfil());
					}
				}
			}
		}

	}

	public void buscarDadosContratos(Cliente cliente) {
		listaCategoriasCadastradas = categoriaDAO.consultarTodasCategoriasAtivas();
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

	public void buscarListaServicos(Prestador prestador) {
		listaCategoriasCadastradas = categoriaDAO.consultarTodasCategoriasAtivas();
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
	
	public void buscarDadosContratosPrestados(Prestador prestador) {
		listaCategoriasCadastradas = categoriaDAO.consultarTodasCategoriasAtivas();
		if (prestador.getListaContratosServicosPrestados() != null) {
			for (Compromisso compromisso : prestador.getListaContratosServicosPrestados()) {
				if (compromisso.getContrato() != null) {
					if (compromisso.getContrato().getCliente() != null) {
						Usuario usuario = cliDAO.consultaLoginById(compromisso.getContrato().getCliente().getId());
						if (usuario != null) {
							compromisso.getContrato().getCliente().setNome(usuario.getNome());
							compromisso.getContrato().getCliente().setEmail(usuario.getEmail());
							compromisso.getContrato().getCliente().setUsuario(usuario.getUsuario());
							compromisso.getContrato().getCliente().setTipoCadastro(usuario.getTipoCadastro());
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
	
	public StatusAmizadeEnum buscasStatusAmizade(String idUsuarioLogado, String idAmigo) {
		List<Amizade> listaAmigosUsuarioLogado = cliDAO.consultarAmigosById(idUsuarioLogado);
		if (listaAmigosUsuarioLogado!= null)
		{
		for (Amizade amizade : listaAmigosUsuarioLogado) {
			if (amizade.getAmigo() != null) {
				if (amizade.getAmigo().getId().equals(idAmigo)) {
					return amizade.getStatusEnum();
				}
			}
		}
		
		}
		return StatusAmizadeEnum.INATIVA;
	}
	
	public void buscarRecomendacoesDadas(Cliente cliente){
		if(cliente.getListaPrestadoresRecomendados()!=null && cliente.getListaAmigos()!=null){
			for(String id: cliente.getListaPrestadoresRecomendados()){
				for(Amizade amizade: cliente.getListaAmigos()){
					if(amizade.getAmigo()!=null && id!=null && id.equals(amizade.getAmigo().getId())){
						amizade.setRecomendacaoDada(true);
					}
				}
			}
		}
	}
	
	public void buscarRecomendacoesRecebidas(Prestador prestador){
		if(prestador.getListaRecomendacoesRecebidas()!=null && prestador.getListaAmigos()!=null){
			for(String id: prestador.getListaRecomendacoesRecebidas()){
				for(Amizade amizade: prestador.getListaAmigos()){
					if(amizade.getAmigo()!=null && id!=null && id.equals(amizade.getAmigo().getId())){
						amizade.setRecomendacaoRecebida(true);
					}
				}
			}
		}
	}

}

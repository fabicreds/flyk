package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusCompromissoEnum;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class ContratarServicoService {

	private ClienteDAO clienteDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	public String contratarServico(String idCliente, String idPrestador, Compromisso compromisso) {
		List<Compromisso> clienteListaServicosContratados = new ArrayList<Compromisso>();
		List<Compromisso> prestadorListaContratosServicosPrestados = new ArrayList<Compromisso>();
		if (compromisso != null && compromisso.getContrato() != null) {
			if (compromisso.getContrato().getCliente() != null) {
				clienteListaServicosContratados = clienteDAO.consultarListaServicosContratadosById(idCliente);
			}
			if (compromisso.getContrato().getPrestador() != null) {
				prestadorListaContratosServicosPrestados = prestadorDAO
						.consultarListaContratosServicosPrestadosById(idPrestador);
			}
		}

		compromisso.setStatus(StatusCompromissoEnum.SOLICITADO);

		if (clienteListaServicosContratados == null || clienteListaServicosContratados.isEmpty()) {
			clienteListaServicosContratados = new ArrayList<Compromisso>();
			clienteListaServicosContratados.add(compromisso);
			clienteDAO.atualizarListaServicosContratadosById(idCliente, clienteListaServicosContratados);
		} else {
			clienteListaServicosContratados.add(compromisso);
			clienteDAO.atualizarListaServicosContratadosById(idCliente, clienteListaServicosContratados);
		}
		if (prestadorListaContratosServicosPrestados == null || prestadorListaContratosServicosPrestados.isEmpty()) {
			prestadorListaContratosServicosPrestados = new ArrayList<Compromisso>();
			prestadorListaContratosServicosPrestados.add(compromisso);
			prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,
					prestadorListaContratosServicosPrestados);
		} else {
			prestadorListaContratosServicosPrestados.add(compromisso);
			prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,
					prestadorListaContratosServicosPrestados);
		}

		buscarDadosContratos(clienteListaServicosContratados);

		return mensagemSucesso(true, clienteListaServicosContratados);
	}

	public String atualizarCompromisso(String idCliente, String idPrestador, Compromisso compromisso, int status, boolean indCliente) {
		List<Compromisso> clienteListaServicosContratados = new ArrayList<Compromisso>();
		List<Compromisso> prestadorListaContratosServicosPrestados = new ArrayList<Compromisso>();
		if (compromisso != null && compromisso.getContrato() != null) {
			if (idCliente != null) {
				clienteListaServicosContratados = clienteDAO.consultarListaServicosContratadosById(idCliente);
				if (clienteListaServicosContratados != null) {
					Cliente cliente = new Cliente();
					cliente.setId(idCliente);
					for (Compromisso c : clienteListaServicosContratados) {
						c.getContrato().setCliente(cliente);
					}
				}
			}
			if (idPrestador != null) {
				prestadorListaContratosServicosPrestados = prestadorDAO
						.consultarListaContratosServicosPrestadosById(idPrestador);
				if (prestadorListaContratosServicosPrestados != null) {
					Prestador prestador = new Prestador();
					prestador.setId(idPrestador);
					for (Compromisso c : prestadorListaContratosServicosPrestados) {
						c.getContrato().setPrestador(prestador);
					}
				}
			}
		}
		
		atualizarStatusCustoCompromisso(clienteListaServicosContratados, compromisso, status);
		atualizarStatusCustoCompromisso(prestadorListaContratosServicosPrestados, compromisso, status);

		clienteDAO.atualizarListaServicosContratadosById(idCliente,
				clienteListaServicosContratados);
		prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,
				prestadorListaContratosServicosPrestados);

		return mensagemSucesso(indCliente, prestadorListaContratosServicosPrestados);
	}

	private String mensagemSucesso(boolean indCliente, List<Compromisso> listaContratos) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("mensagem", "Serviço contrato com sucesso!");
		if(indCliente){
			jObjt.put("numServicosContratados", listaContratos.size());
			jObjt.put("listaServicosContratados",
					clienteUtil.listaServicosContratadosJSON(listaContratos));
		}else{
			jObjt.put("numServicosPrestados", listaContratos.size());
			jObjt.put("listaContratosServicosPrestados",
				clienteUtil.listaServicosContratadosJSON(listaContratos));
		}
		return jObjt.toString();
	}

	private void buscarDadosContratos(List<Compromisso> clienteListaServicosContratados) {

		List<Categoria> listaCategoriasCadastradas = categoriaDAO.consultarTodasCategoriasAtivas();

		if (clienteListaServicosContratados != null) {
			for (Compromisso compromisso : clienteListaServicosContratados) {
				if (compromisso.getContrato() != null) {
					if (compromisso.getContrato().getPrestador() != null) {
						Usuario usuario = clienteDAO
								.consultaLoginById(compromisso.getContrato().getPrestador().getId());
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

	private void atualizarStatusCustoCompromisso(List<Compromisso> listaCompromisso, Compromisso compromisso,
			int status) {
		if (listaCompromisso != null) {
			for (Compromisso comp : listaCompromisso) {
				if (comp.compararCompromissos(compromisso) == 0) {
					if (comp.getContrato() != null && compromisso.getContrato() != null) {
						if (status == 3) {
							comp.getContrato().setCusto(compromisso.getContrato().getCusto());
						}
						comp.setStatus(status);
					}
				}
			}
		}
	}

}

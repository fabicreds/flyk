package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Compromisso;
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
				prestadorListaContratosServicosPrestados = prestadorDAO.consultarListaContratosServicosPrestadosById(idPrestador);
			}
		}
		
		compromisso.setStatus(StatusCompromissoEnum.PRETENDIDO);
		
		if (clienteListaServicosContratados==null || clienteListaServicosContratados.isEmpty()) {
			clienteListaServicosContratados = new ArrayList<Compromisso>();
			clienteListaServicosContratados.add(compromisso);
			clienteDAO.atualizarListaServicosContratadosById(idCliente, clienteListaServicosContratados);
		}else{
			clienteListaServicosContratados.add(compromisso);
			clienteDAO.atualizarListaServicosContratadosById(idCliente, clienteListaServicosContratados);
		}
		if (prestadorListaContratosServicosPrestados==null || prestadorListaContratosServicosPrestados.isEmpty()) {
			prestadorListaContratosServicosPrestados = new ArrayList<Compromisso>();
			prestadorListaContratosServicosPrestados.add(compromisso);
			prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,prestadorListaContratosServicosPrestados);
		}else{
			prestadorListaContratosServicosPrestados.add(compromisso);
			prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,prestadorListaContratosServicosPrestados);
		}
		
		buscarDadosContratos( clienteListaServicosContratados);
		
		return mensagemSucesso(clienteListaServicosContratados);
	}
	
	private String mensagemSucesso(List<Compromisso> clienteListaServicosContratados) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("mensagem", "Serviço contrato com sucesso!");
		jObjt.put("numServicosContratados", clienteListaServicosContratados.size());
		jObjt.put("listaServicosContratados", clienteUtil.listaServicosContratadosJSON(clienteListaServicosContratados));
		return jObjt.toString();
	}
	
	private void buscarDadosContratos(List<Compromisso> clienteListaServicosContratados) {
		
		List<Categoria> listaCategoriasCadastradas = categoriaDAO.consultarTodasCategorias();

		if (clienteListaServicosContratados != null) {
			for (Compromisso compromisso : clienteListaServicosContratados) {
				if (compromisso.getContrato() != null) {
					if (compromisso.getContrato().getPrestador() != null) {
						Usuario usuario = clienteDAO.consultaLoginById(compromisso.getContrato().getPrestador().getId());
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

}

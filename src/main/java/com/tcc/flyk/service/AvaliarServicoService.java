package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.AvaliacaoPrestador;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class AvaliarServicoService {

	private ClienteDAO clienteDAO = new ClienteDAOImpl();

	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;

	public String avaliarPrestador(String idCliente, String idPrestador, Compromisso compromisso,
			AvaliacaoPrestador avaliacao) {
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
		
		atualizarAvaliacaoPrestador(clienteListaServicosContratados, compromisso, avaliacao);
		atualizarAvaliacaoPrestador(prestadorListaContratosServicosPrestados, compromisso, avaliacao);
		
		clienteDAO.atualizarListaServicosContratadosById(idCliente,
				clienteListaServicosContratados);
		prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,
				prestadorListaContratosServicosPrestados);
		
		return mensagemSucesso(true, clienteListaServicosContratados);
	}

	public String avaliarCliente(String idCliente, String idPrestador, Compromisso compromisso, int avaliacao) {
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

		atualizarAvaliacaoCliente(clienteListaServicosContratados, compromisso, avaliacao);
		atualizarAvaliacaoCliente(prestadorListaContratosServicosPrestados, compromisso, avaliacao);
		
		clienteDAO.atualizarListaServicosContratadosById(idCliente,
				clienteListaServicosContratados);
		prestadorDAO.atualizarListaContratosServicosPrestadosById(idPrestador,
				prestadorListaContratosServicosPrestados);
		
		return mensagemSucesso(false, prestadorListaContratosServicosPrestados);
	}
	
	private void atualizarAvaliacaoPrestador(List<Compromisso> listaCompromisso, Compromisso compromisso,
			AvaliacaoPrestador avaliacao) {
		if (listaCompromisso != null) {
			for (Compromisso comp : listaCompromisso) {
				if (comp.compararCompromissos(compromisso) == 0) {
					if (comp.getContrato() != null && compromisso.getContrato() != null) {
						comp.getContrato().setAvaliacaoPrestador(avaliacao);
						comp.getContrato().setDataAvaliacaoServico(new Date());
					}
				}
			}
		}
	}
	
	private void atualizarAvaliacaoCliente(List<Compromisso> listaCompromisso, Compromisso compromisso,
			int avaliacao) {
		if (listaCompromisso != null) {
			for (Compromisso comp : listaCompromisso) {
				if (comp.compararCompromissos(compromisso) == 0) {
					if (comp.getContrato() != null && compromisso.getContrato() != null) {
						comp.getContrato().setAvaliacaoContratante(avaliacao);
					}
				}
			}
		}
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
}

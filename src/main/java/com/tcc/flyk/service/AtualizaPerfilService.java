package com.tcc.flyk.service;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.PrestadorDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.persistence.impl.PrestadorDAOImpl;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Service
public class AtualizaPerfilService {

	private ClienteDAO cliDAO = new ClienteDAOImpl();
	private PrestadorDAO prestadorDAO = new PrestadorDAOImpl();

	@Resource
	private ClienteUtil clienteUtil;
	
	@Resource
	private PrestadorUtil prestadorUtil;

	public Cliente atualizaPerfilCliente(String id, JSONObject jsonCli) {

		Cliente JSONToCli = clienteUtil.toCliente(jsonCli);

		return cliDAO.atualizaCliente(id, JSONToCli);
	}
	
	public Prestador atualizaPerfilPrestador(String id, JSONObject jsonCli) {

		Prestador JSONToPrestador = prestadorUtil.toPrestador(jsonCli);
		//System.out.println("Prestador " + jsonCli.toString());

		return prestadorDAO.atualizaPrestador(id, JSONToPrestador);
	}
}

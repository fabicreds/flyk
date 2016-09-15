package com.tcc.flyk.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class AtualizaPerfilService {


	private ClienteDAO cliDAO = new ClienteDAOImpl();
	private ClienteUtil cUil = new ClienteUtil();
	
	public Cliente atualizaPerfil(String id, JSONObject jsonCli) {

		ClienteDAO cliDAO = new ClienteDAOImpl();
		Cliente cli = new Cliente();
		Cliente JSONToCli = new Cliente();
		JSONToCli = cUil.toCliente(jsonCli);
		System.out.println("jsonCli" + jsonCli);
		
		cli = cliDAO.atualizaCliente(id, JSONToCli);

		return cli;

	}
}

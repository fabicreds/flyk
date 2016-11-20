package com.tcc.flyk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.persistence.PromocaoDAO;
import com.tcc.flyk.persistence.impl.PromocaoDAOImpl;

@Service
public class PromocaoService {

	private PromocaoDAO promDAO = new PromocaoDAOImpl();

	public boolean cadastrarPromocao(Promocao prom) {
		try {
			if (validaAdministrador(prom)) {

				return promDAO.inserirNovaPromocao(prom);
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean validaAdministrador(Promocao prom) {
		if (prom.getNomePromocao() == null || prom.getNomePromocao().isEmpty()) {
			System.out.println("Nome do promocao Nulo");
			return false;
		}

		return true;
	}
	
	public List<Promocao> consultarTodasPromocoes(){
		return promDAO.consultaPromocao();
	}
}

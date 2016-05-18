package com.tcc.flyk.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;

@Service
public class CadastroAdminstradorService {

	private AdministradorDAOImpl admDAO = new AdministradorDAOImpl();

	public boolean CadastrarNovoAdministrador(Administrador adm) {
		try {
			adm.setDataCadastro(new Date());
			adm.setAtivo(true);
			admDAO.inserirNovoAdmisnistrador(adm);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

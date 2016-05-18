package com.tcc.flyk.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.AdministradorDAO;

@Service
public class CadastroAdminstradorService {

	private AdministradorDAO admDAO;

	public boolean CadastrarNovoAdministrador(Administrador adm) {
		try {
			adm.setDataCadastro(new Date());
			adm.setAtivo(true);
			admDAO.inserirNovoAdmisnistrador(adm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}

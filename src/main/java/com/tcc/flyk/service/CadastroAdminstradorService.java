package com.tcc.flyk.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;

@Service
public class CadastroAdminstradorService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();

	public boolean CadastrarNovoAdministrador(Administrador adm) {
		try {
			if(validaAdministrador(adm)){
				adm.setDataCadastro(new Date());
				adm.setAtivo(true);
				admDAO.inserirNovoAdmisnistrador(adm);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean validaAdministrador(Administrador adm){
		if(adm.getNome() ==null || adm.getNome().isEmpty()){
			System.out.println("Nome do Administrador Nulo");
			return false;
		}
		if(adm.getUsuario() ==null || adm.getUsuario().isEmpty()){
			System.out.println("Usuário do Administrador Nulo");
			return false;
		}
		if(adm.getSenha() ==null || adm.getSenha().isEmpty()){
			System.out.println("Senha do Administrador Nulo");
			return false;
		}
		return true;
	}

}

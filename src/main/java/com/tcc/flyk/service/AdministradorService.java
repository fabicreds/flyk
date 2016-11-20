package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;

@Service
public class AdministradorService {

	private AdministradorDAO admDAO = new AdministradorDAOImpl();
	
	public List<Usuario> listarUsuariosAdministradores(){
		List<Usuario> listaAdminsitradores = new ArrayList<Usuario>();
		listaAdminsitradores = admDAO.listarUsuariosAdministradores();
		return listaAdminsitradores;
	}
}

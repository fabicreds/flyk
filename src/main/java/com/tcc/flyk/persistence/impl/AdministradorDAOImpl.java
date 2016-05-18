package com.tcc.flyk.persistence.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.MongoDB;

public class AdministradorDAOImpl extends MongoDB implements AdministradorDAO {

	public AdministradorDAOImpl() {
		super();
	}

	@Override
	public void inserirNovoAdmisnistrador(Administrador adm) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			super.db.getCollection("administrador").insertOne(
			                new Document()
			                        .append("nome", adm.getNome())
			                        .append("usuario", adm.getUsuario())
			                        .append("senha", adm.getSenha())
			                        .append("ativo", adm.isAtivo())
			                        .append("data", format.parse(adm.getDataCadastro().toString())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

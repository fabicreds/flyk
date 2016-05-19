package com.tcc.flyk.persistence.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
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
//			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			super.db.getCollection("administrador").insertOne(
			                new Document()
			                        .append("nome", adm.getNome())
			                        .append("usuario", adm.getUsuario())
			                        .append("senha", adm.getSenha())
			                        .append("ativo", adm.isAtivo())
			                        .append("data", adm.getDataCadastro()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void consulta(){
		FindIterable<Document> iterable = db.getCollection("administrador").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}

}

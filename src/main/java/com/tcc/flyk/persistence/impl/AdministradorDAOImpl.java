package com.tcc.flyk.persistence.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.MongoDB;

public class AdministradorDAOImpl extends MongoDB implements AdministradorDAO {

	public AdministradorDAOImpl(MongoClient mongoClient, MongoDatabase db) {
		super(mongoClient, db);
	}

	@Override
	public void inserirNovoAdmisnistrador(Administrador adm) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			super.db.getCollection("administrador").insertOne(
			                new Document()
			                        .append("nome", "ruaruaruaruaruarua")
			                        .append("usuario", "10075")
			                        .append("senha", "1480")
			                        .append("ativo", true)
			                        .append("data", format.parse("2014-10-01T00:00:00Z")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

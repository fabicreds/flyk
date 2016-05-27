package com.tcc.flyk.persistence;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class MongoDB {
	protected MongoClient mongoClient;
	protected DB db;

	public MongoDB() {
		super();
		this.conecta();
	}

	@SuppressWarnings("deprecation")
	private void conecta() {
		// Cria a classe mongoClient conectando na instância local do mongodb
		mongoClient = new MongoClient(new ServerAddress("localhost"), new MongoClientOptions.Builder().build());

		// Cria a classe db, conectada ao banco de dados test
		db = mongoClient.getDB("test");
	}
}

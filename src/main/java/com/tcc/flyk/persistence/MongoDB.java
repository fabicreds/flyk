package com.tcc.flyk.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	protected MongoClient mongoClient;
	protected MongoDatabase db;

	public MongoDB(MongoClient mongoClient, MongoDatabase db) {
		super();
		this.mongoClient = mongoClient;
		this.db = db;
		this.conecta();
	}

	private void conecta() {
		// Cria a classe mongoClient conectando na instância local do mongodb
		mongoClient = new MongoClient(new ServerAddress("localhost"), new MongoClientOptions.Builder().build());

		// Cria a classe db, conectada ao banco de dados test
		db = mongoClient.getDatabase("test");
	}
}

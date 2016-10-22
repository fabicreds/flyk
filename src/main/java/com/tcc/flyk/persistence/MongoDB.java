package com.tcc.flyk.persistence;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.util.DataBaseUtil;

public class MongoDB {
	public static String NOME_BANCO = "FLYK";
	public static String NOME_COLLECTION = "FLYK";
	
	protected MongoClient mongoClient;
	protected MongoDatabase mongoDatabase;
	protected DB db;

	public MongoDB() {
		super();
		//this.conecta();
	}

	@SuppressWarnings("deprecation")
	protected void conecta() {
		// Cria a classe mongoClient conectando na instÃ¢ncia local do mongodb
		mongoClient = new MongoClient(new ServerAddress("localhost"), new MongoClientOptions.Builder().build());

		// Cria a classe db, conectada ao banco de dados test
		db = mongoClient.getDB("FLYK");
		
		//Cria a classe mongoDatabase, conectada ao banco de dados test
		mongoDatabase = mongoClient.getDatabase("FLYK");
	}
	
	protected void desconecta(){
		mongoClient.close();
	}
	
	//****************************CONSULTA DOCUMENTO COM ID ESPECÍFICO****************************
	public void printDocPorId(String id){

		System.out.println("CONSULTANDO DOCUMENTO PELO ID " + id);
		this.conecta();
		try {
			DBCollection collection = db.getCollection("FLYK");
			BasicDBObject filtro = new BasicDBObject(new Document("_id", new ObjectId(id)));
			DBCursor cursor = collection.find(filtro);
			DBObject resultado;

			// Busca campos de resultado
			if (cursor.hasNext()) {
				resultado = cursor.next();
				resultado.put("foto", "fota");
				System.out.println(resultado);
			} else {
				System.out.println("Consulta de cliente pelo id " + id + " nï¿½o encontrou valores.");
			}

		} catch (Exception e) {
			System.out.println("Erro no print documento por id");
			System.out.println(e);
		}
		this.desconecta();
		
	}
	public void consultaDocs(){
		this.conecta();

		try {
			DBCollection collection = db.getCollection("FLYK");
			DBCursor cursor = collection.find();
			DBObject resultado;

			// Busca campos de resultado
			while (cursor.hasNext()) {
				resultado = cursor.next();
				if(resultado.containsField("foto")){
					resultado.put("foto", "String_da_foto_Favor_ignorar");
				}
				System.out.println(resultado);
			}

		} catch (Exception e) {
			System.out.println("Erro no consultaDocs");
			System.out.println(e);
		}
		this.desconecta();
	}
}

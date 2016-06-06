package com.tcc.flyk.persistence;

import java.util.Date;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.tcc.flyk.entity.Categoria;

public class CategoriaDAO {
	private MongoClient mongoClient;
	private MongoDatabase db;
	private DB FLYK;
	

	//****************************CONECTA NA BASE DE DADOS****************************//
	private void conecta(){
		//Cria a classe mongoClient conectando na instância local do mongodb
		mongoClient = new MongoClient(new ServerAddress("localhost"), new MongoClientOptions.Builder().build());
		
		//Cria a classe db, conectada ao banco de dados FLYK
		db = mongoClient.getDatabase("test");
	}
	
	
	public boolean CadastrarNovaCategoria(Categoria cat){
		try{
			conecta();
			
			System.out.println("gravando categoria");
			//Data de hoje
			Date dHoje = new Date();
			String hoje = "24/05/2016";
			db.getCollection("FLYK").insertOne(new Document("nome_categoria", cat.getNome_categoria())
					.append("descricao_categoria", cat.getDescricao_categoria())
					.append("inicio_vigencia",dHoje)
					.append("status","A"));
			//consultaTudo();//remover depois
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//****************************CONSULTA TODOS OS DOCUMENTOS DO BANCO****************************//
	public void consultaTudo(){

		
		conecta();
		FindIterable<Document> iterable = db.getCollection("FLYK").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}
}

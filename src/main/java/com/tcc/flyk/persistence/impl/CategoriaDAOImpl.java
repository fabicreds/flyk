package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Categoria;

import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.MongoDB;

public class CategoriaDAOImpl extends MongoDB implements CategoriaDAO{
	
	public CategoriaDAOImpl() {
		super();
	}
	

	//****************************CADASTRA CATEGORIA****************************//
	@Override
	public boolean CadastrarNovaCategoria(Categoria cat){
		try{
			
			System.out.println("gravando categoria");
			//Data de hoje
			Date dHoje = new Date();
			super.mongoDatabase.getCollection("FLYK").insertOne(new Document("nome_categoria", cat.getNome_categoria())
					.append("descricao_categoria", cat.getDescricao_categoria())
					.append("inicio_vigencia_categoria",dHoje)
					.append("status_categoria","A"));
			
			consultaTudo();//remover depois
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//****************************CONSULTA CATEGORIA POR NOME****************************//
	@Override
	public Categoria ConsultarCategoriaPorNome(String nome){
		try{
			System.out.println("consultando categoria");

			final Categoria retorno = new Categoria();
			retorno.setNome_categoria("");
			
			//Busca a categoria ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find(new Document("nome_categoria",nome).append("status_categoria", "A"));
			//Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			        System.out.println(document);

			        String nome = document.getString("nome_categoria");
			        retorno.setId(document.getObjectId("_id").toString());
			        retorno.setNome_categoria(nome);
			        retorno.setDescricao_categoria(document.getString("descricao_categoria"));
			        retorno.setInicio_vigencia(document.getDate("inicio_vigencia_categoria"));
			        retorno.setStatus_categoria(document.getString("status_categoria"));
			    }
			});
			
			//Como todas as categorias devem ter nome obrigatoriamente, então caso o nome esteja vazio é porque o registro não foi encontrado
			if(retorno.getNome_categoria()==""){
				return null;
			}else{
				return retorno;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//****************************CONSULTA TODAS AS CATEGORIAS****************************//
	@Override
	public List<Categoria> ConsultarTodasCategorias(){
		try{
			
			System.out.println("consultando todas as categorias");

			db.getCollection("FLYK");

			final List<Categoria> retorno = new ArrayList<Categoria>();
			
			//Busca a categoria ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find(new Document("status_categoria","A"));
			//Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			        System.out.println(document);

			        String nome = document.getString("nome_categoria");
			        
			        //Cria uma nova categoria
			        Categoria cat = new Categoria();
			        cat.setId(document.getObjectId("_id").toString());
			        cat.setNome_categoria(nome);
			        cat.setDescricao_categoria(document.getString("descricao_categoria"));
			        cat.setInicio_vigencia(document.getDate("inicio_vigencia_categoria"));
			        cat.setStatus_categoria(document.getString("status_categoria"));
			        
			        //Adiciona a categoria na lista de retorno
			        retorno.add(cat);
			    }
			});

			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//****************************CONSULTA CATEGORIA POR ID****************************//
	@Override
	public Categoria ConsultarCategoriaPorId(String id){
		try{
			
			System.out.println("consultando categoria");

			db.getCollection("FLYK");

			final Categoria retorno = new Categoria();
			retorno.setNome_categoria("");
			
			//Busca a categoria ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find(new Document("_id", new ObjectId(id)));
			
			//Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			        System.out.println(document);
			        
			        String nome = document.getString("nome_categoria");
			        retorno.setId(document.getString("_id"));
			        retorno.setNome_categoria(nome);
			        retorno.setDescricao_categoria(document.getString("descricao_categoria"));
			        retorno.setInicio_vigencia(document.getDate("inicio_vigencia_categoria"));
			        retorno.setStatus_categoria(document.getString("status_categoria"));
			    }
			});
			
			//Como todas as categorias devem ter nome obrigatoriamente, então caso o nome esteja vazio é porque o registro não foi encontrado
			if(retorno.getNome_categoria()==""){
				return null;
			}else{
				return retorno;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//****************************CONSULTA TODOS OS DOCUMENTOS DO BANCO****************************//
	@Override
	public void consultaTudo(){

		FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}
}

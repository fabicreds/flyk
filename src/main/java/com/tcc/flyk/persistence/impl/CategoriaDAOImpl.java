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

public class CategoriaDAOImpl extends MongoDB implements CategoriaDAO {

	public CategoriaDAOImpl() {
		super();
	}

	// ****************************CADASTRA
	// CATEGORIA****************************//
	@Override
	public boolean cadastrarNovaCategoria(Categoria cat) {
		try {

			System.out.println("gravando categoria " + cat.getNomeCategoria());
			// Data de hoje
			Date dHoje = new Date();
			String nome = cat.getNomeCategoria();
			String desc = cat.getDescricaoCategoria();
			super.conecta();
			super.mongoDatabase.getCollection("FLYK")
					.insertOne(new Document("nome_categoria", nome)
							.append("descricao_categoria", desc)
							.append("inicio_vigencia_categoria", dHoje).append("status_categoria", "A"));
			super.desconecta();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ****************************CONSULTA CATEGORIA POR
	// NOME****************************//
	@Override
	public Categoria consultarCategoriaPorNome(String nome) {
		try {
			System.out.println("consultando categoria");

			final Categoria retorno = new Categoria();
			retorno.setNomeCategoria("");

			// Busca a categoria ativa com este nome
			super.conecta();
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK")
					.find(new Document("nome_categoria", nome).append("status_categoria", "A"));
			// Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					//System.out.println(document);

					String nome = document.getString("nome_categoria");
					retorno.setId(document.getObjectId("_id").toString());
					retorno.setNomeCategoria(nome);
					retorno.setDescricaoCategoria(document.getString("descricao_categoria"));
					retorno.setInicioVigencia(document.getDate("inicio_vigencia_categoria"));
					retorno.setStatusCategoria(document.getString("status_categoria"));
				}
			});
			super.desconecta();

			// Como todas as categorias devem ter nome obrigatoriamente, ent�o
			// caso o nome esteja vazio � porque o registro n�o foi encontrado
			if (retorno.getNomeCategoria()== "") {
				return null;
			} else {
				return retorno;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ****************************CONSULTA CATEGORIA POR PARTE DO
	// NOME****************************//
	@Override
	public List<Categoria> consultarCategoriaPorParteDoNome(String nome) {
		try {
			System.out.println("consultando categoria por parte do nome");

			final List<Categoria> retorno = new ArrayList<Categoria>();

			super.conecta();
			// Busca as categorias ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK")
					.find(new Document("status_categoria", "A").append("nome_categoria",
							new Document("$regex", nome).append("$options", "'i'")));

			// Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					//System.out.println(document);

					String nome = document.getString("nome_categoria");

					// Cria uma nova categoria
					Categoria cat = new Categoria();
					cat.setId(document.getObjectId("_id").toString());
					cat.setNomeCategoria(nome);
					cat.setDescricaoCategoria(document.getString("descricao_categoria"));
					cat.setInicioVigencia(document.getDate("inicio_vigencia_categoria"));
					cat.setStatusCategoria(document.getString("status_categoria"));

					// Adiciona a categoria na lista de retorno
					retorno.add(cat);
				}
			});
			super.desconecta();

			System.out.println("consultando categoria por parte do nome fim");

			return retorno;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ****************************CONSULTA TODAS AS
	// CATEGORIAS****************************//
	@Override
	public List<Categoria> consultarTodasCategorias() {
		try {

			System.out.println("consultando todas as categorias");

			super.conecta();
			db.getCollection("FLYK");

			final List<Categoria> retorno = new ArrayList<Categoria>();

			// Busca a categoria ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK")
					.find(new Document("status_categoria", "A"));
			// Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					System.out.println(document);

					// Cria uma nova categoria
					Categoria cat = new Categoria();
					if (document.getObjectId("_id") != null) {
						cat.setId(document.getObjectId("_id").toString());
					}
					if (document.getString("nome_categoria") != null) {
						cat.setNomeCategoria(document.getString("nome_categoria"));
					}
					if (document.getString("descricao_categoria") != null) {
						cat.setDescricaoCategoria(document.getString("descricao_categoria"));
					}
					if (document.getDate("inicio_vigencia_categoria") != null) {
						cat.setInicioVigencia(document.getDate("inicio_vigencia_categoria"));
					}
					if (document.getString("status_categoria") != null) {
						cat.setStatusCategoria(document.getString("status_categoria"));
					}

					// Adiciona a categoria na lista de retorno
					retorno.add(cat);
				}
			});
			super.desconecta();
			
			return retorno;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ****************************CONSULTA CATEGORIA POR
	// ID****************************//
	@Override
	public Categoria consultarCategoriaPorId(String id) {
		try {

			System.out.println("consultando categoria");

			db.getCollection("FLYK");

			final Categoria retorno = new Categoria();
			retorno.setNomeCategoria("");

			super.conecta();
			// Busca a categoria ativa com este nome
			FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK")
					.find(new Document("_id", new ObjectId(id)));

			// Varre a lista de resultados
			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					System.out.println(document);

					if (document.getObjectId("_id") != null) {
						retorno.setId(document.getObjectId("_id").toString());
					}
					if (document.getString("nome_categoria") != null) {
						retorno.setNomeCategoria(document.getString("nome_categoria"));
					}
					if (document.getString("descricao_categoria") != null) {
						retorno.setDescricaoCategoria(document.getString("descricao_categoria"));
					}
					if (document.getDate("inicio_vigencia_categoria") != null) {
						retorno.setInicioVigencia(document.getDate("inicio_vigencia_categoria"));
					}
					if (document.getString("status_categoria") != null) {
						retorno.setStatusCategoria(document.getString("status_categoria"));
					}
				}
			});
			super.desconecta();

			// Como todas as categorias devem ter nome obrigatoriamente, ent�o
			// caso o nome esteja vazio � porque o registro n�o foi encontrado
			if (retorno.getNomeCategoria() == "") {
				return null;
			} else {
				return retorno;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ****************************CONSULTA TODOS OS DOCUMENTOS DO
	// BANCO****************************//
	@Override
	public void consultaTudo() {

		super.conecta();
		FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				//System.out.println(document);
			}
		});
		super.desconecta();
	}
}

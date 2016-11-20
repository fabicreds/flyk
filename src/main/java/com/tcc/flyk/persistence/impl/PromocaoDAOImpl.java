package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tcc.flyk.entity.Preco;
import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.persistence.MongoDB;
import com.tcc.flyk.persistence.PromocaoDAO;

public class PromocaoDAOImpl extends MongoDB implements PromocaoDAO {

	public PromocaoDAOImpl() {
		super();
	}

	@Override
	public boolean inserirNovaPromocao(Promocao prom) {
		try {

			super.conecta();
			DBCollection collection = db.getCollection("FLYK");

			List<BasicDBObject> precoDocList = new ArrayList<BasicDBObject>();

			BasicDBObject promDoc = new BasicDBObject();

			promDoc.append("datainicio", prom.getDataInicio()).append("datafim", prom.getDataFim())
					.append("status", true).append("descricao", prom.getDescricao())
					.append("nome", prom.getNomePromocao()

			);
			
			for(Preco preco: prom.getListaPreco()){
				BasicDBObject precoDoc = new BasicDBObject();
				precoDoc.append("preco", preco.getValor());
				precoDoc.append("categoria", preco.getCategoria());
				precoDocList.add(precoDoc);
			}
			
			promDoc.append("precos", precoDocList);
			
			collection.insert(promDoc);

			super.desconecta();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Promocao> consultaPromocao() {
		super.conecta();

		final List<Promocao> retorno = new ArrayList<Promocao>();

		DBCollection collection = db.getCollection("FLYK");
		BasicDBObject filtro = new BasicDBObject("precos", new BasicDBObject("$exists", true));
		
		DBCursor cursor = collection.find(filtro);
		DBObject resultado;

		// Busca campos de resultado
		while (cursor.hasNext()) {
			resultado = cursor.next();
			Promocao prom = new Promocao();
			if (resultado.get("_id") != null) {
				prom.setId(String.valueOf(resultado.get("_id")));
			}
			if (resultado.get("datainicio") != null) {
				prom.setDataInicio((Date) resultado.get("datainicio"));
			}
			if (resultado.get("datafim") != null) {
				prom.setDataFim((Date) resultado.get("datafim"));
			}
			if (resultado.get("status") != null) {
				prom.setStatus(String.valueOf(resultado.get("status")));
			}
			if (resultado.get("descricao") != null) {
				prom.setDescricao(String.valueOf(resultado.get("descricao")));
			}
			if (resultado.get("nome") != null) {
				prom.setNomePromocao(String.valueOf(resultado.get("nome")));
			}
			if (resultado.get("precos") != null) {
				List<Preco> listaPreco = new ArrayList<Preco>();
				BasicDBList precosDB = (BasicDBList) resultado.get("precos");
				BasicDBObject[] lightArr = precosDB.toArray(new BasicDBObject[0]);
				for (BasicDBObject preco : lightArr) {
					Preco pr = new Preco();
					if(preco.get("preco")!=null){
						pr.setValor(preco.getDouble("preco"));
					}
					if(preco.get("categoria")!=null){
						pr.setCategoria(preco.getString("categoria"));
					}
					listaPreco.add(pr);
				}
				prom.setListaPreco(listaPreco);
			}
			retorno.add(prom);
		}
		
		return retorno;
	}

}

package com.tcc.flyk.persistence.impl;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.tcc.flyk.entity.Promocao;
import com.tcc.flyk.persistence.MongoDB;
import com.tcc.flyk.persistence.PromocaoDAO;

public class PromocaoDAOImpl extends MongoDB implements PromocaoDAO {

	
	

	public PromocaoDAOImpl() {
		super();
	}

	@Override
	public void inserirNovaPromocao(Promocao prom) {
		try {

			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", 
				new BasicDBObject().append("status", false));
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("status", true);

			super.db.getCollection("promocao").updateMulti(searchQuery, updateQuery);	
			
			super.db.getCollection("promocao").insert(
					new BasicDBObject()
			                		.append("datainicio", prom.getDataInicio())	
			                        .append("status", true)
			                        .append("datafim", prom.getDataFim())			                       
			                        .append("precos", new Document().append("valor",prom.getValorPromocional()).append("descricao", prom.getDescricao()).append("nome", prom.getNomePromocao()
			                        )	
			                        		
			                        		)
			                        
					);
			
			
			//below statement set multi to true.
			//collection.update(searchQuery, updateQuery, false, true);	
			                       
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void consultaPromocao(){
		DBCursor cursor = db.getCollection("promocao").find();
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}


}

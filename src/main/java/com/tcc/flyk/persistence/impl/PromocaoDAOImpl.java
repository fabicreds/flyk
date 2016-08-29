package com.tcc.flyk.persistence.impl;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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
			
			List<BasicDBObject> precoDocList = new ArrayList<BasicDBObject>();			
			DBCollection collectionProm = super.db.getCollection("promocao");
					
					
				
					BasicDBObject promDoc = new BasicDBObject();
					
					promDoc.append("datainicio", prom.getDataInicio())                    
                    .append("datafim", prom.getDataFim())
                    .append("status", true)
                    .append("descricao", prom.getDescricao())
                    .append("nome", prom.getNomePromocao()
                    					
                    );		
					promDoc.append("precos", new Document());
					
					
					for (int i = 0; i < prom.getListaPreco().size(); i++) {
						BasicDBObject precoDoc = new BasicDBObject();						
						precoDoc.append("preco", prom.getListaPreco().get(i).getValor()).append("categoria", prom.getListaPreco().get(i).getCategoria());
						precoDocList.add(precoDoc);
						promDoc.append("precos",precoDocList);						
					}		
					
			collectionProm.insert(promDoc);
			                       
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

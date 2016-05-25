package com.tcc.flyk.persistence.impl;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
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
			super.db.getCollection("promocao").insertOne(
			                new Document()
			                		.append("datainicio", prom.getDataInicio())	
			                        .append("status", true)
			                        .append("datafim", prom.getDataFim())			                       
			                        .append("precos", new Document().append("valor",prom.getValorPromocional()).append("descricao", prom.getDescricao()
			                        )	
			                        		
			                        		)
			                        
					);
			                       
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void consultaPromocao(){
		FindIterable<Document> iterable = db.getCollection("promocao").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}


}

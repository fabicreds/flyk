package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.MongoDB;

public class ClienteDAOImpl extends MongoDB implements ClienteDAO {

	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	public ClienteDAOImpl() {
		super();
	}

	@Override
	public void consulta(){
		FindIterable<Document> iterable = db.getCollection("cliente").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}
	
	@Override
	public List<Usuario> consultaUsuario(String usuario){
		
		BasicDBObject query = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	    obj.add(new BasicDBObject("nome", usuario));
	    obj.add(new BasicDBObject("email", usuario));
	    query.put("$or", obj);
	  
	    System.out.println(query.toString());
	    
		FindIterable<Document> iterable = db.getCollection("cliente").find(query);
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	Usuario usuario = new Usuario();
				usuario.setNome(document.get("nome").toString());
				usuario.setUsuario(document.get("email").toString());
				usuario.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				listaUsuario.add(usuario);
				System.out.println(document);
		    }
		});
		
		return new ArrayList<Usuario>();
	}

}

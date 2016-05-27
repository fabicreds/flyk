package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
		DBCursor cursor = db.getCollection("cliente").find();
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}
	
	@Override
	public List<Usuario> consultaUsuario(String busca){
		
		BasicDBObject query = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	    obj.add(new BasicDBObject("nome", busca));
	    obj.add(new BasicDBObject("email", busca));
	    query.put("$or", obj);
	  
	    System.out.println(query.toString());
	    
	    DBCursor cursor= db.getCollection("cliente").find(query);
		
		while(cursor.hasNext()){
			DBObject adm = cursor.next();
			Usuario usuario = new Usuario();
			usuario.setNome(adm.get("nome").toString());
			usuario.setUsuario(adm.get("usuario").toString());
			usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
			listaUsuario.add(usuario);
		}
		
		return new ArrayList<Usuario>();
	}

}

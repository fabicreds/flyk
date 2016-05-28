package com.tcc.flyk.persistence.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.MongoDB;

public class ClienteDAOImpl extends MongoDB implements ClienteDAO {

	public ClienteDAOImpl() {
		super();
	}

	@Override
	public void consulta() {
		DBCursor cursor = db.getCollection("cliente").find();

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	@Override
	public Usuario consultaUsuario(String busca) {
		try {
			Usuario usuario = new Usuario();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", busca);

			DBObject object = db.getCollection("cliente").findOne(query);

			if(object!=null){
				usuario.setNome(object.get("nome").toString());
				usuario.setUsuario(object.get("usuario").toString());
				usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				if(object.get("status_pessoa").toString().equals("A")){
					usuario.setAtivo(true);
				}else{
					usuario.setAtivo(false);
				}
				return usuario;
			}else{
				return null;
			}

			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean atualizarStatusCliente(Usuario usuario) {
		try {
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", new BasicDBObject().append("status_pessoa", "I"));

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("usuario", usuario.getUsuario());

			db.getCollection("cliente").update(searchQuery, updateQuery);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

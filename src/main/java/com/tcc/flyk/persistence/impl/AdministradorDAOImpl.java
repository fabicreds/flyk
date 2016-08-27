package com.tcc.flyk.persistence.impl;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.MongoDB;

public class AdministradorDAOImpl extends MongoDB implements AdministradorDAO {

	public AdministradorDAOImpl() {
		super();
	}

	@Override
	public void inserirNovoAdmisnistrador(Administrador adm) {
		try {
			super.db.getCollection("administrador")
					.insert(new BasicDBObject().append("nome", adm.getNome()).append("usuario", adm.getUsuario())
							.append("senha", adm.getSenha()).append("ativo", adm.isAtivo())
							.append("data", adm.getDataCadastro()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Administrador consultaAdministradorPorUsuario(String usuario) {
		try {
			Administrador adm = new Administrador();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", usuario);

			DBObject object = db.getCollection("administrador").findOne(query);
			if (object != null) {
				if(object.get("ativo").toString().equals("true")){
					adm.setAtivo(true);
				}else{
					adm.setAtivo(false);
				}
				adm.setNome((String) object.get("nome").toString());
				adm.setUsuario((String) object.get("usuario"));
				adm.setDataCadastro((Date) object.get("data"));
				adm.setSenha((String) object.get("senha"));
				return adm;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario consultaUsuarioAdministrador(String busca) {
		try {
			Usuario usuario = new Usuario();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", busca);

			DBObject objeto = db.getCollection("administrador").findOne(query);
			if(objeto!=null){
				usuario.setNome((String) objeto.get("nome").toString());
				usuario.setUsuario((String) objeto.get("usuario").toString());
				String id = String.valueOf(objeto.get("_id"));

				usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				if(objeto.get("ativo").toString().equals("true")){
					usuario.setAtivo(true);
				}else{
					usuario.setAtivo(false);
				}
				usuario.setSenha((String) objeto.get("senha"));
				return usuario;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean atualizarStatusAdministrador(Usuario usuario) {
		try {
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", new BasicDBObject().append("ativo", usuario.isAtivo()));

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("usuario", usuario.getUsuario());

			db.getCollection("administrador").update(searchQuery, updateQuery);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

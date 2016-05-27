package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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
				adm.setAtivo((boolean) object.get("ativo"));
				adm.setNome((String) object.get("nome").toString());
				adm.setUsuario((String) object.get("usuario"));
				adm.setDataCadastro((Date) object.get("data"));
				return adm;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Usuario> consultaNomeUsuarioAdministrador(String busca) {
		try {
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			BasicDBObject query = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("usuario", java.util.regex.Pattern.compile(busca)));
			obj.add(new BasicDBObject("nome", java.util.regex.Pattern.compile(busca)));
			query.put("$or", obj);

			DBCursor cursor = db.getCollection("administrador").find(query);
			while (cursor.hasNext()) {
				DBObject adm = cursor.next();
				Usuario usuario = new Usuario();
				usuario.setNome((String) adm.get("nome"));
				usuario.setUsuario((String) adm.get("usuario"));
				usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				usuario.setAtivo((boolean) adm.get("ativo"));
				listaUsuario.add(usuario);
			}
			return listaUsuario;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean atualizarStatusUsuario(Usuario usuario) {
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

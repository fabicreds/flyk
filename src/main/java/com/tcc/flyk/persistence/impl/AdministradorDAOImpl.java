package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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
		super.conecta();
		try {
			super.db.getCollection("FLYK")
					.insert(new BasicDBObject().append("nome", adm.getNome()).append("usuario", adm.getUsuario())
							.append("senha", adm.getSenha()).append("ativo", adm.isAtivo())
							.append("data", adm.getDataCadastro()).append("tipo_perfil", TipoCadastroEnum.ADMINISTRADOR.getCodigo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.desconecta();

	}

	@Override
	public Administrador consultaAdministradorPorUsuario(String usuario) {
		try {
			Administrador adm = new Administrador();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", usuario);
					
			super.conecta();
			DBObject object = db.getCollection("FLYK").findOne(query);
			super.desconecta();
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

			super.conecta();
			DBObject objeto = db.getCollection("FLYK").findOne(query);
			super.desconecta();
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
				usuario.setId(id);
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

			super.conecta();
			db.getCollection("FLYK").update(searchQuery, updateQuery);
			super.desconecta();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<Usuario> listarUsuariosAdministradores(){
		try{
			super.conecta();
			db.getCollection("FLYK");
	
			final List<Usuario> retorno = new ArrayList<Usuario>();
	
			DBCollection collection = db.getCollection("FLYK");
			BasicDBObject filtro = new BasicDBObject("tipo_perfil", 4);
			
			DBCursor cursor = collection.find(filtro);
			DBObject resultado;
	
			// Busca campos de resultado
			while (cursor.hasNext()) {
				resultado = cursor.next();
				Usuario user = new Usuario();
				if (resultado.get("_id") != null) {
					user.setId(String.valueOf(resultado.get("_id")));
				}
				if (resultado.get("nome") != null) {
					user.setNome(String.valueOf(resultado.get("nome")));
				}
				if (resultado.get("usuario") != null) {
					user.setUsuario(String.valueOf(resultado.get("usuario")));
				}
				if (resultado.get("email") != null) {
					user.setEmail(String.valueOf(resultado.get("email")));
				}
				if (resultado.get("tipo_perfil") != null) {
					Double tipoCadastro = Double.valueOf(resultado.get("tipo_perfil").toString());
					user.setTipoCadastro(tipoCadastro.intValue());
				}
				if (resultado.get("ativo") != null) {
					user.setAtivo((boolean)resultado.get("ativo"));
				}
	
				// Adiciona a categoria na lista de retorno
				retorno.add(user);
			}
			
			super.desconecta();
			
			return retorno;
	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

}

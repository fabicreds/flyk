package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.MongoDB;

public class AdministradorDAOImpl extends MongoDB implements AdministradorDAO {

	private List<Usuario> listaUsuario = new ArrayList<Usuario>();

	public AdministradorDAOImpl() {
		super();
	}

	@Override
	public void inserirNovoAdmisnistrador(Administrador adm) {
		try {
			super.db.getCollection("administrador")
					.insertOne(new Document().append("nome", adm.getNome()).append("usuario", adm.getUsuario())
							.append("senha", adm.getSenha()).append("ativo", adm.isAtivo()).append("data",
									adm.getDataCadastro()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void consulta() {
		FindIterable<Document> iterable = db.getCollection("administrador").find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document);
			}
		});
	}

	@Override
	public List<Usuario> consultaUsuario(String usuario) {
		try {
			listaUsuario = new ArrayList<Usuario>();
			BasicDBObject query = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("usuario", java.util.regex.Pattern.compile(usuario)));
			obj.add(new BasicDBObject("nome", java.util.regex.Pattern.compile(usuario)));
			query.put("$or", obj);
			System.out.println(query.toString());

			FindIterable<Document> iterable = db.getCollection("administrador").find(query);
			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					Usuario usuario = new Usuario();
					usuario.setNome(document.get("nome").toString());
					usuario.setUsuario(document.get("usuario").toString());
					usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
					listaUsuario.add(usuario);
					System.out.println(document);
				}
			});
			return listaUsuario;
		} catch (Exception e) {
			return null;
		}
	}

}

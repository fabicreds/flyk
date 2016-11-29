package com.tcc.flyk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;
import com.tcc.flyk.util.ClienteUtil;

@Service
public class AmizadeService {

	@Resource
	private ClienteUtil util;

	private ClienteDAOImpl cliDAO = new ClienteDAOImpl();

	public String montarListaAmigos(String id) {
		List<Amizade> listaAmigos = new ArrayList<Amizade>();

		listaAmigos = cliDAO.consultarAmigosById(id);
		buscarDadosAmigos(listaAmigos);
		return mensagemSucesso(listaAmigos);
	}

	private void buscarDadosAmigos(List<Amizade> listaAmigos) {
		if (listaAmigos != null) {
			for (Amizade amizade : listaAmigos) {
				if (amizade.getAmigo() != null) {
					if (amizade.getStatusEnum() != StatusAmizadeEnum.INATIVA) {
						Usuario usuario = cliDAO.consultaLoginById(amizade.getAmigo().getId());
						if (usuario != null) {
							amizade.getAmigo().setNome(usuario.getNome());
							amizade.getAmigo().setEmail(usuario.getEmail());
							amizade.getAmigo().setUsuario(usuario.getUsuario());
							amizade.getAmigo().setTipoCadastro(usuario.getTipoCadastro());
							amizade.getAmigo().setFotoPerfil(usuario.getFotoPerfil());
						}
					} else {
						//apenas enviar amizades com status ativo ou solicitada
						listaAmigos.remove(amizade);
					}
				}
			}
		}

	}

	private String mensagemSucesso(List<Amizade> listaAmigos) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("listaAmigos", util.listaAmigosJSON(listaAmigos));
		if (listaAmigos == null) {
			jObjt.put("numAmigos", 0);
		} else {
			jObjt.put("numAmigos", listaAmigos.size());
		}
		return jObjt.toString();
	}
}

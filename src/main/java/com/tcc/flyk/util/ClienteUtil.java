package com.tcc.flyk.util;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

@Component
public class ClienteUtil {

	@Resource
	private AmizadeUtil amizadeUtil;

	@Resource
	private PrivacidadeUtil privacidadeUtil;

	@Resource
	private PrestadorUtil prestadorUtil;

	@Resource
	private ConversaUtil conversaUtil;
	
	@Resource
	private EnderecoUtil enderecoUtil;

	public JSONObject toJSON(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		if (cliente.getId() != null) {
			jObjt.put("id", cliente.getId());
		}
		if (cliente.getNome() != null) {
			jObjt.put("nome", cliente.getNome());
		}
		if (cliente.getAlias() != null) {
			jObjt.put("alias", cliente.getAlias());
		}
		if (cliente.getEmail() != null) {
			jObjt.put("email", cliente.getEmail());
		}
		if (cliente.getSenha() != null) {
			jObjt.put("senha", cliente.getSenha());
		}
		if (cliente.getFacebookID() != null) {
			jObjt.put("facebookID", cliente.getFacebookID());
		}
		if (cliente.getFotoPerfil() != null) {
			jObjt.put("fotoPerfil", cliente.getFotoPerfil());
		}
		if (cliente.getCPF() != null) {
			jObjt.put("cpf", cliente.getCPF());
		}
		if (cliente.getEndereco() != null) {
			jObjt.put("endereco", enderecoUtil.toJSON(cliente.getEndereco()));
		}
		if (cliente.getListaTelefone() != null) {
			jObjt.put("listaTelefone", listaTelefoneJSON(cliente.getListaTelefone()));
		}
		if (cliente.getNascimento() != null) {
			jObjt.put("nascimento", cliente.getNascimento());
		}
		if (cliente.getApelido() != null) {
			jObjt.put("apelido", cliente.getApelido());
		}
		if (cliente.getAgenda() != null) {
			jObjt.put("agenda", cliente.getAgenda());
		}
		if (cliente.getStatus() != null) {
			jObjt.put("status", cliente.getStatus());
		}
		if (cliente.getListaAmigos() != null) {
			jObjt.put("listaAmigos", listaAmigosJSON(cliente.getListaAmigos()));
		}
		if (cliente.getPrivacidade() != null) {
			jObjt.put("privacidade", privacidadeUtil.toJSON(cliente.getPrivacidade()));
		}
		if (cliente.getTipoCadastro() != null) {
			jObjt.put("tipoCadastro", cliente.getTipoCadastro().getDescricao());
		}
		if (cliente.getPrestador() != null) {
			jObjt.put("prestador", prestadorUtil.toJSON(cliente.getPrestador()));
		}
		if (cliente.getListaPrestadoresRecomendados() != null) {
			jObjt.put("listaPrestadoresRecomendados",
					listaPrestadoresRecomendadosJSON(cliente.getListaPrestadoresRecomendados()));
		}
		if (cliente.getlistaMensagensConversa() != null) {
			jObjt.put("listaMensagensConversa", listaMensagensConversaJSON(cliente.getlistaMensagensConversa()));
		}
		return jObjt;
	}

	private JSONObject listaAmigosJSON(List<Amizade> listaAmigos) {
		JSONObject jObjt = new JSONObject();
		if (listaAmigos != null) {
			int i = 0;
			for (Amizade amizade : listaAmigos) {
				JSONObject jObjt1 = amizadeUtil.toJSON(amizade);
				jObjt.put("amigo" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaTelefoneJSON(List<Telefone> listaTelefone) {
		JSONObject jObjt = new JSONObject();
		if (listaTelefone != null) {
			int i = 0;
			for (Telefone tel : listaTelefone) {
				JSONObject jObjt1 = new JSONObject();
				jObjt1.put("ddd", tel.getDdd());
				jObjt1.put("numero", tel.getNumero());
				jObjt1.put("categoria", tel.getCategoriaTelefone());
				if (tel.getOperadora() != null) {
					jObjt1.put("operadora", tel.getOperadora().getDescricao());
				} else {
					jObjt1.put("operadora", OperadoraEnum.OUTROS.getDescricao());
				}
				jObjt.put("telefone" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaPrestadoresRecomendadosJSON(List<Prestador> listaPrestadoresRecomendados) {
		JSONObject jObjt = new JSONObject();
		if (listaPrestadoresRecomendados != null) {
			int i = 0;
			for (Prestador prestador : listaPrestadoresRecomendados) {
				JSONObject jObjt1 = prestadorUtil.toJSON(prestador);
				jObjt.put("prestador" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	private JSONObject listaMensagensConversaJSON(List<Conversa> listaMensagensConversa) {
		JSONObject jObjt = new JSONObject();
		if (listaMensagensConversa != null) {
			int i = 0;
			for (Conversa conversa : listaMensagensConversa) {
				JSONObject jObjt1 = conversaUtil.toJSON(conversa);
				jObjt.put("conversa" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

}

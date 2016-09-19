package com.tcc.flyk.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Prestador;

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

	@Resource
	private CompromissoUtil compromissoUtil;

	@Resource
	private TelefoneUtil telefoneUtil;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat formatoSimples = new SimpleDateFormat("dd/MM/yyyy");

	public JSONObject toJSON(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		if (cliente.getId() != null) {
			jObjt.put("id", cliente.getId());
		}
		if (cliente.getNome() != null) {
			jObjt.put("nome", cliente.getNome());
		}
		if (cliente.getUsuario() != null) {
			jObjt.put("usuario", cliente.getUsuario());
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
			// jObjt.put("endereco",
			// enderecoUtil.toJSON(cliente.getEndereco()));
			jObjt.put("endereco", endToJSON(cliente.getEndereco()));
		}
		if (cliente.getListaTelefone() != null) {
			jObjt.put("listaTelefone", telefoneUtil.listaTelefoneJSON(cliente.getListaTelefone()));

		}
		if (cliente.getNascimento() != null) {
			jObjt.put("nascimento", format.format(cliente.getNascimento()));
		}
		if (cliente.getApelido() != null) {
			jObjt.put("apelido", cliente.getApelido());
		}
		if (cliente.getListaServicosContratados() != null) {
			jObjt.put("numServicosContratados", cliente.getQtdeServicosContratados());
			jObjt.put("listaServicosContratados", listaServicosContratadosJSON(cliente.getListaServicosContratados()));
		}
		if (cliente.getStatus() != null) {
			jObjt.put("status", cliente.getStatus());
		}
		if (cliente.getListaAmigos() != null) {
			jObjt.put("listaAmigos", listaAmigosJSON(cliente.getListaAmigos()));
		}
		if (cliente.getPrivacidade() != null) {
			// jObjt.put("privacidade",
			// privacidadeUtil.toJSON(cliente.getPrivacidade()));
		}
		if (cliente.getTipoCadastro() != null) {
			jObjt.put("tipoCadastro", cliente.getTipoCadastro().getCodigo());
			jObjt.put("perfil", cliente.getTipoCadastro().getDescricao());
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

	public JSONObject listaAmigosJSON(List<Amizade> listaAmigos) {
		JSONObject jObjt = new JSONObject();
		if (listaAmigos != null) {
			int i = 0;
			for (Amizade amizade : listaAmigos) {
				JSONObject jObjt1 = amizadeUtil.toJSON(amizade);
				jObjt.put("amizade" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	public JSONObject listaPrestadoresRecomendadosJSON(List<Prestador> listaPrestadoresRecomendados) {
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

	public JSONObject listaMensagensConversaJSON(List<Conversa> listaMensagensConversa) {
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

	public JSONObject listaServicosContratadosJSON(List<Compromisso> listaServicosContratados) {
		JSONObject jObjt = new JSONObject();
		if (listaServicosContratados != null) {
			int i = 0;
			for (Compromisso compromisso : listaServicosContratados) {
				JSONObject jObjt1 = compromissoUtil.toJSON(compromisso);
				jObjt.put("compromisso" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}

	public JSONObject endToJSON(Endereco end) {
		JSONObject jObjt = new JSONObject(end);
		if (end.getLogradouro() != null) {
			jObjt.put("logradouro", end.getLogradouro());
		}
		if (end.getBairro() != null) {
			jObjt.put("bairro", end.getBairro());

		}
		/*
		 * if (end.getNumero() != null) { { jObjt.put("numero",
		 * end.getNumero()); }
		 */
		if (end.getComplemento() != null) {
			jObjt.put("complemento", end.getComplemento());
		}
		if (end.getCep() != null) {
			jObjt.put("cep", end.getCep());
		}
		if (end.getCidade() != null) {
			jObjt.put("cidade", end.getCidade());
		}
		if (end.getEstado() != null) {
			jObjt.put("estado", end.getEstado());
		}
		return jObjt;
	}

	public Cliente toCliente(JSONObject json) {
		Cliente cli = new Cliente();

		if (!json.isNull("nome") && !json.getString("nome").isEmpty()) {
			cli.setNome(json.getString("nome"));
		}
		if (!json.isNull("email") && !json.getString("email").isEmpty()) {
			cli.setEmail(json.getString("email"));
		}
		if (!json.isNull("apelido") && !json.getString("apelido").isEmpty()) {
			cli.setApelido(json.getString("apelido"));
		}
		if (!json.isNull("senha") && !json.getString("senha").isEmpty()) {
			cli.setSenha(json.getString("senha"));
		}
		if (!json.isNull("cpf") && !json.getString("cpf").isEmpty()) {
			cli.setCPF(json.getString("cpf"));
		}
		if (!json.isNull("tipoCadastro")) {
			cli.setTipoCadastro(json.getInt("tipoCadastro"));
		}
		if (!json.isNull("datanascimento") && !json.getString("datanascimento").isEmpty()) {
			try {
				cli.setNascimento(formatoSimples.parse(json.getString("datanascimento")));
			} catch (Exception e) {
				cli.setNascimento(null);
			}
		}
		List<JSONObject> listaTelefones = new ArrayList<JSONObject>();
		if (!json.isNull("telefone1")) {
			listaTelefones.add((JSONObject) json.get("telefone1"));
		}
		if (!json.isNull("telefone2")) {
			listaTelefones.add((JSONObject) json.get("telefone2"));
		}
		if (!listaTelefones.isEmpty()) {
			cli.setListaTelefone(telefoneUtil.jsonToListaTelefone(listaTelefones));
		}

		// DADOS DO ENDERE�O
		if (!json.isNull("endereco")) {
			JSONObject enderecoJSON = (JSONObject) json.get("endereco");
			if (enderecoJSON != null) {
				cli.setEndereco(enderecoUtil.toEndereco(enderecoJSON));
			}
		}

		// 'imagem' :$scope.imageSrc
		if (!json.isNull("imagem")) {
			cli.setFotoPerfil(json.getString("imagem"));
		}

		return cli;
	}

}

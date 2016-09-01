package com.tcc.flyk.util;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;

@Component
public class ClienteUtil {

	public JSONObject toJSON(Cliente cliente) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("id", cliente.getId());
		jObjt.put("nome", cliente.getNome());
		jObjt.put("alias", cliente.getAlias());
		jObjt.put("email", cliente.getEmail());
		jObjt.put("senha", cliente.getSenha());
		jObjt.put("facebookID", cliente.getFacebookID());
		jObjt.put("fotoPerfil", cliente.getFotoPerfil());
		jObjt.put("cpf", cliente.getCPF());
		jObjt.put("endereco", enderecoJSON(cliente.getEndereco()));
		jObjt.put("listaTelefone", listaTelefoneJSON(cliente.getListaTelefone()));
		jObjt.put("nascimento", cliente.getNascimento());
		jObjt.put("apelido", cliente.getApelido());
		jObjt.put("agenda", cliente.getAgenda());
		jObjt.put("status", cliente.getStatus());
		jObjt.put("listaAmios", cliente.getListaAmigos());
		jObjt.put("privacidade", cliente.getPrivacidade());
		jObjt.put("tipoCadastro", cliente.getTipoCadastro().getDescricao());
		jObjt.put("prestador", cliente.getPrestador());
		jObjt.put("listaPrestadoresRecomendados", cliente.getListaPrestadoresRecomendados());
		jObjt.put("listaMensagensConversa", cliente.getlistaMensagensConversa());

		return jObjt;
	}

	private JSONObject enderecoJSON(Endereco end) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("logradouro", end.getLogradouro());

		jObjt.put("bairro", end.getBairro());

		jObjt.put("numero", end.getNumero());

		jObjt.put("complemento", end.getComplemento());

		jObjt.put("cep", end.getCep());

		jObjt.put("cidade", end.getCidade());

		jObjt.put("estado", end.getEstado());

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
				if(tel.getOperadora()!=null){
					jObjt1.put("operadora", tel.getOperadora().getDescricao());
				}else{
					jObjt1.put("operadora", OperadoraEnum.OUTROS.getDescricao());
				}
				jObjt.put("telefone" + i, jObjt1);
				i++;
			}
		}
		return jObjt;
	}
}

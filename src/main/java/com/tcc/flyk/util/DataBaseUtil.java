package com.tcc.flyk.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.AvaliacaoPrestador;
import com.tcc.flyk.entity.Categoria;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Contrato;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.CategoriaDAO;
import com.tcc.flyk.persistence.impl.CategoriaDAOImpl;

@Component
public class DataBaseUtil {

	public void montarDadosBasicosCliente(Cliente pessoa, DBObject resultado) {
		// ID
		if (resultado.get("_id") != null) {
			pessoa.setId(String.valueOf(resultado.get("_id")));
		}
		// NOME
		if (resultado.get("nome_completo") != null) {
			pessoa.setNome(String.valueOf(resultado.get("nome_completo")));
		}
		// USUARIO
		if (resultado.get("usuario") != null) {
			pessoa.setUsuario(String.valueOf(resultado.get("usuario")));
		}
		// EMAIL
		if (resultado.get("email") != null) {
			pessoa.setEmail(String.valueOf(resultado.get("email")));
		}
		// EMAIL
		if (resultado.get("data_nascimento") != null) {
			pessoa.setNascimento((Date) resultado.get("data_nascimento"));
			System.out.print("Data nascimento do cliente: " + pessoa.getNascimento());
		}
		// ALIAS
		if (resultado.get("alias") != null) {
			pessoa.setAlias(String.valueOf(resultado.get("alias")));
		}
		// SENHA
		if (resultado.get("senha") != null) {
			pessoa.setSenha(String.valueOf(resultado.get("senha")));
		}
		// ID DO FACEBOOK
		if (resultado.get("facebookID") != null) {
			pessoa.setFacebookID(String.valueOf(resultado.get("facebookID")));
		}
		// CPF
		if (resultado.get("CPF") != null) {
			pessoa.setCPF(String.valueOf(resultado.get("CPF")));
		}
		// FOTO DO PERFIL
		if (resultado.get("foto") != null) {
			pessoa.setFotoPerfil(String.valueOf(resultado.get("foto")));
		}
		// TIPO DE PERFIL
		if (resultado.get("tipo_perfil") != null) {
			String tipoCadastro = String.valueOf(resultado.get("tipo_perfil"));
			if (tipoCadastro.equals("1")) {
				pessoa.setTipoCadastro(TipoCadastroEnum.CLIENTE);
			}
			if (tipoCadastro.equals("2")) {
				pessoa.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
			}
			if (tipoCadastro.equals("3")) {
				pessoa.setTipoCadastro(TipoCadastroEnum.PREMIUM);
			}
			if (tipoCadastro.equals("4")) {
				pessoa.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
			}
		}
		// STATUS DA PESSOA
		if (resultado.get("status_pessoa") != null) {
			pessoa.setStatus(String.valueOf(resultado.get("status_pessoa")));
		}
	}

	public Endereco montarDadosEndereco(DBObject resultado) {
		Endereco enderecoPessoa = new Endereco();
		if (resultado.get("bairro") != null) {
			enderecoPessoa.setBairro(String.valueOf(resultado.get("bairro")));
		}
		if (resultado.get("CEP") != null) {
			enderecoPessoa.setCep(String.valueOf(resultado.get("CEP")));
		}
		if (resultado.get("cidade") != null) {
			enderecoPessoa.setCidade(String.valueOf(resultado.get("cidade")));
		}
		if (resultado.get("complemento") != null) {
			enderecoPessoa.setComplemento(String.valueOf(resultado.get("complemento")));
		}
		if (resultado.get("estado") != null) {
			enderecoPessoa.setEstado(String.valueOf(resultado.get("estado")));
		}
		if (resultado.get("logradouro") != null) {
			enderecoPessoa.setLogradouro(String.valueOf(resultado.get("logradouro")));
		}
		if (resultado.get("numero") != null) {
			enderecoPessoa.setNumero(Integer.valueOf(String.valueOf(resultado.get("numero"))));
		}
		return enderecoPessoa;
	}

	public Privacidade montarDadosPrivacidade(DBObject resultado) {
		Privacidade privacidade = new Privacidade();
		// privacidade_bloco_cpf_cnpj
		if (resultado.get("privacidade_bloco_cpf_cnpj") != null) {
			String codigo = String.valueOf(resultado.get("privacidade_bloco_cpf_cnpj"));
			privacidade.setExibeCPF(Integer.valueOf(codigo));
		}
		// privacidade_bloco_endereco
		if (resultado.get("privacidade_bloco_endereco") != null) {
			String codigo = String.valueOf(resultado.get("privacidade_bloco_endereco"));
			privacidade.setExibeEndereco(Integer.valueOf(codigo));
		}
		// privacidade_bloco_telefone
		if (resultado.get("privacidade_bloco_telefone") != null) {
			String codigo = String.valueOf(resultado.get("privacidade_bloco_telefone"));
			privacidade.setExibeTelefone(Integer.valueOf(codigo));
		}
		// privacidade_bloco_servicos_contratados
		if (resultado.get("privacidade_bloco_servicos_contratados") != null) {
			String codigo = String.valueOf(resultado.get("privacidade_bloco_servicos_contratados"));
			privacidade.setExibeAgenda(Integer.valueOf(codigo));
		}
		return privacidade;
	}

	public List<Telefone> montaDadosTelefones(BasicDBList telefonesDB) {
		List<Telefone> telefones = new ArrayList<Telefone>();
		BasicDBObject[] lightArr = telefonesDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject telefone : lightArr) {
			// shows each item from the lights array
			System.out.println("numero_telefone: " + telefone.get("numero_telefone"));

			Telefone tel = new Telefone();

			// numero
			tel.setNumero(telefone.getString("numero_telefone"));

			// DDD
			if (telefone.get("ddd_telefone") != null) {
				tel.setDdd(Integer.valueOf(telefone.getString("ddd_telefone")));
			}

			// categoria_telefone
			if (telefone.get("categoria_telefone") != null) {
				String categoria = telefone.getString("categoria_telefone");
				tel.setCategoriaTelefone(Integer.valueOf(categoria));
			}

			// operadora_telefone
			if (telefone.get("operadora_telefone") != null) {
				String operadora = telefone.getString("operadora_telefone");
				tel.setOperadora(Integer.valueOf(operadora));
			}

			telefones.add(tel);
		}

		return telefones;
	}

	public List<Amizade> montaDadosAmigos(BasicDBList amigosDB) {
		List<Amizade> amigos = new ArrayList<Amizade>();
		// Varre a lista de telefones, preenchendo o array telefones

		BasicDBObject[] lightArr = amigosDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject amigoDB : lightArr) {
			// shows each item from the lights array
			System.out.println("id_amigo: " + amigoDB.get("id_amigo"));

			Amizade amizade = new Amizade();
			Cliente amigo = new Cliente();

			// numero
			amigo.setId(String.valueOf(amigoDB.get("id_amigo")));
			amizade.setAmigo(amigo);

			// data_amizade
			if (amigoDB.get("data_amizade") != null) {
				amizade.setDataInicioAmizade((Date) amigoDB.get("data_amizade"));
			}

			// status_amizade
			if (amigoDB.get("status_amizade") != null) {
				int codigoStatusAmizade = Integer.valueOf(amigoDB.getString("status_amizade"));
				// faz o set do enum de acordo com o código
				amizade.setStatusEnum(codigoStatusAmizade);
			}

			amigos.add(amizade);
		}
		return amigos;
	}

	public List<Compromisso> montarDadosServicosContratados(BasicDBList servicosContratadosDB) {
		// Varre a lista de compromissos, preenchendo o array agenda
		List<Compromisso> listaServicosContratados = new ArrayList<Compromisso>();
		BasicDBObject[] lightArr = servicosContratadosDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject compromissoDB : lightArr) {

			// Instancia do Compromisso
			Compromisso compromisso = new Compromisso();

			// Instancia do Contrato que farï¿½ parte do Compromisso
			Contrato contrato = new Contrato();

			// Instancia do Prestador que farï¿½ parte do Contrato
			Prestador prestador = new Prestador();

			// Instancia da Avaliacao do Prestador que farï¿½ parte do Contrato
			AvaliacaoPrestador avaliacaoPrestador = new AvaliacaoPrestador();

			// Instancia de Categoria de Sevico que farï¿½ parte do contrato
			Categoria categoria = new Categoria();

			// Setando os dados do Prestador
			if (compromissoDB.get("id_prestador") != null) {
				prestador.setId(compromissoDB.getString("id_prestador"));
			}

			if (compromissoDB.getDate("data_inicio_servico_contratado") != null) {
				compromisso.setDataInicio(compromissoDB.getDate("data_inicio_servico_contratado"));
			}
			if (compromissoDB.getDate("data_fim_servico_contratado") != null) {
				compromisso.setDataFim(compromissoDB.getDate("data_fim_servico_contratado"));
			}
			compromisso.setIndicadorFerias(false);

			// Compromisso - Custo
			if (compromissoDB.get("custo_servico_contratado") != null) {
				contrato.setCusto(Double.valueOf(compromissoDB.getString("custo_servico_contratado")));
			}

			// Compromisso - Status
			if (compromissoDB.getString("status_servico_contratado") != null) {
				compromisso.setStatus(Integer.valueOf(compromissoDB.getString("status_servico_contratado")));
			}

			if (compromissoDB.get("data_avaliacao_servico_contratado") != null) {
				contrato.setDataAvaliacaoServico(compromissoDB.getDate("data_avaliacao_servico_contratado"));
			}

			// Setando os Dados da Avaliacao do Prestador
			if (compromissoDB.get("nota_preco") != null) {
				avaliacaoPrestador.setAvaliacaoPreco(Integer.valueOf(compromissoDB.getString("nota_preco")));
			}
			if (compromissoDB.get("nota_pontualidade") != null) {
				avaliacaoPrestador
						.setAvaliacaoProfissionalismo(Integer.valueOf(compromissoDB.getString("nota_pontualidade")));
			}
			if (compromissoDB.get("nota_qualidade") != null) {
				avaliacaoPrestador.setAvaliacaoQualidade(Integer.valueOf(compromissoDB.getString("nota_qualidade")));
			}
			if (compromissoDB.get("nota_profissionalismo") != null) {
				avaliacaoPrestador
						.setAvaliacaoPontualidade(Integer.valueOf(compromissoDB.getString("nota_profissionalismo")));
			}

			if (compromissoDB.get("nota_contratante") != null) {
				contrato.setAvaliacaoContratante(Integer.valueOf(compromissoDB.getString("nota_contratante")));
			}

			// Setando os Dados da Categoria do Servico
			if (compromissoDB.get("id_categoria_servico_contratado") != null) {
				categoria.setId(compromissoDB.getString("id_categoria_servico_contratado"));
			}
			
			if (compromissoDB.get("descricao_servico_contratado") != null) {
				contrato.setDescricaoServico(compromissoDB.getString("descricao_servico_contratado"));
			}

			contrato.setPrestador(prestador);
			contrato.setAvaliacaoPrestador(avaliacaoPrestador);
			contrato.setServico(categoria);
			// Setando os Dados do Compromisso
			compromisso.setContrato(contrato);
			// Adiciona o compromisso na agenda
			listaServicosContratados.add(compromisso);
		}
		return listaServicosContratados;
	}

	public List<String> montarDadosRecomendacoesDadas(BasicDBList recomendacoesDadasBD) {
		List<String> recomendacoesDadas = new ArrayList<String>();
		BasicDBObject[] lightArr = recomendacoesDadasBD.toArray(new BasicDBObject[0]);
		for (BasicDBObject recomendacaoDadaDB : lightArr) {
			recomendacoesDadas.add(recomendacaoDadaDB.getString("id_usuario_recomendado"));
		}
		return recomendacoesDadas;
	}
	
	public List<String> montarDadosRecomendacoesRecebidas(BasicDBList recomendacoesRecebidasBD) {
		List<String> recomendacoesDadas = new ArrayList<String>();
		BasicDBObject[] lightArr = recomendacoesRecebidasBD.toArray(new BasicDBObject[0]);
		for (BasicDBObject recomendacaoDadaDB : lightArr) {
			recomendacoesDadas.add(recomendacaoDadaDB.getString("id_usuario_recomendador"));
		}
		return recomendacoesDadas;
	}


	public List<Conversa> montarDadosConversas(BasicDBList conversaDB) {
		List<Conversa> mensagens = new ArrayList<Conversa>();
		BasicDBObject[] lightArr = conversaDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject mensagemDB : lightArr) {
			// shows each item from the lights array

			Conversa mensagem = new Conversa();

			// flag enviado ou recebido
			mensagem.setflagEnviadoRecebido(String.valueOf(mensagemDB.get("flagEnviadoOuRecebido")));

			// id
			mensagem.setidUsuario(String.valueOf(mensagemDB.get("id_usuario_conversa")));

			// data_hora_mensagem
			if (mensagemDB.get("data_hora_mensagem") != null) {
				mensagem.setData((Date) mensagemDB.get("data_hora_mensagem"));
			}

			// mensagem
			if (mensagemDB.getString("mensagem") != null) {
				mensagem.setMsg(mensagemDB.getString("mensagem"));
			}

			mensagens.add(mensagem);
		}
		return mensagens;
	}

	public List<Categoria> montarDadosListaServicos(BasicDBList servicosDB) {
		List<Categoria> listaServicos = new ArrayList<Categoria>();
		BasicDBObject[] lightArr = servicosDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject servicoDB : lightArr) {
			Categoria categoriaServico = new Categoria();
			CategoriaDAO catDAO = new CategoriaDAOImpl();
		
			
			
			if (servicoDB.get("id_categoria_servico_prestado") != null) {
				categoriaServico.setId(servicoDB.getString("id_categoria_servico_prestado"));
				
				//Categoria x = catDAO.consultarCategoriaPorId(categoriaServico.getId());
				//System.out.println(x.getDescricaoCategoria());
				categoriaServico.setNomeCategoria(catDAO.consultarCategoriaPorId((String) servicoDB.get("id_categoria_servico_prestado")).getNomeCategoria());
				
			}
			listaServicos.add(categoriaServico);
		}
		return listaServicos;
	}

	public List<Compromisso> montarDadosListaContratosServicosPrestados(BasicDBList servicosContratadosDB) {
		// Varre a lista de compromissos, preenchendo o array agenda
		List<Compromisso> listaServicosContratados = new ArrayList<Compromisso>();
		BasicDBObject[] lightArr = servicosContratadosDB.toArray(new BasicDBObject[0]);
		for (BasicDBObject compromissoDB : lightArr) {

			// Instancia do Compromisso
			Compromisso compromisso = new Compromisso();

			// Instancia do Contrato que farï¿½ parte do Compromisso
			Contrato contrato = new Contrato();

			// Instancia do Cliente que farï¿½ parte do Contrato
			Cliente cliente = new Cliente();

			// Instancia da Avaliacao do Prestador que farï¿½ parte do Contrato
			AvaliacaoPrestador avaliacaoPrestador = new AvaliacaoPrestador();

			// Instancia de Categoria de Sevico que farï¿½ parte do contrato
			Categoria categoria = new Categoria();

			// Setando os dados do Prestador
			if (compromissoDB.get("id_cliente_contratante") != null) {
				cliente.setId(compromissoDB.getString("id_cliente_contratante"));
			}

			if (compromissoDB.getDate("data_inicio_servico_prestado") != null) {
				compromisso.setDataInicio(compromissoDB.getDate("data_inicio_servico_prestado"));
			}
			if (compromissoDB.getDate("data_fim_servico_prestado") != null) {
				compromisso.setDataFim(compromissoDB.getDate("data_fim_servico_prestado"));
			}

			if (compromissoDB.get("custo_servico_prestado") != null) {
				contrato.setCusto(Double.valueOf(compromissoDB.getString("custo_servico_prestado")));
			}

			// Compromisso - Status
			if (compromissoDB.getString("status_servico_prestado") != null) {
				compromisso.setStatus(Integer.valueOf(compromissoDB.getString("status_servico_prestado")));
			}
			if (compromissoDB.get("data_avaliacao_servico_prestado") != null) {
				contrato.setDataAvaliacaoServico(compromissoDB.getDate("data_avaliacao_servico_prestado"));
			}
			// Setando os Dados da Avaliacao do Prestador
			if (compromissoDB.get("nota_contratante") != null) {
				contrato.setAvaliacaoContratante(Integer.valueOf(compromissoDB.getString("nota_contratante")));
			}

			// Setando os Dados da Avaliacao do Prestador
			if (compromissoDB.get("nota_preco") != null) {
				avaliacaoPrestador.setAvaliacaoPreco(Integer.valueOf(compromissoDB.getString("nota_preco")));
			}
			if (compromissoDB.get("nota_pontualidade") != null) {
				avaliacaoPrestador
						.setAvaliacaoProfissionalismo(Integer.valueOf(compromissoDB.getString("nota_pontualidade")));
			}
			if (compromissoDB.get("nota_qualidade") != null) {
				avaliacaoPrestador.setAvaliacaoQualidade(Integer.valueOf(compromissoDB.getString("nota_qualidade")));
			}
			if (compromissoDB.get("nota_profissionalismo") != null) {
				avaliacaoPrestador
						.setAvaliacaoPontualidade(Integer.valueOf(compromissoDB.getString("nota_profissionalismo")));
			}

			// Setando os Dados da Categoria do Servico
			if (compromissoDB.get("id_categoria_servico_prestado") != null) {
				categoria.setId(compromissoDB.getString("id_categoria_servico_prestado"));
			}
			
			if (compromissoDB.get("descricao_servico_prestado") != null) {
				contrato.setDescricaoServico(compromissoDB.getString("descricao_servico_prestado"));
			}

			contrato.setCliente(cliente);
			contrato.setAvaliacaoPrestador(avaliacaoPrestador);
			contrato.setServico(categoria);
			// Setando os Dados do Compromisso
			compromisso.setContrato(contrato);

			// Adiciona o compromisso na agenda
			listaServicosContratados.add(compromisso);
		}
		return listaServicosContratados;
	}

}

package com.tcc.flyk.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.tcc.flyk.entity.Amizade;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.MongoDB;
import com.tcc.flyk.util.DataBaseUtil;

public class ClienteDAOImpl extends MongoDB implements ClienteDAO {
	
	private DataBaseUtil dbUtil = new DataBaseUtil();

	public ClienteDAOImpl() {
		super();
	}

	@Override
	public void consulta() {
		DBCursor cursor = db.getCollection("FLYK").find();

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	@Override
	public boolean inserirNovoCliente(Cliente pessoa) {

		System.out.println("inicio addCliente2");
		// Nome completo
		BasicDBObject doc = new BasicDBObject();
		doc.put("nome_completo", pessoa.getNome());

		// Usuario
		if (pessoa.getUsuario() != null) {
			doc.put("usuario", pessoa.getUsuario());
		}

		// Email
		if (pessoa.getEmail() != null) {
			doc.put("email", pessoa.getEmail());
		}

		// Data de Nascimento
		if (pessoa.getNascimento() != null) {
			doc.put("data_nascimento", pessoa.getNascimento());
		}

		// Senha
		if (pessoa.getSenha() != null) {
			doc.put("senha", pessoa.getSenha());
		}

		System.out.println("1");
		// facebookID
		if (pessoa.getFacebookID() != null) {
			doc.put("facebookID", pessoa.getFacebookID());
		}

		System.out.println("15");
		// CPF
		if (pessoa.getCPF() != null) {
			doc.put("CPF", pessoa.getCPF());
		}

		System.out.println("16");
		// Foto do perfil
		if (pessoa.getFotoPerfil() != null) {
			System.out.println("17");
			doc.put("foto", pessoa.getFotoPerfil());
		}
		System.out.println("2");

		// Tipo de perfil
		if (pessoa.getTipoCadastro() != null) {
			doc.put("tipo_perfil", pessoa.getTipoCadastro().getCodigo());
		}
		// Status
		if (pessoa.getStatus() != null) {
			doc.put("status_pessoa", pessoa.getStatus());
		}
		
		//Imagem
		if (pessoa.getFotoPerfil() != null){
			doc.put("foto", pessoa.getFotoPerfil());
		}

		if (pessoa.getEndereco() != null) {
			// Logradouro
			if (pessoa.getEndereco().getLogradouro() != null) {
				doc.put("logradouro", pessoa.getEndereco().getLogradouro());
			}

			System.out.println("3");
			// Numero
			if (pessoa.getEndereco().getNumero() == 0) {
				doc.put("numero", pessoa.getEndereco().getNumero());
			}

			System.out.println("31");
			// Complemento
			if (pessoa.getEndereco().getComplemento() != null) {
				doc.put("complemento", pessoa.getEndereco().getComplemento());
			}

			System.out.println("32");
			// Bairro
			if (pessoa.getEndereco().getBairro() != null) {
				doc.put("bairro", pessoa.getEndereco().getBairro());
			}

			System.out.println("33");
			// Cidade
			if (pessoa.getEndereco().getCidade() != null) {
				doc.put("cidade", pessoa.getEndereco().getCidade());
			}

			System.out.println("34");
			// Estado
			if (pessoa.getEndereco().getEstado() != null) {
				doc.put("estado", pessoa.getEndereco().getEstado());
			}

			System.out.println("4");
			// CEP
			if (pessoa.getEndereco().getCep() != null) {
				doc.put("CEP", pessoa.getEndereco().getCep());
			}
		}
		/*
		 * System.out.println("5"); //TIPO DE CADASTRO
		 * if(pessoa.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){ //CNPJ
		 * if(pessoa.getPrestador().getCnpj()!=null)){ doc.put("CNPJ",
		 * pessoa.getPrestador().getCnpj()); }
		 * 
		 * if(pessoa.getTipoCadastro()==TipoCadastroEnum.PREMIUM){ //Valor
		 * Premium doc.put("valor_premium",
		 * pessoa.getPrestador().getValorPremium()); } }
		 * 
		 * 
		 * 
		 * System.out.println("5"); //TIPO DE CADASTRO
		 * if(pessoa.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){ //CNPJ
		 * if(pessoa.getPrestador().getCnpj()!=null)){ doc.put("CNPJ",
		 * pessoa.getPrestador().getCnpj()); }
		 * 
		 * if(pessoa.getTipoCadastro()==TipoCadastroEnum.PREMIUM){ //Valor
		 * Premium doc.put("valor_premium",
		 * pessoa.getPrestador().getValorPremium()); } }
		 */
		// PRIVACIDADE
		if (pessoa.getPrivacidade() != null) {

			// agenda
			if (pessoa.getPrivacidade().getExibeAgenda() != null) {
				if (pessoa.getTipoCadastro() == TipoCadastroEnum.CLIENTE) {
					doc.put("privacidade_bloco_servicos_contratados",
							pessoa.getPrivacidade().getExibeAgenda().getCodigo());
				} else {
					doc.put("privacidade_bloco_servicos_prestados",
							pessoa.getPrivacidade().getExibeAgenda().getCodigo());
				}
			}

			// CPF
			if (pessoa.getPrivacidade().getExibeCPF() != null) {
				doc.put("privacidade_bloco_cpf_cnpj", pessoa.getPrivacidade().getExibeCPF().getCodigo());
			}

			// Endere�o
			if (pessoa.getPrivacidade().getExibeEndereco() != null) {
				doc.put("privacidade_bloco_endereco", pessoa.getPrivacidade().getExibeEndereco().getCodigo());
			}

			// Telefone
			if (pessoa.getPrivacidade().getExibeTelefone() != null) {
				doc.put("privacidade_bloco_telefone", pessoa.getPrivacidade().getExibeTelefone().getCodigo());
			}
		}

		// ******************************Telefones******************************//
		if (pessoa.getListaTelefone() != null) {

			int count = pessoa.getListaTelefone().size();
			System.out.println("qtd telefones: " + String.valueOf(count));

			// Varre a lista de telefones, inserindo um por um
			BasicDBList telefones = new BasicDBList();
			for (Telefone tel : pessoa.getListaTelefone()) {

				BasicDBObject telefone = new BasicDBObject();

				// Numero
				telefone.put("numero_telefone", String.valueOf(tel.getNumero()));

				// Categoria
				if (tel.getCategoriaTelefone() != null) {
					telefone.put("categoria_telefone", String.valueOf(tel.getCategoriaTelefone().getCodigo()));
				}

				// DDD
				if (tel.getDdd() != 0) {
					telefone.put("ddd_telefone", String.valueOf(tel.getDdd()));
				}

				// Operadora
				if (tel.getOperadora() != null) {
					telefone.put("operadora_telefone", String.valueOf(tel.getOperadora().getCodigo()));
				}
				System.out.println(String.valueOf(tel.getNumero()));

				telefones.add(telefone);
				System.out.println("telefone adicionado");
			}
			doc.put("telefones", telefones);
		} else {
			System.out.println("sem telefones");
		}

		// ******************************Amigos******************************//
		if (pessoa.getListaAmigos() != null) {

			int count = pessoa.getListaAmigos().size();
			System.out.println("qtd amigos: " + String.valueOf(count));

			BasicDBList amigos = new BasicDBList();

			// Varre a lista de amigos, inserindo um por um
			for (int i = 0; i < count; i++) {
				System.out.println("ID do amigo: " + String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId()));

				BasicDBObject amigo = new BasicDBObject();

				// DAQUI PRA BAIXO � S� PREENCHER OS DADOS DA LISTA DE
				// AMIGOS
				// Inicia o documento e grava o n�mero
				amigo.put("id_amigo", String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId()));

				// Data da amizade
				if (pessoa.getListaAmigos().get(i).getDataInicioAmizade() != null) {
					amigo.put("data_amizade", pessoa.getListaAmigos().get(i).getDataInicioAmizade());
				}

				// Status da amizade
				if (pessoa.getListaAmigos().get(i).getStatusEnum() != null) {
					if (pessoa.getListaAmigos().get(i).getStatusEnum() == StatusAmizadeEnum.ATIVA) {
						amigo.put("status_amizade", "1");
					} else {
						amigo.put("status_amizade", "2");
					}
				}

				amigos.add(amigo);
			}
			doc.put("amigos", amigos);
		} else {
			System.out.println("sem amigos :(");
		}

		// ******************************Recomenda��es dadas a outro
		// usu�rio******************************//
		if (pessoa.getListaPrestadoresRecomendados() != null) {

			int count = pessoa.getListaPrestadoresRecomendados().size();
			System.out.println("qtd prestadores recomendados: " + String.valueOf(count));

			BasicDBList prestadoresRecomendados = new BasicDBList();
			// Varre a lista de prestadores recomendados, inserindo um por um
			for (int i = 0; i < count; i++) {
				System.out.println(
						"ID do prestador: " + String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId()));

				BasicDBObject prestadorRecomendado = new BasicDBObject();

				// Grava o n�mero
				prestadorRecomendado.put("id_usuario_recomendado",
						String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId()));

				prestadoresRecomendados.add(prestadorRecomendado);
			}

			doc.put("recomendacoes_dadas", prestadoresRecomendados);
		} else {
			System.out.println("sem recomenda��es dadas");
		}

		// ******************************Mensagens de
		// conversa******************************//
		if (pessoa.getlistaMensagensConversa() != null) {

			int count = pessoa.getlistaMensagensConversa().size();
			System.out.println("qtd msgs: " + String.valueOf(count));

			BasicDBList mensagens = new BasicDBList();
			// Varre a lista de mensagens, inserindo uma por uma
			for (int i = 0; i < count; i++) {
				BasicDBObject mensagem = new BasicDBObject();

				// Inicia o documento e grava o id da pessoa com a qual o
				// cliente est� conversando
				mensagem.put("id_usuario_conversa", pessoa.getlistaMensagensConversa().get(i).getIdUsuario());

				// flagEnviadoOuRecebido
				mensagem.put("flagEnviadoOuRecebido",
						pessoa.getlistaMensagensConversa().get(i).getflagEnviadoRecebido());

				// data_hora_mensagem
				if (pessoa.getlistaMensagensConversa().get(i).getData() != null) {
					mensagem.put("data_hora_mensagem", pessoa.getlistaMensagensConversa().get(i).getData());
				}

				// mensagem
				if (pessoa.getlistaMensagensConversa().get(i).getMsg() != null) {
					mensagem.put("mensagem", pessoa.getlistaMensagensConversa().get(i).getMsg());
				}

				mensagens.add(mensagem);
			}

			doc.put("mensagens_de_conversa", mensagens);
		} else {
			System.out.println("sem conversas");
		}

		// ******************************Servicos
		// contratados******************************//
		if (pessoa.getListaServicosContratados() != null) {

			int count = pessoa.getListaServicosContratados().size();
			System.out.println("qtd de servicos contratados: " + String.valueOf(count));

			BasicDBList servicosContratados = new BasicDBList();

			// Varre a lista de compromissos, inserindo uma por uma
			for (Compromisso compromisso : pessoa.getListaServicosContratados()) {

				BasicDBObject servicoContratado = new BasicDBObject();

				System.out.println("0");
				// Inicia o documento e grava o id do prestador
				if (compromisso.getContrato() != null && compromisso.getContrato().getPrestador() != null) {
					servicoContratado.put("id_prestador", compromisso.getContrato().getPrestador().getId());
				}
				System.out.println("1");
				// data_servico_contratado
				if (compromisso.getDataInicio() != null) {
					servicoContratado.put("data_servico_contratado", compromisso.getDataInicio());
				}
				System.out.println("2");

				// custo_servico_contratado
				if (compromisso.getContrato() != null) {
					servicoContratado.put("custo_servico_contratado",
							String.valueOf(compromisso.getContrato().getCusto()));
				}

				// categoria do servico contratado
				if (compromisso.getContrato() != null && compromisso.getContrato().getServico() != null) {
					servicoContratado.put("servico_contratado", compromisso.getContrato().getServico().getId());
				}

				System.out.println("3");
				// status_servico_contratado
				if (compromisso.getStatus() != null) {
					servicoContratado.put("status_servico_contratado", compromisso.getStatus().getCodigo());
				}

				System.out.println("4");
				// data_avaliacao_servico_contratado
				if (compromisso.getContrato() != null) {
					servicoContratado.put("data_avaliacao_servico_contratado",
							compromisso.getContrato().getDataAvaliacaoServico());
				}

				System.out.println("5");
				// nota_preco
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_preco",
							String.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoPreco()));
				}
				System.out.println("6");

				// nota_pontualidade
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_pontualidade", String
							.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoPontualidade()));
				}
				System.out.println("7");

				// nota_qualidade
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_qualidade",
							String.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoQualidade()));
				}
				System.out.println("8");

				// nota_profissionalismo
				if (compromisso.getContrato() != null && compromisso.getContrato().getAvaliacaoPrestador() != null) {
					servicoContratado.put("nota_profissionalismo", String
							.valueOf(compromisso.getContrato().getAvaliacaoPrestador().getAvaliacaoProfissionalismo()));
				}

				System.out.println("9");
				servicosContratados.add(servicoContratado);
				System.out.println("10");
			}
			doc.put("servicos_contratados", servicosContratados);
		} else {
			System.out.println("sem servicos contratados");
		}

		System.out.println("11");
		System.out.println(doc);
		// Insere o cliente
		System.out.println(doc.toString());
		try {
			super.db.getCollection("FLYK").insert(doc);
			System.out.println("Usu�rio cadastrado com sucesso");
		} catch (Exception e) {
			System.out.println("ERRO:" + e.getStackTrace());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		consultaTudo();

		return true;
	}

	@Override
	public Usuario consultaLogin(String email) {
		try {
			DBCollection collection = db.getCollection("FLYK");
			BasicDBObject filtro = new BasicDBObject(new Document("email", email));
			DBCursor cursor = collection.find(filtro);
			DBObject resultado;

			Usuario user = new Usuario();
			// Busca campos de resultado
			if (cursor.hasNext()) {
				resultado = cursor.next();
				System.out.println(resultado);
				System.out.println("************************");

				// ID
				user.setId(String.valueOf(resultado.get("_id")));
				// NOME
				user.setNome(String.valueOf(resultado.get("nome_completo")));
				// EMAIL
				user.setEmail(String.valueOf(resultado.get("email")));
				// SENHA
				user.setSenha(String.valueOf(resultado.get("senha")));
				// USUARIO (EMAIL SEM O @)
				user.setUsuario(String.valueOf(resultado.get("usuario")));
				// TIPO CADASTRO
				String tipoCadastro = String.valueOf(resultado.get("tipo_perfil"));
				if (tipoCadastro.equals("1")) {
					user.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				}
				if (tipoCadastro.equals("2")) {
					user.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
				}
				if (tipoCadastro.equals("3")) {
					user.setTipoCadastro(TipoCadastroEnum.PREMIUM);
				}
				if (tipoCadastro.equals("4")) {
					user.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				}
				// FLAG ATIVO
				String flagAtivo = String.valueOf(resultado.get("status_pessoa"));
				if (flagAtivo.equals("A")) {
					user.setAtivo(true);
				} else {
					user.setAtivo(false);
				}
				/*************************
				 * MENSAGENS DE TESTE, REMOVA CASO QUERIA, MAS N�O D� COMMIT
				 * PLEASE
				 **********************/
				System.out.println("**************");
				System.out.println(user.getNome());
				System.out.println(user.getId());
				System.out.println(user.getSenha());
				System.out.println(user.getTipoCadastro());
				System.out.println("**************");

			} else {
				System.out.println("Consulta de cliente pelo email " + email + " n�o encontrou valores.");
				return null;
			}

			return user;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cliente consultaClientePorId(String idCliente) {
		consultaTudo();
		System.out.println("CONSULTA CLIENTE - IN�CIO");

		Cliente pessoa = new Cliente(); // Cliente que ser� retornado

		DBCollection collection = db.getCollection("FLYK");
		// BasicDBObject filtro = new BasicDBObject(new Document("_id",new
		// ObjectId(idCliente)));
		// Metodo acima alterado para
		BasicDBObject filtro = new BasicDBObject("_id", new ObjectId(idCliente));

		DBCursor cursor = collection.find(filtro);
		DBObject resultado;

		// Busca campos de resultado
		if (cursor.hasNext()) {
			resultado = cursor.next();
			System.out.println(resultado);
			System.out.println("************************");

			// ID
			pessoa.setId(idCliente);
			dbUtil.montarDadosBasicosCliente(pessoa, resultado);

			// ENDERE�O
			Endereco enderecoPessoa = dbUtil.montarDadosEndereco(resultado);
			;
			pessoa.setEndereco(enderecoPessoa);

			// PRIVACIDADE
			Privacidade privacidade = dbUtil.montarDadosPrivacidade(resultado);
			pessoa.setPrivacidade(privacidade);

			// ********************* LISTA DE TELEFONES *********************
			// Busca a lista de telefones e coloca na telefonesBD
			BasicDBList telefonesDB = (BasicDBList) resultado.get("telefones");
			if (telefonesDB != null) {
				// Varre a lista de telefones, preenchendo o array telefones
				List<Telefone> telefones = dbUtil.montaDadosTelefones(telefonesDB);
				// Adiciona o array telefones na pessoa
				pessoa.setListaTelefone(telefones);
			}
			System.out.println(" telefones fim");

			// ********************* LISTA DE AMIGOS *********************
			// Busca a lista de telefones e coloca na telefonesBD
			BasicDBList amigosDB = (BasicDBList) resultado.get("amigos");

			if (amigosDB != null) {
				// Varre a lista de telefones, preenchendo o array telefones
				List<Amizade> amigos = dbUtil.montaDadosAmigos(amigosDB);
				// Adiciona o array telefones na pessoa
				pessoa.setListaAmigos(amigos);
			}

			// ********************* LISTA DE SERVICOS CONTRATADOS
			// *********************
			// Busca a lista de compromissos e coloca na servicosContratadosDB
			BasicDBList servicosContratadosDB = (BasicDBList) resultado.get("servicos_contratados");

			if (servicosContratadosDB != null) {
				List<Compromisso> agenda = dbUtil.montarDadosServicosContratados(idCliente, servicosContratadosDB);
				// Adiciona o array compromissos na pessoa
				pessoa.setListaServicosContratados(agenda);
			}

			// ********************* LISTA DE RECOMENDA��ES DADAS
			// *********************
			// Busca a lista de prestadores recomendados e coloca na telefonesBD
			BasicDBList recomendacoesDadasBD = (BasicDBList) resultado.get("recomendacoes_dadas");

			if (recomendacoesDadasBD != null) {
				// Varre a lista de prestadores recomendados, preenchendo o
				// array recomendacoesDadas
				List<Prestador> recomendacoesDadas = dbUtil.montarDadosRecomendacoesDadas(recomendacoesDadasBD);
				// Adiciona o array prestadores recomendados na pessoa
				pessoa.setListaPrestadoresRecomendados(recomendacoesDadas);
			}

			// ********************* LISTA DE CONVERSAS *********************
			// Busca a lista de conversa e coloca na conversaDB
			BasicDBList conversaDB = (BasicDBList) resultado.get("mensagens_de_conversa");

			if (conversaDB != null) {
				// Varre a lista de conversa, preenchendo o array mensagens
				List<Conversa> mensagens = dbUtil.montarDadosConversas(conversaDB);
				// Adiciona o array mensagens na pessoa
				pessoa.setlistaMensagensConversa(mensagens);
			}

		} else {
			System.out.println("Consulta de clientes pelo id " + idCliente + " não encontrou valores.");
			return null;
		}

		// Retorna a pessoa para o chamador
		return pessoa;
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

	// ****************************CONSULTA TODOS OS DOCUMENTOS DO
	// BANCO****************************//
	@Override
	public void consultaTudo() {

		FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document);
			}
		});
	}

	public Usuario consultaLoginById(String id) {
		try {
			DBCollection collection = db.getCollection("FLYK");
			BasicDBObject filtro = new BasicDBObject("_id", new ObjectId(id));
			DBCursor cursor = collection.find(filtro);
			DBObject resultado;

			Usuario user = new Usuario();
			// Busca campos de resultado
			if (cursor.hasNext()) {
				resultado = cursor.next();
				System.out.println(resultado);
				System.out.println("************************");

				// ID
				user.setId(String.valueOf(resultado.get("_id")));
				// NOME
				user.setNome(String.valueOf(resultado.get("nome_completo")));
				// EMAIL
				user.setEmail(String.valueOf(resultado.get("email")));
				// SENHA
				user.setSenha(String.valueOf(resultado.get("senha")));
				// USUARIO (EMAIL SEM O @)
				user.setUsuario(String.valueOf(resultado.get("usuario")));
				// TIPO CADASTRO
				String tipoCadastro = String.valueOf(resultado.get("tipo_perfil"));
				if (tipoCadastro.equals("1")) {
					user.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				}
				if (tipoCadastro.equals("2")) {
					user.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
				}
				if (tipoCadastro.equals("3")) {
					user.setTipoCadastro(TipoCadastroEnum.PREMIUM);
				}
				if (tipoCadastro.equals("4")) {
					user.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				}
				// FLAG ATIVO
				String flagAtivo = String.valueOf(resultado.get("status_pessoa"));
				if (flagAtivo.equals("A")) {
					user.setAtivo(true);
				} else {
					user.setAtivo(false);
				}
				/*************************
				 * MENSAGENS DE TESTE, REMOVA CASO QUERIA, MAS N�O D� COMMIT
				 * PLEASE
				 **********************/
				System.out.println("**************");
				System.out.println(user.getNome());
				System.out.println(user.getId());
				System.out.println(user.getSenha());
				System.out.println(user.getTipoCadastro());
				System.out.println("**************");

			} else {
				System.out.println("Consulta de cliente pelo id " + id + " n�o encontrou valores.");
				return null;
			}

			return user;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cliente atualizaCliente(String id, Cliente c) {
		try {
			Cliente cli = new Cliente();
			BasicDBObject updateQuery = new BasicDBObject();

			if (c.getListaTelefone() != null) {

				int count = c.getListaTelefone().size();
				System.out.println("qtd telefones: " + String.valueOf(count));

				// Varre a lista de telefones, inserindo um por um
				BasicDBList telefones = new BasicDBList();
				for (int i = 0; i < count; i++) {

					BasicDBObject telefone = new BasicDBObject();

					// Numero
					telefone.put("numero_telefone", String.valueOf(c.getListaTelefone().get(i).getNumero()));

					// Categoria
					if (c.getListaTelefone().get(i).getCategoriaTelefone() != null) {
						telefone.put("categoria_telefone",
								String.valueOf(c.getListaTelefone().get(i).getCategoriaTelefone().getCodigo()));
					}

					// DDD
					if (c.getListaTelefone().get(i).getDdd() == 0) {
						telefone.put("ddd_telefone", String.valueOf(c.getListaTelefone().get(i).getDdd()));
					}

					// Operadora
					if (c.getListaTelefone().get(i).getOperadora() != null) {
						telefone.put("operadora_telefone",
								String.valueOf(c.getListaTelefone().get(i).getOperadora().getCodigo()));
					}
					System.out.println(String.valueOf(c.getListaTelefone().get(i).getNumero()));

					telefones.add(telefone);
					System.out.println("telefone adicionado uhul");
				}

				updateQuery.append("$set", new BasicDBObject().append("CPF", c.getCPF()).append("email", c.getEmail())
						.append("telefones", telefones).append("logradouro", c.getEndereco().getLogradouro())
						.append("bairro", c.getEndereco().getBairro()).append("cep", c.getEndereco().getCep())
						.append("cidade", c.getEndereco().getCidade()).append("estado", c.getEndereco().getEstado())
						.append("complemento", c.getEndereco().getComplemento())
						.append("numero", c.getEndereco().getNumero())

				);

			}

			updateQuery.append("$set",
					new BasicDBObject().append("CPF", c.getCPF()).append("email", c.getEmail())
							// .append("telefones", telefones)
							.append("logradouro", c.getEndereco().getLogradouro())
							.append("bairro", c.getEndereco().getBairro()).append("cep", c.getEndereco().getCep())
							.append("cidade", c.getEndereco().getCidade()).append("estado", c.getEndereco().getEstado())
							.append("complemento", c.getEndereco().getComplemento())
							.append("numero", c.getEndereco().getNumero()));
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("_id", new ObjectId(id));

			DBCollection collection = db.getCollection("FLYK");

			collection.update(searchQuery, updateQuery);
			cli = this.consultaClientePorId(String.valueOf(id));

			return cli;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Amizade> consultarAmigosById(String id) {
		List<Amizade> amigos = new ArrayList<Amizade>();

		DBCollection collection = db.getCollection("FLYK");
		BasicDBObject filtro = new BasicDBObject("_id", new ObjectId(id));
		BasicDBObject fieldObject = new BasicDBObject();
		fieldObject.put("amigos", 1);

		DBCursor cursor = collection.find(filtro, fieldObject);
		DBObject resultado;

		// Busca campos de resultado
		if (cursor.hasNext()) {
			resultado = cursor.next();
			BasicDBList amigosDB = (BasicDBList) resultado.get("amigos");
			if (amigosDB != null) {
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
						if (amigoDB.getString("status_amizade").equals("1")) {
							amizade.setStatusEnum(StatusAmizadeEnum.ATIVA);
						} else {
							amizade.setStatusEnum(StatusAmizadeEnum.INATIVA);
						}
					}

					amigos.add(amizade);
				}
				return amigos;
			}
		}
		return null;

	}

}

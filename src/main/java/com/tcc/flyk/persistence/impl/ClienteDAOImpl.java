package com.tcc.flyk.persistence.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.tcc.flyk.entity.AvaliacaoPrestador;
import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Compromisso;
import com.tcc.flyk.entity.Contrato;
import com.tcc.flyk.entity.Conversa;
import com.tcc.flyk.entity.Endereco;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.entity.Privacidade;
import com.tcc.flyk.entity.Telefone;
import com.tcc.flyk.entity.Usuario;
import com.tcc.flyk.entity.enumerator.CategoriaTelefoneEnum;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;
import com.tcc.flyk.entity.enumerator.PrivacidadeEnum;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.StatusCompromissoEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.MongoDB;
public class ClienteDAOImpl extends MongoDB implements ClienteDAO {

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
		//Nome completo
		BasicDBObject doc = new BasicDBObject();
		doc.put("nome_completo", pessoa.getNome());
		
		//Alias
		if(!(pessoa.getAlias()==null)){
			doc.put("alias", pessoa.getAlias());
		}
		
		//Email
		if(!(pessoa.getEmail()==null)){
			doc.put("email", pessoa.getEmail());
		}
		
		//Senha
		if(!(pessoa.getSenha()==null)){
			doc.put("senha", pessoa.getSenha());
		}

		System.out.println("1");
		//facebookID
		if(!(pessoa.getFacebookID()==null)){
			doc.put("facebookID", pessoa.getFacebookID());
		}

		System.out.println("15");
		//CPF
		if(!(pessoa.getCPF()==null)){
			doc.put("CPF", pessoa.getCPF());
		}

		System.out.println("16");
		//Foto do perfil
		if(!(pessoa.getFotoPerfil()==null)){
			System.out.println("17");
			doc.put("foto", pessoa.getFotoPerfil());
		}
		System.out.println("2");
		
		//Tipo de perfil
		doc.put("tipo_perfil", pessoa.getTipoCadastro().getCodigo());
		
		//Status
		if(!(pessoa.getStatus()==null)){
			doc.put("status_pessoa", pessoa.getStatus());
		}
		
		//Logradouro
		if(!(pessoa.getEndereco().getLogradouro()==null)){
			doc.put("logradouro", pessoa.getEndereco().getLogradouro());
		}

		System.out.println("3");
		//Numero
		if(!(pessoa.getEndereco().getNumero()==0)){
			doc.put("numero", pessoa.getEndereco().getNumero());
		}

		System.out.println("31");
		//Complemento
		if(!(pessoa.getEndereco().getComplemento()==null)){
			doc.put("complemento", pessoa.getEndereco().getComplemento());
		}

		System.out.println("32");
		//Bairro
		if(!(pessoa.getEndereco().getBairro()==null)){
			doc.put("bairro", pessoa.getEndereco().getBairro());
		}

		System.out.println("33");
		//Cidade
		if(!(pessoa.getEndereco().getCidade()==null)){
			doc.put("cidade", pessoa.getEndereco().getCidade());
		}

		System.out.println("34");
		//Estado
		if(!(pessoa.getEndereco().getEstado()==null)){
			doc.put("estado", pessoa.getEndereco().getEstado());
		}

		System.out.println("4");
		//CEP
		if(!(pessoa.getEndereco().getCep()==null)){
			doc.put("CEP", pessoa.getEndereco().getCep());
		}
/*
		System.out.println("5");
		//TIPO DE CADASTRO
		if(pessoa.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){
			//CNPJ
			if(!(pessoa.getPrestador().getCnpj()==null)){
				doc.put("CNPJ", pessoa.getPrestador().getCnpj());
			}
			
			if(pessoa.getTipoCadastro()==TipoCadastroEnum.PREMIUM){
				//Valor Premium
				doc.put("valor_premium", pessoa.getPrestador().getValorPremium());
			}			
		}
		
		

		System.out.println("5");
		//TIPO DE CADASTRO
		if(pessoa.getTipoCadastro()==TipoCadastroEnum.PRESTADOR){
			//CNPJ
			if(!(pessoa.getPrestador().getCnpj()==null)){
				doc.put("CNPJ", pessoa.getPrestador().getCnpj());
			}
			
			if(pessoa.getTipoCadastro()==TipoCadastroEnum.PREMIUM){
				//Valor Premium
				doc.put("valor_premium", pessoa.getPrestador().getValorPremium());
			}			
		}
		*/
		//PRIVACIDADE
		if(!(pessoa.getPrivacidade()==null)){
			//Pega a privacidade da pessoa e joga no objeto privacidade
			Privacidade privacidade = new Privacidade();
			privacidade = pessoa.getPrivacidade();
			
			//AGENDA
			String privacidadeString = String.valueOf(quebraPrivacidade(privacidade.getExibeAgenda()));
			if(!(privacidadeString==null)){			
				if(pessoa.getTipoCadastro()==TipoCadastroEnum.CLIENTE){
					doc.put("privacidade_bloco_servicos_contratados", privacidadeString);
				}else{
					doc.put("privacidade_bloco_servicos_prestados", privacidadeString); 
				}
			}

			//CPF
			privacidadeString = String.valueOf(quebraPrivacidade(privacidade.getExibeCPF()));
			if(!(privacidadeString==null)){
				doc.put("privacidade_bloco_cpf_cnpj", privacidadeString);
			}

			//Endereço
			privacidadeString = String.valueOf(quebraPrivacidade(privacidade.getExibeEndereco()));
			if(!(privacidadeString==null)){
				doc.put("privacidade_bloco_endereco", privacidadeString);
			}

			//Telefone
			privacidadeString = String.valueOf(quebraPrivacidade(privacidade.getExibeTelefone()));
			if(!(privacidadeString==null)){
				doc.put("privacidade_bloco_telefone", privacidadeString);
			}
		}


		//******************************Telefones******************************//
		if(!(pessoa.getListaTelefone()==null)){
			
			int count = pessoa.getListaTelefone().size();
			System.out.println("qtd telefones: " + String.valueOf(count));
			
			//Varre a lista de telefones, inserindo um por um
			BasicDBList telefones = new BasicDBList();
			for(int i=0;i<count;i++){

				BasicDBObject telefone = new BasicDBObject();

				//Numero
				telefone.put("numero_telefone", String.valueOf(pessoa.getListaTelefone().get(i).getNumero()));
				
				//Categoria
				if(!(pessoa.getListaTelefone().get(i).getCategoriaTelefone()==null)){
					telefone.put("categoria_telefone", String.valueOf(pessoa.getListaTelefone().get(i).getCategoriaTelefone().getCodigo()));
				}
				
				//DDD
				if(!(pessoa.getListaTelefone().get(i).getDdd()==0)){
					telefone.put("ddd_telefone", String.valueOf(pessoa.getListaTelefone().get(i).getDdd()));
				}
				
				//Operadora
				if(!(pessoa.getListaTelefone().get(i).getOperadora()==null)){
					telefone.put("operadora_telefone", String.valueOf(pessoa.getListaTelefone().get(i).getOperadora().getCodigo()));
				}
				System.out.println(String.valueOf(pessoa.getListaTelefone().get(i).getNumero()));

				telefones.add(telefone);
				System.out.println("telefone adicionado");
			}
			doc.put("telefones", telefones);
		}else{
			System.out.println("sem telefones");
		}
		
		//******************************Amigos******************************//
		if(!(pessoa.getListaAmigos()==null)){
			
			int count = pessoa.getListaAmigos().size();
			System.out.println("qtd amigos: " + String.valueOf(count));
			
			BasicDBList amigos = new BasicDBList();
			
			//Varre a lista de amigos, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do amigo: " + String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId()));

				BasicDBObject amigo = new BasicDBObject();
				
				
				// DAQUI PRA BAIXO É SÓ PREENCHER OS DADOS DA LISTA DE AMIGOS
				//Inicia o documento e grava o número
				amigo.put("id_amigo",String.valueOf(pessoa.getListaAmigos().get(i).getAmigo().getId()));
				
				//Data da amizade
				if(!(pessoa.getListaAmigos().get(i).getDataInicioAmizade()==null)){
					amigo.put("data_amizade",pessoa.getListaAmigos().get(i).getDataInicioAmizade());
				}
				
				//Status da amizade
				if(!(pessoa.getListaAmigos().get(i).getStatusEnum()==null)){
					if(pessoa.getListaAmigos().get(i).getStatusEnum()==StatusAmizadeEnum.ATIVA){
						amigo.put("status_amizade","1");
					}else{
						amigo.put("status_amizade","2");						
					}
				}
				
				amigos.add(amigo);
			}
			doc.put("amigos", amigos);
		}else{
			System.out.println("sem amigos :(");
		}

		
		//******************************Recomendações dadas a outro usuário******************************//
		if(!(pessoa.getListaPrestadoresRecomendados()==null)){
			
			int count = pessoa.getListaPrestadoresRecomendados().size();
			System.out.println("qtd prestadores recomendados: " + String.valueOf(count));
			
			BasicDBList prestadoresRecomendados = new BasicDBList();
			//Varre a lista de prestadores recomendados, inserindo um por um
			for(int i=0;i<count;i++){
				System.out.println("ID do prestador: " + String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId()));

				BasicDBObject prestadorRecomendado = new BasicDBObject();
				
				//Grava o número
				prestadorRecomendado.put("id_usuario_recomendado", String.valueOf(pessoa.getListaPrestadoresRecomendados().get(i).getId()));
				
				prestadoresRecomendados.add(prestadorRecomendado);
			}
			
			doc.put("recomendacoes_dadas", prestadoresRecomendados);
		}else{
			System.out.println("sem recomendações dadas");
		}

		
		//******************************Mensagens de conversa******************************//
		if(!(pessoa.getlistaMensagensConversa()==null)){
			
			int count = pessoa.getlistaMensagensConversa().size();
			System.out.println("qtd msgs: " + String.valueOf(count));
			
			BasicDBList mensagens = new BasicDBList();
			//Varre a lista de mensagens, inserindo uma por uma
			for(int i=0;i<count;i++){
				BasicDBObject mensagem = new BasicDBObject();
				
				//Inicia o documento e grava o id da pessoa com a qual o cliente está conversando
				mensagem.put("id_usuario_conversa", pessoa.getlistaMensagensConversa().get(i).getIdUsuario());
				
				//flagEnviadoOuRecebido
				mensagem.put("flagEnviadoOuRecebido", pessoa.getlistaMensagensConversa().get(i).getflagEnviadoRecebido());
				
				//data_hora_mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getData()==null)){
					mensagem.put("data_hora_mensagem", pessoa.getlistaMensagensConversa().get(i).getData());
				}
				
				//mensagem
				if(!(pessoa.getlistaMensagensConversa().get(i).getMsg()==null)){
					mensagem.put("mensagem", pessoa.getlistaMensagensConversa().get(i).getMsg());
				}

				mensagens.add(mensagem);
			}
			
			doc.put("mensagens_de_conversa", mensagens);
		}else{
			System.out.println("sem conversas");
		}

		
		//******************************Servicos contratados******************************//
		if(!(pessoa.getAgenda()==null)){
			
			int count = pessoa.getAgenda().size();
			System.out.println("qtd de servicos contratados: " + String.valueOf(count));
			
			BasicDBList servicosContratados = new BasicDBList();
			
			//Varre a lista de compromissos, inserindo uma por uma
			for(int i=0;i<count;i++){

				BasicDBObject servicoContratado = new BasicDBObject();

				System.out.println("0");
				//Inicia o documento e grava o id do prestador
				servicoContratado.put("id_prestador", pessoa.getAgenda().get(i).getContrato().getPrestador().getId());

				System.out.println("1");
				//data_servico_contratado
				if(!(pessoa.getAgenda().get(i).getDataInicio()==null)){
					servicoContratado.put("data_servico_contratado", pessoa.getAgenda().get(i).getDataInicio());
				}
				System.out.println("2");
				
				//custo_servico_contratado
				if(!(pessoa.getAgenda().get(i).getContrato().getCusto()==0)){
					servicoContratado.put("custo_servico_contratado", String.valueOf(pessoa.getAgenda().get(i).getContrato().getCusto()));
				}

				System.out.println("3");
				//status_servico_contratado
				if(!(pessoa.getAgenda().get(i).getStatus()==null)){
					String status = "";
					if(pessoa.getAgenda().get(i).getStatus()==StatusCompromissoEnum.PRETENDIDO){
						status = "1";
					}
					if(pessoa.getAgenda().get(i).getStatus()==StatusCompromissoEnum.MARCADO){
						status = "2";
					}
					if(pessoa.getAgenda().get(i).getStatus()==StatusCompromissoEnum.CANCELADO){
						status = "3";
					}
					if(pessoa.getAgenda().get(i).getStatus()==StatusCompromissoEnum.REALIZADO){
						status = "4";
					}
					servicoContratado.put("status_servico_contratado", status);
				}

				System.out.println("4");
				//data_avaliacao_servico_contratado
				if(!(pessoa.getAgenda().get(i).getContrato().getDataAvaliacaoServico()==null)){
					servicoContratado.put("data_avaliacao_servico_contratado", pessoa.getAgenda().get(i).getContrato().getDataAvaliacaoServico());
				}

				System.out.println("5");
				//nota_preco
				if(!(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoPreco()==0)){
					servicoContratado.put("nota_preco", String.valueOf(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoPreco()));
				}
				System.out.println("6");
				
				//nota_pontualidade
				if(!(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoTempo()==0)){
					servicoContratado.put("nota_pontualidade", String.valueOf(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoTempo()));
				}
				System.out.println("7");
				
				//nota_qualidade
				if(!(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoQualidade()==0)){
					servicoContratado.put("nota_qualidade", String.valueOf(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoQualidade()));
				}
				System.out.println("8");
				
				//nota_profissionalismo
				if(!(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoProfissionalismo()==0)){
					servicoContratado.put("nota_profissionalismo", String.valueOf(pessoa.getAgenda().get(i).getContrato().getAvaliacaoPrestador().getAvaliacaoProfissionalismo()));
				}

				System.out.println("9");
				servicosContratados.add(servicoContratado);
				System.out.println("10");
			}
			doc.put("servicos_contratados", servicosContratados);
		}else{
			System.out.println("sem servicos contratados");
		}

		System.out.println("11");
		System.out.println(doc);
		//Insere o cliente
		System.out.println(doc.toString());
		try {
			super.db.getCollection("FLYK").insert(doc);
			System.out.println("Usuário cadastrado com sucesso");
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
	public Usuario consultaUsuario(String busca) {
		try {
			Usuario usuario = new Usuario();
			BasicDBObject query = new BasicDBObject();
			query.put("usuario", busca);

			DBObject object = db.getCollection("cliente").findOne(query);

			if(object!=null){
				usuario.setNome(object.get("nome").toString());
				usuario.setUsuario(object.get("usuario").toString());
				usuario.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				if(object.get("status_pessoa").toString().equals("A")){
					usuario.setAtivo(true);
				}else{
					usuario.setAtivo(false);
				}
				return usuario;
			}else{
				return null;
			}

			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Usuario consultaLogin(String email) {
		try {
			DBCollection collection = db.getCollection("FLYK");
			BasicDBObject filtro = new BasicDBObject(new Document("email", email));
			DBCursor cursor = collection.find(filtro);
			DBObject resultado;

			Usuario user = new Usuario();
			//Busca campos de resultado
			if(cursor.hasNext()){
				resultado = cursor.next();
				System.out.println(resultado);
				System.out.println("************************");

				//ID
				user.setId(String.valueOf(resultado.get("_id")));
				//NOME
				user.setNome(String.valueOf(resultado.get("nome_completo")));
				//EMAIL
				user.setEmail(String.valueOf(resultado.get("email")));
				//SENHA
				user.setSenha(String.valueOf(resultado.get("senha")));
				//USUARIO (EMAIL SEM O @)
				user.setUsuario(String.valueOf(resultado.get("usuario")));
				//TIPO CADASTRO
				String tipoCadastro = String.valueOf(resultado.get("tipo_perfil"));
				if(tipoCadastro.equals("1")){
					user.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				}
				if(tipoCadastro.equals("2")){
					user.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
				}
				if(tipoCadastro.equals("3")){
					user.setTipoCadastro(TipoCadastroEnum.PREMIUM);
				}
				if(tipoCadastro.equals("4")){
					user.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				}
				//FLAG ATIVO
				String flagAtivo = String.valueOf(resultado.get("status_pessoa"));
				if(flagAtivo.equals("A")){
					user.setAtivo(true);
				}else{
					user.setAtivo(false);
				}
				/************************* MENSAGENS DE TESTE, REMOVA CASO QUERIA, MAS NÃO DÊ COMMIT PLEASE **********************/
				System.out.println("**************");
				System.out.println(user.getNome());
				System.out.println(user.getId());
				System.out.println(user.getSenha());
				System.out.println(user.getTipoCadastro());
				System.out.println("**************");
				
				
			}else{
				System.out.println("Consulta de cliente pelo email " + email + " não encontrou valores.");
				return null;
			}
			
			
			return user;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Cliente consultaClientePorId(String idCliente){
		consultaTudo();
		System.out.println("CONSULTA CLIENTE - INÍCIO");
		
		Cliente pessoa = new Cliente(); //Cliente que será retornado
		
		DBCollection collection = db.getCollection("FLYK");
		//BasicDBObject filtro = new BasicDBObject(new Document("_id",new ObjectId(idCliente)));
		//Metodo acima alterado para
		BasicDBObject filtro=new BasicDBObject("_id",new ObjectId(idCliente));

		DBCursor cursor = collection.find(filtro);
		DBObject resultado;

		//Busca campos de resultado
		if(cursor.hasNext()){
			resultado = cursor.next();
			System.out.println(resultado);
			System.out.println("************************");

			//ID
			pessoa.setId(idCliente);
			//NOME
			if(resultado.get("nome_completo")!=null){
				pessoa.setNome(String.valueOf(resultado.get("nome_completo")));
			}
			//ALIAS
			if(resultado.get("alias")!=null){
				pessoa.setAlias(String.valueOf(resultado.get("alias")));
			}
			//EMAIL
			if(resultado.get("email")!=null){
				pessoa.setEmail(String.valueOf(resultado.get("email")));
			}
			//APELIDO
			if(resultado.get("usuario")!=null){
				pessoa.setApelido(String.valueOf(resultado.get("usuario")));
			}
			//SENHA
			if(resultado.get("senha")!=null){
				pessoa.setSenha(String.valueOf(resultado.get("senha")));
			}
			//ID DO FACEBOOK
			if(resultado.get("facebookID")!=null){
				pessoa.setFacebookID(String.valueOf(resultado.get("facebookID")));
			}
			//CPF
			if(resultado.get("CPF")!=null){
				pessoa.setCPF(String.valueOf(resultado.get("CPF")));
			}
			//FOTO DO PERFIL
			if(resultado.get("foto")!=null){
				pessoa.setFotoPerfil(String.valueOf(resultado.get("foto")));
			}
			//TIPO DE PERFIL
			if(resultado.get("tipo_perfil")!=null){
				String tipoCadastro = String.valueOf(resultado.get("tipo_perfil"));
				if(tipoCadastro.equals("1")){
					pessoa.setTipoCadastro(TipoCadastroEnum.CLIENTE);
				}
				if(tipoCadastro.equals("2")){
					pessoa.setTipoCadastro(TipoCadastroEnum.PRESTADOR);
				}
				if(tipoCadastro.equals("3")){
					pessoa.setTipoCadastro(TipoCadastroEnum.PREMIUM);
				}
				if(tipoCadastro.equals("4")){
					pessoa.setTipoCadastro(TipoCadastroEnum.ADMINISTRADOR);
				}
			}
			//STATUS DA PESSOA
			if(resultado.get("status_pessoa")!=null){
				pessoa.setStatus(String.valueOf(resultado.get("status_pessoa")));
			}
			//ENDEREÇO
			Endereco enderecoPessoa = new Endereco();
			if(resultado.get("bairro")!=null){
				enderecoPessoa.setBairro(String.valueOf(resultado.get("bairro")));
			}
			if(resultado.get("CEP")!=null){
				enderecoPessoa.setCep(String.valueOf(resultado.get("CEP")));
			}
			if(resultado.get("cidade")!=null){
				enderecoPessoa.setCidade(String.valueOf(resultado.get("cidade")));
			}
			if(resultado.get("complemento")!=null){
				enderecoPessoa.setComplemento(String.valueOf(resultado.get("complemento")));
			}
			if(resultado.get("estado")!=null){
				enderecoPessoa.setEstado(String.valueOf(resultado.get("estado")));
			}
			if(resultado.get("logradouro")!=null){
				enderecoPessoa.setLogradouro(String.valueOf(resultado.get("logradouro")));
			}
			if(resultado.get("numero")!=null){
				enderecoPessoa.setNumero(Integer.valueOf(String.valueOf(resultado.get("numero"))));
			}
			pessoa.setEndereco(enderecoPessoa);

			//PRIVACIDADE
			Privacidade privacidade = new Privacidade();
			//privacidade_bloco_cpf_cnpj
			if(resultado.get("privacidade_bloco_cpf_cnpj")!=null){
				String codigo = String.valueOf(resultado.get("privacidade_bloco_cpf_cnpj"));
				privacidade.setExibeCPF(montaPrivacidade(Integer.valueOf(codigo)));
			}
			//privacidade_bloco_endereco
			if(resultado.get("privacidade_bloco_endereco")!=null){
				String codigo = String.valueOf(resultado.get("privacidade_bloco_endereco"));
				privacidade.setExibeEndereco(montaPrivacidade(Integer.valueOf(codigo)));
			}
			//privacidade_bloco_telefone
			if(resultado.get("privacidade_bloco_telefone")!=null){
				String codigo = String.valueOf(resultado.get("privacidade_bloco_telefone"));
				privacidade.setExibeTelefone(montaPrivacidade(Integer.valueOf(codigo)));
			}
			//privacidade_bloco_servicos_contratados
			if(resultado.get("privacidade_bloco_servicos_contratados")!=null){
				String codigo = String.valueOf(resultado.get("privacidade_bloco_servicos_contratados"));
				privacidade.setExibeAgenda(montaPrivacidade(Integer.valueOf(codigo)));
			}
			pessoa.setPrivacidade(privacidade);
			
			
			//********************* LISTA DE TELEFONES *********************
			//Busca a lista de telefones e coloca na telefonesBD
			BasicDBList telefonesDB = (BasicDBList) resultado.get("telefones");

			//Varre a lista de telefones, preenchendo o array telefones
			List<Telefone> telefones = new ArrayList<Telefone>();
		    BasicDBObject[] lightArr = telefonesDB.toArray(new BasicDBObject[0]);
		    for(BasicDBObject telefone : lightArr) {
		        // shows each item from the lights array
		    	System.out.println("numero_telefone: " + telefone.get("numero_telefone"));
		    	
		    	Telefone tel = new Telefone();

	    		tel.setCategoriaTelefone(CategoriaTelefoneEnum.FIXO);
		    	//numero
		    	tel.setNumero(Integer.valueOf(telefone.getString("numero_telefone")));
		    	
		    	//DDD
		    	if(telefone.get("ddd_telefone")!=null){
		    		tel.setDdd(Integer.valueOf(telefone.getString("ddd_telefone")));
		    	}
		    	
		    	//categoria_telefone
		    	if(telefone.get("categoria_telefone")!=null){
		    		String categoria = telefone.getString("categoria_telefone");
		    		if(categoria.equals("1")){
			    		tel.setCategoriaTelefone(CategoriaTelefoneEnum.FIXO);
		    		}
		    		if(categoria.equals("2")){
			    		tel.setCategoriaTelefone(CategoriaTelefoneEnum.COMERCIAL);
		    		}
		    		if(categoria.equals("3")){
			    		tel.setCategoriaTelefone(CategoriaTelefoneEnum.MOVEL);
		    		}
		    	}
		    	
		    	//operadora_telefone
		    	if(telefone.get("operadora_telefone")!=null){
		    		String operadora = telefone.getString("operadora_telefone");

		    		if(operadora.equals("1")){
			    		tel.setOperadora(OperadoraEnum.CLARO);	
		    		}
		    		if(operadora.equals("2")){
			    		tel.setOperadora(OperadoraEnum.VIVO);	
		    		}
		    		if(operadora.equals("3")){
			    		tel.setOperadora(OperadoraEnum.TIM);	
		    		}
		    		if(operadora.equals("4")){
			    		tel.setOperadora(OperadoraEnum.OI);	
		    		}
		    		if(operadora.equals("5")){
			    		tel.setOperadora(OperadoraEnum.NEXTEL);	
		    		}
		    	}
		    	
		    	telefones.add(tel);
		    } 
		    //Adiciona o array telefones na pessoa 
			pessoa.setListaTelefone(telefones);
	    	System.out.println(" telefones fim");
			
			//********************* LISTA DE AMIGOS *********************
			//Busca a lista de telefones e coloca na telefonesBD
			BasicDBList amigosDB = (BasicDBList) resultado.get("amigos");
		
			if(amigosDB!=null){
				//Varre a lista de telefones, preenchendo o array telefones
				List<Amizade> amigos = new ArrayList<Amizade>();
			    lightArr = amigosDB.toArray(new BasicDBObject[0]);
			    for(BasicDBObject amigoDB : lightArr) {
			        // shows each item from the lights array
			    	System.out.println("id_amigo: " + amigoDB.get("id_amigo"));
			    	
			    	Amizade amizade = new Amizade();
			    	Cliente amigo = new Cliente();
	
			    	//numero
			    	amigo.setId(String.valueOf(amigoDB.get("_id")));
			    	amizade.setAmigo(amigo);
			    	
			    	//data_amizade
			    	if(amigoDB.get("data_amizade")!=null){
						amizade.setDataInicioAmizade((Date)amigoDB.get("data_amizade"));
			    	}
			    	
			    	//status_amizade
			    	if(amigoDB.get("status_amizade")!=null){
			    		if(amigoDB.getString("status_amizade")=="1"){
				    		amizade.setStatusEnum(StatusAmizadeEnum.ATIVA);
			    		}else{
				    		amizade.setStatusEnum(StatusAmizadeEnum.INATIVA);
			    		}
			    	}
			    	
			    	amigos.add(amizade);
			    } 
			    //Adiciona o array telefones na pessoa 
				pessoa.setListaAmigos(amigos);
			}


			//********************* LISTA DE SERVICOS CONTRATADOS *********************
			//Busca a lista de compromissos e coloca na servicosContratadosDB
			BasicDBList servicosContratadosDB = (BasicDBList) resultado.get("servicos_contratados");

			if(servicosContratadosDB!=null){				
				//Varre a lista de compromissos, preenchendo o array agenda
				List<Compromisso> agenda = new ArrayList<Compromisso>();
			    lightArr = servicosContratadosDB.toArray(new BasicDBObject[0]);
			    for(BasicDBObject compromissoDB : lightArr) {
			        // shows each item from the lights array
			    	System.out.println("id_prestador: " + compromissoDB.getString("id_prestador"));
			    	
			    	Compromisso compromisso = new Compromisso();
			    	Contrato contrato = new Contrato();
	
			    	//id_prestador
			    	Prestador prestador = new Prestador();
			    	prestador.setId(String.valueOf(compromissoDB.get("_id")));
			    	contrato.setPrestador(prestador);
			    	
			    	//data_servico_contratado
		    		try {
						compromisso.setDataInicio((Date)compromissoDB.get("data_servico_contratado"));
		    		}catch(Exception e){
						System.out.println("ERRO AO BUSCAR DATA DA data_servico_contratado NO METODO consultaClientePorId" + idCliente);
						e.printStackTrace();
		    		}
		    		
		    		//status_servico_contratado
		    		if(compromissoDB.get("status_servico_contratado")!=null){
			    		String status = compromissoDB.getString("status_servico_contratado");
			    		if(status.equals("1")){
			    			compromisso.setStatus(StatusCompromissoEnum.PRETENDIDO);
			    		}
			    		if(status.equals("2")){
			    			compromisso.setStatus(StatusCompromissoEnum.MARCADO);
			    		}
			    		if(status.equals("3")){
			    			compromisso.setStatus(StatusCompromissoEnum.CANCELADO);
			    		}
			    		if(status.equals("4")){
			    			compromisso.setStatus(StatusCompromissoEnum.REALIZADO);
			    		}
		    			
		    		}
	
		    		//data_avaliacao_servico_contratado
		    		if(compromissoDB.get("data_avaliacao_servico_contratado")!=null){
			    		try {
			    			contrato.setDataAvaliacaoServico((Date)compromissoDB.get("data_avaliacao_servico_contratado"));
			    		}catch(Exception e){
							System.out.println("ERRO AO BUSCAR DATA DA data_avaliacao_servico_contratado NO METODO consultaClientePorId" + idCliente);
							e.printStackTrace();
			    		}	    			
		    		}
	
		    		AvaliacaoPrestador avaliacao = new AvaliacaoPrestador();
		    		//nota_preco
		    		if(compromissoDB.get("nota_preco")!=null){
		    			String notaS = String.valueOf(compromissoDB.get("nota_preco"));
		    			int notaI = Integer.valueOf(notaS);
			    		avaliacao.setAvaliacaoPreco(notaI);
		    		}
		    		
		    		//nota_pontualidade
		    		if(compromissoDB.get("nota_pontualidade")!=null){
		    			String notaS = String.valueOf(compromissoDB.get("nota_pontualidade"));
		    			int notaI = Integer.valueOf(notaS);
		    			avaliacao.setAvaliacaoPreco(notaI);
		    		}
		    		
		    		//nota_qualidade
		    		if(compromissoDB.get("nota_qualidade")!=null){
		    			String notaS = String.valueOf(compromissoDB.get("nota_qualidade"));
		    			int notaI = Integer.valueOf(notaS);
		    			avaliacao.setAvaliacaoPreco(notaI);
		    		}
		    		
		    		//nota_profissionalismo
		    		if(compromissoDB.get("nota_profissionalismo")!=null){
		    			String notaS = String.valueOf(compromissoDB.get("nota_profissionalismo"));
		    			int notaI = Integer.valueOf(notaS);
		    			avaliacao.setAvaliacaoPreco(notaI);
		    		}
		    		contrato.setAvaliacaoPrestador(avaliacao);
		    		
		    		//Seta o contrato no compromisso
		    		compromisso.setContrato(contrato);
		    		
		    		//Adiciona o compromisso na agenda
			    	agenda.add(compromisso);
			    } 
			    //Adiciona o array compromissos na pessoa 
				pessoa.setAgenda(agenda);
			}
			
			//********************* LISTA DE RECOMENDAÇÕES DADAS *********************
			//Busca a lista de prestadores recomendados e coloca na telefonesBD
			BasicDBList recomendacoesDadasBD = (BasicDBList) resultado.get("recomendacoes_dadas");
		

			if(recomendacoesDadasBD!=null){				
				//Varre a lista de prestadores recomendados, preenchendo o array recomendacoesDadas
				List<Prestador> recomendacoesDadas = new ArrayList<Prestador>();
			    lightArr = recomendacoesDadasBD.toArray(new BasicDBObject[0]);
			    for(BasicDBObject recomendacaoDadaDB : lightArr) {
			        // shows each item from the lights array
			    	System.out.println("id_usuario_recomendado: " + recomendacaoDadaDB.get("id_usuario_recomendado"));
			    	
			    	Prestador prestador = new Prestador();
			    	prestador.setId(recomendacaoDadaDB.getString("id_usuario_recomendado"));
			    	
			    	recomendacoesDadas.add(prestador);
			    } 
			    //Adiciona o array prestadores recomendados na pessoa 
				pessoa.setListaPrestadoresRecomendados(recomendacoesDadas);
			}
			
			
			//********************* LISTA DE CONVERSAS *********************
			//Busca a lista de conversa e coloca na conversaDB
			BasicDBList conversaDB = (BasicDBList) resultado.get("mensagens_de_conversa");
		

			if(conversaDB!=null){	
				//Varre a lista de conversa, preenchendo o array mensagens
				List<Conversa> mensagens = new ArrayList<Conversa>();
			    lightArr = conversaDB.toArray(new BasicDBObject[0]);
			    for(BasicDBObject mensagemDB : lightArr) {
			        // shows each item from the lights array
			    	System.out.println("id_usuario_conversa" + mensagemDB.get("id_usuario_conversa"));
			    	
			    	Conversa mensagem = new Conversa();
	
			    	//flag enviado ou recebido
			    	mensagem.setflagEnviadoRecebido(String.valueOf(mensagemDB.get("flagEnviadoOuRecebido"))); 
			    	
			    	//id
			    	mensagem.setidUsuario(String.valueOf(mensagemDB.get("id_usuario_conversa")));
			    	
			    	//data_hora_mensagem
			    	if(mensagemDB.get("data_hora_mensagem")!=null){
						mensagem.setData((Date)mensagemDB.get("data_hora_mensagem"));
			    	}
			    	
			    	//mensagem
			    	if(mensagemDB.getString("mensagem")!=null){
			    		mensagem.setMsg(mensagemDB.getString("mensagem"));
			    	}
			    	
			    	mensagens.add(mensagem);
			    } 
			    //Adiciona o array mensagens na pessoa 
				pessoa.setlistaMensagensConversa(mensagens);
			}
			
			
			
		}else{
			System.out.println("Consulta de prestadores recomendados pelo id " + idCliente + " não encontrou valores.");
			return null;
		}

		//Retorna a pessoa para o chamador
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

	//Retorna o valor numérico da privacidde
	public int quebraPrivacidade(PrivacidadeEnum privacidade){
		if(privacidade==PrivacidadeEnum.PUBLICO){
			return 1;
		}
		if(privacidade==PrivacidadeEnum.APENAS_AMIGOS){
			return 2;
		}
		if(privacidade==PrivacidadeEnum.PRIVADO){
			return 3;
		}
		return 1;
	}

	//Retorna o valor enum da privacidde
	public PrivacidadeEnum montaPrivacidade(int codigo){
		if(codigo==1){
			return PrivacidadeEnum.PUBLICO;
		}
		if(codigo==2){
			return PrivacidadeEnum.APENAS_AMIGOS;
		}
		if(codigo==3){
			return PrivacidadeEnum.PRIVADO;
		}
		return null;
	}
	
	//****************************CONSULTA TODOS OS DOCUMENTOS DO BANCO****************************//
	@Override
	public void consultaTudo(){

		FindIterable<Document> iterable = super.mongoDatabase.getCollection("FLYK").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}
}

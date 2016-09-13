import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

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
import com.tcc.flyk.entity.enumerator.CategoriaServicoEnum;
import com.tcc.flyk.entity.enumerator.CategoriaTelefoneEnum;
import com.tcc.flyk.entity.enumerator.OperadoraEnum;
import com.tcc.flyk.entity.enumerator.PrivacidadeEnum;
import com.tcc.flyk.entity.enumerator.StatusAmizadeEnum;
import com.tcc.flyk.entity.enumerator.StatusCompromissoEnum;
import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;
import com.tcc.flyk.persistence.ClienteDAO;
import com.tcc.flyk.persistence.impl.ClienteDAOImpl;

public class Tests {

	@Test
	public void testeCliente(){

		Cliente pessoa = new Cliente();
		pessoa.setNome("cliente3");
		pessoa.setAlias("Kira");
		pessoa.setEmail("cliente3");
		pessoa.setSenha("123");
		pessoa.setCPF("92930028301");
		pessoa.setTipoCadastro(TipoCadastroEnum.CLIENTE);
		pessoa.setStatus("A");
		
		//privacidade
		Privacidade privacidade =  new Privacidade();
		privacidade.setExibeAgenda(PrivacidadeEnum.PUBLICO);
		privacidade.setExibeCPF(PrivacidadeEnum.PUBLICO);
		privacidade.setExibeEndereco(PrivacidadeEnum.PUBLICO);
		privacidade.setExibeTelefone(PrivacidadeEnum.PUBLICO);
		pessoa.setPrivacidade(privacidade);

		//endereço
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das tulipas");
		endereco.setNumero(123);
		endereco.setBairro("Jardim das flores");
		endereco.setCidade("Campinas");
		endereco.setEstado("SP");
		endereco.setCep("13240-121");
		pessoa.setEndereco(endereco);

		//telefones
		Telefone tel1 = new Telefone();
		tel1.setCategoriaTelefone(CategoriaTelefoneEnum.MOVEL);
		tel1.setNumero(1234567890);
		tel1.setOperadora(OperadoraEnum.CLARO);
		tel1.setDdd(12);
		Telefone tel2 = new Telefone();
		tel2.setCategoriaTelefone(CategoriaTelefoneEnum.FIXO);
		tel2.setNumero(987654382);

		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		listaTelefone.add(tel1);
		listaTelefone.add(tel2);
		
		pessoa.setListaTelefone(listaTelefone);
		
		//amigos
		Cliente amigo1 = new Cliente();
		amigo1.setId("578c2d3d70346a0d1c619f9f");
		Amizade amizade1 = new Amizade();
		amizade1.setAmigo(amigo1);
		amizade1.setDataInicioAmizade(new Date());
		amizade1.setStatusEnum(StatusAmizadeEnum.ATIVA);
		Cliente amigo2 = new Cliente();
		amigo2.setId("5790386f70346a177025a134");
		Amizade amizade2 = new Amizade();
		amizade2.setAmigo(amigo2);
		amizade2.setDataInicioAmizade(new Date());
		amizade2.setStatusEnum(StatusAmizadeEnum.ATIVA);
		
		List<Amizade> listaAmigos = new ArrayList<Amizade>();
		listaAmigos.add(amizade1);
		listaAmigos.add(amizade2);
		
		pessoa.setListaAmigos(listaAmigos);
		
		//Servicos contratados
		List<Compromisso> servicosContratados = new ArrayList<Compromisso>();
		Compromisso servico1 = new Compromisso();
		Contrato contrato1 = new Contrato(); //Contrato para adicionar no serviço
		Prestador prestadorDoServico1 = new Prestador();
		prestadorDoServico1.setId("h76v3bj97hfb4897gr4f4f444f4f4f4f3");
		contrato1.setPrestador(prestadorDoServico1);
		Cliente cliente1 = new Cliente(); //Cliente para adicionar no contrato
		cliente1.setId("deveria_ser_o_mesmo_id_deste_cliente");
		contrato1.setCliente(cliente1);
		contrato1.setCusto(23456);
		contrato1.setAvaliacaoContratante(5);
		AvaliacaoPrestador avaliacaoPrestador1 = new AvaliacaoPrestador(); //Avaliacao para adicionar no contrato
		avaliacaoPrestador1.setAvaliacaoPreco(5);
		avaliacaoPrestador1.setAvaliacaoProfissionalismo(5);
		avaliacaoPrestador1.setAvaliacaoQualidade(5);
		avaliacaoPrestador1.setAvaliacaoTempo(5);
		contrato1.setAvaliacaoPrestador(avaliacaoPrestador1);
		contrato1.setServico(CategoriaServicoEnum.ENCANADOR);
		contrato1.setDataAvaliacaoServico(new Date());
		servico1.setContrato(contrato1);
		servico1.setDataFim(new Date());
		servico1.setDataInclusao(new Date());
		servico1.setDataInicio(new Date());
		servico1.setIndicadorFerias(false);
		servico1.setStatus(StatusCompromissoEnum.REALIZADO);
		
		servicosContratados.add(servico1);
		
		pessoa.setAgenda(servicosContratados);
	
		
		//Recomendações dadas a outro usuário
		List<Prestador> listaPrestadoresRecomendados = new ArrayList<Prestador>();
		Prestador recomendado1 = new Prestador();
		recomendado1.setId("idprestador12394e0e9309d");
		Prestador recomendado2 = new Prestador();
		recomendado2.setId("idprestador22394e0e9309d");
		
		listaPrestadoresRecomendados.add(recomendado1);
		listaPrestadoresRecomendados.add(recomendado2);
		
		pessoa.setListaPrestadoresRecomendados(listaPrestadoresRecomendados);
		
		//Mensagens de conversa
		Conversa scrap1 = new Conversa();
		scrap1.setData(new Date());
		scrap1.setidUsuario("f0943jf043jt09349j");
		scrap1.setflagEnviadoRecebido("E");
	    scrap1.setMsg("olá");
	    
		Conversa scrap2 = new Conversa();
		scrap2.setData(new Date());
		scrap2.setidUsuario("gh98hjf03093j0f9309f");
		scrap1.setflagEnviadoRecebido("R");
	    scrap2.setMsg("Olá " + pessoa.getEmail());
	    
	    List<Conversa> listaConversa = new ArrayList<Conversa>();

	    listaConversa.add(scrap1);
	    listaConversa.add(scrap2);
	    
	    pessoa.setlistaMensagensConversa(listaConversa);
				
			
		ClienteDAO clienteDAO = new ClienteDAOImpl();
		if(clienteDAO.inserirNovoCliente(pessoa)){
			System.out.println("sucessooooooo");
		}else{
			System.out.println("fracassoooooo");
		}
		
		assertTrue("ok", true);	
	}
}

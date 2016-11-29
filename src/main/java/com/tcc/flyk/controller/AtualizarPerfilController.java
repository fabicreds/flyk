package com.tcc.flyk.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.flyk.entity.Cliente;
import com.tcc.flyk.entity.Prestador;
import com.tcc.flyk.service.AtualizaPerfilService;
import com.tcc.flyk.service.ProfilePageService;
import com.tcc.flyk.util.ClienteUtil;
import com.tcc.flyk.util.PrestadorUtil;

@Controller
public class AtualizarPerfilController {

	@Autowired
	private AtualizaPerfilService atualizaPerfilService = new AtualizaPerfilService();
	
	

	@Autowired
	private ProfilePageService profileService = new ProfilePageService();

	@Resource
	private ClienteUtil clienteUtil;
	@Resource
	private PrestadorUtil prestadorUtil;

	@RequestMapping(value = "/profilePageEdit", method = RequestMethod.GET)
	public String iniciarTelaCadastro() {

		return "profilePageEdit";
	}

	@RequestMapping(value = "/pagamento", method = RequestMethod.GET)
	public String iniciarTelaPagamento() {

		return "pagamento";
	}

	@RequestMapping(value = "/atualizarPerfil", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = "application/json")

	public @ResponseBody String PreenchePerfil(@RequestBody String perfil) {
		JSONObject jsonCliente = new JSONObject();

		try {

			JSONObject dadosPerfil = new JSONObject(perfil);
			JSONObject dadosCli = new JSONObject();
			dadosCli = dadosPerfil.getJSONObject("cliente");

			if (!dadosCli.isNull("tipoCadastro")) {
				
				if ((!dadosCli.isNull("tipoCadastro") && (dadosCli.getInt("tipoCadastro") == 2)	|| dadosCli.getInt("tipoCadastro") == 3)) {

					Prestador prest = new Prestador();

					prest = atualizaPerfilService.atualizaPerfilPrestador(dadosCli.getString("id"), dadosCli);

					///dadosCli.put("cliente", prestadorUtil.toJSON(prest));					

					//Prestador prestadorAtualizado = prestadorUtil.toPrestador(dadosCli);

					//prestadorAtualizado.setStatus("A");
					//JSONObject jsonPrestador = new JSONObject();

					//jsonPrestador.put("mensagem", "Cliente atualizado com sucesso");
					//jsonPrestador.put("cliente", prestadorUtil.toJSON(prestadorAtualizado));
					prest.setStatus("A");
					return profileService.montarDadosPerfil(prest.getId(),prest.getId(),prest.getTipoCadastro());
					//return jsonPrestador.toString();
					

				} else {
					
					Cliente cli = new Cliente();

					ClienteUtil util = new ClienteUtil();

					cli = atualizaPerfilService.atualizaPerfilCliente(
							dadosPerfil.getJSONObject("cliente").getString("id"), dadosPerfil.getJSONObject("cliente"));


					//dadosPerfil.put("cliente", util.toJSON(cli));
					
					
				   return profileService.montarDadosPerfil(cli.getId(),cli.getId(),cli.getTipoCadastro());
						 
						 
						 
						 //json.getString("idUsuario"),json.getString("idUsuario"),  tipoCadastroEnumUtil.definirTipoCadastro(json.getInt("tipoUsuario")));


					//Cliente cliAtualizado = new Cliente();

					//cliAtualizado = util.toCliente(dadosPerfil.getJSONObject("cliente"));
					

					//jsonCliente.put("mensagem", "Cliente atualizado com sucesso");
					//jsonCliente.put("cliente", util.toJSON(cliAtualizado));

					//return jsonCliente.toString();

				}
			}
			
		} catch (Exception e) {
			
			
			
			return mensagemErro("Erro ao atualizar Cliente" + e.getMessage() + e.getLocalizedMessage());
		}

		System.out.println("dadosCli enviado ao service eh " + perfil.toString());
		return perfil;
	}

	private String mensagemErro(String mensagem) {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "erro");
		jObjt.put("mensagem", mensagem);
		return jObjt.toString();
	}

}

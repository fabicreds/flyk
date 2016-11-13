package com.tcc.flyk.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ContatoPageService {
	
	private static final String EMAIL_ENVIO = "contact.flyk@gmail.com";
	
	private static final String NOME_ENVIO = "FLYK";
	
	private static final String SENHA_ENVIO = "flyk123456";
		

	public String enviarEmail(String nome, String email, String assunto, String mensagem) throws EmailException{
		SimpleEmail emailEnvio = new SimpleEmail();
		
		emailEnvio.setHostName("smtp.gmail.com");
		emailEnvio.setSmtpPort(465);
		emailEnvio.addTo(EMAIL_ENVIO, NOME_ENVIO);
		emailEnvio.setFrom(email, nome);
		emailEnvio.setSubject(assunto);
		emailEnvio.setMsg(mensagem);
		emailEnvio.setSSL(true);
		emailEnvio.setAuthentication(EMAIL_ENVIO, SENHA_ENVIO);
		emailEnvio.setTLS(true);
		
		emailEnvio.send();
		
		emailEnvio.addTo(email, nome);
		emailEnvio.setFrom(EMAIL_ENVIO, NOME_ENVIO);
		emailEnvio.setSubject("Contato com FLYK");
		emailEnvio.setContent(montarMensagem(nome), "text/html; charset=utf-8");
		
		emailEnvio.send();
		
		return mensagemSucesso();
	}
	
	private String mensagemSucesso() {
		JSONObject jObjt = new JSONObject();
		jObjt.put("retorno", "sucesso");
		jObjt.put("mensagem", "Email enviado com sucesso");
		return jObjt.toString();
	}
	
	private String montarMensagem(String nome){
		String message = "";
		
		message = message + "<html>";
		message = message + "<body>";
		message = message + "	<div style=\"height: 800px; width: 560px; vertical-align: middle;font-family:'Comic Sans MS', cursive, sans-serif !important\">";
		message = message + "		<table style=\"border-collapse:collapse;border-spacing:0;border:none;\" >";
		message = message + "			<tr height=\"30px\" >";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"480px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "			</tr>";
		message = message + "			<tr height=\"30px\" >";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td width=\"480px\" style=\"padding: 10px\">";
		message = message + "					Olá " + nome + ", <br>";
		message = message + "					<br>";
		message = message + "					Obrigado por entrar em contato conosco!<br>";
		message = message + "					<br>";
		message = message + "					Responderemos às suas dúvidas assim que possível.<br>";
		message = message + "					<br>";
		message = message + "					Obrigada novamente!<br>";
		message = message + "					<br>";
		message = message + "					FLYK";
		message = message + "				</td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "			</tr>";
		message = message + "			<tr height=\"30px\" >";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"480px\"></td>";
		message = message + "				<td bgcolor=\"#EEEEEE\" width=\"30px\"></td>";
		message = message + "				<td width=\"100px\"></td>";
		message = message + "			</tr>";
		message = message + "		</table>";
		message = message + "	</div>";
		message = message + "</body>";
		message = message + "</html>";
		
		
		return message;
		
	}
}

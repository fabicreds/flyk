package com.tcc.flyk.entity;

import org.json.JSONObject;

import com.tcc.flyk.entity.enumerator.TipoCadastroEnum;

public class Usuario {
	
	private String nome;
	
	private String usuario;
	
	private String email;
	
	private TipoCadastroEnum tipoCadastro;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCadastroEnum getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(TipoCadastroEnum tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", usuario=" + usuario + ", email=" + email + ", tipoCadastro=" + tipoCadastro
				+ "]";
	}
	
	public JSONObject toJSON(){
		JSONObject jObjt = new JSONObject();
		jObjt.put("nome", nome);
		jObjt.put("usuario", usuario);
		jObjt.put("email", email);
		jObjt.put("tipoCadastro", tipoCadastro);
		return jObjt;
	}
	

}

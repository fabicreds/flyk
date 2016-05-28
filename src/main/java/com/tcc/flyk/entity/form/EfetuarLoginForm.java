package com.tcc.flyk.entity.form;

public class EfetuarLoginForm {

	//email ou usuario para efetuar login
	private String email;
	
	private String senha;
	
	private boolean lembrar;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isLembrar() {
		return lembrar;
	}

	public void setLembrar(boolean lembrar) {
		this.lembrar = lembrar;
	}
	
	
}

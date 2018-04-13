package br.com.fiap.jsf.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {

	private String user;

	private String pass;

	public void validarLogin() {
		if (user.equals("FIAP") && pass.equals("FIAP2018")) {
			System.out.println("Logado");
		}else {
			System.out.println("Login incorreto");	
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}

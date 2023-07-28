package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fantusuario")
public class FantUsuario {
	@Id
	private Long id;
	private String login;
	private String nome_role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome_role() {
		return nome_role;
	}
	public void setNome_role(String nome_role) {
		this.nome_role = nome_role;
	}
	
	

}

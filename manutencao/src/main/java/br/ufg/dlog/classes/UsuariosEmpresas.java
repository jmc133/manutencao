package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_usuario_empresa")
public class UsuariosEmpresas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id_ue;
	private String nome;
	private String email;
	private String senha;
	private String sexo;
	private Long vinculo;
	
	public Long getId_ue() {
		return id_ue;
	}
	public void setId_ue(Long id_ue) {
		this.id_ue = id_ue;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getVinculo() {
		return vinculo;
	}
	public void setVinculo(Long vinculo) {
		this.vinculo = vinculo;
	}
	
	

}

package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sexo")
public class Sexo {
	@Id
	private String sexo;
	private String nome_sexo;
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNome_sexo() {
		return nome_sexo;
	}
	public void setNome_sexo(String nome_sexo) {
		this.nome_sexo = nome_sexo;
	}
	

}

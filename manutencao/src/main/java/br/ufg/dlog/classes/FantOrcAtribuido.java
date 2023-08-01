package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantOrcAtribuido {
	@Id
	private String nome;
	private Double soma;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getSoma() {
		return soma;
	}
	public void setSoma(Double soma) {
		this.soma = soma;
	}
	

}

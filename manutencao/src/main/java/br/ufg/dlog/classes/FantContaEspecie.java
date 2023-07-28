package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantContaEspecie {
	@Id
	private String especie;
	private Integer conta;
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public Integer getConta() {
		return conta;
	}
	public void setConta(Integer conta) {
		this.conta = conta;
	}
	
	

}

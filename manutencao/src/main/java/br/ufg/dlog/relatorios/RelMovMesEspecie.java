package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelMovMesEspecie {
	@Id
	private Long mes;
	private Long cont;
	private String especie;
	
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	public Long getCont() {
		return cont;
	}
	public void setCont(Long cont) {
		this.cont = cont;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	

}

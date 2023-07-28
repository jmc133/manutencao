package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelMovMesVeiculo {
	@Id
	private Long mes;
	private Long cont;
	private String placa;
	
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
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

}

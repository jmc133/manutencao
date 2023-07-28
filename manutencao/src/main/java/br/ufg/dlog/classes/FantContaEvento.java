package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FantContaEvento")
public class FantContaEvento {
	@Id
	private String evento;
	private int mes_s;
	private Long conta;
	private Long km;
	
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public int getMes_s() {
		return mes_s;
	}
	public void setMes_s(int mes_s) {
		this.mes_s = mes_s;
	}
	public Long getConta() {
		return conta;
	}
	public void setConta(Long conta) {
		this.conta = conta;
	}
	public Long getKm() {
		return km;
	}
	public void setKm(Long km) {
		this.km = km;
	}
	
	

}

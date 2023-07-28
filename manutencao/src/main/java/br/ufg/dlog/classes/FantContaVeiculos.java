package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FantContaVeiculos")
public class FantContaVeiculos {
	@Id
	private String placa_atual;
	private int mes_s;
	private Long conta;
	private Long km;
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
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

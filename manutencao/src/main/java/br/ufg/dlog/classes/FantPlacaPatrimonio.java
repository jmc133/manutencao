package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantPlacaPatrimonio {
	@Id
	private String patrimonio;
	private String placa_atual;
	
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
	}
	
	

}

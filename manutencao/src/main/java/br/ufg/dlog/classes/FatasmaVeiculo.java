package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;




@Entity
public class FatasmaVeiculo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String patrimonio;
	private String placa_atual;
	private String tipo;
	
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
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

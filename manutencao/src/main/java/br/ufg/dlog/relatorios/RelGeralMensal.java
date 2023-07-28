package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelGeralMensal {
	@Id
	private String mes_s;
	private String cont;
	



	public String getMes_s() {
		return mes_s;
	}
	public void setMes_s(String mes_s) {
		this.mes_s = mes_s;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@Override
	public String toString() {
		return "RelGeralMensal [mes_s=" + mes_s + ", cont=" + cont + "]";
	}
	
	

}

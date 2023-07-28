package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelMovMesMotorista {
	@Id
	public Long mes;
	public Long cont;
	public String nm_pessoa_func;
	
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
	public String getNm_pessoa_func() {
		return nm_pessoa_func;
	}
	public void setNm_pessoa_func(String nm_pessoa_func) {
		this.nm_pessoa_func = nm_pessoa_func;
	}
	
	

}

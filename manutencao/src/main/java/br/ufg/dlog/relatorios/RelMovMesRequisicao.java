package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelMovMesRequisicao {
	@Id
	private Long mes;
	private Long cont;
	private String requisicao;
	
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
	public String getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}
	
	

}

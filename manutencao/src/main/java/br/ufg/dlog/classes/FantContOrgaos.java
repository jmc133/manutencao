package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FantContOrgaos")
public class FantContOrgaos {
	@Id
	private String sg_orgao;
	private int mes_s;
	private Long conta;
	private Long km;
	
	
	public String getSg_orgao() {
		return sg_orgao;
	}
	public void setSg_orgao(String sg_orgao) {
		this.sg_orgao = sg_orgao;
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

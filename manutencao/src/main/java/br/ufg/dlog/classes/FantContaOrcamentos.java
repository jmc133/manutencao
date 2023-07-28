package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantContaOrcamentos {
	@Id
	private Long fk_ordem_servico;
	private int cont;
	
	public Long getFk_ordem_servico() {
		return fk_ordem_servico;
	}
	public void setFk_ordem_servico(Long fk_ordem_servico) {
		this.fk_ordem_servico = fk_ordem_servico;
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	
	

}

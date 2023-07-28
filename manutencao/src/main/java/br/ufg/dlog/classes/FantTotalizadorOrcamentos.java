package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantTotalizadorOrcamentos {
	@Id
	private Long linha;
	private Long id_ordem;
	private String nm_fantasia;
	private Double total;
	private Long pj;
	
	
	
	public Long getPj() {
		return pj;
	}
	public void setPj(Long pj) {
		this.pj = pj;
	}
	public Long getLinha() {
		return linha;
	}
	public void setLinha(Long linha) {
		this.linha = linha;
	}
	public Long getId_ordem() {
		return id_ordem;
	}
	public void setId_ordem(Long id_ordem) {
		this.id_ordem = id_ordem;
	}
	public String getNm_fantasia() {
		return nm_fantasia;
	}
	public void setNm_fantasia(String nm_fantasia) {
		this.nm_fantasia = nm_fantasia;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	

}

package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class FantVisualizarOrcamentos {
	@Id
	private Long linha;
	private String nm_fantasia;
	private String cidade;
	private String orcador;
	private String descricao;
	private Long fk_defeitos;
	private Long pj;
	private Double valor_unitario;
	private Double valor_total;
	private String placa_atual;
	
	
	
	public Long getPj() {
		return pj;
	}
	public void setPj(Long pj) {
		this.pj = pj;
	}
	public Long getFk_defeitos() {
		return fk_defeitos;
	}
	public void setFk_defeitos(Long fk_defeitos) {
		this.fk_defeitos = fk_defeitos;
	}
	public Long getLinha() {
		return linha;
	}
	public void setLinha(Long linha) {
		this.linha = linha;
	}
	public String getNm_fantasia() {
		return nm_fantasia;
	}
	public void setNm_fantasia(String nm_fantasia) {
		this.nm_fantasia = nm_fantasia;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getOrcador() {
		return orcador;
	}
	public void setOrcador(String orcador) {
		this.orcador = orcador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
	}
	
	

}

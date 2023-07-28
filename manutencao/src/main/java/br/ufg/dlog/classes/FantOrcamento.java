package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FantOrcamento {
	@Id
	private Long id_orcamento;
	private Long id_ordem;
	private Float qtd_relatado;
	private String descricao;
	private Double valor_unitario;
	private Double valor_total;
	
	public Long getId_orcamento() {
		return id_orcamento;
	}
	public void setId_orcamento(Long id_orcamento) {
		this.id_orcamento = id_orcamento;
	}
	public Long getId_ordem() {
		return id_ordem;
	}
	public void setId_ordem(Long id_ordem) {
		this.id_ordem = id_ordem;
	}
	
	
	public Float getQtd_relatado() {
		return qtd_relatado;
	}
	public void setQtd_relatado(Float qtd_relatado) {
		this.qtd_relatado = qtd_relatado;
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
	
	

}

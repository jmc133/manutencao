package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Orcamentos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id_orcamento;
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_orcamento;
	private Long fk_ordem_servico;
	private Long fk_pessoa_juridica;
	private Long fk_defeitos_relatados;
	private Double valor_unitario;
	private Double valor_total;
	private Long responsavel_orcamento;
	private String orcador;
	@Column(length = 1)
	private String orc_atribuido;
	
	
	
	
	
	
	
	
	public String getOrc_atribuido() {
		return orc_atribuido;
	}
	public void setOrc_atribuido(String orc_atribuido) {
		this.orc_atribuido = orc_atribuido;
	}
	public Long getId_orcamento() {
		return id_orcamento;
	}
	public void setId_orcamento(Long id_orcamento) {
		this.id_orcamento = id_orcamento;
	}
	public Date getData_orcamento() {
		return data_orcamento;
	}
	public void setData_orcamento(Date data_orcamento) {
		this.data_orcamento = data_orcamento;
	}
	public Long getFk_ordem_servico() {
		return fk_ordem_servico;
	}
	public void setFk_ordem_servico(Long fk_ordem_servico) {
		this.fk_ordem_servico = fk_ordem_servico;
	}
	public Long getFk_pessoa_juridica() {
		return fk_pessoa_juridica;
	}
	public void setFk_pessoa_juridica(Long fk_pessoa_juridica) {
		this.fk_pessoa_juridica = fk_pessoa_juridica;
	}
	public Long getFk_defeitos_relatados() {
		return fk_defeitos_relatados;
	}
	public void setFk_defeitos_relatados(Long fk_defeitos_relatados) {
		this.fk_defeitos_relatados = fk_defeitos_relatados;
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
	public Long getResponsavel_orcamento() {
		return responsavel_orcamento;
	}
	public void setResponsavel_orcamento(Long responsavel_orcamento) {
		this.responsavel_orcamento = responsavel_orcamento;
	}
	public String getOrcador() {
		return orcador;
	}
	public void setOrcador(String orcador) {
		this.orcador = orcador;
	}
	
	
	
	

}

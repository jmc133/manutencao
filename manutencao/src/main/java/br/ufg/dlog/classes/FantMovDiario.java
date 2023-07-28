package br.ufg.dlog.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fantmovdiario")
public class FantMovDiario {
	@Id
	private Long codigo;
	private String requisicao;
	private String sg_orgao;
	private String destino;
	private String solicitante;
	private String pri_nome;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}
	public String getSg_orgao() {
		return sg_orgao;
	}
	public void setSg_orgao(String sg_orgao) {
		this.sg_orgao = sg_orgao;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public String getPri_nome() {
		return pri_nome;
	}
	public void setPri_nome(String pri_nome) {
		this.pri_nome = pri_nome;
	}
	
	

}

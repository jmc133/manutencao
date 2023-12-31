package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_REQUISICAO")
public class Requisicao implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "COD_REQ")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cod_req;
	@Column(name = "REQUISICAO")
	@NotNull
	private String requisicao;
	public Long getCod_req() {
		return cod_req;
	}
	public void setCod_req(Long cod_req) {
		this.cod_req = cod_req;
	}
	public String getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}
	
	

}

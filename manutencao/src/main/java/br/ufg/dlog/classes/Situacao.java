package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_SITUACAO")
public class Situacao implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String cd_situacao;
	@NotNull
	private String situacao;
	

	public String getCd_situacao() {
		return cd_situacao;
	}
	public void setCd_situacao(String cd_situacao) {
		this.cd_situacao = cd_situacao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	

}

package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "Cidades")
public class Cidade implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	private Long cd_cidade;
	@NotNull
	private String nm_cidade;
	private String pais;
	private String sigla_uf;
	@Column(name = "NR_CODIGO_SIAPE" )
	private int nr_codigo_siape;
	
	public Long getCd_cidade() {
		return cd_cidade;
	}
	public void setCd_cidade(Long cd_cidade) {
		this.cd_cidade = cd_cidade;
	}
	public String getNm_cidade() {
		return nm_cidade;
	}
	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getSigla_uf() {
		return sigla_uf;
	}
	public void setSigla_uf(String sigla_uf) {
		this.sigla_uf = sigla_uf;
	}
	public int getNr_codigo_siape() {
		return nr_codigo_siape;
	}
	public void setNr_codigo_siape(int nr_codigo_siape) {
		this.nr_codigo_siape = nr_codigo_siape;
	}

	
	

}

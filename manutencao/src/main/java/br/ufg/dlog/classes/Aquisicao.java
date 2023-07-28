package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name = "t_aquisicao")
public class Aquisicao implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "cod_aqui")
	private Long cod_aqui;
	@Column(name = "adquirido_a")
	private String adquirido_a;
	@Column(name = "forma_aqui")
	private String forma_aqui;
	public Long getCod_aqui() {
		return cod_aqui;
	}
	public void setCod_aqui(Long cod_aqui) {
		this.cod_aqui = cod_aqui;
	}
	public String getAdquirido_a() {
		return adquirido_a;
	}
	public void setAdquirido_a(String adquirido_a) {
		this.adquirido_a = adquirido_a;
	}
	public String getForma_aqui() {
		return forma_aqui;
	}
	public void setForma_aqui(String forma_aqui) {
		this.forma_aqui = forma_aqui;
	}
	@Override
	public String toString() {
		return "Aquisicao [cod_aqui=" + cod_aqui + ", adquirido_a=" + adquirido_a + ", forma_aqui=" + forma_aqui + "]";
	}
	

	

	

}

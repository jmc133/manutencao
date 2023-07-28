package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_MODELO")
public class Modelo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "COD_MOD")
	private Long cod_mod;
	@NotNull
	private String t_modelo;
	
	public Long getCod_mod() {
		return cod_mod;
	}
	public void setCod_mod(Long cod_mod) {
		this.cod_mod = cod_mod;
	}
	public String getT_modelo() {
		return t_modelo;
	}
	public void setT_modelo(String t_modelo) {
		this.t_modelo = t_modelo;
	}
	
	

}

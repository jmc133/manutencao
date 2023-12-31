package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_ESPECIE")
public class Especie implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	private int cd_especie;
	@NotNull
	private String especie;
	
	public int getCd_especie() {
		return cd_especie;
	}
	public void setCd_especie(int cd_especie) {
		this.cd_especie = cd_especie;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	

}

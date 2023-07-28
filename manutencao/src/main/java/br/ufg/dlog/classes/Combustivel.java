package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_COMBUSTIVEL")
public class Combustivel implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int cd_comb;
	@NotNull
	private String combustivel;
	
	
	public int getCd_comb() {
		return cd_comb;
	}
	public void setCd_comb(int cd_comb) {
		this.cd_comb = cd_comb;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	
	

}

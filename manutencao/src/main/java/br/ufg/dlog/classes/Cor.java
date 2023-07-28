package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "t_cor")
public class Cor implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_cor;
	@NotNull
	private String cor;

	
	public Long getCod_cor() {
		return cod_cor;
	}
	public void setCod_cor(Long cod_cor) {
		this.cod_cor = cod_cor;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	

}

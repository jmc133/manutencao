package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_MARCA")
public class Marca implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "COD_MARCA")
	private Long cod_marca;
	@NotNull
	private String marca;

	
	public Long getCod_marca() {
		return cod_marca;
	}
	public void setCod_marca(Long cod_marca) {
		this.cod_marca = cod_marca;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	

}

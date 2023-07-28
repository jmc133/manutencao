package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "T_TIPO")
public class Tipo implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_tipo;
	@NotNull
	private String tipo;
	

	public Long getCod_tipo() {
		return cod_tipo;
	}
	public void setCod_tipo(Long cod_tipo) {
		this.cod_tipo = cod_tipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}

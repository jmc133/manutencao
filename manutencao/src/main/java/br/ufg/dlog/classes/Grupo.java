package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name = "T_GRUPO")
public class Grupo implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cod_grupo;
	private String grupo;
	

	public Long getCod_grupo() {
		return cod_grupo;
	}
	public void setCod_grupo(Long cod_grupo) {
		this.cod_grupo = cod_grupo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	

}

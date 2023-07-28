package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Table(name = "T_LOTACAO")
@Entity
public class Lotacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private Long CD_LOTACAO;
	@NotNull
	private String NM_LOTACAO;
	private Long CD_UNI;
	
	public Long getCD_LOTACAO() {
		return CD_LOTACAO;
	}
	public void setCD_LOTACAO(Long cD_LOTACAO) {
		CD_LOTACAO = cD_LOTACAO;
	}
	public String getNM_LOTACAO() {
		return NM_LOTACAO;
	}
	public void setNM_LOTACAO(String nM_LOTACAO) {
		NM_LOTACAO = nM_LOTACAO;
	}
	public Long getCD_UNI() {
		return CD_UNI;
	}
	public void setCD_UNI(Long cD_UNI) {
		CD_UNI = cD_UNI;
	}
	
	
	

}

package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Tipo_manutencao implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
  private Long cd_tipo_manutencao;
  @NotNull
  private String nm_tipo_manutencao;
  
  
public Long getCd_tipo_manutencao() {
	return cd_tipo_manutencao;
}
public void setCd_tipo_manutencao(Long cd_tipo_manutencao) {
	this.cd_tipo_manutencao = cd_tipo_manutencao;
}
public String getNm_tipo_manutencao() {
	return nm_tipo_manutencao;
}
public void setNm_tipo_manutencao(String nm_tipo_manutencao) {
	this.nm_tipo_manutencao = nm_tipo_manutencao;
}
  
  
}

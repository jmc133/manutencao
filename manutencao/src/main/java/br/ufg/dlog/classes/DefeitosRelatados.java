package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DefeitosRelatados implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long IdDefeitos;
	private Long fkOrdemServico;
	private Float QtdRelatado;
	private String descricao;
	private Long atribuido;
	
	
	
	public Long getAtribuido() {
		return atribuido;
	}
	public void setAtribuido(Long atribuido) {
		this.atribuido = atribuido;
	}
	public Long getIdDefeitos() {
		return IdDefeitos;
	}
	public void setIdDefeitos(Long idDefeitos) {
		IdDefeitos = idDefeitos;
	}
	public Long getFkOrdemServico() {
		return fkOrdemServico;
	}
	public void setFkOrdemServico(Long fkOrdemServico) {
		this.fkOrdemServico = fkOrdemServico;
	}

	public Float getQtdRelatado() {
		return QtdRelatado;
	}
	public void setQtdRelatado(Float qtdRelatado) {
		QtdRelatado = qtdRelatado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}

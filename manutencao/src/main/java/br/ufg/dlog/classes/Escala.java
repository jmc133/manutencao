package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Escala implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@NotNull
	private String veiculo;
	@NotNull
	private Long unidade;
	private String destino;
	@Column(name = "data_inicio")
	private String data_inicio;
	@Column(name = "data_final")
	private String data_final;
	private Long condutor;
	private Long condutor2;
	private Double diarias;
	private Long usuario;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datando;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public Long getUnidade() {
		return unidade;
	}
	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getData_final() {
		return data_final;
	}
	public void setData_final(String data_final) {
		this.data_final = data_final;
	}
	public Long getCondutor() {
		return condutor;
	}
	public void setCondutor(Long condutor) {
		this.condutor = condutor;
	}
	public Double getDiarias() {
		return diarias;
	}
	public void setDiarias(Double diarias) {
		this.diarias = diarias;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public Long getCondutor2() {
		return condutor2;
	}
	public void setCondutor2(Long condutor2) {
		this.condutor2 = condutor2;
	}
	public Date getDatando() {
		return datando;
	}
	public void setDatando(Date datando) {
		this.datando = datando;
	}
	
	

}

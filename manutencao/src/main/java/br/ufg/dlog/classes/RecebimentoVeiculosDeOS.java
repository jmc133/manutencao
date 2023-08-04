package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "recebimentoveiculosos")
public class RecebimentoVeiculosDeOS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long idrecebimentos;
	private Long fkordemservico;
	private Long fkpessoajuridica;
	private String responsavelreceber;
	private String responsavelliberar;
	private String posicaotanquerecebido;
	private String posicaotanqueentregue;
	private String relatoavarialataria;
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataentrada;
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dataentrega;
	private Long kmentrada;
	private Long kmentrega;
	@Column(length = 1)
	private String confirmaleitura;
	private String veiculo;
	
	public Long getIdrecebimentos() {
		return idrecebimentos;
	}
	public void setIdrecebimentos(Long idrecebimentos) {
		this.idrecebimentos = idrecebimentos;
	}
	public Long getFkordemservico() {
		return fkordemservico;
	}
	public void setFkordemservico(Long fkordemservico) {
		this.fkordemservico = fkordemservico;
	}
	public Long getFkpessoajuridica() {
		return fkpessoajuridica;
	}
	public void setFkpessoajuridica(Long fkpessoajuridica) {
		this.fkpessoajuridica = fkpessoajuridica;
	}
	public String getResponsavelreceber() {
		return responsavelreceber;
	}
	public void setResponsavelreceber(String responsavelreceber) {
		this.responsavelreceber = responsavelreceber;
	}
	public String getResponsavelliberar() {
		return responsavelliberar;
	}
	public void setResponsavelliberar(String responsavelliberar) {
		this.responsavelliberar = responsavelliberar;
	}
	public String getPosicaotanquerecebido() {
		return posicaotanquerecebido;
	}
	public void setPosicaotanquerecebido(String posicaotanquerecebido) {
		this.posicaotanquerecebido = posicaotanquerecebido;
	}
	public String getPosicaotanqueentregue() {
		return posicaotanqueentregue;
	}
	public void setPosicaotanqueentregue(String posicaotanqueentregue) {
		this.posicaotanqueentregue = posicaotanqueentregue;
	}
	public String getRelatoavarialataria() {
		return relatoavarialataria;
	}
	public void setRelatoavarialataria(String relatoavarialataria) {
		this.relatoavarialataria = relatoavarialataria;
	}
	public Date getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}
	public Date getDataentrega() {
		return dataentrega;
	}
	public void setDataentrega(Date dataentrega) {
		this.dataentrega = dataentrega;
	}
	public Long getKmentrada() {
		return kmentrada;
	}
	public void setKmentrada(Long kmentrada) {
		this.kmentrada = kmentrada;
	}
	public Long getKmentrega() {
		return kmentrega;
	}
	public void setKmentrega(Long kmentrega) {
		this.kmentrega = kmentrega;
	}
	public String getConfirmaleitura() {
		return confirmaleitura;
	}
	public void setConfirmaleitura(String confirmaleitura) {
		this.confirmaleitura = confirmaleitura;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	
	
	
	

}

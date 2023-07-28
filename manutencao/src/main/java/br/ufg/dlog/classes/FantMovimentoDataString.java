package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class FantMovimentoDataString implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cd_saida1;
	private String requisicao;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI")
	private Date data_est;
	private String data_s;
	private String data_r;
	private String placa_atual;
	private String s_destino;
	private Double km_s;
	private Double km_r;
	private Double km_ro;
	private String sg_orgao;
	private String evento;
	private String s_solicitante;
    private String s_executado;
    private String nm_pessoa_func;
    
    
	public Long getCd_saida1() {
		return cd_saida1;
	}
	public void setCd_saida1(Long cd_saida1) {
		this.cd_saida1 = cd_saida1;
	}
	public String getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}
	public Date getData_est() {
		return data_est;
	}
	public void setData_est(Date data_est) {
		this.data_est = data_est;
	}
	public String getData_s() {
		return data_s;
	}
	public void setData_s(String data_s) {
		this.data_s = data_s;
	}
	public String getData_r() {
		return data_r;
	}
	public void setData_r(String data_r) {
		this.data_r = data_r;
	}
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
	}
	public String getS_destino() {
		return s_destino;
	}
	public void setS_destino(String s_destino) {
		this.s_destino = s_destino;
	}
	public Double getKm_s() {
		return km_s;
	}
	public void setKm_s(Double km_s) {
		this.km_s = km_s;
	}
	public Double getKm_r() {
		return km_r;
	}
	public void setKm_r(Double km_r) {
		this.km_r = km_r;
	}
	public Double getKm_ro() {
		return km_ro;
	}
	public void setKm_ro(Double km_ro) {
		this.km_ro = km_ro;
	}
	public String getSg_orgao() {
		return sg_orgao;
	}
	public void setSg_orgao(String sg_orgao) {
		this.sg_orgao = sg_orgao;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getS_solicitante() {
		return s_solicitante;
	}
	public void setS_solicitante(String s_solicitante) {
		this.s_solicitante = s_solicitante;
	}
	public String getS_executado() {
		return s_executado;
	}
	public void setS_executado(String s_executado) {
		this.s_executado = s_executado;
	}
	public String getNm_pessoa_func() {
		return nm_pessoa_func;
	}
	public void setNm_pessoa_func(String nm_pessoa_func) {
		this.nm_pessoa_func = nm_pessoa_func;
	}
    
    

}

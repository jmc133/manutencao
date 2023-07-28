package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class FantMovimentoEdit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cd_saida1;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI")
	private Date data_est;
    private String s_destino;
    private String requisicao;
    private String sg_orgao;
    private String placa_atual;
    private String pri_nome;
    private String evento;
    private Long km_r;
    private Long km_s;
    private Long km_ro;
    
    
	public Long getKm_r() {
		return km_r;
	}
	public void setKm_r(Long km_r) {
		this.km_r = km_r;
	}
	public Long getKm_s() {
		return km_s;
	}
	public void setKm_s(Long km_s) {
		this.km_s = km_s;
	}
	public Long getKm_ro() {
		return km_ro;
	}
	public void setKm_ro(Long km_ro) {
		this.km_ro = km_ro;
	}
	public Long getCd_saida1() {
		return cd_saida1;
	}
	public void setCd_saida1(Long cd_saida1) {
		this.cd_saida1 = cd_saida1;
	}
	public Date getData_est() {
		return data_est;
	}
	public void setData_est(Date data_est) {
		this.data_est = data_est;
	}
	public String getS_destino() {
		return s_destino;
	}
	public void setS_destino(String s_destino) {
		this.s_destino = s_destino;
	}
	public String getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}
	public String getSg_orgao() {
		return sg_orgao;
	}
	public void setSg_orgao(String sg_orgao) {
		this.sg_orgao = sg_orgao;
	}
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
	}
	public String getPri_nome() {
		return pri_nome;
	}
	public void setPri_nome(String pri_nome) {
		this.pri_nome = pri_nome;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
    
    

}

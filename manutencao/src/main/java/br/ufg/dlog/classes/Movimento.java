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
@Table(name = "t_saida1")
public class Movimento implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "cd_saida1")
	private Long id;
	private Long fk_req;
	private Long fk_motorista;
	private Long fk_uni;
	private String fk_veiculos;
	@Column(name = "s_executado")
	private String servico;
	@Column(name = "s_destino")
	private String destino;
	private String ocupantes;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date data_s;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date data_r;
    private Double km_s;
    private Double km_r;
    private Double km_ro;
    @Column(name = "v_s" , length = 1)
    private String v_s;
    @Column(name = "v_r" , length = 1)
    private String v_r;
    @Column(name = "s_solicitante" , length = 50)
    private String solicitante;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date data_est;
    private int mes_s;
    private int ano_s;
    private Long digitador;
    private int dias;
    private String observacoes;
    @Column(name = "semana", length = 10)
    private String semana;
    @Column(name = "evento_s")
    private Long evento;
    @Column(name = "contato_s" , length = 50)
    private String contato;
    private String processo;
    
    
    
	public String getProcesso() {
		return processo;
	}
	public void setProcesso(String processo) {
		this.processo = processo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFk_req() {
		return fk_req;
	}
	public void setFk_req(Long fk_req) {
		this.fk_req = fk_req;
	}
	public Long getFk_motorista() {
		return fk_motorista;
	}
	public void setFk_motorista(Long fk_motorista) {
		this.fk_motorista = fk_motorista;
	}
	public Long getFk_uni() {
		return fk_uni;
	}
	public void setFk_uni(Long fk_uni) {
		this.fk_uni = fk_uni;
	}
	public String getFk_veiculos() {
		return fk_veiculos;
	}
	public void setFk_veiculos(String fk_veiculos) {
		this.fk_veiculos = fk_veiculos;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getOcupantes() {
		return ocupantes;
	}
	public void setOcupantes(String ocupantes) {
		this.ocupantes = ocupantes;
	}
	public Date getData_s() {
		return data_s;
	}
	public void setData_s(Date data_s) {
		this.data_s = data_s;
	}
	public Date getData_r() {
		return data_r;
	}
	public void setData_r(Date data_r) {
		this.data_r = data_r;
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
	public String getV_s() {
		return v_s;
	}
	public void setV_s(String v_s) {
		this.v_s = v_s;
	}
	public String getV_r() {
		return v_r;
	}
	public void setV_r(String v_r) {
		this.v_r = v_r;
	}
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public Date getData_est() {
		return data_est;
	}
	public void setData_est(Date data_est) {
		this.data_est = data_est;
	}
	public int getMes_s() {
		return mes_s;
	}
	public void setMes_s(int mes_s) {
		this.mes_s = mes_s;
	}
	public int getAno_s() {
		return ano_s;
	}
	public void setAno_s(int ano_s) {
		this.ano_s = ano_s;
	}
	public Long getDigitador() {
		return digitador;
	}
	public void setDigitador(Long digitador) {
		this.digitador = digitador;
	}
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}
	public Long getEvento() {
		return evento;
	}
	public void setEvento(Long evento) {
		this.evento = evento;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
    
    

}

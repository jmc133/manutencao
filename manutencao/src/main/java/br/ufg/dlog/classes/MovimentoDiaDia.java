package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "t_movimentodiario")
public class MovimentoDiaDia implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long codigo;
	@NotNull
	private Long requisicao;
	@NotNull
	private Long unidade;
	@NotNull
	private String veiculo;
	private Long motorista;
	private String servico;
	private String destino;
	private String ocupantes;
	private String solicitante;
	@NotNull 
	private Long evento;
	private String horario;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(Long requisicao) {
		this.requisicao = requisicao;
	}
	public Long getUnidade() {
		return unidade;
	}
	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public Long getMotorista() {
		return motorista;
	}
	public void setMotorista(Long motorista) {
		this.motorista = motorista;
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
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public Long getEvento() {
		return evento;
	}
	public void setEvento(Long evento) {
		this.evento = evento;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	

}

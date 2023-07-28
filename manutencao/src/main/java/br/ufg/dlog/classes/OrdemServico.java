package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class OrdemServico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_ordem")
	private Long id_ordem;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_conclusa;
	@Column(name = "data_emissao")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_emissao;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_retorno;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_saida;
	private Long km_concluso;
	private Long km_saida;
	private Long km_saida_buscar;
	private Long km_concluso_busca;
	private Long quem_buscou;
	private Long quem_levou;
	@Column(length = 1)
	private String termo_responsabilidade_ida;
	@Column(length = 1)
	private String termo_responsabilidade_volta;
	private String veiculo;
	private Long criador_os;
	@Column(length = 1)
	private String posicao_para_orcamento;
	
	
	

	public String getPosicao_para_orcamento() {
		return posicao_para_orcamento;
	}
	public void setPosicao_para_orcamento(String posicao_para_orcamento) {
		this.posicao_para_orcamento = posicao_para_orcamento;
	}
	
	public Long getId_ordem() {
		return id_ordem;
	}
	public void setId_ordem(Long id_ordem) {
		this.id_ordem = id_ordem;
	}
	public Date getData_conclusa() {
		return data_conclusa;
	}
	public void setData_conclusa(Date data_conclusa) {
		this.data_conclusa = data_conclusa;
	}

	public Date getData_emissao() {
		return data_emissao;
	}
	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}
	public Date getData_retorno() {
		return data_retorno;
	}
	public void setData_retorno(Date data_retorno) {
		this.data_retorno = data_retorno;
	}
	public Date getData_saida() {
		return data_saida;
	}
	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}
	public Long getKm_concluso() {
		return km_concluso;
	}
	public void setKm_concluso(Long km_concluso) {
		this.km_concluso = km_concluso;
	}
	public Long getKm_saida() {
		return km_saida;
	}
	public void setKm_saida(Long km_saida) {
		this.km_saida = km_saida;
	}
	public Long getKm_saida_buscar() {
		return km_saida_buscar;
	}
	public void setKm_saida_buscar(Long km_saida_buscar) {
		this.km_saida_buscar = km_saida_buscar;
	}
	public Long getKm_concluso_busca() {
		return km_concluso_busca;
	}
	public void setKm_concluso_busca(Long km_concluso_busca) {
		this.km_concluso_busca = km_concluso_busca;
	}
	public Long getQuem_buscou() {
		return quem_buscou;
	}
	public void setQuem_buscou(Long quem_buscou) {
		this.quem_buscou = quem_buscou;
	}
	public Long getQuem_levou() {
		return quem_levou;
	}
	public void setQuem_levou(Long quem_levou) {
		this.quem_levou = quem_levou;
	}
	public String getTermo_responsabilidade_ida() {
		return termo_responsabilidade_ida;
	}
	public void setTermo_responsabilidade_ida(String termo_responsabilidade_ida) {
		this.termo_responsabilidade_ida = termo_responsabilidade_ida;
	}
	public String getTermo_responsabilidade_volta() {
		return termo_responsabilidade_volta;
	}
	public void setTermo_responsabilidade_volta(String termo_responsabilidade_volta) {
		this.termo_responsabilidade_volta = termo_responsabilidade_volta;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public Long getCriador_os() {
		return criador_os;
	}
	public void setCriador_os(Long criador_os) {
		this.criador_os = criador_os;
	}
	
	

}

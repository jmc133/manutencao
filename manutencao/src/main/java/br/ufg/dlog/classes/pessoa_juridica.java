package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class pessoa_juridica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cd_pessoa_jur;
	@Column(length = 1)
	private String ativo;
	private String num_cep;
	private String cidade;
	@Column(name = "num_cnpj")
	private String cnpj;
	private String email;
	@NotNull
	private String nm_fantasia;
	private String nm_logradouro;
	@NotNull
	private String nm_pessoa_jur;
	@Column(length = 3)
	private String num_ddd;
	private String num_telefone;
	private String pais;
	private Long tipo_manutencao;
	@Column(length = 2)
	private String uf;
	
	public Long getCd_pessoa_jur() {
		return cd_pessoa_jur;
	}
	public void setCd_pessoa_jur(Long cd_pessoa_jur) {
		this.cd_pessoa_jur = cd_pessoa_jur;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getNum_cep() {
		return num_cep;
	}
	public void setNum_cep(String num_cep) {
		this.num_cep = num_cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNm_fantasia() {
		return nm_fantasia;
	}
	public void setNm_fantasia(String nm_fantasia) {
		this.nm_fantasia = nm_fantasia;
	}
	public String getNm_logradouro() {
		return nm_logradouro;
	}
	public void setNm_logradouro(String nm_logradouro) {
		this.nm_logradouro = nm_logradouro;
	}
	public String getNm_pessoa_jur() {
		return nm_pessoa_jur;
	}
	public void setNm_pessoa_jur(String nm_pessoa_jur) {
		this.nm_pessoa_jur = nm_pessoa_jur;
	}
	public String getNum_ddd() {
		return num_ddd;
	}
	public void setNum_ddd(String num_ddd) {
		this.num_ddd = num_ddd;
	}
	public String getNum_telefone() {
		return num_telefone;
	}
	public void setNum_telefone(String num_telefone) {
		this.num_telefone = num_telefone;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Long getTipo_manutencao() {
		return tipo_manutencao;
	}
	public void setTipo_manutencao(Long tipo_manutencao) {
		this.tipo_manutencao = tipo_manutencao;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	

}

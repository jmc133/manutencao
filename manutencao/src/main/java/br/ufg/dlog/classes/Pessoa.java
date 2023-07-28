package br.ufg.dlog.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "t_funcionarios")
public class Pessoa implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long cd_pessoa_func;
	@NotNull
	private String nm_pessoa_func;
	private String cargo_f;
	@NotNull
	private String pri_nome;
	@NotNull
	private String matricula_f;
	private String banco;
	private String agencia;
	private String conta;
	@CPF(message = "cpf incorreto")
	private String cpf;
	private String identidade;
	private String cnh;
	private String categoria;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vencimento;
	private String senha;
	private String pis;
	private String endereco;
	private Double salario;
	private Long lotacao;
	private String ativo;
	private String cep;
	private int ddd;
	private Long telefone;
	private int ddd1;
	private Long celular;
	private int a_nivel;
	private String bairro;
	private String e_mail;
	private String servidor_publico;
	private String cidade;
	private String uf;
	@Column(name = "abastecer", length = 1)
	private String abastecer;
	
	
	public String getAbastecer() {
		return abastecer;
	}
	public void setAbastecer(String abastecer) {
		this.abastecer = abastecer;
	}
	public Long getCd_pessoa_func() {
		return cd_pessoa_func;
	}
	public void setCd_pessoa_func(Long cd_pessoa_func) {
		this.cd_pessoa_func = cd_pessoa_func;
	}
	public String getNm_pessoa_func() {
		return nm_pessoa_func;
	}
	public void setNm_pessoa_func(String nm_pessoa_func) {
		this.nm_pessoa_func = nm_pessoa_func;
	}
	public String getCargo_f() {
		return cargo_f;
	}
	public void setCargo_f(String cargo_f) {
		this.cargo_f = cargo_f;
	}
	public String getPri_nome() {
		return pri_nome;
	}
	public void setPri_nome(String pri_nome) {
		this.pri_nome = pri_nome;
	}
	public String getMatricula_f() {
		return matricula_f;
	}
	public void setMatricula_f(String matricula_f) {
		this.matricula_f = matricula_f;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Long getLotacao() {
		return lotacao;
	}
	public void setLotacao(Long lotacao) {
		this.lotacao = lotacao;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public int getDdd1() {
		return ddd1;
	}
	public void setDdd1(int ddd1) {
		this.ddd1 = ddd1;
	}
	public Long getCelular() {
		return celular;
	}
	public void setCelular(Long celular) {
		this.celular = celular;
	}
	public int getA_nivel() {
		return a_nivel;
	}
	public void setA_nivel(int a_nivel) {
		this.a_nivel = a_nivel;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getServidor_publico() {
		return servidor_publico;
	}
	public void setServidor_publico(String servidor_publico) {
		this.servidor_publico = servidor_publico;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String toString() {
		return "Pessoa [cd_pessoa_func=" + cd_pessoa_func + ", nm_pessoa_func=" + nm_pessoa_func + ", cargo_f="
				+ cargo_f + ", pri_nome=" + pri_nome + ", matricula_f=" + matricula_f + ", banco=" + banco
				+ ", agencia=" + agencia + ", conta=" + conta + ", cpf=" + cpf + ", identidade=" + identidade + ", cnh="
				+ cnh + ", categoria=" + categoria + ", vencimento=" + vencimento + ", senha=" + senha + ", pis=" + pis
				+ ", endereco=" + endereco + ", salario=" + salario + ", lotacao=" + lotacao + ", ativo=" + ativo
				+ ", cep=" + cep + ", ddd=" + ddd + ", telefone=" + telefone + ", ddd1=" + ddd1 + ", celular=" + celular
				+ ", a_nivel=" + a_nivel + ", bairro=" + bairro + ", e_mail=" + e_mail + ", servidor_publico="
				+ servidor_publico + ", cidade=" + cidade + ", uf=" + uf + "]";
	}
	
	
	

}

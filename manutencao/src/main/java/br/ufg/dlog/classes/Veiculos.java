package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




@Entity
@Table(name = "t_veiculos")
public class Veiculos implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "patrimonio")
	private String patrimonio;
	@NotNull
	private String chassi;
	private int ano_modelo;
	private String codigo_renavam;
	private String placa_anterior;
	@NotNull
	private String placa_atual;
	private Long municipio_atual;
	private float valor;
	private float hp;
	private float depreciacao;
	private int ano_vei;
	@Column(name = "adquirido")
	private Long adquirido;
	private int ano_aqui;
	private Long municipio_anterior;
	private Long marca;
	private Long modelo;
	private Long tipo;
	private Long grupo;
	private Long cor_v;
	private String situacao;
	private int combustivel_v;
	private String sigla_v;
	private String ocupado_v;
	private Long lotacao_v;
	private int especie;
	private float tanque;
	private int impedimento;
	private Double tx_licenciamento;	
//	private Byte foto;
//	private File foto_file;
	private String foto_nome;
	private String foto_tipo;
	
	
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public int getAno_modelo() {
		return ano_modelo;
	}
	public void setAno_modelo(int ano_modelo) {
		this.ano_modelo = ano_modelo;
	}
	public String getCodigo_renavam() {
		return codigo_renavam;
	}
	public void setCodigo_renavam(String codigo_renavam) {
		this.codigo_renavam = codigo_renavam;
	}
	public String getPlaca_anterior() {
		return placa_anterior;
	}
	public void setPlaca_anterior(String placa_anterior) {
		this.placa_anterior = placa_anterior;
	}
	public String getPlaca_atual() {
		return placa_atual;
	}
	public void setPlaca_atual(String placa_atual) {
		this.placa_atual = placa_atual;
	}
	public Long getMunicipio_atual() {
		return municipio_atual;
	}
	public void setMunicipio_atual(Long municipio_atual) {
		this.municipio_atual = municipio_atual;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public float getHp() {
		return hp;
	}
	public void setHp(float hp) {
		this.hp = hp;
	}
	public float getDepreciacao() {
		return depreciacao;
	}
	public void setDepreciacao(float depreciacao) {
		this.depreciacao = depreciacao;
	}
	public int getAno_vei() {
		return ano_vei;
	}
	public void setAno_vei(int ano_vei) {
		this.ano_vei = ano_vei;
	}

	
	public Long getAdquirido() {
		return adquirido;
	}
	public void setAdquirido(Long adquirido) {
		this.adquirido = adquirido;
	}
	public int getAno_aqui() {
		return ano_aqui;
	}
	public void setAno_aqui(int ano_aqui) {
		this.ano_aqui = ano_aqui;
	}
	public Long getMunicipio_anterior() {
		return municipio_anterior;
	}
	public void setMunicipio_anterior(Long municipio_anterior) {
		this.municipio_anterior = municipio_anterior;
	}
	public Long getMarca() {
		return marca;
	}
	public void setMarca(Long marca) {
		this.marca = marca;
	}
	public Long getModelo() {
		return modelo;
	}
	public void setModelo(Long modelo) {
		this.modelo = modelo;
	}

	
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public Long getGrupo() {
		return grupo;
	}
	public void setGrupo(Long grupo) {
		this.grupo = grupo;
	}
	public Long getCor_v() {
		return cor_v;
	}
	public void setCor_v(Long cor_v) {
		this.cor_v = cor_v;
	}

	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getCombustivel_v() {
		return combustivel_v;
	}
	public void setCombustivel_v(int combustivel_v) {
		this.combustivel_v = combustivel_v;
	}
	public String getSigla_v() {
		return sigla_v;
	}
	public void setSigla_v(String sigla_v) {
		this.sigla_v = sigla_v;
	}
	public String getOcupado_v() {
		return ocupado_v;
	}
	public void setOcupado_v(String ocupado_v) {
		this.ocupado_v = ocupado_v;
	}
	public Long getLotacao_v() {
		return lotacao_v;
	}
	public void setLotacao_v(Long lotacao_v) {
		this.lotacao_v = lotacao_v;
	}
	public int getEspecie() {
		return especie;
	}
	public void setEspecie(int especie) {
		this.especie = especie;
	}
	public float getTanque() {
		return tanque;
	}
	public void setTanque(float tanque) {
		this.tanque = tanque;
	}
	public int getImpedimento() {
		return impedimento;
	}
	public void setImpedimento(int impedimento) {
		this.impedimento = impedimento;
	}
	public Double getTx_licenciamento() {
		return tx_licenciamento;
	}
	public void setTx_licenciamento(Double tx_licenciamento) {
		this.tx_licenciamento = tx_licenciamento;
	}
	/*public Byte getFoto() {
		return foto;
	}
	public void setFoto(Byte foto) {
		this.foto = foto;
	}
	public File getFoto_file() {
		return foto_file;
	}
	public void setFoto_file(File foto_file) {
		this.foto_file = foto_file;
	}*/
	public String getFoto_nome() {
		return foto_nome;
	}
	public void setFoto_nome(String foto_nome) {
		this.foto_nome = foto_nome;
	}
	public String getFoto_tipo() {
		return foto_tipo;
	}
	@Override
	public String toString() {
		return "Veiculos [patrimonio=" + patrimonio + ", chassi=" + chassi + ", ano_modelo=" + ano_modelo
				+ ", codigo_renavam=" + codigo_renavam + ", placa_anterior=" + placa_anterior + ", placa_atual="
				+ placa_atual + ", municipio_atual=" + municipio_atual + ", valor=" + valor + ", hp=" + hp
				+ ", depreciacao=" + depreciacao + ", ano_vei=" + ano_vei + ", adquirido=" + adquirido + ", ano_aqui="
				+ ano_aqui + ", municipio_anterior=" + municipio_anterior + ", marca=" + marca + ", modelo=" + modelo
				+ ", tipo=" + tipo + ", grupo=" + grupo + ", cor_v=" + cor_v + ", situacao=" + situacao
				+ ", combustivel_v=" + combustivel_v + ", sigla_v=" + sigla_v + ", ocupado_v=" + ocupado_v
				+ ", lotacao_v=" + lotacao_v + ", especie=" + especie + ", tanque=" + tanque + ", impedimento="
				+ impedimento + ", tx_licenciamento=" + tx_licenciamento + ", foto_nome=" + foto_nome + ", foto_tipo="
				+ foto_tipo + "]";
	}
	public void setFoto_tipo(String foto_tipo) {
		this.foto_tipo = foto_tipo;
	}

	
			
	
}

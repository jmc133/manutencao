package br.ufg.dlog.relatorios;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RelMovMesOrgaos")
public class RelMovMesOrgaos {
	@Id
	private String mes_s;
	private String cont;
	private String sg_orgao;

	public RelMovMesOrgaos() {
	}
		public RelMovMesOrgaos(String mes_s,String cont,String sg_orgao) {
			this.mes_s = mes_s;
			this.sg_orgao = sg_orgao;
			this.cont = cont;
		}
		public String getMes_s() {
			return mes_s;
		}
		public void setMes_s(String mes_s) {
			this.mes_s = mes_s;
		}
		public String getCont() {
			return cont;
		}
		public void setCont(String cont) {
			this.cont = cont;
		}
		public String getSg_orgao() {
			return sg_orgao;
		}
		public void setSg_orgao(String sg_orgao) {
			this.sg_orgao = sg_orgao;
		}
	


	

}

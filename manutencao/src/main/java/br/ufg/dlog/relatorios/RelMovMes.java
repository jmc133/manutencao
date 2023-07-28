package br.ufg.dlog.relatorios;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relmovmes")
public class RelMovMes {
	@Id
	private String  mes;
	private String cont;

	public RelMovMes(String mes, String cont) {
		this.mes=mes;
		this.cont = cont;
	}

	@Override
	public String toString() {
		return "RelMovMes [mes=" + mes + ", cont=" + cont + "]";
	}
	



	
	
	

}

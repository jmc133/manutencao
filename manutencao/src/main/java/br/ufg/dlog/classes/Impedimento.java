package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;





@Entity
@Table(name = "t_impedimento")
public class Impedimento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "cd_impedimento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cdImpedimento;;
	@NotNull
	@Column(name = "impedimento")
	private String impedimento;
	
	
	public int getCdImpedimento() {
		return cdImpedimento;
	}
	public void setCdImpedimento(int cdImpedimento) {
		this.cdImpedimento = cdImpedimento;
	}
	public String getImpedimento() {
		return impedimento;
	}
	public void setImpedimento(String impedimento) {
		this.impedimento = impedimento;
	}
	

}

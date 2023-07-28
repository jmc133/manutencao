package br.ufg.dlog.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;





@Entity
public class usuarios_role implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long usuario_id;
	
	private Long role_id;
	
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
	

}

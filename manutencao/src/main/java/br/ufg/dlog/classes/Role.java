package br.ufg.dlog.classes;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;



@Entity
public class Role implements GrantedAuthority {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Long id;
	private String nomeRole;

	@Override
	public String getAuthority() {
		// Retorna a regras de acesso(admin, gerente e assim por diante)
		return this.nomeRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}

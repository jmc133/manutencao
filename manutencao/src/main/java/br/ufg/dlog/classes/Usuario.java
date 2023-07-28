package br.ufg.dlog.classes;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;





@Entity
public class Usuario implements UserDetails{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@NotNull
	@Column(name = "login")
	private String login;
	@NotNull
	@Column(name = "senha")
	private String senha;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_role", joinColumns = @JoinColumn(name ="usuario_id",referencedColumnName = "id",
	table = "usuario"),// cria tabela de acesso do usuario
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id",
	table = "role")) 
	
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}

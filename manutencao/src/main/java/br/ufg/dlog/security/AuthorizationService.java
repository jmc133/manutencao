package br.ufg.dlog.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Usuario;
import br.ufg.dlog.repository.UsuarioRepository;

@Service
@Transactional
public class AuthorizationService implements UserDetailsService {
	@Autowired(required = false)
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findUserByLogin(username);
		System.out.println(user);
		if(Objects.isNull(user)) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return new User (user.getUsername(), user.getPassword(),
				user.isEnabled(), true,
				true,true, user.getAuthorities());
	}

}

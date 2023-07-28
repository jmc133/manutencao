package br.ufg.dlog.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantUsuario;

@Repository
public interface FantUsuarioRepository extends JpaRepository<FantUsuario, Long> {
	
	@Query(value = "select usuario.id,usuario.login,\"role\".nome_role from usuario,usuarios_role, \"role\"\r\n"
			+ "where usuarios_role.usuario_id=usuario.id\r\n"
			+ "and usuarios_role.role_id=\"role\".id\r\n"
			+ "and usuario.id=?1", nativeQuery = true)
	Collection<FantUsuario> verificaPermissao(Long id);

}

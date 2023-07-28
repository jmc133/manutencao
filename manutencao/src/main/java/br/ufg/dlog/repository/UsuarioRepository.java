package br.ufg.dlog.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Role;
import br.ufg.dlog.classes.Usuario;





@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
	@Query("select u.id from Usuario u where u.login=?1")
	Long buscaIdLogin(String login);
	@Query("select r from Role r")
	ArrayList<Role> listaDeRoles();
	@Query(value = "SELECT * FROM public.usuario where id=?1", nativeQuery = true)
	Usuario pesquisaPorId(Long id);
	@Query(value ="select max(id) from usuario", nativeQuery = true)
	Long maximoIdUsuario();
	@Query(value = "select * from usuario where login=?1", nativeQuery = true)
	Usuario selecionaUsuarioPorLogin(String login);

}

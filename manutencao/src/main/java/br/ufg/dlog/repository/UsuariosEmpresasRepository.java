package br.ufg.dlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.UsuariosEmpresas;

@Repository
public interface UsuariosEmpresasRepository extends JpaRepository<UsuariosEmpresas, Long> {
	@Query(value = "Select max(id_ue) from t_usuario_empresa", nativeQuery = true)
	Long maximoIdUsuarioEmpresa();
	
	@Query(value = "select * from t_usuario_empresa where email=?1", nativeQuery = true)
	UsuariosEmpresas selecionaUsuarioPorEmail(String email);

}

package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Grupo;

@Transactional
@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long>{
	@Query(value = "SELECT max(cod_grupo) FROM public.t_grupo", nativeQuery = true)
	Long maximoCodigoGrupo();

}

package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Tipo;

@Transactional
@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{
	@Query(value = "SELECT  max(cod_tipo) FROM public.t_tipo", nativeQuery = true)
	Long maximoCodTipo();

}

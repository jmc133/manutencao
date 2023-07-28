package br.ufg.dlog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Especie;

@Transactional
@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer>{
	
	@Query(value = "SELECT max(cd_especie) 	FROM public.t_especie", nativeQuery = true)
	Integer maximoCodigoEspecie();

}

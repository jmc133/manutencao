package br.ufg.dlog.repository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.dlog.classes.Combustivel;

@Repository
@Transactional
public interface CombustivelRepository extends JpaRepository<Combustivel, Integer> {
	@Query(value = "SELECT max(cd_comb) FROM public.t_combustivel", nativeQuery = true)
	Integer maximoCodigoCombustivel();

}

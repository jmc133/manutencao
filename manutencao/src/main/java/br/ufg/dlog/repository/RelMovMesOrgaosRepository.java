package br.ufg.dlog.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.ufg.dlog.relatorios.RelMovMesOrgaos;

@Repository
public interface RelMovMesOrgaosRepository extends JpaRepository<RelMovMesOrgaos, Long> {
	

	


}

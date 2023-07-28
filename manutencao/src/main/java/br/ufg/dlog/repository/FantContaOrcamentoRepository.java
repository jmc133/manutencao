package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContaOrcamentos;

@Repository
public interface FantContaOrcamentoRepository extends JpaRepository<FantContaOrcamentos, Long> {
	@Query(value = "select fk_ordem_servico,count(id_orcamento)cont from orcamentos where verificado is null GROUP by fk_ordem_servico", nativeQuery = true)
	List<FantContaOrcamentos> idOrcados();

}

package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantContaOrcamentos;
import br.ufg.dlog.classes.FantOrcamento;
import br.ufg.dlog.classes.Orcamentos;

@Repository
public interface OrcamentosRepository extends JpaRepository<Orcamentos, Long> {
	@Query(value = "select max(id_orcamento) from orcamentos", nativeQuery = true)
	Long selecionaMaxIdOrcamento();
	
	@Query(value = "select * from orcamentos where fk_pessoa_juridica=?1 and fk_ordem_servico=?2 and fk_defeitos_relatados=?3", nativeQuery = true)
	Orcamentos verificaSeJaFoiOrcado(Long pj,Long os, Long defeitos);
	
	@Query(value = "select count(id_orcamento) from orcamentos where verificado is null", nativeQuery = true)
	int qtdOrcamentosAbertos();
	
	
	
	

}

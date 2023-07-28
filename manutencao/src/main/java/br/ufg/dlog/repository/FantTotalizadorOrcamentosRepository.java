package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantTotalizadorOrcamentos;

@Repository
public interface FantTotalizadorOrcamentosRepository extends JpaRepository<FantTotalizadorOrcamentos, Long> {
	
	@Query(value = "select ROW_NUMBER() OVER(ORDER BY pessoa_juridica.nm_fantasia) linha, ordem_servico.id_ordem, pessoa_juridica.nm_fantasia,\r\n"
			+ "sum(valor_total)total, orcamentos.fk_pessoa_juridica pj\r\n"
			+ "from ordem_servico, orcamentos, pessoa_juridica\r\n"
			+ "where orcamentos.fk_pessoa_juridica=pessoa_juridica.cd_pessoa_jur\r\n"
			+ "and orcamentos.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "and ordem_servico.id_ordem=?1\r\n"
			+ "group by ordem_servico.id_ordem, pessoa_juridica.nm_fantasia,pj", nativeQuery = true)
	List<FantTotalizadorOrcamentos> totalizadorOrcamentos(Long id_servico);

}

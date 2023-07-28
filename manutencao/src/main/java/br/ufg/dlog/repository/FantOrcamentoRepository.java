package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantOrcamento;

@Repository
public interface FantOrcamentoRepository extends JpaRepository<FantOrcamento, Long> {
	
	@Query(value = "select ordem_servico.id_ordem, orcamentos.id_orcamento,defeitos_relatados.qtd_relatado,defeitos_relatados.descricao,\r\n"
			+ "orcamentos.valor_unitario, orcamentos.valor_total\r\n"
			+ "from ordem_servico, orcamentos, defeitos_relatados\r\n"
			+ "where orcamentos.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "and defeitos_relatados.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "and orcamentos.fk_defeitos_relatados=defeitos_relatados.id_defeitos\r\n"
			+ "and orcamentos.fk_pessoa_juridica=?2\r\n"
			+ "and ordem_servico.id_ordem=?1", nativeQuery = true)
	List<FantOrcamento> orcamentosPorFornecedor(Long os, Long pj);

}

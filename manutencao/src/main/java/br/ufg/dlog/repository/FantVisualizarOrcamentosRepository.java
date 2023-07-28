package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantVisualizarOrcamentos;

@Repository
public interface FantVisualizarOrcamentosRepository extends JpaRepository<FantVisualizarOrcamentos, Long> {
	
	@Query(value = "select ROW_NUMBER() OVER(ORDER BY pessoa_juridica.nm_fantasia) Linha, pessoa_juridica.nm_fantasia, pessoa_juridica.cidade, orcamentos.orcador,\r\n"
			+ "			defeitos_relatados.descricao, orcamentos.valor_unitario,orcamentos.valor_total, t_veiculos.placa_atual,\r\n"
			+ "			sum(orcamentos.valor_total)soma,defeitos_relatados.id_defeitos fk_defeitos, pessoa_juridica.cd_pessoa_jur pj,\r\n"
			+ "			orcamentos.id_orcamento id_orc, defeitos_relatados.atribuido, orcamentos.orc_atribuido\r\n"
			+ "			 from pessoa_juridica, ordem_servico, orcamentos, t_veiculos, defeitos_relatados\r\n"
			+ "			 where ordem_servico.id_ordem=?1\r\n"
			+ "			 and orcamentos.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "			 and orcamentos.fk_pessoa_juridica=pessoa_juridica.cd_pessoa_jur\r\n"
			+ "			 and ordem_servico.veiculo=t_veiculos.patrimonio\r\n"
			+ "			 and defeitos_relatados.fk_ordem_servico=ordem_servico.id_ordem	\r\n"
			+ "			 and orcamentos.fk_defeitos_relatados=defeitos_relatados.id_defeitos\r\n"
			+ "			 group by defeitos_relatados.id_defeitos, pessoa_juridica.nm_fantasia, pessoa_juridica.cidade, orcamentos.orcador,defeitos_relatados.descricao,\r\n"
			+ "			 orcamentos.valor_unitario, orcamentos.valor_total, t_veiculos.placa_atual,fk_defeitos,pj,id_orc, defeitos_relatados.atribuido, orcamentos.orc_atribuido\r\n"
			+ "			order by pessoa_juridica.nm_fantasia", nativeQuery = true)
	List<FantVisualizarOrcamentos> visualizarOrcamentos(Long id_ordem);

}

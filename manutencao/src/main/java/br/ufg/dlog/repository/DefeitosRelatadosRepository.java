package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.DefeitosRelatados;

@Repository
public interface DefeitosRelatadosRepository extends JpaRepository<DefeitosRelatados, Long> {
	@Query(value = "select * from defeitos_relatados where fk_ordem_servico=?1", nativeQuery = true)
	List<DefeitosRelatados> itensRelatados(Long fk);
	
	@Query(value = "select max(id_defeitos) from defeitos_relatados ", nativeQuery = true)
	Long maxId();
	
	@Query(value = "select * from defeitos_relatados where id_defeitos=?1", nativeQuery = true)
	DefeitosRelatados defeitoRelatado(Long id);
	
	@Query(value = "select defeitos_relatados.id_defeitos,defeitos_relatados.qtd_relatado,defeitos_relatados.descricao,defeitos_relatados.fk_ordem_servico, defeitos_relatados.atribuido\r\n"
			+ "			from defeitos_relatados, ordem_servico\r\n"
			+ "			where defeitos_relatados.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "			and ordem_servico.posicao_para_orcamento='S'\r\n"
			+ "			and ordem_servico.id_ordem=?1", nativeQuery = true)
	List<DefeitosRelatados> itensRelatadosAtivos(Long fkOrdem);

}

package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantOrcAtribuido;

@Repository
public interface FantOrcAtribuidoRepository extends JpaRepository<FantOrcAtribuido, String>{
	
	@Query(value = "select pessoa_juridica.nm_fantasia nome, sum(orcamentos.valor_total)soma from ordem_servico, orcamentos, pessoa_juridica\r\n"
			+ "where orcamentos.fk_ordem_servico=ordem_servico.id_ordem\r\n"
			+ "and orcamentos.fk_pessoa_juridica=pessoa_juridica.cd_pessoa_jur\r\n"
			+ "and ordem_servico.id_ordem=?1\r\n"
			+ "and orcamentos.orc_atribuido='S'\r\n"
			+ "group by nome ", nativeQuery = true)
	List<FantOrcAtribuido> listaTotalAtribuido(Long id_ordem);

}

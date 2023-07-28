package br.ufg.dlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.classes.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	@Query(value = "select max(id_ordem) from ordem_servico", nativeQuery = true)
	Long  selecionaMaxIdOrdemServico();
	@Query(value = "select max(id_ordem) from ordem_servico where km_concluso_busca is null", nativeQuery = true)
	Long  maximoIdAberta();
	@Query(value = "select *  from ordem_servico where posicao_para_orcamento='S'", nativeQuery = true)
	List<OrdemServico> ordensServicosAbertas();
	@Query(value = "select * from ordem_servico where id_ordem=?1", nativeQuery = true)
	OrdemServico verOrdemPorId(Long id);
	@Query(value = "select id_ordem  from ordem_servico where km_concluso_busca is null", nativeQuery = true)
	Long[] ordemServicoAbertas();
	@Query(value = "select t_veiculos.placa_atual from ordem_servico, t_veiculos where ordem_servico.veiculo=t_veiculos.patrimonio and km_concluso_busca is null", nativeQuery = true)
	String[] veiculosComServicoAberto();
	@Query(value = "select id_ordem from t_veiculos, ordem_servico\r\n"
			+ "where ordem_servico.veiculo=t_veiculos.patrimonio\r\n"
			+ "and t_veiculos.placa_atual LIKE ?1", nativeQuery = true)
	Long ordemServicoPorVeiculo(String placa);
	

	

}

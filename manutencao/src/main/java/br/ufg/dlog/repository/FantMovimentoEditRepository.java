package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantMovimentoEdit;

@Repository
public interface FantMovimentoEditRepository extends JpaRepository<FantMovimentoEdit, Long> {
	
	@Query(value = "select t_saida1.cd_saida1,t_saida1.data_est,t_saida1.s_destino,t_requisicao.requisicao,orgao.sg_orgao,t_veiculos.placa_atual,\r\n"
			+ "t_funcionarios.pri_nome,t_evento.evento, t_saida1.km_r, t_saida1.km_s, t_saida1.km_ro\r\n"
			+ "from t_saida1,t_evento, t_funcionarios,orgao,t_requisicao, t_veiculos\r\n"
			+ "where data_est>=?1 and data_est<=?2\r\n"
			+ "and t_saida1.evento_s=t_evento.codigo\r\n"
			+ "and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "and t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "and t_saida1.fk_veiculos=t_veiculos.patrimonio", nativeQuery = true)
	Collection<FantMovimentoEdit> verMovimentoPorDia(Date um,Date dois);
	
	@Query(value = "select t_saida1.cd_saida1,t_saida1.data_est,t_saida1.s_destino,t_requisicao.requisicao,orgao.sg_orgao,t_veiculos.placa_atual,\r\n"
			+ "t_funcionarios.pri_nome,t_evento.evento, t_saida1.km_r, t_saida1.km_s, t_saida1.km_ro\r\n"
			+ "from t_saida1,t_evento, t_funcionarios,orgao,t_requisicao, t_veiculos\r\n"
			+ "where t_saida1.fk_veiculos=?1 and t_saida1.km_r>=?2\r\n"
			+ "and t_saida1.evento_s=t_evento.codigo\r\n"
			+ "and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "and t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "order by t_saida1.data_est", nativeQuery = true)
	Collection<FantMovimentoEdit> verMovimentoPorPlacaKm(String placa, Long km);
	
	@Query(value = "select t_saida1.cd_saida1,t_saida1.data_est,t_saida1.s_destino,t_requisicao.requisicao,orgao.sg_orgao,t_veiculos.placa_atual,\r\n"
			+ "			t_funcionarios.pri_nome,t_evento.evento, t_saida1.km_r, t_saida1.km_s, t_saida1.km_ro\r\n"
			+ "			from t_saida1,t_evento, t_funcionarios,orgao,t_requisicao, t_veiculos\r\n"
			+ "			where t_saida1.cd_saida1=?1\r\n"
			+ "			and t_saida1.evento_s=t_evento.codigo\r\n"
			+ "			and t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "			and t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "			and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "			and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "			order by t_saida1.data_est", nativeQuery = true)
	Collection<FantMovimentoEdit> pesquisaPorId(Long cd_saida);

}

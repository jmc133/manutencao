package br.ufg.dlog.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufg.dlog.classes.FantMovimento;
import br.ufg.dlog.classes.FantMovimentoDataString;

@Repository
public interface FantMovimentoRepository extends  JpaRepository<FantMovimento, Long>{
	@Query(value = "select t_saida1.cd_saida1,t_requisicao.requisicao,data_est, t_saida1.data_s,t_saida1.data_r,t_veiculos.placa_atual,\r\n"
			+ "					t_saida1.s_destino,t_saida1.km_s,t_saida1.km_r,t_saida1.km_ro,orgao.sg_orgao,t_evento.evento,t_saida1.s_solicitante,\r\n"
			+ "					t_saida1.s_executado,t_funcionarios.nm_pessoa_func\r\n"
			+ "					from t_saida1,t_funcionarios,t_requisicao,orgao, t_veiculos, t_evento\r\n"
			+ "					where t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "					and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "					and t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "					and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "					and t_saida1.evento_s=t_evento.codigo\r\n"
			+ "					and t_saida1.data_est>=?1\r\n"
			+ "					and t_saida1.data_est - interval '1 days' < ?2", nativeQuery = true)
	 Collection<FantMovimento> buscaEntreDatasEstipuladas(Date Datainicial, Date Datafinal);
	
	@Query(value = "select t_saida1.cd_saida1,t_requisicao.requisicao,data_est, t_saida1.data_s,t_saida1.data_r,t_veiculos.placa_atual,\r\n"
			+ "				t_saida1.s_destino,t_saida1.km_s,t_saida1.km_r,t_saida1.km_ro,orgao.sg_orgao,t_evento.evento,t_saida1.s_solicitante,\r\n"
			+ "				t_saida1.s_executado,t_funcionarios.nm_pessoa_func\r\n"
			+ "				from t_saida1,t_funcionarios,t_requisicao,orgao, t_veiculos, t_evento\r\n"
			+ "				where t_saida1.fk_motorista=t_funcionarios.cd_pessoa_func\r\n"
			+ "				and t_saida1.fk_req=t_requisicao.cod_req\r\n"
			+ "				and t_saida1.fk_uni=orgao.cd_orgao\r\n"
			+ "				and t_saida1.fk_veiculos=t_veiculos.patrimonio\r\n"
			+ "				and t_saida1.evento_s=t_evento.codigo\r\n"
			+ "				and t_saida1.data_est>=current_date - interval'36 Hours'", nativeQuery = true)
	 Collection<FantMovimento> buscaApartirDeHoje();
	


}
